package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class GEKarte extends Field{
	private String text;
	private String typ;
	private int width;
	private int height;
	private int x;
	private int y;
	public GEKarte(String text, String typ, int x, int y, int width, int height) {
		super(text, -2);
		this.text = text;
		this.typ = typ;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		System.out.println(x + " " + y);
		this.createKarte();
	}
	
	private void createKarte() {
		this.setBounds(x, y, width, height);
		this.setBackground(colors.get("board"));
		this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
		this.setLayout(new BorderLayout(0, 5));
		
		JLabel headline = new JLabel();
		headline.setPreferredSize(new Dimension(width, (int) (0.2*height)));
		headline.setFont(new Font("Arial", Font.BOLD, 16));
		headline.setVerticalAlignment(SwingConstants.BOTTOM);
		headline.setHorizontalAlignment(SwingConstants.CENTER);
		headline.setBackground(this.colors.get("board"));
		headline.setText(typ.toUpperCase());
		this.add(headline, BorderLayout.NORTH);
		
		JLabel message = new JLabel();
		message.setPreferredSize(new Dimension(width, (int) (0.8*height)));
		message.setFont(new Font("Arial", Font.BOLD, 14));
		message.setVerticalAlignment(SwingConstants.CENTER);
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setBackground(this.colors.get("board"));
		message.setText(this.transformText(text));
		this.add(message, BorderLayout.CENTER);
	}

	@Override
	public void addPlayer(String color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePlayer(String color) {
		// TODO Auto-generated method stub
		
	}
}
