package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Straßenkarte extends Field implements MouseListener{
	private Figure[] player;
	private String name;
	private int position;
	private int width;
	private int height;
	private Color stripeColor;
	private int price;
	private boolean placeHouses;
	private int numHouse;
	private JLabel[] houses;
	private JPanel colorstripe;
	private JPanel playerPos;
 	public Straßenkarte(String name, int position, int width, int height, Color stripeColor, int price) {
		super(name, position);
		// TODO Auto-generated constructor stub
		this.name = name;
		this.position = position;
		this.width = width;
		this.height = height;
		this.stripeColor = stripeColor;
		this.price = price;
		this.placeHouses = false;
		this.numHouse = 0;
		this.houses = new JLabel[4];
		this.setName(String.valueOf(position));
		this.name = this.transformText(name);
		player = new Figure[4];
		karteErstellen();
		
	}
 	
 	private void karteErstellen() {
 		colorstripe = new JPanel();
 		playerPos = new JPanel();
 		JLayeredPane body = new JLayeredPane();
 		JPanel textpanel = new JPanel();
 		JLabelR streetname;
 		JLabelR priceTag;
 		
 		//-------------------Grundstruktur-------------------
 		if (position < 10 || (position > 20 && position < 29)) {
 	 		// Für untere und obere Reihe
 			this.setPreferredSize(new Dimension(width, height));
		 	this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 0, Color.BLACK));

	 	 	colorstripe.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 0, Color.BLACK));
	 	 	playerPos.setPreferredSize(new Dimension(width, (int)(height*0.25)));
 			textpanel.setLayout(new BorderLayout(0, 0));
 			
 			if (position < 10) {
 	 			colorstripe.setBounds(0, 0, width, (int) (height*0.25));
 	 			body.setBounds(5, (int) (height*0.25)+5, width, (int) (height*0.75)-10);
 	 			textpanel.setBounds(0, 0, width-10, (int) (height*0.75)-10);
 	 	 		playerPos.setBounds(0, (int)(height*0.25+10), (int) (width), (int)(height*0.25));
 	 			streetname = new JLabelR(name, 0);
 	 			priceTag = new JLabelR("DM " + String.valueOf(price) + ".-", 0);
 			} else {
 	 			colorstripe.setBounds(0, (int) (height*0.75), width, (int) (height*0.25));
 	 			body.setBounds(5, 5, width, (int) (height*0.75)-10);
 	 			textpanel.setBounds(0, 0, width-10, (int) (height*0.75)-10);
 	 	 		playerPos.setBounds(0, (int)(height*0.25-10), (int) (width), (int)(height*0.25));

 	 			streetname = new JLabelR(name, 180);
 	 			priceTag = new JLabelR("DM " + String.valueOf(price) + ".-", 180);

 			}
 		} else {
 			// Für linke und rechte Reihe
 			this.setPreferredSize(new Dimension(height, width));
		 	this.setBorder(BorderFactory.createMatteBorder(5, 5, 0, 5, Color.BLACK));
	 	 	colorstripe.setBorder(BorderFactory.createMatteBorder(5, 5, 0, 5, Color.BLACK));


 			textpanel.setLayout(new BorderLayout(50,0));

 			if (position < 20) { // linke Seite
 				colorstripe.setBounds((int) (height*0.75), 0, (int) (height*0.25), width);
 				body.setBounds(5, 5, (int) (height*0.75)-10, width);
 				textpanel.setBounds(0, 0, (int) (height*0.75)-10, width);
 	 	 		playerPos.setBounds((int)(height*0.25-10), 0, (int)(height*0.25), width);

 	 			streetname = new JLabelR(name, 90);
 	 			priceTag = new JLabelR("DM " + String.valueOf(price) + ".-", 90);


 			} else { // rechte Seite
 				colorstripe.setBounds(0, 0, (int) (height*0.25), width);
 	 			body.setBounds((int) (height*0.25)+5, 5, (int) (height*0.75)-10, width);
 	 			textpanel.setBounds(0, 0, (int) (height*0.75)-10, width-10);
 	 			playerPos.setBounds((int)(height*0.25+10), 0, (int)(height*0.25), width);
 	 			streetname = new JLabelR(name, 270);
 	 			priceTag = new JLabelR("DM " + String.valueOf(price) + ".-", 270);

 			}
 		}
 		
 		if (position == -1) {
 	 		this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
 	 		colorstripe.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));

 		}
 		
 		this.setBackground(new Color(178,218,173));
 		//this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
 		this.setLayout(null);
 		this.addMouseListener(this);
 		
 		//-------------------Farbstreifen-------------------
 		colorstripe.setBackground(stripeColor);
 		colorstripe.addMouseListener(this);
 		colorstripe.setLayout(null);
 		this.add(colorstripe);
 		
 		//-------------------Textpanel-------------------
 		textpanel.setBackground(new Color(178,218,173));
 		textpanel.addMouseListener(this);
 		//textpanel.setLayout(new BorderLayout());
 		
 		//this.add(textpanel);

 		//-------------------Straßenname-------------------
 		streetname.setForeground(Color.black);
 		streetname.setFont(new Font("Arial", Font.BOLD, 16));
 		streetname.addMouseListener(this);
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
 		priceTag.addMouseListener(this);
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
 		

 	}
	
 	private void placeHouse() {
 		JLabel house = new JLabel();
 		if (position < 10 || (position > 20 && position < 29)) {
 	 		// Für untere und obere Reihe
 			house.setIcon(new ImageIcon(new ImageIcon("images/HausVert.png").getImage().getScaledInstance((int) ((0.25*width/568)*568), (int) ((0.25*height/834)*834), Image.SCALE_DEFAULT)));

 			if (numHouse == 0) {
 				house.setBounds((int)(0.375*width+5), 5, (int)(0.25*width), (int)(0.25*height));
 				this.houses[numHouse] = house;
 				colorstripe.repaint();

 		 		this.numHouse++;
 			} else if (numHouse == 1) {
 				houses[0].setBounds((int)(0.125*width+5), 5, (int)(0.25*width), (int)(0.25*height));
 				house.setBounds((int)(0.625*width+5), 5, (int)(0.25*width), (int)(0.25*height));
 				this.houses[numHouse] = house;
 				colorstripe.repaint();

 		 		this.numHouse++;
 			} else if (numHouse == 2) {
 				houses[0].setBounds(5, 5, (int)(0.25*width), (int)(0.25*height));
 				houses[1].setBounds((int)(0.375*width+5), 5, (int)(0.25*width), (int)(0.25*height));
 				house.setBounds((int)(0.75*width+5), 5, (int)(0.25*width), (int)(0.25*height));
 				colorstripe.repaint();

 				this.houses[numHouse] = house;
 		 		this.numHouse++;
 			} else if (numHouse == 3) {
 				houses[0].setBounds(5, 5, (int)(0.25*width), (int)(0.25*height));
 				houses[1].setBounds((int)(0.25*width+5), 5, (int)(0.25*width), (int)(0.25*height));
 				houses[2].setBounds((int)(0.5*width+5), 5, (int)(0.25*width), (int)(0.25*height));
 				house.setBounds((int)(0.75*width+5), 5, (int)(0.25*width), (int)(0.25*height));
 				colorstripe.repaint();
 				this.houses[numHouse] = house;
 		 		this.numHouse++;
 			}  else if (numHouse == 4) {
 				colorstripe.removeAll();
 				house.setIcon(new ImageIcon(new ImageIcon("images/HotelHor.png").getImage().getScaledInstance((int) ((0.5*width/1086)*1086), (int) (0.25*height), Image.SCALE_DEFAULT)));
 				house.setBounds((int)(0.25*width+5), 5, (int)(0.5*width), (int)(0.25*height));
 				this.houses = null;
 		 		this.numHouse++;
 			}


 		} else {
 			house.setIcon(new ImageIcon(new ImageIcon("images/HausHor.png").getImage().getScaledInstance((int) (0.25*height), (int) (0.25*width), Image.SCALE_DEFAULT)));

 			if (numHouse == 0) {
 				house.setBounds(5, (int)(0.375*width+5), (int)(0.25*width), (int)(0.25*height));
 				this.houses[numHouse] = house;
 				colorstripe.repaint();

 		 		this.numHouse++;
 			} else if (numHouse == 1) {
 				houses[0].setBounds(5, (int)(0.125*width+5), (int)(0.25*width), (int)(0.25*height));
 				house.setBounds(5, (int)(0.625*width+5), (int)(0.25*width), (int)(0.25*height));
 				this.houses[numHouse] = house;
 				colorstripe.repaint();

 		 		this.numHouse++;
 			} else if (numHouse == 2) {
 				houses[0].setBounds(5, 0, (int)(0.25*width), (int)(0.25*height));
 				houses[1].setBounds(5, (int)(0.375*width), (int)(0.25*width), (int)(0.25*height));
 				house.setBounds(5, (int)(0.75*width), (int)(0.25*width), (int)(0.25*height));
 				colorstripe.repaint();

 				this.houses[numHouse] = house;
 		 		this.numHouse++;
 			} else if (numHouse == 3) {
 				houses[0].setBounds(5, 0, (int)(0.25*width), (int)(0.25*height));
 				houses[1].setBounds(5, (int)(0.25*width), (int)(0.25*width), (int)(0.25*height));
 				houses[2].setBounds(5, (int)(0.5*width), (int)(0.25*width), (int)(0.25*height));
 				house.setBounds(5, (int)(0.75*width), (int)(0.25*width), (int)(0.25*height));
 				colorstripe.repaint();
 				this.houses[numHouse] = house;
 		 		this.numHouse++;
 			}  else if (numHouse == 4) {
 				colorstripe.removeAll();
 				house.setIcon(new ImageIcon(new ImageIcon("images/HotelVert.png").getImage().getScaledInstance((int) ((0.5*width/1086)*1086), (int) (0.25*height), Image.SCALE_DEFAULT)));
 				house.setBounds(5, (int)(0.25*width+5), (int)(0.5*width), (int)(0.25*height));
 				this.houses = null;
 		 		this.numHouse++;
 			}
 		}

 		this.colorstripe.add(house);
 		
 		this.revalidate();
 		this.repaint();
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
		this.playerPos.add(fig);
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (numHouse>5) {
			return;
		}
		placeHouses = true;
		if (placeHouses) {
			this.placeHouse();
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
