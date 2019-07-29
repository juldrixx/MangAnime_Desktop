package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Observable;

import components.Media;

public class ModelMedia extends Observable  {
	ArrayList<Media> mediaList;

	public ModelMedia() {
		this.mediaList = new ArrayList<>();
	}
	
	public ModelMedia(ArrayList<Media> mangaList) {
		this.mediaList = mangaList;
	}
	
	public Boolean hasMedia(int id) {
		int index = 0;
		int indexMedia = -1;
		for (Media media : this.mediaList) {
			if (media.getId() == id) {
				indexMedia = index;
				break;
			}
			index += 1;
		}
		
		return indexMedia != -1;
	}
	
	public void removeMedia(Media m) {
		int index = 0;
		int indexMedia = -1;
		for (Media media : this.mediaList) {
			if (media.getId() == m.getId()) {
				indexMedia = index;
				break;
			}
			index += 1;
		}
		
		if(indexMedia != -1) {
			this.mediaList.remove(indexMedia);
			this.setChanged();
			this.notifyObservers();
		}
	}
	
	public void updateMedia(Media m) {
		int index = 0;
		int indexMedia = -1;
		for (Media media : this.mediaList) {
			if (media.getId() == m.getId()) {
				indexMedia = index;
				break;
			}
			index += 1;
		}
		
		if(indexMedia != -1) {
			this.mediaList.set(indexMedia, m);
			this.setChanged();
			this.notifyObservers();
		}
	}
	
	public void addMedia(Media m) {
		
		Boolean alreadyPresent = false;
		for (Media media : this.mediaList) {
			if (media.equals(m)) {
				alreadyPresent = true;
				break;
			}
		}
		
		if(!alreadyPresent) {
			this.mediaList.add(m);
			this.setChanged();
			this.notifyObservers();
		}		
	}

	public ArrayList<Media> getMediaList() {
		Collections.sort(this.mediaList, new Comparator<Media>() {
            public int compare(Media s1, Media s2) {
              return s1.isCompleted().compareTo(s2.isCompleted());
           }
		});

		return this.mediaList;
	}

	public void setMediaList(ArrayList<Media> mediaList) {
		this.mediaList = mediaList;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void init() {
		this.setChanged();
		this.notifyObservers();
	}
}
