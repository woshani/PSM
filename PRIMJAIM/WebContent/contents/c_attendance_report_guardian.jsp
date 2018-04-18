<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="controller.URLSecurity"%>
<%@page import="bean.DailyAttendance"%>
<%@page import="bean.Student"%>
<%@page import="facade.ControllerWrapper"%>
<%@page import="java.text.SimpleDateFormat"%>

<div class="entry">
	<%
		String pinKey = (String) session.getAttribute("pinKey");

			String studentIdEnc = request.getParameter("studentId");
			String studentId = URLSecurity.decryptFinal(studentIdEnc, pinKey);
			String classIdEnc = request.getParameter("classId");
			String classId = URLSecurity.decryptFinal(classIdEnc, pinKey);

			ControllerWrapper controller = new ControllerWrapper();
			String month = request.getParameter("month");

			DailyAttendance attendance = new DailyAttendance();

			Student student = new Student();
			student = controller.getStudentByIdGuarView(studentId, classId);

			SimpleDateFormat fromUser = new SimpleDateFormat("MMMMM yyyy");
			SimpleDateFormat myFormat = new SimpleDateFormat("MMyy");

			String formatedMonth = myFormat.format(fromUser.parse(month));
			String newMonth = formatedMonth.substring(0, 2);
			String newYear = formatedMonth.substring(2, 4);

			attendance = controller.getAttendanceMonthlyByStudent(student.getStudent_id(), student.getClassroom().getClass_id(), formatedMonth.substring(0, 2), formatedMonth.substring(2, 4));

			if (attendance != null) {
	%>

	<h3>
		<font >LAPORAN KEHADIRAN BULANAN</font>
		
		</br>
		</br>
		NAMA PELAJAR : <%=student.getName()%>
		</br>
		SEKOLAH : <%=student.getClassroom().getInstituition().getAcademic_name()%>
		</br>
		KELAS : <%=student.getClassroom().getName()%>
		</br>
		BULAN/TAHUN : <%=controller.convertMonthToMalay(newMonth)%> / 20<%=newYear%></h3>
		</br>
	<table width="1100px" border="1" cellspacing="0" cellpadding="3">
		<tr>
			<th align="center">NAMA PELAJAR</th>

			<%
				for (int index = 1; index < 32; index++) {
					if (index <10){%>
					
					<th align="center">0<%=index%></th>
					<%
				}else{
				%>
					<th align="center"><%=index%></th>
			<%
				}
			}%>
			
		</tr>
		<tr>
			<th  align="left"><%=attendance.getStudent().getName()%></th>
			<td align="center">
				<%
					out.print(attendance.getD01());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD02());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD03());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD04());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD05());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD06());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD07());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD08());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD09());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD10());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD11());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD12());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD13());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD14());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD15());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD16());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD17());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD18());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD19());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD20());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD21());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD22());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD23());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD24());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD25());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD26());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD27());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD28());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD29());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD30());
				%>
			</td>
			<td align="center">
				<%
					out.print(attendance.getD31());
				%>
			</td>
		</tr>
	</table>

	<%
		} else {
	%>
	<p>
		Maklumat kehadiran kelas pada bulan <b><%=month%></b> tiada dalam
		sistem maklumat.
	</p>
	<%
		}
	%>
</div>