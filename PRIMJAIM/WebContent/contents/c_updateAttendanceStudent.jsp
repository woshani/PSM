<%@page import="controller.URLSecurity"%>
<%@page import="bean.DailyAttendance"%>
<%@page import="bean.Student"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="facade.ControllerWrapper"%>

<%
	String pinKey = String.valueOf(session.getAttribute("pinKey"));
	
	String day = request.getParameter("day");
	String month = request.getParameter("month");
	String studentId = URLSecurity.decryptFinal(request.getParameter("studentId"), pinKey);
	String classId = request.getParameter("id");
	String year = request.getParameter("year");
	
	ControllerWrapper controller = new ControllerWrapper();
	DailyAttendance attendance = new DailyAttendance();
	
	attendance = controller.getAttendanceMonthlyByDay(studentId, day, month, year);

%>

<div class="entry">
<form id="UpdateAttendanceForm" method="post" action="UpdateAttendance">

<input type="hidden" name="pinKey" id="pinKey" value="<%=pinKey%>">
<input type="hidden" name="day" id="day" value="<%=day%>">
<input type="hidden" name="month" id="month" value="<%=month%>">
<input type="hidden" name="year" id="year" value="<%=year%>">
<input type="hidden" name="classId" id="classId" value="<%=classId%>">

<p><b>Tarikh : <%=day + "-" + month + "-20" + year%></b></p>

<table>
	<tr>
		<td width="300"><b>Nama Pelajar</b></td>
		<td width="200"><b>Status Kedatangan</b></td>
		<td><b>Ubah Kepada</b></td>
	</tr>

		<tr>
			<td>
				<input type="hidden" name="studentId" id="studentId" value="<%=URLSecurity.encryptFinal(attendance.getStudent().getStudent_id(), pinKey)%>"><%=attendance.getStudent().getName() %>
			</td>
			<td>
				<input type="hidden" name="reasonBefore" id="reasonBefore" value="<%=attendance.getD01()%>"><%=controller.convertSymbol(attendance.getD01()) %>
			</td>
			<td>
				<select name="reason" id="reason">
					
					<option value="/" <%if(attendance.getD01().equalsIgnoreCase("/")){ %>selected<%} %>>Hadir</option>
					<option value="O" <%if(attendance.getD01().equalsIgnoreCase("O")){ %>selected<%} %>>Tidak Hadir</option>
					<option value="L" <%if(attendance.getD01().equalsIgnoreCase("L")){ %>selected<%} %>>Lewat</option>
					<option value="/">Aktiviti Sekolah</option>
					<option value="AK" <%if(attendance.getD01().equalsIgnoreCase("AK")){ %>selected<%} %>>Ancaman Keselamatan</option>
					<option value="BA" <%if(attendance.getD01().equalsIgnoreCase("BA")){ %>selected<%} %>>Bencana Alam</option>
					<option value="S" <%if(attendance.getD01().equalsIgnoreCase("S")){ %>selected<%} %>>Cuti Sakit</option>
					<option value="G" <%if(attendance.getD01().equalsIgnoreCase("G")){ %>selected<%} %>>Digantung Sekolah</option>
					<option value="MP" <%if(attendance.getD01().equalsIgnoreCase("MP")){ %>selected<%} %>>Masalah Peribadi</option>
					<option value="LL" <%if(attendance.getD01().equalsIgnoreCase("LL")){ %>selected<%} %>>Lain-lain Sebab</option>
					<option value="UK" <%if(attendance.getD01().equalsIgnoreCase("UK")){ %>selected<%} %>>Urusan Keluarga</option>
				</select>
			</td>
		</tr>
</table>
<p>&nbsp;</p>
<hr/>
<p>&nbsp;</p>
				<center><input type="submit" 
				name="UpdateAttendance" value="Kemaskini"
				class="blackbutton" /></center>
</form>
</div>