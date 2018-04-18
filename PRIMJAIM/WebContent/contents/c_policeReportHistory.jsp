<%@page import="bean.Station"%>
<%@page import="bean.Police"%>
<%@page import="bean.PpdAttendance"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="bean.Teacher"%>

<%@page import="facade.ControllerWrapper"%>
<%@page import="bean.Instituition"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>


<%
		String month = request.getParameter("selectedMonth");
		SimpleDateFormat fromUser = new SimpleDateFormat("dd-MM-yy");
				
		Date date = new Date(fromUser.parse(month).getTime());
		
		
		Police police = new Police();
		Station station = new Station();
		
		
		Instituition instituition = new Instituition();
		ControllerWrapper controller = new ControllerWrapper();
		PpdAttendance ppdAttendance = null;
		String city = null;
		int count = 0;
		
		
		if(session.getAttribute("SASpolice") != null){
			police = (Police) session.getAttribute("SASpolice");
		}
		
		if (police != null) {
			station = controller.getStationByPoliceId(police.getId());
		}
		
		ArrayList<Station> stations = controller.getAllPoliceStation();
		
			

%>
<center><h2 class="title">REKOD STATISTIK LAPORAN KEHADIRAN HARIAN SEKOLAH </br> DI BAWAH PANTAUAN </br>
<b><%=station.getName()%></b> </br> (<b><%=month%></b> ) </h2></center>
		
<p>&nbsp;</p>
<div id="accordion" style="width: 1100px;">
<h3>STATISTIK PPD</h3>	
	<div>
		<table width="1000px" border="0" cellpadding="5">
				<tr>	
					<th rowspan="2">Bil</th>
					<th rowspan="2">Nama PPD</th>
					<th colspan="3">Kelas</th>
					<th colspan="4">Murid</th>
				</tr>
				<tr>
					<th>Bil Siap</th>
					<th>Jumlah</th>
					<th>Peratus Siap</th>
					<th>Bil Hadir</th>
					<th>Bil Tidak Hadir</th>
					<th>Jumlah</th>
					<th>Peratus Hadir</th>
				</tr>
				<%
				
					for(Station stationv1 : stations){
						ppdAttendance = controller.policeReportRecord(stationv1.getId(), date);
						count = count + 1;	
						%>
						<tr>	
							<td align=center><%=count %></td>
							<td><a href="policeReportHistoryDetail.jsp?stationId=<%=stationv1.getId()%>&month=<%=month%>" ><%=stationv1.getName() %></a></td>
							 
							<td align=center><%=ppdAttendance.getTotalFinish() %></td>
							<td align=center><%=ppdAttendance.getTotalClass() %></td>
							<td align=center><%=ppdAttendance.getFinishPercentage() %>%</td>
							<td align=center><%=ppdAttendance.getTotalAttend() %></td>
							<td align=center><%=ppdAttendance.getTotalAbsent() %></td>
							<td align=center><%=ppdAttendance.getTotalStudent() %></td>
							
							<%if(ppdAttendance.getAttendancePercentage() >= 95){ %>
							<td align="center"><%=ppdAttendance.getAttendancePercentage() %>%</td>
							<%}else if(ppdAttendance.getAttendancePercentage() == 0){ %>
							<td align="center" ><font color="blue"><%=ppdAttendance.getAttendancePercentage() %>%</font></td>
							<%}else if(ppdAttendance.getAttendancePercentage() < 95){ %>
							<td align="center" ><font color="red"><%=ppdAttendance.getAttendancePercentage() %>%</font></td>
							</tr>
							
							
						
					<%
							}}
					%>	
						
			</table>
	</div>

</div>


