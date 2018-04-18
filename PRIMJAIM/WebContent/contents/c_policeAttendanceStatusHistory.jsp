<%@page import="bean.Station"%>
<%@page import="bean.Police"%>
<%@page import="facade.ControllerWrapper"%>
<%@page import="bean.Instituition"%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="bean.Teacher"%>

<%
	String month = request.getParameter("month");
	SimpleDateFormat fromUser = new SimpleDateFormat("dd-MM-yy");

	Date date = new Date(fromUser.parse(month).getTime());

	String pinKey = String.valueOf(session.getAttribute("pinKey"));
	String institutionIdEnc = new String();
	
	Station station = new Station();
	Police police = new Police();
	
	ControllerWrapper controller = new ControllerWrapper();
	
	if (session.getAttribute("SASpolice") != null) {
		police = (Police) session.getAttribute("SASpolice");
	}
	
	if (police != null) {
		station = controller.getStationByPoliceId(police.getId());
	}
	
	Instituition instituition = controller.getInstituitionById(request.getParameter("instituitionId"));
%>
<div class="entry">

	<center>
		<h2 class="title">
			REKOD STATISTIK LAPORAN KEHADIRAN HARIAN </br> <b><%=instituition.getAcademic_name()%></b>
			</br> (<b><%=month%></b>)
		</h2>
	</center>
	<input type="hidden" id="instituitionId" name="instituitionId"
		value="<%=instituition.getAcademic_instituition_id()%>">
	
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
		<h3>STATISTIK KEDATANGAN</h3>
		<div>
			<jsp:include page="c_TestHistory.jsp"></jsp:include>
		</div>
	</div>
</div>