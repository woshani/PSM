package servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

import facade.ControllerWrapper;

/**
 * Servlet implementation class PDFWarningLetter
 */
@WebServlet("/PDFWarningLetter")
public class PDFWarningLetter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PDFWarningLetter() {
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
		try {
			String studentId = request.getParameter("id");
			String warning = request.getParameter("warning");
			String whId = request.getParameter("wh");

			ControllerWrapper controller = new ControllerWrapper();

			Student student = controller.getStudentById(studentId);

			Document document = new Document();

			// this will generate and store the content of outfile
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			// initialize the pdf writer to add data to pdf
			PdfWriter.getInstance(document, buffer);
			// a string used to set default value to the file
			String fileName = student.getName() + "_" + warning + ".pdf";
			// declare an object from java file
			controller.PDFWarningLetter pdf = new controller.PDFWarningLetter();
			// open document
			document.open();
			// use the object's method to add content of the file
			pdf.addWholePage(document, studentId, whId);
			document.close();

			// Download prompt method
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control",
					"must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			// prompt file name. by default. we set the name as report. refer
			// string above
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ fileName + "\"");
			response.setContentType("application/pdf");
			response.setContentLength(buffer.size());

			// write the output out
			ServletOutputStream out1 = response.getOutputStream();
			buffer.writeTo(out1);
			out1.flush();
			out1.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
