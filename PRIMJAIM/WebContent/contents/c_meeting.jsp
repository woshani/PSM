<% String instId = request.getParameter("instId"); %>

<div class="entry">
<h3>Perjumpaan Koku</h3>
<form name="MeetingForm" method="post" action="NewMeeting"> 
<input type="hidden" name="instId" id="instId" value="<%=instId%>">
<table>
	<tr>
		<th>Tarikh Perjumpaan :</th>
		<td><input type="date" name="tarikh"></td>
	</tr>
	<tr>
		<th>Masa Perjumpaan :</th>
		<td><input type="time" name="masa"></td>
	</tr>
	<tr>
		<th>Keterangan :</th>
		<td><textarea rows="4" cols="50" name="terang"></textarea></td>
	<tr>
		<td><input type="submit" id="penuh" name="penuh" 
			value="Kehadiran Penuh"></td>
		<td><input type="submit" name="tidakHadir" value="Tidak hadir"></td>
	</tr>
</table>
</form>
</div>