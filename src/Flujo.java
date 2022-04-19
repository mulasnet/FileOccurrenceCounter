import java.io.IOException;

import com.itextpdf.text.DocumentException;

public class Flujo {
	/**
	 * 
	 * @throws IOException
	 * @throws DocumentException
	 */
	public void start() throws IOException, DocumentException {
		
		Documents d = new Documents();
		d.userInputDataFile();
		d.userInputWordFile();
		System.out.println("\n Searching... \n");
		String[] palabrasABuscar=d.obtenerPalabras();
		Integer[] contadorPalabras = new Integer[palabrasABuscar.length];
		contadorPalabras= d.buscarPalabra(palabrasABuscar, contadorPalabras);
		PDFGenerator pdf = new PDFGenerator();
		pdf.userInputPdfFile();;
		pdf.PDFCreator(palabrasABuscar, contadorPalabras);
		
	}

}
