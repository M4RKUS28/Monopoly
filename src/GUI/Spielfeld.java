package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import Gamedata.Feld;
import Gamedata.SettingsLoader;
import Gamelogic.Game;

// Diese Klasse baut das Spielfeld auf und verwaltet Aktionen
public class Spielfeld extends Constants {
	private Game game;

	private JFrame frame;
	private JPanel board;
	private JPanel cardBox;
	private JPanel cards;
	private JPanel center;
	private JPanel base;

	private JPanel[] borderPanel;
	static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	Toolkit tk = Toolkit.getDefaultToolkit();
	private int screenWidth;
	private int screenHeight;
	private int cardWidth;
	private int cardHeight;

	private HashMap<String, Color> colors = new HashMap<>();

	private SettingsLoader sl;
	private Field[] fields;
	private JLabel[] statsList;
	private JLabel[] dices;
	private JPanel dicePanel;
	private JLabel pasch;
	private boolean paschPrev;
	private GEKarte karte;
	private boolean kartePrev;
	private boolean next = false;
	private JLabel nextPlayer;
	private boolean rollDice = true;
	private JLabel dice;
	private JLabel curPlayer;
	private int[] figuernPos;
	
	public Spielfeld(SettingsLoader sl) {
		this.sl = sl;
	}

	public void initGame(Game game, String path) {
		this.game = game;
		sl.loadData(path);

		borderPanel = new JPanel[4];
		fields = new Field[40];
		statsList = new JLabel[8];
		dices = new JLabel[2];

		figuernPos = new int[4];

		for (int i = 0; i < 4; i++) {
			figuernPos[i] = 0;
		}

		setUpConstants();

		setUpColors();
		buildBasicFrame();

		showFrame();

		game.start();
	}

	private void setUpConstants() {
		screenWidth = tk.getScreenSize().width;
		screenHeight = tk.getScreenSize().height;
		Constants.setScreenWidth(screenWidth);
		Constants.setScreenHeight(screenHeight);
		cardWidth = (int) (screenHeight / 12);
		cardHeight = (int) (cardWidth * 1.5);
		Constants.setCardWidth(cardWidth);
		Constants.setCardHeight(cardHeight);
		Constants.setFigureSize((int) (0.2 * cardWidth));
		Constants.createFonts();
	}

	private void setUpColors() {
		colors.put("braun", new Color(0x894900));
		colors.put("blau_hell", new Color(0x56C1FF));
		colors.put("pink", new Color(0xD41876));
		colors.put("orange", new Color(0xFEAE00));
		colors.put("rot", new Color(0xEE220C));
		colors.put("gelb", new Color(0xFFF056));
		colors.put("gruen", new Color(0x61D836));
		colors.put("blau", new Color(0x0076BA));
		colors.put("board", new Color(0xB2DAAD));
		colors.put("hintergrund", new Color(0x92B28E));
		colors.put("grau", new Color(213, 213, 213, 100));
		colors.put("Prot", new Color(0xB51700));
		colors.put("Pblau", new Color(0x004D80));
		colors.put("Pgrün", new Color(0x017100));
		colors.put("Pgelb", new Color(0xF27200));

	}

