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
		<div id="box1" align=center>
			<h2 class="title"><b>Rekod Laporan Kehadiran Harian</b></h2>
			<div class="entry">
				<%
					if (request.getParameter("month") == null) {
						
				%>


				<form name="SearchForm" method="post" action="PoliceStationReportHistory">
					<b>Pilih Tarikh : </b> <input type="text" name="selectedMonth"
						class="date validate[required]" required/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
						type="submit" name="searchAttendance" value="Cari"
						class="blackbutton" />
				</form>
				<p>&nbsp;</p>
				<hr />
				<p>&nbsp;</p>



				<%
					}
					if (request.getParameter("month") != null) {
						String month = request.getParameter("month");
				%>

				<form name="SearchForm" method="post" action="PoliceStationReportHistory">
					<b>Pilih Tarikh : </b> <input type="text" name="selectedMonth" 
						class="date validate[required]" value=<%=month%> required
						/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
						type="submit" name="searchAttendance" value="Cari"
						class="blackbutton" />
				</form>
				<p>&nbsp;</p>
				<hr />
				<p>&nbsp;</p>


				<jsp:include page="contents/c_policeStationReportHistory.jsp"></jsp:include>
				<%
					}
				%>
			</div>

		</div>
	</div>
	<jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>