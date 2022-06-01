import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Sonderfeld extends Feld{
	private JLabel label;
	public Sonderfeld(String name, int position, String typ) {
		super(name, position);
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
		this.setPreferredSize(new Dimension(110, 110));
		this.setBackground(Color.blue);

	}
	
	private void createLos() {
		label = new JLabel("Los");
		this.add(label);

	}
	
	private void createGefaengnis() {
		label = new JLabel("Prison");
		this.setLayout(new BorderLayout());
		

	
		

		
	}
	
	private void createFreiParken() {
		label = new JLabel("Frei Parken");
		this.add(label);

	}
	
	private void createGehInsGefaengnis() {
		label = new JLabel("Geh ins Gefängnis");
		this.add(label);

	}
}
