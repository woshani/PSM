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
import bean.Race;
import bean.Religion;
import bean.Student;

/**
 * Servlet implementation class UpdateStudent
 */
@WebServlet("/UpdateStudent")
public class UpdateStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateStudent() {
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
		
		HttpSession session = request.getSession();
		String pinKey = (String) session.getAttribute("pinKey");
		
		Student student = new Student();
		ControllerWrapper controller = new ControllerWrapper();
		
		student.setStudent_id(request.getParameter("studentId"));
		student.setName(request.getParameter("name"));
		student.setIc_number(request.getParameter("studentIC"));
		student.setBirth_cert(request.getParameter("birthCert"));
		student.setGender(request.getParameter("gender"));
		Race race = new Race();
		race.setId(Integer.parseInt(request.getParameter("race")));
		student.setRace(race);
		Religion religion = new Religion();
		religion.setId(Integer.parseInt(request.getParameter("religion")));
		student.setReligion(religion);
		student.setPlace_of_birth(request.getParameter("placeOfBirth"));

		

		int status = 0;

		

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
			java.util.Date vDob = sdf.parse(request.getParameter("dob"));
			Date dob = new Date(vDob.getTime());
			
			student.setDob(dob);

			//System.out.println(student.getDob());

			String studentIdEnc = URLSecurity.encryptFinal(
					student.getStudent_id(), pinKey);

			//System.out.println(student.getRace().getId());
			//System.out.println(student.getReligion().getId());
			
			status = controller.updateStudent(student);

			if (status == 1) {
				RequestDispatcher reqDispatcher = request
						.getRequestDispatcher("studentProfile.jsp?id="
								+ studentIdEnc);

				request.setAttribute("status",
						"Maklumat Telah BERJAYA dikemaskini!");

				reqDispatcher.forward(request, response);
			} else {

				RequestDispatcher reqDispatcher = request
						.getRequestDispatcher("studentProfile.jsp?id="
								+ studentIdEnc);

				request.setAttribute("status",
						"Maklumat GAGAL dikemaskini! Sila hubungi ADMIN sekolah anda.");

				reqDispatcher.forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
