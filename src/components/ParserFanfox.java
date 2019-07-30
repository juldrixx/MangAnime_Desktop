package components;

import java.util.Calendar;
import java.util.regex.Pattern;

import com.google.gson.JsonObject;

import utils.RssFeedReader;

public class ParserFanfox implements Parser {

	private String rss_url;
	private RssFeedReader rfr;

	public ParserFanfox(String rss_url) {
		super();
		this.rss_url = rss_url;
		this.rfr = new RssFeedReader(rss_url);
	}
	
	public Media getInformation() {
		if(this.rfr.isReadable()) {
			String name = this.rfr.getTitle().trim();
			String release_url = this.rfr.getItem_link();
			Calendar date = this.rfr.getItem_date();
						
			JsonObject release_date = new JsonObject();
			release_date.addProperty("day_name", date.get(Calendar.DAY_OF_WEEK));
			release_date.addProperty("day", date.get(Calendar.DAY_OF_MONTH));
			release_date.addProperty("month", date.get(Calendar.MONTH));
			release_date.addProperty("year", date.get(Calendar.YEAR));
			release_date.addProperty("hour", date.get(Calendar.HOUR));
			release_date.addProperty("minute", date.get(Calendar.MINUTE));
			release_date.addProperty("second", date.get(Calendar.SECOND));

			double release_number = Double.parseDouble(this.rfr.getItem_title().split(Pattern.quote("Ch."))[1]);
			//String name_url = this.rss_url.split("/")[this.rss_url.split("/").length - 1].split(".xml")[0];
			String url = release_url.split("/manga/")[0] + "/manga/" + release_url.split("/manga/")[1].split("/")[0] + "/";
			String language = "VUS";
			
			return new Media(name, release_number, release_date, language, this.rss_url, url, release_url);
		}
		
		return null;
	}
}
