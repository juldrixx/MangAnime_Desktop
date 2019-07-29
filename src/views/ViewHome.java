package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import i18n.Constants;
import i18n.Messages;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import utils.JPanelOverlay;


@SuppressWarnings("serial")
public class ViewHome extends JLayeredPane {
	
	public JLayeredPane panelManga;
	public JLayeredPane panelAnime;
	
	public JPanelOverlay overlayManga;
	public JPanelOverlay overlayAnime;
	
	public JLabel logoutButton;
	
	public ViewHome() {		
		this.panelManga = new JLayeredPane();
		this.panelManga.setPreferredSize(new Dimension(797, 800));
		this.panelManga.setBounds(0, 0, 797, 800);
		
		this.panelAnime = new JLayeredPane();
		this.panelAnime.setPreferredSize(new Dimension(797, 800));
		this.panelAnime.setBounds(797, 0, 797, 800);
		
		BufferedImage imgManga;
		BufferedImage imgAnime;
		try {
			imgManga = ImageIO.read(this.getClass().getClassLoader().getResource("assets/images/manga.png"));
			Image dimgManga = imgManga.getScaledInstance(797, 800, Image.SCALE_SMOOTH);
			
			imgAnime = ImageIO.read(this.getClass().getClassLoader().getResource("assets/images/anime.jpg"));
			Image dimgAnime = imgAnime.getScaledInstance(797, 800, Image.SCALE_SMOOTH);

			JLabel backgroundManga = new JLabel(new ImageIcon(dimgManga));			
			backgroundManga.setPreferredSize(new Dimension(797, 800));
			backgroundManga.setBounds(0, 0, 797, 800);
			this.panelManga.add(backgroundManga, new Integer(1));
			
			this.overlayManga = new JPanelOverlay();
			this.overlayManga.setOpaque(false);
			this.overlayManga.setBackground(new Color(0, 0, 0, 51));
			this.overlayManga.setPreferredSize(new Dimension(797, 800));
			this.overlayManga.setBounds(0, 0, 797, 800);			
			this.panelManga.add(this.overlayManga, new Integer(2));
			
			JLabel labelManga = new JLabel(Messages.RESOURCE_BUNDLE.getString(Constants.MANGA));
			labelManga.setFont(new Font("Verdana", Font.BOLD, 120));
			labelManga.setForeground(Color.WHITE);
			labelManga.setBounds(150, 320, 700, 150);
			this.panelManga.add(labelManga, new Integer(3));
						
			
			JLabel backgroundAnime = new JLabel(new ImageIcon(dimgAnime));
			backgroundAnime.setPreferredSize(new Dimension(797, 800));
			backgroundAnime.setBounds(0, 0, 797, 800);
			this.panelAnime.add(backgroundAnime, new Integer(1));
			
			this.overlayAnime = new JPanelOverlay();
			this.overlayAnime.setOpaque(false);
			this.overlayAnime.setBackground(new Color(0, 0, 0, 51));
			this.overlayAnime.setPreferredSize(new Dimension(797, 800));
			this.overlayAnime.setBounds(0, 0, 797, 800);			
			this.panelAnime.add(this.overlayAnime, new Integer(2));			
			
			JLabel labelAnime = new JLabel(Messages.RESOURCE_BUNDLE.getString(Constants.ANIME));
			labelAnime.setFont(new Font("Verdana", Font.BOLD, 120));
			labelAnime.setForeground(Color.WHITE);
			labelAnime.setBounds(150, 320, 700, 150);
			this.panelAnime.add(labelAnime, new Integer(3));
			
			this.add(panelManga, new Integer(1));			
			this.add(panelAnime, new Integer(1));
			
			
			IconFontSwing.register(FontAwesome.getIconFont());
			Icon icon = IconFontSwing.buildIcon(FontAwesome.TIMES_CIRCLE, 40, new Color(255, 255, 255));
			this.logoutButton = new JLabel(icon);
			this.logoutButton.setBounds(10, 10, 40, 40);
			this.add(this.logoutButton, new Integer(2));
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
