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
import facade.ControllerWrapper;

/**
 * Servlet implementation class UpdateContact
 */
@WebServlet("/UpdateContact")
public class UpdateContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateContact() {
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
		String guardianIdEnc = request.getParameter("guardianId");
		String contactIdEnc = request.getParameter("contactId");
		
		try {
		//System.out.println(guardianIdEnc);
		//System.out.println(contactIdEnc);
		String contactId = URLSecurity.decryptFinal(contactIdEnc, pinKey);
		String guardianId = URLSecurity.decryptFinal(guardianIdEnc, pinKey);
		String contactNumber = request.getParameter("contactNumber");
		
		//System.out.println(contactId);
		//System.out.println(guardianId);
		
		ControllerWrapper controller = new ControllerWrapper();
		
		
			controller.updateGuardianContact(contactId, contactNumber);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher reqDispatcher = request
				.getRequestDispatcher("guardianProfile.jsp?id="+guardianIdEnc);
		reqDispatcher.forward(request, response);
		
		
	}

}
