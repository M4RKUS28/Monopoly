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

public class Bahnhof extends Field{

	private Figure[] player;
	private String name;
	private int position;
	private int width;
	private int height;
	private int price;
	JPanel playerPos;

	public Bahnhof(String name, int position, int width, int height, int price) {
		super(name, position);
		// TODO Auto-generated constructor stub
		player = new Figure[4];
		this.name = name;
		this.position = position;
		this.width = width;
		this.height = height;
		this.price = price;
		this.name = this.transformText(name);
		karteErstellen();
	}
	
	private void karteErstellen() {
 		JLabelR streetname;
 		JLabelR priceTag;
 		JLayeredPane body = new JLayeredPane();
 		JPanel textpanel = new JPanel();
 		playerPos = new JPanel();

 		//-------------------Grundstruktur-------------------
 		if (position < 10 || (position > 20 && position < 29)) {
 	 		// Für untere und obere Reihe
 			this.setPreferredSize(new Dimension(width, height));
 			textpanel.setBounds(0, 0, width, height);
 			body.setBounds(0, 0, width, height);


	 	 	this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 0, Color.BLACK));
	 	 	
 			if (position < 10) {
 	 			streetname = new JLabelR(name, 0);
 	 			playerPos.setBounds(0, (int)(0.5*height+15), width, (int)(0.25*height));
 	 			priceTag = new JLabelR("DM " + String.valueOf(price) + ".-", 0);
 			} else {
 	 			streetname = new JLabelR(name, 180);
 	 			playerPos.setBounds(0, (int)(0.25*height-5), width, (int)(0.25*height));
 	 			priceTag = new JLabelR("DM " + String.valueOf(price) + ".-", 180);

 			}
 		} else {
 			// Für linke und rechte Reihe
 			this.setPreferredSize(new Dimension(height, width));
 			textpanel.setBounds(0, 0, height, width);
 			body.setBounds(0, 0, height, width);


	 	 	this.setBorder(BorderFactory.createMatteBorder(5, 5, 0, 5, Color.BLACK));

 			if (position < 20) { // linke Seite
 	 			streetname = new JLabelR(name, 90);
 	 			playerPos.setBounds((int)(0.25*height-5), 0, (int)(0.25*height), width);

 	 			priceTag = new JLabelR("DM " + String.valueOf(price) + ".-", 90);


 			} else { // rechte Seite
 	 			streetname = new JLabelR(name, 270);
 	 			playerPos.setBounds((int)(0.5*height+15), 0, (int)(0.25*height), width);

 	 			priceTag = new JLabelR("DM " + String.valueOf(price) + ".-", 270);

 			}
 		}
 		
 		textpanel.setBackground(new Color(178,218,173));
 		this.setLayout(new BorderLayout());
 
 		
 		//-------------------textpanel-------------------
 		textpanel.setLayout(new BorderLayout());
 		//-------------------picpanel-------------------
 	//	picpanel.setLayout(new BorderLayout());
 	//	picpanel.setBackground(Color.green);


 		//-------------------Straßenname-------------------
 		streetname.setForeground(Color.black);
 		streetname.setFont(new Font("Arial", Font.BOLD, 16));
 		streetname.setIcon(new ImageIcon(new ImageIcon("src/images/Zug0.png").getImage().getScaledInstance((int) (width*0.8), (int) (height*0.5*0.8), Image.SCALE_DEFAULT)));
			streetname.setVerticalTextPosition(JLabelR.TOP);
			streetname.setIconTextGap((int)(0.05*height));
			streetname.setHorizontalTextPosition(JLabelR.CENTER);
 		if (position < 10) {
 	 		streetname.setHorizontalAlignment(SwingConstants.CENTER);
 	 		textpanel.add(streetname, BorderLayout.NORTH);
 		} else if (position < 20) {
 	 		streetname.setVerticalAlignment(SwingConstants.CENTER);
 	 		priceTag.setHorizontalAlignment(SwingConstants.RIGHT);
 	 		textpanel.add(streetname, BorderLayout.EAST);
 		} else if (position < 30) {
 	 		streetname.setHorizontalAlignment(SwingConstants.CENTER);
 	 		textpanel.add(streetname, BorderLayout.SOUTH);
 		} else {
 	 		streetname.setVerticalAlignment(SwingConstants.CENTER);
 	 		priceTag.setHorizontalAlignment(SwingConstants.LEFT);
 	 		textpanel.add(streetname, BorderLayout.WEST);
 	 		

 		}
 		
 		//-------------------Preis-------------------
 		priceTag.setForeground(Color.black);
 		priceTag.setFont(new Font("Arial", Font.BOLD, 16));
 		if (position < 10) {
 	 		priceTag.setHorizontalAlignment(SwingConstants.CENTER);
 	 		textpanel.add(priceTag, BorderLayout.SOUTH);
 		} else if (position < 20) {
 	 		priceTag.setVerticalAlignment(SwingConstants.CENTER);
 	 		
 	 		textpanel.add(priceTag, BorderLayout.WEST);
 		} else if (position < 30) {
 	 		priceTag.setHorizontalAlignment(SwingConstants.CENTER);
 	 		textpanel.add(priceTag, BorderLayout.NORTH);
 		} else {
 	 		priceTag.setVerticalAlignment(SwingConstants.CENTER);
 	 		textpanel.add(priceTag, BorderLayout.EAST);
 		}
 		
 		//-------------------Bild-------------------
 		JLabel zug = new JLabel();
 		zug.setHorizontalAlignment(SwingConstants.CENTER);
 		if (position < 10) {
 			zug.setIcon(new ImageIcon(new ImageIcon("src/images/Zug0.png").getImage().getScaledInstance((int) (width*0.8), (int) (height*0.5*0.8), Image.SCALE_DEFAULT)));

 		} else if (position < 20) {
 			zug.setIcon(new ImageIcon(new ImageIcon("src/images/Zug90.png").getImage().getScaledInstance((int) (height*0.5*0.8), (int) (width*0.8), Image.SCALE_DEFAULT)));

 		} else if (position < 30) {
 			zug.setIcon(new ImageIcon(new ImageIcon("src/images/Zug180.png").getImage().getScaledInstance((int) (width*0.8), (int) (height*0.5*0.8), Image.SCALE_DEFAULT)));

 		} else {
 			zug.setIcon(new ImageIcon(new ImageIcon("src/images/Zug270.png").getImage().getScaledInstance((int) (height*0.5*0.8), (int) (width*0.8), Image.SCALE_DEFAULT)));

 		}
 		zug.setBounds(0, (int) (height*0.25), zug.getIcon().getIconWidth(), zug.getIcon().getIconHeight());
 		zug.setHorizontalAlignment(SwingConstants.CENTER);
 		
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
