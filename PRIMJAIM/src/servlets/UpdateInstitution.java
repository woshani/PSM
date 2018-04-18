package servlets;

import java.io.IOException;
import java.io.InputStream;

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
import facade.ControllerWrapper;
import bean.Instituition;

/**
 * Servlet implementation class UpdateInstitution
 */
@WebServlet("/UpdateInstitution")
@MultipartConfig (maxFileSize = 16177215)    // upload file's size up to 16MB
public class UpdateInstitution extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateInstitution() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String pinKey = (String) session.getAttribute("pinKey");
		
		try{
		Instituition institution = new Instituition();
		
		institution.setAcademic_instituition_id(request.getParameter("institutionId"));
		institution.setAcademic_name(request.getParameter("nama"));
		institution.setAddress(request.getParameter("alamat"));
		institution.setPostcode(request.getParameter("poskod"));
		institution.setCity(request.getParameter("bandar"));
		institution.setDistrict(request.getParameter("daerah"));
		institution.setTelephone_number(request.getParameter("telefon"));
		institution.setFax_number(request.getParameter("fax"));
		institution.setPpd(request.getParameter("ppd"));
		
		InputStream logo = null; // input stream of the upload file
		
		// obtains the upload file part in this multipart request
		Part filePart = request.getPart("logo");
		
		if (filePart != null){
		
			//prints out some information for debugging
			//System.out.println(filePart.getName());
			//System.out.println(filePart.getSize());
			//System.out.println(filePart.getContentType());

			// obtains input stream of the upload file
			logo = filePart.getInputStream();
		}else{
			System.out.println("TIADA GAMBAR DITEMUI!");
		}
		
		//declare controllerwrapper for calling update institution process
		ControllerWrapper ctrl = new ControllerWrapper();
		//run update institution process
		int status = ctrl.updateInstitution(institution, logo);
		//int status = 0;
		String institutionIdEnc = URLSecurity.encryptFinal(
				institution.getAcademic_instituition_id(), pinKey);
		
		if (status == 1) {
			RequestDispatcher reqDispatcher = request
					.getRequestDispatcher("instituteProfile.jsp?id="
							+ institutionIdEnc);

			request.setAttribute("status",
					"Maklumat Telah BERJAYA dikemaskini!");

			reqDispatcher.forward(request, response);
		} else {

			RequestDispatcher reqDispatcher = request
					.getRequestDispatcher("instituteProfile.jsp?id="
							+ institutionIdEnc);

			request.setAttribute("status",
					"Maklumat GAGAL dikemaskini! Sila hubungi ADMIN sekolah anda.");

			reqDispatcher.forward(request, response);

		}
		
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
