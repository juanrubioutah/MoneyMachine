package Money.MoneyMachine;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class NewsReader { //Reads news about potential trades and acts accordingly
	
	//TODO: test code
	/*
	public static void main(String args[]) throws IOException {
		NewsReader news = new NewsReader();
		news.read("penguins");
	}
	*/
	
	public NewsReader() {
		
	}

	/**
	 * this method makes a GET request to google with a query string in the URL
	 * then stores the HTML that google returns into a Java DOM
	 * 
	 * @param query
	 * A String to search as the q URL parameter of a google GET request
	 * 
	 * @throws IOException
	 */
	public void read(String query) throws IOException {
		query = query.replace(" ", "+");
		
		//currently this retrieves the HTML google returns when you make a news, safe-search enabled search for String query, then stores it into a Java DOM (Document doc)
		Document doc = Jsoup.connect("https://www.google.com/search"
				+ "?q=" + query
				+ "&tbm=nws"
				+ "&safe=active"
				).get();
		
		//TODO: navigate this to find the news articles
		System.out.println(doc.toString());
	}
}
