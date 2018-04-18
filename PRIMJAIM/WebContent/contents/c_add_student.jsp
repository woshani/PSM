<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="bean.Religion"%>
<%@ page import="bean.Race"%>
<%@ page import="bean.Teacher"%>
<%@ page import="bean.Classroom"%>
<%@ page import="facade.ControllerWrapper"%>

<style>
tr.d0 td {
	background-color: #FFF;
	color: black;
}

tr.d1 td {
	background-color: #F8F8F8;
	color: black;
}

.on {
	background-color: #c6d6e5;
	cursor: pointer;
}

.off0 {
	background-color:  
}

.off1 {
	background-color: #FFF;
}
</style>

<script type="text/javascript">
	var xmlhttp;
	var xmlhttp2;

	function isEmpty(str) {
		return !str.replace(/^\s+/g, '').length; // boolean (`true` if field is empty)
	}

	function GetXmlHttpObject() {
		if (window.XMLHttpRequest) {
			// code for IE7+, Firefox, Chrome, Opera Safari
			return new XMLHttpRequest();
		}
		if (window.ActiveXObject) {

			// code for IE6, IE5
			return new ActiveXObject("Mictrosoft.XMLHTTP");
		}
		return null;
	}

	function GetXmlHttpObject2() {
		if (window.XMLHttpRequest) {
			// code for IE7+, Firefox, Chrome, Opera Safari
			return new XMLHttpRequest();
		}
		if (window.ActiveXObject) {

			// code for IE6, IE5
			return new ActiveXObject("Mictrosoft.XMLHTTP");
		}
		return null;
	}

	function stateChangedLoadStudentCheckIc() {

		if (xmlhttp.readyState == 4) {
			document.getElementById("infoPelajar").innerHTML = xmlhttp.responseText;
			
		}
	}

	function loadCheckStudentIc(str) {

		// Get browser object
		xmlhttp = GetXmlHttpObject();

		// Validate browser
		if (xmlhttp == null) {

			alert("Your browser does not support AJAX!");
			return;
		}

		alertify
				.confirm(
						"Anda ingin membuat Semakan Maklumat Pelajar? <br /> ( Nombor Kad Pengenalan : "
								+ str + " )",
						//if confirm = ok
						function() {

							
							//check ic value contain only number
							var studentIc = document.forms["AddStudentForm"]["studentIc"].value;
							var regex = /^[0-9]+$/;
							var resultValidateIC = validateIC(studentIc);
							var resultValidateStudentAge = validateStudentAge(studentIc);
							
							if (studentIc.length != 12) {
								var x = document.getElementById("divCheckIc2");
								if (x.style.display === "block") {
									x.style.display = "none";
								}
								alertify
										.error('No Kad Pengenalan Pelajar tidak boleh kurang dari 12 aksara.');
								document.getElementById("semakBtnStatus").value = 0;
								document.getElementById("studentIc").focus();
								var errorHtmlCode = '<table width="100%" border="0" cellpadding="5"><tr height="20"><td width="500" align="center"><strong>Kesalahan Dikesan : </strong><font color="red" size="2"> No Kad Pengenalan Pelajar tidak boleh kurang dari 12 aksara.</font></td></tr></table>';
								document.getElementById("infoPelajar").innerHTML = errorHtmlCode;
								
							} else if (!studentIc.match(regex)) {
								var x = document.getElementById("divCheckIc2");
								if (x.style.display === "block") {
									x.style.display = "none";
								}
								alertify
										.error('No Kad Pengenalan Pelajar tidak boleh mengandungi sebarang huruf atau simbol.');
								document.getElementById("semakBtnStatus").value = 0;
								document.getElementById("studentIc").focus();
								var errorHtmlCode = '<table width="100%" border="0" cellpadding="5"><tr height="20"><td width="500" align="center"><strong>Kesalahan Dikesan : </strong><font color="red" size="2"> No Kad Pengenalan Pelajar tidak boleh mengandungi sebarang huruf atau simbol.</font></td></tr></table>';
								document.getElementById("infoPelajar").innerHTML = errorHtmlCode;
								
							}  else if (resultValidateIC == false) {
								var x = document.getElementById("divCheckIc2");
								if (x.style.display === "block") {
									x.style.display = "none";
								}
								alertify
										.error('No Kad Pengenalan TIDAK SAH. Sila semak semula');
								document.getElementById("semakBtnStatus").value = 0;
								document.getElementById("studentIc").focus();
								var errorHtmlCode = '<table width="100%" border="0" cellpadding="5"><tr height="20"><td width="500" align="center"><strong>Kesalahan Dikesan : </strong><font color="red" size="2"> No Kad Pengenalan TIDAK SAH. (6 aksara pertama yang dimasukkan TIDAK tepat) Sila SEMAK No. Kad Pengenalan Pelajar semula.</font></td></tr></table>';
								document.getElementById("infoPelajar").innerHTML = errorHtmlCode;

							} else if (resultValidateStudentAge == false) {
								var x = document.getElementById("divCheckIc2");
								if (x.style.display === "block") {
									x.style.display = "none";
								}
								alertify
										.error('Maklumat Kad Pengenalan Pelajar Tidak Tepat. Umur Pelajar mestilah dalam lingkungan 7 ke 12 tahun.');
								document.getElementById("semakBtnStatus").value = 0;
								document.getElementById("studentIc").focus();
								
								var errorHtmlCode = '<table width="100%" border="0" cellpadding="5"><tr height="20"><td width="500" align="center"><strong>Kesalahan Dikesan : </strong><font color="red" size="2"> Maklumat Kad Pengenalan Pelajar Tidak Tepat. Umur Pelajar mestilah dalam lingkungan 7 ke 12 tahun.</font></td></tr></table>';
								document.getElementById("infoPelajar").innerHTML = errorHtmlCode;

							} else {

								var dob = getDOBFromStudentIc(studentIc);
								document.getElementById("dob").value = dob;
								
								console.log("DOB : "+dob);
								
								$.ajax({
									url : 'CheckIcStudentAjax',
									type : 'POST',
									dataType : 'json',
									data : {
										'studentIc' : str
									}, 
									
									success : function(reply_data) {
										//console.log("Berjaya");
										//console.log(reply_data.statusIcStudent);
										statusIcStudent = reply_data.statusIcStudent;
										
										if(statusIcStudent===2){
											
											var x = document.getElementById("divCheckIc2");
											x.style.display = "none";
																				
											document.getElementById("semakBtnStatus").value = 0;

											alertify.error('Pelajar Bernombor Kad Pengenalan : <br/>'+str+' <br/>Telah Berdaftar Sekolah untuk tahun ini.');
											
											var errorHtmlCode = '<table width="100%" border="0" cellpadding="5"><tr height="20"><td width="500" align="center"><strong>Kesalahan Dikesan : </strong><font color="red" size="2"> Pelajar Bernombor Kad Pengenalan : <b>'+str+' </b>Telah Berdaftar Sekolah untuk tahun ini.</font></td></tr></table>';
											document.getElementById("infoPelajar").innerHTML = errorHtmlCode;
										}else if(statusIcStudent===1){
											
											alertify.success('Nombor MyKad/MyKid Pelajar telah Berjaya disahkan.');
											document.getElementById("studentICStated").value = document.getElementById("studentIc").value;
											
											var x = document.getElementById("divCheckIc2");
											x.style.display = "block";
											
											document.getElementById("semakBtnStatus").value = 1;
											
										}
										
									},
									error : function(status, error) {
										alert(status+" "+error);
									}
								});
								
								var url = "CheckStudentIc";
								url = url + "?studentIc=" + str;
								xmlhttp.onreadystatechange = stateChangedLoadStudentCheckIc;

								xmlhttp.open("POST", url, true);
								xmlhttp.send(null);
								
		
							}
						},
						function() {
							alertify
									.error('Sila buat Semakan No Kad Pengenalan Pelajar sebelum mendaftar.');
							document.getElementById("semakBtnStatus").value = 0;
							document.getElementById("studentIc").focus();
						});
	}

	function stateChangedLoadGuardianCheckIc() {

		if (xmlhttp2.readyState == 4) {
			document.getElementById("infoPenjaga").innerHTML = xmlhttp2.responseText;
			document.getElementById("semakBtnStatus2").value = "1";
			
			//var guardianIcElement = document.getElementById("guardianIc");
			//var guardianIc = guardianIcElement.value.length;
			//alert(guardianIc);
			//if (guardianIc != 0) {
			//	document.getElementById("guardianIc").readOnly = true;
			//}
			document.getElementById("guardianICStated").value = document.forms["AddStudentForm"]["guardianIc"].value;
			
		}
	}

	function loadCheckGuardianIc(str2) {

		// Get browser object
		xmlhttp2 = GetXmlHttpObject();

		// Validate browser
		if (xmlhttp2 == null) {

			alert("Your browser does not support AJAX!");
			return;
		}

		alertify
				.confirm(
						"Anda ingin membuat Semakan Maklumat Ibubapa / Penjaga? <br /> ( Nombor Kad Pengenalan : "
								+ str2 + " )",
						//if confirm = ok
						function() {

							//check ic value contain only number
							var guardianIc = document.forms["AddStudentForm"]["guardianIc"].value;
							var regex = /^[0-9]+$/;
							var resultValidateIC = validateIC(guardianIc);
							var resultValidateParentAge = validateParentAge(guardianIc);
							
							if (guardianIc.length != 12) {
								alertify
										.error('No Kad Pengenalan IbuBapa / Penjaga tidak boleh kurang dari 12 aksara.');
								document.getElementById("guardianIc").focus();
								document.getElementById("semakBtnStatus2").value = 0;
								document.getElementById("infoPenjaga").innerHTML="";
													
							} else if (!guardianIc.match(regex)) {
								alertify
										.error('No Kad Pengenalan IbuBapa / Penjaga tidak boleh mengandungi sebarang huruf atau simbol.');
								document.getElementById("guardianIc").focus();
								document.getElementById("semakBtnStatus2").value = 0;
								document.getElementById("infoPenjaga").innerHTML="";
												
							} else if (resultValidateIC == false) {
								var x = document.getElementById("divCheckIc");
								if (x.style.display === "block") {
									x.style.display = "none";
								}
								alertify
										.error('No Kad Pengenalan TIDAK SAH. Sila semak semula');
								document.getElementById("semakBtnStatus2").value = 0;
								document.getElementById("guardianIc").focus();
								document.getElementById("infoPenjaga").innerHTML="";

							} else if (resultValidateParentAge == false) {
								var x = document.getElementById("divCheckIc");
								if (x.style.display === "block") {
									x.style.display = "none";
								}
								alertify
										.error('Umur IbuBapa/Penjaga kurang dari 18 tahun. IbuBapa/Penjaga mestilah berumur 18 tahun keatas.');
								document.getElementById("semakBtnStatus2").value = 0;
								document.getElementById("guardianIc").focus();
								document.getElementById("infoPenjaga").innerHTML="";

							} else {

								alertify.success('Nombor Kad Pengenalan Penjaga Telah Berjaya Disahkan.');
								
								var x = document.getElementById("divCheckIc");
								if (x.style.display === "none") {
									x.style.display = "block";
								}

								var url = "CheckGuardianIc";
								url = url + "?guardianIc=" + str2;

								xmlhttp2.onreadystatechange = stateChangedLoadGuardianCheckIc;

								xmlhttp2.open("POST", url, true);
								xmlhttp2.send(null);
							}
						},
						function() {
							alertify
									.error('Sila buat Semakan No Kad Pengenalan Penjaga sebelum mendaftar.');
							document.getElementById("semakBtnStatus2").value = 0;
							document.getElementById("guardianIc").focus();
							
						});
	}

	function loadStudentImage() {

		var FileSize = event.target.files[0].size / 1024 / 1024; // in MB

		var val = $('input[type=file]').val().toLowerCase();
		var regex = new RegExp("(.*?).(jpg|jpeg|png)$");

		if (FileSize >= 0.03) {
			alertify
					.alert(
							"Saiz data gambar yang dipilih melebihi dari 30KB, Sila pastikan gambar mestilah bersaiz 30KB kebawah.",
							function() {
								alertify
										.error('Anda boleh menggunakan perisian PAINT untuk menukar saiz gambar.');
							});
			document.getElementById("student-img").value = "";
		}

		if (!(regex.test(val))) {
			document.getElementById("student-img").value = "";
			alertify
					.alert(
							"Format gambar pelajar yang dimasukkan TIDAK SAH! Sila masukkan gambar berformat .jpg .jpeg .png sahaja.",
							function() {
								alertify
										.error('Anda boleh menggunakan perisian PAINT untuk menukar format gambar.');
							});
		} else {

			var reader = new FileReader();

			reader.onload = function() {
				var output = document.getElementById('student-img-tag');
				var output2 = document.getElementById('student-img');
				if (output && output.style) {
					output.style.height = '150px';
					output.style.width = '150px';
				}
				output.src = reader.result;
				output2.src = reader.result;
			};

			reader.readAsDataURL(event.target.files[0]);
		}

	}
	
	function validateIC(ic) {

		//var ic = '160229111111';
		if (ic.match(/^(\d{2})(\d{2})(\d{2})(\d{2})(\d{4})$/)) {

			var year = RegExp.$1;
			var month = RegExp.$2;
			var day = RegExp.$3;
			var country = RegExp.$4;
			var counter = RegExp.$5;
			//console.log(year, month, day);

			// *******************************************************
			// Validate Date Of Birth From Ic Macth posibble date or not
			// *******************************************************
			var now = new Date().getFullYear().toString();
			var decade = now.substr(0, 2);
			// get decade "20" if year below or equal "18", year ="2018" or "18"
			if (now.substr(2, 2) >= year) {
				year = parseInt(decade.concat(year.toString()));
			}
			if (year.toString().length != 4) {
				year = parseInt("19" + year);
			}

			var vDay = parseInt(day.toString());
			var vMonth = parseInt(month.toString());
			var vYear = parseInt(year.toString());

			if (isDate(vDay, vMonth, vYear)) {
				//console.log("ValidateIc : true");
				return true;
			} else {
				//console.log("ValidateIc : false1");
				return false;
			}

		} else {
			//console.log("ValidateIc : false2");
			return false;
		}

	}
	
	function getDOBFromStudentIc(studentIc){
		
		var result = "";
		
		if (studentIc.match(/^(\d{2})(\d{2})(\d{2})(\d{2})(\d{4})$/)) {
			
			var year = RegExp.$1;
			var month = RegExp.$2;
			var day = RegExp.$3;
			
			result = day+""+month+""+year;
		}
		console.log("GetDOBFromIc : "+result);
		return result;
		
	}
	
	function validateStudentAge(ic) {

		if (ic.match(/^(\d{2})(\d{2})(\d{2})(\d{2})(\d{4})$/)) {

			var year = RegExp.$1;
			var month = RegExp.$2;
			var day = RegExp.$3;
			var country = RegExp.$4;
			var counter = RegExp.$5;
			//console.log(year, month, day);

			// *********************************************************
			// Validate Student Age Must Atleast 7-12 years old only
			// *********************************************************
			var yearRange = getYearRange(year);
			if (yearRange >= 7 && yearRange <= 12) {
				//console.log("ValidateStudentAge : true");
				return true;

			} else {
				//console.log("ValidateStudentAge : false1");
				return false;

			}

			//console.log("Gap year : " + yearRange);
			//console.log(year + ":" + month + ":" + day + ":" + country + ":"+ counter);

		} else {
			//console.log("ValidateStudentAge : false2");
			return false;
		}

	}

	function validateParentAge(ic) {

		if (ic.match(/^(\d{2})(\d{2})(\d{2})(\d{2})(\d{4})$/)) {

			var year = RegExp.$1;
			var month = RegExp.$2;
			var day = RegExp.$3;
			var country = RegExp.$4;
			var counter = RegExp.$5;
			//console.log(year, month, day);

			// *********************************************************
			// Validate Guardian Age Must Atleast 18 years old and above
			// *********************************************************
			var yearRange = getYearRange(year);
			if (yearRange >= 18) {
				//console.log("ValidateParentAge : true");
				return true;

			} else {
				//console.log("ValidateParentAge : false1");
				return false;

			}

			//console.log("Gap year : " + yearRange);
			//console.log(year + ":" + month + ":" + day + ":" + country + ":"+ counter);

		} else {
			//console.log("ValidateParentAge : false2");
			return false;
		}

	}

	function isDate(day, month, year) {

		if (day <= 0) {
			return false;
		}
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			if (day > 31) {
				return false;
			}
			return true;
		case 2:
			if (year % 4 == 0) {
				if (day > 29) {
					return false;
				} else {
					return true;
				}
			}
			if (day > 28) {
				return false;
			}
			return true;
		case 4:
		case 6:
		case 9:
		case 11:
			if (day > 30) {
				return false;
			}
			return true;
		default:
			return false;
		}
	}
	
	function getYearRangeStudent(year) {

		//get current date
		var dateNowRaw = new Date(Date.now());

		//var dateNow = new Date(dateNowRaw.getFullYear(), dateNowRaw.getMonth(), dateNowRaw.getDay(), 0, 0, 0, 0);
		var dateNow = new Date(dateNowRaw.getFullYear(), 0, 1, 0, 0, 0, 0);

		var now = new Date().getFullYear().toString();

		// get "20" from "2018"
		var decade = now.substr(0, 2);

		// get decade "20" if year below or equal "18", year ="2018" or "18"
		if (now.substr(2, 2) >= year) {

			year = parseInt(decade.concat(year.toString()));
			//console.log("new year : " + year.toString());
		}

		// convert year if 2 char ("18") = 1918, else 4 char = 2018
		//var date = new Date(year, (month - 1), day, 0, 0, 0, 0);
		var date = new Date(year, 0, 1, 0, 0, 0, 0);

		//get range gap
		var yearRange = diff_years(date, dateNow);

		return yearRange;
	}

	function getYearRange(year) {

		//get current date
		var dateNowRaw = new Date(Date.now());

		//var dateNow = new Date(dateNowRaw.getFullYear(), dateNowRaw.getMonth(), dateNowRaw.getDay(), 0, 0, 0, 0);
		var dateNow = new Date(dateNowRaw.getFullYear(), 0, 1, 0, 0, 0, 0);

		var now = new Date().getFullYear().toString();

		// get "20" from "2018"
		var decade = now.substr(0, 2);

		// get decade "20" if year below or equal "18", year ="2018" or "18"
		if (now.substr(2, 2) >= year) {

			year = parseInt(decade.concat(year.toString()));
			//console.log("new year : " + year.toString());
		}

		// convert year if 2 char ("18") = 1918, else 4 char = 2018
		//var date = new Date(year, (month - 1), day, 0, 0, 0, 0);
		var date = new Date(year, 0, 1, 0, 0, 0, 0);

		//get range gap
		var yearRange = diff_years(date, dateNow);

		return yearRange;
	}

	function diff_years(dt2, dt1) {

		var diff = (dt2.getTime() - dt1.getTime()) / 1000;
		diff /= (60 * 60 * 24);
		return Math.abs(Math.round(diff / 365.25));

	}


	function addStudentFormValidation() {

		var resultSemakan = document.getElementById("semakBtnStatus").value;
		var resultSemakan2 = document.getElementById("semakBtnStatus2").value;
				
		//phone no check
		var phoneNoLengthStatus = validatePhoneNoLength();
		var phoneNoNumberStatus = validatePhoneNoNumberOnly();
		
		//postcode input check
		var postcodeStatus = validatePostCodeNumberOnly();
		var postcodeLengthStatus = validatePostcodeLength();
		
		// 1 = already been check (semak)
		// 0 = not yet check (semak) 
		if (phoneNoLengthStatus === false) {
			
			document.getElementById("guardianPhoneNo2").focus();
			alertify.error('Nombor Telefon yang dimasukkan Tidak Sah, Sila semak semula Nombor Telefon Penjaga.');
			
			return false;

		} else if (phoneNoNumberStatus === false) {
			
			document.getElementById("guardianPhoneNo2").focus();
			alertify.error('Nombor Telefon Tidak Boleh mempunyai sebarang Huruf atau Simbol');
			
			return false;
			
		}  else if (postcodeLengthStatus === false) {
			
			document.getElementById("postcode").focus();
			alertify.error('Poskod mestilah 5 aksara');
			
			return false;
			
		} else if (postcodeStatus === false) {
			
			document.getElementById("postcode").focus();
			alertify.error('Poskod Tidak Boleh mempunyai sebarang Huruf atau Simbol');
			
			return false;
			
		} 

		// 1 = already been check (semak)
		// 0 = not yet check (semak) 
		else if (resultSemakan == "1") {

			if (resultSemakan2 == "1") {

				return true;

			} else {
				document.getElementById("guardianIc").focus();
				
				alertify
						.alert(
								"Sila buat Semakan No Kad Pengenalan Penjaga sebelum mendaftar.",
								function() {
									alertify
											.error('Sila klik pada butang SEMAK untuk menyemak No Kad Pengenalan Penjaga di Bahagian Maklumat Penjaga');
								});
				

				return false;
			}

		} else {
			document.getElementById("studentIc").focus();
			
			alertify
					.alert(
							"Sila buat Semakan No Kad Pengenalan Pelajar sebelum mendaftar.",
							function() {
								alertify
										.error('Sila klik pada butang SEMAK untuk menyemak No Kad Pengenalan Pelajar  Bahagian Maklumat Diri Pelajar');
							});
			
			

			return false;
		}
	}

	function isEven(value) {
		if (value % 2 == 0) {
			return true;
		} else {
			return false;
		}
	}

	function addRow() {

		// find row to copy
		var table = document.getElementById("tblGuardianContact"); // find table to append to

		var totalRows = table.getElementsByTagName("tr").length;

		//alert(totalRows);

		if (totalRows < 4) {

			if (isEven(totalRows)) {

				var backgroundClass = "d1";

			} else {

				var backgroundClass = "d0";

			}

			var row = table.rows[1];

			var clone = row.cloneNode(true); // copy children too

			clone.setAttribute("class", backgroundClass);
			clone.cells[0].innerHTML = totalRows;
			
			//console.log(clone.cells[3].getElementsByTagName('input')[0].value);
			clone.cells[3].getElementsByTagName('input')[0].value = "";
			clone.cells[4].getElementsByTagName('input')[1].value = "";
			clone.cells[4].getElementsByTagName('input')[2].value = "";

			table.appendChild(clone); // add new row to end of table

		} else {
			alert("Hanya TIGA Nombor Telefon sahaja dibenarkan untuk seorang penjaga")
		}
	}

	function deleteRow(vRow) {

		var i = vRow.parentNode.parentNode.rowIndex;

		var table = document.getElementById("tblGuardianContact");

		var totalRows = table.getElementsByTagName("tr").length;
		totalRows = totalRows - 1;

		if (totalRows == 1) {
			alert("Penjaga mestilah mendaftar sekurang-kurangnya SATU nombor telefon.");
		} else {

			document.getElementById("tblGuardianContact").deleteRow(i);

			//rearrange back number for each rows display
			//alert(totalRows);

			for (i = 1; i < totalRows; i++) {

				table.rows[i].cells[0].innerHTML = i;
			}

		}

	}
	
	function validatePhoneNoLength() {

		var phoneNos = document.getElementsByName("guardianPhoneNo2");
		
		// validate length
		for (i = 0; i < phoneNos.length; i++) {

			var phoneNo = phoneNos[i].value;
			if (phoneNo.length != 7) {
				return false;
				break;
			}

			//console.log(phoneNos[i].value);
			//if () { break; }
		}

		//console.log("phoneNo count : "+phoneNos.length);
		//alert("phoneNo count : "+phoneNos.length);
		return true;
	}
	
	function validatePhoneNoNumberOnly() {

		var phoneNos = document.getElementsByName("guardianPhoneNo2");
		
		var regex = /^[0-9]+$/;
		
		// validate length
		for (i = 0; i < phoneNos.length; i++) {

			var phoneNo = phoneNos[i].value;
			if (!phoneNo.match(regex)) {
				return false;
				break;
			}

			//console.log(phoneNos[i].value);
			//if () { break; }
		}
		
		

		//console.log("phoneNo count : "+phoneNos.length);
		//alert("phoneNo count : "+phoneNos.length);
		return true;
	}
	
	function validatePostcodeLength() {

		var postcode = document.getElementById("postcode").value;
		
		if (postcode.length != 5) {
			return false;
		} else{
			return true;
		}
	}
	
	function validatePostCodeNumberOnly() {

		var postcode = document.getElementById("postcode").value;
		
		var regex = /^[0-9]+$/;
		
		console.log(postcode);
		
		if (!postcode.match(regex)) {
			return false;
		}else{
			return true;
		}

	}
