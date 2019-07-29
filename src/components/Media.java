package components;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Media {

	private int id;
	private int media_id;
	private double last_viewed;
	private String name;
	private double release_number;
	private JsonObject release_date;
	private String language;
	private String rss_url;
	private String url;
	private String release_url;
	private Boolean not_completed;
	
	public Media(int id, int media_id, double last_viewed, String name, double release_number, String release_date, String language,
			String rss_url, String url, String release_url) {
		super();
		this.id = id;
		this.media_id = media_id;
		this.last_viewed = last_viewed;
		this.name = name;
		this.release_number = release_number;
		this.release_date = new JsonParser().parse(release_date).getAsJsonObject();
		this.language = language;
		this.rss_url = rss_url;
		this.url = url;
		this.release_url = release_url;
		this.not_completed = last_viewed != release_number;
	}
	
	public Media(int id, int media_id, double last_viewed, String name, double release_number, JsonObject release_date, String language,
			String rss_url, String url, String release_url) {
		super();
		this.id = id;
		this.media_id = media_id;
		this.last_viewed = last_viewed;
		this.name = name;
		this.release_number = release_number;
		this.release_date = release_date;
		this.language = language;
		this.rss_url = rss_url;
		this.url = url;
		this.release_url = release_url;
		this.not_completed = last_viewed != release_number;
	}
		
	
	public Media(String name, double release_number, JsonObject release_date, String language,
			String rss_url, String url, String release_url) {
		super();
		this.id = -1;
		this.media_id = -1;
		this.last_viewed = 0;
		this.name = name;
		this.release_number = release_number;
		this.release_date = release_date;
		this.language = language;
		this.rss_url = rss_url;
		this.url = url;
		this.release_url = release_url;
		this.not_completed = false;
	}
	
	@Override
	public String toString() {
		return "Media [id=" + id + ", media_id=" + media_id + ", last_viewed=" + last_viewed + ", name=" + name + ", release_number="
				+ release_number + ", release_date=" + release_date + ", language=" + language + ", rss_url=" + rss_url
				+ ", url=" + url + ", release_url=" + release_url +  ", not_completed=" + not_completed + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		Media other = (Media) obj;
		return 	other.id == this.id && other.last_viewed == this.last_viewed && other.name.equals(this.name) && other.release_number == this.release_number && 
				other.release_date.equals(this.release_date) && other.language.equals(this.language) && other.rss_url.equals(this.rss_url) && other.url.equals(this.url)
				&& other.release_url.equals(this.release_url) && other.not_completed == this.not_completed;
	}

	public int getMedia_id() {
		return this.media_id;
	}

	public Boolean isCompleted() {
		return !this.not_completed;
	}
	
	public int getId() {
		return this.id;
	}

	public double getLast_viewed() {
		return last_viewed;
	}

	public String getName() {
		return name;
	}

	public double getRelease_number() {
		return release_number;
	}

	public JsonObject getRelease_date() {
		return release_date;
	}

	public String getLanguage() {
		return language;
	}

	public String getRss_url() {
		return rss_url;
	}

	public String getUrl() {
		return url;
	}

	public String getRelease_url() {
		return release_url;
	}

	public void setLast_viewed(double last_viewed) {
		this.last_viewed = last_viewed;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRelease_number(double release_number) {
		this.release_number = release_number;
	}

	public void setRelease_date(JsonObject release_date) {
		this.release_date = release_date;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setRss_url(String rss_url) {
		this.rss_url = rss_url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setRelease_url(String release_url) {
		this.release_url = release_url;
	}	
}
