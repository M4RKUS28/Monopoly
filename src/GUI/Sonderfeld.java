package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Sonderfeld extends Field{
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
		this.setBackground(new Color(133, 224, 190));
	}
	
	private void createLos() {
		label = new JLabel("Los");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setForeground(Color.white);
		label.setFont(new Font("Helvetica", Font.BOLD, 30));
		this.setLayout(new BorderLayout());

		this.add(label, BorderLayout.CENTER);

	}
	
	private void createGefaengnis() {
		label = new JLabel("Gefängnis");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setForeground(Color.white);
		label.setFont(new Font("Helvetica", Font.BOLD, 20));
		this.setLayout(new BorderLayout());
		
		this.add(label, BorderLayout.CENTER);

	
		

		
	}
	
	private void createFreiParken() {
		label = new JLabel("Frei Parken");
		
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setForeground(Color.white);
		label.setFont(new Font("Helvetica", Font.BOLD, 20));
		this.setLayout(new BorderLayout());
		
		this.add(label, BorderLayout.CENTER);

	}
	
	private void createGehInsGefaengnis() {
		label = new JLabel("<html><body><center>Geh ins<br> Gefängnis</center></body></html>");
		
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setForeground(Color.white);
		label.setFont(new Font("Helvetica", Font.BOLD, 20));
		this.setLayout(new BorderLayout());
		
		this.add(label, BorderLayout.CENTER);

	}
}
