<%@page import="java.security.InvalidKeyException"%>
<%@page import="javax.crypto.NoSuchPaddingException"%>
<%@page import="java.security.NoSuchAlgorithmException"%>
<%@page import="javax.crypto.SecretKey"%>
<%@page import="controller.URLSecurity"%>
<%@page import="bean.User"%>
<%@page import="bean.Teacher"%>
<%@page import="facade.ControllerWrapper"%>

<div class="entry">

	<script type="text/javascript">
		function ClearFields() {

			document.getElementById("teacherIC").value = "";
		}

		function validate(event) {
			var charsAllowed = "0123456789";

			var allowed;
			for (var i = 0; i < this.value.length; i++) {
				allowed = false;
				for (var j = 0; j < charsAllowed.length; j++) {
					if (this.value.charAt(i) == charsAllowed.charAt(j)) {
						allowed = true;
					}
				}
				if (i == 2) {
					if (this.value.charAt(i) == "0"
							|| this.value.charAt(i) == "1") {
						allowed = true;
					} else {
						allowed = false;
					}
				}
				if (i == 3) {
					if (this.value.charAt(i - 1) == "0") {
						if (this.value.charAt(i) == "0") {
							allowed = false;
						} else {
							allowed = true;
						}
					} else if (this.value.charAt(i - 1) == "1") {
						if (this.value.charAt(i) == "0"
								|| this.value.charAt(i) == "1"
								|| this.value.charAt(i) == "2") {
							allowed = true;
						} else {
							allowed = false;
						}
					}

				}
				if (i == 4) {
					if (this.value.charAt(i) == "0"
							|| this.value.charAt(i) == "1"
							|| this.value.charAt(i) == "2"
							|| this.value.charAt(i) == "3") {
						allowed = true;

					} else {
						allowed = false;
					}

				}
				if (i == 5) {
					if (this.value.charAt(i - 1) == "0") {
						if (this.value.charAt(i) == "0") {
							allowed = false;
						} else {
							allowed = true;
						}
					} else if (this.value.charAt(i - 1) == "3") {
						if (this.value.charAt(i) == "0"
								|| this.value.charAt(i) == "1") {
							allowed = true;
						} else {
							allowed = false;
						}
					}

				}
				if (allowed == false) {
					
					this.value = this.value.charAt(i).replace(
							this.value.charAt(i), "");
					i--;
					
					alert('Format No. Kad Pengenalan yang anda masukkan TIDAK SAH!');
				} else {
					$('#checkICResult').html("");
				}
			}
			return true;
		}

		$(document).ready(function() {

			$('#teacherIC').change(validate);
			$('#teacherIC').keydown(validate);
			$('#teacherIC').keypress(validate);
			$('#teacherIC').keyup(validate);
			

		});
	</script>
	<%
		try {
			String pinKey = String.valueOf(session.getAttribute("pinKey"));

			String teacherIdEnc = request.getParameter("id");
			String teacherId = URLSecurity.decryptFinal(
					teacherIdEnc, pinKey);

			ControllerWrapper controller = new ControllerWrapper();
			Teacher teacher = new Teacher();

			teacher = controller.getTeacherById(teacherId);

			if (teacher.getTeacher_id() != null) {
	%>
	<div id="accordion" style="width: 1100px;">
		<h3>KEMASKINI MAKLUMAT PENGGUNA</h3>

		<div>
			<font color="red">* </font>Maklumat yang WAJIB diisi. <br /> <br />
			<br />


			<form id="UpdateTeacherForm" method="post" action="UpdateTeacher">
				<table width="1000px" border="0" cellpadding="5">
					<tr>
						<td width="200px"><font color="red">* </font><strong>NAMA
								PENUH </br>(Seperti Kad Pengenalan)
						</strong></td>
						<td width="5px">:</td>
						<td><input type="text" id="teacherName" name="teacherName"
							style="width: 500px;" value="<%=teacher.getName()%>" required></input></td>
					</tr>
					<tr>
						<td><font color="red">* </font><strong>JANTINA</strong></td>
						<td width="5px">:</td>
						<%
							if (teacher.getGender().equalsIgnoreCase("L")) {
						%>
						<td><input type="radio" id="gender" name="gender" value="L"
							checked> Lelaki<br> <input type="radio" id="gender"
							name="gender" value="P"> Perempuan<br></td>
						<%
							} else if (teacher.getGender().equalsIgnoreCase("P")) {
						%>
						<td><input type="radio" id="gender" name="gender" value="L">
							Lelaki<br> <input type="radio" id="gender" name="gender"
							value="P" checked> Perempuan<br></td>
						<%
							}
						%>
					</tr>
					<tr>
						<td><font color="red">* </font><strong>NO. KAD
								PENGENALAN </br>(12 digit sahaja)
						</strong></td>
						<td>:</td>
						<td><input type="text" id="teacherIC" name="teacherIC"
							value="<%=teacher.getIc_number()%>" maxlength="12"
							onclick="ClearFields();" required></input> (contoh: 870125109999)
							<span id="checkICResult"></span></td>
					</tr>
					<tr>
						<td><font color="red">* </font><strong>STATUS
								PERKAHWINAN</strong></td>
						<td>:</td>
						<%
							if (teacher.getMarital_status().equalsIgnoreCase("B")) {
						%>
						<td><input type="radio" id="maritalStatus"
							name="maritalStatus" value="B" checked> Bujang<br> <input
							type="radio" id="maritalStatus" name="maritalStatus" value="K">
							Telah Berkahwin<br></td>
						<%
							} else if (teacher.getMarital_status()
											.equalsIgnoreCase("K")) {
						%>
						<td><input type="radio" id="maritalStatus"
							name="maritalStatus" value="B"> Bujang<br> <input
							type="radio" id="maritalStatus" name="maritalStatus" value="K"
							checked> Telah Berkahwin<br></td>
						<%
							}
						%>
					</tr>
					<tr>
						<td><font color="red">* </font><strong>ALAMAT RUMAH</strong></td>
						<td>:</td>
						<td><textarea id="teacherAddress" name="teacherAddress"
								rows="5" cols="50" required><%=teacher.getAddress()%></textarea></td>
					</tr>
					<tr>
						<td><font color="red">* </font><strong>NO. TEL.
								BIMBIT</strong></td>
						<td>:</td>
						<td><input type="text" id="telNoHp" name="telNoHp"
							value="<%=teacher.getTelno_hp()%>" required></input></td>
					</tr>
					<tr>
						<td><strong>NO. TEL. PEJABAT (SAMBUNGAN)</strong></td>
						<td>:</td>
						<td><input type="text" id="telNoOffice" name="telNoOffice"
							value="<%=teacher.getTelno_ext()%>"></input></td>
					</tr>
					<tr>
						<td><strong>NO. TEL. RUMAH</strong></td>
						<td>:</td>
						<td><input type="text" id="telNoExt" name="telNoExt"
							value="<%=teacher.getTelno_house()%>"></input></td>
					</tr>
					<tr>
						<td><strong>JAWATAN</strong></td>
						<td>:</td>
						<%
							if (teacher.getPosition().equalsIgnoreCase("GB")) {
						%>
						<td>GURU BESAR</td>
						<%
							} else if (teacher.getPosition().equalsIgnoreCase("GPK")) {
						%>
						<td>GURU PENOLONG KANAN 1</td>
						<%
							} else if (teacher.getPosition().equalsIgnoreCase("GPKH")) {
						%>
						<td>GURU PENOLONG KANAN HAL EHWAL MURID</td>
						<%
							} else if (teacher.getPosition().equalsIgnoreCase("GPKK")) {
						%>
						<td>GURU PENOLONG KANAN KO-KURIKULUM</td>
						<%
							} else if (teacher.getPosition().equalsIgnoreCase("G")) {
						%>
						<td>GURU BIASA</td>
						<%
							} else if (teacher.getPosition().equalsIgnoreCase("PPD")) {
						%>
						<td>PEGAWAI PEJABAT PELAJARAN DAERAH</td>
						<%
							}
						%>
					</tr>
					<tr>
						<td><input type="hidden" id="teacherId" name="teacherId"
							value="<%=teacher.getTeacher_id()%>"></input></td>
						<td></td>
						<td align="right"><input type="submit" name="updateTeacher"
							value="Kemaskini" class="blackbutton" /></td>
					</tr>
				</table>
			</form>
		</div>

	</div>

	<%
		} else {
	%>
	<p>
		Maklumat guru <b><font color="red">TIADA</font></b> tiada dalam sistem
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