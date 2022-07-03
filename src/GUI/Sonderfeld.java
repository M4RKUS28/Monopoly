package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Sonderfeld extends Field{
	private JLabel label;
	private int size;
	JPanel playerPos;
	public Sonderfeld(String name, int position, String typ, int size) {
		super(name, position);
		this.size = size;
		createBasic();
		
		switch(typ) {
		case "Los":
			createLos();
			break;
		case "Gefängnis":
			createGefaengnis();
			break;
		case "FreiParken":
			createFreiParken();
			break;
		case "GehInsGefängnis":
			createGehInsGefaengnis();
			break;
		}
		
	}
	
	private void createBasic() {
		this.setPreferredSize(new Dimension(size, size));
		this.setBackground(new Color(178,218,173));

	}
	
	private void createLos() {
		this.setLayout(new BorderLayout());
 	 	this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));

		
 		//-------------------LOS-Schrift-------------------
		JLabelR los = new JLabelR("LOS", 315);
		los.setPreferredSize(new Dimension(size, (int)(size*0.75)));
		los.setForeground(Color.black);
		los.setFont(new Font("Arial", Font.BOLD, 38));
		los.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(los, BorderLayout.CENTER);
		
 		//-------------------Pfeil-------------------
		JLayeredPane pane = new JLayeredPane();
		pane.setPreferredSize(new Dimension((int) (size), (int) ((size*0.22)*0.8)));
		pane.setBackground(Constants.colors.get("blau"));
		pane.setLayout(null);

		playerPos = new JPanel();
		playerPos.setBounds((int) (size*0.1), 0,(int) (size*0.8), (int) ((size*0.22)*0.8));
		playerPos.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
		//playerPos.setBackground(Color.blue);
		playerPos.setOpaque(false);
		JLabel arrow = new JLabel();
		arrow.setIcon(new ImageIcon(new ImageIcon("src/images/Pfeil.png").getImage().getScaledInstance((int) (size*0.8), (int) ((size*0.22)*0.8), Image.SCALE_DEFAULT)));
		arrow.setBounds((int) (size*0.1), 0,(int) (size*0.8), (int) ((size*0.22)*0.8));
		pane.add(arrow, Integer.valueOf(2));
		pane.add(playerPos, Integer.valueOf(1));
		this.add(pane, BorderLayout.SOUTH);
	}
	
	private void createGefaengnis() {
		this.setLayout(new BorderLayout());
 	 	this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 0, Color.BLACK));

		
 		//-------------------unteres Panel-------------------
		JPanel panelSouth = new JPanel();
		panelSouth.setPreferredSize(new Dimension(size, (int)(size*0.25)));
		panelSouth.setBackground(new Color(178,218,173));
		this.add(panelSouth, BorderLayout.SOUTH);
		
 		//-------------------Schriftzug "Nur zu Besuch-------------------
		JLabel nzb = new JLabel();
		nzb.setForeground(Color.black);
		nzb.setHorizontalAlignment(SwingConstants.CENTER);
		nzb.setText("Nur zu Besuch");
		nzb.setFont(new Font("Arial", Font.BOLD, 16));
		panelSouth.add(nzb);
		
 		//-------------------linkes Panel-------------------
		playerPos = new JPanel();
		playerPos.setPreferredSize(new Dimension((int)(size*0.25), (int)(size*0.75)));
		playerPos.setBackground(new Color(178,218,173));
		playerPos.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
		this.add(playerPos, BorderLayout.WEST);
		
 		//-------------------Gefängnis-------------------
		
        GridBagConstraints gbc = new GridBagConstraints();

		JPanel prison = new JPanel();
		prison.setPreferredSize(new Dimension((int)(size*0.75), (int)(size*0.75)));
		prison.setBackground(new Color(192, 58, 25));
 		prison.setBorder(BorderFactory.createMatteBorder(0, 5, 5, 0, Color.BLACK));
 		prison.setLayout(new BorderLayout());
		this.add(prison, BorderLayout.CENTER);
		
		
		/*gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;*/
		JLabelR im = new JLabelR("Im", 45);
		im.setForeground(Color.black);
		im.setFont(new Font("Arial", Font.BOLD, 16));
		
	//	prison.add(im, 1, 2);
		/*gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weighty = 1;
		gbc.weightx = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(-45, 10, 0, -15);
*/
		JLabel picture = new JLabel();
		picture.setIcon(new ImageIcon(new ImageIcon("images/Gefaengnis.png").getImage().getScaledInstance((int) (size*0.7), (int) ((size*0.98)*0.7), Image.SCALE_DEFAULT)));
		picture.setHorizontalAlignment(SwingConstants.CENTER);
		picture.setVerticalAlignment(SwingConstants.CENTER);
		prison.add(picture, BorderLayout.CENTER);
		/*
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		gbc.insets = new Insets(-60, 0, 15, -15);
*/
		JLabelR gefaengnis = new JLabelR("src/Gefängnis", 45);
		im.setForeground(Color.black);
		im.setFont(new Font("Arial", Font.BOLD, 16));
	//
		im.setPreferredSize(new Dimension(size, size));
		
		//prison.add(gefaengnis, 2, 0);


	}
	
	private void createFreiParken() {
		this.setLayout(null);
 	 	this.setBorder(BorderFactory.createMatteBorder(5, 5, 0, 0, Color.BLACK));

		JLayeredPane body = new JLayeredPane();
		body.setBounds(0, 0, size-5, size-5);
 	 	body.setBorder(BorderFactory.createMatteBorder(5, 5, 0, 0, Color.BLACK));

		body.setOpaque(false);
		JPanel picturePanel = new JPanel();
 	 	picturePanel.setBorder(BorderFactory.createMatteBorder(5, 5, 0, 0, Color.BLACK));

		picturePanel.setBackground(Constants.colors.get("board"));
		picturePanel.setBounds(0, 0, size-5, size-5);
		picturePanel.setLayout(new BorderLayout());
		
		JLabel picture = new JLabel();
		picture.setIcon(new ImageIcon(new ImageIcon("src/images/FreiParken.png").getImage().getScaledInstance((int) (size*1), (int) ((size*0.93)*1), Image.SCALE_DEFAULT)));
		picture.setHorizontalAlignment(SwingConstants.CENTER);
		picture.setVerticalAlignment(SwingConstants.CENTER);
		picturePanel.add(picture, BorderLayout.CENTER);
		
		playerPos = new JPanel();
		playerPos.setBounds((int)(size/2-(Constants.getFigureSize()*2+4)/2), (int)(size/2-(Constants.getFigureSize()*2+4)/2), Constants.getFigureSize()*2+4,Constants.getFigureSize()*2+4);
		playerPos.setOpaque(false);
		body.add(playerPos, Integer.valueOf(1));
		body.add(picturePanel, Integer.valueOf(0));
		
		this.add(body);
	}
	
	private void createGehInsGefaengnis() {
		this.setLayout(new BorderLayout());
 	 	this.setBorder(BorderFactory.createMatteBorder(5, 5, 0, 5, Color.BLACK));

		
		JLabel picture = new JLabel();
		picture.setIcon(new ImageIcon(new ImageIcon("src/images/Polizist.png").getImage().getScaledInstance((int) (size*0.7), (int) ((size*0.98)*0.7), Image.SCALE_DEFAULT)));
		picture.setHorizontalAlignment(SwingConstants.CENTER);
		picture.setVerticalAlignment(SwingConstants.CENTER);
		this.add(picture, BorderLayout.CENTER);

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
