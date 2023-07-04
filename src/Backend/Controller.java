package Backend;
import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.io.IOException;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;

public class Controller {

		//instanza della classe metadata presa da tika
		//l'instanza viene proposta così per ovviare conflitti tra metadata di tika e metadata di drew
		org.apache.tika.metadata.Metadata metadata = new org.apache.tika.metadata.Metadata();
		
		protected Audio audio;
		protected Immagine img;
		protected Video video;
		protected Notizia ntz;
		protected String value;
		protected String result;
		
		protected static final String API_KEY = "AIzaSyDmPpWcoJH-c4k38R2ZUM4ov7Ky3giD42U";
		protected static final String API_KEY_GOOGLE = "AIzaSyCha9m9hROeiCk5-UvtB12TJjEWqfiGmRg";
		protected  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		protected  String keys;
		
		
		protected static YouTube getService() throws GeneralSecurityException, IOException {
	        // Crea l'oggetto di trasporto HTTP
	        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

	        // Crea l'oggetto JSON factory
	        final JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

	        // Crea l'istanza del servizio YouTube
	        return new YouTube.Builder(httpTransport, jsonFactory, null)
	                .setApplicationName("YouTube Search Example")
	                .build();
	    }
		
		protected String readInput() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String d = br.readLine();
			br.close();
			return d;
		}
		
		public boolean googleSafeBrowsing(String link) {
			boolean secure = true;
			try {
				
	            String encodedURL = URLEncoder.encode(link, "UTF-8");
	            URL url = new URL("https://safebrowsing.googleapis.com/v4/threatMatches:find?key=" + API_KEY_GOOGLE);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("POST");
	            conn.setRequestProperty("Content-Type", "application/json");

	            String requestBody = "{ \"client\": { \"clientId\": \"yourcompany\", \"clientVersion\": \"1.5.3\" }, \"threatInfo\": { \"threatTypes\": [\"MALWARE\", \"SOCIAL_ENGINEERING\"], \"platformTypes\": [\"WINDOWS\"], \"threatEntryTypes\": [\"URL\"], \"threatEntries\": [ {\"url\": \"" + encodedURL + "\"} ] } }";

	            conn.setDoOutput(true);
	            conn.getOutputStream().write(requestBody.getBytes("UTF-8"));
	            
	            //[ {\"url\": \"" + encodedURL + "\"} ]

	            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();

	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }

	            reader.close();
	            conn.disconnect();

	            if (response.toString().equals("{}")) {
	                System.out.println("Il link è sicuro.");
	                secure = true;
	            } else {
	                System.out.println("Il link non è sicuro. "+response);
	                secure = false;
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			return secure;
		}
}
