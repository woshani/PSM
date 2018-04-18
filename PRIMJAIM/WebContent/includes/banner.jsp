<%@page import="controller.URLSecurity"%>
<%@page import="bean.Teacher"%>
<%@page import="bean.User"%>
<%@page import="bean.Instituition"%>
<%@page import="facade.ControllerWrapper"%>
<%
	ControllerWrapper controller = new ControllerWrapper();
	Teacher teacher = null;
	Instituition instituition = new Instituition();
	String instituitionId = null;
	User user = null;

	if (session.getAttribute("SASteacher") != null) {
		teacher = (Teacher) session.getAttribute("SASteacher");
	}

	if (session.getAttribute("SASuser") != null) {
		user = (User) session.getAttribute("SASuser");
	}

	if (teacher != null) {
		instituitionId = controller.getInstituitionByTeacherId(teacher
				.getTeacher_id());
		instituition = controller.getInstituitionById(instituitionId);
	}

	String pinKey = String.valueOf(session.getAttribute("pinKey"));
%>

<div id="logo" class="container">
	<%
		if (instituitionId != null) {
	%>

	<img align="left"
		src="${pageContext.request.contextPath}/Image?imgID=<%=instituitionId%>"
		width="115" border="0" />
	<!-- <img align="left" src="images.jsp?imgID=" width="115" border="0"> -->
	<table>
		<%
			if (user.getLevel() == 8 || user.getLevel() == 9) {
		%>
		<tr>
			<td><b>INSTITUSI</b></td>
			<td width="30" align="center">:</td>
			<td title="Kemaskini Maklumat Institusi"><u><b><a
						href="updateInstitutionProfile.jsp?id=<%=URLSecurity.encryptFinal(
							instituition.getAcademic_instituition_id(), pinKey)%>"><%=instituition.getAcademic_name()%></a></b></u>
			</td>
		</tr>
		<%
			} else if (user.getLevel() == 7) {
		%>
		<tr>
			<td><b>SEKOLAH</b></td>
			<td width="30" align="center">:</td>
			<td title="Kemaskini Maklumat Institusi"><u><b><a
						href="updateInstitutionProfile.jsp?id=<%=URLSecurity.encryptFinal(
							instituition.getAcademic_instituition_id(), pinKey)%>"><%=instituition.getAcademic_name()%></a></b></u>
			</td>
		</tr>
		<%
			} else {
		%>
		<tr>
			<td><b>SEKOLAH</b></td>
			<td width="30" align="center">:</td>
			<td title="Kemaskini Maklumat Institusi"><b><%=instituition.getAcademic_name()%></b>
			</td>
		</tr>
		<%
			}
		%>
		<tr>
			<td><b>ALAMAT</b></td>
			<td width="30" align="center">:</td>
			<td><b><%=instituition.getAddress()%></b></td>
		</tr>
		<tr>
			<td></td>
			<td width="30" align="center"></td>
			<td><b><%=instituition.getPostcode()%>,<%=instituition.getCity()%></b></td>
		</tr>
		<tr>
			<td><b>NO TEL</b></td>
			<td width="30" align="center">:</td>
			<td><b><%=instituition.getTelephone_number()%></b></td>
		</tr>
		<tr>
			<td><b>FAX</b></td>
			<td width="30" align="center">:</td>
			<td><b><%=instituition.getFax_number()%></b></td>
		</tr>
	</table>

	<%
		} else {
	%>
	<h1>
		<a href="index.jsp"><img src="images/PRIME.png" /></a>

	</h1>
	<div style="background-color: black;">
		<marquee loop="10" direction="left">
			<a style="font-size: 20px;" target="4">
				<font color="red">* </font><font color="white">SELAMAT DATANG KE APLIKASI ATAS TALIAN PARENTAL RELATIONSHIP INFORMATION MANAGEMENT <b>(PRIM@UTeM)</b>. </font>
<!-- 				<font color="red">* </font><font color="white"></font> -->
			</a>
		</marquee>
	</div>
	<%
		}
	%>
</div>