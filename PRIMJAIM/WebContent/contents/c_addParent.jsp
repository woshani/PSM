<%@page import="controller.URLSecurity"%>
<%
	String studentIdEnc = request.getParameter("id");
%>
<hr></hr>
<p>
	<font color="red">*</font> <font color="black"> Maklumat Wajib
		diisi.</font>
</p>
<br />

<form id="AddParentForm" method="post" action="AddParent" onsubmit="return addParentFormValidation();">

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
					maxlength="12" required tabindex="1" />
				<td width="35" align="left">
					<div id="divCheckIc" name="divCheckIc"
						style="width: 35px; height: 35px; display: none;">
						<img style="display: block; width: 100%; height: 100%;"
							src="images/good.png" alt=""></img>
					</div>
				</td>
				<td width="405" align="left"><input type="button" value="SEMAK"
					name="checkGuardianBtn"
					onclick="loadCheckGuardianIc(guardianIc.value)" class="blackbutton"></input></td>
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
		<div id="infoPenjaga" name="infoPenjaga"></div>

		<br></br>
	</fieldset>
	</br>
	<div align="center">
		<input type="hidden" value="<%=studentIdEnc%>" name="studentIdEnc"
			id="studentIdEnc"> <input type="submit" name="addParentBtn"
			value="Simpan Maklumat" 
			class="blackbutton" />
	</div>

</form>

