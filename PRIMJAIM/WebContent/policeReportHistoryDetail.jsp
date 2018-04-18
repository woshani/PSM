<%@page import="bean.Station"%>
<%@page import="bean.Police"%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="errorPage.jsp"%>
<%@page import="bean.Instituition"%>
<%@page import="facade.ControllerWrapper"%>
<%@ page import="bean.Teacher"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="includes/header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="includes/banner.jsp"></jsp:include>
	<jsp:include page="includes/menu.jsp"></jsp:include>
	
	<%
		String month = request.getParameter("month");
				
		Instituition instituition = null;
		ControllerWrapper controller = new ControllerWrapper();
		Station station = new Station();
		Police police = new Police();
		
		if(request.getParameter("stationId") != null){
			
			station = controller.getStationByStationId(request.getParameter("stationId"));

		}
		
	%>
	
<div id="page" class="container">
	<div id="box1">
		<div style="clear: both;"><center><h2 class="title">REKOD STATISTIK LAPORAN KEHADIRAN HARIAN </br> <b><%=station.getName()%></b> </br> (<b><%=month%></b>) </h2></center>
		</div>
		<div style="clear: both;">&nbsp;</div>

		<jsp:include page="contents/c_policeReportHistoryDetail.jsp"></jsp:include>

	</div>
</div>

	<jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>