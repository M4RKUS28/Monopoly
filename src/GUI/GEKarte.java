package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class GEKarte extends Field implements MouseListener{
	private String text;
	private String typ;
	private int width;
	private int height;
	private int x;
	private int y;
	private Game game;
	public GEKarte(String text, String typ, int x, int y, int width, int height, Game game) {
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
	
	private void createKarte() {
		JPanel textPanel = new JPanel();
		textPanel.setBounds(0, 0, width, height);
		textPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 0, 5, Color.black));
		textPanel.setLayout(new BorderLayout(0, 5));
		
		this.setBounds(x, y, width, (int)(1.25*height));
		if (typ.contains("mein")) {
			this.setBackground(Constants.colors.get("rosa"));
			textPanel.setBackground(Constants.colors.get("rosa"));

		} else {
			this.setBackground(Constants.colors.get("Erblau"));
			textPanel.setBackground(Constants.colors.get("Erblau"));


		}
		this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
		this.setLayout(null);
		
		
		
		JLabel headline = new JLabel();
		headline.setPreferredSize(new Dimension(width, (int) (0.2*height)));
		headline.setFont(new Font("Arial", Font.BOLD, 16));
		headline.setVerticalAlignment(SwingConstants.BOTTOM);
		headline.setHorizontalAlignment(SwingConstants.CENTER);
		headline.setBackground(this.colors.get("board"));
		headline.setText(typ.toUpperCase());
		textPanel.add(headline, BorderLayout.NORTH);
		
		JLabel message = new JLabel();
		message.setPreferredSize(new Dimension(width, (int) (0.8*height)));
		message.setFont(new Font("Arial", Font.BOLD, 14));
		message.setVerticalAlignment(SwingConstants.CENTER);
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setBackground(this.colors.get("board"));
		message.setText(this.transformText(text));
		textPanel.add(message, BorderLayout.CENTER);
		this.add(textPanel);
		this.add(createButtons());
		this.setName("GEKarte");
		

	}
	
	private JPanel createButtons() {
		JPanel buttonBoard = new JPanel();
		if (typ.contains("mein")) {
			buttonBoard.setBackground(Constants.colors.get("rosa"));

		} else {
			buttonBoard.setBackground(Constants.colors.get("Erblau"));


		}
		buttonBoard.setBounds(5, (int)(height), width-5, (int)(height*0.25)-5);
		buttonBoard.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, Color.black));

		buttonBoard.setLayout(new FlowLayout(FlowLayout.CENTER, (int) (0.03*width), 0));
		
		
		buttonBoard.add(createButton("OK"));
		
		
		return buttonBoard;
	}
	
	private JLabel createButton(String text) {
		JLabel ok = new JLabel();
		if (typ.contains("mein")) {
			ok.setBackground(Constants.colors.get("rosa"));

		} else {
			ok.setBackground(Constants.colors.get("Erblau"));


		}
		ok.setBackground(Constants.colors.get("board"));
		ok.setFont(new Font("Arial", Font.BOLD, 14));
		ok.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
		ok.setPreferredSize(new Dimension((int)(width/3-0.05*width), (int)(height*0.2)));
		ok.setVerticalAlignment(SwingConstants.CENTER);
		ok.setHorizontalAlignment(SwingConstants.CENTER);
		ok.setText("<html><head></head><body><center>" + "OK" + "</center></body></html>");
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
			if (typ.contains("mein")) {
				game.resumeGCard();
			} else {
				game.resumeECard();
			}
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
