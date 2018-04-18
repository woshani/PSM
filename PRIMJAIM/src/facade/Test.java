package facade;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import com.itextpdf.text.Document;

import connection.DBConnection;
import controller.AttendanceController;
import controller.PDFAttendance;
import controller.PDFHemAttendance;
import controller.StudentController;
import bean.Classroom;
import bean.DailyAttendance;
import bean.SchoolAttendance;
import bean.Student;

public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		DBConnection dbConn = new DBConnection();
		Connection conn;
		conn = dbConn.getConnection();
		
		ControllerWrapper controller = new ControllerWrapper();
		AttendanceController attCtrl = new AttendanceController();
		DailyAttendance attendance = new DailyAttendance();
		
		//List<Student> students = controller.getStudentsByInstituitionId("MCT2001");
		/*String i = controller.getInstituitionByTeacherId("GPKH100001"); 
		
		System.out.print("Size :" + i +":");*/
		
		int num = controller.getSchoolAttendancePercentage("MCT2001");
		
		System.out.print(num);
		

	}

}
