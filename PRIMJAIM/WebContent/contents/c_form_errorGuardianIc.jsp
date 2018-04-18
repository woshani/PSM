<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	int error = (Integer) request.getAttribute("status");

	// data student ic = null
	if (error == 0) {
%>
<table width="100%" border="0" cellpadding="5">
	<tr height="20">
		<td width="500" align="center"><strong>Kesalahan Dikesan
				: </strong><font color="red" size="2"> TIADA DATA dimasukkan untuk pengesahan.
				Sila masukkan Nombor Kad Pengenalan IBUBAPA / Penjaga untuk tujuan semakan
				didalam sistem.</font></td>
	</tr>
</table>
<%
	// data student ic = not have 12 char.
	} else if (error == 101) {
%>

<table width="100%" border="0" cellpadding="5">
	<tr height="20">
		<td width="500" align="center"><strong>Kesalahan Dikesan
				: </strong><font color="red" size="2"> Nombor Kad Pengenalan IBUBAPA / Penjaga yang dimasukkan TIDAK BETUL (Kurang 12 aksara). Sila semak semula!</font></td>
	</tr>
</table>
<%
	}
%>
