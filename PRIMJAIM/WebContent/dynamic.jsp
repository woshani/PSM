<%

// dynamic.jsp source
// result returned as comma separated string  which is parsed by Javascript and added in the combo box 
//  This page can be changed to query the database or read data from file depending on the input parameter
String input= request.getParameter("params");
String teacherId = request.getParameter("id");
if(input.equals("I"))
 out.print("http://www.gmail.com,http://www.orkut.com,http://www.google.com");
else if(input.equals("C"))
 out.print("http://www.yahoo.com,http://mail.yahoo.com");
else if(input.equals("S"))
 out.print("http://hotmail.com,http://www.microsoft.com");
else
 out.print("No data found");
%>
