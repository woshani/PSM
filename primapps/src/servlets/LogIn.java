package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.google.gson.Gson;

import bean.Guardian;
import bean.User;
import controller.URLSecurity;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		PrintWriter out = response.getWriter();
				
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		ControllerWrapper ctrlWrap = new ControllerWrapper();
		
		String jsonInString ="ERROR-101";
		
		try {
			int userId = ctrlWrap.userLogin(username, password);
			
			if(userId > 0) {
				User user = ctrlWrap.getUserByUsername(username);
				
				
				
				// Add by Mohamad Idzhar 27/11/2014 (URL passing parameter encapsulation process)
				// Declaration for Security Class
				// Generating security key
				String securityKey = URLSecurity.sessionKeyGenerate();
				
				
				// Generated security key pass to android variable to be stored in android shared pref
				
				
  				// Generated security temporary stored in table guardian as compare key.
				
				
				if(user.getLevel() == 2) {
					Guardian guardian = 
							ctrlWrap.getGuardianByUserId(user.getUser_id());
					
					Gson gson = new Gson();
					
					//Convert Java object to JSON, and assign to a String
					jsonInString = gson.toJson(guardian);
					
					//out.print(jsonInString);
				}
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//print result
		out.print(jsonInString);
	}

}
