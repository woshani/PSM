package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

/**
 * Servlet implementation class AndroidTest
 */
@WebServlet("/AndroidTest")
public class AndroidTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AndroidTest() {
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
		// TODO Auto-generated method stub
		
		String x = request.getParameter("input");
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		// create Json Object
		JSONObject json = new JSONObject();

		if(!x.isEmpty()&&x.equals("1")){
			// put some value pairs into the JSON object .
			json.put("Mobile", "111-111-111");
			json.put("Name", "Mohamad Idzhar");
			json.put("Email", "whiteredcode@gmail.com");
			
		}else{
			// put some value pairs into the JSON object .
			json.put("Mobile", "222-222-222");
			json.put("Name", "Bin yaakub");
			json.put("Email", "whiteredcode@gmail.com");
		}
		

		Gson gson = new Gson();
		String jsonOut = gson.toJson(json);
		
		// finally output the json string
		//out.print(json.toString());
		out.print(jsonOut);
	}

}
