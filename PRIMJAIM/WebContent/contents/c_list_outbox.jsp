<%@page import="controller.URLSecurity"%>
<%@page import="bean.User"%>
<%@page import="bean.Announcement"%>
<%@page import="bean.Instituition"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.Student"%>
<%@page import="bean.Teacher"%>
<%@page import="bean.Classroom"%>
<%@page import="bean.AlertPackage"%>
<%@page import="bean.OutgoingSMS"%>
<%@page import="facade.ControllerWrapper"%>
<%
	String pinKey = (String) session.getAttribute("pinKey");

	Teacher teacher = new Teacher();

	teacher = (Teacher) session.getAttribute("SASteacher");

	// create a controller object
	ControllerWrapper controller = new ControllerWrapper();

	String instituitionId = controller.getInstituitionByTeacherId(teacher.getTeacher_id());
	
	String instituitionIdEnc = URLSecurity.encryptFinal(instituitionId, pinKey);

	// create a outgoing sms list for keeping a collection of outgoing sms
	//List<OutgoingSMS> osms = new ArrayList<OutgoingSMS>();

	// ADDED ON 08/05/2015 BY MOHAMAD IDZHAR BIN YA`AKUB
	// CREATE NEW FUNCTION RETRIEVE SMS DATA SENT AND DELAY BY ACADEMIC INSTITUTION ID AND TEACHER ID
	// CODE START
	//List<Announcement> sentAnnouncements = new ArrayList<Announcement>();
	//List<Announcement> delayAnnouncements = new ArrayList<Announcement>();
	List<OutgoingSMS> sentSMS = new ArrayList<OutgoingSMS>();
	List<OutgoingSMS> delaySMS = new ArrayList<OutgoingSMS>();

	//sentAnnouncements = controller.getAllSentAnnouncementsById(teacher.getTeacher_id());
	//delayAnnouncements = controller.getAllDelayAnnouncementsById(teacher.getTeacher_id());
	sentSMS = controller.getAllSentSMSsByTeacherId(teacher.getTeacher_id());
	delaySMS = controller.getAllDelaySMSsByTeacherId(teacher.getTeacher_id());

	// CODE END

	// calling get all outgoing sms method via controller object
	// to get all outgoing sms from database
	//osms = controller.getAllOutgoingSMSsByTeacher(teacher.getTeacher_id());
%>



<div id="accordion" style="width: 1100px;">
	<h3>SMS BELUM DIHANTAR</h3>
	<div>
		<%
			if (delaySMS.size() < 1) {
		%>
		<p>Tiada laporan yang belum dihantar.</p>
		<%
			} else {
		%>
		<form id="SendOutgoingSMSForm" method="post" action="SendOutgoingSMS">
			<table width="1000px" border="0" cellpadding="10">
				<tr>
					<th width="50">&nbsp;</th>
					<th width="50">No.</th>
					<th width="250">Nama Pelajar</th>
					<th>Kelas</th>
					<th>Jenis Sms</th>
					<th>Tarikh Kejadian</th>
					<th width="200">Tarikh dan Masa Direkod</th>
				</tr>
				<%
					// display all books from book vector in table rows
						for (int index = 0; index < delaySMS.size(); index++) {
							// set current date time
							DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy h:m:s a");

							// getting object information object list
							String osms_id = delaySMS.get(index).getOsms_id();
							String osms_idEnc = URLSecurity.encryptFinal(osms_id, pinKey);
							
							Timestamp datetime = null;
							Student student = delaySMS.get(index).getStudent();
							Classroom classroom = controller.getClassroomByStudentId(student.getStudent_id());
							AlertPackage alertPackage = delaySMS.get(index).getAlertPackage();
							if (delaySMS.get(index).getDatetime() != null) {
								datetime = delaySMS.get(index).getDatetime();

							}
				%>
				<tr>
					<td align="center"><input type="checkbox" name="osms_id"
						id="chk_<%=osms_idEnc%>" value="<%=osms_idEnc%>"
						class="validate[minCheckbox[1]]" /></td>
					<td align="center"><%=(index + 1)%></td>
					<td><a
						href="studentProfile.jsp?id=<%=URLSecurity.encryptFinal(student.getStudent_id() , pinKey)%>"> <%=student.getName()%></a></td>
					<td align="center"><%=classroom.getName()%></td>
					<td align="center"><%=alertPackage.getName()%></td>

					<%
						if (delaySMS.get(index).getEventDate() != null) {
					%>
					<td align="center"><%=delaySMS.get(index).getEventDate()%></td>
					<%
						} else {
					%><td align="center">-</td>
					<%
						}
					%>

					<%
						if (delaySMS.get(index).getDatetime() != null) {
					%>
					<td align="center"><%=delaySMS.get(index).getEventDate()%></td>
					<%
						} else {
					%><td align="center">-</td>
					<%
						}
					String osmsIdEnc = URLSecurity.encryptFinal(delaySMS.get(index).getOsms_id(), pinKey);
					%>
					
					
					<td align="center"><a href="${pageContext.request.contextPath}/DeleteOutGoingSMS?id=<%=osmsIdEnc%>">DELETE</a></td>
				</tr>
				<%
					}
				%>
				<tr>
					<td colspan="5">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="5" align="right"><input type="submit"
						name="alertGuardian" value="Hantar Laporan" class="blackbutton" /></td>
				</tr>
			</table>
			<input type="hidden" name="instituitionId" id="instituitionId"
				value=<%=instituitionIdEnc%>>
		</form>
		<%
			}
		%>

	</div>
	<h3>SMS TELAH DIHANTAR</h3>
	<div>
		<%
			if (sentSMS.size() < 1) {
		%>
		<p>Tiada laporan yang belum dihantar.</p>
		<%
			} else {
		%>
		<table width="1000px" border="0" cellpadding="10">
			<tr>
				<th width="50">No.</th>
				<th width="250">Nama Pelajar</th>
				<th>Kelas</th>
				<th>Jenis Sms</th>
				<th>Tarikh Kejadian</th>
				<th width="200">Tarikh dan Masa Direkod</th>
			</tr>
			<%
				// display all books from book vector in table rows
					for (int index = 0; index < sentSMS.size(); index++) {
						// set current date time
						DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy h:m:s a");

						// getting object information object list
						String osms_id = sentSMS.get(index).getOsms_id();
						Timestamp datetime = null;
						Student student = sentSMS.get(index).getStudent();
						Classroom classroom = controller.getClassroomByStudentId(student.getStudent_id());
						AlertPackage alertPackage = sentSMS.get(index).getAlertPackage();
						if (sentSMS.get(index).getDatetime() != null) {
							datetime = sentSMS.get(index).getDatetime();

						}
			%>
			<tr>
				<td align="center"><%=(index + 1)%></td>
				<td><a
					href="studentProfile.jsp?id=<%=URLSecurity.encryptFinal(student.getStudent_id() , pinKey)%>"> <%=student.getName()%></a></td>
				<td align="center"><%=classroom.getName()%></td>
				<td align="center"><%=alertPackage.getName()%></td>

				<%
					if (sentSMS.get(index).getEventDate() != null) {
				%>
				<td align="center"><%=sentSMS.get(index).getEventDate()%></td>
				<%
					} else {
				%><td align="center">-</td>
				<%
					}
				%>

				<%
					if (sentSMS.get(index).getDatetime() != null) {
				%>
				<td align="center"><%=dateFormat.format(datetime)%></td>
				<%
					} else {
				%><td align="center">-</td>
				<%
					}
				%>

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