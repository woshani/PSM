<%@page import="java.sql.*"%>
<%@page import="connection.DBConnection" %>
<%
String country=request.getParameter("count");  
 String buffer="<select name='state' onchange='showCity(this.value);'><option value='-1'>Select</option>";  
 try{
	 DBConnection conn = new DBConnection(); 
	 Connection con = conn.getConnection();
 Statement stmt = con.createStatement();  
 ResultSet rs = stmt.executeQuery("Select * from state where countryid='"+country+"' ");  
   while(rs.next()){
   buffer=buffer+"<option value='"+rs.getString(1)+"'>"+rs.getString(3)+"</option>";  
   }  
 buffer=buffer+"</select>";  
 response.getWriter().println(buffer); 
 }
 catch(Exception e){
     System.out.println(e);
 }

 %>