</script>
<%
	// Capture Successful / Error message on Add Process
	String alertMain = (String) request.getSession().getAttribute("alertAddStudent");
	if (alertMain != null) {
%>
<script>
	alertify.alert("<%=alertMain%>");
</script>
<%
	request.getSession().removeAttribute("alertAddStudent");
	}

	Teacher teacher = new Teacher();
	teacher = (Teacher) session.getAttribute("SASteacher");
	ControllerWrapper controller = new ControllerWrapper();
	List<Classroom> classrooms = new ArrayList<Classroom>();

	classrooms = controller.getClassroomsByTeacherId(teacher
			.getTeacher_id());
%>
<hr></hr>
<p>
	<font color="red">*</font> <font color="black"> Maklumat Wajib
		diisi.</font>
</p>
<br></br>
<form id="AddStudentForm" enctype="multipart/form-data"
	onsubmit="return addStudentFormValidation();" action="AddStudent"
	method="POST">

	<fieldset>

		<legend>
			<font color="black">Maklumat Diri Pelajar</font>
		</legend>

		<table width="100%" border="0" cellpadding="5">
			<tr height="20">
				<td width="265" align="right"><font color="red">*</font> <strong>Nombor
						MyKid / MyKad Pelajar</strong></td>
				<td width="5" align="center">:</td>
				<td width="150" align="left"><input type="text"
					style="text-align: center;" name="studentIc" id="studentIc"
					maxlength="12" required tabindex="1" /></td>
				<td width="35" align="left">
					<div id="divCheckIc2" name="divCheckIc2"
						style="width: 35px; height: 35px; display: none;">
						<img style="display: block; width: 100%; height: 100%;"
							src="images/good.png" alt=""></img>
					</div>
				</td>
				<td width="405" align="left"><input type="button" value="SEMAK"
					name="checkStudentBtn"
					onclick="loadCheckStudentIc(studentIc.value)" class="blackbutton"></input></td>
			</tr>
			<tr>
				<td><input type="hidden" id="semakBtnStatus"
					name="semakBtnStatus" value="0"></input> 
					<input type="hidden" id="studentICStated" name="studentICStated"></input>
					<input type="hidden"
					id="dob" name="dob"></input></td>
				<td></td>
				<td>(contoh: 020125109999)</td>
				<td></td>
			</tr>
		</table>
		<br></br>

		<div id="infoPelajar" name="infoPelajar"></div>
		<br></br>
	</fieldset>
	<br></br>
	<!-- Guardian / Parent Information -->
	<fieldset>
		<legend>
			<font color="black">Maklumat IbuBapa / Penjaga</font>
		</legend>
		<table width="100%" border="0" cellpadding="5">
			<tr height="20">
				<td width="265" align="right"><font color="red">*</font> <strong>Nombor
						MyKad Penjaga</strong></td>
				<td width="5" align="center">:</td>
				<td width="150" align="left"><input type="text"
					style="text-align: center;" name="guardianIc" id="guardianIc"
					maxlength="12" required tabindex="1" />
				<td width="35" align="left">
					<div id="divCheckIc" name="divCheckIc"
						style="width: 35px; height: 35px; display: none;">
						<img style="display: block; width: 100%; height: 100%;"
							src="images/good.png" alt=""></img>
					</div>
				</td>
				<td width="405" align="left"><input type="button" value="SEMAK"
					name="checkGuardianBtn"
					onclick="loadCheckGuardianIc(guardianIc.value)" class="blackbutton"></input></td>
			</tr>
			<tr>
				<td><input type="hidden" id="semakBtnStatus2"
					name="semakBtnStatus2" value="0"></input>
					</td>
				<td></td>
				<td>(contoh: 750112109999)</td>
				<td></td>
			</tr>
		</table>
		<br></br>
		<div id="infoPenjaga" name="infoPenjaga"></div>
		<br></br>
	</fieldset>
	<br></br>
	<div align="center">
		<input type="submit" name="addStudentBtn" value="Simpan Maklumat"
			class="blackbutton" />
	</div>

</form>