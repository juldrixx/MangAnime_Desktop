package utils;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class JPanelImage extends JPanel {

	private Image image;
	
    public JPanelImage(Image image){
    	this.image = image;
    }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.drawImage(image, 0, 0, this);
    }
}
