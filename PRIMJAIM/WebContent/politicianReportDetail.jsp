<%@page import="bean.Politician"%>
<%@page import="bean.Dun"%>
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
		
		Dun dun = new Dun();
		Politician politician = new Politician();
		
		if(request.getParameter("dunId") != null){
			
			dun = controller.getDunByDunId(request.getParameter("dunId"));

		}
		
	%>
	
<div id="page" class="container">
	<div id="box1">
		<div style="clear: both;"><center><h2 class="title">REKOD STATISTIK LAPORAN KEHADIRAN HARIAN </br> <b>DUN <%=dun.getName()%> (<%=dun.getCode() %>)</b> </br></h2></center>
		</div>
		<div style="clear: both;">&nbsp;</div>

		<jsp:include page="contents/c_politicianReportDetail.jsp"></jsp:include>

	</div>
</div>

	<jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>