package servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

/**
 * Servlet implementation class GCMNotification
 */
@WebServlet("/GCMNotification")
public class GCMNotification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Put your Google API Server Key here
	private static final String GOOGLE_SERVER_KEY = "AIzaSyCJq5djon9SwMYf4EEA4Yy3xNUDxDHwrpo";
	static final String MESSAGE_KEY = "message";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GCMNotification() {
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

		Result result = null;

		String share = request.getParameter("shareRegId");

		// GCM RedgId of Android device to send push notification
		String regId = "c5HXuA5ZOyE:APA91bF1ladPBfE2SaySz14p9ixfaDX0OQia9mdCk6ySLI9MQ8mSShm6hW1cXYMETvcxZjOQ1mYJ7kJ6lHLTFRyxU-9T1tPjz2apwuUJLVeehQi1VkPtHcYL4QBf7gM3qdba7ml_JE-H";
		if (share != null && !share.isEmpty()) {
			regId = request.getParameter("regId");
			PrintWriter writer = new PrintWriter("GCMRegId.txt");
			writer.println(regId);
			writer.close();
			request.setAttribute("pushStatus", "GCM RegId Received.");
			
		} else {

			try {
				BufferedReader br = new BufferedReader(new FileReader(
						"GCMRegId.txt"));
				regId = br.readLine();
				br.close();
				String userMessage = request.getParameter("message");
				Sender sender = new Sender(GOOGLE_SERVER_KEY);
				Message message = new Message.Builder().timeToLive(30)
						.delayWhileIdle(true).addData(MESSAGE_KEY, userMessage)
						.build();
				System.out.println("regId: " + regId);
				result = sender.send(message, regId, 1);
				request.setAttribute("pushStatus", result.toString());
			} catch (IOException ioe) {
				ioe.printStackTrace();
				request.setAttribute("pushStatus",
						"RegId required: " + ioe.toString());
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("pushStatus", e.toString());
			}
			
		}
	}

}
