<%@page import="bean.User"%>
<%@page import="bean.Dun"%>
<%@page import="controller.URLSecurity"%>
<%@page import="facade.ControllerWrapper"%>
<%@page import="bean.Politician"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>

<div class="entry">
	<%
		String pinKey = (String) session.getAttribute("pinKey");

		if (request.getParameter("id") != null) {
			String politicianIdEnc = request.getParameter("id");
			String politician_id = URLSecurity.decryptFinal(
					politicianIdEnc, pinKey);

			ControllerWrapper controller = new ControllerWrapper();

			Politician politician = new Politician();
			Dun dun = new Dun();
			User user = new User();

			politician = controller.getPoliticianById(politician_id);
			dun = controller.getDunByPoliticianId(politician.getId());

			user = (User) session.getAttribute("SASuser");

			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("MMMMM YYYY");

			String month = ft.format(dNow);
	%>
	<div id="accordion" style="width: 1100px;">
		<h3>MAKLUMAT AHLI KORPORAT</h3>
		<div>
			<form name="PoliticianContactInformation"
				id="PoliticianContactInformation" method="post"
				action="UpdatePoliticianContact">
				<table width="1000px" border="0" cellpadding="5">
					<tr>
						<td width="200px"><strong>NAMA</strong></td>
						<td width="5px">:</td>
						<td><%=politician.getName()%></td>
					</tr>
					<tr>
						<td width="200px"><strong>NO. TELEFON</strong></td>
						<td width="5px">:</td>
						<td>+6 <input type="text" name="contactNumber"
							id="contactNumber" disabled="disabled"
							value=<%=politician.getPhoneNo()%>> <font color="red">*</font>(tanpa
							spacing atau simbol '-') &nbsp<input type="button"
							name="kemaskini"
							onclick="document.PoliticianContactInformation.contactNumber.disabled = false; return false;"
							value="KEMASKINI" /> &nbsp &nbsp <input type="submit"
							name="simpan" value="SIMPAN" /></td>
					</tr>
					<input type="hidden" name="politicianId" id="politicianId"
						value=<%=politicianIdEnc%>> 
					<%
						if (user.getLevel() == 12) {
					%>
					<tr>
						<td width="200px"><strong>DUN / KOD</strong></td>
						<td width="5px">:</td>
						<td><%=dun.getName()%> (<%=dun.getCode()%>)</td>
					</tr>


					<tr>
						<td width="200px"><strong>PARLIMEN / KOD</strong></td>
						<td width="5px">:</td>
						<td><%=dun.getParlimen().getName()%> (<%=dun.getParlimen().getCode()%>)</td>
					</tr>
					
					<%
						}
					%>


				</table>
		</div>
	</div>
	<%
		}
	%>
</div>