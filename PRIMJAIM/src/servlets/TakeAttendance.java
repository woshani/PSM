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
 * Servlet implementation class TakeAttendance
 */
@WebServlet("/TakeAttendance")
public class TakeAttendance extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TakeAttendance() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		ControllerWrapper controller = new ControllerWrapper();
		
		int status = 0;
		String classIdEnc = "";

		try {
			HttpSession session = request.getSession();
			String pinKey = (String) session.getAttribute("pinKey");
			
			
			classIdEnc = request.getParameter("classId");
			String classId = URLSecurity.decryptFinal(classIdEnc, pinKey);
			

			String studentId[] = request.getParameterValues("PickList");
			
			status = controller.insertFinishTime(classId);
			status = controller.insertDailyAttendance(classId);
			int index = 0;
			
			//controller.updateTotalDay(classId);
			
			//int index = 0;
			
			/*if(studentId != null)
			{
				while (index < studentId.length) {
				
					String v_studentId = URLSecurity.decryptFinal(studentId[index], pinKey);
					
					// carry_forward process if absent student picked
					// flaw found! only one student will be count
					status = controller.dailyattendancecarryforwstat(v_studentId, classId);
					
					status = controller.insertDailyAttendanceAbsent(v_studentId,classId);
					
					index = index + 1;
				}
				// set class_id status
				status = controller.updateAbsentProgress(classId);
			}else{
				// carry forward process if all student attend
				status = controller.dailyattendancecarryforwstat2(classId);
			}*/
			
			// carry forward absent data process
			status = controller.dailyattendancecarryforwstat2(classId);
			
			if(studentId != null)
			{
				while (index < studentId.length) {
				
					String v_studentId = URLSecurity.decryptFinal(studentId[index], pinKey);
					
					// run absent process from pick list student id
					status = controller.insertDailyAttendanceAbsent(v_studentId,classId);
					
					index = index + 1;
				}
			}
			
			controller.updateTotalDay(classId);

		} catch (Exception e) {
			e.printStackTrace();
			status = 0;
		}

		RequestDispatcher reqDispatcher = request.getRequestDispatcher("classProfile.jsp?alert="+status+"&id="+classIdEnc);
		reqDispatcher.forward(request, response);
	}
}
