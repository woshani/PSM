package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.codehaus.jettison.json.JSONObject;

import bean.Student;
import facade.ControllerWrapper;

/**
 * Servlet implementation class CheckStudentIc
 */
@WebServlet("/CheckStudentIc")
public class CheckStudentIc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckStudentIc() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
						
		String studentIc = request.getParameter("studentIc");
		Student student = new Student();

		try{
			
		
		if (studentIc == "") {			
			
			// set status = 0, data null given.
			request.setAttribute("status", 0);
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/contents/c_form_errorStudentIc.jsp");
			dispatcher.forward(request, response);

		} else {

			// check ic student status
			ControllerWrapper ctrlWrap = new ControllerWrapper();

			int result = 0;

			// check student ic length
			if (studentIc.length() == 12) {

				try {

					result = ctrlWrap.checkStudentIc(studentIc);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 0 = new student (no data recorded)
				if (result == 0) {
									
					RequestDispatcher dispatcher = getServletContext()
							.getRequestDispatcher(
									"/contents/c_form_newStudentForm.jsp");
					dispatcher.include(request, response);

					// 1 = already recorded
				} else if (result == 1) {
					
									
					try {
						student = ctrlWrap.getStudentByIc(studentIc);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					request.setAttribute("student", student);

					RequestDispatcher dispatcher = getServletContext()
							.getRequestDispatcher(
									"/contents/c_form_addStudentForm.jsp");
					dispatcher.forward(request, response);
					
				} else{

					// set status = 1, student already registered and have class
					// assigned
					request.setAttribute("status", 1);
					RequestDispatcher dispatcher = getServletContext()
							.getRequestDispatcher(
									"/contents/c_form_errorStudentIc.jsp");
					dispatcher.forward(request, response);
				}

			} else {
				
				// set status = 101, data not have 12 character.
				request.setAttribute("status", 101);
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(
								"/contents/c_form_errorStudentIc.jsp");
				dispatcher.forward(request, response);

			}

		}
				
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
