<%@page import="facade.ControllerWrapper"%>
<%@page import="bean.Instituition"%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="bean.Teacher"%>

<%
	String month = request.getParameter("month");
	SimpleDateFormat fromUser = new SimpleDateFormat("dd-MM-yy");

	Date date = new Date(fromUser.parse(month).getTime());

	Teacher teacher = new Teacher();
	teacher = (Teacher) session.getAttribute("SASteacher");
	String instituitionId = null;

	Instituition instituition = new Instituition();
	ControllerWrapper controller = new ControllerWrapper();

	if (request.getParameter("instituitionId") != null) {
		instituitionId = request.getParameter("instituitionId");
		instituition = controller.getInstituitionById(instituitionId);
	}
%>
<div class="entry">

	<center>
		<h2 class="title">
			REKOD STATISTIK LAPORAN KEHADIRAN HARIAN </br> <b><%=instituition.getAcademic_name()%></b>
			</br> (<b><%=month%></b>)
		</h2>
	</center>
	<input type="hidden" id="instituitionId" name="instituitionId"
		value="<%=instituitionId%>">
	<%
		if (teacher.getPosition().equalsIgnoreCase("GPK") || teacher.getPosition().equalsIgnoreCase("GB")
				|| teacher.getPosition().equalsIgnoreCase("GPKH") || teacher.getPosition().equalsIgnoreCase("PPD")
				|| teacher.getPosition().equalsIgnoreCase("JPN") || teacher.getPosition().equalsIgnoreCase("KPM")) {
	%>
	<div id="accordion" style="width: 1100px;">
		<h3>SENARAI KELAS KEDATANGAN SELESAI</h3>
		<div>
			<jsp:include page="c_attendanceStatusDoneHistory.jsp"></jsp:include>
		</div>
		<h3>SENARAI KELAS KEDATANGAN BELUM SELESAI</h3>
		<div>
			<jsp:include
				page="c_attendanceStatusNotDoneHistory.jsp"></jsp:include>
		</div>
		<h3>STATISTIK KEDATANGAN TAHAP 1</h3>
		<div>
			<jsp:include page="c_attendanceStatisticTahap1History.jsp"></jsp:include>
		</div>
		<h3>STATISTIK KEDATANGAN TAHAP 2</h3>
		<div>
			<jsp:include page="c_attendanceStatisticTahap2History.jsp"></jsp:include>
		</div>
		<h3>STATISTIK KEDATANGAN KESELURUHAN</h3>
		<div>
			<jsp:include page="c_attendanceStatisticAllHistory.jsp"></jsp:include>
		</div>
	</div>
	<%
		}
	%>

</div>