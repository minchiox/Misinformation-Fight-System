
import com.mpatric.mp3agic.Mp3File;


public class Audio {
	

	protected Mp3File mp3File;
	protected String percorso;
	
	public  Audio(String percorso) {
		this.percorso = percorso;
	}

	protected String getPercorso() {
		return percorso;
	}
	protected void setPercorso(String percorso) {
		this.percorso = percorso;
	}
	
	public Mp3File getMp3File() {
		return mp3File;
	}
	public void setMp3File(Mp3File mp3File) {
		this.mp3File = mp3File;
	}
	
	public void load(String percorso) {
		try {
			Mp3File mp3File = new Mp3File(percorso);
			System.out.println("Audio cariato correttamente");
		} catch (Exception e) {
			System.out.println("Errore durante il caricamento dell'audio.");
			e.printStackTrace();
		}
	}

	
	

}
