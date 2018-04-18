<%@page import="java.util.ArrayList"%>
<%@page import="facade.ControllerWrapper"%>
<%@page import="bean.GuardianContact"%>
<%@page import="controller.URLSecurity"%>
<%@page import="bean.Guardian"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	Guardian guardian = (Guardian) request.getAttribute("guardian");

	String pinKey = (String) session.getAttribute("pinKey");

	String guardianIdEnc = URLSecurity.encryptFinal(
			guardian.getGuardian_id(), pinKey);

	ArrayList<GuardianContact> contacts = new ArrayList<GuardianContact>();
	ControllerWrapper ctrlWrap = new ControllerWrapper();

	contacts = ctrlWrap.getAllGuardianContacts(guardian
			.getGuardian_id());
%>
<table width="100%" border="0" cellpadding="5">
	<tr height="20">
		<td width="500" align="center"><font color="red" size="2">
				*</font> <strong>Maklumat PENJAGA TELAH DIREKOD. Sila KEMASKINI
				Maklumat Penjaga dibawah JIKA TERDAPAT PERUBAHAN.</strong></td>
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
		<td width="300" align="left"><p><%=guardian.getName()%></p>
			<input type="hidden"
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
		<td align="left"><p>LELAKI</p>
			<input type="hidden" id="gender" name="gender"
			value="L">
		</td>
		<%
			} else if (guardian.getGender().equalsIgnoreCase("P")) {
		%>
		<td align="left"><p>PEREMPUAN</p>
			<input type="hidden" id="gender" name="gender"
			value="P"></td>
		<%
			}
		%>

	</tr>
	<tr height="20">
		<td align="right"><font color="red">*</font><strong>Pekerjaan</strong></td>
		<td width="5" align="center">:</td>
		<td align="left"><p><%=guardian.getOccupation()%></p><input type="hidden" required
			style="width: 300px;" value="<%=guardian.getOccupation()%>"
			id="occupation" name="occupation"></td>
	</tr>
	
	<tr height="20">
		<td align="right"><font color="red">* </font><strong>Alamat</strong></td>
		<td align="center" width="5px">:</td>
		<td align="left"><p><%=guardian.getAddress()%></p>
			<input type="hidden" id="guardianAddress"
				name="guardianAddress" value="<%=guardian.getAddress()%>" /></td>
	</tr>
	<tr height="20">
		<td align="right"><font color="red">* </font><strong>Bandar</strong></td>
		<td width="5" align="center">:</td>
		<td align="left"><p><%=guardian.getCity()%></p><input type="hidden" required
			style="width: 300px;" value="<%=guardian.getCity()%>" id="city"
			name="city"></td>
	</tr>
	<tr height="20">
		<td align="right"><font color="red">* </font><strong>Poskod</strong></td>
		<td width="5" align="center">:</td>
		<td align="left"><p><%=guardian.getPostcode()%></p><input type="hidden" name="postcode"
			id="postcode" value=<%=guardian.getPostcode()%> style="width:50px; text-align:center;"> </td>
	</tr> 
	<tr height="20">
		<td align="right"><font color="red">* </font><strong>Negeri</strong></td>
		<td width="5" align="center">:</td>
		<td align="left"><p><%=guardian.getState()%></p><input type="hidden" style="width: 300px;" id="state"
			name="state" value="<%=guardian.getState()%>"/></td>
	</tr>
	<tr height="20">
		<td align="right"><font color="red">* </font><strong>Hubungan</strong></td>
		<td width="5" align="center">:</td>
		<td align="left"><p><%=guardian.getRelationship()%></p>
			<input type="hidden" style="width: 300px;" id="relationship"
			name="relationship" value="<%=guardian.getRelationship()%>"/></td>
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
				

					<%
					if(!contacts.isEmpty()){
										
						String phone1 = "";
						String phone2 = "";

						for (int i = 0; i < contacts.size(); i++) {
							if (contacts.get(i).getPhone_number().length() == 10) {
								phone1 = contacts.get(i).getPhone_number().substring(0, 3);
								phone2 = contacts.get(i).getPhone_number().substring(3, 10);
							} else if (contacts.get(i).getPhone_number().length() == 11) {
								phone1 = contacts.get(i).getPhone_number().substring(0, 4);
								phone2 = contacts.get(i).getPhone_number().substring(4, 11);
							}else{
								phone1 = "010";
								phone2 = "0";
							}
							
					
					%>
					<tr class="d0">
					<td width="10%" align="center">
						<%=i+1%>
					</td>
					<td width="20%" align="center"><select id="telco" name="telco">
					<%
					
					String[] telcos = {"CELCOM","MAXIS","DIGI","UMOBILE","LAIN-LAIN"};
					
					for(int z=0; z<telcos.length; z++){
						if(contacts.get(i).getProvider().contentEquals(telcos[z])){
							%><option value="<%=contacts.get(i).getProvider()%>" selected="selected"><%=contacts.get(i).getProvider()%></option><%
						}else if (contacts.get(i).getProvider().contentEquals("-")&&telcos[z].contentEquals("LAIN-LAIN")){
							%><option value="LAIN-LAIN" selected="selected">LAIN-LAIN</option><%
						}else{
							%><option value="<%=telcos[z]%>"><%=telcos[z]%></option><%
						}
					}
					
					%>
					</select></td>
					<td width="15%" align="center"><select id="smsStatus"
						name="smsStatus">
							<%
								if(contacts.get(i).getSubscription().contentEquals("Y")){
									%><option value="Y" selected="selected">AKTIF</option>
									<option value="N"> TIDAK AKTIF</option>
									<%
								}else{
									%><option value="N" selected="selected">TIDAK AKTIF</option>
									<option value="Y"> AKTIF</option><%
								}
							%>
					</select></td>
					<td width="35%" align="center">+6 
					<select id="guardianPhoneNo1" name="guardianPhoneNo1">
					<%
						String[] phone1s = {"010","011", "012", "013", "014", "015","016", "017", "018", "019", "0111","0112","0113","0114","0115"};
					
						for(int y=0;y<phone1s.length;y++){
							
							if(phone1.contentEquals(phone1s[y])){
								%><option value="<%=phone1%>" selected="selected"><%=phone1%></option><%
							}else{
								%><option value="<%=phone1s[y]%>"><%=phone1s[y]%></option><%
							}
						}
					
					%>
					</select> <input type="text" id="guardianPhoneNo2" name="guardianPhoneNo2"
						size="9" maxlength="7" style="text-align: center;" value="<%=phone2%>" required /></td>
					<td width="20%" align="center"><input type="button"
						class="blackbutton" value="Padam" onclick="deleteRow(this)">
						<input type="hidden" id="guardianContactId" name="guardianContactId" value="<%=contacts.get(i).getGcontact_id()%>"></input>
						<input type="hidden" id="gcGuardianId" name="gcGuardianId" value=""></input>
					</td>
					</tr>
					<%
						}
					}else{
					%>
					<tr class="d0">
					<td width="10%" align="center">1
					</td>
					<td width="20%" align="center"><select id="telco" name="telco">
							<option value="CELCOM">CELCOM</option>
							<option value="MAXIS">MAXIS</option>
							<option value="DIGI">DIGI</option>
							<option value="U-MOBILE">U-MOBILE</option>
							<option value="LAIN-LAIN">LAIN-LAIN</option>
					</select></td>
					<td width="15%" align="center"><select id="smsStatus"
						name="smsStatus">
							<option value="AKTIF">AKTIF</option>
							<option value="TIDAK AKTIF">TIDAK AKTIF</option>
					</select></td>
					<td width="35%" align="center">+6 <select
						id="guardianPhoneNo1" name="guardianPhoneNo1">
							<option value="011">011</option>
							<option value="012">012</option>
							<option value="013">013</option>
							<option value="014">014</option>
							<option value="015">015</option>
							<option value="016">016</option>
							<option value="017">017</option>
							<option value="018">018</option>
							<option value="019">019</option>
							<option value="0111">0111</option>
							<option value="0112">0112</option>
							<option value="0113">0113</option>
							<option value="0114">0114</option>
							<option value="0115">0115</option>
					</select> <input type="text" id="guardianPhoneNo2" name="guardianPhoneNo2"
						size="9" maxlength="7" style="text-align: center;" required /></td>
					<td width="20%" align="center"><input type="button"
						class="blackbutton" value="Padam" onclick="deleteRow(this)">
						<input type="hidden" id="guardianContactId" name="guardianContactId" value=""></input>
						<input type="hidden" id="gcGuardianId" name="gcGuardianId" value=""/></input>
					</td>
					</tr>
					<%	
					}
					%> 
				

			</table>
		</td>
	</tr>
	<tr>
		<td align="left"><input type="hidden" id="guardianIdEnc"
			name="guardianIdEnc" value="<%=guardianIdEnc%>"></input></td>
		<td align="left"><input type="hidden" id="guardianICStated"
			name="guardianICStated" value="<%=guardian.getIc()%>"></input> <input
			type="hidden" id="statusPenjaga" name="statusPenjaga" value="1"></input></td>
	</tr>

</table>
