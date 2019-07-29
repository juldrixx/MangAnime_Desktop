package components;

import java.text.DecimalFormat;
import java.util.Calendar;
import com.google.gson.JsonObject;

import utils.RssFeedReader;

public class ParserJetanime implements Parser {

	private String rss_url;
	private RssFeedReader rfr;

	public ParserJetanime(String rss_url) {
		super();
		this.rss_url = rss_url;
		this.rfr = new RssFeedReader(rss_url);
	}
	
	@Override
	public Media getInformation() {
		if(this.rfr.isReadable()) {
			String title = this.rfr.getItem_title();
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
			
			double release_number = Double.parseDouble(title.split(" ")[title.split(" ").length - 2]);
			//String name_url = this.rss_url.split("/")[this.rss_url.split("/").length - 2];
			String url = this.rss_url.replaceAll("rss", "anime");
			String language = title.split(" ")[title.split(" ").length - 1];
			DecimalFormat format = new DecimalFormat("0.#");
			String name = title.replaceAll(format.format(release_number), "").replaceAll(language, "").replaceAll("Episode", "").trim();
			
			return new Media(name, release_number, release_date, language, this.rss_url, url, release_url);
		}
		
		return null;
	}

}
