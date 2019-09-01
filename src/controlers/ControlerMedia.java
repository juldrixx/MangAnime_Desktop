package controlers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import components.API;
import components.Media;
import i18n.Constants;
import i18n.Messages;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import models.ModelCurrentPage;
import models.ModelMedia;
import models.ModelUsername;
import utils.DateUtils;
import utils.Page;
import utils.TypeMedia;
import views.ViewMedia;

public class ControlerMedia {

	public ViewMedia view;
	
	private TypeMedia type;
	private ModelUsername modelUsername;
	private ModelCurrentPage modelCurrentPage;
	private ModelMedia modelMedia;
	private API api;
	private Map<Integer, ControlerMediaUnit> controlerMediaUnitList;
	
	public ControlerMedia(TypeMedia type, ModelUsername modelUsername, ModelCurrentPage modelCurrentPage, API api) {
		this.api = api;
		this.type = type;
		this.modelUsername = modelUsername;
		this.modelCurrentPage = modelCurrentPage;

		this.view = new ViewMedia(type);
		this.controlerMediaUnitList = new Hashtable<>();
		
		ActionAdd actionAdd = new ActionAdd();
		this.view.buttonAdd.setAction(actionAdd);
		
		MouseListenerActionBack mouseListenerActionBack = new MouseListenerActionBack();
		this.view.actionBack.addMouseListener(mouseListenerActionBack);		
	}
	
	public void init() {
		this.modelMedia = new ModelMedia(this.api.getMedia(type.toString(), modelUsername.getUsername()));
		MediaObserver mediaObserver = new MediaObserver();
		this.modelMedia.addObserver(mediaObserver);
		this.modelMedia.init();
	}
	
	public class MouseListenerActionBack implements MouseListener {
		
		public MouseListenerActionBack() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			modelCurrentPage.setCurrentPage(Page.HOME);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			IconFontSwing.register(FontAwesome.getIconFont());
			Icon iconBack = IconFontSwing.buildIcon(FontAwesome.ANGLE_LEFT, 80, Color.decode("#e6e6e6"));
			view.actionBack.setIcon(iconBack);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			IconFontSwing.register(FontAwesome.getIconFont());
			Icon iconBack = IconFontSwing.buildIcon(FontAwesome.ANGLE_LEFT, 80, Color.WHITE);			
			view.actionBack.setIcon(iconBack);			
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
	
	public class MediaObserver implements Observer {

		public MediaObserver() {
			
		}
		
		@Override
		public void update(Observable o, Object arg) {
			
			ArrayList<Integer> keyToBeDeleted = new ArrayList<>();
			for(int key : controlerMediaUnitList.keySet()) {
				if(!modelMedia.hasMedia(key)) {
					view.panelMedia.remove(controlerMediaUnitList.get(key).view);
					keyToBeDeleted.add(key);
					
				}
			}
			
			for(int key : keyToBeDeleted) {
				controlerMediaUnitList.remove(key);
			}
			
			view.panelMedia.repaint();
			view.panelMedia.revalidate();
			
			int i = 0;
			for(Media m : modelMedia.getMediaList()) {
				ControlerMediaUnit cmu;
				if(!controlerMediaUnitList.containsKey(m.getId())) {
					cmu = new ControlerMediaUnit(type, api, m, modelMedia);
					controlerMediaUnitList.put(m.getId(), cmu);
					
				}
				else {
					cmu = controlerMediaUnitList.get(m.getId());

				}
				view.panelMedia.add(cmu.view, i);

				IconFontSwing.register(FontAwesome.getIconFont());
				if (!m.isCompleted()) {
					cmu.view.labelState.setText(Messages.RESOURCE_BUNDLE.getString(Constants.NOT_COMPLETED));
					
					Icon icon = IconFontSwing.buildIcon(FontAwesome.TIMES, 25, Color.decode("#d10531"));
					cmu.view.actionState.setIcon(icon);
				}
				else {
					cmu.view.labelState.setText("");
					Icon icon = IconFontSwing.buildIcon(FontAwesome.CHECK, 25, Color.decode("#0dab76"));
					cmu.view.actionState.setIcon(icon);
				}
				
				String day_name = DateUtils.getDayName(m.getRelease_date().get("day_name").getAsInt());
				String day = m.getRelease_date().get("day").getAsString();
				String month = DateUtils.getMonthName(m.getRelease_date().get("month").getAsInt());
				String year = m.getRelease_date().get("year").getAsString();
				String hour = m.getRelease_date().get("hour").getAsString();
				String minute = m.getRelease_date().get("minute").getAsString();
				//String second = m.getRelease_date().get("second").getAsString();
				cmu.view.labelDate.setText(day + " " + month + " " + year );
				cmu.view.labelDate.setToolTipText(day_name +  " à " + hour + "h" + minute);
				
				//System.out.println(m);
				cmu.view.labelTitle.setText(m.getName());
				DecimalFormat format = new DecimalFormat("0.#");
				cmu.view.labelLastViewed.setText(format.format(m.getLast_viewed()));
				cmu.view.ftfLastViewed.setText(format.format(m.getLast_viewed()));
				cmu.view.labelRelease.setText(format.format(m.getRelease_number()));
				i++;
			}
			
			view.repaint();
			view.revalidate();
		}
		
	}
	
	@SuppressWarnings("serial")
	public class ActionAdd extends AbstractAction {
		
		public ActionAdd() {
			this.putValue(NAME, Messages.RESOURCE_BUNDLE.getString(Constants.ADD));
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Media m = api.addMedia(type.toString(), view.tfRssUrl.getText(), modelUsername.getUsername());
			if(m != null) {
				modelMedia.addMedia(m);
			}
			else {
				view.tfRssUrl.setBackground(Color.RED);

				new Timer().schedule( 
				        new java.util.TimerTask() {
				            @Override
				            public void run() {
				            	view.tfRssUrl.setBackground(Color.WHITE);
				            }
				        }, 
				        200
				);
			}
			view.tfRssUrl.setText("");
		}
	}
}
