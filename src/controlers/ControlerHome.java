package controlers;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import models.ModelCurrentPage;
import models.ModelUsername;
import utils.JPanelOverlay;
import utils.Page;
import views.ViewHome;

public class ControlerHome {

	public ViewHome view;
	private ModelUsername modelUsername;
	private ModelCurrentPage modelCurrentPage;
	
	public ControlerHome(ModelUsername modelUsername, ModelCurrentPage modelCurrentPage) {
		this.view = new ViewHome();
		this.modelUsername = modelUsername;
		this.modelCurrentPage = modelCurrentPage;
		
		MouseMotion mouseManga = new MouseMotion("manga");
		MouseMotion mouseAnime = new MouseMotion("anime");
		MouseMotion mouseLogout = new MouseMotion("logout");
		this.view.panelManga.addMouseListener(mouseManga);
		this.view.panelAnime.addMouseListener(mouseAnime);
		this.view.logoutButton.addMouseListener(mouseLogout);
	}
	
	private class MouseMotion implements MouseListener {
		
		String type;
		
		public MouseMotion(String type) {
			this.type = type;			
		}


		@Override
		public void mouseClicked(MouseEvent e) {
			switch (this.type) {
			case "manga":
				modelCurrentPage.setCurrentPage(Page.MANGA);
				break;
			case "anime":
				modelCurrentPage.setCurrentPage(Page.ANIME);
				break;
			case "logout":
				modelUsername.erase();
				modelCurrentPage.setCurrentPage(Page.LOGIN);
				break;
			default:
				break;
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			JPanelOverlay overlay = new JPanelOverlay();
			JLayeredPane panel = new JLayeredPane();
			JLabel label = new JLabel();
			
			switch (this.type) {
			case "manga":
				overlay = view.overlayManga;
				panel = view.panelManga;
				break;
			case "anime":
				overlay = view.overlayAnime;
				panel = view.panelAnime;
				break;
			case "logout":
				label = view.logoutButton;
				break;
			default:
				break;
			}
			
			overlay.setBackground(new Color(0, 0, 0, 153));
			panel.setCursor(new Cursor(Cursor.HAND_CURSOR));

			IconFontSwing.register(FontAwesome.getIconFont());
			Icon icon = IconFontSwing.buildIcon(FontAwesome.TIMES_CIRCLE, 40, Color.decode("#e6e6e6"));
			label.setIcon(icon);
			label.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JPanelOverlay overlay = new JPanelOverlay();
			JLayeredPane panel = new JLayeredPane();
			JLabel label = new JLabel();
			
			switch (this.type) {
			case "manga":
				overlay = view.overlayManga;
				panel = view.panelManga;
				break;
			case "anime":
				overlay = view.overlayAnime;
				panel = view.panelAnime;
				break;
			case "logout":
				label = view.logoutButton;
				break;
			default:
				break;
			}
			
			overlay.setBackground(new Color(0, 0, 0, 51));
			panel.setCursor(Cursor.getDefaultCursor());
			
			IconFontSwing.register(FontAwesome.getIconFont());
			Icon icon = IconFontSwing.buildIcon(FontAwesome.TIMES_CIRCLE, 40, new Color(255, 255, 255));
			label.setIcon(icon);
			label.setCursor(Cursor.getDefaultCursor());
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
	}
}
