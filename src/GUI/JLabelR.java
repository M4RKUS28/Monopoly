package GUI;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import javax.swing.JLabel;


public class JLabelR extends JLabel {
	private double theta;
	private String text;

	public JLabelR(String text, double winkel) {
		super(text);
		this.theta = winkel;
		this.text = text;

	}

	@Override
	public void paint(Graphics g) {
		// Graphics2D g2d = (Graphics2D) g;

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		AffineTransform aT = g2.getTransform();

		Shape oldshape = g2.getClip();
		double x = getWidth() / 2.0;
		double y = getHeight() / 2.0;
		aT.rotate(Math.toRadians(theta), x, y);
		g2.setTransform(aT);
		g2.setClip(oldshape);

		if (this.theta == 90) {
			if (text.contains("DM")) {
				g2.translate(0, Constants.cardWidth/4);


			} else {
				if (text.contains("West-<br>bahnhof")) {
					g2.setClip(oldshape.getBounds().x, oldshape.getBounds().y, oldshape.getBounds().width,
							oldshape.getBounds().height + 25);
					g2.translate(0, Constants.cardWidth/7);


				} else if (text.contains("lektri")) {
					g2.setClip(oldshape.getBounds().x, oldshape.getBounds().y, oldshape.getBounds().width,
							oldshape.getBounds().height + 25);

					g2.translate(0, Constants.cardWidth/12);
				} else if (text.contains("Gemein")) {
					g2.setClip(oldshape.getBounds().x, oldshape.getBounds().y, oldshape.getBounds().width,
							oldshape.getBounds().height + 25);
					g2.translate(0, Constants.cardWidth/6);
				} else {

					g2.translate(0, Constants.cardWidth/-9);
				}
			}
		}
		if (this.theta == 270) {
			if (text.contains("DM")) {
				g2.translate(0, Constants.cardWidth/4);
			} else {
				
				if (text.contains("Haupt-<br>bahnhof")) {
					g2.setClip(oldshape.getBounds().x, oldshape.getBounds().y, oldshape.getBounds().width,
							oldshape.getBounds().height + 25);
					g2.translate(0, Constants.cardWidth/7);
				} else if (text.contains("Gemein")) {
					g2.setClip(oldshape.getBounds().x, oldshape.getBounds().y, oldshape.getBounds().width,
							oldshape.getBounds().height + 25);
					g2.translate(0, Constants.cardWidth/6);
				} else if (text.contains("Ereig")) {
					g2.setClip(oldshape.getBounds().x, oldshape.getBounds().y, oldshape.getBounds().width,
							oldshape.getBounds().height + 35);
					g2.translate(0, Constants.cardWidth/4);
				}  else if (text.contains("steuer")) {
					g2.setClip(oldshape.getBounds().x, oldshape.getBounds().y, oldshape.getBounds().width,
							oldshape.getBounds().height + 27);
					g2.translate(0, Constants.cardWidth/4);
				}  else if (text.contains("athaus")) {
					g2.setClip(oldshape.getBounds().x, oldshape.getBounds().y, oldshape.getBounds().width,
							oldshape.getBounds().height + 25);
					g2.translate(0, Constants.cardWidth/-6);
				} else if (text.contains("Haupt-<br>stra")) {
					g2.setClip(oldshape.getBounds().x, oldshape.getBounds().y, oldshape.getBounds().width,
							oldshape.getBounds().height + 25);
					g2.translate(0, Constants.cardWidth/-12);
				}  else if (text.contains("Bahnhof")) {
					g2.setClip(oldshape.getBounds().x, oldshape.getBounds().y, oldshape.getBounds().width,
							oldshape.getBounds().height + 25);
					System.out.println("Gemins " + Constants.cardWidth/-20);
					g2.translate(0, Constants.cardWidth/-5);
				} else if (text.contains("Park")) {
					g2.setClip(oldshape.getBounds().x, oldshape.getBounds().y, oldshape.getBounds().width,
							oldshape.getBounds().height + 25);
					System.out.println("Gemins " + Constants.cardWidth/-20);
					g2.translate(0, Constants.cardWidth/-14);
				} else if (text.contains("Schloss")) {
					g2.setClip(oldshape.getBounds().x, oldshape.getBounds().y, oldshape.getBounds().width,
							oldshape.getBounds().height + 25);
					System.out.println("Gemins " + Constants.cardWidth/-20);
					g2.translate(0, Constants.cardWidth/-6);
				}
				
				
				else {
					g2.translate(0,0);

				}

			}
		}

		super.paintComponent(g);

		/*
		 * g2d.translate(getWidth() / 2, getHeight() / 2);
		 * 
		 * g2d.rotate(theta); super.paintComponent(g);
		 */


		
	}
	
	/*public void resizeToFitting() {
		Font labelFont = this.getFont();
		//String labelText = this.getText();

		int stringWidth = this.getFontMetrics(labelFont).stringWidth(labelText);
		int componentWidth = this.getWidth();

		// Find out how much the font can grow in width.
		double widthRatio = (double)componentWidth / (double)stringWidth;

		int newFontSize = (int)(labelFont.getSize() * widthRatio);
		int componentHeight = this.getHeight();

		// Pick a new font size so it will not be larger than the height of label.
		int fontSizeToUse = Math.min(newFontSize, componentHeight);

		// Set the label's font size to the newly determined size.
		this.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		//Constants.setSmallestStreetFontSize(fontSizeToUse);
	}
	
	public void resizeToSmallest() {
		//this.setFont(new Font(this.getFont().getName(), Font.PLAIN, Constants.getSmallestStreetFontSize()));
	}
	
	*/
	
	
	
	
	
	
}