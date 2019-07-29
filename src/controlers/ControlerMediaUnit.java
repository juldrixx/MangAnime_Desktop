package controlers;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import components.API;
import components.Media;
import i18n.Constants;
import i18n.Messages;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import models.ModelMedia;
import utils.TypeMedia;
import views.ViewMediaUnit;

public class ControlerMediaUnit {

	private API api;
	private Media media;
	private ModelMedia modelMedia;
	
	public ViewMediaUnit view;
	
	public ControlerMediaUnit(TypeMedia type, API api, Media media, ModelMedia modelMedia) {
		this.api = api;
		this.media = media;
		this.modelMedia = modelMedia;
		
		this.view = new ViewMediaUnit();
		
		MouseListenerLastViewed mouseListenerLastViewed = new MouseListenerLastViewed();
		this.view.labelLastViewed.addMouseListener(mouseListenerLastViewed);
		
		ActionLastViewedEdition actionLastViewedEdition = new ActionLastViewedEdition();
		this.view.buttonLastViewed.setAction(actionLastViewedEdition);
		
		MouseListenerActionState mouseListenerActionState = new MouseListenerActionState();
		this.view.actionState.addMouseListener(mouseListenerActionState);
		
		MouseListenerActionURL mouseListenerActionURL = new MouseListenerActionURL();
		this.view.actionURL.addMouseListener(mouseListenerActionURL);
		
		MouseListenerActionUnfollow mouseListenerActionUnfollow = new MouseListenerActionUnfollow();
		this.view.actionUnfollow.addMouseListener(mouseListenerActionUnfollow);
	}
	
	public class MouseListenerActionUnfollow implements MouseListener {

		public MouseListenerActionUnfollow() {
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(api.unfollowMedia((media.getId()))) {
				modelMedia.removeMedia(media);
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			Icon icon = IconFontSwing.buildIcon(FontAwesome.TRASH, 25, Color.WHITE);
			view.actionUnfollow.setIcon(icon);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			Icon icon = IconFontSwing.buildIcon(FontAwesome.TRASH, 25, Color.decode("#554b4b"));
			view.actionUnfollow.setIcon(icon);			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}		
	}
	
	public class MouseListenerActionURL implements MouseListener {

		public MouseListenerActionURL() {
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			String url = media.getUrl();

	        if(Desktop.isDesktopSupported()){
	            Desktop desktop = Desktop.getDesktop();
	            try {
	                desktop.browse(new URI(url));
	            } catch (IOException | URISyntaxException err) {
	                // TODO Auto-generated catch block
	            	err.printStackTrace();
	            }
	        }else{
	            Runtime runtime = Runtime.getRuntime();
	            try {
	                runtime.exec("xdg-open " + url);
	            } catch (IOException err) {
	                // TODO Auto-generated catch block
	            	err.printStackTrace();
	            }
	        }
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			Icon icon = IconFontSwing.buildIcon(FontAwesome.LINK, 25, Color.WHITE);
			view.actionURL.setIcon(icon);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			Icon icon = IconFontSwing.buildIcon(FontAwesome.LINK, 25, Color.decode("#13a1ff"));
			view.actionURL.setIcon(icon);			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}		
	}
	
	public class MouseListenerActionState implements MouseListener {

		public MouseListenerActionState() {
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			Media m = api.updateMedia(media.getMedia_id(), media.getRelease_number());
			if(m != null) {
				media = m;
				modelMedia.updateMedia(media);
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			IconFontSwing.register(FontAwesome.getIconFont());
			if (!media.isCompleted()) {				
				Icon icon = IconFontSwing.buildIcon(FontAwesome.TIMES, 25, new Color(255, 255, 255));
				view.actionState.setIcon(icon);
			}
			else {
				Icon icon = IconFontSwing.buildIcon(FontAwesome.CHECK, 25, new Color(255, 255, 255));
				view.actionState.setIcon(icon);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			IconFontSwing.register(FontAwesome.getIconFont());
			if (!media.isCompleted()) {				
				Icon icon = IconFontSwing.buildIcon(FontAwesome.TIMES, 25, Color.decode("#d10531"));
				view.actionState.setIcon(icon);
			}
			else {
				Icon icon = IconFontSwing.buildIcon(FontAwesome.CHECK, 25, Color.decode("#0dab76"));
				view.actionState.setIcon(icon);
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
		
	}
	
	@SuppressWarnings("serial")
	public class ActionLastViewedEdition extends AbstractAction {
		
		public ActionLastViewedEdition() {
			this.putValue(NAME, Messages.RESOURCE_BUNDLE.getString(Constants.OK));
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Media m = api.updateMedia(media.getMedia_id(), Double.parseDouble((view.ftfLastViewed.getText())));
			if(m != null) {
				media = m;
				modelMedia.updateMedia(media);
			}
			
			view.panelLastViewed.remove(view.panelLastViewedEdition);
			view.panelLastViewed.add(view.labelLastViewed);
			view.repaint();
			view.revalidate();
		}
	}
	
	public class MouseListenerLastViewed implements MouseListener {

		public MouseListenerLastViewed() {
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			view.panelLastViewed.remove(view.labelLastViewed);
			view.panelLastViewed.add(view.panelLastViewedEdition);
			view.repaint();
			view.revalidate();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
		
	}
}
