package Backend;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class ControllerFonte extends Controller {

	protected Fonte fonte;
	protected BufferedWriter writer;
	protected BufferedReader reader;
	
	//funzione utilizzata per segnalare una fonte
	public String segnalaFonte(String url, String motivo) throws IOException {
		String fileNome = "File/segnalazione"; // Specifica il nome del tuo file qui
		

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileNome, true))) {
        	writer.newLine();
            writer.write(url);
            writer.newLine();
            writer.write(motivo);
            value ="Segnalazione inviata correttamente, Grazie!";
        } catch (IOException e) {
           value = "Si è verificato un errore durante il salvataggio nel file: " + e.getMessage().toString();
        }
        return value;
	}
	
	//funzione utilizzata per capire il rating di una fonte interno
		public String rateFonte(String url) throws IOException {
			
			String fileNome = "File/top10";
		
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
	                value = "Questa fonte è una fonte raccomandabile";
	         
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
	            			value = "Questa fonte non è una fonte raccomandabile";
	            			
	            		}
	            		else {
	            			value = "Questa fonte ha un livello di affidabilità medio";
	            		
	            		}
	            	}
	            }
	        } catch (IOException e) {
	            value = "Si è verificato un errore durante la lettura del file: " + e.getMessage().toString();
	        }
	        //reader.close();
	        return  value;
		}
	
	//funzione utilizzata per capire il rating di una fonte interno
	private int rateFonteInterno(String url) throws IOException {
		
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
                System.out.println("Questa fonte è una fonte raccomandabile");
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
            			System.out.println("Questa fonte non è una fonte raccomandabile");
            			flagVariable = -1;
            		}
            		else {
            			System.out.println("Questa fonte ha un livello di affidabilità medio");
            			flagVariable = 0;
            		}
            	}
            }
        } catch (IOException e) {
            System.out.println("Si è verificato un errore durante la lettura del file: " + e.getMessage());
        }
        //reader.close();
        return  flagVariable;
	}
	
	public boolean valutaFonte(String url) throws IOException {
		boolean value = googleSafeBrowsing(url);
		return value;
	}
	
	public String calcoloIndiceFonte(String url) throws IOException {
		int indice = 0;
		int valueReport = rateFonteInterno(url);
		boolean exist = valutaFonte(url);
		if(exist) {
			indice +=50;
		}
		if(valueReport == 1) {
			indice+=50;
		}else if(valueReport == 0) {
				indice+=25;
		}
		return value = "L'indice di affidabilità di questa fonte è di: "+indice+"%";
	}
}
