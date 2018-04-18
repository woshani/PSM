 <%@page import="bean.Politician"%>
<%@page import="bean.Police"%>
<%@page import="bean.User"%>
<%@page import="bean.Guardian"%>
<%@page import="bean.Teacher"%>
<%@page import="facade.ControllerWrapper"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%
	User user = new User();
	Teacher teacher = new Teacher();
	Guardian guardian = new Guardian();
	Police police = new Police();
	Politician politician = new Politician();
	
	int userLevel = 0;
	if(session.getAttribute("SASuser") != null) { 
		user = (User) session.getAttribute("SASuser");
	}
	
	if(session.getAttribute("SASteacher") != null) {
		teacher = (Teacher) session.getAttribute("SASteacher");
	}
	if(session.getAttribute("SASguardian") != null) {
		guardian = (Guardian) session.getAttribute("SASguardian");
	}
	if(session.getAttribute("SASpolice") != null) {
		police = (Police) session.getAttribute("SASpolice");
	}
	if(session.getAttribute("SASpolitician") != null) {
		politician = (Politician) session.getAttribute("SASpolitician");
	}

String fullname = "Tetamu";

if(user.getUser_id() != null) {
	userLevel = user.getLevel();
	if(userLevel > 2 && userLevel < 11) {
		fullname = teacher.getName();
	} else if(userLevel == 2) {
		fullname = guardian.getName();
	} else if(userLevel == 11 || userLevel == 12) {
		fullname = politician.getName();
	}else if(userLevel == 13 || userLevel == 14) {
		fullname = police.getName();
	}
}

Date dNow = new Date( );
SimpleDateFormat ft = 
new SimpleDateFormat ("MMMMM YYYY");

String month = ft.format(dNow); 

%>
<script type="text/javascript"> 
function display_c(){
var refresh=1000; // Refresh rate in milli seconds
mytime=setTimeout('display_ct()',refresh);
}

// function blink()
// {
//     if(document.getElementById("ct"))
//     {
//         var d = document.getElementById("ct") ;
//         d.style.color= (d.style.color=='red'?'#ffffcc':'red');
//         setTimeout('blink()', 1000);
//     }
// }

function display_ct() {	
	var month_name=new Array(12);
	month_name[0]="01";
	month_name[1]="02";
	month_name[2]="03";
	month_name[3]="04";
	month_name[4]="05";
	month_name[5]="06";
	month_name[6]="07";
	month_name[7]="08";
	month_name[8]="09";
	month_name[9]="10";
	month_name[10]="11";
	month_name[11]="12";
	
	var x = new Date();
	var x1="Tarikh : "+x.getDate() + "/" + month_name[x.getMonth()] + "/" + x.getFullYear(); 
	
	if(x.getMinutes()<10){
		if (x.getSeconds()<10){
			x1 = x1 + "&emsp;[" +  x.getHours( )+ ":0" +  x.getMinutes() + ":0" +  x.getSeconds()+"]";	
		}else{
			x1 = x1 + "&emsp;[" +  x.getHours( )+ ":0" +  x.getMinutes() + ":" +  x.getSeconds()+"]";
		}
	}else if(x.getMinutes()>=10){
		if (x.getSeconds()<10){
			x1 = x1 + "&emsp;[" +  x.getHours( ) + ":" +  x.getMinutes() + ":0" +  x.getSeconds()+"]";	
		}else{
			x1 = x1 + "&emsp;[" +  x.getHours( )+ ":" +  x.getMinutes() + ":" +  x.getSeconds()+"]";
		}
	}
	
	/* if(x.getHours()>12){
		document.getElementById('ct').innerHTML = x1+" TEST";
		document.getElementById('ct').style.color='green';
		tt=display_c();		
	}else{
		document.getElementById('ct').innerHTML = x1+" TEST";
		document.getElementById('ct').style.color='red';
		tt=display_c();
		blink();
	} */
	document.getElementById('ct').innerHTML = x1;
	//document.getElementById('ct').style.color='green';
	tt=display_c();
}

	 
</script>

<body onload=display_ct() >

