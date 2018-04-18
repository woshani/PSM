<%@page import="bean.Teacher"%>
<%@page import="bean.TeacherKoku"%>
<%@page import="facade.ControllerWrapper"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
	if(request.getAttribute("SASresponse") != null) {
%>
	<script>alert('<%=request.getAttribute("SASresponse") %>');</script>
<%
	}

	Teacher teacher = (Teacher) session.getAttribute("SASteacher");
	ControllerWrapper controller = new ControllerWrapper();
	
	List<TeacherKoku> teacherKokus = new ArrayList<TeacherKoku>();
	
	teacherKokus = controller.getTeacherKokuByTeacherId(teacher.getTeacher_id());
	
%>
<div class="entry">
<%if(teacherKokus.size() > 0) {%>
	<div id="accordion" style="width: 1100px;">
	<%
	for(TeacherKoku teacherKoku : teacherKokus){
	if(teacherKoku.getInstituitionKokuId().getKokuId().getKoku_Type().getKoku_Type().equalsIgnoreCase("BU")){
	%>
		<h3>BADAN BERUNIFORM : <%=teacherKoku.getInstituitionKokuId().getKokuId().getKokuName() %></h3>
		<div>
		<p>
			<a href="KokuStudentList.jsp?instId=<%=teacherKoku.getInstituitionKokuId().getInstituitionKokuId()%>" class="blackbutton"><font color="white">Senarai Pelajar</font></a>
			<a href="kokuMeeting.jsp?instId=<%=teacherKoku.getInstituitionKokuId().getInstituitionKokuId()%>" class="blackbutton"><font color="white">Kedatangan</font></a>
			<%-- <a href="kokuMeeting.jsp?instId=<%=teacherKoku.getInstituitionKokuId().getInstituitionKokuId()%>" class="blackbutton"><font color="white">Menetap Jawatan</font></a> --%>
		</p>	
		</div>
	<%}if(teacherKoku.getInstituitionKokuId().getKokuId().getKoku_Type().getKoku_Type().equalsIgnoreCase("KP")){ %>	
		<h3>KELAB PERSATUAN : <%=teacherKoku.getInstituitionKokuId().getKokuId().getKokuName() %></h3>
		<div>
		<p>
			<a href="KokuStudentList.jsp?instId=<%=teacherKoku.getInstituitionKokuId().getInstituitionKokuId()%>" class="blackbutton"><font color="white">Senarai Pelajar</font></a>
			<a href="kokuMeeting.jsp?instId=<%=teacherKoku.getInstituitionKokuId().getInstituitionKokuId()%>" class="blackbutton"><font color="white">Kedatangan</font></a>
		</p>
		</div>
	<%}if(teacherKoku.getInstituitionKokuId().getKokuId().getKoku_Type().getKoku_Type().equalsIgnoreCase("SP")){ %>	
		<h3>SUKAN PERMAINAN : <%=teacherKoku.getInstituitionKokuId().getKokuId().getKokuName() %></h3>
		<div>
		<p>
			<a href="KokuStudentList.jsp?instId=<%=teacherKoku.getInstituitionKokuId().getInstituitionKokuId()%>" class="blackbutton"><font color="white">Senarai Pelajar</font></a>
			<a href="kokuMeeting.jsp?instId=<%=teacherKoku.getInstituitionKokuId().getInstituitionKokuId()%>" class="blackbutton"><font color="white">Kedatangan</font></a>
		</p>
		</div>
	<%}}%>
	</div>
	<%}else{ %>
	<p>Tiada Data</p>
	<%} %> 
</div>