<%@page import="java.util.ArrayList"%>
<%@page import="bean.Classroom"%>
<%@page import="java.util.List"%>
<%@page import="facade.ControllerWrapper"%>
<%@page import="bean.Teacher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	Teacher teacher = new Teacher();

	teacher = (Teacher) session.getAttribute("SASteacher");

	ControllerWrapper controller = new ControllerWrapper();

	List<Classroom> classrooms = new ArrayList<Classroom>();

	classrooms = controller.getClassroomsByTeacherId(teacher
			.getTeacher_id());
%>

<table width="100%" border="0" cellpadding="5">
	<tr height="20">
		<td width="500" align="center"><font color="red" size="2">
				*</font> <strong>Maklumat PELAJAR BARU. Sila lengkapkan
				Borang Maklumat Pelajar dibawah.</strong></td>
	</tr>
	<tr>
		<td><hr></hr></td>
	</tr>
</table>
<br></br>
<table width="100%" border="0" cellpadding="5">

	<tr height="20">
		<td width="250" align="right"><strong>Gambar Pelajar</strong></td>
		<td width="5" align="center">:</td>
		<td width="300" height="300" align="left"><img
			id="student-img-tag" src="images/defaultLogo.jpg" alt=""
			width="150px" height="150px" /> 
			<br></br> (Gambar diatas akan
			mewakili pelajar secara automatik jika tiada input.)<br></br> (Gambar
			yang hendak dimuat naik mestilah bersaiz 30KB kebawah.)<br></br> 
			<input
			type="file" id="student-img" name="student-img" value=""
			onchange="loadStudentImage()" /></td>
	</tr>
	<tr height="20">
		<td></td>
		<td></td>
		<td><hr></hr></td>
	</tr>


	<tr height="20">
		<td width="250" align="right"><font color="red">*</font> <strong>Nama
				Pelajar</strong></td>
		<td width="5" align="center">:</td>
		<td width="300" align="left"><input type="text" required
			style="width: 550px;" id="studentName" name="studentName"
			placeholder="Seperti di dalam MyKid / MyKad"></td>
	</tr>
	<tr>
		<td align="right"><font color="red">*</font><strong>
				Nombor Surat Beranak </strong></td>
		<td align="center">:</td>
		<td align="left"><input type="text" required
			style="width: 300px;" id="birthCert" name="birthCert"
			placeholder="Nombor Surat Beranak"> (Sila masukkan tanda "-"
			jika tiada maklumat)</td>
	</tr>
	<tr>
		<td align="right"><font color="red">*</font><strong>
				Jantina</strong></td>
		<td align="center">:</td>
		<td align="left"><input type="radio" id="gender" name="gender"
			value="L" checked> Lelaki <br> <input type="radio"
			id="gender" name="gender" value="P"> Perempuan<br></td>
	</tr>
	<tr>
		<td align="right"><font color="red">* </font><strong>Bangsa</strong></td>
		<td align="center" width="5px">:</td>
		<td align="left"><select name="race" required>
				<option value="1">Melayu/Bumiputera</option>
				<option value="2">Cina</option>
				<option value="3">India</option>
				<option value="4">Lain-Lain</option>
		</select></td>
	</tr>
	<tr>
		<td align="right"><font color="red">* </font><strong>Agama</strong></td>
		<td align="center" width="5px">:</td>
		<td align="left"><select name="religion" required>
				<option value="1">Islam</option>
				<option value="2">Buddha</option>
				<option value="3">Hindu</option>
				<option value="4">Kristian</option>
				<option value="5">Taoisme</option>
				<option value="6">Lain-lain</option>
				<option value="7">Tiada</option>
		</select></td>
	</tr>
	<tr>
		<td align="right"><font color="red">* </font><strong>Tempat
				Lahir</strong></td>
		<td align="center" width="5px">:</td>
		<td align="left"><textarea id="placeOfBirth" name="placeOfBirth"
				maxlength="148" rows="5" cols="50" required></textarea><br /> (Sila
			masukkan tanda "-" jika tiada maklumat)</td>
	</tr>
	<tr>
		<td align="right"><font color="red">* </font><strong>Kelas</strong></td>
		<td align="center">:</td>
		<td align="left"><select name="class" id="class"
			style="width: 500px;">
				<%
					for (Classroom classroom : classrooms) {
				%>
				<option value="<%=classroom.getClass_id()%>"><%=classroom.getType()%>
					(<%=classroom.getName()%>)
				</option>
				<%
					}
				%>
		</select></td>
	</tr>
	<tr>
			<td align="left">
			<input type="hidden" id="statusPelajar" name="statusPelajar" value="0"></input>
			</td>
			
		
	</tr>
</table>