<%@page import="bean.Classroom" %>
<%@page import="bean.Teacher" %>
<%@page import="bean.SchoolAttendance" %>
<%@page import="facade.ControllerWrapper" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.text.SimpleDateFormat"%>

<%

	String month = request.getParameter("month");

	SimpleDateFormat fromUser = new SimpleDateFormat("MMMMM yyyy");
	SimpleDateFormat myFormat = new SimpleDateFormat("MMyy");
	
	String formatedMonth = myFormat.format(fromUser.parse(month));
	String newMonth = formatedMonth.substring(0, 2);
	String newYear = formatedMonth.substring(2, 4);

	Teacher teacher = (Teacher) session.getAttribute("SASteacher");

	ControllerWrapper controller = new ControllerWrapper();
	
	List<Classroom> classrooms = controller.getClassBySchool(teacher.getTeacher_id());
	
	List<SchoolAttendance> schoolAttendances = new ArrayList<SchoolAttendance>();

	int index = 1;
	int bil = 1;
	int check = 0;

%>

<div class="entry">
<br>
<br>
<a href="${pageContext.request.contextPath}/PDFHem?id=<%=teacher.getTeacher_id() %>&month=<%=month %>" 
	class="blackbutton">PDF</a>
<br>
<br>
<br>
<table width="1100px" border="1" cellspacing="0" cellpadding="3">

	<tr>
		<th align="center">BIL</th>
		<th align="center">KELAS</th>
		<th align="center">NAMA GURU</th>
		<th align="center">STATUS</th>
		<th align="center">JANTINA(L/P)</th>
		
		<%
			do{
				if (index < 10){
				%>
					<th align="center">0<%=index%></th>		
					<%index = index + 1;
				}else{				
				%>
				<th align="center"><%=index%></th>		
					<%index = index + 1;
				}
			}
			while(index < 32);		
		%>
	</tr>
	
	<%for(Classroom classroom : classrooms) {
	
		teacher = controller.getTeacherByClassroomId(classroom.getClass_id());
		
		%>
		
		<tr>
			
			<td align="center"><%=bil %>.</td>
			<td align="center"><%=classroom.getName()%> <br/>(<%=classroom.getType()%>)</td>
			<td align="left"><%=teacher.getName()%></td>
		
		<%
	
		schoolAttendances = controller.jumlahKedatanganBulananSekolah(classroom.getClass_id(), formatedMonth.substring(0, 2), formatedMonth.substring(2, 4));
		check = 0;
		
		if(schoolAttendances == null){
			
			do{
			
				if(check > 0){
				%>
					
					<tr>
				
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					
				<%
				}
				%>
				
					<%index = 0;
					do{
				%>
					<td>&nbsp;</td>
				<%index = index + 1;
					}
					while(index < 33);
				%>
					</tr>
				<%
				
				check++;
			}while(check < 4);
		}
		
		for(SchoolAttendance schoolAttendance : schoolAttendances){
			
			if(check > 0){
	%>
	
		<tr>
			
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			
	<%}if(check == 4) {%>	
			
			<td>&nbsp;</td>
			<td align="center">JUMLAH</td>
			
			<%}else{ %>
			
			<%			
				if(schoolAttendance.getStatus().equalsIgnoreCase("/")){
					
					%>
					<td align="center">HADIR</td>
					<%
					
				}else if ((schoolAttendance.getStatus().equalsIgnoreCase("-"))){
					
					%>
					<td align="center">BELUM DIREKOD</td>
					<%}
				else{
					
					%>
					<td align="center">TIDAK HADIR</td>
					<%}
			
			%>
			
			
			
			
			<td align="center"><%=schoolAttendance.getGender()%></td>
			
			<%} %>
			
			<td align="center"><%=schoolAttendance.getD01()%></td>
			<td align="center"><%=schoolAttendance.getD02()%></td>
			<td align="center"><%=schoolAttendance.getD03()%></td>
			<td align="center"><%=schoolAttendance.getD04()%></td>
			<td align="center"><%=schoolAttendance.getD05()%></td>
			<td align="center"><%=schoolAttendance.getD06()%></td>
			<td align="center"><%=schoolAttendance.getD07()%></td>
			<td align="center"><%=schoolAttendance.getD08()%></td>
			<td align="center"><%=schoolAttendance.getD09()%></td>
			<td align="center"><%=schoolAttendance.getD10()%></td>
			<td align="center"><%=schoolAttendance.getD11()%></td>
			<td align="center"><%=schoolAttendance.getD12()%></td>
			<td align="center"><%=schoolAttendance.getD13()%></td>
			<td align="center"><%=schoolAttendance.getD14()%></td>
			<td align="center"><%=schoolAttendance.getD15()%></td>
			<td align="center"><%=schoolAttendance.getD16()%></td>
			<td align="center"><%=schoolAttendance.getD17()%></td>
			<td align="center"><%=schoolAttendance.getD18()%></td>
			<td align="center"><%=schoolAttendance.getD19()%></td>
			<td align="center"><%=schoolAttendance.getD20()%></td>
			<td align="center"><%=schoolAttendance.getD21()%></td>
			<td align="center"><%=schoolAttendance.getD22()%></td>
			<td align="center"><%=schoolAttendance.getD23()%></td>
			<td align="center"><%=schoolAttendance.getD24()%></td>
			<td align="center"><%=schoolAttendance.getD25()%></td>
			<td align="center"><%=schoolAttendance.getD26()%></td>
			<td align="center"><%=schoolAttendance.getD27()%></td>
			<td align="center"><%=schoolAttendance.getD28()%></td>
			<td align="center"><%=schoolAttendance.getD29()%></td>
			<td align="center"><%=schoolAttendance.getD30()%></td>
			<td align="center"><%=schoolAttendance.getD31()%></td>
			
			</tr>
	<%		check = check + 1;
		} 
		%>
		<tr>
		<%index = 0;
			do{
		%>
		<td>&nbsp;</td>
		<%index = index + 1;
			}
			while(index < 36);
		%>
	</tr>
		<%
		bil = bil + 1;
	} %>
	
</table>
</div>