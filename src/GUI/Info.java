package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Gamelogic.Game;

public class Info extends Field implements MouseListener{
	private String text;
	private String[] typ;
	private int width;
	private int height;
	private int x;
	private int y;
	private Game game;
	private Spielfeld spielfeld;
	
	public Info(String text, String[] typ, int x, int y, int width, int height, Game game) {
		super(text, -2);
		this.text = text;
		this.typ = typ;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.game = game;
		this.createKarte();
	}
	
	public Info(String text, String[] typ, int x, int y, int width, int height, Game game, Spielfeld spielfeld) {
		super(text, -2);
		this.text = text;
		this.typ = typ;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.game = game;
		this.spielfeld = spielfeld;
		this.createKarte();
	}
	
	private void createKarte() {
		this.setBounds(x, y, width, height);
		this.setBackground(Constants.colors.get("board"));
		this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
		this.setLayout(null);
		
		JPanel messageBoard = new JPanel();
		messageBoard.setBackground(Constants.colors.get("board"));
		messageBoard.setBounds(0, 5, width, (int)(height*0.75)-5);
		messageBoard.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, Color.black));

		messageBoard.setLayout(new BorderLayout());
		JLabel headline = new JLabel();
		headline.setBounds(0, 0, width, (int) (0.3*0.8*height));
		headline.setFont(new Font("Arial", Font.BOLD, 14));
		headline.setVerticalAlignment(SwingConstants.CENTER);
		headline.setHorizontalAlignment(SwingConstants.CENTER);
		headline.setBackground(this.colors.get("board"));
		headline.setText(this.transformText("INFO"));

		messageBoard.add(headline, BorderLayout.NORTH);
		
		
		JLabel message = new JLabel();
		message.setBounds(0, (int) (0.3*0.8*height), width, (int) (0.7*0.8*height));
		message.setFont(new Font("Arial", Font.BOLD, 14));
		message.setVerticalAlignment(SwingConstants.CENTER);
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setBackground(this.colors.get("board"));
		message.setText(this.transformText(text));

		messageBoard.add(message, BorderLayout.SOUTH);

		this.add(messageBoard);
		Component[] comp = messageBoard.getComponents();
		for (Component c:comp) {
			System.out.println(c.getBounds());
		}
		this.add(createButtons());
		
		
		
	}
	
	private JPanel createButtons() {
		JPanel buttonBoard = new JPanel();
		buttonBoard.setBackground(Constants.colors.get("board"));

		buttonBoard.setBounds(5, (int)(height*0.75), width-5, (int)(height*0.25)-5);
		buttonBoard.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, Color.black));

		buttonBoard.setLayout(new FlowLayout(FlowLayout.CENTER, (int) (0.03*width), 0));
		
		for (String t : typ) {
			buttonBoard.add(createButton(t));
		}
		
		return buttonBoard;
	}
	
	private JLabel createButton(String text) {
		JLabel ok = new JLabel();
		ok.setBackground(Constants.colors.get("board"));
		ok.setFont(new Font("Arial", Font.BOLD, 14));
		ok.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
		ok.setPreferredSize(new Dimension((int)(width/3-0.05*width), (int)(height*0.2)));
		ok.setVerticalAlignment(SwingConstants.CENTER);
		ok.setHorizontalAlignment(SwingConstants.CENTER);
		ok.setText("<html><head></head><body><center>" + text + "</center></body></html>");
		ok.addMouseListener(this);
		ok.setName(text);
		return ok;
	}

	@Override
	public void addPlayer(String color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePlayer(String color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getComponent().getName().equals("OK")) {
			this.setVisible(false);

		} else if (e.getComponent().getName().equals("Würfeln")) {
			game.werkMiete();
			this.setVisible(false);
		} else if (e.getComponent().getName().equals("Ja")) {
			game.kaufen();
			this.setVisible(false);
		} else if (e.getComponent().getName().equals("Nein")) {
			this.setVisible(false);
		} else if (e.getComponent().getName().equals("DM 1000")) {
			game.freikaufen();
			this.setVisible(false);
		}  else if (e.getComponent().getName().equals("Pasch")) {
			game.wuerfeln();
			this.setVisible(false);
		} else if (e.getComponent().getName().equals("Beenden")) {
			game.bankrott();
			this.setVisible(false);
		}  else if (e.getComponent().getName().equals("Hypothek")) {
			spielfeld.hypothekAufnehmen();
			this.setVisible(false);
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
}
