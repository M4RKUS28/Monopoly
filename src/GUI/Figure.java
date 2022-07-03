package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;

import javax.swing.JPanel;

public class Figure extends JPanel{

	private int size;
	private String color;
	public Figure(String color) {
		this.color = color;
		this.size = Constants.getFigureSize();

		this.setPreferredSize(new Dimension(size, size));
	//	this.setBounds(0,0,18, 18);
		this.setBackground(Constants.colors.get("Prot"));
	}
	
	@Override
	public void paint(Graphics g) {
	     Graphics2D g2d = (Graphics2D) g;
	     g2d.setColor(Color.white);
	     g2d.fillOval(0, 0, (int)(size), (int)(size));
	     g2d.setColor(Constants.colors.get(color));
	     g2d.fillOval((int)(size*0.15), (int)(size*0.15), (int)(size*0.8), (int)(size*0.8));
	     
	     
	}
}
