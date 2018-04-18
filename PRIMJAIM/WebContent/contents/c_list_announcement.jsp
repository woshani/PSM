<%@page import="controller.URLSecurity"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.User"%>
<%@page import="bean.Instituition"%>
<%@page import="bean.Classroom"%>
<%@page import="bean.Student"%>
<%@page import="bean.Announcement"%>
<%@page import="facade.ControllerWrapper"%>
<%@page import="bean.Teacher"%>
<script type="text/javascript">
	$(function(){
	   $("#sendAnnouncementBtn").click(function(e){
		   var form = $('#SendAnnouncementsForm').validationEngine('validate') ;
		   if (form == true) {
		      	submitValue = $("#sendAnnouncementBtn").data('submit-value');
		      	$("#sendAnnouncementBtn").attr("disabled", true);
		      	$("#sendAnnouncementBtn").val((submitValue) ? submitValue : 'Sila Tunggu Sebentar..');				
		      	$('#SendAnnouncementsForm').submit();
		   }
		   
	   });
	});
</script>
<%
	String pinKey = (String) session.getAttribute("pinKey");	

	Teacher teacher = new Teacher();

	teacher = (Teacher) session.getAttribute("SASteacher");
	// create a controller object
	ControllerWrapper controller = new ControllerWrapper();
	// create a annoucement list for keeping a collection of announcement
	//--List<Announcement> announcements = new ArrayList<Announcement>();
	List<Announcement> sentAnnouncements = new ArrayList<Announcement>();
	List<Announcement> delayAnnouncements = new ArrayList<Announcement>();
	// calling get all announcements method via controller object
	// to get all announcements from database
	//--announcements = controller.getAllAnnouncements();

	sentAnnouncements = controller.getAllSentAnnouncementsById(teacher.getTeacher_id());
	delayAnnouncements = controller.getAllDelayAnnouncementsById(teacher.getTeacher_id());

	String instituitionId = controller.getInstituitionByTeacherId(teacher.getTeacher_id());
	String instituitionIdEnc = URLSecurity.encryptFinal(instituitionId, pinKey);
