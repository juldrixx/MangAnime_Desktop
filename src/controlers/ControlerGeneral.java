package controlers;

import java.util.Observable;
import java.util.Observer;

import components.API;
import models.ModelCurrentPage;
import models.ModelUsername;
import utils.TypeMedia;
import views.ViewGeneral;

public class ControlerGeneral {

	private ModelUsername modelUsername;
	private ModelCurrentPage modelCurrentPage;
		
	private ViewGeneral viewGeneral;
	
	private API api;
	
	public ControlerGeneral() {
		this.modelUsername = new ModelUsername();
		this.modelCurrentPage = new ModelCurrentPage();
		
		ObserverCurrentPage observerCurrentPage = new ObserverCurrentPage();
		this.modelCurrentPage.addObserver(observerCurrentPage);
		
		this.viewGeneral  = new ViewGeneral();
		this.api = new API();
		
		this.viewGeneral.pack();
		
		this.modelUsername.init();
		this.modelCurrentPage.init();
	}
	
	public class ObserverCurrentPage implements Observer {

		public ObserverCurrentPage() {
			
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			switch (modelCurrentPage.getCurrentPage()) {
			case LOGIN:
				ControlerLogin controlerLogin = new ControlerLogin(modelUsername, modelCurrentPage);
				controlerLogin.view.tfUsername.setText(modelUsername.getUsername());
				viewGeneral.setContentPane(controlerLogin.view);
				break;
			case HOME:
				ControlerHome controlerHome = new ControlerHome(modelUsername, modelCurrentPage);
				viewGeneral.setContentPane(controlerHome.view);
				break;
			case MANGA:
				ControlerMedia controlerManga = new ControlerMedia(TypeMedia.MANGA, modelUsername, modelCurrentPage, api);
				viewGeneral.setContentPane(controlerManga.view);
				viewGeneral.repaint();
				viewGeneral.revalidate();
				java.awt.EventQueue.invokeLater(new Runnable() {
				      public void run() {
				    	  controlerManga.init();
				      }
				});
				
				break;
			case ANIME:
				ControlerMedia controlerAnime = new ControlerMedia(TypeMedia.ANIME, modelUsername, modelCurrentPage, api);
				viewGeneral.setContentPane(controlerAnime.view);
				viewGeneral.repaint();
				viewGeneral.revalidate();
				java.awt.EventQueue.invokeLater(new Runnable() {
				      public void run() {
				    	  controlerAnime.init();
				      }
				});
				break;
			default:
				break;
			}			
			
			viewGeneral.repaint();
			viewGeneral.revalidate();			
		}
	}
}
