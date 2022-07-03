package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
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

// Diese Klasse baut das Spielfeld auf und verwaltet Aktionen
public class Spielfeld extends Constants {
	private JFrame frame;
	private JPanel board;
	private JPanel cardBox;
	private JPanel cards;
	private ArrayList<Field> felder;
	private JPanel center;

	private JPanel[] borderPanel;
	static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	Toolkit tk = Toolkit.getDefaultToolkit();
	private int screenWidth;
	private int screenHeight;
	private int cardWidth;
	private int cardHeight;

	private HashMap<String, Color> colors = new HashMap<>();

	public Spielfeld() {
		borderPanel = new JPanel[4];
		felder = new ArrayList<Field>();
		
		setUpConstants();
		
		setUpColors();
		buildBasicFrame();

		showFrame();

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
		Constants.setFigureSize((int)(0.2*cardWidth));

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

		JLabel dice = new JLabel();
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
		buildHouse.setIcon(new ImageIcon(new ImageIcon("src/images/HausDisabled.png").getImage().getScaledInstance(
				(int) ((cardHeight * 0.5 / 1210) * 1210), (int) ((cardHeight * 0.5 / 1210) * 1210),
				Image.SCALE_DEFAULT)));
		buildHouse.setBounds((int) (0.25 * cardHeight), (int) (1.5 * cardWidth - cardHeight * 0.25),
				(int) (cardHeight * 0.5), (int) (0.5 * cardHeight));
		buildHouse.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("BuildHouse");
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

		JLabel nextPlayer = new JLabel();
		nextPlayer.setIcon(new ImageIcon(new ImageIcon("src/images/NextEnabled.png").getImage().getScaledInstance(
				(int) ((cardHeight * 0.5 / 1210) * 1210), (int) ((cardHeight * 0.5 / 1210) * 1210),
				Image.SCALE_DEFAULT)));
		nextPlayer.setBounds((int) (0.25 * cardHeight), (int) (0.95 * 3 * cardWidth - cardHeight * 0.5),
				(int) (cardHeight * 0.5), (int) (0.5 * cardHeight));
		dice.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Next Player");
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

	}

	private void buildBoard() {
		board = new JPanel();
		board.setBounds((int) (screenWidth * 0.02 + cardHeight), (int) ((screenHeight - 12 * cardWidth) / 2),
				12 * cardWidth, 12 * cardWidth);
		board.setLayout(new BorderLayout(0, 0));
		createEdges();
		createFields();

		frame.add(board);

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
		center.add(new GEKarte(
				"Du wirst zu Strassenausbesserungsar-/nbeiten herangezogen. Zahle für Deine/n Häuser und Hotels/nDM 800 je Haus/nDM 2300 je Hotel/nan die Bank.",
				"Ereigniskarte", (int) ((screenHeight - 2 * cardHeight) / 2 - (3.2 * cardWidth) / 2),
				(int) ((screenHeight - 2 * cardHeight) / 2 - (1.85 * cardWidth) / 2), (int) (3.2 * cardWidth),
				(int) (1.85 * cardWidth)));
		center.repaint();
		board.add(center, BorderLayout.CENTER);
		board.add(borderPanel[0], BorderLayout.SOUTH);
		board.add(borderPanel[1], BorderLayout.WEST);
		board.add(borderPanel[2], BorderLayout.NORTH);
		board.add(borderPanel[3], BorderLayout.EAST);
	}

	private void createFields() {
		borderPanel[0].add(new Sonderfeld("Gefängnis", 0, "Gefängnis", cardHeight));

		for (int i = 0; i < 4; i++) {
			borderPanel[0].add(new Straßenkarte("Turmstrasse", 5, cardWidth, cardHeight, colors.get("braun"), 4000));
		}
		// borderPanel[0].add(new Bahnhof("Südbahnhof", 5, cardWidth, cardHeight,
		// 4000));
		borderPanel[0].add(new Bahnhof("Südbahnhof", 6, cardWidth, cardHeight, 4000));
		for (int i = 0; i < 4; i++) {
			Straßenkarte c = new Straßenkarte("Chausseestrasse", 5, cardWidth, cardHeight, colors.get("blau_hell"), 4000);
			c.addPlayer("Pgelb");
			felder.add(c);
			borderPanel[0]
					.add(c);
		}
		borderPanel[0].add(new Sonderfeld("Los", 0, "Los", cardHeight));

		for (int i = 0; i < 4; i++) {
			borderPanel[1].add(new Straßenkarte("Seestrasse", 15, cardWidth, cardHeight, colors.get("pink"), 4000));
			
		}
		borderPanel[1].add(new InfrastrukturKarte("Elektrizitaets-Werk", 15, cardWidth, cardHeight, 4000));
		//borderPanel[1].add(new Bahnhof("Westbahnhof", 15, cardWidth, cardHeight, 4000));

		for (int i = 0; i < 4; i++) {
			borderPanel[1].add(new Straßenkarte("Seestrasse", 15, cardWidth, cardHeight, colors.get("orange"), 4000));
		}

		borderPanel[2].add(new Sonderfeld("FreiParken", 0, "FreiParken", cardHeight));

		for (int i = 0; i < 4; i++) {
			borderPanel[2].add(new Straßenkarte("Theaterstrasse", 25, cardWidth, cardHeight, colors.get("rot"), 4000));
		}
		borderPanel[2].add(new Bahnhof("Nordbahnhof", 25, cardWidth, cardHeight, 4000));

		for (int i = 0; i < 4; i++) {
			borderPanel[2].add(new Straßenkarte("Lessingstrasse", 25, cardWidth, cardHeight, colors.get("gelb"), 4000));
		}

		borderPanel[2].add(new Sonderfeld("GehInsGefängnis", 0, "GehInsGefängnis", cardHeight));

		for (int i = 0; i < 4; i++) {
			borderPanel[3]
					.add(new Straßenkarte("Bahnhofsstrasse", 35, cardWidth, cardHeight, colors.get("gruen"), 4000));
		}
		borderPanel[3].add(new Bahnhof("Hauptbahnhof", 36, cardWidth, cardHeight, 4000));
		borderPanel[3].add(new GESFeld("Zusatzsteuer", 36, cardWidth, cardHeight));

		for (int i = 0; i < 4; i++) {
			borderPanel[3].add(new Straßenkarte("Schlossallee", 35, cardWidth, cardHeight, colors.get("blau"), 4000));
		}
		
		Field c = felder.get(0);
		c.removePlayer("Pgelb");
		frame.repaint();
	}

