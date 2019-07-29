package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

@SuppressWarnings("serial")
public class ViewMediaUnit extends JPanel {
	
	public JLabel labelState;
	public JLabel labelDate;
	public JLabel labelTitle;
	public JLabel labelLastViewed;
	public JLabel labelRelease;
	public JLabel actionState;
	public JLabel actionURL;
	public JLabel actionUnfollow;
	
	public JFormattedTextField ftfLastViewed;
	public JButton buttonLastViewed;
	
	public JPanel panelLastViewed;
	public JPanel panelLastViewedEdition;
	
	public ViewMediaUnit() {
		ArrayList<Integer> colWidth = new ArrayList<>();
		colWidth.add(200);
		colWidth.add(200);
		colWidth.add(400);
		colWidth.add(200);
		colWidth.add(200);
		colWidth.add(264);
		IconFontSwing.register(FontAwesome.getIconFont());
		
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		
		this.labelState = new JLabel("");
		this.labelState.setForeground(Color.WHITE);
		this.labelState.setFont(new Font("Verdana", Font.PLAIN, 15));
		this.labelState.setOpaque(true);
		this.labelState.setBackground(Color.decode("#212529"));
		this.labelState.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		this.labelState.setPreferredSize(new Dimension(colWidth.get(0), 40));
		this.add(this.labelState);
		
		
		this.labelDate = new JLabel("");
		this.labelDate.setForeground(Color.WHITE);
		this.labelDate.setFont(new Font("Verdana", Font.PLAIN, 15));
		this.labelDate.setOpaque(true);
		this.labelDate.setBackground(Color.decode("#212529"));
		this.labelDate.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		this.labelDate.setPreferredSize(new Dimension(colWidth.get(1), 40));
		this.add(this.labelDate);
		
		
		this.labelTitle = new JLabel("");
		this.labelTitle.setForeground(Color.WHITE);
		this.labelTitle.setFont(new Font("Verdana", Font.PLAIN, 15));
		this.labelTitle.setOpaque(true);
		this.labelTitle.setBackground(Color.decode("#212529"));
		this.labelTitle.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		this.labelTitle.setPreferredSize(new Dimension(colWidth.get(2), 40));
		this.add(this.labelTitle);
		
		
		this.panelLastViewed = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		this.panelLastViewed.setPreferredSize(new Dimension(colWidth.get(3), 40));
		this.panelLastViewed.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		this.panelLastViewed.setBackground(Color.decode("#212529"));
	
		this.labelLastViewed = new JLabel("");
		this.labelLastViewed.setForeground(Color.WHITE);
		this.labelLastViewed.setFont(new Font("Verdana", Font.PLAIN, 15));
		this.labelLastViewed.setPreferredSize(new Dimension(colWidth.get(3), 40));
		this.labelLastViewed.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.panelLastViewed.add(this.labelLastViewed);
		
		this.panelLastViewedEdition = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.panelLastViewedEdition.setPreferredSize(new Dimension(colWidth.get(3), 40));
		this.panelLastViewedEdition.setBorder(BorderFactory.createEmptyBorder(5,0,0,0));
		this.panelLastViewedEdition.setOpaque(false);
		
		NumberFormat format = NumberFormat.getNumberInstance(Locale.UK);;
		this.ftfLastViewed = new JFormattedTextField(format);
		this.ftfLastViewed.setColumns(6);
		this.ftfLastViewed.setPreferredSize(new Dimension(20, 30));
		this.ftfLastViewed.setFont(new Font("Verdana", Font.PLAIN, 15));
		this.panelLastViewedEdition.add(this.ftfLastViewed);
		
		this.buttonLastViewed = new JButton("");
		this.buttonLastViewed.setBackground(Color.decode("#212529"));
		this.buttonLastViewed.setForeground(Color.WHITE);
		this.buttonLastViewed.setFont(new Font("Verdana", Font.PLAIN, 15));
		this.panelLastViewedEdition.add(this.buttonLastViewed);
		
		this.add(this.panelLastViewed);
		
		
		this.labelRelease = new JLabel("");
		this.labelRelease.setForeground(Color.WHITE);
		this.labelRelease.setFont(new Font("Verdana", Font.PLAIN, 15));
		this.labelRelease.setOpaque(true);
		this.labelRelease.setBackground(Color.decode("#212529"));
		this.labelRelease.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		this.labelRelease.setPreferredSize(new Dimension(colWidth.get(4), 40));
		this.add(this.labelRelease);
		
		
		JPanel panelAction = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
		panelAction.setBackground(Color.decode("#212529"));
		panelAction.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		panelAction.setPreferredSize(new Dimension(colWidth.get(5), 40));		

		this.actionState = new JLabel();
		this.actionState.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelAction.add(this.actionState);
		
		Icon iconURL = IconFontSwing.buildIcon(FontAwesome.LINK, 25, Color.decode("#13a1ff"));
		this.actionURL = new JLabel(iconURL);
		this.actionURL.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelAction.add(this.actionURL);
		
		Icon iconUnfollow = IconFontSwing.buildIcon(FontAwesome.TRASH, 25, Color.decode("#554b4b"));
		this.actionUnfollow = new JLabel(iconUnfollow);
		this.actionUnfollow.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelAction.add(this.actionUnfollow);
		
		this.add(panelAction);
		
		
		this.setMinimumSize(new Dimension(1464, 40));
		this.setMaximumSize(new Dimension(1464, 40));
		this.setPreferredSize(new Dimension(1464, 40));
		this.setBackground(Color.red);
		
	}
}
