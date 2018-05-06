package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import controller.MobileController;

/**
 * Servlet implementation class GetCurrentGpaServ
 */
@WebServlet("/GetCurrentGpaServ")
public class GetCurrentGpaServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCurrentGpaServ() {
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
		String matric = request.getParameter("matric");
		String message;
		PrintWriter out= response.getWriter();
		MobileController mc = new MobileController();
		ArrayList<String> ar = mc.getCurrentGpa(matric);
		JSONObject json = new JSONObject();
		if(ar.size() == 0) {
			json.put("status","false");
		}else {
			json.put("sesi", ar.get(1));
			json.put("gpa", ar.get(0));
			json.put("status","true");
		}
		message = json.toString();
		out.print(message);
	}

}
