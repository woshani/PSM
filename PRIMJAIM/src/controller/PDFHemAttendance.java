package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import bean.Classroom;
import bean.Instituition;
import bean.SchoolAttendance;
import bean.Teacher;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import facade.ControllerWrapper;

public class PDFHemAttendance {

	private Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private Font standardFont = new Font(Font.FontFamily.TIMES_ROMAN, 12);
	private static Font tableTitle = new Font(Font.FontFamily.TIMES_ROMAN, 14,
			Font.BOLD, BaseColor.WHITE);
	private static Font cellTitle = new Font(Font.FontFamily.TIMES_ROMAN, 11,
			Font.BOLD);
	private static Font cellContent = new Font(Font.FontFamily.TIMES_ROMAN, 10);

	public void addWholePage(Document document, String teacherId, String month,
			String year) throws Exception {

		ControllerWrapper controller = new ControllerWrapper();

		List<Classroom> classrooms = controller.getClassBySchool(teacherId);

		Instituition instituition = controller.getInstituitionById(controller
				.getInstituitionByTeacherId(teacherId));

		List<SchoolAttendance> schoolAttendances = new ArrayList<SchoolAttendance>();

		Teacher teacher = new Teacher();

		int check = 0;
		int index = 1;
		int bil = 1;

		// retrieve logo data in byte[] from academic institution id
		byte[] logoByte = controller.getPhoto(instituition
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

		String schoolName = new String(instituition.getAcademic_name() + " ("
				+ instituition.getAcademic_instituition_id() + ")");
		Paragraph headerSchool = new Paragraph(schoolName, boldFont);
		headerSchool.setAlignment(Element.ALIGN_CENTER);
		document.add(headerSchool);

		String headerTitle = new String(
				"Rekod Kedatangan Bulanan Sekolah Bagi Bulan " + month
						+ " Tahun 20" + year);
		Paragraph header = new Paragraph(headerTitle, standardFont);
		header.setAlignment(Element.ALIGN_CENTER);
		document.add(header);

		headerTitle = new String("Tarikh dijana : " + controller.getTodayDate()).replace("-", "/");
		header = new Paragraph(headerTitle, standardFont);
		header.setAlignment(Element.ALIGN_CENTER);
		header.setSpacingAfter(20);
		document.add(header);
		document.add(spaceLine);
		document.add(spaceLine);

		PdfPTable titleTable = new PdfPTable(1);
		PdfPCell c2 = new PdfPCell(new Phrase("KEDATANGAN BULANAN SEKOLAH",
				tableTitle));
		c2.setBackgroundColor(BaseColor.DARK_GRAY);
		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		c2.setVerticalAlignment(Element.ALIGN_JUSTIFIED);
		titleTable.addCell(c2);
		titleTable.setWidthPercentage(100);

		PdfPTable contentTable = new PdfPTable(36);

		float[] columnWidths = new float[] { 150f, 500f, 1050f, 300f, 300f,
				100f, 100f, 100f, 100f, 100f, 100f, 100f, 100f, 100f, 100f,
				100f, 100f, 100f, 100f, 100f, 100f, 100f, 100f, 100f, 100f,
				100f, 100f, 100f, 100f, 100f, 100f, 100f, 100f, 100f, 100f,
				100f };
		contentTable.setWidths(columnWidths);
		contentTable.setWidthPercentage(100);

		PdfPCell c1 = new PdfPCell();

		c1 = new PdfPCell(new Phrase("BIL", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_BOTTOM);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("KELAS", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_BOTTOM);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("NAMA GURU", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_BOTTOM);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("STATUS", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_BOTTOM);
		contentTable.addCell(c1);

		c1 = new PdfPCell(new Phrase("JANTINA", cellTitle));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_BOTTOM);
		contentTable.addCell(c1);

		do {

			c1 = new PdfPCell(new Phrase(String.valueOf(index), cellTitle));
			c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_BOTTOM);
			contentTable.addCell(c1);

			index = index + 1;

		} while (index < 32);

		for (Classroom classroom : classrooms) {

			teacher = controller.getTeacherByClassroomId(classroom
					.getClass_id());

			c1 = new PdfPCell(
					new Phrase(String.valueOf(bil + "."), cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(classroom.getName()+"\n("+classroom.getType()+")"),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			c1 = new PdfPCell(new Phrase(String.valueOf(teacher.getName()),
					cellContent));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			contentTable.addCell(c1);

			schoolAttendances = controller.jumlahKedatanganBulananSekolah(
					classroom.getClass_id(), month, year);
			check = 0;

			if (schoolAttendances == null) {

				do {

					if (check > 0) {

						c1 = new PdfPCell(new Phrase(String.valueOf(" "),
								cellContent));
						c1.setHorizontalAlignment(Element.ALIGN_LEFT);
						c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
						contentTable.addCell(c1);

						c1 = new PdfPCell(new Phrase(String.valueOf(" "),
								cellContent));
						c1.setHorizontalAlignment(Element.ALIGN_LEFT);
						c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
						contentTable.addCell(c1);

						c1 = new PdfPCell(new Phrase(String.valueOf(" "),
								cellContent));
						c1.setHorizontalAlignment(Element.ALIGN_LEFT);
						c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
						contentTable.addCell(c1);

					}

					index = 0;
					do {

						c1 = new PdfPCell(new Phrase(String.valueOf(" "),
								cellContent));
						c1.setHorizontalAlignment(Element.ALIGN_LEFT);
						c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
						contentTable.addCell(c1);

						index = index + 1;
					} while (index < 33);

					check++;
				} while (check < 4);
			}

			for (SchoolAttendance schoolAttendance : schoolAttendances) {

				if (check > 0) {

					c1 = new PdfPCell(new Phrase(String.valueOf(" "),
							cellContent));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);
					c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
					contentTable.addCell(c1);

					c1 = new PdfPCell(new Phrase(String.valueOf(" "),
							cellContent));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);
					c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
					contentTable.addCell(c1);

					c1 = new PdfPCell(new Phrase(String.valueOf(" "),
							cellContent));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);
					c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
					contentTable.addCell(c1);

				}

				if (check == 4) {

					c1 = new PdfPCell(new Phrase(String.valueOf(" "),
							cellContent));
					c1.setHorizontalAlignment(Element.ALIGN_LEFT);
					c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
					contentTable.addCell(c1);

					c1 = new PdfPCell(new Phrase(String.valueOf("JUMLAH"),
							cellContent));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
					contentTable.addCell(c1);

				} else {

					if (schoolAttendance.getStatus().equalsIgnoreCase("/")) {
						c1 = new PdfPCell(new Phrase(String.valueOf("Hadir"),
								cellContent));
						c1.setHorizontalAlignment(Element.ALIGN_CENTER);
						c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
						contentTable.addCell(c1);
					} else if (schoolAttendance.getStatus().equalsIgnoreCase(
							"O")) {
						c1 = new PdfPCell(new Phrase(
								String.valueOf("Tidak Hadir"), cellContent));
						c1.setHorizontalAlignment(Element.ALIGN_CENTER);
						c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
						contentTable.addCell(c1);
					} else if (schoolAttendance.getStatus().equalsIgnoreCase(
							"-")) {
						c1 = new PdfPCell(new Phrase(
								String.valueOf("Belum Direkod"), cellContent));
						c1.setHorizontalAlignment(Element.ALIGN_CENTER);
						c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
						contentTable.addCell(c1);
					}

					c1 = new PdfPCell(new Phrase(
							String.valueOf(schoolAttendance.getGender()),
							cellContent));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
					contentTable.addCell(c1);

				}

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD01()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD02()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD03()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD04()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD05()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD06()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD07()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD08()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD09()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD10()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD11()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD12()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD13()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD14()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD15()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD16()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD17()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD18()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD19()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD20()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD21()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD22()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD23()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD24()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD25()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD26()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD27()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD28()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD29()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD30()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				c1 = new PdfPCell(new Phrase(String.valueOf(schoolAttendance
						.getD31()), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				check = check + 1;

			}

			index = 0;
			do {
				c1 = new PdfPCell(new Phrase(String.valueOf(" "), cellContent));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				contentTable.addCell(c1);

				index = index + 1;
			} while (index < 36);

			bil = bil + 1;
		}

		document.add(titleTable);
		document.add(contentTable);
		document.newPage();
		
		PdfPTable verificationTable = new PdfPTable(3);
		float[] columnWidthsVTable = new float[] { 1500f, 1500f, 1500f };
		verificationTable.setWidths(columnWidthsVTable);
		verificationTable.setWidthPercentage(100);
		
		
				
		c1 = new PdfPCell(new Phrase("", cellTitle));
		c1.disableBorderSide(Rectangle.BOX);c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_CENTER);
		c1.setFixedHeight(200);
		
		verificationTable.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("", cellTitle));
		c1.disableBorderSide(Rectangle.BOX);c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_CENTER);
		c1.setFixedHeight(200);
		
		verificationTable.addCell(c1);
		
		
		c1 = new PdfPCell(new Phrase("Disahkan Oleh (Guru Besar / Pengetua) : \n\n\n\n\n\n\n"
				+ "........................................................."
				+ "...............................................", boldFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_CENTER);
		c1.setFixedHeight(200);
		
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
