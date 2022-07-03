package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;
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
				g2.translate(0, 20);
			} else {
				if (text.contains("West-<br>bahnhof")) {
					g2.setClip(oldshape.getBounds().x, oldshape.getBounds().y, oldshape.getBounds().width,
							oldshape.getBounds().height + 25);
					g2.translate(0, 12);

				} else if (text.contains("lektri")) {
					g2.setClip(oldshape.getBounds().x, oldshape.getBounds().y, oldshape.getBounds().width,
							oldshape.getBounds().height + 25);
					g2.translate(0, 7);
				} else if (text.contains("Ereignis-<br>feld")) {
					g2.setClip(oldshape.getBounds().x, oldshape.getBounds().y, oldshape.getBounds().width,
							oldshape.getBounds().height + 25);
					g2.translate(0, 50);
				} else {
					g2.translate(0, -12);
				}
			}
		}
		if (this.theta == 270) {
			if (text.contains("DM")) {
				g2.translate(0, 20);
			} else {
				if (!text.contains("Haupt-<br>bahnhof")) {
					g2.translate(0, -15);
				} else {
					g2.setClip(oldshape.getBounds().x, oldshape.getBounds().y, oldshape.getBounds().width,
							oldshape.getBounds().height + 25);
					g2.translate(0, 12);
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

}
