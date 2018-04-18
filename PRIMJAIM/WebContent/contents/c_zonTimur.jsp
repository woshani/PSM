<%@ page import="bean.PpdAttendance"%>
<%@page import="facade.ControllerWrapper"%>

 <script type="text/javascript">
	$(document).ready(function() {
		  $('table.highchart').highchartTable();
		});
	
</script>
	
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script> 
	

<%

	ControllerWrapper controller = new ControllerWrapper();
	PpdAttendance ppdAttendance = new PpdAttendance();
	

%>	

<table width="1100px" border="0" cellspacing="0" cellpadding="3">
<tr>
	<td width="400"><a href="JPNReport.jsp?city=PAHANG" title="Klik Untuk Lihat Statistik Jpn">
			<table class="highchart" data-graph-container-before="1" data-graph-type="pie" style="display:none" data-graph-datalabels-enabled="1" data-graph-color-1="#999">
			    <caption>PAHANG</caption>
			    <%
			    
			    	ppdAttendance = controller.jpnReport("PAHANG");
			    
			    %>
			    <thead>
			        <tr>                                  
			            <th></th>
			            <th>Peratus Murid</th>
			        </tr>
			     </thead>
			     <tbody>
			        <tr>
			            <td>Hadir</td>
			            <td data-graph-item-color="#4900FF"><%=ppdAttendance.getAttendancePercentage() %></td>

			        </tr>
			        <tr>
			            <td>Tidak Hadir</td>
			            <td data-graph-item-color="#FF0000"><%=100 - ppdAttendance.getAttendancePercentage() %></td>

			        </tr>
			    </tbody>
			</table></a>
		</td>
		<td width="400"><a href="JPNReport.jsp?city=TERENGGANU" title="Klik Untuk Lihat Statistik Jpn">
			<table class="highchart" data-graph-container-before="1" data-graph-type="pie" style="display:none" data-graph-datalabels-enabled="1" data-graph-color-1="#999">
			    <caption>TERENGGANU</caption>
			    <%
			    
			    	ppdAttendance = controller.jpnReport("TERENGGANU");
			    
			    %>
			    <thead>
			        <tr>                                  
			            <th></th>
			            <th>Peratus Murid</th>
			        </tr>
			     </thead>
			     <tbody>
			        <tr>
			            <td>Hadir</td>
			            <td data-graph-item-color="#4900FF"><%=ppdAttendance.getAttendancePercentage() %></td>

			        </tr>
			        <tr>
			            <td>Tidak Hadir</td>
			            <td data-graph-item-color="#FF0000"><%=100 - ppdAttendance.getAttendancePercentage() %></td>

			        </tr>
			    </tbody>
			</table></a>
		</td>
		<td width="400"><a href="JPNReport.jsp?city=KELANTAN" title="Klik Untuk Lihat Statistik Jpn">
			<table class="highchart" data-graph-container-before="1" data-graph-type="pie" style="display:none" data-graph-datalabels-enabled="1" data-graph-color-1="#999">
			    <caption>KELANTAN</caption>
			    <%
			    
			    	ppdAttendance = controller.jpnReport("KELANTAN");
			    
			    %>
			    <thead>
			        <tr>                                  
			            <th></th>
			            <th>Peratus Murid</th>
			        </tr>
			     </thead>
			     <tbody>
			        <tr>
			            <td>Hadir</td>
			            <td data-graph-item-color="#4900FF"><%=ppdAttendance.getAttendancePercentage() %></td>

			        </tr>
			        <tr>
			            <td>Tidak Hadir</td>
			            <td data-graph-item-color="#FF0000"><%=100 - ppdAttendance.getAttendancePercentage() %></td>

			        </tr>
			    </tbody>
			</table></a>
		</td>
</tr>		
</table>