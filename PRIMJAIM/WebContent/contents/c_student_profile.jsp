<%@page import="java.security.InvalidKeyException"%>
<%@page import="javax.crypto.NoSuchPaddingException"%>
<%@page import="java.security.NoSuchAlgorithmException"%>
<%@page import="javax.crypto.SecretKey"%>
<%@page import="controller.URLSecurity"%>
<div class="entry">
	<%@page import="java.text.DateFormat"%>
	<%@page import="java.text.SimpleDateFormat"%>
	<%@page import="bean.Student"%>
	<%@page import="bean.Classroom"%>
	<%@page import="bean.Instituition"%>
	<%@page import="bean.Teacher"%>
	<%@page import="bean.Guardian"%>
	<%@page import="java.util.List"%>
	<%@page import="facade.ControllerWrapper"%>
	<%
		try {
		String pinKey = String.valueOf(session.getAttribute("pinKey"));
		String studentIdEnc = request.getParameter("id");
		String studentId = URLSecurity.decryptFinal(studentIdEnc, pinKey);
		
		ControllerWrapper controller = new ControllerWrapper();
		Student student = new Student();
		student = controller.getStudentById(studentId);
		if(student.getStudent_id() != null) {
			Classroom classroom = student.getClassroom();
			Instituition institution = classroom.getInstituition();
			List<Teacher> teachers = controller.getTeachersByClassroomId(classroom.getClass_id());
			List<Guardian> guardians = controller.getGuardiansByStudentId(student.getStudent_id());
			
			DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy h:m:s a");
			
			String institutionIdEnc = URLSecurity.encryptFinal(institution.getAcademic_instituition_id(), pinKey);
	%>
	<div id="accordion" style="width: 1100px;">
		<h3>MAKLUMAT PELAJAR</h3>
		<div>
			
			<table width="1000px" border="0" cellpadding="5">
				<%-- <tr>
    	  	<td><strong>ID PELAJAR</strong></td>
    	  	<td width="5px">:</td>
    	  	<td><%=student.getStudent_id() %></td>
    	  </tr> --%>
				<tr>
					<td width="200px"><strong>NAMA</strong></td>
					<td width="5px">:</td>
					<td title="Kemaskini Maklumat Pengguna"><a
						href="updateStudentProfile.jsp?id=<%=URLSecurity.encryptFinal(student.getStudent_id(),
							pinKey)%>"><b><%=student.getName()%></b></a></td>
				</tr>
				<tr>
					<td><strong>NOMBOR IC</strong></td>
					<td width="5px">:</td>
					<td><%=student.getIc_number()%></td>
				</tr>
				<tr>
					<td><strong>NO. SIJIL SURAT BERANAK</strong></td>
					<td width="5px">:</td>
					<td><%=student.getBirth_cert()%></td>
				</tr>
				<tr>
					<td><strong>JANTINA</strong></td>
					<td width="5px">:</td>
					<td><%=student.getGender()%></td>
				</tr>
				<tr>
					<td><strong>BANGSA</strong></td>
					<td width="5px">:</td>
					<td><%=student.getRace().getRace()%></td>
				</tr>
				<tr>
					<td><strong>AGAMA</strong></td>
					<td width="5px">:</td>
					<td><%=student.getReligion().getReligion()%></td>
				</tr>
				<tr>
					<td><strong>TARIKH LAHIR</strong></td>
					<td width="5px">:</td>
					<td><%=student.getDate_of_birth()%></td>
				</tr>
				<tr>
					<td><strong>TEMPAT LAHIR</strong></td>
					<td width="5px">:</td>
					<td><%=student.getPlace_of_birth()%></td>
				</tr>
			</table>
		</div>
		<h3>MAKLUMAT INSTITUSI</h3>
		<div>
			<table width="1000px" border="0" cellpadding="5">
				<tr>
					<td width="200px"><strong>NAMA</strong></td>
					<td width="5px">:</td>
					<td><a href="instituteProfile.jsp?id=<%=institutionIdEnc%>">
							<%=institution.getAcademic_name()%></a></td>
				</tr>
			</table>
		</div>
		<h3>MAKLUMAT KELAS</h3>
		<div>
			<table width="1000px" border="0" cellpadding="5">
				<tr>
					<td width="170px"><strong>NAMA</strong></td>
					<td width="5px">:</td>
					<td>
						<%
							String classIdEnc = URLSecurity.asciiToHex(URLSecurity
											.encryptionURL(classroom.getClass_id(), pinKey));
						%><a href="classProfile.jsp?id=<%=classIdEnc%>"> <%=classroom.getName()%></a>
					</td>
				</tr>
			</table>
		</div>
		<h3>MAKLUMAT PENJAGA</h3>
		<div>
			<table width="1000px" border="0" cellpadding="5">
				<th>No.</th>
				<th>Nama Penjaga</th>
				<th>Hubungan</th>
			<%
				if (guardians.size() > 0) {
			%>
			
				
				<%
					int guardianIndex = 0;
								for (Guardian guardian : guardians) {
									guardianIndex++;
									String guardianIdEnc = URLSecurity.encryptFinal(
											guardian.getGuardian_id(), pinKey);
				%>
				<tr>
					<td align="center"><%=guardianIndex%></td>
					<td align="center"><a href="guardianProfile.jsp?id=<%=guardianIdEnc%>"> <b><%=guardian.getName()%></b></a></td>
					<td align="center"><%=guardian.getRelationship() %></td>
				</tr>
				<%
					}
				%>
			
			<%
				} else {
			%>
			<p>Tiada penjaga untuk palajar ini.</p>
			<%
				}
			%>
			</table>
		</div>
	</div>
	<%
		} else {
	%>
	<p>Maklumat pelajar TIADA dalam sistem maklumat.</p>
	<%
		}
	%>
</div>
<p>&nbsp;</p>
<hr />
<p>&nbsp;</p>
<p>
	<a href="registerParent.jsp?id=<%=studentIdEnc%>"
		class="blackbutton">Daftar Ibu Bapa</a>
</p>

<%
	} catch (NoSuchAlgorithmException e) {
		System.out.println("No Such Algorithm:" + e.getMessage());
		return;
	} catch (NoSuchPaddingException e) {
		System.out.println("No Such Padding:" + e.getMessage());
		return;
	} catch (InvalidKeyException e) {
		System.out.println("Invalid Key:" + e.getMessage());
		return;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
%>
