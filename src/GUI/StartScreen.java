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
import javax.swing.border.Border;

public class StartScreen extends JFrame implements MouseListener{
	
	JLabel schriftzug;
	JButton start;
	
	JLabel modusausswahlInfo;
	JLabel modusausswahlDM;
	JLabel modusausswahlRo;
	JLabel modusausswahlE;
	
	int versionsAuswahl;
	
	JLabel settingInfo;
	JLabel settingLosDoppel;
	boolean settigLosDoppelToggle;
	
	public StartScreen() {
		versionsAuswahl = 0;
		
		//Frame
		this.setResizable(false);
		this.setSize(768 ,512);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.white);
		this.setIconImage((new ImageIcon("src/images/Dude.jpg")).getImage());
		this.setTitle("Monopoly by J.H. and M.M.");
		
		//Monopoly Schriftzug
		schriftzug = new JLabel();
		schriftzug.setBounds(214,46,340,50);
		schriftzug.setVerticalAlignment(JLabel.CENTER);
		schriftzug.setHorizontalAlignment(JLabel.CENTER);	
		schriftzug.setText("MONOPOLY");
		schriftzug.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 50));
		schriftzug.setBackground(Color.red);
		schriftzug.setForeground(Color.white);
		schriftzug.setOpaque(true);		
		this.add(schriftzug);
		
		//Startbutton
		start = new JButton();
		start.setBounds(284, 128, 200, 40);
		start.setText("START");
		start.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		start.setFocusable(false);		
		start.setBackground(new Color(54, 181, 63));
		start.setForeground(Color.white);
		start.setBorder(BorderFactory.createEtchedBorder());
		start.addMouseListener(this);
		this.add(start);
		
		//Modusauswahl
		modusausswahlInfo = new JLabel();
		modusausswahlInfo.setBounds(60, 240, 200, 20);
		modusausswahlInfo.setText("Version des Spiels:");
		modusausswahlInfo.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 16));
		this.add(modusausswahlInfo);
		
		modusausswahlDM = new JLabel();
		modusausswahlDM.setBounds(60, 280, 200, 20);
		modusausswahlDM.setText("Deutschland (DM)");
		modusausswahlDM.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));	
		modusausswahlDM.setIcon(new ImageIcon((new ImageIcon("src/images/knopf1.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
		modusausswahlDM.setIconTextGap(16);
		modusausswahlDM.addMouseListener(this);
		this.add(modusausswahlDM);
		
		modusausswahlRo = new JLabel();
		modusausswahlRo.setBounds(60, 320, 200, 20);
		modusausswahlRo.setText("Rosenheim");
		modusausswahlRo.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));	
		modusausswahlRo.setIcon(new ImageIcon((new ImageIcon("src/images/knopf0.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
		modusausswahlRo.setIconTextGap(16);
		modusausswahlRo.addMouseListener(this);
		this.add(modusausswahlRo);
		
		modusausswahlE = new JLabel();
		modusausswahlE.setBounds(60, 360, 200, 20);
		modusausswahlE.setText("England");
		modusausswahlE.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));	
		modusausswahlE.setIcon(new ImageIcon((new ImageIcon("src/images/knopf0.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
		modusausswahlE.setIconTextGap(16);
		modusausswahlE.addMouseListener(this);
		this.add(modusausswahlE);
		
		//Einstellungen
		settingInfo = new JLabel();
		settingInfo.setBounds(480, 240, 200, 20);
		settingInfo.setText("Einstellungen:");
		settingInfo.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 16));
		this.add(settingInfo);
		
		settingLosDoppel = new JLabel();
		settingLosDoppel.setBounds(480, 280, 300, 20);
		settingLosDoppel.setText("Doppelt Geld über Los");
		settingLosDoppel.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));	
		settingLosDoppel.setIcon(new ImageIcon((new ImageIcon("src/images/knopf1.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
		settingLosDoppel.setIconTextGap(16);
		settingLosDoppel.addMouseListener(this);
		this.add(settingLosDoppel);
		
		
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().equals(start)) {
			this.setVisible(false);
			Main.start();
		} else if (e.getComponent().equals(modusausswahlDM)) {
			versionsAuswahl = 0;
			modusausswahlDM.setIcon(new ImageIcon((new ImageIcon("src/images/knopf1.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
			modusausswahlRo.setIcon(new ImageIcon((new ImageIcon("src/images/knopf0.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
			modusausswahlE.setIcon(new ImageIcon((new ImageIcon("src/images/knopf0.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
		} else if (e.getComponent().equals(modusausswahlRo)) {
			versionsAuswahl = 1;
			modusausswahlDM.setIcon(new ImageIcon((new ImageIcon("src/images/knopf0.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
			modusausswahlRo.setIcon(new ImageIcon((new ImageIcon("src/images/knopf1.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
			modusausswahlE.setIcon(new ImageIcon((new ImageIcon("src/images/knopf0.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
		} else if (e.getComponent().equals(modusausswahlE)) {
			versionsAuswahl = 2;
			modusausswahlDM.setIcon(new ImageIcon((new ImageIcon("src/images/knopf0.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
			modusausswahlRo.setIcon(new ImageIcon((new ImageIcon("src/images/knopf0.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
			modusausswahlE.setIcon(new ImageIcon((new ImageIcon("src/images/knopf1.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
		} else if (e.getComponent().equals(settingLosDoppel)) {
			versionsAuswahl = 2;			
			if (settigLosDoppelToggle) {
				settingLosDoppel.setIcon(new ImageIcon((new ImageIcon("src/images/knopf0.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
				settigLosDoppelToggle = false;
			} else {
				settingLosDoppel.setIcon(new ImageIcon((new ImageIcon("src/images/knopf1.png")).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
				settigLosDoppelToggle = true;
			}
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