<%
try {
	session.invalidate();
	response.sendRedirect("logIn.jsp");
}
catch (Exception e)
{
	out.println(e);
}
%>