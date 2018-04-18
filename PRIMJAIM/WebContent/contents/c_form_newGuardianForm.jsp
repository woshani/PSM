<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table width="100%" border="0" cellpadding="5">
	<tr height="20">
		<td width="500" align="center"><font color="red" size="2">
				*</font> <strong>Maklumat PENJAGA BARU. Sila lengkapkan Borang
				Maklumat Penjaga dibawah.</strong></td>
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
			placeholder="Seperti di dalam MyKad">></td>
	</tr>
	<tr>
		<td align="right"><font color="red">*</font><strong>
				Jantina</strong></td>
		<td align="center">:</td>

		<td align="left"><input type="radio" id="gender" name="gender"
			value="L" checked> Lelaki<br> <input type="radio"
			id="gender" name="gender" value="P"> Perempuan<br></td>
	</tr>
	<tr height="20">
		<td align="right"><font color="red">*</font><strong>Pekerjaan</strong></td>
		<td width="5" align="center">:</td>
		<td align="left"><input type="text" required
			style="width: 300px;" id="occupation" name="occupation"></td>
	</tr>
	
	<tr height="20">
		<td align="right"><font color="red">* </font><strong>Alamat</strong></td>
		<td align="center" width="5px">:</td>
		<td align="left"><textarea id="guardianAddress"
				name="guardianAddress" maxlength="148" rows="5" cols="50" required></textarea><br />
			(Sila masukkan tanda "-" jika tiada maklumat)</td>
	</tr>
	<tr height="20">
		<td align="right"><font color="red">* </font><strong>Bandar</strong></td>
		<td width="5" align="center">:</td>
		<td align="left"><input type="text" required
			style="width: 300px;" id="city" name="city"></td>
	</tr>
	<tr height="20">
		<td align="right"><font color="red">* </font><strong>Poskod</strong></td>
		<td width="5" align="center">:</td>
		<td align="left"><input type="text" name="postcode"
			id="postcode" style="width:55px; text-align:center;" maxlength="5" required> </td>
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
		<td align="left"><select style="width: 300px;" id="relationship"
			name="relationship">
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
						<input type="hidden" id="guardianContactId" name="guardianContactId" values=""/>
						<input type="hidden" id="gcGuardianId" name="gcGuardianId" values=""/>
					</td>
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
</td>
</tr>
<tr>
	<td align="left"><input type="hidden" id="guardianICStated"
		name="guardianICStated"></input> <input type="hidden"
		id="statusPenjaga" name="statusPenjaga" value="0"></input></td>
</tr>

</table>