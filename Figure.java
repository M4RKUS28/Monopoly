import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Figure extends JPanel{
	private Color c;
	private Color curColor;
	public Figure(Color c) {
		this.c = c;
		curColor = c;
		this.setPreferredSize(new Dimension(50, 50));
	}
	
	public void setActive(boolean active) {
		if (active) {
			curColor = c;
		} else {
			curColor = new Color(133, 224, 190);
		}
		repaint();
	}
	
	
	@Override
	public void paint(Graphics g) {
	     Graphics2D g2d = (Graphics2D) g;
	     g2d.setColor(curColor);
	     g2d.fillOval(5, 0, 20, 20);
	     
	}
}
