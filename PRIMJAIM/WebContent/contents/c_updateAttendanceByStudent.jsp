<%@page import="controller.URLSecurity"%>
<%@page import="bean.DailyAttendance"%>
<%@page import="bean.Student"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="facade.ControllerWrapper"%>

<%
	String pinKey = String.valueOf(session.getAttribute("pinKey"));
	
	String studentIdEnc = request.getParameter("studentId");
	String classIdEnc = request.getParameter("id");
	
	String studentId = URLSecurity.decryptFinal(studentIdEnc, pinKey);
	String classId = URLSecurity.decryptFinal(classIdEnc, pinKey);
	
	
	String month = request.getParameter("month");
	String year = request.getParameter("year");
	String actualMonth = request.getParameter("actualMonth");
	
	ControllerWrapper controller = new ControllerWrapper();
	
	DailyAttendance attendance = controller.getAttendanceMonthlyByStudent(studentId, classId, month, year);
%>

<div class="entry">

	<table width="1100px" border="1" cellspacing="0" cellpadding="3">
		<tr>
			<td align="center"><b>Nama Pelajar</b></td>
			
			<%
			for(int index = 1; index < 32; index++){
					
					%>
				<td><b><a href="checkAttendance.jsp?studentId=<%=studentIdEnc%>&id=<%=classIdEnc%>&month=<%=month %>&day=<%=index%>&year=<%=year%>&actualMonth=<%=actualMonth%>" ><%=index %></a></b></td>
				<%	
				
				}
			%>
		</tr>
		<tr>
		<th>
			<%=attendance.getStudent().getName() %>		
		</th>
		<td align="center">
			<%
				out.print(attendance.getD01());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD02());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD03());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD04());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD05());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD06());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD07());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD08());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD09());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD10());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD11());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD12());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD13());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD14());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD15());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD16());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD17());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD18());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD19());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD20());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD21());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD22());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD23());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD24());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD25());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD26());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD27());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD28());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD29());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD30());
			%>
		</td>
		<td align="center">
			<%
				out.print(attendance.getD31());
			%>
		</td>
	</table>

</div> 