package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class CustomScrollBarUI extends BasicScrollBarUI {
    
    private final Dimension d = new Dimension();

   @Override
    protected JButton createDecreaseButton(int orientation) {
      return new JButton() {
        
          private static final long serialVersionUID = -3592643796245558676L;

          @Override
            public Dimension getPreferredSize() {
              return d;
            }
          };
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
      return new JButton() {
        
          private static final long serialVersionUID = 1L;

      @Override
        public Dimension getPreferredSize() {
          return d;
        }
      };
    }
    

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
      Graphics2D g2 = (Graphics2D) g.create();
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      Color color = null;
      JScrollBar sb = (JScrollBar) c;
      sb.setBackground(new Color(178,218,173));

      if (!sb.isEnabled() || r.width > r.height) {
        return;
      } else if (isDragging) {
        color = Color.DARK_GRAY; // change color
      } else if (isThumbRollover()) {
        color = Color.LIGHT_GRAY; // change color
      } else {
        color = Color.GRAY; // change color
      }
      g2.setPaint(new Color(146,178,142));
      g2.fillRoundRect((int)(r.x+6), r.y, (int)(r.width*0.4), r.height, 10, 10);
     
      g2.setPaint(new Color(146,178,142));

      g2.drawRoundRect((int)(r.x+6), r.y, (int)(r.width*0.4), r.height, 10, 10);
      g2.dispose();
    }

    @Override
    protected void setThumbBounds(int x, int y, int width, int height) {
      super.setThumbBounds(x, y, width, height);
      scrollbar.repaint();
    }
  }