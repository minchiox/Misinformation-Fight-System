package Backend;

public class Video {

	public  Video (String percorso) {
		this.percorso = percorso;
	}
	
	protected String percorso;
	protected int altezza;
	protected int larghezza;
	protected double frameRate;
	
	protected String getPercorso() {
		return percorso;
	}
	protected void setPercorso(String percorso) {
		this.percorso = percorso;
	}

	
	protected int getAltezza() {
		return altezza;
	}
	protected void setAltezza(int altezza) {
		this.altezza = altezza;
	}

	
	protected int getLarghezza() {
		return larghezza;
	}
	protected void setLarghezza(int larghezza) {
		this.larghezza = larghezza;
	}

	
	protected double getFrameRate() {
		return frameRate;
	}
	protected void setFrameRate(double frameRate) {
		this.frameRate = frameRate;
	}
	
	
}
