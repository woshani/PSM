<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="errorPage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="includes/header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="includes/banner.jsp"></jsp:include>
	<jsp:include page="includes/menu.jsp"></jsp:include>

	<div id="page" class="container">
		<div id="box1">
			<h2 class="title">Kemaskini Maklumat Institusi</h2>
			<div style="clear: both;">&nbsp;</div>


			<%
				if (request.getParameter("id") != null) {
			%>
			<jsp:include page="contents/c_update_institution_profile.jsp"></jsp:include>
			<%
				}
			%>
		</div>
	</div>

	<jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>