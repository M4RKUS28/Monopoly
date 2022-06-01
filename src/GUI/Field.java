package GUI;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public abstract class Field extends JPanel{
	public String name;
	public int position;	
	
	public Field(String name, int position) {
		this.name = name;
		this.position = position;
	}
	
	/* public void paintComponent(Graphics g){
	        Graphics2D g2d=(Graphics2D)g; // Create a Java2D version of g.
	        g2d.translate(110, 0);
	        g2d.rotate(Math.PI * 0.5);  // Rotate the image by 1 radian.
	        this.repaint();
	   }*/
}
