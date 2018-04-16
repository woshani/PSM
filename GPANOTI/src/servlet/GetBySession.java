package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import controller.ExcelDataCon;

/**
 * Servlet implementation class getBySession
 */
@WebServlet("/getBySession")
public class GetBySession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBySession() {
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
PrintWriter out= response.getWriter();
		
		//BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String jsonGetsesi = request.getParameter("sesi");
        String jsonGetcourse = request.getParameter("kos");

		ExcelDataCon exc = new ExcelDataCon();
		ArrayList<ArrayList<String>> res = exc.getBySesi(jsonGetsesi, jsonGetcourse);
		String json = new Gson().toJson(res);
        out.print(json); 
	}

}
