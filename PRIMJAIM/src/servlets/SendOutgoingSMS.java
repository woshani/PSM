package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.URLSecurity;
import bean.Instituition;
import bean.Message;
import bean.OutgoingSMS;
import facade.ControllerWrapper;

/**
 * Servlet implementation class SendOutgoingSMS
 */
@WebServlet("/SendOutgoingSMS")
public class SendOutgoingSMS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendOutgoingSMS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String pinKey = (String) session.getAttribute("pinKey");
		//System.out.println(pinKey);
		
		ControllerWrapper controller = new ControllerWrapper();
		List<OutgoingSMS> outgoingSMSs = new ArrayList<OutgoingSMS>();
				
		String instituitionIdEnc = request.getParameter("instituitionId");
		//System.out.println(instituitionIdEnc);
		Instituition instituition = null;
		
		try {
			String instituitionId = URLSecurity.decryptFinal(instituitionIdEnc, pinKey);
			//System.out.println(instituitionId);
			instituition = controller.getInstituitionById(instituitionId);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String[] osms_idsEnc = request.getParameterValues("osms_id");
		
		
		
		String result = "No Response";

		for(int index=0; index<osms_idsEnc.length; index++) {
			
			try {
				String osms_id = URLSecurity.decryptFinal(osms_idsEnc[index], pinKey);
				//System.out.println(osms_id);
				OutgoingSMS outgoingSMS = controller.getOutgoingSMSById(osms_id);
				
				outgoingSMSs.add(outgoingSMS);
			} catch (Exception e) {
				result = "Exception occured when getting outgoing sms information.";
				e.printStackTrace();
			}
		}
		
		
		
		try {
			List<Message> messages = controller.getAllMessagesByOutgoingSMSs(outgoingSMSs);
		
			result = controller.sendSMSs(messages,instituition.getAcademic_instituition_id());
			
			//result = controller.sendSMSs(messages,instituition.getAcademic_name());
			
			
		} catch (Exception e) {
			result = "Exception occured when sending messages.";
			e.printStackTrace();
		}
		
		request.setAttribute("SASresponse", result);
		
		RequestDispatcher reqDispatcher = 
				request.getRequestDispatcher("listOutbox.jsp");
	    // forward request and response to jsp file
		reqDispatcher.forward(request, response);
	}

}
