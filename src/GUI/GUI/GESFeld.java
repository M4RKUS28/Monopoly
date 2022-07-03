package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GESFeld extends Field {
	private String name;
	private int position;
	private int width;
	private int height;
	private int price;
	private JPanel playerPos;

	public GESFeld(String name, int position, int width, int height) {
		super(name, position);
		this.name = name;
		this.position = position;
		this.width = width;
		this.height = height;
		this.name = this.transformText(name);
		erstelleKarte();
	}
	
	public GESFeld(String name, int position, int width, int height, int price) {
		super(name, position);
		this.name = name;
		this.position = position;
		this.width = width;
		this.height = height;
		this.name = this.transformText(name);
		this.price = price;
		erstelleKarte();
	}


	private void erstelleKarte() {
		JLabelR text;
		playerPos = new JPanel();
 		JLayeredPane body = new JLayeredPane();
 		JPanel textpanel = new JPanel();

		// -------------------Grundstruktur-------------------
		if (position < 10 || (position > 20 && position < 29)) {
			// Für untere und obere Reihe
			this.setPreferredSize(new Dimension(width, height));
 			textpanel.setBounds(0, 0, width, height);
			body.setBounds(0, 0, width, height);
			this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 0, Color.BLACK));
			body.setBounds(0, 0, width, height);
			if (position < 10) {
				text = new JLabelR(name, 0);
 	 			playerPos.setBounds(0, (int)(0.5*height+15), width, (int)(0.25*height));


			} else {
				text = new JLabelR(name, 180);
 	 			playerPos.setBounds(0, (int)(0.25*height-5), width, (int)(0.25*height));

			}
		} else {
			// Für linke und rechte Reihe
			this.setPreferredSize(new Dimension(height, width));
 			textpanel.setBounds(0, 0, height, width);
 			body.setBounds(0, 0, height, width);

			this.setBorder(BorderFactory.createMatteBorder(5, 5, 0, 5, Color.BLACK));

			if (position < 20) { // linke Seite
				text = new JLabelR(name, 90);
 	 			playerPos.setBounds((int)(0.25*height-5), 0, (int)(0.25*height), width);


			} else { // rechte Seite
				text = new JLabelR(name, 270);
 	 			playerPos.setBounds((int)(0.5*height+15), 0, (int)(0.25*height), width);

			}
		}
		// -------------------Hintergrund-------------------
		this.setBackground(new Color(178, 218, 173));
		this.setLayout(new BorderLayout());

		//-------------------textpanel-------------------
 		textpanel.setLayout(new BorderLayout());
 		textpanel.setBackground(Constants.colors.get("board"));
		
		// -------------------Text-------------------
		text.setForeground(Color.black);
		text.setFont(new Font("Arial", Font.BOLD, 16));
		if (name.contains("Gemein")) {
			text.setIcon(new ImageIcon(new ImageIcon("src/images/Schatztruhe.png").getImage()
					.getScaledInstance((int) (0.75 * width), (int) (0.75 * width), Image.SCALE_DEFAULT)));
		} else if (name.contains("eignis")) {
			text.setIcon(new ImageIcon(new ImageIcon("src/images/fragezeichen.png").getImage()
					.getScaledInstance((int) (0.75 * width), (int) (0.75 * width), Image.SCALE_DEFAULT)));
		} else if (name.contains("usatz")) {
			text.setIcon(new ImageIcon(new ImageIcon("src/images/Ring.png").getImage()
					.getScaledInstance((int) (0.6 * width), (int) (0.6 * width), Image.SCALE_DEFAULT)));
		} else {
			
		}
		text.setVerticalTextPosition(JLabelR.TOP);
		text.setIconTextGap((int) (0.05 * height));
		text.setHorizontalTextPosition(JLabelR.CENTER);
		if (position < 10) {
			text.setHorizontalAlignment(SwingConstants.CENTER);
			textpanel.add(text, BorderLayout.NORTH);
		} else if (position < 20) {
			text.setVerticalAlignment(SwingConstants.CENTER);

			textpanel.add(text, BorderLayout.EAST);
		} else if (position < 30) {
			text.setHorizontalAlignment(SwingConstants.CENTER);

			textpanel.add(text, BorderLayout.SOUTH);
		} else {
			text.setVerticalAlignment(SwingConstants.CENTER);

			textpanel.add(text, BorderLayout.WEST);
		}
		if (name.contains("steuer")) {
			JLabelR priceTag;
			if (position < 10) {
				priceTag = new JLabelR("DM " + String.valueOf(price) + ".-", 0);
				priceTag.setFont(new Font("Arial", Font.BOLD, 16));
		 		textpanel.add(priceTag, BorderLayout.SOUTH);
			} else {
				priceTag = new JLabelR("DM " + String.valueOf(price)+ ".-", 270);
				priceTag.setFont(new Font("Arial", Font.BOLD, 16));
		 		textpanel.add(priceTag, BorderLayout.EAST);
			}
			
	 		
		}
		
		//-------------------Body-------------------
 		body.setBackground(Constants.colors.get("board"));
 		body.setLayout(null);
 		body.add(textpanel, Integer.valueOf(0));
 		
 		//-------------------playerPos-------------------
 		playerPos.setOpaque(false);
 		playerPos.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
 		body.add(playerPos, Integer.valueOf(1));
 		this.add(body);

 		this.repaint();
 		
 		this.add(textpanel);
	}

	
	public void addPlayer(String color) {
		Figure fig = new Figure(color);
		fig.setPreferredSize(new Dimension(Constants.getFigureSize(), Constants.getFigureSize()));
		fig.setName(color);
		Component[] components = playerPos.getComponents();
		for (Component component : components) {
			if (color== component.getName()) {
				return;
			}
		}
		this.playerPos.add(new Figure(color));
		System.out.println(playerPos.getComponents());
		this.revalidate();
		this.repaint();
	}
	
	public void removePlayer(String color) {

		Component[] components = playerPos.getComponents();
		for (Component component : components) {
			if (color== component.getName()) {
				playerPos.remove(component);
			}
		}
		this.revalidate();
		this.repaint();
	}
}
