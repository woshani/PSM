<%@page import="bean.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<%
	Date dNow = new Date();
	SimpleDateFormat ft = new SimpleDateFormat("MMMMM YYYY");

	String month = ft.format(dNow);
%>

<script type="text/javascript"> 
function display_c(){
	var refresh=1000; // Refresh rate in milli seconds
	mytime=setTimeout('display_ct()',refresh);
}

function display_ct() {	
	var month_name=new Array(12);
	month_name[0]="Januari";
	month_name[1]="Februari";
	month_name[2]="Mac";
	month_name[3]="April";
	month_name[4]="Mei";
	month_name[5]="Jun";
	month_name[6]="Julai";
	month_name[7]="Ogos";
	month_name[8]="September";
	month_name[9]="Oktober";
	month_name[10]="November";
	month_name[11]="Disember";
	
	var weekday = new Array(7);
	weekday[0]=  "Ahad";
	weekday[1] = "Isnin";
	weekday[2] = "Selasa";
	weekday[3] = "Rabu";
	weekday[4] = "Khamis";
	weekday[5] = "Jumaat";
	weekday[6] = "Sabtu";
	
	var x = new Date();
	var x1=x.getDate() + "-" + month_name[x.getMonth()] + "-" + x.getFullYear(); 
	x1 = weekday[x.getDay()] + ", " + x1 + ", " +  x.getHours( )+ ":" +  x.getMinutes() + ":" +  x.getSeconds();
	document.getElementById('ct').innerHTML = x1;

	tt=display_c();
	 }
	 
</script>

<style>
#navMemo {
	line-height: 15px;
	background-color: #eeeeee;
	width: 340px; /* old - 400px */
	height: 650px;
	float: left;
	padding: 5px;
}

#sectionMemo {
	width: 730px; /* old - 600px */
	float: right;
	/* padding: 10px; */ /* old */
	padding: 5px 5px 5px 15px; /* new */
	align: center;
}
</style>

<%-- <div class="entry"
	style="outline-style: solid; outline-color: black;">
	<form id="LogInForm" method="post" action="LogIn">
		</br>
		<table width="370px" border="0" cellpadding="5">
			<%
				if (request.getAttribute("SASinvalidUsername") != null) {
			%>
			<tr>
				<td colspan="2"><span class="warning">* Nama pengguna
						tiada dalam sistem</span></td>
			</tr>
			<%
				} else if (request.getAttribute("SASinvalidPassword") != null) {
			%>
			<tr>
				<td colspan="2"><span class="warning">* Kata laluan
						salah</span></td>
			</tr>
			<%
				}
			%>
			<tr>
				<td width="120px">Nama Pengguna :</td>
				<td><input name="username" type="text"
					class="validate[required]" maxlength="10"
					style="text-transform: uppercase;" /></td>
			</tr>
			<tr>
				<td>Kata Laluan :</td>
				<td><input name="password" type="password"
					class="validate[required]" maxlength="12" /></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" name="logIn"
					value="Log Masuk" class="blackbutton" /></td>
			</tr>
		</table>
	</form>
</div> --%>

<body onload=display_ct()>
	</br>
	<div id="navMemo">
		<form id="LogInForm" method="post" action="LogIn">
			<fieldset>
				<legend>Log Masuk</legend>
				</br>
				<table width="315px" border="0" cellpadding="5">
					<%
						/* old - 370px */
					%>
					<%
						if (request.getAttribute("SASinvalidUsername") != null) {
					%>
					<tr>
						<td colspan="2"><span class="warning">* Nama pengguna
								tiada dalam sistem</span></td>
					</tr>
					<%
						} else if (request.getAttribute("SASinvalidPassword") != null) {
					%>
					<tr>
						<td colspan="2"><span class="warning">* Kata laluan
								salah</span></td>
					</tr>
					<%
						}
					%>
					<tr>
						<%
							/* old - 120px */
						%>
						<td width="110px"><font color="black">Nama Pengguna :</font></td>
						<td><input name="username" type="text"
							class="validate[required]" maxlength="10"
							style="text-transform: uppercase;" /></td>
					</tr>
					<tr>
						<td><font color="black">Kata Laluan :</font></td>
						<td><input name="password" type="password"
							class="validate[required]" maxlength="12" /></td>
					</tr>
					<%
						if ((request.getAttribute("SASinvalidUsername") != null)
								|| request.getAttribute("SASinvalidPassword") != null) {
					%>

					<%
						} else {
					%>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
					<%
						}
					%>
					<tr>
						<td colspan="2" align="right"><input type="submit"
							name="logIn" value="Log Masuk" class="blackbutton" /></td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>

	<div id="sectionMemo">
		<h1 align="left">
		<font color="black" size="5">
			<p><u>Terkini dari PRIM : </u></p>
		</font>
		</h1>
		
		<h1 align="center">
		<font color="black" size="5">
		
		<p>(09/10/2017) Demo Aplikasi Telefon Pintar PRIM (Android) untuk Ibu/Bapa dan Penjaga 
		</p>
			<a href="http://prim.utem.edu.my/apk/prim.apk">
			<!--<a href="http://localhost:8080/PRIMWeb2017/apk/prim.apk">-->
				<font color="blue" size="5">Muat Turun PRIM (Penjaga) APK file - Demo Release</font>
				</a>
		</font>
		</h1>
		
		</br>
		</br>
		
		<hr>
		
		<h1 align="center">
		<font color="black" size="5">
		
		Kami mohon pandangan ibubapa/penjaga kepada anak-anak yang bersekolah diseluruh MALAYSIA untuk mengisi </font><br>
		<a href="https://docs.google.com/forms/d/e/1FAIpQLSfkLeFjwZgQ94DyGI7GEp6UTp3t7foZhn3X-tZgM9FOHrfEGg/viewform?c=0&w=1">
			<font color="blue" size="5">
				>> Borang KAJISELIDIK Atas Talian  << 
			</font></a> <br>
		<font color="black" size="5">yang telah kami sediakan.</font>
		</font></h1>
		</br>
		</br>
		<h1 align="right"><font color="black" size="3"><p>	Kumpulan Penyelidik PRIM,<br>
			Fakulti Teknologi Maklumat dan Komunikasi (FTMK),<br>
			Universiti Teknikal Malaysia Melaka (UTeM).
		</p></font></h1>
		</br>
		</br>
		
		<hr>
		</br>
		<h2 align="center">
			<font color="black" size="5">Statistik Kedatangan Hari Ini ( <span
				id='ct'></span> )
			</font>
		</h2>

		<%
			if (request.getParameter("city") == null) {
		%>
		<%
			/* new */
		%>
		<div style="height: 500px; overflow-y: auto;">
			<jsp:include page="c_displayjpnreportOuter.jsp"></jsp:include>
		</div>
		<%
			}
		%>

		<%
			if ((request.getParameter("city") != null)
					&& (request.getParameter("instituitionId") != null)) {
		%>
		<input type="button" onclick="location.href='logIn.jsp'"
			value="Kembali" class="blackbutton" />
		<%
			/* new */
		%>
		<div style="height: 500px; overflow-y: auto;">
			<jsp:include page="c_displayppdKedatanganSekolah.jsp"></jsp:include>
		</div>
		<%
			}
		%>
		<br>
	</div>
</body>