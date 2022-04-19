import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;

public class Documents {

	String wordsFileDirectory;
	String dataFileDirectory;

	public void userInputWordFile() throws FileNotFoundException, IOException {

		Scanner sc = new Scanner(System.in);
		boolean wordFileExists = false;
		do {

			System.out.print("Please, input the WORDS directory you want to search: ");
			wordsFileDirectory = sc.nextLine() + Constants.TYPE_OF_WORDS_FILE;
			try {
				File words = new File(wordsFileDirectory);
				wordFileExists = words.exists();
				if (!wordFileExists) {
					System.out.println("Error, the file '" + wordsFileDirectory + "' don´t exists \n");

				}
			} catch (Exception e) {
				e.printStackTrace();
				wordFileExists = false;

			}
		} while (!wordFileExists);
	}

	public void userInputDataFile() throws FileNotFoundException, IOException {

		Scanner sc = new Scanner(System.in);
		boolean dataFileExists;
		do {

			System.out.print("Please, input the DATA directory you want to read: ");
			dataFileDirectory = sc.nextLine() + Constants.TYPE_OF_DATA_FILE;

			try {
				File data = new File(dataFileDirectory);
				dataFileExists = data.exists();

				if (!dataFileExists) {
					System.out.println("Error, the file '" + dataFileDirectory + "' don´t exists \n");

				}
			} catch (Exception e) {
				dataFileExists = false;
				e.printStackTrace();
			}
		} while (!dataFileExists);
	}

	public String[] obtenerPalabras() throws IOException {
		Scanner sc = new Scanner(System.in);
		FileReader fr = null;
		BufferedReader br;
		fr = new FileReader(wordsFileDirectory, Charset.forName("UTF8"));
		br = new BufferedReader(fr);
		String linea;
		String palabras[] = new String[contarLineas()];
		for (int i = 0; i < palabras.length; i++) {
			linea = br.readLine();
			palabras[i] = linea;
		}

		br.close();
		return palabras;
	}

	public int contarLineas() throws IOException {
		FileReader fr = null;
		BufferedReader br;
		fr = new FileReader(wordsFileDirectory, Charset.forName("UTF8"));
		br = new BufferedReader(fr);
		int numeroLineas = 0;
		while (br.readLine() != null) {
			numeroLineas++;
		}
		br.close();
		return numeroLineas;

	}

	public Integer[] buscarPalabra(String[] palabras, Integer[] contadorPalabras) throws IOException {

		FileReader fr = null;
		BufferedReader br;
		fr = new FileReader(dataFileDirectory, Charset.forName("UTF8"));
		br = new BufferedReader(fr);
		String linea;
		for (int i = 0; i < contadorPalabras.length; i++) {
			contadorPalabras[i] = 0;
		}

		while ((linea = br.readLine()) != null) {
			linea.toLowerCase();
			String[] palabrasLibro = linea.split(" ");

			for (int i = 0; i < palabras.length; i++) {

				for (int j = 0; j < palabrasLibro.length; j++) {

					if (palabrasLibro[j].toLowerCase().contains(palabras[i].toLowerCase())) {
						contadorPalabras[i]++;
					}
				}
			}

		}
		for (int i = 0; i < contadorPalabras.length; i++) {
			System.out.print(" " + palabras[i] + " ");

			System.out.println(contadorPalabras[i]);
		}
		br.close();
		return contadorPalabras;
	}

}
