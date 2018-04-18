package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import bean.Student;
import controller.MobileController;

/**
 * Servlet implementation class LoginMobile
 */
@WebServlet("/LoginMobile")
public class LoginMobile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginMobile() {
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
		doGet(request, response);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out= response.getWriter();
		String matric = request.getParameter("matric");
		String pass = request.getParameter("password");
		String token = request.getParameter("device");
		
			String message;
			JSONObject json = new JSONObject();
			MobileController mc = new MobileController();
			Student stud = mc.loginMobile(matric, pass,token);
			if(stud.getName()==null) {
				json.put("status","false");
			}else {
				json.put("name", stud.getName());
				json.put("phone", stud.getPhone());
				json.put("status","true");
			}
			
			message = json.toString();
			out.print(message);
	}

}
