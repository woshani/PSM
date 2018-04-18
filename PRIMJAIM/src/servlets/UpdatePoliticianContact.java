package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Politician;
import controller.URLSecurity;
import facade.ControllerWrapper;

/**
 * Servlet implementation class UpdatePoliticianContact
 */
@WebServlet("/UpdatePoliticianContact")
public class UpdatePoliticianContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePoliticianContact() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String pinKey = (String) session.getAttribute("pinKey");
		String politicianIdEnc = request.getParameter("politicianId");

		try {

			String politicianId = URLSecurity.decryptFinal(politicianIdEnc, pinKey);
			String contactNumber = request.getParameter("contactNumber");

			// System.out.println(contactId);
			// System.out.println(guardianId);

			ControllerWrapper controller = new ControllerWrapper();
			
			Politician politician = new Politician();
			
			politician.setId(politicianId);
			politician.setPhoneNo(contactNumber);

			controller.updatePoliticianContact(politician);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher reqDispatcher = request
				.getRequestDispatcher("index.jsp?id=" + politicianIdEnc);
		reqDispatcher.forward(request, response);

	}
}
