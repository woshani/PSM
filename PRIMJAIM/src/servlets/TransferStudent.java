package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

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
 * Servlet implementation class TransferStudent
 */
@WebServlet("/TransferStudent")
public class TransferStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferStudent() {
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
		
		ControllerWrapper controller = new ControllerWrapper();
		
		String[] studentIdEnc = request.getParameterValues("studentId");
		
		String[] transferDates = request.getParameterValues("transferDate");
		//System.out.println(transferDates);
		SimpleDateFormat fromUser = new SimpleDateFormat("dd-MM-yy");
		
		String classIdEnc = request.getParameter("classId");
		
		
		try {
			String classId = URLSecurity.encryptFinal(classIdEnc, pinKey);
			
			for(int index=0; index<studentIdEnc.length; index++) {
				String studentId = URLSecurity.decryptFinal(studentIdEnc[index], pinKey);
				
				Date date = new Date(fromUser.parse(transferDates[index]).getTime());
				//System.out.println(date);
				controller.studentTransfer(studentId, date);
			}
		
		
			RequestDispatcher reqDispatcher = request
				.getRequestDispatcher("classProfile.jsp?id="+classIdEnc);
			reqDispatcher.forward(request, response);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
