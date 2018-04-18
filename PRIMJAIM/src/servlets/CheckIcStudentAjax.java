package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;

import bean.Student;
import facade.ControllerWrapper;

/**
 * Servlet implementation class CheckIcStudentAjax
 */
@WebServlet("/CheckIcStudentAjax")
public class CheckIcStudentAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckIcStudentAjax() {
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
			
			JSONObject json = new JSONObject();
			PrintWriter out = response.getWriter();
							
			String studentIc = request.getParameter("studentIc");
			Student student = new Student();

			try{
				
			
			if (studentIc == "") {			
				
				json.put("statusIcStudent", 0);			

				

			} else {

				// check ic student status
				ControllerWrapper ctrlWrap = new ControllerWrapper();

				int result = 0;

				// check student ic length
				if (studentIc.length() == 12) {

					try {

						result = ctrlWrap.checkStudentIc(studentIc);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// 0 = new student (no data recorded)
					if (result == 0) {
										
						json.put("statusIcStudent", 1);
											
						
						// 1 = already recorded
					} else if (result == 1) {
						
						json.put("statusIcStudent", 1);
											
						try {
							student = ctrlWrap.getStudentByIc(studentIc);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						
						
					} else if(result==2){
						
						json.put("statusIcStudent", 2);
						
						
					} else{

						json.put("statusIcStudent", 0);
						
						// set status = 1, student already registered and have class
						
					}

				} else {
					
					json.put("statusIcStudent", 0);

				}

			}
			out.print(json);
			
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}

}
