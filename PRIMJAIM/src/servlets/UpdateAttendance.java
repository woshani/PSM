package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.URLSecurity;
import facade.ControllerWrapper;

/**
 * Servlet implementation class UpdateAttendance
 */
@WebServlet("/UpdateAttendance")
public class UpdateAttendance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAttendance() {
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
		
		HttpSession session = request.getSession();
		String pinKey = (String) session.getAttribute("pinKey");
		
		String reason[] = request.getParameterValues("reason");
		String studentId[] = request.getParameterValues("studentId");
		String reasonBefore[] = request.getParameterValues("reasonBefore");
		String day = request.getParameter("day");
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		String classIdEnc = request.getParameter("classId");
		
		int status = 0;
		ControllerWrapper controller = new ControllerWrapper();
		
		//System.out.println("Class Id Encapsulate :"+classIdEnc);
		//System.out.println("Student Id Encapsulate :"+studentId[0]);
		
			
		int index = 0;
		while(index < studentId.length){
			try {
				String classId = URLSecurity.decryptFinal(classIdEnc, pinKey);
				String v_studentId = URLSecurity.decryptFinal(studentId[index],pinKey);
				//System.out.println("Student Id: "+v_studentId+"   Class Id : "+classId);
				status = controller.updateAttendance(v_studentId, classId, day, month, year, reasonBefore[index], reason[index]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				status = 0;
			}
			index++;
		}
			
		
		RequestDispatcher reqDispatcher = request
				.getRequestDispatcher("classProfile.jsp?alert="+status);
		reqDispatcher.forward(request, response);
		
	}

}
