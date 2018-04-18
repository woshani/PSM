<%@page import="bean.Instituition"%>
<%@ page import="facade.ControllerWrapper"%>
<%@ page import="bean.Teacher"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="bean.AttendanceStatistics"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<%
	String month = request.getParameter("month");
	SimpleDateFormat fromUser = new SimpleDateFormat("dd-MM-yy");

	Date date = new Date(fromUser.parse(month).getTime());

	ControllerWrapper controller = new ControllerWrapper();

	Teacher teacher = null;
	Instituition instituition = null;

	List<AttendanceStatistics> attendanceStatistics = new ArrayList<AttendanceStatistics>();

	if (request.getParameter("instituitionId") != null) {

		instituition = controller.getInstituitionById(request
				.getParameter("instituitionId"));

	} else {

		teacher = (Teacher) session.getAttribute("SASteacher");

		instituition = controller.getInstituitionById(controller
				.getInstituitionByTeacherId(teacher.getTeacher_id()));

	}

	SimpleDateFormat ft = new SimpleDateFormat("ddMMYY");
	String selectedDate = ft.format(date);
	
	SimpleDateFormat ft2 = new SimpleDateFormat("MMMMM YYYY");
	String dateForPrint = ft2.format(date);
	
	
	attendanceStatistics = controller.getAttendanceStatistics(
			instituition.getAcademic_instituition_id(), selectedDate);
	
	
	AttendanceStatistics totalAttendanceStatistic = controller
			.getTotalAttendanceStatistics(
					instituition.getAcademic_instituition_id(),
					selectedDate);

	int index = 0;
%>
<table width="1000px" border="0" cellspacing="0" cellpadding="3">
	<tr>
		<td align ="center"><b><h3>Statistik Kedatangan Keseluruhan Sekolah</h3></b></td>
	</tr>
</table>

<br>


<table width="1000px" border="1" cellspacing="0" cellpadding="3">
	<tr>
		<th rowspan="2">BIL</th>
		<th rowspan="2">GURU</th>
		<th rowspan="2">KELAS</th>
		<th colspan="2">JUMLAH HADIR</th>
		<th colspan="2">JUMLAH TIDAK HADIR</th>
		<th rowspan="2">JUMLAH PELAJAR</th>
		<th rowspan="2">PERATUS KEDATANGAN</th>
		<th rowspan="2">LAPORAN</th>
	</tr>
	<tr>
		<th>L</th>
		<th>P</th>
		<th>L</th>
		<th>P</th>
	</tr>

	<%
		for (AttendanceStatistics attendanceStatistic : attendanceStatistics) {

			teacher = controller
					.getTeacherByClassroomId(attendanceStatistic
							.getClassroom().getClass_id());
	%>

	<tr>
		<td align="center"><%=index = index + 1%>.</td>
		<%
			if (teacher.getName() == null) {
		%>
		<td align="center">TIADA GURU</td>
		<%
			} else {
		%>
		<td><%=teacher.getName()%></td>
		<%
			}
		%>
		<td align="center"><%=attendanceStatistic.getClassroom().getName()%>
			<br />(<%=attendanceStatistic.getClassroom().getType()%>)</td>
		<td align="center"><%=attendanceStatistic.getJumlahHadirL()%></td>
		<td align="center"><%=attendanceStatistic.getJumlahHadirP()%></td>
		<td align="center"><%=attendanceStatistic.getJumlahTidakHadirL()%></td>
		<td align="center"><%=attendanceStatistic.getJumlahTidakHadirP()%></td>
		<td align="center"><%=attendanceStatistic.getJumlahPelajar()%></td>
		<%
			if (attendanceStatistic.getPeratusanKedatangan() > 0.0
						&& attendanceStatistic.getPeratusanKedatangan() < 95) {
		%>
		<td align="center"><font color="red"><%=attendanceStatistic.getPeratusanKedatangan()%>%</font></td>
		<%
			} else if (attendanceStatistic.getPeratusanKedatangan() > 94) {
		%>
		<td align="center"><%=attendanceStatistic.getPeratusanKedatangan()%>%</td>
		<%
			} else {
		%>
		<td align="center"><font color="blue"><%=attendanceStatistic.getPeratusanKedatangan()%>%</font></td>
		<%
			}
		%>
		<td  align="center">
		<%if(attendanceStatistic.getPeratusanKedatangan() > 0.0){
			%>
			<a
			href="${pageContext.request.contextPath}/PDFMonthlyAttendance?id=<%=attendanceStatistic.getClassroom().getClass_id()%>&month=<%=dateForPrint%>"
			class="blackbutton"><font color="white">PDF</font></a>
			<%
		}else{
			%>
			<font color="red">Belum Sedia</font>
			<%
		}%>
		
		</td>
	</tr>
	<%
		}
	%>
	<tr>
		<td colspan="3" align="center" rowspan="2">JUMLAH</td>
		<td align="center"><%=totalAttendanceStatistic.getJumlahHadirL()%></td>
		<td align="center"><%=totalAttendanceStatistic.getJumlahHadirP()%></td>
		<td align="center"><%=totalAttendanceStatistic.getJumlahTidakHadirL()%></td>
		<td align="center"><%=totalAttendanceStatistic.getJumlahTidakHadirP()%></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
	<tr>

		<td colspan="2" align="center"><%=totalAttendanceStatistic.getJumlahHadirL()
					+ totalAttendanceStatistic.getJumlahHadirP()%></td>
		<td colspan="2" align="center"><%=totalAttendanceStatistic.getJumlahTidakHadirL()
					+ totalAttendanceStatistic.getJumlahTidakHadirP()%></td>
		<td align="center"><%=totalAttendanceStatistic.getJumlahPelajar()%></td>
		<%
			if (totalAttendanceStatistic.getPeratusanKedatangan() > 0.0
					&& totalAttendanceStatistic.getPeratusanKedatangan() < 95) {
		%>
		<td align="center"><font color="red"><%=totalAttendanceStatistic.getPeratusanKedatangan()%>%</font></td>
		<%
			} else if (totalAttendanceStatistic.getPeratusanKedatangan() > 94) {
		%>
		<td align="center"><%=totalAttendanceStatistic.getPeratusanKedatangan()%>%</td>
		<%
			} else {
		%>
		<td align="center"><font color="blue"><%=totalAttendanceStatistic.getPeratusanKedatangan()%>%</font></td>
		<%
			}
		%>
		<td></td>

	</tr>


</table>
