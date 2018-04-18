<%
	int x = 1;
%>



<div id="accordion" style="width: 1100px;">
	<h3>PENGUMUMAN BELUM DIHANTAR</h3>
	<div>
		<%
			if (x == 1) {
		%>
		<p>Tiada PENGUMUMAN yang BELUM dihantar.</p>
		<%
			} else {
		%>
		<p>Tiada PENGUMUMAN yang BELUM dihantar.</p>
		<%
			}
		%>

	</div>
	<h3>PENGUMUMAN TELAH DIHANTAR</h3>
	<div>
		<%
			if (x == 1) {
		%>
		<p>Tiada PENGUMUMAN yang TELAH dihantar.</p>
		<%
			} else {
		%>
		<p>Tiada PENGUMUMAN yang TELAH dihantar.</p>
		<%
			}
		%>

	</div>

</div>