<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="errorPage.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="includes/header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="includes/banner.jsp"></jsp:include>
	<jsp:include page="includes/menu.jsp"></jsp:include>

<div id="page" class="container">
	<div id="box1">
		<div style="clear: both;">&nbsp;</div>
		
		<div style="clear: both;">&nbsp;</div>
		<div class="entry">
		
		<form name="SearchForm" method="post" action="schoolAttendanceList.jsp">
			<b>Pilih bulan : </b>
			<input type="text" name="month" 
			class="pickmonth validate[required]" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="submit" name="searchAttendance" value="Cari"
				class="blackbutton" />
		</form>
<% if(request.getParameter("month") != null) { %>		
		<jsp:include page="contents/c_school_attendance_list.jsp"></jsp:include>
<%} %>
	</div>
</div>
</div>
	<jsp:include page="includes/footer.jsp"></jsp:include>

</body>
</html>