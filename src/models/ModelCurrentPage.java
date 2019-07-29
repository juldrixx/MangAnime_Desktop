package models;

import java.util.Observable;

import utils.Page;

public class ModelCurrentPage extends Observable {
	
	Page currentPage;

	public ModelCurrentPage() {
		this.currentPage = Page.LOGIN;
	}
	
	public void setCurrentPage(Page page) {
		this.currentPage = page;
		this.setChanged();
		this.notifyObservers();
	}
	
	public Page getCurrentPage() {
		return this.currentPage;
	}
	
	public void init() {
		this.setChanged();
		this.notifyObservers();
	}
}
