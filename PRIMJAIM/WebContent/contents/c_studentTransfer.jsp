<%@page import="controller.URLSecurity"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="bean.TransferStudent"%>
<%@ page import="facade.ControllerWrapper"%>

<%@ page import="java.util.List"%>


<div class="entry">
	<link rel="stylesheet" type="text/css"
		href="../styles/jquery.dataTables.css">
	<script type="text/javascript" language="javascript"
		src="../scripts/jquery.js"></script>
	<script type="text/javascript" language="javascript"
		src="../scripts/jquery.dataTables.js"></script>
	<script type="text/javascript" language="javascript" class="init">
		$(document).ready(function() {
			$('#example').DataTable();
		});
	</script>
	<%
		ControllerWrapper controller = new ControllerWrapper();
		List<TransferStudent> transferStudents = controller.allTransferStudent();
		
		String pinKey = String.valueOf(session.getAttribute("pinKey"));
		
		String classIdEnc = request.getParameter("classId");
		String classId = URLSecurity.decryptFinal(classIdEnc, pinKey);
		
		if (transferStudents.size()>0){
			
		
		
	%>
	
	<form id="TransferStudentProcess" method="post"
		action="TransferStudentProcessDate.jsp">
		<table id="example" border="1" class="display" cellspacing="0"
			width="1000">

			<thead>
				<tr>
					<th rowspan="1">Bil. </th>
					<th rowspan="1">Nama Pelajar</th>
					<th rowspan="1">No. Kad Pengenalan</th>
					<th rowspan="1">Tarikh Pindah/Keluar Sekolah</th>
					<th rowspan="1">Daftar</th>
				</tr>
			</thead>
			<tbody>

				<%	
					int index = 1;
						for (TransferStudent transferStudent : transferStudents) {
							
							String studentIdEnc = URLSecurity.encryptFinal(transferStudent.getStudent().getStudent_id(), pinKey);
				%>
					
				<tr>
					<td align="center"><%=index%>.</td>
					<td><%=transferStudent.getStudent().getName()%></td>
					<td align="center" ><%=transferStudent.getStudent().getIc_number()%></td>
					<td align="center"><%=transferStudent.getTransferDate()%></td>
					<td align="center"><input type="checkbox" name="studentId"
						value="<%=studentIdEnc%>"></td>

				</tr>
				<%
					index = index + 1;
						}
				%>
			</tbody>
		</table>
		<input type="hidden" name="classId" id="classId" value="<%=classIdEnc%>">
		<center>
		<br />
			<input type="submit" name="TransferSchool" value="DAFTAR MASUK SEKOLAH"
				class="blackbutton" />
		</center>
	</form>
	<%
		}else{
			%><p>Tiada Rekod Pelajar berpindah ditemui!</p><% 
		}
	%>

</div>
