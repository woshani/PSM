<%@page import="controller.URLSecurity"%>
<div class="entry">
	<%@page import="bean.Guardian"%>
	<%@page import="bean.Student"%>
	<%@page import="bean.GuardianContact"%>
	<%@page import="bean.AlertPackage"%>
	<%@page import="java.util.List"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="facade.ControllerWrapper"%>
	<%@page import="java.util.Date"%>
	<%@page import="java.text.SimpleDateFormat"%>
	<%
		String pinKey = (String) session.getAttribute("pinKey");

		if (request.getParameter("id") != null) {
			String guardianIdEnc = request.getParameter("id");
			String guardian_id = URLSecurity.decryptFinal(guardianIdEnc,
					pinKey);

			ControllerWrapper controller = new ControllerWrapper();
			Guardian guardian = new Guardian();
			guardian = controller.getGuardianById(guardian_id);

			if (guardian.getGuardian_id() != null) {
				List<Student> students = controller
						.getStudentsByGuardianId(guardian.getGuardian_id());
				ArrayList<GuardianContact> guardianContacts = controller
						.getAllGuardianContacts(guardian.getGuardian_id());
				List<AlertPackage> alertPackages = controller
						.getAlertPackagesByGuardian(guardian);
				GuardianContact subscribedContact = controller
						.getGuardianSubscribedContact(guardian
								.getGuardian_id());
				int totalOutgoingSMS = controller
						.getGuardianOutgoingSMSCount(guardian);
				int totalIncomingSMS = controller
						.getGuardianIncomingSMSCount(guardian);
				int monthlyOutgoingSMS = controller
						.getGuardianMonthlyOutgoingSMSCount(guardian);
				int monthlyIncomingSMS = controller
						.getGuardianMonthlyIncomingSMSCount(guardian);

				Date dNow = new Date();
				SimpleDateFormat ft = new SimpleDateFormat("MMMMM YYYY");

				String month = ft.format(dNow);
	%>
	<div id="accordion" style="width: 1100px;">
		<h3>MAKLUMAT PENJAGA</h3>
		<div>
			<table width="1000px" border="0" cellpadding="5">
				<tr>
					<td width="200px"><strong>NAMA</strong></td>
					<td width="5px">:</td>
					<td><%=guardian.getName()%></td>

				</tr>
				<tr>
					<td width="200px"><strong>NO. KAD PENGENALAN</strong></td>
					<td width="5px">:</td>
					<td><%=guardian.getIc()%></td>

				</tr>
				<tr>
					<td><strong>JANTINA</strong></td>
					<td width="5px">:</td>
					<%
						if (guardian.getGender().equalsIgnoreCase("L")) {
					%>
					<td>LELAKI</td>
					<%
						} else if (guardian.getGender().equalsIgnoreCase("P")) {
					%>
					<td>PEREMPUAN</td>
					<%
						}
					%>
				</tr>
				<tr>
					<td><strong>PEKERJAAN</strong></td>
					<td width="5px">:</td>
					<td><%=guardian.getOccupation()%></td>
				</tr>
				<tr>
					<td><strong>ALAMAT</strong></td>
					<td width="5px">:</td>
					<td><%=guardian.getFullAddress()%></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<form name="ContactInformation" id="ContactInformation" method="post"
				action="UpdateContact">
				<table width="700px" border="1" cellspacing="0" cellpadding="3">
					<tr>
						<th>Bil</th>
						<th>Nombor Telefon</th>
						<!--<th>Kemaskini</th>-->
						<th>Simpan</th>
					</tr>
					<%
						if (guardianContacts.size() > 0) {
					%>
					<%
						int gsIndex = 0;
									for (GuardianContact guardianContact : guardianContacts) {
										gsIndex++;

										String guardianContactEnc = URLSecurity
												.encryptFinal(
														guardianContact.getGcontact_id(),
														pinKey);
					%>
					<tr>
						<td align="center"><%=gsIndex%></td>
						<td align="center">+6 <input type="number" name="contactNumber"
							id="contactNumber" oninput="this.value = this.value.replace(/[^0-9.]/g, ''); this.value = this.value.replace(/(\..*)\./g, '$1');" onKeyDown="if(this.value.length==11 && event.keyCode!=8) return false;"
							value=<%=guardianContact.getPhone_number()%> style="text-align:center"></td>
						<!--<td align="center"><input type="button" name="kemaskini"
							onclick="$('.contactNumber').removeAttr('disabled'); "
							value="KEMASKINI" /></td>-->
						<td align="center"><input type="submit" name="simpan"
							value="SIMPAN" /></td>
					</tr>
					<input type="hidden" name="contactId" id="contactId"
						value=<%=guardianContactEnc%>>
					<input type="hidden" name="guardianId" id="guardianId"
						value=<%=guardianIdEnc%>>
					<%
						}
					%>
				</table>

			</form>
			<%
				} else {
			%>
			<p>Tiada maklumat mengenai penjaga didapati.</p>
			<%
				}
			%>
		</div>
		<h3>MAKLUMAT ANAK JAGAAN</h3>
		<div>
			<%
				if (students.size() > 0) {
			%>
			<ul>
				<%
					int stuIndex = 0;
								for (Student student : students) {
									stuIndex++;
									String studentIdEnc = URLSecurity.encryptFinal(
											student.getStudent_id(), pinKey);
									String classIdEnc = URLSecurity.encryptFinal(
											student.getClassroom().getClass_id(),
											pinKey);
				%>
				<li style="text-align: left;"><%=stuIndex%>) <a
					href="guardianStudent.jsp?studentId=<%=studentIdEnc%>&classId=<%=classIdEnc%>&month=<%=month%>">
						<%=student.getName()%></a> [SEKOLAH : <%=student.getClassroom().getInstituition()
									.getAcademic_name()%> KELAS : <%=student.getClassroom().getName()%>]</li>
				<%
					}
				%>
			</ul>
			<%
				} else {
			%>
			<p>Tiada pelajar di bawah jagaan penjaga ini.</p>
			<%
				}
			%>
		</div>
		<h3>MAKLUMAT LANGANAN SMS</h3>
		<div>
			<h4>Pakej Langanan:</h4>
			<%
				if (students.size() > 0) {
			%>
			<ul>
				<%
					int packIndex = 0;
								for (AlertPackage alertPackage : alertPackages) {
									packIndex++;
				%>
				<li><%=packIndex%>) <%=alertPackage.getName()%></li>
				<%
					}
				%>
			</ul>
			<p>&nbsp;</p>
			<h4>Nombor Telefon yang Dilanggan:</h4>
			<p><%=subscribedContact.getPhone_number()%>
				(<%=subscribedContact.getProvider()%>)
			</p>
			<p>&nbsp;</p>
			<h4>Maklumat SMS Dihantar dan Diterima:</h4>
			<p>
				Jumlah SMS Dihantar ke Penjaga :
				<%=totalOutgoingSMS%></p>
			<p>
				Jumlah SMS Dihantar oleh Penjaga :
				<%=totalIncomingSMS%></p>
			<p>
				Jumlah SMS Dihantar ke Penjaga bulan ini :
				<%=monthlyOutgoingSMS%></p>
			<p>
				Jumlah SMS Dihantar oleh Penjaga bulan ini :
				<%=monthlyIncomingSMS%></p>
			<%
				} else {
			%>
			<p>Tiada sebarang langanan SMS.</p>
			<%
				}
			%>
		</div>
	</div>
	<%
		} else {
	%>
	<p>
		Maklumat penjaga dengan ID <b><%=request.getParameter("id")%></b>
		tiada dalam sistem maklumat.
	</p>
	<%
		}
		} else {
			Guardian guardian = new Guardian();
			guardian = (Guardian) session.getAttribute("SASguardian");
			ControllerWrapper controller = new ControllerWrapper();

			if (guardian.getGuardian_id() != null) {
				List<Student> students = controller
						.getStudentsByGuardianId(guardian.getGuardian_id());
				List<GuardianContact> guardianContacts = controller
						.getAllGuardianContacts(guardian.getGuardian_id());
				List<AlertPackage> alertPackages = controller
						.getAlertPackagesByGuardian(guardian);
				GuardianContact subscribedContact = controller
						.getGuardianSubscribedContact(guardian
								.getGuardian_id());
				int totalOutgoingSMS = controller
						.getGuardianOutgoingSMSCount(guardian);
				int totalIncomingSMS = controller
						.getGuardianIncomingSMSCount(guardian);
				int monthlyOutgoingSMS = controller
						.getGuardianMonthlyOutgoingSMSCount(guardian);
				int monthlyIncomingSMS = controller
						.getGuardianMonthlyIncomingSMSCount(guardian);

				Date dNow = new Date();
				SimpleDateFormat ft = new SimpleDateFormat("MMMMM YYYY");

				String month = ft.format(dNow);
	%>
	<div id="accordion" style="width: 1100px;">
		<h3>MAKLUMAT PENJAGA</h3>
		<div>
			<table width="1000px" border="0" cellpadding="5">
				<tr>
					<td width="200px"><strong>NAMA</strong></td>
					<td width="5px">:</td>
					<td><%=guardian.getName()%></td>
				</tr>
				<tr>
					<td><strong>JANTINA</strong></td>
					<td width="5px">:</td>
					<%
						if (guardian.getGender().equalsIgnoreCase("L")) {
					%>
					<td>LELAKI</td>
					<%
						} else if (guardian.getGender().equalsIgnoreCase("P")) {
					%>
					<td>PEREMPUAN</td>
					<%
						}
					%>
				</tr>
				<tr>
					<td><strong>PEKERJAAN</strong></td>
					<td width="5px">:</td>
					<td><%=guardian.getOccupation()%></td>
				</tr>
				<tr>
					<td><strong>ALAMAT</strong></td>
					<td width="5px">:</td>
					<td><%=guardian.getFullAddress()%></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<form name="ContactInformation" id="ContactInformation" method="post"
				action="UpdateContact">
				<table width="700px" border="1" cellspacing="0" cellpadding="3">
					<%
						if (guardianContacts.size() > 0) {
					%>
					<%
						int gsIndex = 0;
									for (GuardianContact guardianContact : guardianContacts) {
										gsIndex++;
					%>

					<tr>
						<th>Bil</th>
						<th>Nombor Telefon</th>
						<!--  <th>Kemaskini</th>-->
						<th>Simpan</th>
					</tr>

					<tr>
						<td align="center"><%=gsIndex%></td>
						<td align="center">+6 <input type="number" name="contactNumber"
							id="contactNumber" oninput="this.value = this.value.replace(/[^0-9.]/g, ''); this.value = this.value.replace(/(\..*)\./g, '$1');" onKeyDown="if(this.value.length==11 && event.keyCode!=8) return false;"
							value=<%=guardianContact.getPhone_number()%> style="text-align:center"></td>
						<!--<td align="center"><input type="button" name="kemaskini"
							onclick="$('.contactNumber').removeAttr('disabled'); "
							value="KEMASKINI" /></td>-->
						<td align="center"><input type="submit" name="simpan"
							value="SIMPAN" /></td>
					</tr>
					<input type="hidden" name="contactId" id="contactId"
						value=<%=guardianContact.getGcontact_id()%>>
					<input type="hidden" name="guardianId" id="guardianId"
						value=<%=guardian.getGuardian_id()%>>
					<%
						}
					%>
				</table>

			</form>
			<%
				} else {
			%>
			<p>Tiada maklumat mengenai penjaga didapati.</p>
			<%
				}
			%>
		</div>
		<h3>MAKLUMAT ANAK JAGAAN</h3>
		<div>
			<%
				if (students.size() > 0) {
			%>
			<ul>
				<%
					int stuIndex = 0;
								for (Student student : students) {
									stuIndex++;
									String studentIdEnc = URLSecurity.encryptFinal(
											student.getStudent_id(), pinKey);
									String classIdEnc = URLSecurity.encryptFinal(
											student.getClassroom().getClass_id(),
											pinKey);
				%>
				<li><%=stuIndex%>) <a
					href="guardianStudent.jsp?studentId=<%=studentIdEnc%>&classId=<%=classIdEnc%>&month=<%=month%>">
						<%=student.getName()%></a> [SEKOLAH : <%=student.getClassroom().getInstituition()
									.getAcademic_name()%> KELAS : <%=student.getClassroom().getName()%>]</li>
				<%
					}
				%>
			</ul>
			<%
				} else {
			%>
			<p>Tiada pelajar di bawah jagaan penjaga ini.</p>
			<%
				}
			%>
		</div>
		<h3>MAKLUMAT LANGANAN SMS</h3>
		<div>
			<h4>Pakej Langanan:</h4>
			<%
				if (students.size() > 0) {
			%>
			<ul>
				<%
					int packIndex = 0;
								for (AlertPackage alertPackage : alertPackages) {
									packIndex++;
				%>
				<li><%=packIndex%>) <%=alertPackage.getName()%></li>
				<%
					}
				%>
			</ul>
			<p>&nbsp;</p>
			<h4>Nombor Telefon yang Dilanggan:</h4>
			<p><%=subscribedContact.getPhone_number()%>
				(<%=subscribedContact.getProvider()%>)
			</p>
			<p>&nbsp;</p>
			<h4>Maklumat SMS Dihantar dan Diterima:</h4>
			<p>
				Jumlah SMS Dihantar ke Penjaga :
				<%=totalOutgoingSMS%></p>
			<p>
				Jumlah SMS Dihantar oleh Penjaga :
				<%=totalIncomingSMS%></p>
			<p>
				Jumlah SMS Dihantar ke Penjaga bulan ini :
				<%=monthlyOutgoingSMS%></p>
			<p>
				Jumlah SMS Dihantar oleh Penjaga bulan ini :
				<%=monthlyIncomingSMS%></p>
			<%
				} else {
			%>
			<p>Tiada sebarang langanan SMS.</p>
			<%
				}
			%>
		</div>
	</div>
	<%
		}
		}
	%>
</div>