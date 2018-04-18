<%@page import="java.security.InvalidKeyException"%>
<%@page import="javax.crypto.NoSuchPaddingException"%>
<%@page import="java.security.NoSuchAlgorithmException"%>
<%@page import="javax.crypto.SecretKey"%>
<%@page import="controller.URLSecurity"%>
<div class="entry">
	<%@page import="bean.User"%>
	<%@page import="bean.Teacher"%>
	<%@page import="bean.Instituition"%>
	<%@page import="bean.Classroom"%>
	<%@page import="java.util.List"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="facade.ControllerWrapper"%>
	<%
		try {
			String pinKey = String.valueOf(session.getAttribute("pinKey"));

			String teacherId = URLSecurity.decryptFinal(
					request.getParameter("id"), pinKey);

			ControllerWrapper controller = new ControllerWrapper();
			Teacher teacher = new Teacher();

			if (teacherId == null) {
				teacher = (Teacher) session.getAttribute("SASteacher");
			} else {
				teacher = controller.getTeacherById(teacherId);
			}

			if (teacher.getTeacher_id() != null) {
				List<Classroom> classrooms = controller
						.getClassroomsByTeacherId(teacher.getTeacher_id());
	%>

	<div id="accordion" style="width: 1100px;">
		<h3>MAKLUMAT PENGENDALI</h3>
		<div>
			
			<table width="1000px" border="0" cellpadding="5">
				<tr>
					<td width="200px"><strong>NAMA</strong></td>
					<td width="5px">:</td>
					<td title="Kemaskini Maklumat Pengguna"><a
						href="updateTeacherProfile.jsp?id=<%=URLSecurity.encryptFinal(teacher.getTeacher_id(),
							pinKey)%>"><%=teacher.getName()%></a></td>
				</tr>
				<tr>
					<td><strong>JANTINA</strong></td>
					<td width="5px">:</td>
					<%
						if (teacher.getGender().equalsIgnoreCase("L")) {
					%>
					<td>LELAKI</td>
					<%
						} else if (teacher.getGender().equalsIgnoreCase("P")) {
					%>
					<td>PEREMPUAN</td>
					<%
						}
					%>
				</tr>
				<tr>
					<td><strong>NO. KAD PENGENALAN</strong></td>
					<td>:</td>
					<td><%=teacher.getIc_number()%></td>
				</tr>
				<tr>
					<td><strong>STATUS PERKAHWINAN</strong></td>
					<td>:</td>
					<%
						if (teacher.getMarital_status().equalsIgnoreCase("B")) {
					%>
					<td>BUJANG</td>
					<%
						} else if (teacher.getMarital_status()
										.equalsIgnoreCase("K")) {
					%>
					<td>TELAH BERKAHWIN</td>
					<%
						}
					%>
				</tr>
				<tr>
					<td><strong>ALAMAT RUMAH</strong></td>
					<td>:</td>
					<td><%=teacher.getAddress()%></td>
				</tr>
				<tr>
					<td><strong>NO. TEL. PEJABAT (SAMBUNGAN)</strong></td>
					<td>:</td>
					<td><%=teacher.getTelno_ext()%></td>
				</tr>
				<tr>
					<td><strong>NO. TEL. BIMBIT</strong></td>
					<td>:</td>
					<td><%=teacher.getTelno_hp()%></td>
				</tr>
				<tr>
					<td><strong>NO. TEL. RUMAH</strong></td>
					<td>:</td>
					<td><%=teacher.getTelno_house()%></td>
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
			</table>
		</div>

	</div>

	<%
		} else {
	%>
	<p>
		Maklumat guru dengan ID <b><%=teacherId%></b> tiada dalam sistem
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