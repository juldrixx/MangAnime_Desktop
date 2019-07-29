package utils;

import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class RssFeedReader {

	String rssUrl;
	String title;
	String item_title;
	String item_link;
	Calendar item_date = Calendar.getInstance();
	Boolean readSuccessful;
	
	@SuppressWarnings("unchecked")
	public RssFeedReader(String rssUrl) {
		this.rssUrl = rssUrl;
		try {
            URLConnection feedUrl = new URL(this.rssUrl).openConnection();
            feedUrl.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));
 
            this.title = feed.getTitle();
 
            SyndEntry entry = ((List<SyndEntry>) feed.getEntries()).get(0);
            this.item_title = entry.getTitle();
            this.item_link = entry.getLink();
            this.item_date.setTime(entry.getPublishedDate());
            this.readSuccessful = true;
        } catch (Exception ex) {
        	this.title = "";
            this.item_title = "";
            this.item_link = "";
            this.readSuccessful = false;
        }
	}
	
	public Boolean isReadable() {
		return this.readSuccessful;
	}
	
	public String getRssUrl() {
		return this.rssUrl;
	}
	
	public String getTitle() {
		return this.title;
	}

	public String getItem_title() {
		return this.item_title;
	}

	public String getItem_link() {
		return this.item_link;
	}

	public Calendar getItem_date() {
		return this.item_date;
	}	
}
