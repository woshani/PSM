package controller;

import java.sql.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import facade.ControllerWrapper;

public class ReportFooterPageEvent extends PdfPageEventHelper {

	// public void onStartPage(PdfWriter writer,Document document) {
	// Rectangle rect = writer.getBoxSize("art");
	// ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER,
	// new Phrase("Top Left"), rect.getLeft(), rect.getTop(), 0);
	// ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER,
	// new Phrase("Top Right"), rect.getRight(), rect.getTop(), 0);
	// }

	public void onEndPage(PdfWriter writer, Document document) {
		try{
		
		ControllerWrapper controller = new ControllerWrapper();
		
		Rectangle rect = writer.getBoxSize("art");
		ColumnText
				.showTextAligned(
						writer.getDirectContent(),
						Element.ALIGN_CENTER,
						new Phrase(
								"Dokumen ini dijana oleh : Parental Relationship Information Management (PRIM@UTeM)"),
						rect.getRight(), rect.getBottom(), 0);
		ColumnText.showTextAligned(
				writer.getDirectContent(),
				Element.ALIGN_CENTER,
				new Phrase("Halaman "
						+ writer.getPageNumber()+" [Tarikh Dijana : "+(String)controller.getTodayDate2()+"]"), rect.getLeft(), rect
						.getBottom(), 0);
		
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
