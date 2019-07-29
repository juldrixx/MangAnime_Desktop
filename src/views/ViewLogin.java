package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


import i18n.Constants;
import i18n.Messages;
import utils.JPlaceholderTextField;

@SuppressWarnings("serial")
public class ViewLogin extends JPanel {
	public JLabel labelMainTitle;
	public JLabel labelSubTitle;
	public JPlaceholderTextField tfUsername;
	public JButton buttonLogin;
	
	/**
	 * Constructeur les différents widgets et leur position dans la vue
	 */
	public ViewLogin(){
		
		JPanel panelTitle = new JPanel();
		JPanel panelContainer = new JPanel();
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		panelTitle.setLayout(new BoxLayout(panelTitle, BoxLayout.Y_AXIS));
		panelTitle.setBorder(BorderFactory.createEmptyBorder(250, 0, 0, 0));
		panelContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		panelContainer.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));
		
		
		this.labelMainTitle = new JLabel(Messages.RESOURCE_BUNDLE.getString(Constants.WELCOME_MESSAGE));
		this.labelMainTitle.setFont(new Font("Verdana", Font.BOLD, 40));
		this.labelMainTitle.setForeground(Color.WHITE);
		this.labelSubTitle = new JLabel(Messages.RESOURCE_BUNDLE.getString(Constants.WELCOME_TIP));
		this.labelSubTitle.setFont(new Font("Verdana", Font.BOLD + Font.ITALIC, 20));
		this.labelSubTitle	.setForeground(Color.WHITE);
		panelTitle.add(this.labelMainTitle);
		panelTitle.add(this.labelSubTitle);
		panelTitle.setBackground(Color.decode("#1e2122"));
		this.labelMainTitle.setAlignmentX(CENTER_ALIGNMENT);
		this.labelSubTitle.setAlignmentX(CENTER_ALIGNMENT);
		this.add(panelTitle);
		
		
		this.tfUsername = new JPlaceholderTextField(20);
		this.tfUsername.setBorder(BorderFactory.createCompoundBorder(
				this.tfUsername.getBorder(), 
		        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		this.tfUsername.setPlaceholder(Messages.RESOURCE_BUNDLE.getString(Constants.USERNAME));
		this.tfUsername.setFont(new Font("Verdana", Font.PLAIN, 20));
				
		this.buttonLogin = new JButton();
		this.buttonLogin.setBackground(Color.WHITE);
		this.buttonLogin.setForeground(Color.decode("#666666"));
		this.buttonLogin.setFont(new Font("Verdana", Font.PLAIN, 20));
		this.buttonLogin.setPreferredSize(new Dimension(110, 50));
		this.buttonLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelContainer.add(this.tfUsername);
		panelContainer.add(this.buttonLogin);
		panelContainer.setBackground(Color.decode("#1e2122"));
			
		this.add(panelContainer);
		this.setAlignmentY(CENTER_ALIGNMENT);
		this.setOpaque(true);
		this.setBackground(Color.decode("#1e2122"));
	}
}
