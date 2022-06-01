import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Diese Klasse baut das Spielfeld auf und verwaltet Aktionen
public class Spielfeld {
	private JFrame frame;
	private JPanel board;
	private ArrayList<Feld> felder;
	private JPanel center;
	private ArrayList<JPanel> borderPanels;
	Font kabel;
	
	public Spielfeld() {
		borderPanels = new ArrayList<JPanel>();
		
		buildBasicFrame();
		
		showFrame();

	}
	
	// Diese Methode erstellt die Grundstruktur des JFrames
	private void buildBasicFrame() {
		frame = new JFrame();
		frame.setTitle("Monopoly");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setSize(new Dimension(868, 896));
		board = new JPanel();
		board.setSize(new Dimension(868, 896));
		board.setLayout(new BorderLayout());
		board.setBackground(Color.white);
		setUpBorders();
		createFelder();
		frame.add(board);
		
	}
	
	private void setUpBorders() {
		for (int i = 0; i < 4; i++) {
			JPanel curPanel = new JPanel();
			
			if (i%2 == 0) {
				//Horizontal
				curPanel.setPreferredSize(new Dimension(780, 110));
				curPanel.setBackground(Color.green);
			} else {
				//Vertikal
				curPanel.setPreferredSize(new Dimension(110, 648));
				curPanel.setBackground(Color.pink);

			}
			curPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));

			
			borderPanels.add(i, curPanel);
		}
		
		//Untere Reihe
		board.add(borderPanels.get(0), BorderLayout.SOUTH);
		//Linke Reihe
		board.add(borderPanels.get(1), BorderLayout.WEST);
		//Obere Reihe
		board.add(borderPanels.get(2), BorderLayout.NORTH);
		//Rechte Reihe
		board.add(borderPanels.get(3), BorderLayout.EAST);
		
		center = new JPanel();
		center.setPreferredSize(new Dimension(648, 648));
		center.setBackground(new Color(133, 224, 190));
		center.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		try {
		    //create the font to use. Specify the size!
			InputStream myStream = new BufferedInputStream(new FileInputStream("Kabel_Regular.ttf"));
		    kabel = Font.createFont(Font.TRUETYPE_FONT, new File("Kabel_Regular.ttf"));
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    //register the font
		    ge.registerFont(kabel);
		} catch (IOException e) {
		    e.printStackTrace();
		} catch(FontFormatException e) {
		    e.printStackTrace();
		}
		JLabel text = new JLabel("Badstraße");

		text.setFont(new Font("kabel", Font.PLAIN, 12));
		//center.add(text);
		center.add(new Ereigniskarte());
		
		board.add(center, BorderLayout.CENTER);
	}
	
	private void createFelder() {

		borderPanels.get(0).add(new Sonderfeld("Gefängnis", 10, "Gefängnis"));

		
		for (int i = 9; i > 0; i--) {
			borderPanels.get(0).add(new Straßenkarte("Straße", i, 72, 110));
		}
		
		borderPanels.get(0).add(new Sonderfeld("Los", 0, "Los"));
		
		for (int i = 19; i > 10; i--) {
			borderPanels.get(1).add(new Straßenkarte("Straße", i, 110, 72));
		}
		
		borderPanels.get(2).add(new Sonderfeld("Frei Parken", 20, "FreiParken"));
		
		for (int i = 20; i < 29; i++) {
			borderPanels.get(2).add(new Straßenkarte("Straße", i+1, 72, 110));
		}
		
		borderPanels.get(2).add(new Sonderfeld("Geh Ins Gefängnis", 30, "GehInsGefängnis"));
		
		for (int i = 30; i < 39; i++) {
			borderPanels.get(3).add(new Straßenkarte("Straße", i+1, 110, 72));
		}

	}
	
	private void showFrame() {
		frame.setVisible(true);
	}
}
