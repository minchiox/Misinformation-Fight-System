import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import java.io.FileInputStream;
import java.io.InputStream;




public class Metadati {
	
	org.apache.tika.metadata.Metadata metadata = new org.apache.tika.metadata.Metadata();
	protected Audio audio;
	protected Immagine img;
	protected Video video;
	
	
	//funzione per leggere i Metadati di un immagine
			public void leggiMetadatiImg(String percorso) {
				 try {	 
					 	//leggo il file
			            File file = new File(percorso);
			            //avvio la classe che leggerà i metadati
			            Metadata metadata = ImageMetadataReader.readMetadata(file);
			            //for che cicla finchè trova tag nei metadati
			            for (Directory directory : metadata.getDirectories()) {
			            	//ottiene i tag e li converte in tipo stringa
			                for (Tag tag : directory.getTags()) {
			                    System.out.println(tag);
			                }
			            }
			        } catch (Exception e) {
			            System.out.println("Errore durante la lettura dei metadati dell'immagine.");
			            e.printStackTrace();
			        }
			}
	
	
	
	//metodo per leggere i metadati di un audiop
			public void leggiMetadatiAudio(String percorso) {
		        try {
		        	//creo il file mp3
		            Mp3File mp3File = new Mp3File(percorso);
		            //devo controllare se ha tag 1 o tag 2 con gli if
		            if (mp3File.hasId3v1Tag()) {
		            	//Entrerà qui con ID3v1
		                ID3v1 id3v1Tag = mp3File.getId3v1Tag();
		                System.out.println("Titolo: " + id3v1Tag.getTitle());
		                System.out.println("Artista: " + id3v1Tag.getArtist());
		                System.out.println("Album: " + id3v1Tag.getAlbum());
		                System.out.println("Genere: " + id3v1Tag.getGenre());
		            }
		           
		            if (mp3File.hasId3v2Tag()) {
		            	//Entrerà qui con ID3v2
		                ID3v2 id3v2Tag = mp3File.getId3v2Tag();
		                System.out.println("Titolo: " + id3v2Tag.getTitle());
		                System.out.println("Artista: " + id3v2Tag.getArtist());
		                System.out.println("Album: " + id3v2Tag.getAlbum());
		                System.out.println("Genere: " + id3v2Tag.getGenre());
		            }
		        } catch (Exception e) {
		            System.out.println("Errore durante la lettura dei metadati dell'audio.");
		            e.printStackTrace();
		        }
			}
		
			
			//metodo per legggere i metadagti diun video
			protected void leggiMetadatiVideo(String percorso) {
				try {
		            File videoFile = new File(percorso);
		            InputStream inputStream = new FileInputStream(videoFile);
		            //Metadata metadata = new Metadata();
		            AutoDetectParser parser = new AutoDetectParser();
		            ParseContext parseContext = new ParseContext();
		            
		            parser.parse(inputStream, new BodyContentHandler(), metadata, parseContext);
		            
		            // Get the video metadata
		            String format = metadata.get("Content-Type");
		            String duration = metadata.get("xmpDM:duration");
		            String resolution = metadata.get("Image Width") + "x" + metadata.get("Image Height");
		            // ... other desired metadata
		            
		            System.out.println("Format: " + format);
		            System.out.println("Duration: " + duration);
		            System.out.println("Resolution: " + resolution);
		            // ... print other metadata
		            
		            inputStream.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
			    }
			     
}
