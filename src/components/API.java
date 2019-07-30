package components;

import java.util.ArrayList;
import java.util.regex.Pattern;

import utils.DatabaseManager;

public class API {

	private DatabaseManager dbm;
	
	public API() {
		this.dbm = new DatabaseManager();
	}
	
	public ArrayList<Media> getMedia(String name_type, String username) {
		ArrayList<Media> mediaList = new ArrayList<>();
		
		if(this.testUser(username)) {
			ArrayList<Media> mediaListDB = dbm.getMedia(username, name_type);
			
			for(Media media : mediaListDB) {
				mediaList.add(this.updateMediaGet(media));
			}
		}
		
		return mediaList;
	}
	
	public Media updateMedia(int id_medialist, double value) {
		Media m = null;
		
		if(this.dbm.updateMediaList(id_medialist, value)) {
			return this.dbm.getMediaByIdMediaList(id_medialist);
		}
		
		return m;
	}
	
	public Media addMedia(String type, String rss_url, String username) {
		Media m = null;
		Parser parser = null;
		
		if(this.testUser(username)) {
			String site = "";
			try {
				String[] url_splitted = rss_url.split(Pattern.quote("."));
				site = url_splitted[0].contains("www") ? url_splitted[1] : url_splitted[0].split("://")[1];
			}
			catch(Exception e) {
				return m;
			}
			
			switch (site) {
			case "fanfox":			
				parser = new ParserFanfox(rss_url);
				break;
			case "jetanime":			
				parser = new ParserJetanime(rss_url);
				break;
			default:
				return m;
			}
			
			Media info = parser.getInformation();
			
			int id_media = this.dbm.mediaExist(rss_url);
			if(id_media != -1) {
				if(this.dbm.updateTypeMedia(rss_url, type)) {
					if(!this.dbm.followMediaList(id_media)) {
						return m;
					}
				}
				else {
					return m;
				}
			}
			else {
				if(!this.dbm.insertMedia(type, info)) {
					return m;
				}
			}
			
			if(!this.dbm.testMediaList(rss_url, username)) {
				if(!this.dbm.insertMediaList(rss_url, username)){
					return m;
				}
			}
	
			ArrayList<Media> mediaList = this.dbm.getMedia(username, type);
			for(Media med : mediaList) {
				if(med.getRss_url().equals(rss_url)) {
					return med;
				}
			}
		}

		return m;
	}
	
	public Boolean unfollowMedia(int id_medialist) {
		return this.dbm.unfollowMediaList(id_medialist);
	}
	
	private Media updateMediaGet(Media m) {
		String[] url_splitted = m.getRss_url().split(Pattern.quote("."));
		String site = url_splitted[0].contains("www") ? url_splitted[1] : url_splitted[0].split("://")[1];

		Parser parser = null;
		
		switch (site) {
		case "fanfox":			
			parser = new ParserFanfox(m.getRss_url());
			break;
		case "jetanime":			
			parser = new ParserJetanime(m.getRss_url());
			break;
		default:
			return m;
		}
		
		Media info = parser.getInformation();
		if (info != null) {
			Media media = new Media(m.getId(), m.getMedia_id(), m.getLast_viewed(), info.getName(), info.getRelease_number(), info.getRelease_date(), info.getLanguage(), m.getRss_url(), info.getUrl(), info.getRelease_url());
			if(this.dbm.updateMedia(media)) {
				return media;
			}
		}
		
		return m;
	}
	
	private Boolean testUser(String username) {
		if(!dbm.userExist(username)) {
			return dbm.insertUser(username);
		}
		return true;
	}
}
