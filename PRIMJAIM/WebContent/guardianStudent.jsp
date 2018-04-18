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
		<h2 class="title">Laporan Kehadiran Pelajar</h2>
<% if(request.getParameter("studentId") != null) {
	
		String studentId = request.getParameter("studentId"); 
		String classId = request.getParameter("classId");
		%>
		<div style="clear: both;">&nbsp;</div>
		<div class="entry">
		<form name="SearchForm" method="post" action="guardianStudent.jsp?studentId=<%=studentId%>&classId=<%=classId%>">
			<b>Pilih bulan : </b>
			<input type="text" name="month" 
			class="pickmonth validate[required]" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="submit" name="searchAttendance" value="Cari"
				class="blackbutton" />
		</form>
		<p>&nbsp;</p>
		<hr />
		<p>&nbsp;</p>
		</div>
<% if(request.getParameter("month") != null) { %>
		<jsp:include page="contents/c_attendance_report_guardian.jsp"></jsp:include>
<% } %>
	</div>
<% } %>
</div>

	<jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>