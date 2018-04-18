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
import bean.OutgoingSMS;
import facade.ControllerWrapper;

/**
 * Servlet implementation class DeleteOutGoingSMS
 */
@WebServlet("/DeleteOutGoingSMS")
public class DeleteOutGoingSMS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteOutGoingSMS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String pinKey = (String) session.getAttribute("pinKey");
		
		ControllerWrapper controller = new ControllerWrapper();
		OutgoingSMS ogsms = new OutgoingSMS();

		String idEnc = request.getParameter("id");
		try {
			String id = URLSecurity.decryptFinal(idEnc, pinKey);

			ogsms.setOsms_id(id);

		
			if (ogsms.getOsms_id() != null) {

				controller.deleteOutGoingSMS(ogsms);
				
				System.out.println("Delete Annoucement DONE");
				
				RequestDispatcher reqDispatcher = request.getRequestDispatcher("/listOutbox.jsp");
				reqDispatcher.forward(request, response);
				
				System.out.println("Dispatch to listOutbox.jsp DONE");
				
			} else {
				request.setAttribute("SASinvalidTarget", "Y");
				RequestDispatcher reqDispatcher = request.getRequestDispatcher("/listOutbox.jsp");
				reqDispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
