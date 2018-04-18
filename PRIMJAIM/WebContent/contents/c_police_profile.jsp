<%@page import="bean.Station"%>
<%@page import="controller.URLSecurity"%>
<%@page import="facade.ControllerWrapper"%>
<%@page import="bean.Police"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>

<div class="entry">
	<%
		String pinKey = (String) session.getAttribute("pinKey");

		if (request.getParameter("id") != null) {
			String policeIdEnc = request.getParameter("id");
			String police_id = URLSecurity
					.decryptFinal(policeIdEnc, pinKey);

			ControllerWrapper controller = new ControllerWrapper();
			Police police = new Police();
			police = controller.getPoliceById(police_id);

			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("MMMMM YYYY");

			String month = ft.format(dNow);

			Station station = new Station();
			station = controller.getStationByPoliceId(police_id);
	%>
	<div id="accordion" style="width: 1100px;">
		<h3>MAKLUMAT ANGGOTA</h3>
		<div>
			<form name="PoliceContactInformation" id="PoliceContactInformation"
				method="post" action="UpdatePoliceContact">
				<table width="1000px" border="0" cellpadding="5">
					<tr>
						<td width="200px"><strong>NAMA</strong></td>
						<td width="5px">:</td>
						<td><%=police.getName()%></td>
					</tr>
					<tr>
						<td width="200px"><strong>NO. TELEFON</strong></td>
						<td width="5px">:</td>
						<td>+6 <input type="text" name="contactNumber"
							id="contactNumber" disabled="disabled"
							value=<%=police.getPhoneNo()%>> <font color="red">*</font>(tanpa
							spacing atau simbol '-') &nbsp<input type="button"
							name="kemaskini"
							onclick="document.PoliceContactInformation.contactNumber.disabled = false; return false;"
							value="KEMASKINI" /> &nbsp &nbsp <input type="submit"
							name="simpan" value="SIMPAN" /></td>
					</tr>
					<input type="hidden" name="policeId" id="policeId"
						value=<%=policeIdEnc%>> 
				</table>
		</div>
		<h3>MAKLUMAT INSTITUSI</h3>
		<div>
			<table width="1000px" border="0" cellpadding="5">
				<tr>
					<td width="200px"><strong>NAMA</strong></td>
					<td width="5px">:</td>
					<td><%=station.getName()%></td>
				</tr>
				<tr>
					<td width="200px"><strong>ALAMAT</strong></td>
					<td width="5px">:</td>
					<td><%=station.getAddress()%></td>
				</tr>
				<tr>
					<td width="200px"><strong>NO. TELEFON</strong></td>
					<td width="5px">:</td>
					<td><%=station.getTelNo()%></td>
				</tr>
			</table>
		</div>
	</div>
	<%
		}
	%>
</div>