%>
<div id="accordion" style="width: 1100px;">
	<h3>PENGUMUMAN BELUM DIHANTAR</h3>
	<div>
		<%
			if (delayAnnouncements.size() < 1) {
		%>
		<p>Tiada PENGUMUMAN yang BELUM dihantar.</p>
		<%
			} else {
		%>
		<form id="SendAnnouncementsForm" name="SendAnnouncementsForm" method="post" class="form-disable"
			action="SendAnnouncements">
			<table width="1000px" border="0" cellpadding="10">
				<tr>
					<th width="50">&nbsp;</th>
					<th width="50">No.</th>
					<th width="250">Perkara</th>
					<th>Sasaran</th>
					<th width="200">Tarikh & Masa</th>
					<th width="150">Nama Pengumum</th>
				</tr>
				<%
					// display all books from book vector in table rows
						for (int index = 0; index < delayAnnouncements.size(); index++) {
							// set current date time
							DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy h:m:s a");

							// getting object information object list
							String announcement_id = delayAnnouncements.get(index).getAnnouncement_id();
							String subject = delayAnnouncements.get(index).getSubject();
							String type = delayAnnouncements.get(index).getType();
							Student student = new Student();
							Classroom classroom = new Classroom();
							Instituition instituition = new Instituition();
							if (type.equals("S")) {
								student = (Student) delayAnnouncements.get(index).getTarget();
							} else if (type.equals("C")) {
								classroom = (Classroom) delayAnnouncements.get(index).getTarget();
							} else if (type.equals("I")) {
								instituition = (Instituition) delayAnnouncements.get(index).getTarget();
							}
							Timestamp datetime = delayAnnouncements.get(index).getDatetime();
							User announcer = delayAnnouncements.get(index).getAnnouncer();
							String annoucerName = delayAnnouncements.get(index).getName();
				%>
				<tr>
					<td align="center"><input type="checkbox" name="ann_id"
						id="chk_<%=announcement_id%>" value="<%=announcement_id%>"
						class="validate[minCheckbox[1]]" /></td>
					<td align="center"><%=(index + 1)%></td>
					<td><%=subject%></td>
					<%
						if (type.equals("S")) {
							String studentIdEnc = URLSecurity.encryptFinal(student.getStudent_id(), pinKey);
					%>
					<td align="center"><a
						href="studentProfile.jsp?id=<%=studentIdEnc%>"> <%=student.getName()%></a></td>
					<%
						} else if (type.equals("C")) {
							String classIdEnc = URLSecurity.encryptFinal(classroom.getClass_id(), pinKey);
					%>
					<td align="center"><a
						href="classProfile.jsp?id=<%=classIdEnc%>"> <%=classroom.getName()%></a></td>
					<%
						} else if (type.equals("I")) {
							String institutionIdEnc = URLSecurity.encryptFinal(instituition.getAcademic_instituition_id(), pinKey);
					%>
					<td align="center"><a
						href="instituteProfile.jsp?id=<%=institutionIdEnc%>">
							<%=instituition.getAcademic_name()%></a></td>
					<%
						} else if (type.equals("P")) {
												%>
					<td align="center">POLIS</td>
					<% 
						} else if (type.equals("D")) {
												%>
					<td align="center">ADUN</td>
					<% 
						} else {
					%>
					<td align="center">Semua</td>
					<%
						}
					String deleteAnnIdEnc = URLSecurity.encryptFinal(delayAnnouncements.get(index).getAnnouncement_id(), pinKey);
					%>
					<td align="center"><%=dateFormat.format(datetime)%></td>
					
				    <td align="center"><%=annoucerName%></td>
					
					<td align="center"><a href="${pageContext.request.contextPath}/DeleteAnnouncement?id=<%=deleteAnnIdEnc%>">DELETE</a></td>
				</tr>
				<%
					}
				%>
				<tr>
					<td colspan="6">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="6" align="right">
					<input type="submit"
						id ="sendAnnouncementBtn"
						name="sendAnnouncementBtn" value="Hantar Pengumuman"
						class="blackbutton" data-submit-value="Pengumuman Sedang Dihantar.."/>
						</td>
				</tr>
			</table>
			
			<input type="hidden" name="instituitionId" id="instituitionId"
				value=<%=instituitionIdEnc%>>
		</form>
		<%
			}
		%>

	</div>
	<h3>PENGUMUMAN TELAH DIHANTAR</h3>
	<div>
		<%
			if (sentAnnouncements.size() < 1) {
		%>
		<p>Tiada PENGUMUMAN yang TELAH dihantar.</p>
		<%
			} else {
		%>
		<table width="1000px" border="0" cellpadding="10">
			<tr>
				<th width="50">No.</th>
				<th width="250">Perkara</th>
				<th>Sasaran</th>
				<th width="200">Tarikh & Masa</th>
				<th width="150">Pengumum</th>
			</tr>
			<%
				// display all books from book vector in table rows
				for (int index = 0; index < sentAnnouncements.size(); index++) {
					// set current date time
					DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy h:m:s a");

					// getting object information object list
					String announcement_id = sentAnnouncements.get(index).getAnnouncement_id();
					String subject = sentAnnouncements.get(index).getSubject();
					String type = sentAnnouncements.get(index).getType();
					Student student = new Student();
					Classroom classroom = new Classroom();
					Instituition instituition = new Instituition();
					if (type.equals("S")) {
						student = (Student) sentAnnouncements.get(index).getTarget();
					} else if (type.equals("C")) {
						classroom = (Classroom) sentAnnouncements.get(index).getTarget();
					} else if (type.equals("I")) {
						instituition = (Instituition) sentAnnouncements.get(index).getTarget();
					}
					Timestamp datetime = sentAnnouncements.get(index).getDatetime();
					User announcer = sentAnnouncements.get(index).getAnnouncer();
					String annoucerName = sentAnnouncements.get(index).getName();
					
					
			%>
			<tr>
				<td align="center"><%=(index + 1)%></td>
				<td><%=subject%></td>
				<%
					if (type.equals("S")) {
						String studentIdEnc = URLSecurity.encryptFinal(student.getStudent_id(), pinKey);
				%>
				<td align="center"><a
					href="studentProfile.jsp?id=<%=studentIdEnc%>"> <%=student.getName()%></a></td>
				<%
					} else if (type.equals("C")) {
						String classIdEnc = URLSecurity.encryptFinal(classroom.getClass_id(), pinKey);
				%>
				<td align="center"><a
					href="classProfile.jsp?id=<%=classIdEnc%>"> <%=classroom.getName()%></a></td>
				<%
					} else if (type.equals("I")) {
						String institutionIdEnc = URLSecurity.encryptFinal(instituition.getAcademic_instituition_id(), pinKey);
				%>
				<td align="center"><a
					href="instituteProfile.jsp?id=<%=institutionIdEnc%>">
						<%=instituition.getAcademic_name()%></a></td>
				<%
					} else if (type.equals("P")) {
						%>
				<td align="center">POLIS</td>
				<% 
					} else if (type.equals("D")) {
											%>
				<td align="center">ADUN</td>
				<% 
					} else {
				%>
				<td align="center">Semua</td>
				<%
					}
				%>
				<td align="center"><%=dateFormat.format(datetime)%></td>
				<td align="center"><%=annoucerName%></td>
			</tr>
			<%
				}
			%>

		</table>
			<%
			}
		%>
	</div>

</div>