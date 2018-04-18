<%@page import="bean.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true"%>
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
	<div id="box1" align="center">
	
		<%		
			if(session.getAttribute("SASuser")==null){ 
			// if session is expired, forward it to login page
		%>
			<h2 class="title"><b>PRIM</b> : <font color="red">Sesi anda telah TAMAT</font></h2>
			<div style="clear: both;"></div>
			<div class="entry">
			<h3>Sila Log Masuk semula untuk menggunakan PRIM. </h3>
			<h3><a href="/logIn.jsp"><font color="blue">[KEMBALI KE LAMAN UTAMA]</font></a></h3>
			
		</div>
		<%} else{
		%>
			
		<h2 class="title"><b>PRIM :</b> Masalah Teknikal</h2>
		<div style="clear: both;"></div>
		<div class="entry">
			<h3>Harap maaf, PRIM mengalami sedikit masalah teknikal dan tidak dapat meneruskan proses yang anda kehendaki.</h3>
			<h3>Sila berhubung dengan pihak ADMIN kami bagi mengatasi masalah ini. Kesulitan anda amat kami kesali.</h3>
			<p><b>Error Message:</b> <%=exception.getMessage() %></p>
		</div>
		<%} %>
	</div>
</div>

	<jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>