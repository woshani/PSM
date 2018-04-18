<%@page import="bean.Teacher"%>
<%@page import="bean.Classroom"%>
<%@page import="facade.ControllerWrapper"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%

	Teacher teacher = new Teacher();
List<Classroom> classrooms = new ArrayList<Classroom>();
	if (session.getAttribute("SASteacher") != null) {
		teacher = (Teacher) session.getAttribute("SASteacher");
		ControllerWrapper controller = new ControllerWrapper();
		
		String instituitionId = controller.getInstituitionByTeacherId(teacher.getTeacher_id());
		classrooms = controller.getClassroomsByInstituitionId(instituitionId);
	}
	
%>

<!-- <form action="AddTeacher" method="post"> -->
<form>
	<table>
		<tr>
			<td>Nama Guru</td><td>:</td><td><input type="text" id="teacherName" name="teacherName" placeholder="Nama Guru"></td>
		</tr>
		<tr>
			<td>No KP</td><td>:</td><td><input type="text" id="IC" name="IC" placeholder="No KP"></td>
		</tr>
		<tr>
			<td>Jantina</td><td>:</td><td><input type="radio" name="gender" id="gender" value="L" checked>Lelaki</td></tr>
			<tr><td></td><td></td><td><input type="radio" name="gender" id="gender" value="P">Perempuan</td>	
		</tr>
		<tr>
			<td>Status Perkahwinan</td><td>:</td><td><input type="radio" name="mariage" id="mariage" value="K" checked>Berkahwin</td></tr>
			<tr><td></td><td></td><td><input type="radio" name="mariage" id="mariage" value="B">Bujang</td>	
		</tr>
		<tr>
			<td>Alamat</td><td>:</td><td><input type="text" id="address" name="address" placeholder="Alamat"></td>
		</tr>
		<tr>
			<td>Telefon Ext</td><td>:</td><td><input type="text" id="telExt" name="telExt" placeholder="Telefon Ext" maxlength="4"></td>
		</tr>
		<tr>
			<td>Telefon Rumah</td><td>:</td><td><input type="text" id="telHome" name="telHome" placeholder="Telefon Rumah" maxlength="10"></td>
		</tr>
		<tr>
			<td>Telefon Hp</td><td>:</td><td><input type="text" id="telHP"  name="telHP" placeholder="Telefon Hp" maxlength="10"></td>
		</tr>
		<tr>
			<td>Jawatan</td><td>:</td><td><input type="radio" name="position" id="position" value="G" checked>Guru</td></tr>
									<tr><td></td><td></td>	<td><input type="radio" name="position" id="position" value="GB">Guru Besar</td></tr>
									<tr><td></td><td></td>	<td><input type="radio" name="position" id="position" value="GPK">Guru Penolong Kanan 1</td></tr>
									<tr><td></td><td></td>	<td><input type="radio" name="position" id="position" value="GPKH">Guru Penolong Kanan HEM</td></tr>
									<tr><td></td><td></td>	<td><input type="radio" name="position" id="position" value="GPKK">Guru Penolong Kanan Kokurikulum</td></tr>	
		<%
		if (session.getAttribute("SASteacher") != null) {
			%>
		<tr>
			<td>Kelas</td><td>:</td><td><select>
		<%
			for(Classroom classroom : classrooms){
		%>
		
		<option value="<%=classroom.getClass_id()%>"><%=classroom.getName() %></option>
		
		
		<%}%>
		</select></td></tr><%}%>
		<tr><td></td><td></td><td><input type="submit" value="Daftar"></td></tr>
	</table>
</form>