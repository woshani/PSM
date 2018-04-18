package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import controller.TokenController;

/**
 * Servlet implementation class Verify
 */
@WebServlet("/Verify")
public class Verify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Verify() {
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
		String userId = request.getParameter("s");
		String token = request.getParameter("t");
		int projectId = Integer.parseInt(request.getParameter("p"));
		
		String result = "0";
		
		TokenController tokenCtrl = new TokenController();
		
		try {
			result = tokenCtrl.TokenVerification(userId, token, projectId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		// create Json Object
		JSONObject json = new JSONObject();

		json.put("result",result);

		//Gson gson = new Gson();
		//String jsonOut = gson.toJson(json);

		// finally output the json string
		// out.print(json.toString());
		out.print(json);
	}

}
