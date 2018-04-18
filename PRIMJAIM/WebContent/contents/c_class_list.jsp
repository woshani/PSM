<%@page import="java.security.InvalidKeyException"%>
<%@page import="javax.crypto.NoSuchPaddingException"%>
<%@page import="java.security.NoSuchAlgorithmException"%>
<%@page import="controller.URLSecurity"%>
<%@page import="javax.crypto.spec.SecretKeySpec"%>
<%@page import="com.sun.mail.util.BASE64DecoderStream"%>
<%@page import="javax.crypto.SecretKey"%>
<%@page import="bean.Teacher"%>
<%@page import="bean.Classroom"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="facade.ControllerWrapper"%>

<%

	Teacher teacher = new Teacher();
	ControllerWrapper controller = new ControllerWrapper();
	List<Classroom> classrooms = new ArrayList<Classroom>();
 
	teacher = (Teacher) session.getAttribute("SASteacher");
	
	if(teacher.getTeacher_id() != null) {
		classrooms = controller.getClassroomsByTeacherId(teacher.getTeacher_id());
	}
		
	String pinKey = String.valueOf(session.getAttribute("pinKey"));
	String classIdEnc = new String();
	
	


%>

<div class="entry">
<div id="accordion" style="width: 1100px;">
<h3>KELAS YANG DIPERTANGGUNGJAWABKAN</h3>
<div>
<%if(classrooms.size() > 0) { %>
		<ul>
<%
int classIndex = 0;
for (Classroom classroom : classrooms) {
	classIndex++;
%>
			<li><%=classIndex %>) 
				<%
				// URL Security Encapsulation process for Class ID
				try{
					classIdEnc = URLSecurity.asciiToHex(URLSecurity.encryptionURL(classroom.getClass_id(), pinKey));
					
					
					//out.print("Encrypted: " + classIdEnc);
					
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
				<a href="classProfile.jsp?id=<%=classIdEnc %>">
				<%=classroom.getName() %> (<%=classroom.getType() %>)</a></li>
<% } %>
		</ul>
<% } else { %>
		<p>Tiada kelas yang dipertanggungjawabkan.</p>
<% } %>
	</div>
</div>
</div>

<%
	String alert = request.getParameter("alert");
	if (alert != null){
		%>
		<script>alertify.alert("Kedatangan telah Berjaya dikemaskini.");</script>
		<%
	}

%>