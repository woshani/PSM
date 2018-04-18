<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="errorPage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="includes/header.jsp"></jsp:include>
<script type="text/javascript">
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
			// Validate Date Of Birth From Ic
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

	function loadCheckGuardianIc(str2) {

		alertify
				.confirm(
						"Anda ingin membuat Semakan Maklumat Ibubapa / Penjaga? <br /> ( Nombor Kad Pengenalan : "
								+ str2 + " )",
						//if confirm = ok
						function() {

							//check ic value contain only number
							var guardianIc = document.forms["UpdateGuardianForm"]["guardianIc"].value;
							var regex = /^[0-9]+$/;

							var resultValidateIC = validateIC(guardianIc);
							var resultValidateParentAge = validateParentAge(guardianIc);

							if (guardianIc.length != 12) {
								var x = document.getElementById("divCheckIc");
								if (x.style.display === "block") {
									x.style.display = "none";
								}
								alertify
										.error('No Kad Pengenalan IbuBapa / Penjaga tidak boleh kurang dari 12 aksara.');
								document.getElementById("semakBtnStatus").value=0;
								document.getElementById("guardianIc").focus();
								

							} else if (!guardianIc.match(regex)) {
								var x = document.getElementById("divCheckIc");
								if (x.style.display === "block") {
									x.style.display = "none";
								}
								alertify
										.error('No Kad Pengenalan IbuBapa / Penjaga tidak boleh mengandungi sebarang huruf atau simbol.');
								document.getElementById("semakBtnStatus").value=0;
								document.getElementById("guardianIc").focus();

							} else if (resultValidateIC == false) {
								var x = document.getElementById("divCheckIc");
								if (x.style.display === "block") {
									x.style.display = "none";
								}
								alertify
										.error('No Kad Pengenalan TIDAK SAH. Sila semak semula');
								document.getElementById("semakBtnStatus").value=0;
								document.getElementById("guardianIc").focus();

							} else if (resultValidateParentAge == false) {
								var x = document.getElementById("divCheckIc");
								if (x.style.display === "block") {
									x.style.display = "none";
								}
								alertify
										.error('Umur IbuBapa/Penjaga kurang dari 18 tahun. IbuBapa/Penjaga mestilah berumur 18 tahun keatas.');
								document.getElementById("semakBtnStatus").value=0;
								document.getElementById("guardianIc").focus();
								
							} else {
								document.forms["UpdateGuardianForm"]["semakBtnStatus"].value = 1;
								var x = document.getElementById("divCheckIc");
								if (x.style.display === "none") {
									x.style.display = "block";
								}
								document.getElementById("guardianICStated").value = document.getElementById("guardianIc").value;
								alertify
										.success('Nombor Kad Pengenalan Telah Berjaya Disahkan.');
								

							}
						},
						function() {
							var x = document.getElementById("divCheckIc");
							if (x.style.display === "block") {
								x.style.display = "none";
							}
							alertify
									.error('Sila buat Semakan No Kad Pengenalan Penjaga sebelum mendaftar.');
							document.getElementById("guardianIc").focus();

						});
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

	function updateGuardianFormValidation() {

		var resultSemakan = document.getElementById("semakBtnStatus").value;

		//phone no check
		var phoneNoLengthStatus = validatePhoneNoLength();
		var phoneNoNumberStatus = validatePhoneNoNumberOnly();
		
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
			
		} else if (resultSemakan == "1") {

			return true;
			
		} else {

			alertify
					.alert(
							"Sila buat Semakan No Kad Pengenalan Penjaga sebelum menyimpan maklumat.",
							function() {
								alertify
										.error('Sila klik pada butang SEMAK untuk menyemak No Kad Pengenalan Penjaga di Bahagian Maklumat Penjaga');
							});
			document.getElementById("guardianIc").focus();
			return false;
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
</script>
</head>
<body>
	<jsp:include page="includes/banner.jsp"></jsp:include>
	<jsp:include page="includes/menu.jsp"></jsp:include>

	<div id="page" class="container">
		<div id="box1">
			<h2 class="title" align="center">Kemaskini Maklumat Ibu Bapa /
				Penjaga</h2>
			<div style="clear: both;">&nbsp;</div>


			<%
				if (request.getParameter("id") != null) {
			%>
			<jsp:include page="contents/c_update_guardian_profile.jsp"></jsp:include>
			<%
				}
			%>
		</div>
	</div>

	<jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>