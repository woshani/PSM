package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

import bean.Classroom;
import bean.Guardian;
import bean.Monitor;
import bean.Teacher;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.lowagie.text.Chunk;
import com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithm.WordListener;

import facade.ControllerWrapper;

public class PDFWarningLetter {

	private Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private Font boldUnderlineFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD|Font.UNDERLINE);
	private Font standardFont = new Font(Font.FontFamily.TIMES_ROMAN, 12);
	private static Font tableTitle = new Font(Font.FontFamily.TIMES_ROMAN, 13,
			Font.BOLD, BaseColor.WHITE);
	private static Font cellContent = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);

	public void addWholePage(Document document, String studentId, String whId)
			throws Exception {

		ControllerWrapper controller = new ControllerWrapper();

		Monitor monitor = new Monitor();
		Teacher teacher = new Teacher();
		Guardian guardian = new Guardian();
		Classroom classroom = new Classroom();

		monitor = controller.studentWarning(studentId, whId);
		teacher = controller.getTeacherByClassroomId(monitor.getStudent()
				.getClassroom().getClass_id());
		classroom = controller.getClassroomById(monitor.getStudent()
				.getClassroom().getClass_id());
		guardian = controller.getGuardianByStudentId(studentId);

		// retrieve logo data in byte[] from academic institution id
		byte[] logoByte = controller.getPhoto(classroom.getInstituition()
				.getAcademic_instituition_id());
		// convert byte array back to BufferedImage
		InputStream in = new ByteArrayInputStream(logoByte);
		BufferedImage logoBuffImage = ImageIO.read(in);

		Image image = Image.getInstance(logoBuffImage, null);
		// set position inside pdf
		image.setAbsolutePosition(40, 740); // Code 1
		// scale back image size
		image.scaleAbsolute(75, 75); // Code 2

		document.add(image);

		// Add School Name
		String schoolName = new String(classroom.getInstituition()
				.getAcademic_name().trim()
				+ " ("
				+ classroom.getInstituition().getAcademic_instituition_id()
				+ ")");
		Paragraph headerSchool = new Paragraph(schoolName, boldFont);
		headerSchool.setAlignment(Element.ALIGN_CENTER);
		document.add(headerSchool);

		// Add School Name
		String schoolAddress = new String(capitalizer(classroom.getInstituition()
				.getAddress().trim())
				+ ", \n"+classroom.getInstituition().getPostcode().trim()+", "
				+ capitalizer(classroom.getInstituition().getDistrict().trim())
				+ ", Melaka (No Telefon : "
				+ classroom.getInstituition().getTelephone_number() + ")");
		Paragraph addressSchool = new Paragraph(schoolAddress, standardFont);
		addressSchool.setAlignment(Element.ALIGN_CENTER);
		addEmptyLine(addressSchool, 2);
		document.add(addressSchool);
		

	
		LineSeparator ls = new LineSeparator();
		document.add(ls);

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();
		String currDate = sdf.format(cal.getTime());

		Paragraph para2;

		para2 = new Paragraph("Ruj. Kami :", boldFont);
		para2.setAlignment(Paragraph.ALIGN_LEFT);
		para2.setSpacingBefore(5);
		document.add(para2);

		para2 = new Paragraph("Tarikh       : " + currDate, boldFont);
		para2.setAlignment(Paragraph.ALIGN_LEFT);
		para2.setSpacingAfter(10);
		document.add(para2);

		para2 = new Paragraph(capitalizer(guardian.getName())+", \n"+capitalizer(guardian.getAddress())+", \n"+
		capitalizer(guardian.getPostcode())+" "+capitalizer(guardian.getCity())+", "+capitalizer(guardian.getState()), standardFont);
		para2.setAlignment(Paragraph.ALIGN_LEFT);
		para2.setSpacingAfter(15);
		document.add(para2);

		para2 = new Paragraph("Tuan/Puan,", standardFont);
		para2.setAlignment(Paragraph.ALIGN_LEFT);
		para2.setSpacingAfter(10);
		document.add(para2);

		para2 = new Paragraph("PEMBERITAHUAN KETIDAKHADIRAN KE SEKOLAH : "
				+ monitor.getWarning().getDescription(), boldUnderlineFont);
		para2.setAlignment(Paragraph.ALIGN_LEFT);
		para2.setSpacingAfter(20);
		document.add(para2);

		para2 = new Paragraph(
				"Saya dengan ini diarah untuk memaklumkan bahawa anak tuan / puan seperti maklumat dibawah telah tidak hadir ke sekolah selama "+monitor.getWarning().getAbsentDay()
				+" hari "+monitor.getWarning().getWarningType().toLowerCase()+" tanpa sebarang sebab. Sila rujuk lampiran untuk melihat Tarikh Ketidakhadiran yang telah direkod.",standardFont);
				
		para2.setAlignment(Paragraph.ALIGN_LEFT);
		para2.setSpacingAfter(10);
		document.add(para2);

		para2 = new Paragraph("\t \t \t \t \t \t Nama Pelajar : " + monitor.getStudent().getName().trim()+"\n"
				+ "\t \t \t \t \t \t Jenis Kelas \t \t : " + monitor.getStudent().getClassroom().getType().trim()+"\n"
				+ "\t \t \t \t \t \t Nama Kelas \t : " + monitor.getStudent().getClassroom().getName().trim() + "\n",
				standardFont);
		para2.setAlignment(Paragraph.ALIGN_LEFT);
		para2.setSpacingAfter(10);
		document.add(para2);

		para2 = new Paragraph(
				"2.	Sila maklumi pihak sekolah seberapa segera atau boleh berhubung terus dengan Pengetua / Guru Besar sekolah dengan seberapa segera.",
				standardFont);
		para2.setAlignment(Paragraph.ALIGN_LEFT);
		para2.setSpacingAfter(10);
		document.add(para2);

		para2 = new Paragraph(
				"3.	Mengikut Peraturan Sekolah, anak tuan/puan boleh dikenakan tindakan buang sekolah jika tidak hadir tanpa apa-apa kenyataan daripada ibubapa/penjaga atau tanpa sebab-sebab yang munasabah.",
				standardFont);
		para2.setAlignment(Paragraph.ALIGN_LEFT);
		para2.setSpacingAfter(10);
		document.add(para2);

		para2 = new Paragraph("Sekian dimaklumkan, terima kasih", standardFont);
		para2.setAlignment(Paragraph.ALIGN_LEFT);
		para2.setSpacingAfter(20);
		document.add(para2);

		para2 = new Paragraph("'BERKHIDMAT UNTUK NEGARA'", boldFont);
		para2.setAlignment(Paragraph.ALIGN_LEFT);
		para2.setSpacingAfter(20);
		document.add(para2);

		para2 = new Paragraph("Saya yang menurut perintah,", standardFont);
		para2.setAlignment(Paragraph.ALIGN_LEFT);
		para2.setSpacingAfter(40);
		document.add(para2);

		para2 = new Paragraph("................................................................",
				standardFont);
		para2.setAlignment(Paragraph.ALIGN_LEFT);
		para2.setSpacingAfter(10);
		document.add(para2);
		
		para2 = new Paragraph(capitalizer("( "+teacher.getName().trim())+" )", standardFont);
		para2.setAlignment(Paragraph.ALIGN_LEFT);
		para2.setSpacingAfter(5);
		document.add(para2);

		para2 = new Paragraph(capitalizer("Guru Kelas, "+monitor.getStudent().getClassroom().getType().trim()+", "+monitor.getStudent().getClassroom().getName().trim()), standardFont);
		para2.setAlignment(Paragraph.ALIGN_LEFT);
		para2.setSpacingAfter(5);
		document.add(para2);

		para2 = new Paragraph("s.k,	1.  Guru Kelas. \n \t \t  \t 2.  Fail Sekolah.", standardFont);
		para2.setAlignment(Paragraph.ALIGN_LEFT);
		para2.setSpacingAfter(10);
		document.add(para2);

		document.newPage();
		
		PdfPTable titleTable = new PdfPTable(1);
		
		PdfPCell c2 = new PdfPCell(new Phrase("TARIKH KETIDAKHADIRAN YANG TELAH DIREKOD", tableTitle));
		c2.setBackgroundColor(BaseColor.DARK_GRAY);
		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		c2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		titleTable.addCell(c2);
		titleTable.setWidthPercentage(100);
		titleTable.setHorizontalAlignment(1);
		
		PdfPTable contentTable = new PdfPTable(1);
		
		PdfPCell c1 = new PdfPCell();

		c1 = new PdfPCell(new Phrase("NAMA MURID : "+monitor.getStudent().getName(), cellContent));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		
		contentTable.addCell(c1);
		contentTable.setWidthPercentage(100);
		
		float[] columnWidths = new float[] { 150f, 600f };
		PdfPTable contentTable2 = new PdfPTable(columnWidths);
		contentTable2.setWidthPercentage(100);
		
		c1 = new PdfPCell(new Phrase("TARIKH KETIDAKHADIRAN", cellContent));
		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable2.addCell(c1);
		
		c1 = new PdfPCell(new Phrase(""+monitor.getDateAbsent().getFirstWarning(), cellContent));		
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		contentTable2.addCell(c1);
		
		
		document.add(titleTable);
		document.add(contentTable);
		document.add(contentTable2);
		
		/*pada tarikh "+ monitor.getDateAbsent().getFirstWarning() + ".",standardFont);*/
		
	}

	// additional method to add new space
	public void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	
	private String capitalizer(String word){
        String[] words = word.split(" ");
        StringBuilder sb = new StringBuilder();
        if (words[0].length() > 0) {
            sb.append(Character.toUpperCase(words[0].charAt(0)) + words[0].subSequence(1, words[0].length()).toString().toLowerCase());
            for (int i = 1; i < words.length; i++) {
                sb.append(" ");
                if (words[i].length() > 0) sb.append(Character.toUpperCase(words[i].charAt(0)) + words[i].subSequence(1, words[i].length()).toString().toLowerCase());
            }
        }
        return  sb.toString();
    }

}
