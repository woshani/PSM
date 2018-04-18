<%@page import="facade.ControllerWrapper"%>
<%@page import="bean.Police"%>
<%@page import="bean.Station"%>
<%@page import="bean.Teacher"%>

<%
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
	
	String institutionId = request.getParameter("instituitionId");
	
	
%>
<div class="entry">
<input type="hidden" id="instituitionId" name="instituitionId" value="<%=institutionId%>">

<div id="accordion" style="width: 1100px;">
<h3>SENARAI KELAS KEDATANGAN SELESAI</h3>
<div>
	<jsp:include page="c_attendanceStatusDone.jsp"></jsp:include>
</div>
<h3>SENARAI KELAS KEDATANGAN BELUM SELESAI</h3>
<div>
	<jsp:include page="c_attendanceStatusNotDone.jsp"></jsp:include>
</div>
<h3>STATISTIK KEDATANGAN</h3>
<div>
	<jsp:include page="c_Test.jsp"></jsp:include>
</div>
</div>


</div>