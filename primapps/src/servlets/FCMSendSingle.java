package servlets;

import google.FCM;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class FCMSendSingle
 */
@WebServlet("/FCMSendSingle")
public class FCMSendSingle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FCMSendSingle() {
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
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		FCM fcm = new FCM();

		List<String> putIds;

		String tokenId1 = "c5HXuA5ZOyE:APA91bF1ladPBfE2SaySz14p9ixfaDX0OQia9mdCk6ySLI9MQ8mSShm6hW1cXYMETvcxZjOQ1mYJ7kJ6lHLTFRyxU-9T1tPjz2apwuUJLVeehQi1VkPtHcYL4QBf7gM3qdba7ml_JE-H";
		String senderTokenId = "c5HXuA5ZOyE:APA91bF1ladPBfE2SaySz14p9ixfaDX0OQia9mdCk6ySLI9MQ8mSShm6hW1cXYMETvcxZjOQ1mYJ7kJ6lHLTFRyxU-9T1tPjz2apwuUJLVeehQi1VkPtHcYL4QBf7gM3qdba7ml_JE-H";
		String server_key = "AAAA8nKsqc0:APA91bHAgh2_CY_dwxdh330wlI2OH78OK7pCr74SNQn_X3n_0s2oQYDYXWr6qgqzE6Y6d0qkoUcDl9ynSorAyWv6okSTBFY64gFPKI6ZIJzJjsQ-cBZIJC8gKJ58b6serrazpapzAvt9";
		String message = "Welcome EDAlert Push Service 2017.";

		putIds = new ArrayList<>();
		putIds.add(tokenId1);
		putIds.add(senderTokenId);

		/* for Group */
		//fcm.send_FCM_NotificationMulti(putIds, senderTokenId, server_key, message);

		/* for individual */
		JSONObject json = new JSONObject();
		
		fcm.send_FCM_Notification(tokenId1, server_key, message);

	}

}
