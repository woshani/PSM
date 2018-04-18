<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@ page import="bean.Teacher"%>
<%@ page import="facade.ControllerWrapper"%>
<%@ page import="java.util.List"%>
<%@ page import="bean.Classroom"%>
<%@ page import="bean.Student"%>
<%@ page import="bean.Instituition"%>
<%@page import="bean.User"%>

<%
	String count = request.getParameter("count");
	String buffer = "<select name='target'><option value='-1'>SILA PILIH</option>";

	Teacher teacher = new Teacher();

	teacher = (Teacher) session.getAttribute("SASteacher");

	System.out.print("I : " + teacher.getTeacher_id());
	System.out.print("C : " + request.getParameter("count"));

	User user = new User();
	int userLevel = 0;

	ControllerWrapper controller = new ControllerWrapper();

	Instituition instituition = controller
			.getInstituitionById(controller
					.getInstituitionByTeacherId(teacher.getTeacher_id()));
	List<Student> students = null;
	List<Classroom> classrooms = null;

	if (session.getAttribute("SASuser") != null) {
		user = (User) session.getAttribute("SASuser");
	}

	//Start get required array data.
	if (user.getUser_id() != null) {
		userLevel = user.getLevel();

		if (userLevel == 3) {
			classrooms = controller.getClassroomsByTeacherId(teacher
					.getTeacher_id());
			students = controller.getStudentByTeacher(teacher
					.getTeacher_id());
			
		} else if (userLevel > 3) {

			students = controller
						.getStudentsByInstituitionId2(instituition
								.getAcademic_instituition_id());			
			classrooms = controller
						.getClassroomsByInstituitionId(instituition
								.getAcademic_instituition_id());
			
		}
	} else {
		out.print("No data found");
	}
	//End get data

	//Problem Found : Not counter session problem (19062016 Mohamad Idzhar)
	//Start display data in html select list format
	if (count.equals("I")) {

		buffer = buffer + "<option value='"
				+ instituition.getAcademic_instituition_id() + "'>"
				+ instituition.getAcademic_name() + "</option>";
		buffer = buffer + "</select>";
		response.getWriter().println(buffer);
		
	} else if (count.equals("C")) {

		for (Classroom classroom : classrooms) {
			buffer = buffer + "<option value='"
					+ classroom.getClass_id() + "'>["
					+classroom.getType()+"] "+ classroom.getName() + "</option>";
		}
		buffer = buffer + "</select>";
		response.getWriter().println(buffer);
		
	} else if (count.equals("S")) {
		//out.print(students.size());
		int counter = 0;
		for (Student student : students) {
			counter = counter +1;
			
			String txtDisplay = counter+") ["+student.getClassname()+ ","+student.getClassType()+"] " + student.getName();
			
			buffer = buffer + "<option value='"
					+ student.getStudent_id() + "'>"
					+ txtDisplay+ "</option>";
		}
		
		buffer = buffer + "</select>";
		response.getWriter().println(buffer);
	} else {
		out.print("No data found");
	}
	//End display process
%>
