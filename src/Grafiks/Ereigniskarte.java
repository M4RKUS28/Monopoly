import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Ereigniskarte extends Feld{
	public Ereigniskarte() {
		super("name", 40);
		this.setPreferredSize(new Dimension(250, 200));
		this.setBackground(Color.CYAN);
	}
	
   
}
