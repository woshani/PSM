<%@page import="java.util.List"%>
<%@page import="controller.WarningHistoryController"%>
<%@page import="bean.WarningHistory"%>
<%@page import="facade.ControllerWrapper"%>
<%@page import="bean.Monitor"%>
<%@page import="controller.URLSecurity"%>
<%
	String studentIdEnc = request.getParameter("id");
	String whIdEnc = request.getParameter("wh");
	String pinKey = String.valueOf(session.getAttribute("pinKey"));
	String studentId = URLSecurity.decryptFinal(studentIdEnc, pinKey);
	String whId = URLSecurity.decryptFinal(whIdEnc, pinKey);
	
	ControllerWrapper controller = new ControllerWrapper();
	Monitor monitor = new Monitor();
	monitor = controller.studentWarning(studentId, whId);
	
	List<WarningHistory> warningHistories = controller.getAllWarningHistory(studentId);
	
%>
<div class="entry">
	<div id="accordion" style="width: 1100px;">
		<h3>REKOD AMARAN KETIDAKHADIRAN</h3>
		<div>
			<table width="1000px" border="1" cellspacing="0" cellpadding="3">
				<tr>
					<th width="3%" align="center">NO.</th>
					<th width="20%" align="center">AMARAN</th>
					<th width="35%" align="center">JENIS AMARAN</th>
					<th width="25%" align="center">TARIKH AMARAN DIREKOD</th>
					<th width="17%" align="center">CETAK PDF</th>
				</tr>
				<%
				int wHIndex = 0;
				
				for (WarningHistory warningHistory : warningHistories) {
					wHIndex++;
				%>
				
				<tr>
					<td align="center"><%=wHIndex%>. </td>
					<td align="center"><%=warningHistory.getWarning().getDescription() %></td>
					<td align="center"><%=warningHistory.getWarning().getWarningType() %></td>
					<td align="center"><%=warningHistory.getWarningDate() %></td>
					<td align="center">
						<a href="${pageContext.request.contextPath}/PDFWarningLetter?id=<%=studentId%>&warning=<%=warningHistory.getWarning().getDescription()%>&wh=<%=warningHistory.getWhId()%>">
								<font color="red"><%=warningHistory.getWarning().getDescription() %></font> </a>
					</td>
				</tr>	
				<%
				} 
				%>			
			</table>
		</div>
		<h3>PROFIL MURID</h3>
		<div>
			<p align=center>
				NAMA MURID : <%=monitor.getStudent().getName().trim()%>
				</br> KELAS : <%=monitor.getStudent().getClassroom().getName().trim()%>
				</br> JENIS KELAS : <%=monitor.getStudent().getClassroom().getType().trim() %> </br>
			</p>
		</div>
	</div>
</div>