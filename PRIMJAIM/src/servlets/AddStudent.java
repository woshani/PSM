package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import controller.URLSecurity;
import bean.Guardian;
import bean.GuardianContact;
import bean.Race;
import bean.Religion;
import bean.Student;
import facade.ControllerWrapper;

/**
 * Servlet implementation class AddStudent
 */
@WebServlet("/AddStudent")
@MultipartConfig(maxFileSize = 31215)
public class AddStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddStudent() {
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
		HttpSession session = request.getSession();
		String pinKey = (String) session.getAttribute("pinKey");

		// if user give input file = 1
		int pictureStatus = 0;

		String alert = "Tiada sebarang perubahan.";

		try {
			String guardianIdEnc = request.getParameter("guardianIdEnc");
			String studentIdEnc = request.getParameter("studentIdEnc");
			//System.out.println(" Guardian Id Encapsulation : " + guardianIdEnc);
			//System.out.println(" Student Id Encapsulation : " + studentIdEnc);

			String studentId = "";
			String guardianId = "";

			String classId = request.getParameter("class");
			
			System.out.println(classId);
			
			String statusPelajar = request.getParameter("statusPelajar");
			String statusPenjaga = request.getParameter("statusPenjaga");

			if (guardianIdEnc != null) {
				guardianId = URLSecurity.decryptFinal(guardianIdEnc, pinKey);
				//System.out.println("Get Guardian ID :" + guardianId);
			}

			if (studentIdEnc != null) {
				studentId = URLSecurity.decryptFinal(studentIdEnc, pinKey);
				//System.out.println("Get Student ID :" + studentId);
			}

			// Get input value from previous page.

			// Student Information Collection
			Student student = new Student();

			student.setStudent_id(request.getParameter("studentId"));
			student.setName(request.getParameter("studentName"));
			student.setIc_number(request.getParameter("studentICStated"));

			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
			java.util.Date vDob = sdf.parse(request.getParameter("dob"));
			Date dob = new Date(vDob.getTime());
			student.setDob(dob);
			student.setBirth_cert(request.getParameter("birthCert"));
			student.setPlace_of_birth(request.getParameter("placeOfBirth"));
			student.setGender(request.getParameter("gender"));

			Race race = new Race();
			race.setId(Integer.parseInt(request.getParameter("race")));
			student.setRace(race);

			Religion religion = new Religion();
			religion.setId(Integer.parseInt(request.getParameter("religion")));
			student.setReligion(religion);

			InputStream picture = null; // input stream of the upload file

			// obtains the upload file part in this multipart request
			Part filePart = request.getPart("student-img");

			if (filePart.getSize() > 0) {

				// prints out some information for debugging
				//System.out.println(filePart.getName());
				//System.out.println(filePart.getSize());
				//System.out.println(filePart.getContentType());
				pictureStatus = 1;

				// obtains input stream of the upload file
				picture = filePart.getInputStream();
			} else {
				//System.out.println("TIADA GAMBAR DITEMUI!");
				pictureStatus = 0;
			}

			// Guardian Information Collection

			String[] guardianContactId = request
					.getParameterValues("guardianContactId");
			String[] gcGuardianId = request
					.getParameterValues("gcGuardianId");
			String[] guardianPhoneNo1 = request
					.getParameterValues("guardianPhoneNo1");
			String[] guardianPhoneNo2 = request
					.getParameterValues("guardianPhoneNo2");
			String[] telco = request.getParameterValues("telco");
			String[] smsStatus = request.getParameterValues("smsStatus");

			ControllerWrapper controller = new ControllerWrapper();

			ArrayList<GuardianContact> contacts = new ArrayList<GuardianContact>();

			for (int i = 0; i < guardianPhoneNo1.length; i++) {

				GuardianContact contact = new GuardianContact();

				contact.setGcontact_id(guardianContactId[i]);
				
				Guardian gcGuardian = new Guardian();
				gcGuardian.setGuardian_id(gcGuardianId[i]);
				
				contact.setGuardian(gcGuardian);
				
				contact.setPhone_number(guardianPhoneNo1[i].toString() + ""
						+ guardianPhoneNo2[i].toString());
				contact.setProvider(telco[i].toString());

				if (smsStatus[i].toString().contentEquals("AKTIF")) {
					contact.setSubscription("Y");
				} else {
					contact.setSubscription("N");
				}
				contacts.add(contact);
			}

			Guardian guardian = new Guardian();

			guardian.setGuardian_id(guardianId);
			guardian.setIc(request.getParameter("guardianICStated"));
			guardian.setName(request.getParameter("guardianName"));
			guardian.setGender(request.getParameter("gender"));
			guardian.setOccupation(request.getParameter("occupation"));
			guardian.setDependent2(request.getParameter("dependent"));
			guardian.setAddress(request.getParameter("guardianAddress"));
			guardian.setCity(request.getParameter("city"));
			guardian.setPostcode(request.getParameter("postcode"));
			guardian.setState(request.getParameter("state"));
			guardian.setRelationship(request.getParameter("relationship"));

			/*System.out.println("Guardian Update Information : ");
			System.out.println("Guardian ID : " + guardian.getGuardian_id());
			System.out.println("Name : " + guardian.getName());
			System.out.println("Ic : " + guardian.getIc());
			System.out.println("Gender : " + guardian.getGender());
			System.out.println("Occupation : " + guardian.getOccupation());
			System.out.println("Dependent : " + guardian.getDependent2()); 
			System.out.println("Address : " + guardian.getAddress());
			System.out.println("City : " + guardian.getCity());
			System.out.println("Postcode : " + guardian.getPostcode());
			System.out.println("State : " + guardian.getState());
			System.out.println("Relationship : " + guardian.getRelationship());

			System.out.println("Guardian Contact Update Information : ("
					+ contacts.size() + ")");

			for (int i = 0; i < contacts.size(); i++) {
				System.out.println(i + ") Phone Number : "
						+ contacts.get(i).getPhone_number());
				System.out.println(i + ") Telco : "
						+ contacts.get(i).getProvider());
				System.out.println(i + ") SMS Status : "
						+ contacts.get(i).getSubscription());
			}
			
			//Print Student Info :
			System.out.println("Maklumat Pelajar : ");
			System.out.println("ID Pelajar :" + student.getStudent_id());
			System.out.println("IC Pelajar :" + student.getIc_number());
			System.out.println("DOB Pelajar :" + student.getDob());
			System.out.println("Nama Pelajar :" + student.getName());
			System.out.println("No Surat Beranak :" + student.getBirth_cert());
			System.out.println("Jantina :" + student.getGender());
			System.out.println("Tempat Lahir :" + student.getPlace_of_birth());
			System.out.println("Bangsa :" + student.getRace().getId());
			System.out.println("Agama :" + student.getReligion().getId());
			System.out.println("Picture Status :" + pictureStatus);

			System.out.println("Kelas ID :" + classId);*/

			String resultProgressStudent="";
						
			/*if (statusPelajar.contentEquals("1")) {
				System.out.println("Daftar Pelajar dengan Maklumat yang telah direkod.");			
			} else {
				System.out.println("Daftar Pelajar dengan Maklumat BARU.");	
			}*/ 
			
			resultProgressStudent = controller.addStudentForRegistration(student, guardian, classId, picture, pictureStatus, contacts);

			if(resultProgressStudent.isEmpty()){
				alert = "Pendaftaran Pelajar GAGAL!";
			}else{
				alert = "Pendaftaran Pelajar BERJAYA!";
			}
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			alert = "Pendaftaran Pelajar TIDAK Berjaya.";
		}

		// RequestDispatcher reqDispatcher =
		// request.getRequestDispatcher("registerStudent.jsp");
		// reqDispatcher.forward(request, response);

		request.getSession().setAttribute("alertAddStudent", alert);
		response.sendRedirect("registerStudent.jsp");

	}

}
