<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%

	String month = "0";
	String day = "1";

%>
<title>Insert title here</title>
	<script type="text/javascript">
	function confirm(status,day){
		
		if (status==1) {
		  return true;
		}else if(status == 0) {
			alert("Kedatangan Sudah Diambil");
			return false;
		}
		
	}
	</script>
</head>

<body>

 <a
			href="takeAttendance.jsp?id=<%=%>"
			onclick="return confirm(<%=month%>,<%=day %>)"
			class="blackbutton">Merekod Kehadiran
			Harian</a>

</body>
</html>