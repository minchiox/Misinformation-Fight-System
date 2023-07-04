package v2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.drew.imaging.ImageProcessingException;
import Backend.*;

public class GuiController extends Controller{
	
	private ControllerAudio crA = new ControllerAudio();
	private ControllerVideo crV = new ControllerVideo();
	private ControllerImg crI = new ControllerImg();
	private ControllerFonte crF = new ControllerFonte();
	private ControllerNotizia crN = new ControllerNotizia();
	protected String result;
	protected String value;
	
	
	protected ControllerAudio getCrA() {
		return crA;
	}
	protected void setCrA(ControllerAudio crA) {
		this.crA = crA;
	}
	protected ControllerVideo getCrV() {
		return crV;
	}
	protected void setCrV(ControllerVideo crV) {
		this.crV = crV;
	}
	protected ControllerImg getCrI() {
		return crI;
	}
	protected void setCrI(ControllerImg crI) {
		this.crI = crI;
	}
	protected ControllerFonte getCrF() {
		return crF;
	}
	protected void setCrF(ControllerFonte crF) {
		this.crF = crF;
	}
	protected ControllerNotizia getCrN() {
		return crN;
	}
	protected void setCrN(ControllerNotizia crN) {
		this.crN = crN;
	}
	
	/******************************************
	 * DICHIARAZIONE METODI CONTROLLERAUDIO
	 ********************************************/
	
	public String leggiMetadatiAudio(String percorso) {
		return result = crA.leggiMetadatiAudio(percorso);
	}
	
	public String calcolaIndiceAudio(String percorso) {
		return result = crA.calcolaIndiceAudio(percorso);
	}
		
	public String getAudio(String value) {
		return  result = crA.getAudio(value);
	}	
	
	/*********************************************
	 * FINE DICHIARAZIONE METODI CONTROLLERAUDIO
	 *********************************************/
	
	
	/*********************************************
	 * DICHIARAZIONE METODI CONTROLLERIMG
	 * 
	 *********************************************/
	
	public String leggiMetadatiImg(String percorso) {
		return result = crI.leggiMetadatiImg(percorso);
	}
	
	protected String calcolaIndiceImg(String percorso) throws ImageProcessingException, IOException {
		return result = crI.calcolaIndiceImg(percorso);
	}
	
	/*****************************************
	 * FINE DICHIARAZIONE METODI CONTROLLERIMG
	 *****************************************/
	
	/********************************************
	 * INIZIO DICHIARAZIONE METODI CONTROLLEVIDEO
	 * 
	 **********************************************/
	
	protected String leggiMetadatiVideo(String percorso) {
		return result = crV.leggiMetadatiVideo(percorso);
	}
	
	public String calcolaIndiceVideo(String percorso) {
		return result = crV.calcolaIndiceVideo(percorso);
	}
	
	public  String  getVideo(String percorso) {
		return result = crV.getVideo(percorso);
	}
	
	/***********************************************
	 * FINE DICHIARAZIONE METODI CONTROLLERVIDEO
	 * 
	 **********************************************/
	
	/***********************************************
	 * INIZIO DICHIARAZIONE METODI CONTROLLERNOTIZIA
	 ***********************************************/
	public String getNotice(String url) throws IOException {
		return result = crN.getNotice(url);
	}
	
	public String soggettiNotizia(String testo) {
		return result = crN.soggettiNotizia(testo);
	}
	
	public String calcoloIndiceNotizia(String url) throws IOException {
		return result = crN.calcoloIndiceNotizia(url);
	}
	
	/*************************************************
	 * FINE DICHIARAZIONE METODI CONTROLLERNOTIZIA
	 * 
	 **************************************************/
	
	/*******************************************************
	 * INIZIO DICHIARAZIONE METODI CONTROLLERFONTE
	 ********************************************************/
	protected String segnalaFonte(String url, String motivo) throws IOException {
		return result = crF.segnalaFonte(url, motivo);
	}
	
	public String calcoloIndiceFonte(String url) throws IOException {
		return result = crF.calcoloIndiceFonte(url);
	}
	
	public String rateFonte(String url) throws IOException {
		return result = crF.rateFonte(url);
	}
	/********************************************************
	 * FINE DICHIARAZIONE METODI CONTROLLERFONTE
	 *********************************************************/
	
	/*********************************************************
	 * 	METODI GuiCONTROLLER
	 ********************************************************/
	
}
