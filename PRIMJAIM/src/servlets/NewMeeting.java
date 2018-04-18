package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Meeting;
import facade.ControllerWrapper;

/**
 * Servlet implementation class NewMeeting
 */
@WebServlet("/NewMeeting")
public class NewMeeting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewMeeting() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		ControllerWrapper controller = new ControllerWrapper();
		
		String instId = request.getParameter("instId");
		
		String tarikh = request.getParameter("tarikh");
		String masa = request.getParameter("masa");
		String keterangan = request.getParameter("terang");
		Meeting meeting = new Meeting();
		
		meeting.setMeetingDate(tarikh);
		meeting.setMeetingTime(masa);
		meeting.setMeetingDetails(keterangan);
		
		try {
			meeting = controller.insertMeeting(meeting,instId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(request.getParameter("penuh") != null) {
			response.sendRedirect("kokuList.jsp");
		} else if(request.getParameter("tidakHadir") != null) {
			String name = meeting.getMeetingId();
			request.setAttribute("theName", name);
			
			RequestDispatcher reqDispatcher = 
					request.getRequestDispatcher("kokuAttendance.jsp?instKokuId="+instId);
			reqDispatcher.forward(request, response);
		}
		
	}

}
