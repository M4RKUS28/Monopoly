package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Info extends Field{
	private String text;
	private String[] typ;
	private int width;
	private int height;
	private int x;
	private int y;
	public Info(String text, String[] typ, int x, int y, int width, int height) {
		super(text, -2);
		this.text = text;
		this.typ = typ;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		System.out.println(x + " " + y);
		this.createKarte();
	}
	
	private void createKarte() {
		this.setBounds(x, y, width, height);
		this.setBackground(Constants.colors.get("board"));
		this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
		this.setLayout(null);
		
		JPanel messageBoard = new JPanel();
		messageBoard.setBackground(Constants.colors.get("board"));
		messageBoard.setBounds(0, 5, width, (int)(height*0.75)-5);
		messageBoard.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, Color.black));

		messageBoard.setLayout(new BorderLayout());
		
		JLabel message = new JLabel();
		message.setPreferredSize(new Dimension(width, (int) (0.8*height)));
		message.setFont(new Font("Arial", Font.BOLD, 14));
		message.setVerticalAlignment(SwingConstants.CENTER);
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setBackground(this.colors.get("board"));
		message.setText(this.transformText(text));
		messageBoard.add(message, BorderLayout.CENTER);
		
		this.add(messageBoard);
		
		this.add(createButtons());
		
		
		
	}
	
	private JPanel createButtons() {
		JPanel buttonBoard = new JPanel();
		buttonBoard.setBackground(Constants.colors.get("board"));
		buttonBoard.setBounds(5, (int)(height*0.75), width-5, (int)(height*0.25)-5);
		buttonBoard.setBorder(BorderFactory.createMatteBorder(5, 5, 0, 5, Color.black));

		buttonBoard.setLayout(new FlowLayout(FlowLayout.CENTER, (int) (0.05*width), 0));
		
		for (String t : typ) {
			buttonBoard.add(createButton(t));
		}
		
		return buttonBoard;
	}
	
	private JLabel createButton(String text) {
		JLabel ok = new JLabel();
		ok.setBackground(Constants.colors.get("board"));
		ok.setFont(new Font("Arial", Font.BOLD, 14));
		ok.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
		ok.setPreferredSize(new Dimension((int)(height*0.25), (int)(width/3)));
		ok.setText("<html><head></head><body><center>" + text + "</center></body></html>");
		ok.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Button " + text);
				//setVisible false
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

		});
		return ok;
	}

	@Override
	public void addPlayer(String color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePlayer(String color) {
		// TODO Auto-generated method stub
		
	}
}