	private void createKartenbehaelter() {
		cardBox = new JPanel();
		
		createCards();
		JScrollPane scrollPane = new JScrollPane(cards);
		scrollPane.setBounds((int) (screenWidth * 0.03 + cardHeight + screenHeight),
				(int) ((screenHeight - 12 * cardWidth) / 2), 3 * cardWidth, 12 * cardWidth);
		scrollPane.setBackground(new Color(178, 218, 173));
		scrollPane.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());

		frame.add(scrollPane);
		frame.repaint();
	}

	private void createCards() {
		cards = new JPanel();
		cards.setPreferredSize(new Dimension(3 * cardWidth, (int) (13.75 * cardHeight)));
		cards.setLayout(null);
		cards.setBackground(colors.get("board"));

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 2; j++) {
				JLayeredPane collector = new JLayeredPane();
				collector.setLayout(null);
				
				if (j == 0) {
					collector.setBounds((int) (0.3 * cardWidth), (int) (i * 2.75 * cardHeight + 0.25 * cardHeight),
							cardWidth, (int) (2.2 * cardHeight));
				} else {
					collector.setBounds((int) (1.6 * cardWidth), (int) (i * 2.75 * cardHeight + 0.25 * cardHeight),
							cardWidth, (int) (2.2 * cardHeight));
				}

				for (int k = 0; k < 3; k++) {

					Straßenkarte s = new Straßenkarte("Opernplatz", -1, cardWidth, cardHeight, colors.get("rot"), 4000);
					s.setBounds(0, (int) (k * (0.6 * cardHeight)), cardWidth, cardHeight);

					collector.add(s, Integer.valueOf(k));

				}
				cards.add(collector);
				collector.repaint();

			}
			Straßenkarte s = new Straßenkarte("Opernplatz", 0, cardWidth, cardHeight, colors.get("rot"), 4000);
			s.setBounds(cardWidth, (int) (i * cardHeight + 10), cardWidth, cardHeight);

		}
	}

	private void createStats() {
		JPanel base = new JPanel();
		base.setBackground(colors.get("hintergrund"));
		// base.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
		base.setLayout(new GridLayout(4, 1, 0, 30));
		int width = (int) (0.99 * screenWidth - (screenWidth * 0.04 + cardHeight + screenHeight + 3 * cardWidth));
		int height = (int) ((1.08 * cardWidth) / (1.5 * cardHeight) * width);
		if (width > 1.5 * cardHeight) {
			width = (int) (1.5 * cardHeight);
			height = (int) (1.08 * cardWidth);
		}
		base.setBounds((int) (screenWidth * 0.04 + cardHeight + screenHeight + 3 * cardWidth),
				(int) (screenHeight * 0.5 - (height * 4 + 4 / 3 * height) / 2), width,
				(int) (height * 4 + 4 / 3 * height));
		for (int i = 0; i < 4; i++) {
			JLayeredPane stats = new JLayeredPane();
			stats.setBackground(colors.get("board"));
			stats.setLayout(null);
			stats.setPreferredSize(new Dimension(width, height));
			// stats.setBounds(0, (int)(i*1.08*cardWidth+30), (int)(1.5*cardHeight),
			// (int)(1.08*cardWidth));

			JPanel namePanel = new JPanel();
			namePanel.setBounds((int) (0.1 * width), 0, (int) (0.8 * width), (int) (0.5 * height));
			namePanel.setBackground(colors.get("board"));
			namePanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));

			JLabel name = new JLabel();
			name.setText("<html><body><center>Spieler " + String.valueOf(i + 1) + "</center></body></html>");
			name.setHorizontalAlignment(SwingConstants.CENTER);
			name.setVerticalAlignment(SwingConstants.CENTER);
			name.setFont(new Font("Arial", Font.BOLD, 24));
			name.setForeground(colors.get("Prot"));
			namePanel.add(name);
			stats.add(namePanel, Integer.valueOf(1));

			JPanel moneyPanel = new JPanel();
			moneyPanel.setBounds(0, (int) (0.4 * height), (int) (width), (int) (0.68 * height - 5));
			moneyPanel.setBackground(colors.get("board"));
			moneyPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));

			JLabel money = new JLabel();
			money.setText("<html><body><center>DM 15000.-</center></body></html>");
			money.setHorizontalAlignment(SwingConstants.CENTER);
			money.setVerticalAlignment(SwingConstants.BOTTOM);
			money.setFont(new Font("Arial", Font.BOLD, 32));
			moneyPanel.add(money);
			stats.add(moneyPanel, Integer.valueOf(0));
			base.add(stats);

		}
		frame.add(base);
		frame.repaint();
	}

	private void showFrame() {
		frame.setVisible(true);
	}
}
