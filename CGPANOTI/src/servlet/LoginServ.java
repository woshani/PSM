package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Staff;
import controller.StaffCon;

/**
 * Servlet implementation class LoginServ
 */
@WebServlet("/LoginServ")
public class LoginServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html; charset=UTF-8");
		String username = request.getParameter("user");
		String pass = request.getParameter("password");
		if(username == "" || pass == "") {
			response.sendRedirect("index.jsp?x=1");
		}else {
			StaffCon st = new StaffCon();
			Staff staff = st.getStaffDetail(username, pass);
			if(staff.getFullName()==null) {
				response.sendRedirect("index.jsp?x=2");
			}else {
				HttpSession session=request.getSession();  
		        session.setAttribute("userid",staff.getUserID());
		        session.setAttribute("staffid",staff.getStaffID());
		        session.setAttribute("fullname",staff.getFullName());
		        session.setAttribute("address",staff.getAddress());
		        session.setAttribute("email",staff.getEmail());
		        session.setAttribute("phone",staff.getPhoneNum());
		        session.setAttribute("password",staff.getPassword());
		        response.sendRedirect("dashboard/index.jsp");
			}
			
		}
		
		
		
		
	}

}
