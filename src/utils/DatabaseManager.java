package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import components.Media;

public class DatabaseManager {
	private Boolean isConnected;
	private Connection connect;
	private PreparedStatement userExistPS;
	private PreparedStatement insertUserPS;
	private PreparedStatement getMediaPS;
	private PreparedStatement updateMediaPS;
	private PreparedStatement updateTypeMediaPS;
	private PreparedStatement insertMediaPS;
	private PreparedStatement mediaExistPS;
	private PreparedStatement updateMediaListPS;
	private PreparedStatement insertMediaListPS;
	private PreparedStatement testMediaListPS;
	private PreparedStatement unfollowMediaListPS;
	private PreparedStatement followMediaListByIdMediaPS;
	private PreparedStatement getMediaByIdMediaListPS;
	
	public DatabaseManager() {
		this.isConnected = true;
		try {
			this.connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/manganime?serverTimezone=UTC","root","");
			this.userExistPS = this.connect.prepareStatement("SELECT * FROM USER WHERE username = ?;");
			this.insertUserPS = this.connect.prepareStatement("INSERT INTO USER (username) VALUES (?);");
			this.getMediaPS = this.connect.prepareStatement("SELECT m.id as id, ml.id as media_id, ml.last_viewed, m.name, m.release_number, m.release_date, m.language, m.rss_url, m.url, m.release_url FROM user u JOIN medialist ml on u.id = ml.user_id JOIN media m on ml.Media_id = m.id JOIN typemedia tm on tm.id = m.TypeMedia_id WHERE u.username = ? and tm.name_type = ? and ml.followed = 1;");
			this.updateMediaPS = this.connect.prepareStatement("UPDATE media SET name = ?, release_number = ?, release_date = ?, language = ?, url = ?, release_url = ? WHERE id = ?;");
			this.updateTypeMediaPS = this.connect.prepareStatement("UPDATE media SET typemedia_id = (SELECT id FROM typemedia WHERE name_type = ?) WHERE rss_url = ?;");
			this.insertMediaPS = this.connect.prepareStatement("INSERT INTO media (typemedia_id, name, rss_url, release_number, release_date, language, url, release_url) VALUES ((SELECT id FROM typemedia WHERE name_type = ?), ?, ?, ?, ?, ?, ?, ?);");
			this.mediaExistPS = this.connect.prepareStatement("SELECT id FROM media WHERE rss_url = ?;");
			this.updateMediaListPS = this.connect.prepareStatement("UPDATE medialist SET last_viewed = ? WHERE id = ?;");
			this.insertMediaListPS = this.connect.prepareStatement("INSERT INTO medialist (media_id, user_id) VALUES ((SELECT id FROM media WHERE rss_url = ?), (SELECT id FROM user WHERE username = ?));");
			this.testMediaListPS = this.connect.prepareStatement("SELECT * FROM medialist WHERE media_id = (SELECT id FROM media WHERE rss_url = ?) AND user_id = (SELECT id FROM user WHERE username = ?)");
			this.unfollowMediaListPS = this.connect.prepareStatement("UPDATE medialist SET followed = 0 where id = ?;");
			this.followMediaListByIdMediaPS = this.connect.prepareStatement("UPDATE medialist SET followed = 1 where media_id = ?;");
			this.getMediaByIdMediaListPS = this.connect.prepareStatement("SELECT m.id as id, ml.id as media_id, ml.last_viewed, m.name, m.release_number, m.release_date, m.language, m.rss_url, m.url, m.release_url FROM medialist ml JOIN media m on ml.Media_id = m.id WHERE ml.id = ? and ml.followed = 1;");

		} catch (SQLException e) {
			System.out.println(e);
			this.isConnected = false;
		}
	}

