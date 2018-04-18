package servlets;

import java.io.IOException;

import javax.crypto.SecretKey;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.URLSecurity;
import bean.Guardian;
import bean.Police;
import bean.Politician;
import bean.Teacher;
import bean.User;
import facade.ControllerWrapper;

/**
 * Servlet implementation class LogIn
 */
@WebServlet("/LogIn")
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogIn() {
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
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		ControllerWrapper ctrlWrap = new ControllerWrapper();
		
		try {
			int userId = ctrlWrap.userLogin(username, password);
			
			
			if(userId > 0) {
				User user = ctrlWrap.getUserByUsername(username);
				session.setAttribute("SASuser", user);
				
				
				// Add by Mohamad Idzhar 27/11/2014 (URL passing parameter encapsulation process)
				// Declaration for Security Class
				String encKey = URLSecurity.sessionKeyGenerate();
				
				
				// Security key generate and stored in session
				session.setAttribute("pinKey", encKey);
				
				
				if(user.getLevel() > 2 && user.getLevel() < 11) {
					Teacher teacher = 
							ctrlWrap.getTeacherByUserId(user.getUser_id());
					
					session.setAttribute("SASteacher", teacher);
				} 
				
				if(user.getLevel() == 2) {
					Guardian guardian = 
							ctrlWrap.getGuardianByUserId(user.getUser_id());
					session.setAttribute("SASguardian", guardian);
				}
				
				// politician
				if(user.getLevel() == 11 || user.getLevel() == 12) {
					Politician politician = 
							ctrlWrap.getPoliticianByUserId(user.getUser_id());
					session.setAttribute("SASpolitician", politician);
				}
				
				// police
				if(user.getLevel() == 13 || user.getLevel() == 14) {
					Police police = 
							ctrlWrap.getPoliceByUserId(user.getUser_id());
					session.setAttribute("SASpolice", police);
				}
				
				// authorized user will be send to index.jsp page
				RequestDispatcher reqDispatcher = 
						request.getRequestDispatcher("index.jsp");
				reqDispatcher.forward(request, response);
				
			} else if(userId == -1) {
				request.setAttribute("SASinvalidUsername", "Y");
				
				RequestDispatcher reqDispatcher = 
						request.getRequestDispatcher("logIn.jsp");
				reqDispatcher.forward(request, response);
				
			} else if(userId == -2) {
				request.setAttribute("SASinvalidPassword", "Y");
				
				RequestDispatcher reqDispatcher = 
						request.getRequestDispatcher("logIn.jsp");
				reqDispatcher.forward(request, response);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
