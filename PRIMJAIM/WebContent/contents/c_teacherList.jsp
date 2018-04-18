<%@ page import="facade.ControllerWrapper"%>
<%@ page import="bean.TeacherClass"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.Enumeration"%>
<%
	TeacherClass teacherClass = new TeacherClass();
	ControllerWrapper controller = new ControllerWrapper();
	Vector<TeacherClass> teacherClasss = new Vector<TeacherClass>();
	
	teacherClasss = controller.TeacherList();

%>
<table width="700px" border="1" cellspacing="0" cellpadding="3">
		
		
			<%
	Enumeration enum1 = teacherClasss.elements();

	while (enum1.hasMoreElements()) {

		teacherClass = new TeacherClass();
		teacherClass = (TeacherClass) enum1.nextElement();
		%>
		<tr>
		<td><%=teacherClass.getTeacherId()%></td>
		<td><%=teacherClass.getTeacherName() %></td>
		<td><%=teacherClass.getClassName() %></td>
		</tr>
	<%
	}
	%>
		
	</table>