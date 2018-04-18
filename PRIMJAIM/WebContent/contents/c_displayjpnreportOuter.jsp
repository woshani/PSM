<%@page import="bean.PpdAttendance"%>
<%@page import="facade.ControllerWrapper"%>
<%@page import="bean.Instituition"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>


<%
		Instituition instituition = new Instituition();
		ControllerWrapper controller = new ControllerWrapper();
		PpdAttendance ppdAttendance = null;
		String city = null;
		int count = 0;

		if(request.getParameter("city") != null){
			city = request.getParameter("city");
		}else{
			city = "MELAKA";
		}
		
		List<Instituition> instituitions = controller.getPpdByCity(city);
%>


<div id="accordion" style="width: 100%;"  >
<h3>STATISTIK PPD</h3>	
	<div>
		<table width="100%" border="0" cellpadding="5">
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
				
					for(Instituition instituitionss : instituitions){
						ppdAttendance = controller.jpnPpdReport(instituitionss.getPpd());
						count = count + 1;	
						%>
						<tr>	
							<td><%=count %></td>
							<td><%=instituitionss.getAcademic_name() %></td>
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
							}
					}
					%>	
						
			</table>
	</div>
</div>





