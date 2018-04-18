package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Guardian;
import bean.Student;
import facade.ControllerWrapper;

/**
 * Servlet implementation class CheckGuardianIc
 */
@WebServlet("/CheckGuardianIc")
public class CheckGuardianIc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckGuardianIc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String guardianIc = request.getParameter("guardianIc");
		Guardian guardian = new Guardian();

		if (guardianIc == "") {

			// set status = 0, data null given.
			request.setAttribute("status", 0);
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/contents/c_form_errorGuardianIc.jsp");
			dispatcher.forward(request, response);

		} else {

			// check ic student status
			ControllerWrapper ctrlWrap = new ControllerWrapper();

			int result = 0;

			// check student ic length
			if (guardianIc.length() == 12) {

				try {

					result = ctrlWrap.checkGuardianIc(guardianIc);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 0 = new student (no data recorded)
				if (result == 0) {
					RequestDispatcher dispatcher = getServletContext()
							.getRequestDispatcher(
									"/contents/c_form_newGuardianForm.jsp");
					dispatcher.include(request, response);

					// 1 = already recorded
				} else if (result == 1) {

					try {
						guardian = ctrlWrap.getGuardianByIc(guardianIc);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					request.setAttribute("guardian", guardian);

					RequestDispatcher dispatcher = getServletContext()
							.getRequestDispatcher(
									"/contents/c_form_addGuardianForm.jsp");
					dispatcher.forward(request, response);
				}
				
			} else {
				
				// set status = 101, data not have 12 character.
				request.setAttribute("status", 101);
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(
								"/contents/c_form_errorGuardianIc.jsp");
				dispatcher.forward(request, response);

			}

		}
	}

}
