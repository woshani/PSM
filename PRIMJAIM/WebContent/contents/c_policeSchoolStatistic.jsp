<%@page import="bean.Police"%>
<%@page import="bean.Station"%>
<%@page import="facade.ControllerWrapper"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="bean.PpdAttendance"%>
<%@page import="bean.Instituition"%>

<%

		String pinKey = String.valueOf(session.getAttribute("pinKey"));
		String institutionIdEnc = new String();
		
		Station station = new Station();
		Police police = new Police();
		
		ControllerWrapper controller = new ControllerWrapper();
		
		if (session.getAttribute("SASpolice") != null) {
			police = (Police) session.getAttribute("SASpolice");
		}
		
		if (police != null) {
			station = controller.getStationByPoliceId(police.getId());
		}
		
		int count = 0;

%>

<div id="accordion" style="width: 1100px;">
		<h3>SEKOLAH RENDAH</h3>
		<div>
			<table width="1000px" border="0" cellpadding="5">
				<tr>	
					<th rowspan="2">Bil</th>
					<th rowspan="2">Nama Sekolah</th>
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
					List<Instituition> instituitionss = controller.getAllInstitutionsByStationId(station.getId(), 1);
				
					for(Instituition instituitions : instituitionss){
						PpdAttendance ppdAttendance = controller.ppdReport(instituitions.getAcademic_instituition_id());
						count = count + 1;	
						%>
						<tr>	
							<td><%=count %></td>
							<td><a href="policeAttendanceStatus.jsp?instituitionId=<%=instituitions.getAcademic_instituition_id()%>" ><%=instituitions.getAcademic_name() %></a></td>
							<td><%=ppdAttendance.getTotalFinish() %></td>
							<td><%=ppdAttendance.getTotalClass() %></td>
							<td><%=ppdAttendance.getFinishPercentage() %>%</td>
							<td><%=ppdAttendance.getTotalAttend() %></td>
							<td><%=ppdAttendance.getTotalAbsent() %></td>
							<td><%=ppdAttendance.getTotalStudent() %></td>
							
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
		<h3>SENARAI MENENGAH</h3>
		<div>
			<table width="1000px" border="0" cellpadding="5">
				<tr>	
					<th rowspan="2">Bil</th>
					<th rowspan="2">Nama Sekolah</th>
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
					instituitionss = controller.getAllInstitutionsByStationId(station.getId(), 2);
				
					count = 0;
				
					for(Instituition instituitions : instituitionss){
						PpdAttendance ppdAttendance = controller.ppdReport(instituitions.getAcademic_instituition_id());
						count = count + 1;	
						%>
						<tr>	
							<td><%=count %></td>
							<td><a href="policeAttendanceStatus.jsp?instituitionId=<%=instituitions.getAcademic_instituition_id()%>" ><%=instituitions.getAcademic_name() %></a></td>
							<td><%=ppdAttendance.getTotalFinish() %></td>
							<td><%=ppdAttendance.getTotalClass() %></td>
							<td><%=ppdAttendance.getFinishPercentage() %>%</td>
							<td><%=ppdAttendance.getTotalAttend() %></td>
							<td><%=ppdAttendance.getTotalAbsent() %></td>
							<td><%=ppdAttendance.getTotalStudent() %></td>
							
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