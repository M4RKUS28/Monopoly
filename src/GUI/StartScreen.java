package GUI;

import GUI.*;
import Main.Main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
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
	
	public StartScreen() {
		this.setResizable(false);
		this.setSize(768 ,512);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.white);
		
		ImageIcon icon = new ImageIcon("src/images/Dude.jpg");
		this.setIconImage(icon.getImage());
		this.setTitle("Monopoly by J.H. and M.M.");
		
		schriftzug = new JLabel();
		schriftzug.setBounds(234,16,300,50);
		schriftzug.setVerticalAlignment(JLabel.CENTER);
		schriftzug.setHorizontalAlignment(JLabel.CENTER);	
		schriftzug.setText("Monpoly");
		schriftzug.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 50));
		schriftzug.setBackground(Color.red);
		schriftzug.setForeground(Color.white);
		schriftzug.setOpaque(true);		
		this.add(schriftzug);
		
		start = new JButton();
		start.setBounds(284, 98, 200, 40);
		start.setText("START");
		start.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		start.setFocusable(false);
		start.addMouseListener(this);
		start.setBackground(new Color(54, 181, 63));
		start.setForeground(Color.white);
		start.setBorder(BorderFactory.createEtchedBorder());
		this.add(start);
		
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().equals(start)) {
			this.setVisible(false);
			Main.start();
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