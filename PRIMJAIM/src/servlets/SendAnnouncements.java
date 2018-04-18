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

import service.NotificationSystem;
import controller.URLSecurity;
import bean.Announcement;
import bean.Instituition;
import bean.Message;
import facade.ControllerWrapper;

/**
 * Servlet implementation class SendAnnouncements
 */
@WebServlet("/SendAnnouncements")
public class SendAnnouncements extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendAnnouncements() {
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
		
		ControllerWrapper controller = new ControllerWrapper();
		List<Announcement> announcements = new ArrayList<Announcement>();
		
		String instituitionIdeEnc = request.getParameter("instituitionId");
		
		
		Instituition instituition = null;
		
		try {
			String instituitionId = URLSecurity.decryptFinal(instituitionIdeEnc, pinKey);
			instituition = controller.getInstituitionById(instituitionId);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String[] announcement_ids = request.getParameterValues("ann_id");
		
		String result = "No Response";
		String resultNotification = "No Response";

		// get all annoucement_ids need to be send from c_list_annoucements highlighted
		for(int index=0; index<announcement_ids.length; index++) {
			System.out.println(announcement_ids[index]);
			try {
				Announcement announcement = 
						controller.getAnnouncementById(announcement_ids[index]);
				
				announcements.add(announcement);
			} catch (Exception e) {
				result = "Exception occured when getting outgoing sms information.";
				e.printStackTrace();
			}
		}
		
		
		try {
			
			//get all messages by announcement (guardianid, message, title, ) and update announcement status.
			//trigger also run to capture data into table messages
			List<Message> messages = 
					controller.getAllMessagesByAnnouncements(announcements);
			
						
			//process send sms
			//result = controller.sendSMSs(messages, instituition.getAcademic_instituition_id());
			result = "All Announcement has been send!";
			
			resultNotification = controller.sendNotificationMulti(messages, instituition.getAcademic_instituition_id());
			
		} catch (Exception e) {
			result = "Exception occured when sending messages.";
			e.printStackTrace();
		}
		
		request.setAttribute("SASresponse", result);
		
		RequestDispatcher reqDispatcher = 
				request.getRequestDispatcher("listAnnouncement.jsp");
	    // forward request and response to jsp file
		reqDispatcher.forward(request, response);
	}

}
