package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import bean.Student;
import controller.MobileController;

/**
 * Servlet implementation class LogoutMobile
 */
@WebServlet("/LogoutMobile")
public class LogoutMobile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutMobile() {
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
			System.out.println(matric);
			String message;
			JSONObject json = new JSONObject();
			MobileController mc = new MobileController();
			String res = mc.logoutMobile(matric);
			if(!res.isEmpty()) {
				json.put("res", res);
			}else {
				json.put("res", "error");
			}

			
			message = json.toString();
			out.print(message);
	}

}
