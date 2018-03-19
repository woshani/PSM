package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.Staff;
import controller.StaffCon;

/**
 * Servlet implementation class RegisterStaff
 */
@WebServlet("/RegisterStaff")
public class RegisterStaff extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterStaff() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String staffid = request.getParameter("staffid");
		String name= request.getParameter("fullname");
		String phone= request.getParameter("phone");
		String email= request.getParameter("email");
		String address= request.getParameter("address");
		String msg;
		
		Staff s = new Staff();
		StaffCon sc = new StaffCon();
		PrintWriter out= response.getWriter();
		
		s.setStaffID(staffid);
		s.setFullName(name);
		s.setEmail(email);
		s.setPhoneNum(phone);
		s.setAddress(address);
		s.setPassword("abc123");
		
		msg = sc.registerStaff(s);
		
		out.print(msg);
	}

}
