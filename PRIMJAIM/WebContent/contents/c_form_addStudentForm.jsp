<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="bean.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.Classroom"%>
<%@page import="java.util.List"%>
<%@page import="facade.ControllerWrapper"%>
<%@page import="bean.Teacher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	//get class id
	
	Teacher teacher = new Teacher();

	teacher = (Teacher) session.getAttribute("SASteacher");

	ControllerWrapper controller = new ControllerWrapper();

	List<Classroom> classrooms = new ArrayList<Classroom>();

	classrooms = controller.getClassroomsByTeacherId(teacher
	.getTeacher_id());


	//get student detail
	
	Student student = (Student)request.getAttribute("student");
%>

<table width="100%" border="0" cellpadding="5">
	<tr height="20">
		<td width="500" align="center"><font color="red" size="2">
				*</font> <strong>Maklumat PELAJAR telah direkod. Sila KEMASKINI
				Borang Maklumat Pelajar dibawah JIKA TERDAPAT PERUBAHAN.</strong></td>
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
		<td width="300" height="300" align="left">
		
		<!-- <img
			id="student-img-tag" src="images/defaultLogo.jpg" alt=""
			width="150px" height="150px" /> <br></br> (Gambar diatas akan
			mewakili pelajar secara automatik jika tiada input.)<br></br> (Gambar
			yang hendak dimuat naik mestilah bersaiz 30KB kebawah.)<br></br> <input
			type="file" name="file" id="student-img"
			value="images/defaultLogo.jpg" onchange="loadStudentImage()" /></td>-->
			
		<img
			id="student-img-tag" src="${pageContext.request.contextPath}/ImagePelajar?studentID=<%=student.getStudent_id()%>" alt=""
			width="150px" height="150px" />
			
			<br></br> 
			
			<input type="file" name="student-img" id="student-img"
			value="" onchange="loadStudentImage()" />
			
			<br></br> 
			(Gambar diatas akan	mewakili gambar pelajar secara automatik jika tiada input)
			<br></br> (Gambar yang hendak dimuat naik mestilah bersaiz 30KB kebawah)
			<br></br> </td>
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
			value="<%=student.getName()%>"
			placeholder="Seperti di dalam MyKid / MyKad"></td>
	</tr>
	<tr>
		<td align="right"><font color="red">*</font><strong>
				Nombor Surat Beranak </strong></td>
		<td align="center">:</td>
		<td align="left"><input type="text" required
			style="width: 300px;" id="birthCert" name="birthCert"
			placeholder="Nombor Surat Beranak"
			value="<%=student.getBirth_cert()%>"> (Sila masukkan tanda
			"-" jika tiada maklumat)</td>
	</tr>


	<tr>
		<td align="right"><font color="red">*</font><strong>
				Jantina</strong></td>
		<td align="center">:</td>
		<%
			if (student.getGender().equalsIgnoreCase("LELAKI")) {
		%>
		<td align="left"><input type="radio" id="gender" name="gender"
			value="L" checked> Lelaki<br> <input type="radio"
			id="gender" name="gender" value="P"> Perempuan<br></td>
		<%
			} else if (student.getGender().equalsIgnoreCase("PEREMPUAN")) {
		%>
		<td align="left"><input type="radio" id="gender" name="gender"
			value="L"> Lelaki<br> <input type="radio" id="gender"
			name="gender" value="P" checked> Perempuan<br></td>
		<%
			}
		%>
		
	</tr>
	<tr>
		<td align="right"><font color="red">* </font><strong>Bangsa</strong></td>
		<td align="center" width="5px">:</td>
		<td align="left"><select name="race" id="race" required>
				<option selected="selected" value="<%=student.getRace().getId()%>"><%=student.getRace().getRace()%></option>
				<option value="1">Melayu/Bumiputera</option>
				<option value="2">Cina</option>
				<option value="3">India</option>
				<option value="4">Lain-Lain</option>
		</select></td>
	</tr>
	<tr>
		<td align="right"><font color="red">* </font><strong>Agama</strong></td>
		<td align="center" width="5px">:</td>
		<td align="left">
		<select name="religion" id="religion" required>
				<option selected="selected" value="<%=student.getReligion().getId()%>"><%=student.getReligion().getReligion()%></option>
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
				maxlength="148" rows="5" cols="50" value"<%=student.getPlace_of_birth()%>" required><%=student.getPlace_of_birth()%></textarea><br /> (Sila
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
				<option value="<%=classroom.getClass_id()%>" selected="selected"><%=classroom.getType()%>
					(<%=classroom.getName()%>)
				</option>
				<%
					}
				%>
		</select></td>
	</tr>
	<tr>
		
		<td align="left"><input type="hidden" id="studentId" name="studentId"
			value="<%=student.getStudent_id()%>"></input></td>
		<td align="left">
			<input type="hidden" id="statusPelajar"
					name="statusPelajar" value="1"></input></td>

	</tr>
</table>
<%
//for testing purposes
/*
String race = request.getParameter("race");
String religion = request.getParameter("religion");
out.println("Race: " + race );
out.println("Religion: " + religion );
out.println("Race: " + student.getRace().getId() );
out.println("Religion: " + student.getReligion().getId() );*/

%>