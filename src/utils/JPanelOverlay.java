package utils;

import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JPanelOverlay extends JPanel {


	@Override
	protected void paintComponent(Graphics g) {
        g.setColor( getBackground() );
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }	
}
