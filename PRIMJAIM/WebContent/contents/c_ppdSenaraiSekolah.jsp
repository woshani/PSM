<%@page import="controller.URLSecurity"%>
<%@page import="facade.ControllerWrapper"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="bean.Teacher"%>
<%@page import="bean.Instituition"%>

<%
	String pinKey = String.valueOf(session.getAttribute("pinKey"));
	String institutionIdEnc = new String();

	Teacher teacher = null;
	Instituition instituition = new Instituition();
	ControllerWrapper controller = new ControllerWrapper();

	if (session.getAttribute("SASteacher") != null) {
		teacher = (Teacher) session.getAttribute("SASteacher");
	}

	if (teacher != null) {
		instituition = controller.getInstituitionById(controller.getInstituitionByTeacherId(teacher
				.getTeacher_id()));
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
				List<Instituition> instituitionss = controller.getPPDSekolahByJenis(instituition.getPpd(), 1);

				for (Instituition instituitions : instituitionss) {
					count = count + 1;
					institutionIdEnc = URLSecurity.encryptFinal(instituitions.getAcademic_instituition_id(), pinKey);
			%>
			<tr>
				<td><%=count%></td>
				<td><a
					href="instituteProfile.jsp?id=<%=institutionIdEnc%>"><%=instituitions.getAcademic_name()%></a></td>
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
				instituitionss = controller.getPPDSekolahByJenis(instituition.getPpd(), 2);
				count = 0;
				for (Instituition instituitions : instituitionss) {
					count = count + 1;
					
					institutionIdEnc = URLSecurity.encryptFinal(instituitions.getAcademic_instituition_id(), pinKey);
			%>
			<tr>
				<td><%=count%></td>
				<td><a
					href="instituteProfile.jsp?id=<%=institutionIdEnc%>"><%=instituitions.getAcademic_name()%></a></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</div>