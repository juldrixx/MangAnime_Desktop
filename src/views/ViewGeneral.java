package views;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import i18n.Constants;
import i18n.Messages;

@SuppressWarnings("serial")
public class ViewGeneral extends JFrame {

	public ViewGeneral() {		
		this.setIconImage((new ImageIcon(this.getClass().getClassLoader().getResource("assets/images/icon_manganime.png"))).getImage());
		this.setResizable(false);
		this.setPreferredSize(new Dimension(1600, 800));
		this.setTitle(Messages.RESOURCE_BUNDLE.getString(Constants.APP_NAME));		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
