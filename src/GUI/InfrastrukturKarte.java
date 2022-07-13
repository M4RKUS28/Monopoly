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

public class InfrastrukturKarte extends Field {
	private String name;
	private int position;
	private int width;
	private int height;
	private int price;
	JPanel playerPos;


	public InfrastrukturKarte(String name, int position, int width, int height, int price) {
		super(name, position);
		this.name = name;
		this.position = position;
		this.width = width;
		this.height = height;
		this.price = price;
		createKarte();
	}

	private void createKarte() {
		JLabelR streetname;
		JLabelR priceTag;
		JLayeredPane body = new JLayeredPane();
 		JPanel textpanel = new JPanel();
 		playerPos = new JPanel();
		if (position < 20) {
			textpanel.setBounds(0, 0, height, width);
 			body.setBounds(0, 0, height, width);
	 		playerPos.setBounds((int)(0.25*height-5), 0, (int)(0.25*height), width);

			this.setPreferredSize(new Dimension(height, width));
			this.setBorder(BorderFactory.createMatteBorder(5, 5, 0, 5, Color.BLACK));
			streetname = new JLabelR(this.transformText(name), 90);
			streetname.setIcon(new ImageIcon(new ImageIcon("src/images/Gluehbirne.png").getImage()
					.getScaledInstance((int) (0.6 * width), (int) (0.6 * width), Image.SCALE_DEFAULT)));
			streetname.setVerticalTextPosition(JLabelR.TOP);
			streetname.setIconTextGap((int) (0.05 * height));
			streetname.setHorizontalTextPosition(JLabelR.CENTER);
			priceTag = new JLabelR("DM " + String.valueOf(price) + ".-", 90);
		} else {
			textpanel.setBounds(0, 0, width, height);
 			body.setBounds(0, 0, width, height);
	 		playerPos.setBounds(0, (int)(0.25*height-5), width, (int)(0.25*height));

			this.setPreferredSize(new Dimension(width, height));
			this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 0, Color.BLACK));
			streetname = new JLabelR(this.transformText(name), 180);
			priceTag = new JLabelR("DM " + String.valueOf(price) + ".-", 180);
			streetname.setIcon(new ImageIcon(new ImageIcon("src/images/Wasserhahn.png").getImage()
					.getScaledInstance((int) (0.6 * width), (int) (0.6 * width), Image.SCALE_DEFAULT)));
			streetname.setVerticalTextPosition(JLabelR.TOP);
			streetname.setIconTextGap((int) (0.05 * height));
			streetname.setHorizontalTextPosition(JLabelR.CENTER);
		}

		//-------------------textpanel-------------------
 		textpanel.setLayout(new BorderLayout());
 		textpanel.setBackground(Constants.colors.get("board"));
 		
		this.setBackground(Constants.colors.get("board"));
		this.setLayout(new BorderLayout());

		streetname.setForeground(Color.black);
		streetname.setFont(new Font("Arial", Font.BOLD, Integer.valueOf(Constants.fonts.get("straßenname"))));

		priceTag.setForeground(Color.black);
		priceTag.setFont(new Font("Arial", Font.BOLD, Integer.valueOf(Constants.fonts.get("straßenname"))));

		if (position < 20) {
			streetname.setVerticalAlignment(SwingConstants.CENTER);
			textpanel.add(streetname, BorderLayout.EAST);
			priceTag.setHorizontalAlignment(SwingConstants.RIGHT);
			textpanel.add(priceTag, BorderLayout.WEST);
		} else {
			streetname.setHorizontalAlignment(SwingConstants.CENTER);
			textpanel.add(streetname, BorderLayout.SOUTH);
			priceTag.setHorizontalAlignment(SwingConstants.CENTER);
			textpanel.add(priceTag, BorderLayout.NORTH);
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
		this.playerPos.add(fig);
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
