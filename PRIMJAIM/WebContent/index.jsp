<%@page import="bean.Politician"%>
<%@page import="bean.Police"%>
<%@page import="controller.URLSecurity"%>
<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="errorPage.jsp"%>
<%@page import="bean.User"%>
<%@page import="bean.Guardian"%>
<%@page import="bean.Teacher"%>
<%
	User user = new User();
	Teacher teacher = new Teacher();
	Guardian guardian = new Guardian();
	Police police = new Police();
	Politician politician = new Politician();

	String pinKey = String.valueOf(session.getAttribute("pinKey"));
	String teacherIdEnc = new String();
	String guardianIdEnc = new String();
	String policeIdEnc = new String();
	String politicianIdEnc = new String();

	if (session.getAttribute("SASuser") != null) {
		user = (User) session.getAttribute("SASuser");
	}
	if (session.getAttribute("SASteacher") != null) {
		teacher = (Teacher) session.getAttribute("SASteacher");
	}
	if (session.getAttribute("SASguardian") != null) {
		guardian = (Guardian) session.getAttribute("SASguardian");
	}
	if (session.getAttribute("SASpolice") != null) {
		police = (Police) session.getAttribute("SASpolice");
	}
	if (session.getAttribute("SASpolitician") != null) {
		politician = (Politician) session.getAttribute("SASpolitician");
	}
%>
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
		<div id="box1" align="center">
			<h2 class="title">Selamat Datang ke <b>PRIM</b></h2>
			<div style="clear: both;">&nbsp;</div>
			<%
				if (user.getUser_id() != null) {
					int userLevel = user.getLevel();
					if (userLevel > 2 && userLevel < 11) {

						teacherIdEnc = URLSecurity.encryptFinal(teacher.getTeacher_id(), pinKey);
			%>
			<jsp:include page="contents/c_teacher_profile.jsp">
				<jsp:param name="id" value="<%=teacherIdEnc%>"></jsp:param>
			</jsp:include>

			<%
				} else if (userLevel == 2) {

						guardianIdEnc = URLSecurity.encryptFinal(guardian.getGuardian_id(), pinKey);
			%>
			<jsp:include page="contents/c_guardian_profile.jsp">
				<jsp:param name="id" value="<%=guardianIdEnc%>"></jsp:param>
			</jsp:include>
			<%
				}
					
				else if (userLevel == 11 || userLevel == 12) {

					politicianIdEnc = URLSecurity.encryptFinal(politician.getId(), pinKey);
					%>
					<jsp:include page="contents/c_politician_profile.jsp">
						<jsp:param name="id" value="<%=politicianIdEnc%>"></jsp:param>
					</jsp:include>
					<%
			}
				else if (userLevel == 13 || userLevel == 14) {

					policeIdEnc = URLSecurity.encryptFinal(police.getId(), pinKey);
					
					%>
					<jsp:include page="contents/c_police_profile.jsp">
						<jsp:param name="id" value="<%=policeIdEnc%>"></jsp:param>
					</jsp:include>
					<%
			}
				} else {
					response.sendRedirect("logIn.jsp");
				}
			%>
		</div>
	</div>

	<jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>