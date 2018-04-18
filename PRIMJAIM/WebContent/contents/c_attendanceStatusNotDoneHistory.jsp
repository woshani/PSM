<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="bean.FinishTime"%>
<%@ page import="bean.Teacher"%>
<%@ page import="facade.ControllerWrapper"%>
<%@ page import="java.util.Vector"%>
<%@page import="bean.Instituition"%>
<%@ page import="java.util.Enumeration"%>
<%
	String month = request.getParameter("month");
	SimpleDateFormat fromUser = new SimpleDateFormat("dd-MM-yy");

	Date date = new Date(fromUser.parse(month).getTime());

	ControllerWrapper controller = new ControllerWrapper();
	FinishTime finishTime = null;
	Vector<FinishTime> finishTimes = new Vector<FinishTime>();
	Teacher teacher = null;
	Instituition instituition = null;

	if (request.getParameter("instituitionId") != null) {

		instituition = controller.getInstituitionById(request
				.getParameter("instituitionId"));

	} else {

		teacher = (Teacher) session.getAttribute("SASteacher");

		instituition = controller.getInstituitionById(controller
				.getInstituitionByTeacherId(teacher.getTeacher_id()));

	}

	finishTimes = controller.StatusKedatanganRecord(2, date,
			instituition.getAcademic_instituition_id());

	Enumeration enum2 = finishTimes.elements();
	int count = 0;
	if (enum2.hasMoreElements()) {
%>
<table width="1000px" border="1" cellspacing="0" cellpadding="3">
	<tr>
		<th rowspan="1">BIL</th>
		<th rowspan="1">NAMA GURU</th>
		<th rowspan="1">KELAS</th>
	</tr>

	<%
		while (enum2.hasMoreElements()) {

				finishTime = new FinishTime();

				finishTime = (FinishTime) enum2.nextElement();

				teacher = new Teacher();

				teacher = controller.getTeacherByClassroomId(finishTime
						.getClassroom().getClass_id());
	%>
	<tr>
		<td align="center"><%=count = count + 1%>.</td>
		<%
			if (teacher.getName() == null) {
		%>
		<td align="center">TIADA GURU</td>
		<%
			} else {
		%>
		<td align="left"><%=teacher.getName()%></td>
		<%
			}
		%>
		<td align="center"><%=finishTime.getClassroom().getName()%> (<%=finishTime.getClassroom().getType()%>)</td>

	</tr>
	<%
		}
	%>

</table>
<%
	} else {
%>
<p>Tiada Maklumat.</p>
<%
	}
%>