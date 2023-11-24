package v2;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.drew.imaging.ImageProcessingException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.FlowLayout;



@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	GuiController guiC = new GuiController();
	private JPanel contentPane;
	String value;
	String result;
	
	
	/*
	 * JFrame frame = new JFrame("Popup Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opzioni");
        
        JButton mainButton = new JButton("Apri Menu");
        menuBar.add(mainButton);
        menuBar.add(menu);
        
        JMenuItem menuItem1 = new JMenuItem("Opzione 1");
        JMenuItem menuItem2 = new JMenuItem("Opzione 2");
        JMenuItem menuItem3 = new JMenuItem("Opzione 3");
        
        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);
        
        mainButton.addActionListener(e -> {
            menu.show(mainButton, 0, mainButton.getHeight());
        });
        
        frame.setJMenuBar(menuBar);
        frame.setSize(300, 200);
        frame.setVisible(true);
	 * 
	 * 
	 */
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 293);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton_4 = new JButton("SOGGETTI");
		contentPane.add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Pannello newFrame = new Pannello();
                newFrame.setVisible(true);
                value = newFrame.askValue("Scrivi la notizia dal quale vuoi estrapolare i soggetti");
				result = guiC.soggettiNotizia(value);
				newFrame.setOutput(result);
            }});
		
		JButton btnNewButton_6 = new JButton("SEGNALA");
		contentPane.add(btnNewButton_6);
		btnNewButton_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Pannello newFrame = new Pannello();
                newFrame.setVisible(true);
                value = newFrame.askValue("Scrivi il link della fonte che vuoi segnalare");
                String motivo = newFrame.askValue("Scrivi le ragioni che ti portano a inviare una segnalazione");
				try {
					result = guiC.segnalaFonte(value, motivo);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				newFrame.setOutput(result);
            }});
		
		
		JMenu mnNewMenu = new JMenu("Immagine");
		mnNewMenu.setBounds(10, 10, 111, 24);
		//contentPane.add(mnNewMenu);
		JMenuBar menuBar = new JMenuBar();
		contentPane.add(menuBar);
		//JButton btnNewButton = new JButton("Immagine");
		//btnNewButton.setBounds(20, 33, 85, 21);
		//contentPane.add(btnNewButton);
		//menuBar.add(btnNewButton);
        menuBar.add(mnNewMenu);
		JMenuItem mntmNewMenuItem = new JMenuItem("INDICE");
		mntmNewMenuItem.setBounds(158, 48, 133, 24);
		mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Pannello newFrame = new Pannello();
                newFrame.setVisible(true);
                try {
                	value = newFrame.askValue("Inserisci il percorso dell'immagine della quale vuoi calcolare l'indice");
					result = guiC.calcolaIndiceImg(value);
					newFrame.setOutput(result);
				} catch (ImageProcessingException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            }
        });

		//contentPane.add(mntmNewMenuItem);
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("ELENCO");
		mntmNewMenuItem_8.setBounds(109, 165, 133, 24);
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Pannello newFrame = new Pannello();
                newFrame.setVisible(true);
				result = "Funzione in fase di sviluppo, scusateci per il disagio";
				newFrame.setOutput(result);
            }});
		//contentPane.add(mntmNewMenuItem_11);
		//contentPane.add(mntmNewMenuItem_8);
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("METADATI");
		mntmNewMenuItem_11.setBounds(274, 169, 133, 24);
		mntmNewMenuItem_11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Pannello newFrame = new Pannello();
                newFrame.setVisible(true);
                value = newFrame.askValue("Inserisci il percorso dell'immagine della quale vuoi ricevere i metadati");
				result = guiC.leggiMetadatiImg(value);
				newFrame.setOutput(result);
            }});
		//contentPane.add(mntmNewMenuItem_11);
		mnNewMenu.add(mntmNewMenuItem);
		mnNewMenu.add(mntmNewMenuItem_8);
		mnNewMenu.add(mntmNewMenuItem_11);
		

		
		JMenu mnNewMenu_1 = new JMenu("Video");
		mnNewMenu_1.setBounds(10, 61, 111, 24);
		//contentPane.add(mnNewMenu_1);
		JMenuBar menuBar_1 = new JMenuBar();
		contentPane.add(menuBar_1);
		//JButton btnNewButton_1 = new JButton("Video");
		//btnNewButton_1.setBounds(20, 81, 85, 21);
		//contentPane.add(btnNewButton_1);
		//menuBar.add(btnNewButton_1);
        menuBar.add(mnNewMenu_1);
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("INDICE");
		mntmNewMenuItem_1.setBounds(131, 61, 133, 24);
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Pannello newFrame = new Pannello();
                newFrame.setVisible(true);
                value = newFrame.askValue("Inserisci il percorso del video per ottenere un indice di affidabilità");
				result = guiC.calcolaIndiceVideo(value);
				newFrame.setOutput(result);
            }});
		//contentPane.add(mntmNewMenuItem_1);
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("ELENCO");
		mntmNewMenuItem_7.setBounds(98, 131, 133, 24);
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Pannello newFrame = new Pannello();
                newFrame.setVisible(true);
                value = newFrame.askValue("Scrivi il video che vuoi cercare");
				result = guiC.getVideo(value);
				newFrame.setOutput(result);
            }});
		//contentPane.add(mntmNewMenuItem_7);
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("METADATI");
		mntmNewMenuItem_10.setBounds(121, 199, 133, 24);
		//contentPane.add(mntmNewMenuItem_10);
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Pannello newFrame = new Pannello();
                newFrame.setVisible(true);
                value = newFrame.askValue("Inserisci il percorso del video per ottenere un indice di affidabilità");
				result = guiC.leggiMetadatiVideo(value);
				newFrame.setOutput(result);
            }});
		mnNewMenu_1.add(mntmNewMenuItem_1);
		mnNewMenu_1.add(mntmNewMenuItem_7);
		mnNewMenu_1.add(mntmNewMenuItem_10);
		
		
		JMenu mnNewMenu_2 = new JMenu("Audio");
		mnNewMenu_2.setBounds(10, 117, 111, 24);
		//contentPane.add(mnNewMenu_2);
		JMenuBar menuBar_2 = new JMenuBar();
		contentPane.add(menuBar_2);
		//JButton btnNewButton_2 = new JButton("Audio");
		//btnNewButton_2.setBounds(10, 137, 85, 21);
		//contentPane.add(btnNewButton_2);
		//menuBar.add(btnNewButton_2);
        menuBar.add(mnNewMenu_2);
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("INDICE");
		mntmNewMenuItem_2.setBounds(212, 20, 133, 24);
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Pannello newFrame = new Pannello();
                newFrame.setVisible(true);
                value = newFrame.askValue("Inserisci il percorso dell'audio per ottenere un indice di affidabilità");
				result = guiC.calcolaIndiceAudio(value);
				newFrame.setOutput(result);
            }});
		//contentPane.add(mntmNewMenuItem_2);		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("ELENCO");
		mntmNewMenuItem_6.setBounds(314, 117, 133, 24);
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Pannello newFrame = new Pannello();
                newFrame.setVisible(true);
                value = newFrame.askValue("Inserisci il titolo dell'audio che vuoi cercare");
				result = guiC.getAudio(value);
				newFrame.setOutput(result);
            }});
		//contentPane.add(mntmNewMenuItem_6);
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("METADATI");
		mntmNewMenuItem_9.setBounds(195, 169, 133, 24);
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Pannello newFrame = new Pannello();
                newFrame.setVisible(true);
                value = newFrame.askValue("Inserisci il percorso dell'audio della quale vuoi ricevere i metadati");
				result = guiC.leggiMetadatiAudio(value);
				newFrame.setOutput(result);
            }});
		//contentPane.add(mntmNewMenuItem_9);
		mnNewMenu_2.add(mntmNewMenuItem_2);
		mnNewMenu_2.add(mntmNewMenuItem_6);
		mnNewMenu_2.add(mntmNewMenuItem_9);
		
		
		JMenu mnNewMenu_3 = new JMenu("Fonte");
		mnNewMenu_3.setBounds(10, 199, 111, 24);
		//contentPane.add(mnNewMenu_3);
		JMenuBar menuBar_3 = new JMenuBar();
		contentPane.add(menuBar_3);
		//JButton btnNewButton_3 = new JButton("Fonte");
		//btnNewButton_3.setBounds(128, 95, 85, 21);
		//contentPane.add(btnNewButton_3);
		//menuBar.add(btnNewButton_3);
        menuBar.add(mnNewMenu_3);
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("INDICE");
		mntmNewMenuItem_3.setBounds(212, 117, 133, 24);
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Pannello newFrame = new Pannello();
                newFrame.setVisible(true);
                value = newFrame.askValue("Scrivi il link della fonte che vuoi valutare");
				try {
					result = guiC.calcoloIndiceFonte(value);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				newFrame.setOutput(result);
            }});
		//contentPane.add(mntmNewMenuItem_3);
		JMenuItem mntmNewMenuItem_10_1 = new JMenuItem("VALUTAZIONE");
		mntmNewMenuItem_10_1.setBounds(205, 211, 133, 24);
		mntmNewMenuItem_10_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Pannello newFrame = new Pannello();
                newFrame.setVisible(true);
                value = newFrame.askValue("Scrivi il link della fonte che vuoi valutare");
				try {
					result = guiC.rateFonte(value);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				newFrame.setOutput(result);
            }});
		//contentPane.add(mntmNewMenuItem_10_1);
		JMenuItem mntmNewMenuItem_10_2 = new JMenuItem("BLOCCA");
		mntmNewMenuItem_10_2.setBounds(212, 199, 133, 24);
		mntmNewMenuItem_10_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Pannello newFrame = new Pannello();
                newFrame.setVisible(true);
				result = "Funzione in fase di sviluppo, scusateci per il disagio";
				newFrame.setOutput(result);
            }});
		//contentPane.add(mntmNewMenuItem_10_2);
		JMenuItem mntmNewMenuItem_10_3 = new JMenuItem("SBLOCCA");
		mntmNewMenuItem_10_3.setBounds(257, 199, 133, 24);
		mntmNewMenuItem_10_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Pannello newFrame = new Pannello();
                newFrame.setVisible(true);
				result = "Funzione in fase di sviluppo, scusateci per il disagio";
				newFrame.setOutput(result);
            }});
		//contentPane.add(mntmNewMenuItem_10_3);
		mnNewMenu_3.add(mntmNewMenuItem_3);
		mnNewMenu_3.add(mntmNewMenuItem_10_1);
		mnNewMenu_3.add(mntmNewMenuItem_10_2);
		mnNewMenu_3.add(mntmNewMenuItem_10_3);
		
		JMenu mnNewMenu_4 = new JMenu("Notizia");
		mnNewMenu_4.setBounds(10, 165, 111, 24);
		//contentPane.add(mnNewMenu_4);
		JMenuBar menuBar_4 = new JMenuBar();
		contentPane.add(menuBar_4);
		//JButton btnNewButton_5 = new JButton("Notizia");
		//btnNewButton_5.setBounds(-12, 218, 85, 21);
		//contentPane.add(btnNewButton_5);
		//menuBar.add(btnNewButton_5);
        menuBar.add(mnNewMenu_4);
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("INDICE");
		mntmNewMenuItem_4.setBounds(212, 61, 133, 24);
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Pannello newFrame = new Pannello();
                newFrame.setVisible(true);
                value = newFrame.askValue("l'url della notizia che vuoi verifica");
				try {
					result = guiC.calcoloIndiceNotizia(value);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				newFrame.setOutput(result);
            }});
		//contentPane.add(mntmNewMenuItem_4);
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("ELENCO");
		mntmNewMenuItem_5.setBounds(228, 115, 133, 24);
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Pannello newFrame = new Pannello();
                newFrame.setVisible(true);
                value = newFrame.askValue("Scrivi la notizia che vuoi cercare");
				try {
					result = guiC.getNotice(value);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				newFrame.setOutput(result);
            }});
		//contentPane.add(mntmNewMenuItem_5);
		mnNewMenu_4.add(mntmNewMenuItem_4);
		mnNewMenu_4.add(mntmNewMenuItem_5);
		

	}

}
