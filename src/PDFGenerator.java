import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator {

	String pdfFileDirectory;

	public void userInputPdfFile() throws FileNotFoundException, IOException {

		Scanner sc = new Scanner(System.in);
		boolean fileExists;
		System.out.println("\n Count done... \n");
		System.out.print("Please, input the PDF directory you want to store the ocurrences: ");
		pdfFileDirectory = sc.nextLine() + Constants.TYPE_OF_PDF_FILE;
	}

	public void PDFCreator(String[] wordsToSearch, Integer[] contadorPalabras)
			throws FileNotFoundException, DocumentException {

		Document document = new Document();
		FileOutputStream pdfFile = new FileOutputStream(pdfFileDirectory);
		PdfWriter.getInstance(document, pdfFile);
		int[] cellMeasures = { 2, 8 };
		document.open();

		PdfPTable table = new PdfPTable(2);
		table.setWidths(cellMeasures);
		Font fuente = new Font();
		fuente.setColor(BaseColor.WHITE);
		PdfPCell header = new PdfPCell(new Phrase(Constants.TEXTO_CABECERA_DE_TABLA, fuente));

		header.setColspan(2);
		header.setBackgroundColor(BaseColor.BLACK);
		header.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(header);

		PdfPCell cell1 = new PdfPCell(new Phrase(Constants.TEXTO_CABECERA_DE_TABLA_PALABRAS));
		cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);

		table.addCell(cell1);
		PdfPCell cell2 = new PdfPCell(new Phrase(Constants.TEXTO_CABECERA_DE_TABLA_NUMERO_APARICIONES));
		cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell2);

		for (int i = 0; i < wordsToSearch.length; i++) {
			PdfPCell wordContent = new PdfPCell(new Phrase(wordsToSearch[i]));
			wordContent.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(wordContent);
			PdfPCell counterContent = new PdfPCell(new Phrase(contadorPalabras[i].toString()));
			counterContent.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(counterContent);
		}

		System.out.println("\n The file '" + pdfFileDirectory + "' has been created successfully \n");

		document.add(table);
		document.close();

	}
}
