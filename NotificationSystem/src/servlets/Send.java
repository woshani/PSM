package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.NotificationMessage;
import bean.Project;
import controller.NotificationController;
import controller.ProjectController;
import controller.TokenController;

/**
 * Servlet implementation class Send
 */
@WebServlet("/Send")
public class Send extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Send() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
							
		// GET ARRAY OF USERID FROM APPS AND CONVERT INTO ARRAY LIST STRING
		String[] userIds = request.getParameterValues("userId");
		String spId = request.getParameter("p");
		String textTitle = request.getParameter("t");
		String textMessage = request.getParameter("m");
				
		String combineMessage = textTitle + " - " + textMessage;
		
		int pId = Integer.parseInt(spId);
		
		//filter id, only registered with token will be added in arraylist
		
		//get token from id include filter only available id will send		
		TokenController tokenCtrl = new TokenController();
		
		ArrayList<String> receiverIds = new ArrayList<String>();
		
		int countTotal = userIds.length;
		int countSend = 0;
		int countFailed =0;
		
		for(int i =0; i < userIds.length; i++){
			int currentProcessNumber = i+1;
			
			try {
				String result = tokenCtrl.getTokenFromUserId(userIds[i], pId);
				if (result.contentEquals("0")||result.isEmpty()){
					countFailed = countFailed + 1;
					
					System.out.println("Notification System : Send Servlet : Checking Token Id : "+currentProcessNumber+" /"+userIds.length+" : Not Found");
				}else{
					receiverIds.add(result);
					countSend = countSend + 1;
					System.out.println("Notification System : Send Servlet : Checking Token Id : "+currentProcessNumber+" /"+userIds.length+" : "+result);
				}
				
				//System.out.println(result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("USER TOTAL :"+countTotal);
		System.out.println("USER OK :"+countSend);
		System.out.println("USER UNKNOWN :"+countFailed);
			
		//get project detail (authToken and name)
		ProjectController projCtrl = new ProjectController();
		Project project = new Project();
		
		project = projCtrl.getAuthToken(pId);

		

		NotificationMessage message = new NotificationMessage(project.getName()
				+ " System: ", combineMessage, project);

		NotificationController nc = new NotificationController();
		try {
			nc.pushFCMNotificationMulti(receiverIds, message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
