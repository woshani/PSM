<%@page import="bean.Warning"%>
<%@page import="bean.WarningHistory"%>
<%@page import="java.security.InvalidKeyException"%>
<%@page import="javax.crypto.NoSuchPaddingException"%>
<%@page import="java.security.NoSuchAlgorithmException"%>
<%@page import="javax.crypto.SecretKey"%>
<%@page import="controller.URLSecurity"%>
<div class="entry">
	<%@page import="bean.Classroom"%>
	<%@page import="bean.Student"%>
	<%@page import="bean.Teacher"%>
	<%@page import="java.util.List"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="facade.ControllerWrapper"%>
	<%@page import="java.text.SimpleDateFormat"%>
	<%@page import="java.util.Date"%>
	<script type="text/javascript">
	function confirm(status){
		
		if (status==1) {
		  return true;
		}else if(status == 0) {
			alertify.alert("Kedatangan Sudah Diambil");
			alertify.error('Kedatangan boleh diubah melalui butang Laporan Dan Kemaskini Kehadiran Kelas.');
			return false;
		}
	}
	</script>
	<%
		try {

			String pinKey = String.valueOf(session.getAttribute("pinKey"));

			String classId = URLSecurity.decryptFinal(
					request.getParameter("id"), pinKey);

			// Declare URL variable to encrypt
			String teacherIdEnc = new String();
			String studentIdEnc = new String();
			String classIdEnc = new String();

			int status = 0;
			ControllerWrapper controller = new ControllerWrapper();
			Classroom classroom = new Classroom();
			classroom = controller.getClassroomById(classId);

			if (classroom.getClass_id() != null) {
				List<Teacher> teachers = controller
						.getTeachersByClassroomId(classroom.getClass_id());
				List<Student> students = controller
						.getStudentsByClassId(classroom.getClass_id());
				status = controller
						.CheckFinishTime(classroom.getClass_id());

				Date dNow = new Date();
				SimpleDateFormat ft = new SimpleDateFormat("MMMMM YYYY");

				String month = ft.format(dNow);

				classIdEnc = URLSecurity.encryptFinal(
						classroom.getClass_id(), pinKey);
	%>

	<p>

		<%-- <a href="registerNextClass.jsp?id=<%=classroom.getClass_id()%>"
			class="blackbutton">Naik Kelas</a>
		<a href="registerStudent.jsp"
			class="blackbutton">Daftar Pelajar</a> --%>
		<b>Langkah 1.</b> <a href="takeAttendance.jsp?id=<%=classIdEnc%>"
			onclick="return confirm(<%=status%>)" class="blackbutton">Merekod
			Kehadiran Harian</a> <b>Langkah 2.</b> <a
			href="attendanceReport.jsp?id=<%=classIdEnc%>&month=<%=month%>"
			class="blackbutton">Laporan Dan Kemaskini Kehadiran Kelas</a> <b></b>
		<a href="studentTransfer.jsp?classId=<%=classIdEnc%>"
			class="blackbutton">Cari dan Daftar Pelajar</a> <a
			href="registerStudent.jsp" class="blackbutton">Daftar Pelajar</a>
	</p>

	<hr />

	<div id="accordion" style="width: 1100px;">
		<h3>SENARAI PELAJAR</h3>
		<div>
			<%
				if (students.size() > 0) {
			%>
			<form id="TransferStudent" method="post"
				action="studentTransferDate.jsp">
				<table width="1000px" border="1" cellspacing="0" cellpadding="3">
					<tr>
						<th align="center">Bil</th>
						<th align="center">Nama Pelajar</th>
						<th align="center">Status Amaran</th>
						<th align="center">Surat Amaran</th>
						<th align="center">Status Pindah</th>
					</tr>
					<%
						int stuIndex = 0;
									for (Student student : students) {
										stuIndex++;

										String description = controller.WarningList(student
												.getStudent_id());
										
										
										
										
										
					%>
					<tr>
						<td align="center" width="30"><%=stuIndex%>.</td>
						<td align="left" width="300">
							<%
								studentIdEnc = URLSecurity.asciiToHex(URLSecurity
														.encryptionURL(student.getStudent_id(),
																pinKey));
							%> <a href="studentProfile.jsp?id=<%=studentIdEnc%>"> <%=student.getName()%></a>
						</td>
						<td align="center" width="150">
							<%
								if (description.equalsIgnoreCase("TIADA")) {
							%><font color="green"> - </font> <%
 	} else {
 %><font color="red"><%=description%></font></a> <%
 	}
 %>
						</td>
						<td align="center" width="150">
							<%
								if (description.equalsIgnoreCase("TIADA")) {
							%><font color="green"> - </font> <%
 	} else {
	
 		WarningHistory wh = new WarningHistory();
 		wh = controller.WarningList2(student.getStudent_id());
 		
 		String whIdEnc = URLSecurity.encryptFinal(Integer.toString(wh.getWhId()), pinKey);
 		
 		
 						
 %> <font color="black"> Cetak PDF : </font> </br> <a href="${pageContext.request.contextPath}/PDFWarningLetter?id=<%=student.getStudent_id()%>&warning=<%=description%>&wh=<%=wh.getWhId()%>">
								<font color="red"><%=description%></font> </a> 
								
	</br> </br><a href="warning.jsp?id=<%=studentIdEnc%>&wh=<%=whIdEnc%>">
								<font color="blue">REKOD AMARAN</font> </a> 
 <%
 		

 					}
 %>
						</td>
						<td align="center" width="200" required><input
							type="checkbox" name="studentId" value="<%=studentIdEnc%>"></td>
					</tr>
					<%
						}
					%>
				</table>
				<input type="hidden" name="classId" id="classId"
					value=<%=classIdEnc%>>
				<center>
					<br /> <input type="submit" name="TransferSchool"
						value="PINDAH SEKOLAH" class="blackbutton" />
				</center>
			</form>
			<%
				} else {
			%>
			<p>Tiada pelajar di dalam kelas ini.</p>
			<%
				}
			%>
		</div>

		<h3>MAKLUMAT KELAS</h3>
		<div>
			<table width="1000px" border="0" cellpadding="5">
				<tr>
					<td width="200px"><strong>NAMA</strong></td>
					<td width="5px">:</td>
					<td><%=classroom.getName()%></td>
				</tr>
				<tr>
					<td width="200px"><strong>JENIS KELAS</strong></td>
					<td width="5px">:</td>
					<td><%=classroom.getType()%></td>
				</tr>
				<tr>
					<td width="200px"><strong>JUMLAH PELAJAR</strong></td>
					<td width="5px">:</td>
					<td><%=students.size()%></td>
				</tr>
			</table>
		</div>
		<h3>SENARAI GURU</h3>
		<div>
			<%
				if (teachers.size() > 0) {
			%>
			<ul>
				<%
					int teacherIndex = 0;
								for (Teacher teacher : teachers) {
									teacherIndex++;
				%>
				<li><%=teacherIndex%>) <%
					teacherIdEnc = URLSecurity.encryptFinal(
											teacher.getTeacher_id(), pinKey);
				%> <a href="teacherProfile.jsp?id=<%=teacherIdEnc%>"> <%=teacher.getName()%></a></li>
				<%
					}
				%>
			</ul>
			<%
				} else {
			%>
			<p>Tiada guru kelas untuk kelas ini.</p>
			<%
				}
			%>
		</div>
	</div>

	<p>&nbsp;</p>

	<%
		} else {
	%>
	<p>
		Maklumat kelas dengan ID <b><%=classId%></b> tiada dalam sistem
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
