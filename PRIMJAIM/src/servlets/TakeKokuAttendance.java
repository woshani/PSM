package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.KokuAttendance;
import bean.Meeting;
import bean.Student;
import facade.ControllerWrapper;

/**
 * Servlet implementation class TakeKokuAttendance
 */
@WebServlet("/TakeKokuAttendance")
public class TakeKokuAttendance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeKokuAttendance() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		if(request.getParameter("tidakHadir") != null) {
			ControllerWrapper controller = new ControllerWrapper();
			
			String idStudents[] = request.getParameterValues("PickList");
			String idMeeting = request.getParameter("idMeeting");
			
			for(String idStudent : idStudents) {
				Student student = new Student();
				Meeting meeting = new Meeting();
				KokuAttendance kokuAttd = new KokuAttendance();
				
				student.setStudent_id(idStudent);
				kokuAttd.setStudentId(student);
				meeting.setMeetingId(idMeeting);
				kokuAttd.setMeetingId(meeting);
				
				try {
					kokuAttd = controller.insertAttendance(kokuAttd);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			RequestDispatcher reqDispatcher = 
					request.getRequestDispatcher("index.jsp");
			reqDispatcher.forward(request, response);
		}
			
	
	}

}
