package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facade.ControllerWrapper;

/**
 * Servlet implementation class RegisterClass
 */
@WebServlet("/RegisterClass")
public class RegisterClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterClass() {
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
		
		String student[] = request.getParameterValues("studentid");
		String kelas[] = request.getParameterValues("classRegister");
		String classId = request.getParameter("class");
		ControllerWrapper controller = new ControllerWrapper();
		
		int index = 0;
		int status = 0;
		
		System.out.print(student.length);
		
		while (index < student.length) {
			
			
			
			try {
				
				status = controller.insertStudentClass(student[index], kelas[index]);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			index = index + 1;
		}
		
		try {
			status = controller.insertOriginalStudentClass(classId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		RequestDispatcher reqDispatcher = request
				.getRequestDispatcher("index.jsp");
		reqDispatcher.forward(request, response);
	}

}
