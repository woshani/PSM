package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.NotificationMessage;
import bean.Project;
import bean.PushNoti;
import controller.MobileController;
import controller.NotificationController;

/**
 * Servlet implementation class SendPushNoti
 */
@WebServlet("/SendPushNoti")
public class SendPushNoti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendPushNoti() {
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
		doGet(request, response);
		response.setContentType("text/plain"); 
		PrintWriter out= response.getWriter();
		String sessions = request.getParameter("sessions");
		String sender = request.getParameter("sender");
		//out.print(sessions);
		MobileController mc = new MobileController();
		ArrayList<PushNoti> arPn = mc.getPushNoti(sessions,sender);
		
		int status = 0;
		
		try {
			for(int i = 0;i<arPn.size();i++) {
				status = NotificationController.pushFCMNotification(arPn.get(i).getToken(), arPn.get(i).getMsj());
				System.out.print(status);
			}
			mc.insertMessage(arPn);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(status==200) {
			out.print("OKAY");
		}else {
			out.print("UHOH");
		}
	}

}
