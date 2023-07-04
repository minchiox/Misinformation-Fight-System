package Backend;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Immagine {
	
	public  Immagine(String percorso) {
		this.percorso = percorso;
	}

	protected String percorso;
	protected int width;
	protected int height;
	protected String nome;
	
	
	protected String getPercorso() {
		return percorso;
	}
	protected void setPercorso(String percorso) {
		this.percorso = percorso;
	}
	protected int getWidth() {
		return width;
	}
	protected void setWidth(int width) {
		this.width = width;
	}
	protected int getHeight() {
		return height;
	}
	protected void setHeight(int height) {
		this.height = height;
	}
	protected String getNome() {
		return nome;
	}
	protected void setNome(String nome) {
		this.nome = nome;
	}
	
	//funzione per caricare un immagine
	public BufferedImage loadImage() {
        try {
            File file = new File(percorso);
            return ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("Errore durante il caricamento dell'immagine.");
            e.printStackTrace();
            return null;
        }
    }
	
	//funzione per caricare un immagine e trovarne altezza e larghezza (prova iniziale)
	public void caricaImg(){
		
		//ImageLoader imageLoader = new ImageLoader();
	    BufferedImage image = loadImage();
	    if (image != null) {
	        width = image.getWidth();
	        height = image.getHeight();	        
	        // Esempio di stampa delle dimensioni dell'immagine:
	        System.out.println("Larghezza: " + width + ", Altezza: " + height);
	    }
	}
	
	//SVILUPPO FUTURO********************
	//public void trovaImmagine(){}
	//La seguente funzione fa riferimento alla if numero 1
}