<div id="menu-wrapper">
	<div id="menu" class="container">
	<ul>
	<%if(userLevel > 2){ %>
		
			<li><a href="index.jsp"><b>Utama</b></a></li>
			
			<%if(userLevel > 4 && userLevel < 8) {%>
					<li><a href="attendanceStatus.jsp"><b>Status Kedatangan</b></a></li>
					<li><a href="absentStudentList.jsp"><b>Pelajar Tidak Hadir</b></a></li>
					<li><a href="schoolAttendanceList.jsp?month=<%=month%>"><b>Kedatangan Bulanan Sekolah</b></a></li>
					<!-- <li><a href="schoolAttendanceByRace.jsp">Kedatangan Bulanan Mengikut Bangsa</a></li> -->
					<!-- <li><a href="teacherList.jsp">Senarai Guru</a></li>
					<li><a href="classProfile.jsp">Senarai Pelajar</a></li>
					<li><a href="classProfile.jsp">Senarai Kelas</a></li> -->
			<%}if(userLevel > 2 && userLevel < 8){ %>
				<li><a href="classProfile.jsp"><b>Kelas</b></a></li>
				<!-- <li><a href="forum.jsp"><b>Forum</b></a></li> -->
			<%} %>
			<%if(userLevel == 8){ %>
				<li><a href="PPDSchoolList.jsp"><b>Senarai Sekolah</b></a></li>
				<li><a href="PPDKedatanganSekolah.jsp"><b>Kedatangan Harian Sekolah</b></a></li>
			<%} %>
			<%if(userLevel == 9){ %>
				<li><a href="JPNReport.jsp"><b>Kedatangan Harian Sekolah</b></a></li>
				<li><a href="JPNReportHistory.jsp"><b>Rekod Kedatangan Terdahulu</b></a></li>
				<li><a href="listJPNAnnouncement.jsp"><b>Pengumuman</b></a></li>
				<li><a href="listJPNOutbox.jsp"><b>SMS Keluar</b></a></li>
			<%} %>
			<%if(userLevel == 10){ %>
				<li><a href="ZonUtara.jsp"><b>Zon Utara</b></a></li>
				<li><a href="ZonTengah.jsp"><b>Zon Tengah</b></a></li>
				<li><a href="ZonSelatan.jsp"><b>Zon Selatan</b></a></li>
				<li><a href="ZonTimur.jsp"><b>Zon Timur</b></a></li>
				<li><a href="ZonMalaysiaTimur.jsp"><b>Zon Malaysia Timur</b></a></li>
				<li><a href="listKPMAnnouncement.jsp"><b>Pengumuman</b></a></li>
				<li><a href="listKPMOutbox.jsp"><b>SMS Keluar</b></a></li>
			<%} %>
			<%if(userLevel > 2 && userLevel < 8){ %>
				<li><a href="listAnnouncement.jsp"><b>Pengumuman</b></a></li>
				<li><a href="listOutbox.jsp"><b>SMS Keluar</b></a></li>
			<%} %>
			
			<!-- Ketua Menteri / Parlimen -->
			<%if(userLevel == 11){ %>
				<li><a href="politicianReport.jsp"><b>Kedatangan Harian Sekolah</b></a></li>
				<li><a href="politicianReportHistory.jsp"><b>Rekod Kedatangan Terdahulu</b></a></li>
			<%} %>
			
			<!-- Ketua DUN  -->
			<%if(userLevel == 12){ %>
				<li><a href="dunSchoolList.jsp"><b>Senarai Sekolah</b></a></li>
				<li><a href="politicianSchoolAttendance.jsp"><b>Kedatangan Harian Sekolah</b></a></li>
				<li><a href="politicianDunReportHistory.jsp"><b>Rekod Kedatangan Terdahulu</b></a></li>
			<%} %>
			
			<!-- Ketua Polis / IPK -->
			<%if(userLevel == 13){ %>
				<li><a href="PoliceReport.jsp"><b>Kedatangan Harian Sekolah</b></a></li>
				<li><a href="PoliceReportHistory.jsp"><b>Rekod Kedatangan Terdahulu</b></a></li>
			<%} %>
			
			<!-- Ketua Balai Polis -->
			<%if(userLevel == 14){ %>
				<li><a href="StationSchoolList.jsp"><b>Senarai Sekolah</b></a></li>
				<li><a href="PoliceSchoolAttendance.jsp"><b>Kedatangan Harian Sekolah</b></a></li>
				<li><a href="policeStationReportHistory.jsp"><b>Rekod Kedatangan Terdahulu</b></a></li>
			<%} %>
			
			<!-- 
			<li><a href="teacherProfile.jsp">Guru</a></li>
			<li><a href="studentProfile.jsp">Pelajar</a></li>
			<li><a href="guardianProfile.jsp">Penjaga</a></li> -->
		
	<%} %>
	 
	</ul>
	</div>
</div>

<div id="personal-wrapper">
<%
	if(user.getUser_id() != null) {
%>
	
	Selamat datang, <%=fullname %> | <a href="LogOut.jsp">Log Keluar</a><br>
	<span id='ct' ></span> 
	
<%
	} else {
%>
		&nbsp;
<%
	}
%>
</div>
</body>