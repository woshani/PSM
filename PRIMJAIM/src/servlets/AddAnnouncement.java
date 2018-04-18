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

import bean.Announcement;
import bean.Classroom;
import bean.Dun;
import bean.Instituition;
import bean.Message;
import bean.Station;
import bean.Student;
import bean.User;
import facade.ControllerWrapper;

/**
 * Servlet implementation class AddAnnouncement
 */
@WebServlet("/AddAnnouncement")
public class AddAnnouncement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAnnouncement() {
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
		ControllerWrapper controller = new ControllerWrapper();
		Announcement announcement = new Announcement();
		
		//polis and dun part
		Announcement announcementPolice = new Announcement();
		Announcement announcementDun = new Announcement();

		HttpSession session = request.getSession();
		
		User announcer = (User) session.getAttribute("SASuser");
		String subject = request.getParameter("subject");
		String text = request.getParameter("text");
		String type = request.getParameter("type");
		
		announcement.setAnnouncer(announcer);
		announcement.setSubject(subject);
		announcement.setText(text);
		announcement.setType(type);
		
		//police
		announcementPolice.setAnnouncer(announcer);
		announcementPolice.setSubject("Salinan POLIS : "+ subject );
		announcementPolice.setText(text);
		announcementPolice.setType("P");
		
		//dun
		announcementDun.setAnnouncer(announcer);
		announcementDun.setSubject("Salinan ADUN : "+subject);
		announcementDun.setText(text);
		announcementDun.setType("D");
		
		try {
			if(!type.equals("A")) {
				String target = request.getParameter("target");
				if(type.equals("I")) {
					if(controller.checkInstituitionId(target) > 0) {
						Instituition institution = controller.getInstituitionById(target);
						announcement.setTarget(institution);
						
						Station station = controller.getStationByInstitutionId(target);
						announcementPolice.setTarget(station);
						
						Dun dun = controller.getDunByInstitutionId(target);
						announcementDun.setTarget(dun);
					}
				} else if(type.equals("C")) {
					if(controller.checkClassroomId(target) > 0) {
						Classroom classroom = controller.getClassroomById(target);
						announcement.setTarget(classroom);
						
						Station station = controller.getStationByClassId(target);
						announcementPolice.setTarget(station);
						
						
						Dun dun = controller.getDunByClassId(target);
						announcementDun.setTarget(dun);
					}
				} else if(type.equals("S")) {
					if(controller.checkStudentId(target) > 0) {
						Student student = controller.getStudentById(target);
						announcement.setTarget(student);
						
						Station station = controller.getStationByStudentId(target);
						announcementPolice.setTarget(station);
						
						Dun dun = controller.getDunByStudentId(target);
						announcementDun.setTarget(dun);
					}
				}
			}
			
			if(announcement.getTarget() != null) {
				
				System.out.println("POLIS :"+announcementPolice.getTarget()+" : "+announcementPolice.getType());
				System.out.println("NORMAL :"+announcement.getTarget());
				controller.addNewAnnouncement(announcement);
				
				controller.addNewAnnouncement(announcementPolice);
				controller.addNewAnnouncement(announcementDun);
				
				System.out.println("target:"+request.getParameter("target")+":");
				
				
				
				
				
				/*// start modification
				// ??? get instituitionid
				String[] announcement_ids = controller.addNewAnnouncement(announcement);
								
				List<Announcement> announcements = new ArrayList<Announcement>();
				
					// ??? get instituitionid
					String instituitionId = request.getParameter("instituitionId");
				
				Instituition instituition = null;
				
				try {
					instituition = controller.getInstituitionById(instituitionId);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String result = "No Response";

				// get all annoucement_ids need to be send from c_list_annoucements highlighted
				for(int index=0; index<announcement_ids.length; index++) {
					try {
						Announcement announcement1 = 
								controller.getAnnouncementById(announcement_ids[index]);
						
						announcements.add(announcement1);
					} catch (Exception e) {
						result = "Exception occured when getting outgoing sms information.";
						e.printStackTrace();
					}
				}
				
				try {
					List<Message> messages = 
							controller.getAllMessagesByAnnouncements(announcements);
					result = controller.sendSMSs(messages,instituition.getAcademic_instituition_id());
				} catch (Exception e) {
					result = "Exception occured when sending messages.";
					e.printStackTrace();
				}
				
				request.setAttribute("SASresponse", result);
				
				// end modification				
*/				
				RequestDispatcher reqDispatcher = 
						request.getRequestDispatcher("listAnnouncement.jsp");
				reqDispatcher.forward(request, response);
			} else {
				request.setAttribute("SASinvalidTarget", "Y");
				RequestDispatcher reqDispatcher = 
						request.getRequestDispatcher("addAnnouncement.jsp");
				reqDispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
