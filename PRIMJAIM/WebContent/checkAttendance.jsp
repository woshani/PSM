<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="controller.URLSecurity"%>
<%@page import="facade.ControllerWrapper"%>

<%
	String pinKey = String.valueOf(session.getAttribute("pinKey"));
	String month = request.getParameter("month");
	String day = request.getParameter("day");
	String dbDay = null;
	String year = request.getParameter("year");
	String actualMonth = request.getParameter("actualMonth"); 
	
	int result = 0;
	int compareDate = 0;
	
	ControllerWrapper controller = new ControllerWrapper();
	
	if(Integer.parseInt(day) < 10){
		dbDay = "0" + day;
	}else{
		dbDay = day;
	}
	
	compareDate = controller.compareDate(dbDay, year+month);
	
	if(request.getParameter("id") != null && request.getParameter("studentId") == null){
		
		String classIdEnc = request.getParameter("id");
		String classId = URLSecurity.decryptFinal(request.getParameter("id"), pinKey) ;
		
		result = controller.attendanceAccess(classId, dbDay, year+month);
		
		out.print("Result : "+result);
		if(result == 1){
			RequestDispatcher reqDispatcher = request
					.getRequestDispatcher("updateAttendance.jsp?id="+classIdEnc+"&month="+month+"&day="+day+"&year="+year);
			reqDispatcher.forward(request, response);
		}else if(result == 0){
			RequestDispatcher reqDispatcher = request
					.getRequestDispatcher("attendanceReport.jsp?id="+classIdEnc+"&month="+actualMonth+"&alert="+compareDate);
			reqDispatcher.forward(request, response);
		}
		
	}else if(request.getParameter("studentId") != null && request.getParameter("id") != null){
		
		String studentIdEnc = request.getParameter("studentId");
		String studentId = URLSecurity.decryptFinal(studentIdEnc, pinKey);
		
		String classIdEnc = request.getParameter("id");
		String classId = URLSecurity.decryptFinal(classIdEnc, pinKey);
		out.print("test");
		result = controller.attendanceAccessStudent(studentId, classId, dbDay, year+month);
		
		
		if(result == 1){
			RequestDispatcher reqDispatcher = request
					.getRequestDispatcher("updateAttendance.jsp?studentId="+studentIdEnc+"&id="+classIdEnc+"&month="+month+"&day="+day+"&year="+year);
			reqDispatcher.forward(request, response);
		}else if(result == 0){
			day = null;
			RequestDispatcher reqDispatcher = request
					.getRequestDispatcher("updateAttendance.jsp?studentId="+studentIdEnc+"&id="+classIdEnc+"&month="+month+"&day="+day+"&year="+year+"&alert="+compareDate);
			reqDispatcher.forward(request, response);
		}
	}
	
	
	

%>