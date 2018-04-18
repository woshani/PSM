<%@page import="bean.Teacher"%>

<%
	Teacher teacher = new Teacher();
	teacher = (Teacher) session.getAttribute("SASteacher");
	String instituitionId = null;

	if (request.getParameter("instituitionId") != null) {
		instituitionId = request.getParameter("instituitionId");
	}
%>
<div class="entry">
	<input type="hidden" id="instituitionId" name="instituitionId"
		value="<%=instituitionId%>">
	<%
		if (teacher.getPosition().equalsIgnoreCase("GPK")
				|| teacher.getPosition().equalsIgnoreCase("GB")
				|| teacher.getPosition().equalsIgnoreCase("GPKH")
				|| teacher.getPosition().equalsIgnoreCase("PPD")
				|| teacher.getPosition().equalsIgnoreCase("JPN")
				|| teacher.getPosition().equalsIgnoreCase("KPM")) {
	%>
	<div id="accordion" style="width: 1100px;">
		<h3>SENARAI KELAS KEDATANGAN SELESAI</h3>
		<div>
			<jsp:include page="c_attendanceStatusDone.jsp"></jsp:include>
		</div>
		<h3>SENARAI KELAS KEDATANGAN BELUM SELESAI</h3>
		<div>
			<jsp:include page="c_attendanceStatusNotDone.jsp"></jsp:include>
		</div>
		<h3>STATISTIK KEDATANGAN TAHAP 1</h3>
		<div>
			<jsp:include page="c_attendanceStatisticTahap1.jsp"></jsp:include>
		</div>
		<h3>STATISTIK KEDATANGAN TAHAP 2</h3>
		<div>
			<jsp:include page="c_attendanceStatisticTahap2.jsp"></jsp:include>
		</div>
		<h3>STATISTIK KEDATANGAN KESELURUHAN</h3>
		<div>
			<jsp:include page="c_attendanceStatisticAll.jsp"></jsp:include>
		</div>
	</div>
	<%
		}
	%>

</div>