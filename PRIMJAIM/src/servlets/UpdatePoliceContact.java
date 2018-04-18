package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Police;
import controller.URLSecurity;
import facade.ControllerWrapper;

/**
 * Servlet implementation class UpdatePoliceContact
 */
@WebServlet("/UpdatePoliceContact")
public class UpdatePoliceContact extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdatePoliceContact() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String pinKey = (String) session.getAttribute("pinKey");
		String policeIdEnc = request.getParameter("policeId");

		try {

			String policeId = URLSecurity.decryptFinal(policeIdEnc, pinKey);
			String contactNumber = request.getParameter("contactNumber");

			// System.out.println(contactId);
			// System.out.println(guardianId);

			ControllerWrapper controller = new ControllerWrapper();
			
			Police police = new Police();
			
			police.setId(policeId);
			police.setPhoneNo(contactNumber);

			controller.updatePoliceContact(police);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher reqDispatcher = request
				.getRequestDispatcher("index.jsp?id=" + policeIdEnc);
		reqDispatcher.forward(request, response);

	}
}
