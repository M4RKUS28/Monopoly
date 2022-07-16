package GUI;

import GUI.*;
import Main.Main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.Border;

public class StartScreen extends JFrame implements MouseListener {

	JPanel mainPanel;

	JLabel schriftzug;
	JButton start;
	JLabel bild;

	JButton impressum;
	JButton settings;

	JButton back;

	JLabel impressumTitel;
	JLabel impressum0;
	JLabel impressum1;
	JLabel impressum2;
	JLabel impressum3;
	JLabel impressum4;
	JLabel impressum01;
	JLabel impressum11;
	JLabel impressum21;
	JLabel impressum31;
	JLabel impressum41;

	JLabel tmp;

	public StartScreen() {

		createFrame();

	}

	private void createFrame() {
		// Frame
		this.setResizable(false);
		this.setSize(768, 512);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.white);
		this.setIconImage((new ImageIcon("src/images/Dude.jpg")).getImage());
		this.setTitle("Monopoly");

		mainPanel = ((JPanel) ((JLayeredPane) ((JRootPane) this.getComponent(0)).getComponent(1)).getComponent(0));
		//
		// Hauptscreen
		//

		// Monopoly Schriftzug
		schriftzug = new JLabel();
		schriftzug.setBounds(214, 40, 340, 50);
		schriftzug.setVerticalAlignment(JLabel.CENTER);
		schriftzug.setHorizontalAlignment(JLabel.CENTER);
		schriftzug.setText("MONOPOLY");
		schriftzug.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 50));
		schriftzug.setBackground(Color.red);
		schriftzug.setForeground(Color.white);
		schriftzug.setOpaque(true);

		// Startbutton
		start = new JButton();
		start.setBounds(224, 330, 320, 40);
		start.setText("START");
		start.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		start.setFocusable(false);
		start.setBackground(new Color(54, 181, 63));
		start.setForeground(Color.white);
		start.setBorder(BorderFactory.createEtchedBorder());
		start.setOpaque(true);
		start.addMouseListener(this);

		// Bild
		bild = new JLabel();
		bild.setBounds(284, 110, 200, 200);
		bild.setIcon(new ImageIcon((new ImageIcon("src/images/monopoly-man.png")).getImage().getScaledInstance(200, 200,
				java.awt.Image.SCALE_SMOOTH)));

		// Impressum
		impressum = new JButton();
		impressum.setBounds(209, 380, 170, 40);
		impressum.setText("Impressum");
		impressum.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		impressum.setFocusable(false);
		impressum.setBackground(new Color(54, 181, 63));
		impressum.setForeground(Color.white);
		impressum.setBorder(BorderFactory.createEtchedBorder());
		impressum.setOpaque(true);
		impressum.addMouseListener(this);

		// Settings
		settings = new JButton();
		settings.setBounds(389, 380, 170, 40);
		settings.setText("Einstellungen");
		settings.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		settings.setFocusable(false);
		settings.setBackground(new Color(54, 181, 63));
		settings.setForeground(Color.white);
		settings.setBorder(BorderFactory.createEtchedBorder());
		settings.setOpaque(true);
		settings.addMouseListener(this);

		//
		// Settings
		//

		tmp = new JLabel();
		tmp.setBounds(154, 200, 500, 20);
		tmp.setText("Derzeit gibt es noch keine Einstellungen");
		tmp.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));

		//
		// Impressum
		//

		impressumTitel = new JLabel();
		impressumTitel.setBounds(134, 122, 200, 20);
		impressumTitel.setText("Entwickler:");
		impressumTitel.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));

		impressum0 = new JLabel();
		impressum0.setBounds(134, 162, 200, 16);
		impressum0.setText("Jonas Hörter");
		impressum0.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 16));

		impressum1 = new JLabel();
		impressum1.setBounds(134, 202, 200, 16);
		impressum1.setText("Matthias Meierlohr");
		impressum1.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 16));

		impressum2 = new JLabel();
		impressum2.setBounds(134, 242, 200, 16);
		impressum2.setText("Markus Huber");
		impressum2.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 16));

		impressum3 = new JLabel();
		impressum3.setBounds(134, 282, 200, 16);
		impressum3.setText("Tobias Kölbl");
		impressum3.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 16));

		impressum4 = new JLabel();
		impressum4.setBounds(134, 322, 200, 16);
		impressum4.setText("Paul Herz");
		impressum4.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 16));

		impressum01 = new JLabel();
		impressum01.setBounds(334, 162, 400, 16);
		impressum01.setText("|    Backend und UI");
		impressum01.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 16));

		impressum11 = new JLabel();
		impressum11.setBounds(334, 202, 400, 16);
		impressum11.setText("|    UI");
		impressum11.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 16));

		impressum21 = new JLabel();
		impressum21.setBounds(334, 242, 400, 16);
		impressum21.setText("|    Backend und Datenmanagement");
		impressum21.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 16));

		impressum31 = new JLabel();
		impressum31.setBounds(334, 282, 400, 16);
		impressum31.setText("|    Datenmanagement");
		impressum31.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 16));

		impressum41 = new JLabel();
		impressum41.setBounds(334, 322, 400, 16);
		impressum41.setText("|    Datenmanagement und Bilder");
		impressum41.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 16));

		//
		// Sonnstiges
		//

		back = new JButton();
		back.setBounds(234, 422, 300, 40);
		back.setText("Zurück zum Hauptmenü");
		back.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		back.setFocusable(false);
		back.setBackground(new Color(54, 181, 63));
		back.setForeground(Color.white);
		back.setBorder(BorderFactory.createEtchedBorder());
		back.setOpaque(true);
		back.addMouseListener(this);

		hauptScreen();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void hauptScreen() {
		mainPanel.removeAll();
		this.add(schriftzug);
		this.add(start);
		this.add(bild);
		this.add(impressum);
		this.add(settings);
		this.repaint();
	}

	private void settingsScreen() {
		mainPanel.removeAll();
		this.add(tmp);
		this.add(back);
		this.repaint();
	}

	private void impressumScreen() {
		mainPanel.removeAll();
		this.add(impressumTitel);
		this.add(impressum0);
		this.add(impressum1);
		this.add(impressum2);
		this.add(impressum3);
		this.add(impressum4);
		this.add(impressum01);
		this.add(impressum11);
		this.add(impressum21);
		this.add(impressum31);
		this.add(impressum41);
		this.add(back);
		this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().equals(start)) {
			this.setVisible(false);
			Main.start(0);
		} else if (e.getComponent().equals(impressum)) {
			impressumScreen();
		} else if (e.getComponent().equals(settings)) {
			settingsScreen();
		} else if (e.getComponent().equals(back)) {
			hauptScreen();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}