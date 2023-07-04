package Backend;
import java.io.File;
import java.io.IOException;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class ControllerImg extends Controller{

	protected int indice;
	
	//funzione per leggere i Metadati di un immagine
	public String leggiMetadatiImg(String percorso) {
		 try {	 
			 	//leggo il file
	            File file = new File(percorso);
	            //avvio la classe che leggerà i metadati
	            Metadata metadata = ImageMetadataReader.readMetadata(file);
	            //for che cicla finchè trova tag nei metadati
	            for (Directory directory : metadata.getDirectories()) {
	            	//ottiene i tag e li converte in tipo stringa
	                for (Tag tag : directory.getTags()) {
	                    System.out.println(	value += tag.toString()+"\r\n");
	                }
	            }
	        } catch (Exception e) {
	            value = "Errore durante la lettura dei metadati dell'immagine.";
	        }
		 return value;
	}
	
	public String calcolaIndiceImg(String percorso){
		indice = 0;
		//leggo il file
        File file = new File(percorso);
        //avvio la classe che leggerà i metadati
        Metadata metadata;
		try {
			metadata = ImageMetadataReader.readMetadata(file);
			//for che cicla finchè trova tag nei metadati
	        for (Directory directory : metadata.getDirectories()) {
	        	//ottiene i tag e li converte in tipo stringa
	            for (Tag tag : directory.getTags()) {
	                if(tag == null || indice == 100) {
	                	break;
	                }
	                indice+=25;
	            }
	        }
	        value = ("l'indice è : "+indice+"%");
		} catch (ImageProcessingException e) {
			value = e.toString();
		} catch (IOException e) {
			value = e.toString();
		}
        return value;
	}
}
