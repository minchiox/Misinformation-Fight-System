package Backend;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;

public class ControllerAudio extends Controller{
	
	protected int indice;
	
	//metodo per leggere i metadati di un audiop
	public String leggiMetadatiAudio(String percorso) {
		value = "";
        try {
        	//creo il file mp3
            Mp3File mp3File = new Mp3File(percorso);
            //devo controllare se ha tag 1 o tag 2 con gli if
            if (mp3File.hasId3v1Tag()) {
            	//Entrerà qui con ID3v1
                ID3v1 id3v1Tag = mp3File.getId3v1Tag();
                value +=("Titolo: " + id3v1Tag.getTitle()+"\r\n");
                value +=("Artista: " + id3v1Tag.getArtist()+"\r\n");
                value +=("Album: " + id3v1Tag.getAlbum()+"\r\n");
                value +=("Genere: " + id3v1Tag.getGenre()+"\r\n");
            }
           
            if (mp3File.hasId3v2Tag()) {
            	//Entrerà qui con ID3v2
                ID3v2 id3v2Tag = mp3File.getId3v2Tag();
                value +=("Titolo: " + id3v2Tag.getTitle()+"\r\n");
                value +=("Artista: " + id3v2Tag.getArtist()+"\r\n");
                value +=("Album: " + id3v2Tag.getAlbum()+"\r\n");
                value +=("Genere: " + id3v2Tag.getGenre()+"\r\n");
            }
        } catch (Exception e) {
            value = "Errore durante la lettura dei metadati dell'audio.";
        }
        return value;
	}
	
	public String calcolaIndiceAudio(String percorso) {
		 try {
			 	indice = 0;
	        	//creo il file mp3
	            Mp3File mp3File = new Mp3File(percorso);
	            //devo controllare se ha tag 1 o tag 2 con gli if
	            if (mp3File.hasId3v1Tag()) {
	            	//Entrerà qui con ID3v1
	                ID3v1 id3v1Tag = mp3File.getId3v1Tag();
	                indice = calcoloID3v1(id3v1Tag);
	                value = "L'indice di attendibilità è: "+indice+"%";
	            }
	            if (mp3File.hasId3v2Tag()) {
	            	//Entrerà qui con ID3v2
	                ID3v2 id3v2Tag = mp3File.getId3v2Tag();
	                indice = calcoloID3v2(id3v2Tag);
	               value = "L'indice di attendibilità è: "+indice+"%";
	            }
	        } catch (Exception e) {
	            value = "Errore durante la lettura dei metadati dell'audio.";
	            e.printStackTrace();
	        }
		 return value;
	}
	
	//calcola l'indice interno in caso di ID3v1
	private int calcoloID3v1(ID3v1 id3v1Tag) {
		if(id3v1Tag.getTitle()!=null) {
			indice+=25;
		}
        if(id3v1Tag.getArtist() !=null){
        	indice+=25;
        }
        if(id3v1Tag.getAlbum() !=null){
        	indice+=25;
        }
        if(id3v1Tag.getGenre() != 0){
        	indice+=25;
        }
        return indice;
	}
	
	//calcola l'indice interno in caso di ID3v2
		private int calcoloID3v2(ID3v2 id3v2Tag) {
			if(id3v2Tag.getTitle()!=null) {
				indice+=25;
			}
	        if(id3v2Tag.getArtist() !=null){
	        	indice+=25;
	        }
	        if(id3v2Tag.getAlbum() !=null){
	        	indice+=25;
	        }
	        if(id3v2Tag.getGenre() != 0){
	        	indice+=25;
	        }
	        return indice;
		}
	
	public String getAudio(String percorso) {
        try {
            // Crea l'oggetto YouTube
            YouTube youtubeService = getService();
            
            // Chiedi che tipo di video cercare
            System.out.println("Che audio vuoi cercare?");
            //keys = br.readLine();
            
            
            // Esegui la ricerca di video audio
            List<SearchResult> searchResults = searchAudioVideos(youtubeService, percorso, 10);

            /* Stampa i risultati della ricerca
            for (SearchResult searchResult : searchResults) {
                System.out.println(searchResult.getSnippet().getTitle());
            }
            */
            value = "";
         // Ottenere l'URL dell'audio del primo video
            for (SearchResult searchResult : searchResults) {
            	// DEBUG System.out.println("Sono nel for");
            	
                //SearchResult video = searchResults.get(0);
                String videoId = searchResult.getId().getVideoId();
                String audioUrl = "https://www.youtube.com/watch?v=" + videoId;
                String titolo = searchResult.getSnippet().getTitle();
                value +="Titolo dell'audio: "+ titolo+"\r\n";
                value +="URL dell'audio: " + audioUrl+"\r\n";
            }
            
        } catch (GeneralSecurityException | IOException e) {
            value = "Errore nella lettura dell'audio";
        }
        return value;
    }
	
	private static List<SearchResult> searchAudioVideos(YouTube youtube, String query, long maxResults) throws IOException {
        // Crea la richiesta di ricerca
        YouTube.Search.List search = youtube.search().list("id,snippet");

        // Imposta la chiave API
        search.setKey(API_KEY);

        // Imposta i parametri di ricerca
        search.setQ(query);
        search.setType("video");
        search.setVideoCategoryId("10"); // Categoria "Music"
        search.setVideoDuration("short"); // Durata breve (meno di 4 minuti)

        // Esegui la richiesta di ricerca e ottieni la risposta
        SearchListResponse searchResponse = search.execute();

        // Restituisci i risultati della ricerca
        return searchResponse.getItems();
    }

}