	// Diese Methode erstellt die Grundstruktur des JFrames
	private void buildBasicFrame() {
		frame = new JFrame();
		frame.setTitle("Monopoly");
		frame.setIconImage((new ImageIcon("src/images/Dude.jpg")).getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setSize(new Dimension(screenWidth, screenHeight));
		device.setFullScreenWindow(frame);
		frame.getContentPane().setBackground(colors.get("hintergrund"));
		frame.setResizable(false);
		frame.setLayout(null);
		

		createControlls();
		buildBoard();
		createKartenbehaelter();
		createStats();
		// setUpBorders();
		// createFelder();

	}

	private void createControlls() {
		JPanel controllpanel = new JPanel();
		controllpanel.setBounds((int) (screenWidth * 0.01), (int) (screenHeight / 2 - (cardHeight)), cardHeight,
				3 * cardWidth + 5);
		controllpanel.setBackground(colors.get("board"));
		controllpanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
		controllpanel.setLayout(null);

		dice = new JLabel();
		dice.setIcon(new ImageIcon(new ImageIcon("src/images/DiceEnabled.png").getImage().getScaledInstance(
				(int) ((cardHeight * 0.5 / 1210) * 1210), (int) ((cardHeight * 0.5 / 1210) * 1210),
				Image.SCALE_DEFAULT)));
		dice.setBounds((int) (0.25 * cardHeight), (int) (0.05 * 3 * cardWidth), (int) (cardHeight * 0.5),
				(int) (0.5 * cardHeight));
		dice.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Roll dice");
				if (rollDice) {
					disableDice();
					game.wuerfeln();
					
				}
				enableNext();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		controllpanel.add(dice);

		JLabel buildHouse = new JLabel();
		buildHouse.setIcon(new ImageIcon(new ImageIcon("src/images/HausEnabled.png").getImage().getScaledInstance(
				(int) ((Constants.cardHeight * 0.5 / 1210) * 1210), (int) ((Constants.cardHeight * 0.5 / 1210) * 1210),
				Image.SCALE_DEFAULT)));
		buildHouse.setBounds((int) (0.25 * Constants.cardHeight),
				(int) (1.5 * Constants.cardWidth - Constants.cardHeight * 0.25), (int) (Constants.cardHeight * 0.5),
				(int) (0.5 * Constants.cardHeight));
		buildHouse.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				game.toggleHausBauen();
				if (game.getHausBauen()) {
					hausBauenEin();
				} else {
					hausBauenAus();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		controllpanel.add(buildHouse);

		nextPlayer = new JLabel();
		nextPlayer.setIcon(new ImageIcon(new ImageIcon("src/images/NextDisabled.png").getImage().getScaledInstance(
				(int) ((cardHeight * 0.5 / 1210) * 1210), (int) ((cardHeight * 0.5 / 1210) * 1210),
				Image.SCALE_DEFAULT)));
		nextPlayer.setBounds((int) (0.25 * cardHeight), (int) (0.95 * 3 * cardWidth - cardHeight * 0.5),
				(int) (cardHeight * 0.5), (int) (0.5 * cardHeight));
		nextPlayer.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Next Player");
				nextPlayer();
				game.naechster();
				disableNext();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		controllpanel.add(nextPlayer);

		frame.add(controllpanel);
		frame.repaint();
		createEnde();

	}
	
	private void createEnde() {
		MouseListener ml = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		};
		JPanel ende = new JPanel();
		ende.setBackground(Constants.colors.get("board"));
		ende.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
		ende.setBounds((int) (screenWidth * 0.01), (int) (screenHeight / 2 - (cardHeight)+ 3 * cardWidth + 10), cardHeight,
				(int)(0.25*cardHeight));
		ende.setLayout(new BorderLayout());
		ende.addMouseListener(ml);
		JLabel text = new JLabel();
		text.setText("Beende Runde");
		text.setFont(new Font("Arial", Font.BOLD, Integer.valueOf(Constants.fonts.get("straßenname"))));
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setVerticalAlignment(SwingConstants.CENTER);
		text.addMouseListener(ml);
		ende.add(text, BorderLayout.CENTER);
		frame.add(ende);
		frame.repaint();
	}

	private void buildBoard() {
		board = new JPanel();
		board.setBounds((int) (screenWidth * 0.02 + cardHeight), (int) ((screenHeight - 12 * cardWidth) / 2),
				12 * cardWidth, 12 * cardWidth);
		board.setLayout(new BorderLayout(0, 0));
		createEdges();
		createFields();
		createDicePanel();
		frame.add(board);

	}

	public void showGKarte(int id) {
		// center.removeAll();
		karte = new GEKarte(sl.getGcardsList()[id].getText(), "Gemeinschaftskarte",
				(int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (3.2 * Constants.cardWidth) / 2),
				(int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (1.85 * Constants.cardWidth) / 2),
				(int) (3.2 * Constants.cardWidth), (int) (1.85 * Constants.cardWidth), game);
		center.add(karte);
		center.revalidate();
		center.repaint();
		kartePrev = true;
	}

	public void showEKarte(int id) {
		// center.removeAll();
		karte = new GEKarte(sl.getEcardsList()[id].getText(), "Ereigniskarte",
				(int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (3.2 * Constants.cardWidth) / 2),
				(int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (1.25*(1.85 * Constants.cardWidth)) / 2),
				(int) (3.2 * Constants.cardWidth), (int) (1.85 * Constants.cardWidth), game);
		center.add(karte);
		center.repaint();
		kartePrev = true;

	}

	private void nextPlayer() {
		if (kartePrev) {
			center.remove(karte);
			center.repaint();
			kartePrev = false;
		}
		
		enableDice();
	}

	private void createEdges() {
		for (int i = 0; i < 4; i++) {
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout());
			if (i % 2 == 0) { // horizontal
				panel.setPreferredSize(new Dimension(screenHeight, cardHeight));
				panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			} else {
				panel.setPreferredSize(new Dimension(cardHeight, screenHeight - 2 * cardHeight));
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			}
			// panel.setLayout(new FlowLayout(FlowLayout.CENTER, -5, 0));
			panel.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));

