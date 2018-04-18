<%@page import="facade.ControllerWrapper"%>
<%@ page import="bean.Student"%>
<%@ page import="bean.Classroom"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%

String studentId[] = request.getParameterValues("PickList");

if(studentId != null){

ControllerWrapper controller = new ControllerWrapper();
Student student = null;

Classroom classroom = new Classroom();
classroom = controller.getClassroomByStudentId(studentId[0]);

List<Classroom> classes = new ArrayList<Classroom>();
classes = controller.getClassroomList(classroom.getClass_id());

%>
<form id="studentClassList" method="post" action="RegisterClass"">
<input type="hidden" id="class" name="class" value="<%=classroom.getClass_id()%>">
<table>
<tr>
	<th width="50">Bil</th>
	<th width="400">Pelajar</th>
	<th width="200">Kelas</th>
</tr>

<%
int index = 0;
while (index < studentId.length) {
	
	student = new Student();
	
	student = controller.getStudentById(studentId[index]);
	index = index + 1;
%>



<tr>
	<td><%=index %></td>
	<td><%=student.getName()%><input type="hidden" id="studentid" name="studentid" value="<%=student.getStudent_id()%>"></td>
	
	<td align="center">
		<select name="classRegister" id="classRegister">
		<%for(Classroom classs : classes){ %>
			<option value=<%=classs.getClass_id() %>><%=classs.getName() %></option>
		<%} %>
		</select>
	</td>
	
</tr>

<%
}
%>

</table>

<br>

<center><input type="submit" 
			name="registerOtherStudentClass" value="Hantar"
			class="blackbutton" /></center>
</form>
<%}%>