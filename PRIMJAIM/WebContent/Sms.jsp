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
		<h2 class="title">Senarai Sistem Pesanan Ringkas</h2>
		<div style="clear: both;">&nbsp;</div>
		
			<jsp:include page="contents/c_sms.jsp"></jsp:include>
	</div>
</div>

	<jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>

