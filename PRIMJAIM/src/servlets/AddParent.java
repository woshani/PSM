package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.URLSecurity;
import facade.ControllerWrapper;
import bean.Guardian;
import bean.GuardianContact;

/**
 * Servlet implementation class AddParent
 */
@WebServlet("/AddParent")
public class AddParent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddParent() {
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
		
		String guardianIdEnc = request.getParameter("guardianIdEnc");
		String studentIdEnc = request.getParameter("studentIdEnc");
		
		String alert = "-";
		
		try {
			
			String studentId = "";
			String guardianId = "";
			
			if (guardianIdEnc != null) {
				guardianId = URLSecurity.decryptFinal(guardianIdEnc, pinKey);
				//System.out.println("Get Guardian ID :" + guardianId);
			}

			if (studentIdEnc != null) {
				studentId = URLSecurity.decryptFinal(studentIdEnc, pinKey);
				//System.out.println("Get Student ID :" + studentId);
			}
			
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
			guardian.setAddress(request.getParameter("guardianAddress"));
			guardian.setCity(request.getParameter("city"));
			guardian.setPostcode(request.getParameter("postcode"));
			guardian.setState(request.getParameter("state"));
			guardian.setRelationship(request.getParameter("relationship"));

			/*System.out.println("Guardian Update Information : "
					+ guardian.getGuardian_id());
			System.out.println("Name : " + guardian.getName());
			System.out.println("Ic : " + guardian.getIc());
			System.out.println("Gender : " + guardian.getGender());
			System.out.println("Occupation : " + guardian.getOccupation());
			System.out.println("Dependent : " + guardian.getDependent());
			System.out.println("Address : " + guardian.getAddress());
			System.out.println("City : " + guardian.getCity());
			System.out.println("Postcode : " + guardian.getPostcode());
			System.out.println("State : " + guardian.getState());
			System.out.println("Realationship : " + guardian.getRelationship());

			System.out.println("Guardian Contact Update Information : ("
					+ contacts.size() + ")");

			for (int i = 0; i < contacts.size(); i++) {
				System.out.println(i + ") Phone Number : "
						+ contacts.get(i).getPhone_number());
				System.out.println(i + ") Telco : "
						+ contacts.get(i).getProvider());
				System.out.println(i + ") SMS Status : "
						+ contacts.get(i).getSubscription());
			}*/

			ControllerWrapper controller = new ControllerWrapper();

			guardianId = controller.addParentForRegistration(studentId, guardian, guardian.getRelationship(), contacts);
			
			if (guardianId!=null){
				alert = "Pendaftaran Penjaga telah BERJAYA!";
			}else{
				alert = "Pendaftaran Penjaga telah GAGAL.";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			alert = "Pendaftaran Penjaga TIDAK Berjaya.";
		}
		
		request.getSession().setAttribute("alertAddParent", alert);
		response.sendRedirect("studentProfile.jsp?id=" + studentIdEnc);
	}
}
