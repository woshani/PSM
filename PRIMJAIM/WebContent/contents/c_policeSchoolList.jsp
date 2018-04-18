<%@page import="bean.Police"%>
<%@page import="bean.Station"%>
<%@page import="controller.URLSecurity"%>
<%@page import="facade.ControllerWrapper"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="bean.Teacher"%>
<%@page import="bean.Instituition"%>

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

	int count = 0;
%>

<div id="accordion" style="width: 1100px;">
	<h3>SEKOLAH RENDAH</h3>
	<div>
		<table width="1000px" border="0" cellpadding="5">
			<tr>
				<th>Bil</th>
				<th>Sekolah</th>
			</tr>
			<%
				List<Instituition> instituitionss = controller.getAllInstitutionsByStationId(station.getId(), 1);

				for (Instituition instituitions : instituitionss) {
					count = count + 1;
					institutionIdEnc = URLSecurity.encryptFinal(instituitions.getAcademic_instituition_id(), pinKey);
			%>
			<tr>
				<td align=center><%=count%></td>
				<td><%=instituitions.getAcademic_name()%></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
	<h3>SENARAI MENENGAH</h3>
	<div>
		<table width="1000px" border="0" cellpadding="5">
			<tr>
				<th>Bil</th>
				<th>Sekolah</th>
			</tr>
			<%
				instituitionss = controller.getAllInstitutionsByStationId(station.getId(), 2);
				count = 0;
				for (Instituition instituitions : instituitionss) {
					count = count + 1;
					
					institutionIdEnc = URLSecurity.encryptFinal(instituitions.getAcademic_instituition_id(), pinKey);
			%>
			<tr>
				<td align=center><%=count%></td>
				<td><%=instituitions.getAcademic_name()%></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</div>