	public Boolean userExist(String username) {		
		if (!this.isConnected) {return false;}
		
		try {
			this.userExistPS.setString(1, username);
			ResultSet rs = this.userExistPS.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}
	
	public Boolean insertUser(String username) {		
		if (!this.isConnected) {return false;}
		
		try {
			this.insertUserPS.setString(1, username);
			int i = this.insertUserPS.executeUpdate();
			return i != 0;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}
	
	public ArrayList<Media> getMedia(String username, String name_type) {		
		ArrayList<Media> listMedia = new ArrayList<>();

		if (!this.isConnected) {return listMedia;}
		
		try {			
			this.getMediaPS.setString(1, username);
			this.getMediaPS.setString(2, name_type);
			ResultSet rs = this.getMediaPS.executeQuery();
			while (rs.next()) {
				Media media = new Media(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(4), rs.getDouble(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
				listMedia.add(media);
			}
			return listMedia;
		} catch (SQLException e) {
			System.out.println(e);
			return listMedia;
		}
	}
	
	public Boolean updateMedia(Media media) {		
		if (!this.isConnected) {return false;}
		try {
			this.updateMediaPS.setString(1, media.getName());
			this.updateMediaPS.setDouble(2, media.getRelease_number());
			this.updateMediaPS.setString(3, media.getRelease_date().toString());
			this.updateMediaPS.setString(4, media.getLanguage());
			this.updateMediaPS.setString(5, media.getUrl());
			this.updateMediaPS.setString(6, media.getRelease_url());
			this.updateMediaPS.setInt(7, media.getId());
			int i = this.updateMediaPS.executeUpdate();
			return i != 0;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}

	public Boolean updateTypeMedia(String rss_url, String type) {		
		if (!this.isConnected) {return false;}
		
		try {
			this.updateTypeMediaPS.setString(1, type);
			this.updateTypeMediaPS.setString(2, rss_url);
			
			int i = this.updateTypeMediaPS.executeUpdate();
			return i != 0;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}
	
	public Boolean insertMedia(String name_type, Media media) {		
		if (!this.isConnected) {return false;}
		
		try {
			this.insertMediaPS.setString(1, name_type);
			this.insertMediaPS.setString(2, media.getName());
			this.insertMediaPS.setString(3, media.getRss_url());
			this.insertMediaPS.setDouble(4, media.getRelease_number());
			this.insertMediaPS.setString(5, media.getRelease_date().toString());
			this.insertMediaPS.setString(6, media.getLanguage());
			this.insertMediaPS.setString(7, media.getUrl());
			this.insertMediaPS.setString(8, media.getRelease_url());
			
			int i = this.insertMediaPS.executeUpdate();
			return i != 0;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}
	
	public int mediaExist(String rss_url) {		
		if (!this.isConnected) {return -1;}
		
		try {
			this.mediaExistPS.setString(1, rss_url);
			ResultSet rs = this.mediaExistPS.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			else {
				return -1;
			}
		} catch (SQLException e) {
			System.out.println(e);
			return -1;
		}
	}
	
	public Boolean updateMediaList(int id_medialist, double value) {		
		if (!this.isConnected) {return false;}
		
		try {
			this.updateMediaListPS.setDouble(1, value);
			this.updateMediaListPS.setInt(2, id_medialist);
			
			int i = this.updateMediaListPS.executeUpdate();
			return i != 0;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}
	
	public Boolean insertMediaList(String rss_url, String username) {		
		if (!this.isConnected) {return false;}
		
		try {
			this.insertMediaListPS.setString(1, rss_url);
			this.insertMediaListPS.setString(2, username);
			
			int i = this.insertMediaListPS.executeUpdate();
			return i != 0;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}
	
	public Boolean testMediaList(String rss_url, String username) {		
		if (!this.isConnected) {return false;}
		
		try {
			this.testMediaListPS.setString(1, rss_url);
			this.testMediaListPS.setString(2, username);
			
			ResultSet rs = this.testMediaListPS.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}
	
	public Boolean unfollowMediaList(int id_medialist) {		
		if (!this.isConnected) {return false;}
		
		try {
			this.unfollowMediaListPS.setInt(1, id_medialist);
			
			int i = this.unfollowMediaListPS.executeUpdate();
			return i != 0;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}
	
	public Boolean followMediaList(int id_media) {		
		if (!this.isConnected) {return false;}
		
		try {
			this.followMediaListByIdMediaPS.setInt(1, id_media);
			
			int i = this.followMediaListByIdMediaPS.executeUpdate();
			return i != 0;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}
	
	public Media getMediaByIdMediaList(int id_medialist) {		
		Media media = null;

		if (!this.isConnected) {return media;}
		
		try {			
			this.getMediaByIdMediaListPS.setInt(1, id_medialist);
			ResultSet rs = this.getMediaByIdMediaListPS.executeQuery();
			while (rs.next()) {
				media = new Media(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(4), rs.getDouble(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
			}
			return media;
		} catch (SQLException e) {
			System.out.println(e);
			return media;
		}
	}
}
