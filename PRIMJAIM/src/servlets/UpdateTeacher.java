package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.URLSecurity;
import facade.ControllerWrapper;
import bean.Teacher;

/**
 * Servlet implementation class UpdateTeacher
 */
@WebServlet("/UpdateTeacher")
public class UpdateTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateTeacher() {
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
		Teacher teacher = new Teacher();
		ControllerWrapper controller = new ControllerWrapper();

		int status = 0;

		HttpSession session = request.getSession();
		String pinKey = (String) session.getAttribute("pinKey");

		teacher.setTeacher_id(request.getParameter("teacherId"));
		teacher.setName(request.getParameter("teacherName"));
		teacher.setGender(request.getParameter("gender"));
		teacher.setIc_number(request.getParameter("teacherIC"));
		teacher.setMarital_status(request.getParameter("maritalStatus"));
		teacher.setAddress(request.getParameter("teacherAddress"));
		teacher.setTelno_hp(request.getParameter("telNoHp"));
		teacher.setTelno_house(request.getParameter("telNoOffice"));
		teacher.setTelno_ext(request.getParameter("telNoExt"));

		try {
			String teacherIdEnc = URLSecurity.encryptFinal(
					teacher.getTeacher_id(), pinKey);
			
			status = controller.updateTeacher(teacher);
			
			if (status == 1) {
				RequestDispatcher reqDispatcher = request
						.getRequestDispatcher("teacherProfile.jsp?id="
								+ teacherIdEnc);

				request.setAttribute("status",
						"Maklumat Telah BERJAYA dikemaskini!");

				reqDispatcher.forward(request, response);
			} else {

				RequestDispatcher reqDispatcher = request
						.getRequestDispatcher("teacherProfile.jsp?id="
								+ teacherIdEnc);

				request.setAttribute("status",
						"Maklumat GAGAL dikemaskini! Sila hubungi ADMIN sekolah anda.");

				reqDispatcher.forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
