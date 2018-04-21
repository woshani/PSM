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
import com.google.gson.GsonBuilder;

import bean.Student;
import controller.ExcelDataCon;

/**
 * Servlet implementation class GetByMatric
 */
@WebServlet("/GetByMatric")
public class GetByMatric extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetByMatric() {
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
		response.setContentType("text/plain"); 
        String jsonGetmatrik = request.getParameter("matrik");


		ExcelDataCon exc = new ExcelDataCon();
		Student stud = exc.getByMatric(jsonGetmatrik);
		ArrayList<ArrayList<String>> res = stud.getResults();
		String json = new Gson().toJson(res);
		
		System.out.println(json);
		System.out.println(stud.getName());
		System.out.println(stud.getMatric());
		System.out.println(stud.getCohort());
		System.out.println(stud.getCourse());
		System.out.println(stud.getPA());
		System.out.println(stud.getPhone());
		System.out.println(stud.getStatus());
		
		Gson gson = new GsonBuilder().serializeNulls().create();
	    String jsonInString = gson.toJson(stud);
	    
		out.print(jsonInString);
	}

}
