<%@page import="bean.PpdAttendance"%>
<%@ page import="bean.Teacher"%>

<%@page import="facade.ControllerWrapper"%>
<%@page import="bean.Instituition"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>


<%

		Teacher teacher = null;
		Instituition instituition = new Instituition();
		ControllerWrapper controller = new ControllerWrapper();
		PpdAttendance ppdAttendance = null;
		String city = null;
		int count = 0;
		
		
		if(session.getAttribute("SASteacher") != null){
			teacher = (Teacher) session.getAttribute("SASteacher");
		}
		
		if(request.getParameter("city") != null){
			city = request.getParameter("city");
		}else{
			instituition = controller.getInstituitionById(controller.getInstituitionByTeacherId(teacher.getTeacher_id()));
			city = instituition.getCity();
		}
		
		List<Instituition> instituitions = controller.getPpdByCity(city);
		
		

%>

	<script type="text/javascript">
	$(document).ready(function() {
		$(document).ready(function() {
			  $('table.highchart').highchartTable();
			});
		
		$('table.highchart').bind('highchartTable.beforeRender', function(event, highChartConfig) {
			  $.each(highChartConfig.yAxis, function (index, value)   {
			    value.title.text = value.title.text || 'Peratus %';
			  });
			});
			
	}
		  
		  );
		  
		  
	</script>
	
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
	
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
				
					for(Instituition instituitionss : instituitions){
						ppdAttendance = controller.jpnPpdReport(instituitionss.getPpd());
						count = count + 1;	
						%>
						<tr>	
							<td><%=count %></td>
							<td><a href="PPDKedatanganSekolah.jsp?instituitionId=<%=instituitionss.getAcademic_instituition_id()%>" ><%=instituitionss.getAcademic_name() %></a></td>
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

<%

	ppdAttendance = controller.jpnReport(city);

%>

<table class="highchart" data-graph-container-before="1" data-graph-type="column" style="display:none" >
<caption>Statistik Peratusan Kedatangan Negeri <%=city %></caption>
  <thead>
      <tr>
          <th></th>
          <th>Siap/Hadir</th>
          <th>Tidak Siap/Tidak Hadir</th>
      </tr>
   </thead>
   <tbody>
      <tr>
          <td>Kebersedian Sekolah</td>
          <td><%=ppdAttendance.getFinishPercentage() %>%</td>
          <td><%=100 - ppdAttendance.getFinishPercentage() %>%</td>
      </tr>
      <tr>
          <td>Kehadiran Murid</td>
          <td><%=ppdAttendance.getAttendancePercentage() %>%</td>
          <td><%=100 - ppdAttendance.getAttendancePercentage() %>%</td>
      </tr>
  </tbody>
</table>

