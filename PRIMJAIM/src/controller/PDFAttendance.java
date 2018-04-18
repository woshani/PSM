package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

import bean.AnalysisYearAttendance;
import bean.AuditDailyAttendance;
import bean.Classroom;
import bean.DailyAttendance;
import bean.Report;
import bean.Teacher;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;



import facade.ControllerWrapper;

public class PDFAttendance {

	private Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private Font standardFont = new Font(Font.FontFamily.TIMES_ROMAN, 12);
	private static Font tableTitle = new Font(Font.FontFamily.TIMES_ROMAN, 14,
			Font.BOLD, BaseColor.WHITE);
	private static Font cellTitle = new Font(Font.FontFamily.TIMES_ROMAN, 11,
			Font.BOLD);
	private static Font cellContent = new Font(Font.FontFamily.TIMES_ROMAN, 10);


	public void addWholePage(Document document, String classId, String month,
			String year) throws Exception {

		ControllerWrapper controller = new ControllerWrapper();

		List<DailyAttendance> attendances = controller.MonthlyAttendanceList(
				month, year, classId);

		List<DailyAttendance> attendancesTransfer = controller
				.MonthlyTransferAttendanceList(month, year, classId);

		List<Report> reports = controller.MonthlyReportList(month, year,
				classId);

		List<AuditDailyAttendance> auditAttendances = controller
				.getAuditAttendance(classId, month, "20" + year);

		AnalysisYearAttendance analysis = controller.getAnalysisYearAttendance(
				month, year, classId);

		Classroom classroom = controller.getClassroomById(classId);
		
		Teacher teacher = controller.getTeacherByClassroomId(classId);

		// retrieve logo data in byte[] from academic institution id
		byte[] logoByte = controller.getPhoto(classroom.getInstituition()
				.getAcademic_instituition_id());
		byte[] logoByte2 = controller.getPhoto("JPN0000001");

		// convert byte array back to BufferedImage
		InputStream in = new ByteArrayInputStream(logoByte);
		BufferedImage logoBuffImage = ImageIO.read(in);

		Image image = Image.getInstance(logoBuffImage, null);
		// set position inside pdf
		image.setAbsolutePosition(150, 730); // Code 1
		// scale back image size
		image.scaleAbsolute(100, 100); // Code 2

		// convert byte array back to BufferedImage
		InputStream in2 = new ByteArrayInputStream(logoByte2);
		BufferedImage logoBuffImage2 = ImageIO.read(in2);

		Image image2 = Image.getInstance(logoBuffImage2, null);
		// set position inside pdf
		image2.setAbsolutePosition(950, 730); // Code 1
		// scale back image size
		image2.scaleAbsolute(100, 100); // Code 2

		/*
		 * // Logo code String imageUrl =
		 * "http://prim.utem.edu.my/images/JPNMelaka.jpg"; // create variable
		 * image and store the internet image Image image2 =
		 * Image.getInstance(new URL(imageUrl)); // set position inside pdf
		 * image2.setAbsolutePosition(950, 730); // Code 1 // scale back image
		 * size // image.scaleAbsolute(720, 644); // Code 2
		 * image2.scaleAbsolute(100, 100);
		 */

		document.add(image);
		document.add(image2);

		// declare paragraph has space line 1
		Paragraph spaceLine = new Paragraph();
		addEmptyLine(spaceLine, 1);

		String schoolName = new String(classroom.getInstituition()
				.getAcademic_name()
				+ " ("
				+ classroom.getInstituition().getAcademic_instituition_id()
				+ ")");
		Paragraph headerSchool = new Paragraph(schoolName, boldFont);
		headerSchool.setAlignment(Element.ALIGN_CENTER);
		document.add(headerSchool);

		String headerTitle = new String(
				"REKOD KEDATANGAN HARIAN SEKOLAH BULAN " + month + " TAHUN 20"
						+ year +"\nNAMA KELAS : " + classroom.getName()+"\nJENIS KELAS : "+classroom.getType()+"");
		Paragraph header = new Paragraph(headerTitle, standardFont);
		header.setAlignment(Element.ALIGN_CENTER);
		document.add(header);

		headerTitle = new String("NAMA GURU KELAS : " + teacher.getName());
		header = new Paragraph(headerTitle, standardFont);
		header.setAlignment(Element.ALIGN_CENTER);
		// header.setSpacingAfter(20);
		document.add(header);
		document.add(spaceLine);
		document.add(spaceLine);

		PdfPTable titleTable = new PdfPTable(1);
		PdfPCell c2 = new PdfPCell(new Phrase("KEDATANGAN BULANAN", tableTitle));
		c2.setBackgroundColor(BaseColor.DARK_GRAY);
		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		c2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		titleTable.addCell(c2);
		titleTable.setWidthPercentage(100);

		PdfPTable contentTable = new PdfPTable(36);

		// init column size for table transaction
		float[] columnWidths = new float[] { 100f, 1700f, 80f, 80f, 80f, 80f,
				80f, 80f, 80f, 80f, 80f, 80f, 80f, 80f, 80f, 80f, 80f, 80f,
				80f, 80f, 80f, 80f, 80f, 80f, 80f, 80f, 80f, 80f, 80f, 80f,
				80f, 80f, 80f, 300f, 300f, 300f };
		contentTable.setWidths(columnWidths);
		contentTable.setWidthPercentage(100);

		PdfPCell c1 = new PdfPCell();

		c1 = new PdfPCell(new Phrase("Bil", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("Nama Penuh", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("1", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("2", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("3", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("4", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("5", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("6", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("7", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("8", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("9", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("10", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("11", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("12", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("13", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("14", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("15", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("16", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("17", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("18", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("19", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("20", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("21", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("22", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("23", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("24", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("25", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("26", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("27", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("28", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("29", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("30", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("31", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("Bulan Ini", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("Bulan Sudah", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("Akhir Bulan", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable.addCell(c1);

		int count = 0;

		for (DailyAttendance attendance : attendances) {

			count = count + 1;

			c1 = new PdfPCell(new Phrase(String.valueOf(count + "."),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getStudent()
					.getName()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD01()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD02()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD03()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD04()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD05()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD06()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD07()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD08()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD09()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD10()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD11()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD12()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD13()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD14()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD15()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD16()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD17()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD18()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD19()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD20()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD21()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD22()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD23()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD24()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD25()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD26()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD27()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD28()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD29()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD30()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance.getD31()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance
					.getThisMonth()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance
					.getPreviousMonth()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendance
					.getEndMonth()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);
		}

		for (DailyAttendance attendanceTransfer : attendancesTransfer) {

			count = count + 1;

			c1 = new PdfPCell(new Phrase(String.valueOf(count + "."),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf("*** "
					+ attendanceTransfer.getStudent().getName()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD01()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD02()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD03()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD04()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD05()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD06()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD07()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD08()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD09()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD10()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD11()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD12()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD13()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD14()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD15()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD16()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD17()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD18()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD19()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD20()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD21()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD22()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD23()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD24()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD25()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD26()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD27()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD28()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD29()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD30()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getD31()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getThisMonth()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getPreviousMonth()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(attendanceTransfer
					.getEndMonth()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);
		}

		for (int index = 0; index < 3; index++) {

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);
		}

		for (Report report : reports) {

			c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getItem()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD01()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD02()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD03()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD04()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD05()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD06()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD07()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD08()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD09()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD10()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD11()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD12()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD13()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD14()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD15()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD16()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD17()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD18()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD19()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD20()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD21()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD22()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD23()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD24()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD25()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD26()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD27()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD28()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD29()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD30()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getD31()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getThisMonth()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report
					.getPreviousMonth()), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(report.getEndMonth()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);
		}

		Paragraph p1 = new Paragraph("*** Pelajar Berpindah.");

		document.add(titleTable);
		document.add(contentTable);
		document.add(p1);
		document.newPage();

		PdfPTable titleTable2 = new PdfPTable(1);
		titleTable2.setWidthPercentage(100);
		PdfPCell c3 = new PdfPCell(new Phrase("RINGKASAN KEDATANGAN BULANAN",
				tableTitle));
		c3.setBackgroundColor(BaseColor.DARK_GRAY);
		c3.setHorizontalAlignment(Element.ALIGN_CENTER);
		titleTable2.addCell(c3);

		PdfPTable contentTable2 = new PdfPTable(4);

		float[] columnWidths2 = new float[] { 1000f, 1000f, 1000f, 1000f };
		contentTable2.setWidths(columnWidths2);
		contentTable2.setWidthPercentage(100);

		PdfPCell c4 = new PdfPCell();
		c4 = new PdfPCell(new Phrase("Hitung Panjang Kedatangan", cellTitle));
		c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c4.setHorizontalAlignment(Element.ALIGN_CENTER);
		c4.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable2.addCell(c4);

		c4 = new PdfPCell(new Phrase("Peratusan Kedatangan", cellTitle));
		c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c4.setHorizontalAlignment(Element.ALIGN_CENTER);
		c4.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable2.addCell(c4);

		c4 = new PdfPCell(new Phrase("Hitung Panjang Ramai Pelajar/Murid",
				cellTitle));
		c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c4.setHorizontalAlignment(Element.ALIGN_CENTER);
		c4.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable2.addCell(c4);

		c4 = new PdfPCell(new Phrase("Jumlah Persekolahan", cellTitle));
		c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c4.setHorizontalAlignment(Element.ALIGN_CENTER);
		c4.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable2.addCell(c4);

		c4 = new PdfPCell(new Phrase(String.valueOf(analysis.getAttendance()),
				cellContent));
		c4.setHorizontalAlignment(Element.ALIGN_CENTER);
		c4.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable2.addCell(c4);

		c4 = new PdfPCell(new Phrase(String.valueOf(analysis
				.getAttendance_percentage() + "%"), cellContent));
		c4.setHorizontalAlignment(Element.ALIGN_CENTER);
		c4.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable2.addCell(c4);

		c4 = new PdfPCell(new Phrase(
				String.valueOf(analysis.getNo_of_student()), cellContent));
		c4.setHorizontalAlignment(Element.ALIGN_CENTER);
		c4.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable2.addCell(c4);

		c4 = new PdfPCell(new Phrase(String.valueOf(analysis.getTotal_day()),
				cellContent));
		c4.setHorizontalAlignment(Element.ALIGN_CENTER);
		c4.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable2.addCell(c4);

		document.add(titleTable2);
		document.add(contentTable2);
		document.add(spaceLine);

		PdfPTable titleTable3 = new PdfPTable(1);
		titleTable3.setWidthPercentage(100);
		PdfPCell c5 = new PdfPCell(new Phrase("LOG KEDATANGAN", tableTitle));
		c5.setBackgroundColor(BaseColor.DARK_GRAY);
		c5.setHorizontalAlignment(Element.ALIGN_CENTER);
		c5.setVerticalAlignment(Element.ALIGN_MIDDLE);
		titleTable3.addCell(c5);

		PdfPTable contentTable3 = new PdfPTable(6);

		float[] columnWidths3 = new float[] { 200f, 1800f, 800f, 800f, 700f,
				700f };
		contentTable3.setWidths(columnWidths3);
		contentTable3.setWidthPercentage(100);

		PdfPCell c6 = new PdfPCell();

		c6 = new PdfPCell(new Phrase("Bil", cellTitle));
		c6.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c6.setHorizontalAlignment(Element.ALIGN_CENTER);
		c6.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable3.addCell(c6);

		c6 = new PdfPCell(new Phrase("Nama Pelajar", cellTitle));
		c6.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c6.setHorizontalAlignment(Element.ALIGN_CENTER);
		c6.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable3.addCell(c6);

		c6 = new PdfPCell(new Phrase("Tarikh Audit", cellTitle));
		c6.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c6.setHorizontalAlignment(Element.ALIGN_CENTER);
		c6.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable3.addCell(c6);

		c6 = new PdfPCell(new Phrase("Tarikh Kedatangan", cellTitle));
		c6.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c6.setHorizontalAlignment(Element.ALIGN_CENTER);
		c6.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable3.addCell(c6);

		c6 = new PdfPCell(new Phrase("Status Sebelum", cellTitle));
		c6.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c6.setHorizontalAlignment(Element.ALIGN_CENTER);
		c6.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable3.addCell(c6);

		c6 = new PdfPCell(new Phrase("Status Selepas", cellTitle));
		c6.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c6.setHorizontalAlignment(Element.ALIGN_CENTER);
		c6.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable3.addCell(c6);

		int indexc6 = 0;

		for (AuditDailyAttendance auditAttendance : auditAttendances) {

			indexc6 = indexc6 + 1;

			c6 = new PdfPCell(new Phrase(String.valueOf(indexc6 + "."),
					cellContent));
			c6.setHorizontalAlignment(Element.ALIGN_CENTER);
			c6.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable3.addCell(c6);

			c6 = new PdfPCell(new Phrase(String.valueOf(auditAttendance
					.getStudentClass().getStudent().getName()), cellContent));
			c6.setHorizontalAlignment(Element.ALIGN_LEFT);
			c6.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable3.addCell(c6);

			c6 = new PdfPCell(new Phrase(String.valueOf(auditAttendance
					.getAuditDate()), cellContent));
			c6.setHorizontalAlignment(Element.ALIGN_CENTER);
			c6.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable3.addCell(c6);

			c6 = new PdfPCell(new Phrase(String.valueOf(auditAttendance
					.getAttendanceDate()), cellContent));
			c6.setHorizontalAlignment(Element.ALIGN_CENTER);
			c6.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable3.addCell(c6);

			c6 = new PdfPCell(new Phrase(String.valueOf(auditAttendance
					.getStatusBefore()), cellContent));
			c6.setHorizontalAlignment(Element.ALIGN_CENTER);
			c6.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable3.addCell(c6);

			c6 = new PdfPCell(new Phrase(String.valueOf(auditAttendance
					.getStatusAfter()), cellContent));
			c6.setHorizontalAlignment(Element.ALIGN_CENTER);
			c6.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable3.addCell(c6);
		}

		document.add(titleTable3);
		document.add(contentTable3);
		
		document.add(spaceLine);
		document.add(spaceLine);
		document.add(spaceLine);
				
		PdfPTable verificationTable = new PdfPTable(3);
		float[] columnWidthsVTable = new float[] { 1500f, 1500f, 1500f };
		verificationTable.setWidths(columnWidthsVTable);
		verificationTable.setWidthPercentage(100);
		
		
				
		c1 = new PdfPCell(new Phrase("Disediakan Oleh (Guru Kelas) : \n\n\n\n\n"
				+ "........................................................."
				+ "...........................", boldFont));c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_CENTER);
		c1.setFixedHeight(130);
		
		verificationTable.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Disemak Oleh \n(Guru Penolong Kanan Hal Ehwal Murid) : \n\n\n\n"
				+ "........................................................."
				+ "...........................", boldFont));c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_CENTER);
		c1.setFixedHeight(130);
		
		verificationTable.addCell(c1);
		
		
		c1 = new PdfPCell(new Phrase("Disahkan Oleh (Guru Besar / Pengetua) : \n\n\n\n\n"
				+ "........................................................."
				+ "...........................", boldFont));c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_CENTER);
		c1.setFixedHeight(130);
		
		verificationTable.addCell(c1);
		document.add(verificationTable);

	}

	// additional method to add new space
	public void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

}
