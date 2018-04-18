<%@ page import="facade.ControllerWrapper"%>
<%@ page import="bean.Teacher"%>
<%@ page import="bean.Classroom"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>


<%
	ControllerWrapper controller = new ControllerWrapper();
	Teacher teacher = null;
	List<Classroom> classrooms = new ArrayList<Classroom>();
	
	String teacher_id = request.getParameter("id");

	if (teacher_id == null) {
		teacher = (Teacher) session.getAttribute("SASteacher");
	} else {
		teacher = controller.getTeacherById(teacher_id);
	}
	
	classrooms = controller.getClassBySchool(teacher.getTeacher_id());
	
	int index = 0;
%>

<table width="1000px" border="1" cellspacing="0" cellpadding="3">
	<tr>
		<th rowspan="2">BIL</th>
		<th rowspan="2">GURU</th>
		<th rowspan="2">KELAS</th>
		<th colspan="2">JUMLAH HADIR</th>
		<th colspan="2">JUMLAH TIDAK HADIR</th>
		<th rowspan="2">JUMLAH PELAJAR</th>
	</tr>
	<tr>
		<th>L</th>
		<th>P</th>
		<th>L</th>
		<th>P</th>
	</tr>
	<%for(Classroom classroom : classrooms){
		
		teacher = controller.getTeacherByClassroomId(classroom.getClass_id());
		%>
	<tr>
			<td><%=index = index + 1 %></td>
			<%if(teacher.getName() == null){%>
			<td>TIADA GURU</td>
			<% }else{%>
			<td><%=teacher.getName()%></td>
			<%} %>
			<td><%=classroom.getName() %></td>
			<td><%=controller.totalAttendMale(classroom.getClass_id()) %></td>
			<td><%=controller.totalAttendFemale(classroom.getClass_id()) %></td>
			<td><%=controller.totalAbsentMale(classroom.getClass_id()) %></td>
			<td><%=controller.totalAbsentFemale(classroom.getClass_id()) %></td>
			<td><%=controller.jumlahPelajar(classroom.getClass_id()) %></td>
	</tr>
	<%}teacher = (Teacher) session.getAttribute("SASteacher"); %>
	
	

	<tr>
			<td colspan="3" align="center" rowspan="2">JUMLAH</td>
			<td><%=controller.getHadirKelasJantina("/", teacher.getTeacher_id(), "L") %></td>
			<td><%=controller.getHadirKelasJantina("/", teacher.getTeacher_id(), "P") %></td>
			<td><%=controller.getHadirKelasJantina("O", teacher.getTeacher_id(), "L") %></td>
			<td><%=controller.getHadirKelasJantina("O", teacher.getTeacher_id(), "P") %></td>
			<td></td>
	</tr>
	<tr>
	
			<td colspan="2"><%=controller.getHadirKelas("/", teacher.getTeacher_id()) %></td>
			<td colspan="2"><%=controller.getHadirKelas("O", teacher.getTeacher_id()) %></td> 
			<td><%=controller.getHadirKelas("/", teacher.getTeacher_id())+controller.getHadirKelas("O", teacher.getTeacher_id()) %></td>
	</tr>
</table>