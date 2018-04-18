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
import bean.Guardian;
import facade.ControllerWrapper;

/**
 * Servlet implementation class CheckGuardianIcForUpdate
 */
@WebServlet("/CheckGuardianIcForUpdate")
public class CheckGuardianIcForUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckGuardianIcForUpdate() {
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

		String guardianIdEnc = request.getParameter("guardianIdEnc");
		
		try {
			

			String guardianIc = request.getParameter("guardianIc");
			String checkGuardianIcStatus = "";
			
			if (guardianIc == "") {

				// set status = 0, data null given.
				request.setAttribute("status", 0);
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(
								"/contents/c_form_errorGuardianIc.jsp");
				dispatcher.forward(request, response);

			} else {

				// check ic student status
				ControllerWrapper ctrlWrap = new ControllerWrapper();

				int result = 0;

				// check student ic length
				if (guardianIc.length() == 12) {

					result = ctrlWrap.checkGuardianIc(guardianIc);

					// 1 = already recorded
					if (result == 1) {
						checkGuardianIcStatus = "Nombor IC telah berdaftar didalam sistem";

					// 0
					} else {
						checkGuardianIcStatus = "Nombor IC  ";

					}

					request.setAttribute("status", checkGuardianIcStatus);
					request.setAttribute("id", guardianIdEnc);
					RequestDispatcher dispatcher = getServletContext()
							.getRequestDispatcher(
									"/contents/c_form_errorGuardianIc.jsp");
					dispatcher.forward(request, response);

				} else {

					// set status = 101, data not have 12 character.
					request.setAttribute("status", 101);
					RequestDispatcher dispatcher = getServletContext()
							.getRequestDispatcher(
									"/contents/c_form_errorGuardianIc.jsp");
					dispatcher.forward(request, response);

				}

			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
