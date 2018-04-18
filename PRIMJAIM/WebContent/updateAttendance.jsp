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
		<h2 class="title">Kemaskini Kehadiran Kelas</h2>

<% 
	if(request.getParameter("studentId") != null && request.getParameter("id") != null){
%>

		<div style="clear: both;"><h3>Sila Pilih Tarikh Untuk Mengemaskini</h3></div>
		<br>
		<div class="entry">
		<jsp:include page="contents/c_updateAttendanceByStudent.jsp"></jsp:include>
		</div>
		
		<%
		
		if(request.getParameter("alert") != null){
		
			if(request.getParameter("alert").endsWith("0")){
				%>
					<script>alert("Kedatangan Belum Dibuat. Sila Buat Langkah 1 Dahulu.");</script>
				<%
			}else if(request.getParameter("alert").endsWith("1")){
				%>
					<script>alert("Kedatangan Tidak Boleh Diubah.");</script>
				<%	
			}

		
		}else if(request.getParameter("day") != null){
			
			%>
			
			<br>
			<div class="entry">
			<jsp:include page="contents/c_updateAttendanceStudent.jsp"></jsp:include>
			</div>
		
			<%
			
		}
		
	}else if(request.getParameter("studentId") == null && request.getParameter("id") != null){
%>

		<div style="clear: both;">&nbsp;</div>
		<div class="entry">
		<jsp:include page="contents/c_updateAttendance.jsp"></jsp:include>
		</div>
	
<% } %>


	</div>
</div>

	<jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>

