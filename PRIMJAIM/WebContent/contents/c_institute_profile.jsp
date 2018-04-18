<%@page import="controller.URLSecurity"%>
<div class="entry">
<%@page import="bean.Classroom"%>
<%@page import="bean.Instituition"%>
<%@page import="bean.Teacher"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="facade.ControllerWrapper"%>
<%
	String pinKey = (String) session.getAttribute("pinKey");
	String instituitionIdEnc = request.getParameter("id");
	String instituitionId = URLSecurity.decryptFinal(instituitionIdEnc, pinKey);

	ControllerWrapper controller = new ControllerWrapper();
	Instituition institution = new Instituition();
	
	institution = controller.getInstituitionById(instituitionId);
	
	if(institution.getAcademic_instituition_id() != null) {
		List<Classroom> classrooms = controller.getClassroomsByInstituitionId(
				institution.getAcademic_instituition_id());
		List<Teacher> teachers = new ArrayList<Teacher>();
		for(Classroom classroom : classrooms) {
			teachers.addAll(controller.getTeachersByClassroomId(classroom.getClass_id()));
		}
%>
<div id="accordion" style="width: 1100px;">
	<h3>MAKLUMAT INSTITUSI</h3>
	<div>
		<table width="1000px" border="0" cellpadding="5">
    	  <tr>
    	  	<td width="200px"><strong>NAMA</strong></td>
    	  	<td width="5px">:</td>
    	  	<td title="Kemakini Maklumat Institusi"><u><a
						href="updateInstitutionProfile.jsp?id=<%=URLSecurity.encryptFinal(institution.getAcademic_instituition_id(),
							pinKey)%>"><%=institution.getAcademic_name()%></a></u></td>
    	  </tr>
    	  <tr>
    	  	<td><strong>ALAMAT</strong></td>
    	  	<td width="5px">:</td>
    	  	<td><%=institution.getAddress() %></td>
    	  </tr>
    	  <tr>
    	  	<td><strong>POSKOD</strong></td>
    	  	<td width="5px">:</td>
    	  	<td><%=institution.getPostcode() %></td>
    	  </tr>
    	  <tr>
    	  	<td><strong>BANDAR</strong></td>
    	  	<td width="5px">:</td>
    	  	<td><%=institution.getCity() %></td>
    	  </tr>
    	  <tr>
    	  	<td><strong>NOMBOR TELEFON</strong></td>
    	  	<td width="5px">:</td>
    	  	<td><%=institution.getTelephone_number() %></td>
    	  </tr>
    	  <tr>
    	  	<td><strong>NOMBOR FAX</strong></td>
    	  	<td width="5px">:</td>
    	  	<td><%=institution.getFax_number() %></td>
    	  </tr>
    	</table>
	</div>
	<h3>SENARAI KELAS</h3>
	<div>
<%if(classrooms.size() > 0) { %>
		<ul>
<%
int classIndex = 0;
for (Classroom classroom : classrooms) {
	classIndex++;
%>
			<li><%=classIndex %>) 
				
				<%=classroom.getName() %></li>
<% } %>
		</ul>
<% } else { %>
		<p>Tiada kelas di bawah sekolah ini.</p>
<% } %>
	</div>
	<h3>SENARAI GURU</h3>
	<div>
<%if(teachers.size() > 0) { %>
		<ul>
<%
int teacherIndex = 0;
for (Teacher teacher : teachers) {
	teacherIndex++;
	String teacherIdEnc = URLSecurity.encryptFinal(teacher.getTeacher_id(), pinKey);
%>
			<li><%=teacherIndex %>) 
				<a href="teacherProfile.jsp?id=<%=teacherIdEnc %>">
				<%=teacher.getName() %></a></li>
<% } %>
		</ul>
<% } else { %>
		<p>Tiada guru di bawah sekolah ini.</p>
<% } %>
	</div>
</div>
<% } else { %>
	<p>Maklumat institusi TIADA dalam sistem maklumat.</p>
<% } %>
</div>