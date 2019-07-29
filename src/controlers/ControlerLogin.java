package controlers;

import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import i18n.Constants;
import i18n.Messages;
import models.ModelCurrentPage;
import models.ModelUsername;
import utils.Page;
import views.ViewLogin;

public class ControlerLogin {

	public ViewLogin view;
	private ModelUsername modelUsername;
	private ModelCurrentPage modelCurrentPage;
	
	public ControlerLogin(ModelUsername modelUsername, ModelCurrentPage modelCurrentPage) {
		this.modelUsername = modelUsername;
		this.modelCurrentPage = modelCurrentPage;
		this.view = new ViewLogin();
		
		ActionLogin actionLogin = new ActionLogin();
		this.modelUsername.addObserver(actionLogin);
		this.view.buttonLogin.setAction(actionLogin);
		
		ListenerUsername actionUsername = new ListenerUsername();
		this.view.tfUsername.getDocument().addDocumentListener(actionUsername);
		this.view.tfUsername.setText(this.modelUsername.getUsername());
	}
	
	
	@SuppressWarnings("serial")
	public class ActionLogin extends AbstractAction implements Observer {

		public ActionLogin() {
			this.putValue(NAME, Messages.RESOURCE_BUNDLE.getString(Constants.VALIDATE));
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			modelCurrentPage.setCurrentPage(Page.HOME);
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			if(modelUsername.getUsername().isEmpty()){
				this.setEnabled(false);
			}
			else{
				this.setEnabled(true);
			}
		}
	}
	
	public class ListenerUsername implements DocumentListener {
		
		public ListenerUsername() {
			
		}		

		@Override
		public void insertUpdate(DocumentEvent e) {
			modelUsername.setUsername(view.tfUsername.getText());		
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			modelUsername.setUsername(view.tfUsername.getText());
		}


		@Override
		public void changedUpdate(DocumentEvent e) {
			
		}
	}
}
