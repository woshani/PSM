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

import bean.DataModel;
import bean.DataResult;
import controller.MobileController;

/**
 * Servlet implementation class GetResultAllSem
 */
@WebServlet("/GetResultAllSem")
public class GetResultAllSem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetResultAllSem() {
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
		MobileController mc = new MobileController();
		PrintWriter out= response.getWriter();
		String matric = request.getParameter("matric");
		Gson gson = new Gson();
		
		ArrayList<DataResult> al = mc.getDataResult(matric);
		String jsonInString = gson.toJson(al);
		out.print(jsonInString);
		System.out.println(jsonInString);
	
	}

}
