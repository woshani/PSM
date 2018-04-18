<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="bean.Religion"%>
<%@ page import="bean.Race"%>
<%@ page import="bean.Teacher"%>
<%@ page import="bean.Classroom"%>
<%@ page import="facade.ControllerWrapper"%>
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
		//pass value to disabled editting on studentIc
		var status = true;
		if (xmlhttp.readyState == 4) {
			document.getElementById("infoPelajar").innerHTML = xmlhttp.responseText;
			if (status) {
				var studentIc = document.forms["AddStudentForm"]["studentIc"].value.length;
				alert(studentIc);
				if (studentIc != 0) {
					document.getElementById("studentIc").readOnly = true;
					//alert(null);
				}
			}
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

		// Form URL object to get sub category
		var url = "CheckStudentIc";
		url = url + "?studentIc=" + str;

		//xmlhttp.onreadystatechange = stateChangedLoadStudentCheckIc;
		xmlhttp.open("POST", url, true);
		xmlhttp.send(null);
	}

	function stateChangedLoadGuardianCheckIc() {
		//pass value to disabled editting on guardianIc
		var status2 = true;
		if (xmlhttp2.readyState == 4) {
			document.getElementById("infoPenjaga").innerHTML = xmlhttp2.responseText;
			if (status2) {
				var guardianIcElement = document.getElementById("guardianIc");
				var guardianIc = guardianIcElement.value.length;
				alert(guardianIc);
				if (guardianIc != 0) {
					document.getElementById("guardianIc").readOnly = true;
				}
			}
		}
	}

	function loadCheckGuardianIc(str2) {

		// Get browser object
		xmlhttp2 = GetXmlHttpObject2();

		// Validate browser
		if (xmlhttp2 == null) {

			alert("Your browser does not support AJAX!");
			return;
		}

		// Form URL object to get sub category
		var url2 = "CheckGuardianIc";
		url2 = url2 + "?guardianIc=" + str2;

		xmlhttp2.onreadystatechange = stateChangedLoadGuardianCheckIc;
		xmlhttp2.open("GET", url2, true);
		xmlhttp2.send(null);
		//alert(url2);
	}

	function loadStudentImage() {
		var FileSize = event.target.files[0].size / 1024 / 1024; // in MB
		if (FileSize >= 0.03) {
			alert('Saiz data gambar yang dipilih melebihi dari 30KB, Sila pastikan gambar mestilah bersaiz 30KB kebawah');
			document.getElementById("student-img").value = "";
		} else {

			var val = $('input[type=file]').val().toLowerCase();
			var regex = new RegExp("(.*?).(jpg|jpeg|png)$");
			if (!(regex.test(val))) {
				document.getElementById("student-img").value = "";
				alert('Format gambar pelajar yang dimasukkan TIDAK SAH! Sila masukkan gambar berformat .jpg .jpeg .png sahaja.');
			} else {

				var reader = new FileReader();
				reader.onload = function() {
					var output = document.getElementById('student-img-tag');
					if (output && output.style) {
						output.style.height = '150px';
						output.style.width = '150px';
					}
					output.src = reader.result;
				};
				reader.readAsDataURL(event.target.files[0]);
			}
		}
	};
</script>

<%
	Integer error = (Integer) request.getAttribute("status");

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
<form id="AddStudentForm" enctype="multipart/form-data" action="#">
	<!-- <form id="addStudent" method="post" action="AddStudent"> -->
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
					name="studentIc" id="studentIc" maxlength="12" required
					tabindex="1" /></td>
				<td width="405" align="left"><input type="button" value="SEMAK"
					name="checkStudentBtn"
					onclick="loadCheckStudentIc(studentIc.value)" class="blackbutton"></input></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td>(contoh: 020125109999)</td>
				<td></td>
			</tr>
		</table>
		<br></br>
		<% if (!error.equals(null)){
			
			if (error == 0) {
				%>
				<table width="100%" border="0" cellpadding="5">
					<tr height="20">
						<td width="500" align="center"><strong>Kesalahan Dikesan
								: </strong><font color="red" size="2"> Tiada data untuk pengesahan.
								Sila masukkan Nombor MyKad / MyKid Pelajar untuk tujuan semakan
								didalam sistem.</font></td>
					</tr>
				</table>
				<%
					} else if (error == 1) {
				%>
				
				<table width="100%" border="0" cellpadding="5">
					<tr height="20">
						<td width="500" align="center"><strong>Kesalahan Dikesan
								: </strong><font color="red" size="2"> Pelajar tersebut sudah mendaftar
								kelas untuk tahun ini didalam sistem. Sila semak semula IC atau tunggu sehingga guru kelas lama
								memindahkannya keluar dari kelas.</font></td>
					</tr>
				</table>
				<%
					}
				
				}
		
			%>
			<div id="infoPelajar" name="infoPelajar"></div>
			<br></br>
	</fieldset>
	<br></br>
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
					name="guardianIc" id="guardianIc" maxlength="12" required
					tabindex="1" /></td>
				<td width="405" align="left"><input type="button" value="SEMAK"
					name="checkGuardianBtn"
					onclick="loadCheckGuardianIc(guardianIc.value)" class="blackbutton"></input></td>
			</tr>
			<tr>
				<td></td>
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
	<div align="right">
		<input type="submit" name="addStudentBtn" value="Daftar"
			class="blackbutton" />
	</div>

</form>
