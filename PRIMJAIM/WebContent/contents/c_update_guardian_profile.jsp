<%@page import="bean.GuardianContact"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.URLSecurity"%>
<%@page import="facade.ControllerWrapper"%>
<%@page import="bean.Guardian"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<style>
tr.d0 td {
	background-color: #FFF;
	color: black;
}

tr.d1 td {
	background-color: #F8F8F8;
	color: black;
}

.on {
	background-color: #c6d6e5;
	cursor: pointer;
}

.off0 {
	background-color: #FDD7DD;
}

.off1 {
	background-color: #FFF;
}
</style>



<%
	try {
		String pinKey = String.valueOf(session.getAttribute("pinKey"));

		String guardianIdEnc = request.getParameter("id");
		String guardianId = URLSecurity.decryptFinal(guardianIdEnc,
				pinKey);

		ControllerWrapper ctrlWrap = new ControllerWrapper();

		Guardian guardian = ctrlWrap.getGuardianById2(guardianId);

		String status = request.getParameter("status");

		if (guardian.getGuardian_id() != null) {
			
			ArrayList<GuardianContact> contacts = new ArrayList<GuardianContact>();
			contacts = ctrlWrap.getAllGuardianContacts(guardian.getGuardian_id());
%>
<script type="text/javascript">
	
</script>

<hr></hr>
<p>
	<font color="red">*</font> <font color="black"> Maklumat Wajib
		diisi.</font>
</p>
<br />

<form id="UpdateGuardianForm" method="POST"
	onsubmit="return updateGuardianFormValidation();"
	action="UpdateGuardian">

	<fieldset>

		<legend>
			<font color="black">Maklumat Ibu Bapa / Penjaga</font>
		</legend>

		<table width="100%" border="0" cellpadding="5">
			<tr height="20">
				<td width="265" align="right"><font color="red">*</font> <strong>Nombor
						MyKad Penjaga</strong></td>
				<td width="5" align="center">:</td>
				<td width="150" align="left"><input type="text"
					style="text-align: center;" name="guardianIc" id="guardianIc"
					maxlength="12" value="<%=guardian.getIc()%>" required tabindex="1" />
				<td width="35" align="left">
					<div id="divCheckIc" name="divCheckIc"
						style="width: 35px; height: 35px; display: none;">
						<img style="display: block; width: 100%; height: 100%;"
							src="images/good.png" alt=""></img>
					</div>
				</td>
				<td width="405" align="left"><input type="button" value="SEMAK"
					name="checkGuardianBtn" id="checkGuardianBtn"
					onclick="loadCheckGuardianIc(guardianIc.value, guardianIdEnc.value)"
					class="blackbutton"></input></td>
			</tr>
			<tr>
				<td><input type="hidden" id="semakBtnStatus"
					name="semakBtnStatus" value="0"></input></td>
				<td></td>
				<td>(contoh: 750112109999)</td>
				<td></td>
			</tr>
		</table>

		<br> </br>
		<div id="infoPenjaga" name="infoPenjaga">
			<table width="100%" border="0" cellpadding="5">
				<tr height="20">
					<td width="500" align="center"><font color="red" size="2">
							*</font> <strong>Sila KEMASKINI Maklumat Penjaga JIKA TERDAPAT
							PERUBAHAN.</strong></td>
				</tr>
				<tr>
					<td><hr></hr></td>
				</tr>
			</table>
			<br></br>

			<table width="100%" border="0" cellpadding="5">
				<tr height="20">
					<td width="250" align="right"><font color="red">*</font> <strong>Nama
							Penjaga</strong></td>
					<td width="5" align="center">:</td>
					<td width="300" align="left"><input type="text" required
						style="width: 550px;" id="guardianName" name="guardianName"
						value="<%=guardian.getName()%>"></td>
				</tr>
				<tr>
					<td align="right"><font color="red">*</font><strong>
							Jantina</strong></td>
					<td align="center">:</td>
					<%
						if (guardian.getGender().equalsIgnoreCase("L")) {
					%>
					<td align="left"><input type="radio" id="gender" name="gender"
						value="L" checked> Lelaki<br> <input type="radio"
						id="gender" name="gender" value="P"> Perempuan<br></td>
					<%
						} else if (guardian.getGender().equalsIgnoreCase("P")) {
					%>
					<td align="left"><input type="radio" id="gender" name="gender"
						value="L"> Lelaki<br> <input type="radio" id="gender"
						name="gender" value="P" checked> Perempuan<br></td>
					<%
						}
					%>

				</tr>
				<tr height="20">
					<td align="right"><font color="red">*</font><strong>Pekerjaan</strong></td>
					<td width="5" align="center">:</td>
					<td align="left"><input type="text" required
						style="width: 300px;" value="<%=guardian.getOccupation()%>"
						id="occupation" name="occupation"></td>
				</tr>
				<tr height="20">
					<td align="right"><font color="red">* </font><strong>Tanggungan</strong></td>
					<td width="5" align="center">:</td>
					<td align="left"><input type="text" required
						style="width: 300px;" value="<%=guardian.getDependent()%>"
						id="dependent" name="dependent"></td>
				</tr>

				<tr height="20">
					<td align="right"><font color="red">* </font><strong>Alamat</strong></td>
					<td align="center" width="5px">:</td>
					<td align="left"><textarea id="guardianAddress"
							name="guardianAddress" maxlength="148" rows="5" cols="50"
							required><%=guardian.getAddress()%></textarea><br /> (Sila
						masukkan tanda "-" jika tiada maklumat)</td>
				</tr>

				<tr height="20">
					<td align="right"><font color="red">* </font><strong>Bandar</strong></td>
					<td width="5" align="center">:</td>
					<td align="left"><input type="text" required
						style="width: 300px;" value="<%=guardian.getCity()%>" id="city"
						name="city"></td>
				</tr>
				<tr height="20">
					<td align="right"><font color="red">* </font><strong>Poskod</strong></td>
					<td width="5" align="center">:</td>
					<td align="left"><input type="text" required
						style="width: 300px;" value="<%=guardian.getPostcode()%>"
						id="postcode" name="postcode" maxlength="5"></td>
				</tr>
				<tr height="20">
					<td align="right"><font color="red">* </font><strong>Negeri</strong></td>
					<td width="5" align="center">:</td>
					<td align="left"><select id="state" name="state">
							<option value="JOHOR">JOHOR</option>
							<option value="KEDAH">KEDAH</option>
							<option value="KELANTAN">KELANTAN</option>
							<option value="MELAKA" selected="selected">MELAKA</option>
							<option value="NEGERI SEMBILAN">NEGERI SEMBILAN</option>
							<option value="PAHANG">PAHANG</option>
							<option value="PERAK">PERAK</option>
							<option value="PERLIS">PERLIS</option>
							<option value="PULAU PINANG">PULAU PINANG</option>
							<option value="SABAH">SABAH</option>
							<option value="SARAWAK">SARAWAK</option>
							<option value="SELANGOR">SELANGOR</option>
							<option value="TERENGGANU">TERENGGANU</option>
							<option value="WILAYAH PERSEKUTUTAN KUALA LUMPUR">WILAYAH
								PERSEKUTUTAN KUALA LUMPUR</option>
							<option value="WILAYAH PERSEKUTUTAN LABUAN">WILAYAH
								PERSEKUTUTAN LABUAN</option>
							<option value="WILAYAH PERSEKUTUTAN PUTRAJAYA">WILAYAH
								PERSEKUTUTAN PUTRAJAYA</option>
					</select></td>
				</tr>
				<tr height="20">
					<td align="right"><font color="red">* </font><strong>Hubungan</strong></td>
					<td width="5" align="center">:</td>
					<td align="left"><select style="width: 300px;"
						id="relationship" name="relationship">
							<option value="MOTHER" selected="selected">IBU</option>
							<option value="FATHER">BAPA</option>
							<option value="GUARDIAN">PENJAGA</option>
					</select></td>
				</tr>
				<tr>
					<td align="right"><font color="red">* </font><strong>No
							Telefon :</strong></td>
					<td width="5" align="center">:</td>
					</td>
					<td colspan="1">
						<table width="100%" border="0" cellpadding="5"
							id="tblGuardianContact">
							<tr>
								<th width="10%">No.</th>
								<th width="20%">Nama Telco</th>
								<th width="15%">SMS Servis</th>
								<th width="35%">Nombor Telefon</th>
								<th width="20%"><input type="button" class="blackbutton"
									value="Tambah" onclick="addRow()"></th>
							</tr>
							<tr class="d0">

								<%
									String phone1 = "";
											String phone2 = "";

											for (int i = 0; i < contacts.size(); i++) {
												if (contacts.get(i).getPhone_number().length() == 10) {
													phone1 = contacts.get(i).getPhone_number()
															.substring(0, 3);
													phone2 = contacts.get(i).getPhone_number()
															.substring(3, 10);
												} else if (contacts.get(i).getPhone_number().length() == 11) {
													phone1 = contacts.get(i).getPhone_number()
															.substring(0, 4);
													phone2 = contacts.get(i).getPhone_number()
															.substring(4, 11);
												} else {
													phone1 = "010";
													phone2 = "0";
												}
								%>

								<td width="10%" align="center"><%=i + 1%> <%=contacts.get(i).getPhone_number() %></td>
								<td width="20%" align="center"><select id="telco"
									name="telco">
										<%
											String[] telcos = { "CELCOM", "MAXIS", "DIGI",
																"UMOBILE", "LAIN-LAIN" };

														for (int z = 0; z < telcos.length; z++) {
															if (contacts.get(i).getProvider()
																	.contentEquals(telcos[z])) {
										%><option value="<%=contacts.get(i).getProvider()%>"
											selected="selected"><%=contacts.get(i).getProvider()%></option>
										<%
											} else if (contacts.get(i).getProvider()
																	.contentEquals("-")
																	&& telcos[z].contentEquals("LAIN-LAIN")) {
										%><option value="LAIN-LAIN" selected="selected">LAIN-LAIN</option>
										<%
											} else {
										%><option value="<%=telcos[z]%>"><%=telcos[z]%></option>
										<%
											}
														}
										%>
								</select></td>
								<td width="15%" align="center"><select id="smsStatus"
									name="smsStatus">
										<%
											if (contacts.get(i).getSubscription()
																.contentEquals("Y")) {
										%><option value="Y" selected="selected">AKTIF</option>
										<option value="N">TIDAK AKTIF</option>
										<%
											} else {
										%><option value="N" selected="selected">TIDAK AKTIF</option>
										<option value="Y">AKTIF</option>
										<%
											}
										%>
								</select></td>
								<td width="35%" align="center">+6 <select
									id="guardianPhoneNo1" name="guardianPhoneNo1">
										<%
											String[] phone1s = { "010", "011", "012", "013", "014",
																"015", "016", "017", "018", "019", "0111",
																"0112", "0113", "0114", "0115" };

														for (int y = 0; y < phone1s.length; y++) {

															if (phone1.contentEquals(phone1s[y])) {
										%><option value="<%=phone1%>" selected="selected"><%=phone1%></option>
										<%
											} else {
										%><option value="<%=phone1s[y]%>"><%=phone1s[y]%></option>
										<%
											}
														}
										%>
								</select> <input type="text" id="guardianPhoneNo2"
									name="guardianPhoneNo2" size="9" maxlength="7"
									style="text-align: center;" value="<%=phone2%>" required /></td>
								<td width="20%" align="center"><input type="button"
									class="blackbutton" value="Padam" onclick="deleteRow(this)"></td>
								<%
									}
								%>
							</tr>

						</table>
					</td>
				</tr>
				<tr>

				</tr>
				<tr>
					<td align="left">&nbsp</td>

				</tr>
				<tr>
					<td align="left">&nbsp</td>

				</tr>
				<tr>
					<td align="left">&nbsp</td>

				</tr>

			</table>
		</div>
	</fieldset>
	</br>
	<div align="center">

		<td align="left"><input type="hidden" id="guardianICStated"
			name="guardianICStated" /> <input type="hidden"
			value="<%=guardianIdEnc%>" name="guardianIdEnc" id="guardianIdEnc" />
			<input type="submit" name="updateGuardianBtn" value="Simpan Maklumat"
			class="blackbutton" />
	</div>

</form>
<%
	} else {
%>
<p>
	Maklumat IbuBapa / Penjaga <b><font color="red">TIADA</font></b> dalam
	sistem maklumat.
</p>
<%
	}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
%>
</div>
