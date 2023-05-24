import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Metadati metadati = new Metadati();
		
		/*
		System.out.println("inserisci percorso immagine");
		String s = br.readLine();
		metadati.leggiMetadatiImg(s);
		
		
		System.out.println("Inserisci percorso audio");
		String d = br.readLine();
		metadati.leggiMetadatiAudio(d);
		*/
		System.out.println("Inserisci percorso video");
		String b = br.readLine();
		metadati.leggiMetadatiVideo(b);
	}

}
