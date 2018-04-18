
<%@page import="bean.Instituition"%>
<%@page import="facade.ControllerWrapper"%>
<%@page import="controller.URLSecurity"%>
<%@page import="java.security.InvalidKeyException"%>
<%@page import="javax.crypto.NoSuchPaddingException"%>
<%@page import="java.security.NoSuchAlgorithmException"%>
<%
	try {
		String pinKey = String.valueOf(session.getAttribute("pinKey"));

		String institutionIdEnc = request.getParameter("id");
				
		String institutionId = URLSecurity.decryptFinal(
				institutionIdEnc, pinKey);

		ControllerWrapper controller = new ControllerWrapper();

		Instituition institution = new Instituition();

		institution = controller.getInstituitionById(institutionId);

		if (institution.getAcademic_instituition_id() != null) {
%>
<script>
$(document).ready(function () {	
	$('input[type=file]').change(function () {
		var val = $(this).val().toLowerCase();   
		var regex  =  new RegExp("(.*?).(jpg|jpeg|png)$");
		if(!(regex.test(val)))
		{
			$(this).val(''); 
			alert('Format gambar logo yang dimasukkan TIDAK SAH! Sila masukkan gambar berformat .jpg .jpeg .png sahaja.');
		}
    });
});

</script>

<div id="accordion" style="width: 1100px;">
	<h3>KEMASKINI MAKLUMAT INSTITUSI</h3>

	<div>
		<font color="red">* </font>Maklumat yang WAJIB diisi. <br /> <br />
		<br />

		<form id="UpdateInstitutionForm" method="post"
			action="UpdateInstitution" enctype="multipart/form-data">
			<table width="1000px" border="0" cellpadding="5">
				<tr>
					<td width="200px"><font color="red">* </font><strong>NAMA
							INSTITUSI/SEKOLAH </strong></td>
					<td width="5px">:</td>
					<td><input type="text" id="nama" name="nama"
						style="width: 500px;" value="<%=institution.getAcademic_name()%>"
						required></input></td>
				</tr>
				<tr>
					<td><font color="red">* </font><strong>ALAMAT </strong></td>
					<td width="5px">:</td>
					<td><input type="text" id="alamat" name="alamat" style="width: 700px;"
						value="<%=institution.getAddress()%>" required> </input><br></td>
				</tr>
				<tr>
					<td><font color="red">* </font><strong>POSKOD</strong></td>
					<td>:</td>
					<td><input type="text" id="poskod" name="poskod"
						value="<%=institution.getPostcode()%>" maxlength="5" required></input>
						(contoh: 76100)</td>
				</tr>
				<tr>
					<td><font color="red">* </font><strong>BANDAR</strong></td>
					<td width="5px">:</td>
					<td><select name="bandar" required>
							<option selected="selected" value="<%=institution.getCity()%>"><%=institution.getCity()%></option>
							<option value="Ayer Keroh">Ayer Keroh</option>
							<option value="Asahan">Asahan</option>
							<option value="Batang Melaka">Batang Melaka</option>
							<option value="Batu Berendam">Batu Berendam</option>
							<option value="Bemban">Bemban</option>
							<option value="Bukit Beruang">Bukit Beruang</option>
							<option value="Bukit Katil">Bukit Katil</option>
							<option value="Cheng">Cheng</option>
							<option value="Durian Tunggal">Durian Tunggal</option>
							<option value="Hang Tuah Jaya">Hang Tuah Jaya</option>
							<option value="Klebang">Klebang</option>
							<option value="Krubong">Krubong</option>
							<option value="Kuala Sungai Baru">Kuala Sungai Baru</option>
							<option value="Lendu">Lendu</option>
							<option value="Lubuk China">Lubuk China</option>
							<option value="Machap Baru">Machap Baru</option>
							<option value="Masjid Tanah">Masjid Tanah</option>
							<option value="Melaka Pindah">Melaka Pindah</option>
							<option value="Merlimau">Merlimau</option>
							<option value="Naning">Naning</option>
							<option value="Nyalas">Nyalas</option>
							<option value="Pulau Sebang">Pulau Sebang</option>
							<option value="Ramuan China">Ramuan China</option>
							<option value="Selandar">Selandar</option>
							<option value="Serkam">Serkam</option>
							<option value="Simpang Ampat">Simpang Ampat</option>
							<option value="Sungai Rambai">Sungai Rambai</option>
							<option value="Sungai Udang">Sungai Udang</option>
							<option value="Tampin">Tampin</option>
							<option value="Tanjung Bidara">Tanjung Bidara</option>
							<option value="Tanjung Kling">Tanjung Kling</option>
							<option value="Tanjung Tuan">Tanjung Tuan</option>
							<option value="Telok Mas">Telok Mas</option>
							<option value="Umbai">Umbai</option>
							<option value="Lain-lain">Lain-lain</option>
					</select></td>
				</tr>
				<tr>
					<td><font color="red">* </font><strong>DAERAH</strong></td>
					<td width="5px">:</td>
					<td><select name="daerah" required>
							<option selected="selected"
								value="<%=institution.getDistrict()%>"><%=institution.getDistrict()%></option>
							<option value="Alor Gajah">Alor Gajah</option>
							<option value="Jasin">Jasin</option>
							<option value="Melaka Tengah">Melaka Tengah</option>
					</select></td>
				</tr>
				<tr>
					<td><font color="red">* </font><strong>PEJABAT
							PELAJARAN DAERAH </strong></td>
					<td>:</td>
					<td><%=institution.getPpd()%><input type="hidden" id="ppd"
						name="ppd" value="<%=institution.getPpd()%>" required></input></td>

				</tr>
				<tr>
					<td><font color="red">* </font><strong>NO. TELEFON </strong></td>
					<td>:</td>
					<td><input type="text" id="telefon" name="telefon"
						value="<%=institution.getTelephone_number()%>"></input></td>
				</tr>
				<tr>
					<td><strong>NO. FAX </strong></td>
					<td>:</td>
					<td><input type="text" id="fax" name="fax"
						value="<%=institution.getFax_number()%>"></input></td>

				</tr>
				<tr>
					<td><font color="red">* </font><strong>LOGO</strong></td>
					<td>: &nbsp</td>
					<td><input type="file" name="logo" id="logo" size="50" required/></td>
					<td><label name="logoStatus" value=""></label></td>
				</tr>
				<tr>
					<td><input type="hidden" id="institutionId"
						name="institutionId"
						value="<%=institution.getAcademic_instituition_id()%>"></input></td>
					<td></td>
					<td align="right"><input type="submit"
						name="updateInstitution" value="Kemaskini" class="blackbutton" /></td>
				</tr>
			</table>
		</form>
	</div>

</div>
<%
	} else {
%>
<p>
	Maklumat INSTITUSI <b><font color="red">TIADA</font></b> tiada dalam
	sistem.
</p>
<%
	}
	} catch (NoSuchAlgorithmException e) {
		System.out.println("No Such Algorithm:" + e.getMessage());
		return;
	} catch (NoSuchPaddingException e) {
		System.out.println("No Such Padding:" + e.getMessage());
		return;
	} catch (InvalidKeyException e) {
		System.out.println("Invalid Key:" + e.getMessage());
		return;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
%>

