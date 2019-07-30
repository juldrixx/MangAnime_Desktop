package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import i18n.Constants;
import i18n.Messages;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import utils.JPlaceholderTextField;
import utils.TypeMedia;

@SuppressWarnings("serial")
public class ViewMedia extends JLayeredPane {

	public JPlaceholderTextField tfRssUrl;
	public JButton buttonAdd;
	public JPanel panelMedia;
	public JLabel actionBack;
	
	public ViewMedia(TypeMedia type) {
		
		ArrayList<Integer> colWidth = new ArrayList<>();
		colWidth.add(200);
		colWidth.add(200);
		colWidth.add(400);
		colWidth.add(200);
		colWidth.add(200);
		colWidth.add(264);
		
		this.setOpaque(true);
		this.setBackground(Color.WHITE);
		
		
		BufferedImage imgBanner;
		try {
			
			switch (type) {
			case MANGA:
				imgBanner = ImageIO.read(this.getClass().getClassLoader().getResource("assets/images/manga_banner.png"));
				break;
			case ANIME:
				imgBanner = ImageIO.read(this.getClass().getClassLoader().getResource("assets/images/anime_banner.jpg"));
				break;
			default:
				imgBanner = ImageIO.read(this.getClass().getClassLoader().getResource("assets/images/manga_banner.png"));
				break;
			}			
			
			Image dimgBanner = imgBanner.getScaledInstance(1600, imgBanner.getHeight() * 1600 / imgBanner.getWidth(), Image.SCALE_SMOOTH);
			JLabel banner = new JLabel(new ImageIcon(dimgBanner));
			banner.setPreferredSize(new Dimension(1600, 200));
			banner.setBounds(0, 0, 1600, 200);
			
			this.add(banner, new Integer(1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		IconFontSwing.register(FontAwesome.getIconFont());
		Icon iconBack = IconFontSwing.buildIcon(FontAwesome.ANGLE_LEFT, 80, Color.WHITE);
		this.actionBack = new JLabel(iconBack);
		this.actionBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.actionBack.setBounds(0, 0, 60, 60);
		this.add(this.actionBack, new Integer(2));
		
		JPanel panelInput = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		panelInput.setBounds(0, 200, 1600, 50);
		panelInput.setOpaque(false);
		
		this.tfRssUrl = new JPlaceholderTextField();
		this.tfRssUrl = new JPlaceholderTextField(70);
		this.tfRssUrl.setBorder(BorderFactory.createCompoundBorder(
				this.tfRssUrl.getBorder(), 
		        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		switch (type) {
		case MANGA:
			this.tfRssUrl.setPlaceholder("http://fanfox.net/rss/...");
			break;
		case ANIME:
			this.tfRssUrl.setPlaceholder("https://www.jetanime.co/rss/...");
			break;
		default:
			break;
		}
		
		this.tfRssUrl.setFont(new Font("Verdana", Font.PLAIN, 20));
		panelInput.add(this.tfRssUrl);
	
		this.buttonAdd = new JButton();
		this.buttonAdd.setBackground(Color.WHITE);
		this.buttonAdd.setForeground(Color.decode("#666666"));
		this.buttonAdd.setFont(new Font("Verdana", Font.PLAIN, 20));
		this.buttonAdd.setPreferredSize(new Dimension(110, 50));
		this.buttonAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelInput.add(this.buttonAdd);
		
		this.add(panelInput, new Integer(2));
		
		
		JPanel panelHeader = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		panelHeader.setBounds(68, 250, 1464, 40);
		ArrayList<String> header = new ArrayList<>();
		header.add("");
		header.add(Messages.RESOURCE_BUNDLE.getString(Constants.DATE));
		
		switch (type) {
		case MANGA:
			header.add(Messages.RESOURCE_BUNDLE.getString(Constants.MANGA));
			header.add(Messages.RESOURCE_BUNDLE.getString(Constants.LAST_READ));
			break;
		case ANIME:
			header.add(Messages.RESOURCE_BUNDLE.getString(Constants.ANIME));
			header.add(Messages.RESOURCE_BUNDLE.getString(Constants.LAST_VIEWED));
			break;
		default:
			break;
		}	
		
		header.add(Messages.RESOURCE_BUNDLE.getString(Constants.LAST_RELEASE));
		header.add("");
		
		int i = 0;
		for (String head : header) {
			JLabel labelHead = new JLabel(head);
			labelHead.setForeground(Color.WHITE);
			labelHead.setFont(new Font("Verdana", Font.BOLD, 20));
			labelHead.setOpaque(true);
			labelHead.setBackground(Color.decode("#212529"));
			labelHead.setPreferredSize(new Dimension(colWidth.get(i), 40));
			labelHead.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
			panelHeader.add(labelHead);
			i++;
		}
		
		this.add(panelHeader);
		
		this.panelMedia = new JPanel();
		this.panelMedia.setLayout(new BoxLayout(this.panelMedia, BoxLayout.Y_AXIS));
		this.panelMedia.setOpaque(true);
		this.panelMedia.setBackground(Color.WHITE);
		
		JScrollPane scrollPanelMedia = new JScrollPane(this.panelMedia);
		scrollPanelMedia.setBounds(68, 290, 1465, 480);
		scrollPanelMedia.setBorder(BorderFactory.createEmptyBorder());
		scrollPanelMedia.setOpaque(false);
		scrollPanelMedia.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scrollPanelMedia, new Integer(2));
	}
	
}
