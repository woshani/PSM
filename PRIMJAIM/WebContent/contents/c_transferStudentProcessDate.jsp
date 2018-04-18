<%@page import="controller.URLSecurity"%>
<%@page import="bean.Student"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="facade.ControllerWrapper"%>

<%

	String pinKey = String.valueOf(session.getAttribute("pinKey"));

	String[] studentIdEnc = request.getParameterValues("studentId");
	String classIdEnc = request.getParameter("classId");
	
	String classId = URLSecurity.decryptFinal(classIdEnc, pinKey);
	
	ControllerWrapper controller = new ControllerWrapper();
	Student student = null;

%>
<div class="entry">
<form id="TransferStudent" method="post" action="TransferStudentProcess">
<table width="1000px" border="1" cellspacing="0" cellpadding="3">
				<tr>
					<th align="center" >Bil</th>
					<th align="center">Nama Pelajar</th>
					<th align="center">No. Kad Pengenalan</th>
					<th align="center">Tarikh Daftar Masuk Sekolah/Kelas Baru</th>
				</tr>
				<%
					int stuIndex = 0;
					
					
					for(int index=0; index<studentIdEnc.length; index++) {
						
						String studentId = URLSecurity.decryptFinal(studentIdEnc[index], pinKey);
						
						stuIndex++;	
						student = controller.getStudentById(studentId);
						String datePickerInputId = "transferDate";
						datePickerInputId = datePickerInputId+""+index;
						
						
				%>
				<tr>
				
					<td align="center"><%=stuIndex%>.</td>
					<td align="left"><%=student.getName() %></td>
					<td align="center"><%=student.getIc_number() %></td>
					<td align="center"><input type="text" name="transferDate" id=<%=datePickerInputId%> class="date2" required/></td>
		
				</tr>
				<input type="hidden" name="studentId" id="studentId" value=<%=studentIdEnc[index] %>>
				<%} %>
</table>
<input type="hidden" name="classId" id="classId" value=<%=classIdEnc%>>
<br/>
<center><input type="submit" 
				name="TransferSchool" value="SAHKAN"
				class="blackbutton" /></center>
</form>
</div>