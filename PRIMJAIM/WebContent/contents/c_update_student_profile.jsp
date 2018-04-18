<%@page import="java.security.InvalidKeyException"%>
<%@page import="javax.crypto.NoSuchPaddingException"%>
<%@page import="java.security.NoSuchAlgorithmException"%>
<%@page import="controller.URLSecurity"%>
<%@page import="facade.ControllerWrapper"%>
<%@page import="bean.Student"%>

<div class="entry">
	<script type="text/javascript">
		
		function updateStudentFormValidation(){
			
			//check ic value contain only number
			var studentIc = document.forms["UpdateStudentForm"]["studentIC"].value;
			var regex = /^[0-9]+$/;
			var resultValidateIC = validateIC(studentIc);
			var resultValidateStudentAge = validateStudentAge(studentIc);
			
			if (studentIc.length != 12) {
				alertify
						.error('No Kad Pengenalan Pelajar tidak boleh kurang dari 12 aksara.');
				document.getElementById("studentIC").focus();
				
				return false;
													
			} else if (!studentIc.match(regex)) {
				alertify
						.error('No Kad Pengenalan Pelajar tidak boleh mengandungi sebarang huruf atau simbol.');
				document.getElementById("studentIC").focus();
				
				return false;
								
			} else if (resultValidateIC == false) {
				
				alertify
						.error('No Kad Pengenalan TIDAK SAH. Sila semak semula');
				
				document.getElementById("studentIC").focus();
				
				return false;
				

			} else if (resultValidateStudentAge == false) {
				
				alertify
						.error('Umur Pelajar mestilah didalam lingkungan 7-12 tahun.');
				
				document.getElementById("studentIC").focus();
				
				return false;
				
			} else if(resultValidateStudentAge === true && resultValidateIC === true ) {
				
				var dob = getDOBFromStudentIc(studentIc);
				document.getElementById("dob").value = dob;
				
				console.log("DOB : "+dob);
				
				alertify.success('Nombor Kad Pengenalan Pelajar Telah Berjaya Disahkan.');
				
				return true;
			}
		
			return false;
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
		
	</script>
	<%
		try {
			String pinKey = String.valueOf(session.getAttribute("pinKey"));
			String studentIdEnc = request.getParameter("id");
			String studentId = URLSecurity.decryptFinal(studentIdEnc,
					pinKey);

			ControllerWrapper controller = new ControllerWrapper();
			Student student = new Student();

			student = controller.getStudentById(studentId);

			if (student.getStudent_id() != null) {
	%>
	<div id="accordion" style="width: 1100px;">
		<h3>KEMASKINI MAKLUMAT PELAJAR</h3>
		<div>
			<font color="red">* </font>Maklumat yang WAJIB diisi. <br /> <br />
			<br />
			<form id="UpdateStudentForm" method="POST" action="UpdateStudent"
				onsubmit="return updateStudentFormValidation();">

				<table width="1000px" border="0" cellpadding="5">
					<tr>
						<td width="200px"><font color="red">* </font><strong>NAMA
								PENUH </br>(Seperti Kad Pengenalan)
						</strong></td>
						<td width="5px">:</td>
						<td><input type="text" id="name" name="name"
							style="width: 500px;" value="<%=student.getName()%>" required></input></td>
					</tr>
					<tr>
						<td><font color="red">* </font><strong>NO. KAD
								PENGENALAN <br />(12 digit sahaja)
						</strong></td>
						<td>:</td>
						<td><input type="text" id="studentIC" name="studentIC"
							value="<%=student.getIc_number()%>" maxlength="12"
							required></input> (contoh: 110125109999)
							<span id="checkICResult"></span></td>
					</tr>
					<tr>
						<td><strong>NO. SIJIL SURAT BERANAK</strong></td>
						<td>:</td>
						<td><input type="text" id="birthCert" name="birthCert"
							value="<%=student.getBirth_cert()%>" required></input> (Sila
							masukkan tanda "-" jika tiada maklumat)</td>
					</tr>
					<tr>
						<td><font color="red">* </font><strong>JANTINA</strong></td>
						<td width="5px">:</td>
						<%
							if (student.getGender().equalsIgnoreCase("LELAKI")) {
						%>
						<td><input type="radio" id="gender" name="gender" value="L"
							checked> Lelaki<br> <input type="radio" id="gender"
							name="gender" value="P"> Perempuan<br></td>
						<%
							} else if (student.getGender()
											.equalsIgnoreCase("PEREMPUAN")) {
						%>
						<td><input type="radio" id="gender" name="gender" value="L">
							Lelaki<br> <input type="radio" id="gender" name="gender"
							value="P" checked> Perempuan<br></td>
						<%
							}
						%>
					</tr>
					<tr>
						<td><font color="red">* </font><strong>BANGSA</strong></td>
						<td width="5px">:</td>
						<td><select name="race" required>
								<option selected="selected"
									value="<%=student.getRace().getId()%>"><%=student.getRace().getRace()%></option>
								<option value="1">Melayu/Bumiputera</option>
								<option value="2">Cina</option>
								<option value="3">India</option>
								<option value="4">Lain-Lain</option>
						</select></td>
					</tr>
					<tr>
						<td><font color="red">* </font><strong>AGAMA</strong></td>
						<td width="5px">:</td>
						<td><select name="religion" required>
								<option value="1">Islam</option>
								<option value="2">Buddha</option>
								<option value="3">Hindu</option>
								<option value="4">Kristian</option>
								<option value="5">Taoisme</option>
								<option value="6">Lain-lain</option>
								<option value="7">Tiada</option>
						</select></td>
					</tr>
					<tr>
						<td><font color="red">* </font><strong>TEMPAT LAHIR</strong></td>
						<td width="5px">:</td>
						<td><textarea id="placeOfBirth" name="placeOfBirth"
								maxlength="148" rows="5" cols="50" required><%=student.getPlace_of_birth()%></textarea><br />
							(Sila masukkan tanda "-" jika tiada maklumat)</td>
					</tr>
					<tr>
						<td><input type="hidden" id="studentId" name="studentId"
							value="<%=student.getStudent_id()%>"></input></td>
						<td><input type="hidden" id="dob" name="dob"
							value="<%=student.getDate_of_birth()%>"></input></td>
						<td></td>
						<td align="right"><input type="submit" name="updateStudent" 
							value="Kemaskini" class="blackbutton" /></td>
					</tr>
				</table>
			</form>
		</div>

		<%
			} else {
		%>
		<p>
			Maklumat murid <b><font color="red">TIADA</font></b> dalam sistem
			maklumat.
		</p>
		<%
			}

			} catch (NoSuchAlgorithmException e) {
				System.out.println("No Such Algorithm:" + e.getMessage());
				return;
			} catch (NoSuchPaddingException e) {
				System.out.println("No Such Padding:" + e.getMessage());
				return;
			} catch (InvalidKeyException e) {
				System.out.println("Invalid Key:" + e.getMessage());
				return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		%>
	</div>