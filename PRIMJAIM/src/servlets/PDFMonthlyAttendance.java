package servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import controller.PDFAttendance;
import controller.ReportFooterPageEvent;
import bean.Classroom;
import facade.ControllerWrapper;

/**
 * Servlet implementation class PDFMonthlyAttendance
 */
@WebServlet("/PDFMonthlyAttendance")
public class PDFMonthlyAttendance extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PDFMonthlyAttendance() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String classId = request.getParameter("id");
			String month = request.getParameter("month");
			

			SimpleDateFormat fromUser = new SimpleDateFormat("MMMMM yyyy");
			SimpleDateFormat fromUserTitle = new SimpleDateFormat("MMMMM_yyyy");
			SimpleDateFormat myFormat = new SimpleDateFormat("MMyy");

			String formatedMonthTitle = fromUserTitle.format(fromUser.parse(month));
			String formatedMonth = myFormat.format(fromUser.parse(month));
			String newMonth = formatedMonth.substring(0, 2);
			String newYear = formatedMonth.substring(2, 4);

			ControllerWrapper controller = new ControllerWrapper();
			Classroom classroom = controller.getClassroomById(classId);

			Document document = new Document(PageSize.A3.rotate());

			// this will generate and store the content of outfile
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			// initialize the pdf writer to add data to pdf
			PdfWriter writer = PdfWriter.getInstance(document, buffer);
			// a string used to set default value to the file
			String fileName = "Kedatangan_" + classroom.getName() + "_Bulan_" + formatedMonthTitle + ".pdf";
			// declare an object from java file
			PDFAttendance pdf = new PDFAttendance();
						
			Rectangle rect = new Rectangle(180, 25, 920, 1050);
			
	        writer.setBoxSize("art", rect);
	        ReportFooterPageEvent event = new ReportFooterPageEvent();
	        writer.setPageEvent(event);
			// open document
			document.open();
			// use the object's method to add content of the file

			pdf.addWholePage(document, classId, newMonth, newYear);

			document.close();

			// Download prompt method
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			// prompt file name. by default. we set the name as report. refer
			// string above
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.setContentType("application/pdf");
			response.setContentLength(buffer.size());

			// write the output out
			ServletOutputStream output = response.getOutputStream();
			buffer.writeTo(output);
			output.flush();
			output.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
	}

}
