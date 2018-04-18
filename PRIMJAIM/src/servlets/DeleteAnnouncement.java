package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.URLSecurity;
import bean.Announcement;
import bean.Classroom;
import bean.Instituition;
import bean.Student;
import bean.User;
import facade.ControllerWrapper;

/**
 * Servlet implementation class DeleteAnnouncement
 */
@WebServlet("/DeleteAnnouncement")
public class DeleteAnnouncement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteAnnouncement() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *      
	 *      FOR A HREF TO SERVLET FROM WEB, CODE MUST IN DOGET FUNCTION
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
		HttpSession session = request.getSession();
		String pinKey = (String) session.getAttribute("pinKey");
		
		ControllerWrapper controller = new ControllerWrapper();
		Announcement announcement = new Announcement();

		String idEnc = request.getParameter("id");
		

		try {
			String id = URLSecurity.decryptFinal(idEnc, pinKey);
			
			announcement.setAnnouncement_id(id);
			
			if (announcement.getAnnouncement_id() != null) {

				int result = controller.deleteAnnouncement(announcement);
				
				System.out.println("Delete Annoucement DONE "+result);

				RequestDispatcher reqDispatcher = request.getRequestDispatcher("/listAnnouncement.jsp");
				reqDispatcher.forward(request, response);
				
			} else {
				request.setAttribute("SASinvalidTarget", "Y");
				RequestDispatcher reqDispatcher = request.getRequestDispatcher("/listAnnouncement.jsp");
				reqDispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

	}

}
