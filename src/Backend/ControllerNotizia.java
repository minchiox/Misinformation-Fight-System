package Backend;
import org.jsoup.Jsoup;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ControllerNotizia extends Controller{
	
	String query = "";
   // String searchUrl = "https://www.google.com/search?q=" + query;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Notizia ntz = new Notizia();
    
    //ottiene una notizia da google 
	public String getNotice(String query) throws IOException {
	
    	//System.out.println("Cosa vuoi cercare?");
		//query = br.readLine();
		String searchUrl = "https://www.google.com/search?q=" + query;
        ntz.setTesto(query);
		value ="";
        try {
            Document doc = Jsoup.connect(searchUrl).get();
            Elements searchResults = doc.select("div.g");

            for (Element result : searchResults) {
	                Element titleElement = result.selectFirst("h3");
	                Element linkElement = result.selectFirst("a");
	                String title = titleElement != null ? titleElement.text() : "";
	                String url = linkElement != null ? linkElement.absUrl("href") : "";
	                value +="Titolo: " + title+ "\r\n";
	                value +="URL: " + url+"\r\n";
	                value +="\r\n";
                }
        } catch (IOException e) {
           value ="Errore durante la ricerca del link";
        }
        return value;
	}
	
	//funzione che estrapola il testo dividendolo in parole 
	public String soggettiNotizia(String testo) {
		String[] parole = testo.split("\\s+"); // Dividi il testo in parole utilizzando gli spazi come delimitatori
		value ="";
        for (String parola : parole) {
            if (contieneMaiuscole(parola)) {
                value += parola + "\r\n";
            }
        }
        return value;
    
	}
	
	//funzione interna che riconosce se una parola è un nome o contiene una lettera maiuscola
	private static boolean contieneMaiuscole(String parola) {
	    for (char carattere : parola.toCharArray()) {
	        if (Character.isUpperCase(carattere)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	/*
	 * SVILUPPI FUTURI IF 15 e IF 16
	 * 
	 * public void bloccaFonte(){}
	 * 
	 * 
	 * public void sbloccaFonte(){}
	 * 
	 * 
	 */
	public boolean valutaNotizia(String url) {
		boolean value = googleSafeBrowsing(url);
		return value;
	}
	
	//funzione utilizzata per capire il rating di una notizia
		protected int rateNotizia(String url) throws IOException {
			
			String fileNome = "File/top10";
			int flagVariable = 0;
		
	        try (BufferedReader reader = new BufferedReader(new FileReader(fileNome))) {
	        	
	            String linea;
	            boolean trovata = false;

	            while ((linea = reader.readLine()) != null) {
	                if (url.contains(linea)) {
	                    trovata = true;
	                    break;
	                }
	            }

	            if (trovata) {
	                System.out.println("Questa notizia è una notizia raccomandabile");
	                flagVariable = 1;
	            } else {
	            	fileNome = "File/flop10";
	            	try (BufferedReader reader1 = new BufferedReader(new FileReader(fileNome))) {
	            		
	            		while ((linea = reader1.readLine()) != null) {
	            			if(url.contains(linea)) {
	            				trovata = true;
	            				break;
	            			}
	            		}
	            		if(trovata) {
	            			System.out.println("Questa notizia non è una notizia raccomandabile");
	            			flagVariable = -1;
	            		}
	            		else {
	            			System.out.println("Questa notizia ha un livello di affidabilità medio");
	            			flagVariable = 0;
	            		}
	            	}
	            }
	        } catch (IOException e) {
	            System.out.println("Si è verificato un errore durante la lettura del file: " + e.getMessage());
	        }
	        //reader.close();
	        return flagVariable;
		}
	
	public String calcoloIndiceNotizia(String url) throws IOException {
		int indice = 0;
		int valueReport = rateNotizia(url);
		boolean exist = valutaNotizia(url);
		if(exist) {
			indice +=50;
		}
		if(valueReport == 1) {
			indice+=50;
		}else if(valueReport == 0) {
				indice+=25;
		}
		return value = "L'indice di affidabilità di questa notizia è di: "+indice+"%";
	}
}
