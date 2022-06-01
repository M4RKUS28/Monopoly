import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Straßenkarte extends Feld{
	private Figure[] player;
 	public Straßenkarte(String name, int position, int width, int height) {
		super(name, position);
		// TODO Auto-generated constructor stub
		player = new Figure[4];
		createKarte(width, height);
		
	}
	
	private void createKarte(int width, int height) {
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(new Color(133, 224, 190));
		//this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		//this.setLayout(new FlowLayout(FlowLayout.LEADING ,0, 0));
		this.setLayout(new BorderLayout());
		if (position < 20) {
			if (position == 1) {
				this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
			} else if (position == 19) {
				this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.BLACK));
			} else {
				this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, Color.BLACK));

			}
		}
		else if (position < 40) {
			if (position == 21) {
				this.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.BLACK));
			} else if (position == 39) {
				this.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.BLACK));
			} else {
				this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.BLACK));

			}
		}
		
		JPanel colorStripe = new JPanel();
		colorStripe.setBackground(Color.cyan);
		colorStripe.setPreferredSize(new Dimension(width-1, 27));
		if (position == 1 || position == 21) {
			colorStripe.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		} else {
			colorStripe.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

		}
		
		this.add(colorStripe, BorderLayout.NORTH);
		
		JPanel textpanel = new JPanel();
		textpanel.setBackground(null);
		textpanel.setLayout(new BorderLayout());
		textpanel.setPreferredSize(new Dimension(width-1, 34));
		
		JLabel name = new JLabel();
		name.setText("<html><body>Badstraße</body></html>");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(new Font("Helvetica", Font.BOLD, 12));
		textpanel.add(name, BorderLayout.NORTH);
	
		JLabel price = new JLabel();
		price.setText("DM 1200.-");
		price.setHorizontalAlignment(SwingConstants.CENTER);
		price.setFont(new Font("Helvetica", Font.BOLD, 12));
		textpanel.add(price, BorderLayout.CENTER);
		this.add(textpanel, BorderLayout.CENTER);
		
		JPanel figures = new JPanel();
		figures.setBackground(null);
		figures.setPreferredSize(new Dimension(width-1, 49));

		figures.setLayout(new GridLayout(2, 2));
		
		for (int i = 0; i < 4; i++) {
			player[i] = new Figure(Color.red);
			figures.add(player[i]);
		}
		this.add(figures, BorderLayout.SOUTH);
		
	}
	
	public void playerOnField(int player) {
		this.player[player].setActive(true);

	}

}