			borderPanel[i] = panel;

		}
		center = new JPanel();
		center.setPreferredSize(new Dimension(screenHeight - 2 * cardHeight, screenHeight - 2 * cardHeight));
		center.setBackground(colors.get("board"));
		center.setLayout(null);
		center.add(new Figure("rot"));
		/*center.add(new GEKarte(
				"Du wirst zu Strassenausbesserungsar-/nbeiten herangezogen. Zahle für Deine/n Häuser und Hotels/nDM 800 je Haus/nDM 2300 je Hotel/nan die Bank.",
				"Ereigniskarte", (int) ((screenHeight - 2 * cardHeight) / 2 - (3.2 * cardWidth) / 2),
				(int) ((screenHeight - 2 * cardHeight) / 2 - (1.85 * cardWidth) / 2), (int) (3.2 * cardWidth),
				(int) (1.85 * cardWidth)));*/
		center.repaint();
		board.add(center, BorderLayout.CENTER);
		board.add(borderPanel[0], BorderLayout.SOUTH);
		board.add(borderPanel[1], BorderLayout.WEST);
		board.add(borderPanel[2], BorderLayout.NORTH);
		board.add(borderPanel[3], BorderLayout.EAST);
	}

	private void createFields() {
		Feld[] felder = sl.getFelderList();
		for (Feld feld : felder) {

			switch (feld.type()) {
			case STRASSE:

				Straßenkarte k = new Straßenkarte(feld.getName(), feld.getPos(), Constants.cardWidth,
						Constants.cardHeight, Constants.colors.get(feld.toStrasse().getFarbe()), feld.getPreis(), game);
				fields[feld.getPos()] = k;
				break;
			case BAHN:
				Bahnhof b = new Bahnhof(feld.getName(), feld.getPos(), Constants.cardWidth, Constants.cardHeight,
						feld.getPreis(), game);
				fields[feld.getPos()] = b;
				break;
			case WERK:
				InfrastrukturKarte i = new InfrastrukturKarte(feld.getName(), feld.getPos(), Constants.cardWidth,
						Constants.cardHeight, feld.getPreis(), game);
				fields[feld.getPos()] = i;
				break;
			case SONDERFELD:
				switch (feld.getPos()) {
				case 0:
					Sonderfeld s = new Sonderfeld("Los", 0, "Los", cardHeight);
					fields[0] = s;
					break;
				case 2:
				case 17:
				case 33:
					GESFeld g = new GESFeld("Gemeinschaftsfeld", feld.getPos(), Constants.cardWidth,
							Constants.cardHeight);
					fields[feld.getPos()] = g;
					break;
				case 7:
				case 22:
				case 36:
					GESFeld e = new GESFeld("Ereignisfeld", feld.getPos(), Constants.cardWidth, Constants.cardHeight);
					fields[feld.getPos()] = e;
					break;
				case 4:
					GESFeld es = new GESFeld("Einkommenssteuer", feld.getPos(), Constants.cardWidth,
							Constants.cardHeight, sl.getEinkommensteuer());
					fields[4] = es;
					break;
				case 38:
					GESFeld z = new GESFeld("Zusatzsteuer", feld.getPos(), Constants.cardWidth, Constants.cardHeight,
							sl.getZusatzsteuer());
					fields[38] = z;
					break;
				case 10:
					Sonderfeld ge = new Sonderfeld("Gefängnis", 10, "Gefängnis", Constants.cardHeight);
					fields[10] = ge;
					break;
				case 20:
					Sonderfeld f = new Sonderfeld("FreiParken", 20, "FreiParken", Constants.cardHeight);
					fields[20] = f;
					break;
				case 30:
					Sonderfeld geh = new Sonderfeld("GehInsGefängnis", 30, "GehInsGefängnis", cardHeight);
					fields[30] = geh;
					break;
				}
				break;
			case UNKNOWN:
				System.out.println(feld.getPos() + "UNKNOWN");
				break;
			}
		}

		for (int i = 10; i >= 0; i--) {
			borderPanel[0].add(fields[i]);
		}
		for (int i = 20; i >= 11; i--) {
			borderPanel[1].add(fields[i]);
		}
		for (int i = 20; i <= 30; i++) {
			borderPanel[2].add(fields[i]);
		}
		for (int i = 31; i < 40; i++) {
			borderPanel[3].add(fields[i]);
		}

		board.revalidate();
		board.repaint();
	}

	private void createDicePanel() {
		
		dicePanel = new JPanel();
		dicePanel.setBackground(Constants.colors.get("board"));
		dicePanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
		dicePanel.setBounds(
				(int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (1.375 * Constants.cardHeight) / 2),
				(int) (0.1 * cardHeight), (int) (1.375 * Constants.cardHeight), (int) (0.75 * Constants.cardHeight));
		dicePanel.setLayout(null);

		JLabel d1 = new JLabel();
		d1.setIcon(new ImageIcon(new ImageIcon("src/images/One.png").getImage().getScaledInstance(
				(int) ((cardHeight * 0.5 / 1210) * 1210), (int) ((cardHeight * 0.5 / 1210) * 1210),
				Image.SCALE_DEFAULT)));
		d1.setBounds((int) (0.125 * Constants.cardHeight), (int) (0.125 * Constants.cardHeight),
				(int) (0.5 * Constants.cardHeight), (int) (0.5 * Constants.cardHeight));
		dices[0] = d1;
		dicePanel.add(d1);
		JLabel d2 = new JLabel();
		d2.setIcon(new ImageIcon(new ImageIcon("src/images/One.png").getImage().getScaledInstance(
				(int) ((cardHeight * 0.5 / 1210) * 1210), (int) ((cardHeight * 0.5 / 1210) * 1210),
				Image.SCALE_DEFAULT)));
		d2.setBounds((int) (0.75 * Constants.cardHeight), (int) (0.125 * Constants.cardHeight),
				(int) (0.5 * Constants.cardHeight), (int) (0.5 * Constants.cardHeight));
		dices[1] = d2;
		dicePanel.add(d2);
		center.add(dicePanel);
		center.repaint();
	}
	
	private void createPaschNotify() {
		pasch = new JLabel();
		pasch.setFont(new Font("Arial", Font.BOLD, Integer.valueOf(Constants.fonts.get("straßenname"))));
		pasch.setBorder(BorderFactory.createMatteBorder(0, 5, 5, 5, Color.black));
		pasch.setBackground(Constants.colors.get("board"));
		pasch.setBounds(
				(int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (1.375 * Constants.cardHeight) / 2),
				(int) (0.01*cardHeight+0.75*Constants.cardHeight), (int) (1.375 * Constants.cardHeight), (int) (0.25 * Constants.cardHeight));
		pasch.setText("<html><head></head><center>Pasch: nochmal würfeln </center></body></html>");
		pasch.setVerticalAlignment(SwingConstants.BOTTOM);
		center.add(pasch);
		center.repaint();
	}

	public void updateDice(int w1, int w2) {
		if (w1 == w2) {
			createPaschNotify();
			paschPrev=true;
		} else {
			if (paschPrev) {
				center.remove(pasch);
				center.repaint();
			}
			paschPrev = false;
		}
		int[] w = { w1, w2 };
		for (int i = 0; i < 2; i++) {
			switch (w[i]) {
			case 1:
				dices[i].setIcon(new ImageIcon(new ImageIcon("src/images/One.png").getImage().getScaledInstance(
						(int) ((cardHeight * 0.5 / 1210) * 1210), (int) ((cardHeight * 0.5 / 1210) * 1210),
						Image.SCALE_DEFAULT)));
				break;
			case 2:
				dices[i].setIcon(new ImageIcon(new ImageIcon("src/images/Two.png").getImage().getScaledInstance(
						(int) ((cardHeight * 0.5 / 1210) * 1210), (int) ((cardHeight * 0.5 / 1210) * 1210),
						Image.SCALE_DEFAULT)));
				break;
			case 3:
				dices[i].setIcon(new ImageIcon(new ImageIcon("src/images/Three.png").getImage().getScaledInstance(
						(int) ((cardHeight * 0.5 / 1210) * 1210), (int) ((cardHeight * 0.5 / 1210) * 1210),
						Image.SCALE_DEFAULT)));
				break;
			case 4:
				dices[i].setIcon(new ImageIcon(new ImageIcon("src/images/Four.png").getImage().getScaledInstance(
						(int) ((cardHeight * 0.5 / 1210) * 1210), (int) ((cardHeight * 0.5 / 1210) * 1210),
						Image.SCALE_DEFAULT)));
				break;
			case 5:
				dices[i].setIcon(new ImageIcon(new ImageIcon("src/images/Five.png").getImage().getScaledInstance(
						(int) ((cardHeight * 0.5 / 1210) * 1210), (int) ((cardHeight * 0.5 / 1210) * 1210),
						Image.SCALE_DEFAULT)));
				break;
			case 6:
				dices[i].setIcon(new ImageIcon(new ImageIcon("src/images/Six.png").getImage().getScaledInstance(
						(int) ((cardHeight * 0.5 / 1210) * 1210), (int) ((cardHeight * 0.5 / 1210) * 1210),
						Image.SCALE_DEFAULT)));
				break;
			}
		}
		dicePanel.revalidate();
		dicePanel.repaint();

	}

	public void updateCurPlayer(int player) {
		curPlayer.setText("<html><head></head><body><center>Spieler " + String.valueOf(player+1) + "</center></body></html>");
		if (player == 0) {
			curPlayer.setForeground(Constants.colors.get("Prot"));
		} else if (player == 1) {
			curPlayer.setForeground(Constants.colors.get("Pgelb"));
		} else if (player == 2) {
			curPlayer.setForeground(Constants.colors.get("Pgrün"));
		} else if (player == 3) {
			curPlayer.setForeground(Constants.colors.get("Pblau"));
		}
		curPlayer.repaint();
	}
	
	private void createKartenbehaelter() {
		cardBox = new JPanel();
		cardBox.setBounds((int) (screenWidth * 0.03 + cardHeight + screenHeight),
				(int) ((screenHeight - 12 * cardWidth) / 2), 3 * cardWidth, 12 * cardWidth);
		cardBox.setBackground(Constants.colors.get("board"));
		cardBox.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
		cardBox.setLayout(null);

		createCards();
		
		curPlayer = new JLabel();
		curPlayer.setBackground(Constants.colors.get("board"));
		curPlayer.setFont(new Font("Arial", Font.BOLD, 36));
		curPlayer.setText("<html><head></head><body><center>Spieler 1</center></body></html>");
		curPlayer.setForeground(Constants.colors.get("Prot"));
		curPlayer.setBounds(0, 0, 3*cardWidth, (int)(0.5*cardHeight));
		curPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		curPlayer.setVerticalAlignment(SwingConstants.CENTER);
		curPlayer.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
		cardBox.add(curPlayer);
		JScrollPane scrollPane = new JScrollPane(cards);
		scrollPane.setBounds(0, (int)(0.5*cardHeight-5), 3 * cardWidth, (int)(12 * cardWidth-0.5*cardHeight+5));
		scrollPane.setBackground(new Color(178, 218, 173));
		scrollPane.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		
		cardBox.add(scrollPane);
		frame.add(cardBox);
		frame.repaint();
	}

	public void updateKartenbehaelter(ArrayList<Integer> b) {
		Collections.sort(b);
		Feld[] felder = sl.getFelderList();
		ArrayList<Feld> besitz = new ArrayList<Feld>();
		
		for (Integer f: b) {
			besitz.add(felder[f]);
		}
		
		ArrayList<JLayeredPane> collectors = new ArrayList<JLayeredPane>();

		JLayeredPane c = new JLayeredPane();
		c.setLayout(null);
		c.setBounds((int) (0), (int) (0), cardWidth, (int) (2.2 * cardHeight));

		JLayeredPane bahn = new JLayeredPane();
		bahn.setLayout(null);
		bahn.setBounds((int) (0), (int) (0), cardWidth, (int) (3.5 * cardHeight));

		JLayeredPane werk = new JLayeredPane();
		werk.setLayout(null);
		werk.setBounds((int) (0), (int) (0), cardWidth, (int) (2.2 * cardHeight));

		int layer = 0;
		int layerb = 0;
		int layerw = 0;
		String prevFarbe = "null";

		for (Feld feld : besitz) {
			switch (feld.type()) {
			case STRASSE:
				if (!feld.toStrasse().getFarbe().equals(prevFarbe)) {
					if (!prevFarbe.equals("null")) {	
						
						collectors.add(c);
					}
					c = new JLayeredPane();
					c.setLayout(null);
					c.setBounds((int) (0), (int) (0), cardWidth, (int) (2.2 * cardHeight));
					layer = 0;
				}
				Straßenkarte s = new Straßenkarte(feld.getName(), feld.getPos()*-1, Constants.cardWidth, Constants.cardHeight,
						Constants.colors.get(feld.toStrasse().getFarbe()), feld.getPreis(), game);
				s.setBounds(0, (int) (layer * (0.6 * cardHeight)), cardWidth, cardHeight);
				c.add(s, Integer.valueOf(layer));
				
				prevFarbe = feld.toStrasse().getFarbe();
				layer++;
				break;
			case BAHN:
				Bahnhof bahnhof = new Bahnhof(feld.getName(), feld.getPos()*-1, Constants.cardWidth, Constants.cardHeight,
						feld.getPreis(), game);
				bahnhof.setBounds(0, (int)(layerb*(0.6*Constants.cardHeight)), Constants.cardWidth, Constants.cardHeight);
				bahn.add(bahnhof, Integer.valueOf(layerb));
				bahnhof.repaint();
				layerb++;
				break;
		
			case WERK:
				InfrastrukturKarte i = new InfrastrukturKarte(feld.getName(), feld.getPos()*-1, Constants.cardWidth,
						Constants.cardHeight, feld.getPreis(), game);
				i.setBounds(0, (int)(layerw*(0.6*Constants.cardHeight)), Constants.cardWidth, Constants.cardHeight);
				werk.add(i, Integer.valueOf(layerw));
				
				i.repaint();
				layerw++;
				break;
			default:
				break;
			}

		}
		if (layer > 0) {
			collectors.add(c);
		}
		if (layerw > 0) {
			collectors.add(werk);
		}
		if (layerb > 0) {
			collectors.add(bahn);
		}
		
		cards.removeAll();
		int index = 0;
		
		for (int i = 0; i < collectors.size(); i++) {
			if (i%2 == 0) {
				if (i == collectors.size()-1) {
					collectors.get(index).setBounds((int) (0.3 * cardWidth),
							(int) ((int)(i/2) * 2.75 * cardHeight + 0.25 * cardHeight), cardWidth, (int) (3.5 * cardHeight));
				} else {
					collectors.get(index).setBounds((int) (0.3 * cardWidth),
							(int) ((int)(i/2) * 2.75 * cardHeight + 0.25 * cardHeight), cardWidth, (int) (2.2 * cardHeight));
				}
				
			} else {
				if (i == collectors.size()-1) {
				collectors.get(index).setBounds((int) (1.6 * cardWidth),
						(int) ((int)(i/2)  * 2.75 * cardHeight + 0.25 * cardHeight), cardWidth, (int) (3.5 * cardHeight));
				} else {
					collectors.get(index).setBounds((int) (1.6 * cardWidth),
							(int) ((int)(i/2)  * 2.75 * cardHeight + 0.25 * cardHeight), cardWidth, (int) (2.2 * cardHeight));
				}
			}
			
			cards.add(collectors.get(index));
			collectors.get(index).repaint();
			index++;
			if (index >= collectors.size()) {
				break;
			}
		}
		
	
		cards.revalidate();
		cards.repaint();
	}

	private void createCards() {
		cards = new JPanel();
		cards.setPreferredSize(new Dimension(3 * cardWidth, (int) (13.75 * cardHeight)));
		cards.setLayout(null);
		cards.setBackground(colors.get("board"));

	
	}

	public void updateStats(int[] values) {
		for (int i = 0; i < 4; i++) {
			statsList[i].setText("Spieler " + String.valueOf(values[i] + 1));
			if (values[i+4] == -999999999) {
				statsList[i].setText("Bankrott");
			}
			if (values[i] == 0) {
				statsList[i].setForeground(Constants.colors.get("Prot"));
			} else if (values[i] == 1) {
				statsList[i].setForeground(Constants.colors.get("Pgelb"));
			} else if (values[i] == 2) {
				statsList[i].setForeground(Constants.colors.get("Pgrün"));
			} else if (values[i] == 3) {
				statsList[i].setForeground(Constants.colors.get("Pblau"));
			}
			statsList[i + 4].setText("DM " + String.valueOf(values[i + 4]) + ".-");
		}
		base.revalidate();
		base.repaint();
	}

	private void createStats() {
		base = new JPanel();
		base.setBackground(colors.get("hintergrund"));
		// base.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
		base.setLayout(new GridLayout(4, 1, 0, 30));
		int width = (int) (0.99 * screenWidth - (screenWidth * 0.04 + cardHeight + screenHeight + 3 * cardWidth));
		int height = (int) ((1.08 * cardWidth) / (1.5 * cardHeight) * width);
		if (width > 1.5 * cardHeight) {
			width = (int) (1.5 * cardHeight);
			height = (int) (1.08 * cardWidth);
		}
		Constants.fonts.put("name", String.valueOf((int) (0.8 * width) / 6));
		Constants.fonts.put("money", String.valueOf((int) (width) / 6));

		base.setBounds((int) (screenWidth * 0.04 + cardHeight + screenHeight + 3 * cardWidth),
				(int) (screenHeight * 0.5 - (height * 4 + 4 / 3 * height) / 2), width,
				(int) (height * 4 + 4 / 3 * height));
		
		String[] color = {"Prot", "Pgelb", "Pgrün", "Pblau"};
		
		for (int i = 0; i < 4; i++) {
			JLayeredPane stats = new JLayeredPane();
			stats.setBackground(colors.get("board"));
			stats.setLayout(null);
			stats.setPreferredSize(new Dimension(width, height));

			JPanel namePanel = new JPanel();
			namePanel.setName("namePanel");
			namePanel.setBounds((int) (0.1 * width), 0, (int) (0.8 * width), (int) (0.5 * height));
			namePanel.setBackground(colors.get("board"));
			namePanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));

			JLabel name = new JLabel();
			name.setName("name");
			name.setText("<html><body><center>Spieler " + String.valueOf(i + 1) + "</center></body></html>");
			name.setHorizontalAlignment(SwingConstants.CENTER);
			name.setVerticalAlignment(SwingConstants.CENTER);
			name.setFont(new Font("Arial", Font.BOLD, Integer.valueOf(Constants.fonts.get("name"))));
			name.setForeground(colors.get(color[i]));
			statsList[i] = name;
			namePanel.add(name);
			stats.add(namePanel, Integer.valueOf(1));

			JPanel moneyPanel = new JPanel();
			moneyPanel.setName("moneyPanel");
			moneyPanel.setBounds(0, (int) (0.4 * height), (int) (width), (int) (0.68 * height - 5));
			moneyPanel.setBackground(colors.get("board"));
			moneyPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));

			JLabel money = new JLabel();
			money.setName("money");
			money.setText("<html><body><center>DM 30000.-</center></body></html>");
			money.setHorizontalAlignment(SwingConstants.CENTER);
			money.setVerticalAlignment(SwingConstants.BOTTOM);
			money.setFont(new Font("Arial", Font.BOLD, Integer.valueOf(Constants.fonts.get("money"))));
			statsList[i + 4] = money;

			moneyPanel.add(money);
			stats.add(moneyPanel, Integer.valueOf(0));
			base.add(stats);

		}
		frame.add(base);
		frame.repaint();
	}

	public void figurenUpdate(int[] pos) {
		if (figuernPos[0] > -1) {
			fields[figuernPos[0]].removePlayer("Prot");
		}
		if (figuernPos[1] > -1) {
			fields[figuernPos[1]].removePlayer("Pgelb");
		}
		if (figuernPos[2] > -1) {
			fields[figuernPos[2]].removePlayer("Pgrün");
		}
		if (figuernPos[3] > -1) {
			fields[figuernPos[3]].removePlayer("Pblau");
		}

		figuernPos = pos;

		if (figuernPos[0] > -1) {
			fields[figuernPos[0]].addPlayer("Prot");
		}
		if (figuernPos[1] > -1) {
			fields[figuernPos[1]].addPlayer("Pgelb");
		}
		if (figuernPos[2] > -1) {
			fields[figuernPos[2]].addPlayer("Pgrün");
		}
		if (figuernPos[3] > -1) {
			fields[figuernPos[3]].addPlayer("Pblau");
		}
	}

	public void insGef(int x) {
		switch (x) {
		case 0:
			fields[figuernPos[0]].removePlayer("Prot");
			((Sonderfeld) fields[10]).addPlayerToGef("Prot");
			break;
		case 1:
			fields[figuernPos[1]].removePlayer("Pgelb");
			((Sonderfeld) fields[10]).addPlayerToGef("Pgelb");
			break;
		case 2:
			fields[figuernPos[2]].removePlayer("Pgrün");
			((Sonderfeld) fields[10]).addPlayerToGef("Pgrün");
			break;
		case 3:
			fields[figuernPos[3]].removePlayer("Pblau");
			((Sonderfeld) fields[10]).addPlayerToGef("Pblau");
			break;
		}
	}

	public void ausGef(int x) {
		switch (x) {
		case 0:
			fields[10].addPlayer("Prot");
			((Sonderfeld) fields[10]).removePlayerFromGef("Prot");
			break;
		case 1:
			fields[10].addPlayer("Pgelb");
			((Sonderfeld) fields[10]).removePlayerFromGef("Pgelb");
			break;
		case 2:
			fields[10].addPlayer("Pgrün");
			((Sonderfeld) fields[10]).removePlayerFromGef("Pgrün");
			break;
		case 3:
			fields[10].addPlayer("Pblau");
			((Sonderfeld) fields[10]).removePlayerFromGef("Pblau");
			break;
		}
	}
	
	public void zahleMiete(int spieler, int miete) {
		String[] buttons = {"OK"};
		center.add(new Info("Du zahlst DM " + String.valueOf(miete) + " an Spieler " + String.valueOf(spieler+1), buttons, (int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (3.2 * Constants.cardWidth) / 2),
				(int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (1.85 * Constants.cardWidth) / 2),
				(int) (3.2 * Constants.cardWidth), (int) (1.85 * Constants.cardWidth), game));
		center.repaint();

	}
	
	public void zahleMieteWerk(int spieler, int zahl) {
		String[] buttons = {"Würfeln"};
		center.add(new Info("Die Miete an Spieler " + String.valueOf(spieler+1) + " ist " + String.valueOf(zahl) + "mal so hoch, wie Augen gewürfelt werden.", buttons, (int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (3.2 * Constants.cardWidth) / 2),
				(int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (1.85 * Constants.cardWidth) / 2),
				(int) (3.2 * Constants.cardWidth), (int) (1.85 * Constants.cardWidth), game));
		center.repaint();
	}
	
	public void grundstueckKaufen(int position) {
		String[] buttons = {"Ja", "Nein"};
		Feld[] felder = sl.getFelderList();
		center.add(new Info("Möchtest du das Grundstück " + felder[position].getName() + " für DM " + felder[position].getPreis() + " kaufen?", buttons, (int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (3.2 * Constants.cardWidth) / 2),
				(int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (1.85 * Constants.cardWidth) / 2),
				(int) (3.2 * Constants.cardWidth), (int) (1.85 * Constants.cardWidth), game));
		center.repaint();

	}
	
	public void bankrottGehen() {
		String[] buttons = {"Beenden", "Hypothek"};
		center.add(new Info("ACHTUNG: Du hast Schulden. Klicke auf \"Hypothek\", um Hypotheken aufzunehemen.", buttons, (int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (3.2 * Constants.cardWidth) / 2),
				(int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (1.85 * Constants.cardWidth) / 2),
				(int) (3.2 * Constants.cardWidth), (int) (1.85 * Constants.cardWidth), game));
	}
	
	public void hypothekAufnehmen() {
		String[] buttons = {"OK"};
		center.add(new Info("Wähle Besitztümer aus, die du mit einer Hypothek belasten möchtest", buttons, (int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (3.2 * Constants.cardWidth) / 2),
				(int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (1.85 * Constants.cardWidth) / 2),
				(int) (3.2 * Constants.cardWidth), (int) (1.85 * Constants.cardWidth), game));
	
	}
	
	public void imGefaegnis() {
		if (game.getGefZahl() > 0) {
			String[] buttons = {"DM 1000", "Pasch"};
			Info info = new Info("Um aus dem Gefängnis freizukommen, hast du zwei Möglichkeiten:", buttons, (int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (3.2 * Constants.cardWidth) / 2),
					(int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (1.85 * Constants.cardWidth) / 2),
					(int) (3.2 * Constants.cardWidth), (int) (1.85 * Constants.cardWidth), game);
			center.add(info);
			
		} else if (game.getGefZahl() == 0){
			String[] buttons = {"DM 1000"};
			Info info = new Info("Du musst dich aus dem Gefängnis freikaufen:", buttons, (int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (3.2 * Constants.cardWidth) / 2),
					(int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (1.85 * Constants.cardWidth) / 2),
					(int) (3.2 * Constants.cardWidth), (int) (1.85 * Constants.cardWidth), game);
			center.add(info);
		} else {
			System.out.println("Fehler bei der Anzahl der NochImGefRunden");
		}
		center.repaint();
	}
	
	public void hausBauenEin() {
		String[] buttons = {"OK"};
		center.add(new Info("Hausbaumodus ist aktiviert.", buttons, (int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (3.2 * Constants.cardWidth) / 2),
				(int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (1.85 * Constants.cardWidth) / 2),
				(int) (3.2 * Constants.cardWidth), (int) (1.85 * Constants.cardWidth), game));
	
	
	}
	
	public void hausBauenAus() {
		String[] buttons = {"OK"};
		center.add(new Info("Hausbaumodus ist deaktiviert.", buttons, (int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (3.2 * Constants.cardWidth) / 2),
				(int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (1.85 * Constants.cardWidth) / 2),
				(int) (3.2 * Constants.cardWidth), (int) (1.85 * Constants.cardWidth), game));
	}
	
	public void einkommenssteuer() {
		String[] buttons = {"OK"};
		center.add(new Info("Du musst DM 4000 Einkommenssteuer zahlen.", buttons, (int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (3.2 * Constants.cardWidth) / 2),
				(int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (1.85 * Constants.cardWidth) / 2),
				(int) (3.2 * Constants.cardWidth), (int) (1.85 * Constants.cardWidth), game));
	}
	
	public void zusatzsteuer() {
		String[] buttons = {"OK"};
		center.add(new Info("Du musst DM 2000 Einkommenssteuer zahlen.", buttons, (int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (3.2 * Constants.cardWidth) / 2),
				(int) ((Constants.screenHeight - 2 * Constants.cardHeight) / 2 - (1.85 * Constants.cardWidth) / 2),
				(int) (3.2 * Constants.cardWidth), (int) (1.85 * Constants.cardWidth), game));
	}
	
	public void enableNext() {
		next = true;
		nextPlayer.setIcon(new ImageIcon(new ImageIcon("src/images/NextEnabled.png").getImage().getScaledInstance(
				(int) ((cardHeight * 0.5 / 1210) * 1210), (int) ((cardHeight * 0.5 / 1210) * 1210),
				Image.SCALE_DEFAULT)));
		nextPlayer.repaint();
	}
	
	
	
	public void disableNext() {
		next = false;;
		nextPlayer.setIcon(new ImageIcon(new ImageIcon("src/images/NextDisabled.png").getImage().getScaledInstance(
				(int) ((cardHeight * 0.5 / 1210) * 1210), (int) ((cardHeight * 0.5 / 1210) * 1210),
				Image.SCALE_DEFAULT)));
		nextPlayer.repaint();
	}
	
	public void enableDice() {
		rollDice = true;
		dice.setIcon(new ImageIcon(new ImageIcon("src/images/DiceEnabled.png").getImage().getScaledInstance(
				(int) ((cardHeight * 0.5 / 1210) * 1210), (int) ((cardHeight * 0.5 / 1210) * 1210),
				Image.SCALE_DEFAULT)));
		dice.repaint();

	}

	public void disableDice() {
		rollDice = false;
		dice.setIcon(new ImageIcon(new ImageIcon("src/images/DiceDisabled.png").getImage().getScaledInstance(
				(int) ((cardHeight * 0.5 / 1210) * 1210), (int) ((cardHeight * 0.5 / 1210) * 1210),
				Image.SCALE_DEFAULT)));
		dice.repaint();
	}

	private void showFrame() {
		frame.setVisible(true);
	}
}
