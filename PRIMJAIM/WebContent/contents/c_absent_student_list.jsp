<%@page import="facade.ControllerWrapper"%>
<%@page import="bean.Teacher"%>
<%@page import="bean.DailyAttendance"%>
<%@page import="bean.Classroom"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<%
	ControllerWrapper controller = new ControllerWrapper();

	Teacher teacher = new Teacher();
	teacher = (Teacher) session.getAttribute("SASteacher");
	Classroom classroom = null;
	Vector<Classroom> classes = new Vector<Classroom>();
	List<DailyAttendance> attendances = new ArrayList<DailyAttendance>();

	classes = controller.getClassBySchool(teacher.getTeacher_id());
%>

<div class="entry">
	<div id="accordion" style="width: 1100px;">
		<%
			try {
				Enumeration enum1 = classes.elements();
				int count = 0;
				int error = 0;
				while (enum1.hasMoreElements()) {

					classroom = (Classroom) enum1.nextElement();

					attendances = controller.getAbsentStudentList(classroom
							.getClass_id());

					if (attendances.size() > 0) {
		%>

		<h3><%=classroom.getName()%> [<%=classroom.getType()%>]
			: <font color="green"><%=attendances.size()%> orang </font>
		</h3>
		<div>

			<table width="1000px" border="1" cellspacing="0" cellpadding="3">
				<tr>
					<th rowspan="1">Bil</th>
					<th rowspan="1">Nama Pelajar</th>
					<th rowspan="1">Status Kedatangan</th>
				</tr>
				<%
					for (DailyAttendance attendance : attendances) {
				%>
				<tr>
					<td align="center"><%=count = count + 1%>.</td>
					<td align="left"><%=attendance.getStudent().getName()%></td>
					<td align="center"><%=attendance.getD01()%></td>
				</tr>
				<%
					}
								count = 0;
								error = 1;
				%>
			</table>

		</div>

		<%
			} else if (attendances.isEmpty()
							&& !enum1.hasMoreElements()) {
						if (error == 0) {
		%>Tiada Rekod Pelajar Tidak Hadir untuk Hari Ini.
		<%
			error = 1;
						}

					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		%>
	</div>
</div>

