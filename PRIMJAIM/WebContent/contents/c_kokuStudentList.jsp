<%@page import="bean.StudentKoku"%>
<%@page import="bean.SkPosition"%>
<%@page import="facade.ControllerWrapper"%>
<%@page import="java.util.List"%>

<%

	String instKokuId = request.getParameter("instId");
	SkPosition skPosition = null;
	ControllerWrapper controller = new ControllerWrapper();
	List<StudentKoku> studentKokus = controller.getAllStudentByInstKokuId(instKokuId);
	
%>

<div class="entry">

<table>
	
		<tr>
			<td>Bil</td>
			<td>Pelajar</td>
			<td>Jawatan</td>
		</tr>
		
<%
	int index = 0;
	for(StudentKoku studentKoku : studentKokus){
		
		skPosition = new SkPosition();
		
		skPosition = controller.getSkPositionByStudentKokuId(studentKoku.getStudentKokuId());
		
		%>
			
		<tr>
			<td><%=index=index+1 %></td>
			<td><%=studentKoku.getStudentId().getName()%></td>
			<td><%=skPosition.getPosition().getPosition_name() %></td>
		</tr>	
		
		<%
		
	}

%>
</table>

</div>