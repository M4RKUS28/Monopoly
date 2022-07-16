package GUI;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.SwingConstants;

import Main.Main;

public class EndScreen extends JFrame implements MouseListener{
	
	JPanel mainPanel;
	
	JLabel podium1;
	JLabel podium2;
	JLabel podium3;
	
	JLabel spielerAnzeige1;
	JLabel spielerAnzeige2;
	JLabel spielerAnzeige3;
	
	JLabel geldAnzeige1;
	JLabel geldAnzeige2;
	JLabel geldAnzeige3;
	
	JLabel spielerAnzeige4;
	
	JButton neues;
	JButton beenden;
	
	public EndScreen() {
		//Frame
				this.setResizable(false);
				this.setSize(768 ,512);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setLayout(null);
				this.getContentPane().setBackground(Color.white);
				this.setIconImage((new ImageIcon("src/images/Dude.jpg")).getImage());
				this.setTitle("Monopoly by J.H. and M.M.");
						
				mainPanel = ((JPanel) ((JLayeredPane) ((JRootPane) this.getComponent(0)).getComponent(1)).getComponent(0));
				
				//podiumbloecke
				
				
				podium1 = new JLabel();
				podium1.setBounds(304,196,160,140);
				podium1.setText("1");
				podium1.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 50));
				podium1.setForeground(new Color(238, 203, 4));
				podium1.setBackground(new Color(46, 51, 75));
				podium1.setHorizontalAlignment(SwingConstants.CENTER);
				podium1.setVerticalAlignment(SwingConstants.CENTER);
				podium1.setOpaque(true);
				this.add(podium1);
				
				podium2 = new JLabel();
				podium2.setBounds(464,236,160,100);
				podium2.setText("2");
				podium2.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 40));
				podium2.setForeground(new Color(238, 203, 4));
				podium2.setBackground(new Color(46, 51, 75));
				podium2.setHorizontalAlignment(SwingConstants.CENTER);
				podium2.setVerticalAlignment(SwingConstants.CENTER);
				podium2.setOpaque(true);
				this.add(podium2);
				
				podium3 = new JLabel();
				podium3.setBounds(144,276,160,60);
				podium3.setText("3");
				podium3.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 30));
				podium3.setForeground(new Color(238, 203, 4));
				podium3.setBackground(new Color(46, 51, 75));
				podium3.setHorizontalAlignment(SwingConstants.CENTER);
				podium3.setVerticalAlignment(SwingConstants.CENTER);
				podium3.setOpaque(true);
				this.add(podium3);
				
				//Spielerschriftzuege auf dem Podium							
				spielerAnzeige1 = new JLabel();
				spielerAnzeige1.setBounds(304,156,160,40);
				spielerAnzeige1.setText("Spieler 1");
				spielerAnzeige1.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 30));
				spielerAnzeige1.setHorizontalAlignment(SwingConstants.CENTER);
				spielerAnzeige1.setVerticalAlignment(SwingConstants.CENTER);
				this.add(spielerAnzeige1);
				
				
				spielerAnzeige2 = new JLabel();
				spielerAnzeige2.setBounds(464,201,160,40);
				spielerAnzeige2.setText("Spieler 2");
				spielerAnzeige2.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 25));
				spielerAnzeige2.setHorizontalAlignment(SwingConstants.CENTER);
				spielerAnzeige2.setVerticalAlignment(SwingConstants.CENTER);
				this.add(spielerAnzeige2);
				
				spielerAnzeige3 = new JLabel();
				spielerAnzeige3.setBounds(144,241,160,40);
				spielerAnzeige3.setText("Spieler 3");
				spielerAnzeige3.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
				spielerAnzeige3.setHorizontalAlignment(SwingConstants.CENTER);
				spielerAnzeige3.setVerticalAlignment(SwingConstants.CENTER);
				this.add(spielerAnzeige3);
				
				//Geldanzeige auf dem Podium
				geldAnzeige1 = new JLabel();
				geldAnzeige1.setBounds(304,131,160,40);
				geldAnzeige1.setText("DM 30000");
				geldAnzeige1.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
				geldAnzeige1.setHorizontalAlignment(SwingConstants.CENTER);
				geldAnzeige1.setVerticalAlignment(SwingConstants.CENTER);
				this.add(geldAnzeige1);
				
				geldAnzeige2 = new JLabel();
				geldAnzeige2.setBounds(464,181,160,40);
				geldAnzeige2.setText("DM 30000");
				geldAnzeige2.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 17));
				geldAnzeige2.setHorizontalAlignment(SwingConstants.CENTER);
				geldAnzeige2.setVerticalAlignment(SwingConstants.CENTER);
				this.add(geldAnzeige2);
				
				geldAnzeige3 = new JLabel();
				geldAnzeige3.setBounds(144,221,160,40);
				geldAnzeige3.setText("DM 30000");
				geldAnzeige3.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 15));
				geldAnzeige3.setHorizontalAlignment(SwingConstants.CENTER);
				geldAnzeige3.setVerticalAlignment(SwingConstants.CENTER);
				this.add(geldAnzeige3);
				
				//Schriftzug unterm Podium
				spielerAnzeige4 = new JLabel();
				spielerAnzeige4.setBounds(224,380,320,20);
				spielerAnzeige4.setText("Platz 4: Spieler 4, DM 30000");
				spielerAnzeige4.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
				spielerAnzeige4.setHorizontalAlignment(SwingConstants.CENTER);
				spielerAnzeige4.setVerticalAlignment(SwingConstants.CENTER);
				this.add(spielerAnzeige4);
				
				//Buttons
				neues = new JButton();
				neues.setBounds(219, 50, 160, 40);
				neues.setText("Neues Spiel");
				neues.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
				neues.setFocusable(false);		
				neues.setBackground(new Color(54, 181, 63));
				neues.setForeground(Color.white);
				neues.setBorder(BorderFactory.createEtchedBorder());
				neues.setOpaque(true);
				neues.addMouseListener(this);
				this.add(neues);
				
				beenden = new JButton();
				beenden.setBounds(389, 50, 160, 40);
				beenden.setText("Beenden");
				beenden.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
				beenden.setFocusable(false);		
				beenden.setBackground(new Color(54, 181, 63));
				beenden.setForeground(Color.white);
				beenden.setBorder(BorderFactory.createEtchedBorder());
				beenden.setOpaque(true);
				beenden.addMouseListener(this);
				this.add(beenden);
							
				this.setLocationRelativeTo(null);
				this.setVisible(true);
	}
	
	public void endAnzeige(int[][] i) {
		spielerAnzeige1.setText("Spieler " + i[0][0]);
		spielerAnzeige2.setText("Spieler " + i[1][0]);
		spielerAnzeige3.setText("Spieler " + i[2][0]);
		
		geldAnzeige1.setText("DM " + i[0][1]);
		geldAnzeige2.setText("DM " + i[1][1]);
		geldAnzeige3.setText("DM " + i[2][1]);
		
		spielerAnzeige1.setText("Platz 4: Spieler " + i[0][0] + ", DM " + i[0][1]);
		this.setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().equals(neues)) {
			this.setVisible(false);
			Main.start(0);
		} else if (e.getComponent().equals(beenden)) {
			this.setVisible(false);
			System.exit(0);
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
