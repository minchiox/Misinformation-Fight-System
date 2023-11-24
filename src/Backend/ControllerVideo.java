package Backend;
import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

public class ControllerVideo extends Controller {
	
	protected int indice;
	//protected String [] vector;

	//metodo per legggere i metadagti diun video
	public String leggiMetadatiVideo(String percorso) {
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
            value = "";
            value +="Formato: " + format;
            value +="Durata: " + duration;
            value +="Risoluzione: " + resolution;
            // ... print other metadata
            
            inputStream.close();
        } catch (Exception e) {
            value = "Errore durante la lettura del video";
        }
		return value;
	 }
	
	//calcola indice video
	public String calcolaIndiceVideo(String percorso) {
		try {
			indice = 0;
            File videoFile = new File(percorso);
            InputStream inputStream = new FileInputStream(videoFile);
            //Metadata metadata = new Metadata();
            AutoDetectParser parser = new AutoDetectParser();
            ParseContext parseContext = new ParseContext();
            
            parser.parse(inputStream, new BodyContentHandler(), metadata, parseContext);
            
            // Get the video metadata
            String format = metadata.get("Content-Type");
            String duration = metadata.get("xmpDM:duration");
            String width = metadata.get("Image Width");
            String height = metadata.get("Image Height");
            // ... other desired metadata
            //String[] vector = new String = {format, duration, width, height};
            String[] arrayDiStringhe = {format, duration, width, height}; 
            for(int i=0; i<4; i++) {
            	if(arrayDiStringhe[i]!=null) {
            		indice+=25;
            	}
            }
            value = "L'indice di affidabilità del video è: "+indice+"%";
            inputStream.close();
        } catch (Exception e) {
            value = "Errore durante la lettura del video";
        }
		return value;
	}
	
	public  String getVideo(String percorso) {
        try {
            // Crea l'oggetto YouTube
            YouTube youtubeService = getService();
            
            // Chiedi che tipo di video cercare
            //System.out.println("Che video vuoi cercare?");
            //keys = br.readLine();
            
            // Esegui la ricerca di video
            List<SearchResult> searchResults = searchVideos(youtubeService, percorso, 10);

            /* Stampa i risultati della ricerca
            for (SearchResult searchResult : searchResults) {
                System.out.println(searchResult.getSnippet().getTitle());
            }
            */
         // Ottenere l'URL del primo video
            value = "";
            for (SearchResult searchResult : searchResults) {
                //SearchResult video = searchResults.get(0);
                String videoId = searchResult.getId().getVideoId();
                String videoUrl = "https://www.youtube.com/watch?v=" + videoId;
                String titolo = searchResult.getSnippet().getTitle();
                value +="Titolo dell'video: "+ titolo+"\r\n";
                value +="URL dell'video: " + videoUrl+"\r\n";
                //System.out.println("");
            
            }
        } catch (Exception e) {
            value = "Errore durante la lettura del video";
        }
        return value;
    }
	
	private static List<SearchResult> searchVideos(YouTube youtube, String query, long maxResults) throws IOException {
        // Crea la richiesta di ricerca
        YouTube.Search.List search = youtube.search().list("id,snippet");

        // Imposta la chiave API
        search.setKey(API_KEY);

        // Imposta i parametri di ricerca
        search.setQ(query);
        search.setType("video");
        search.setMaxResults(maxResults);

        // Esegui la richiesta di ricerca e ottieni la risposta
        SearchListResponse searchResponse = search.execute();

        // Restituisci i risultati della ricerca
        return searchResponse.getItems();
    }
}
