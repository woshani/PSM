<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="controller.URLSecurity"%>
<%@page import="bean.Classroom"%>
<%@page import="bean.DailyAttendance"%>
<%@page import="bean.Student"%>
<%@page import="bean.Teacher"%>
<%@page import="bean.Report"%>
<%@page import="bean.AnalysisYearAttendance"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="facade.ControllerWrapper"%>

<div class="entry">
	<%
		String pinKey = String.valueOf(session.getAttribute("pinKey"));

			ControllerWrapper controller = new ControllerWrapper();
			Teacher teacher = new Teacher();
			if (session.getAttribute("SASteacher") != null) {
		teacher = (Teacher) session.getAttribute("SASteacher");
			} else {
		teacher = controller.getTeacherById(teacher.getTeacher_id());
			}
			
			Student student = null;
			
			// Calling list of student daily attendance 01 - 30
			List<DailyAttendance> attendances = new ArrayList<DailyAttendance>();
			List<DailyAttendance> attendancesTransfer = new ArrayList<DailyAttendance>();
			
			//Calling list of student daily attendance after transfer 01 - 30
			
			// Calling summary Report of daily attendance 01 - 30
			List<Report> reports = new ArrayList<Report>();
			
			// Calling Analysis Result of daily attendance
			AnalysisYearAttendance analysis = new AnalysisYearAttendance();
			
			
			Classroom classroom = new Classroom();
			
			//decrypt back id parameter to get classroom id
			String classId = URLSecurity.decryptFinal(request.getParameter("id"), pinKey);
			
			classroom = controller.getClassroomById(classId);
			
			//Encrypted classroomId
			String classIdEnc = URLSecurity.encryptFinal(classroom.getClass_id(), pinKey);
			
			String month = request.getParameter("month");
			
			if (classroom.getClass_id()!=null) {
		SimpleDateFormat fromUser = new SimpleDateFormat("MMMMM yyyy");
		SimpleDateFormat myFormat = new SimpleDateFormat("MMyy");
		
		String formatedMonth = myFormat.format(fromUser.parse(month));
		String newMonth = formatedMonth.substring(0, 2);
		String newYear = formatedMonth.substring(2, 4);
		
		attendances = controller.MonthlyAttendanceList(formatedMonth.substring(0, 2), formatedMonth.substring(2, 4),
		classroom.getClass_id());
		
		attendancesTransfer = controller.MonthlyTransferAttendanceList(formatedMonth.substring(0, 2), formatedMonth.substring(2, 4),
		classroom.getClass_id());		
			
		reports = controller.MonthlyReportList(formatedMonth.substring(0, 2), formatedMonth.substring(2, 4),
		classroom.getClass_id());
		
		analysis = controller.getAnalysisYearAttendance(formatedMonth.substring(0, 2), formatedMonth.substring(2, 4),
		classroom.getClass_id());
		
		// if got data on selected month
		if(attendances.size() > 0) {
	%>

	<h3>
		LAPORAN KEHADIRAN KELAS
		<%=classroom.getName()%>
		PADA BULAN
		<%=controller.convertMonthToMalay(newMonth)%>
		20<%=newYear%></h3>
	<br> <br>
	<h3>Sila Pilih Pada Nama Murid atau Tarikh Untuk Mengemaskini</h3>
	<br> <br> <a
		href="${pageContext.request.contextPath}/PDFMonthlyAttendance?id=<%=classroom.getClass_id() %>&month=<%=month %>"
		class="blackbutton">PDF</a><input type="button" class="blackbutton"
		onclick="symbol()" value="Periksa Simbol"> <br> <br>
	<div>
		<table width="1100px" border="1" cellspacing="0" cellpadding="3">
			<tr>
				<th align="center">BIL</th>
				<th align="center">PELAJAR</th>

				<%
					for(int index = 1; index < 32; index++){
				%>
				<%
					if (index <10){
				%>

				<th align="center"><a
					href="checkAttendance.jsp?id=<%=classIdEnc%>&month=<%=newMonth%>&day=<%=index%>&year=<%=formatedMonth.substring(2, 4)%>&actualMonth=<%=month%>">0<%=index%></a></th>
				<%
					}else{
				%>
				<th align="center"><a
					href="checkAttendance.jsp?id=<%=classIdEnc%>&month=<%=newMonth%>&day=<%=index%>&year=<%=formatedMonth.substring(2, 4)%>&actualMonth=<%=month%>"><%=index%></a></th>
				<%
					}
								}
				%>

				<th align="center">Bulan ini</th>
				<th align="center">Bulan sudah</th>
				<th align="center">Hingga akhir<br>bulan ini
				</th>
			</tr>
			<%
				int index = 0;
					for(DailyAttendance attendance : attendances) {
						
						//Encrypted studentId
						String studentIdEnc = new String();
						studentIdEnc = URLSecurity.encryptFinal(attendance.getStudent().getStudent_id(), pinKey);
			%>
			<tr>
				<th align="center"><%=index = index + 1%>.</th>
				<th align="center"><a
					href="updateAttendance.jsp?studentId=<%=studentIdEnc%>&id=<%=classIdEnc%>&month=<%=newMonth%>&year=<%=formatedMonth.substring(2, 4)%>&actualMonth=<%=month%>"><%=attendance.getStudent().getName()%></a>
				</th>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD01())%>"><%=attendance.getD01()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD02())%>"><%=attendance.getD02()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD03())%>"><%=attendance.getD03()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD04())%>"><%=attendance.getD04()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD05())%>"><%=attendance.getD05()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD06())%>"><%=attendance.getD06()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD07())%>"><%=attendance.getD07()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD08())%>"><%=attendance.getD08()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD09())%>"><%=attendance.getD09()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD10())%>"><%=attendance.getD10()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD11())%>"><%=attendance.getD11()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD12())%>"><%=attendance.getD12()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD13())%>"><%=attendance.getD13()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD14())%>"><%=attendance.getD14()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD15())%>"><%=attendance.getD15()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD16())%>"><%=attendance.getD16()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD17())%>"><%=attendance.getD17()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD18())%>"><%=attendance.getD18()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD19())%>"><%=attendance.getD19()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD20())%>"><%=attendance.getD20()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD21())%>"><%=attendance.getD21()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD22())%>"><%=attendance.getD22()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD23())%>"><%=attendance.getD23()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD24())%>"><%=attendance.getD24()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD25())%>"><%=attendance.getD25()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD26())%>"><%=attendance.getD26()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD27())%>"><%=attendance.getD27()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD28())%>"><%=attendance.getD28()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD29())%>"><%=attendance.getD29()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD30())%>"><%=attendance.getD30()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendance.getD31())%>"><%=attendance.getD31()%></span>

				</td>
				<td align="center">
					<%
						out.print(attendance.getThisMonth());
					%>
				</td>
				<td align="center">
					<%
						out.print(attendance.getPreviousMonth());
					%>
				</td>
				<td align="center">
					<%
						out.print(attendance.getEndMonth());
					%>
				</td>
			</tr>

			<%
				}
				
				if(attendancesTransfer.size()>0){
			
				for(DailyAttendance attendanceTransfer : attendancesTransfer) {
						
						//Encrypted studentId
						String studentIdEnc = new String();
						studentIdEnc = URLSecurity.encryptFinal(attendanceTransfer.getStudent().getStudent_id(), pinKey);
			%>
			<tr>
				<th align="center"><%=index = index + 1%>.</th>
				<th align="center"><%="*** "+attendanceTransfer.getStudent().getName()%>
				</th>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD01())%>"><%=attendanceTransfer.getD01()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD02())%>"><%=attendanceTransfer.getD02()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD03())%>"><%=attendanceTransfer.getD03()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD04())%>"><%=attendanceTransfer.getD04()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD05())%>"><%=attendanceTransfer.getD05()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD06())%>"><%=attendanceTransfer.getD06()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD07())%>"><%=attendanceTransfer.getD07()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD08())%>"><%=attendanceTransfer.getD08()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD09())%>"><%=attendanceTransfer.getD09()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD10())%>"><%=attendanceTransfer.getD10()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD11())%>"><%=attendanceTransfer.getD11()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD12())%>"><%=attendanceTransfer.getD12()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD13())%>"><%=attendanceTransfer.getD13()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD14())%>"><%=attendanceTransfer.getD14()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD15())%>"><%=attendanceTransfer.getD15()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD16())%>"><%=attendanceTransfer.getD16()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD17())%>"><%=attendanceTransfer.getD17()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD18())%>"><%=attendanceTransfer.getD18()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD19())%>"><%=attendanceTransfer.getD19()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD20())%>"><%=attendanceTransfer.getD20()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD21())%>"><%=attendanceTransfer.getD21()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD22())%>"><%=attendanceTransfer.getD22()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD23())%>"><%=attendanceTransfer.getD23()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD24())%>"><%=attendanceTransfer.getD24()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD25())%>"><%=attendanceTransfer.getD25()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD26())%>"><%=attendanceTransfer.getD26()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD27())%>"><%=attendanceTransfer.getD27()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD28())%>"><%=attendanceTransfer.getD28()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD29())%>"><%=attendanceTransfer.getD29()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD30())%>"><%=attendanceTransfer.getD30()%></span>

				</td>
				<td align="center"><span
					title="<%=controller.convertSymbol(attendanceTransfer.getD31())%>"><%=attendanceTransfer.getD31()%></span>

				</td>
				<td align="center">
					<%
						out.print(attendanceTransfer.getThisMonth());
					%>
				</td>
				<td align="center">
					<%
						out.print(attendanceTransfer.getPreviousMonth());
					%>
				</td>
				<td align="center">
					<%
						out.print(attendanceTransfer.getEndMonth());
					%>
				</td>
			</tr>

			<%
				}
				}
			%>
			<tr height="20">
				<td colspan="36"></td>
			</tr>
			<%
				
				for(Report report : reports) {
			%>
			<tr>
				<th></th>
				<th>
					<%
						out.print(report.getItem());
					%>
				</th>
				<td align="center">
					<%
						out.print(report.getD01());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD02());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD03());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD04());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD05());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD06());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD07());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD08());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD09());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD10());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD11());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD12());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD13());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD14());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD15());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD16());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD17());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD18());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD19());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD20());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD21());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD22());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD23());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD24());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD25());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD26());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD27());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD28());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD29());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD30());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getD31());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getThisMonth());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getPreviousMonth());
					%>
				</td>
				<td align="center">
					<%
						out.print(report.getEndMonth());
					%>
				</td>
			</tr>
			<%
				}
			%>

		</table>

	</div>
	<p>&nbsp;</p>
	<p>*** Pelajar Berpindah.</p>
	<p>&nbsp;</p>
	<hr />
	<p>&nbsp;</p>

	<table cellspacing="0" cellpadding="3">
		<tr>
			<td colspan="3"><h2>Ringkasan Kedatangan Bulanan</h2></td>
		</tr>
		<tr>
			<td><b>Jumlah Persekolahan</b></td>
			<td>:</td>
			<td><%=analysis.getTotal_day()%></td>
		</tr>
		<tr>
			<td><b>Hitung Panjang Kedatangan</b></td>
			<td>:</td>
			<td><%=analysis.getAttendance()%></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><b>Peratusan Kedatangan</b></td>
			<td>:</td>
			<td><%=analysis.getAttendance_percentage()%>%</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><b>Hitung Panjang Ramai Pelajar / murid</b></td>
			<td>:</td>
			<td><%=analysis.getNo_of_student()%></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><b>Ramai pelajar / murid dalam<br>daftar hujung
					bulan lepas
			</b></td>
			<td>:</td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><b>Ramai pelajar / murid yang masuk</b></td>
			<td>:</td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><b>Ramai pelajar / murid yang keluar</b></td>
			<td>:</td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><b>Ramai pelajar / murid <br> daftar hujung bulan
			</b></td>
			<td>:</td>
			<td></td>
		</tr>
	</table>

	<p>&nbsp;</p>
	<hr />
	<p>&nbsp;</p>

	<%
		} else {
	%>
	<p>
		Maklumat kehadiran kelas pada bulan <b><%=month%></b> tiada dalam
		sistem maklumat.
	</p>
	<%
		} 
		}
	%>
</div>

<%
	if (request.getParameter("alert") != null){
		if(request.getParameter("alert").endsWith("0")){
%>
<script>
	alert("Kedatangan Belum Dibuat. Sila Buat Langkah 1 Dahulu.");
</script>
<%
	}else if(request.getParameter("alert").endsWith("1")){
%>
<script>
	alert("Kedatangan Tidak Boleh Diubah.");
</script>
<%
	}
	}
%>