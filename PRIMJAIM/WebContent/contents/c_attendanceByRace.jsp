<%@page import="facade.ControllerWrapper"%>
<%@page import="java.util.List"%>
<%@page import="bean.AttendanceByRace"%>
<%@page import="bean.Teacher"%>
<%@page import="bean.Instituition"%>

<%

	Teacher teacher = new Teacher();
	Instituition instituition = new Instituition();

	teacher = (Teacher) session.getAttribute("SASteacher");

	int hitungPanjangKedatangan = 0;

	ControllerWrapper controller = new ControllerWrapper();
	
	instituition = controller.getInstituitionById(controller.getInstituitionByTeacherId(teacher.getTeacher_id()));

	List<AttendanceByRace> attendanceByRaces = controller.getAttendanceByRace(instituition.getAcademic_instituition_id(), "15");

%>

<table width="1100px" border="1" cellspacing="0" cellpadding="3">

	<tr>
		<th rowspan="2">Bulan</th>
		<th colspan="2">Melayu</th>
		<th colspan="2">Cina</th>
		<th colspan="2">India</th>
		<th colspan="2">Lain-lain</th>
		<th colspan="2">Jumlah</th>
		<th rowspan="2">Jumlah Murid</th>
		<th rowspan="2">Hitung Panjang Kedatangan</th>
		<th rowspan="2">Peratusan Kedatangan</th>
	</tr>
	<tr>
		<th>L.</th>
		<th>P.</th>
		<th>L.</th>
		<th>P.</th>
		<th>L.</th>
		<th>P.</th>
		<th>L.</th>
		<th>P.</th>
		<th>L.</th>
		<th>P.</th>
	</tr>
	
	<%for(AttendanceByRace attendanceByRace : attendanceByRaces){ 
	
		hitungPanjangKedatangan = controller.getHitungPanjangKedatangan(instituition.getAcademic_instituition_id(), attendanceByRace.getMonth_year());
	
	%>
		<tr>
			<td><b><%=controller.convertMonthToMalay(attendanceByRace.getMonth_year().substring(2, 4)) %></b></td>
			<td><%=attendanceByRace.getMelayuL() %></td>
			<td><%=attendanceByRace.getMelayuP() %></td>
			<td><%=attendanceByRace.getCinaL() %></td>
			<td><%=attendanceByRace.getCinaP() %></td>
			<td><%=attendanceByRace.getIndiaL() %></td>
			<td><%=attendanceByRace.getIndiaP() %></td>
			<td><%=attendanceByRace.getLainL() %></td>
			<td><%=attendanceByRace.getLainP() %></td>
			<td><%=attendanceByRace.getJumlahL() %></td>
			<td><%=attendanceByRace.getJumlahP() %></td>
			<td><%=attendanceByRace.getJumlah() %></td>
			<td><%=hitungPanjangKedatangan %></td>
			<td><%=(hitungPanjangKedatangan/attendanceByRace.getJumlah())*100 %>%</td>
		</tr>
		<%} %>
		
	</table>
		