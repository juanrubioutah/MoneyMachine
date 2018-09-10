package Money.MoneyMachine;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

public class NewsReader { //Reads news about potential trades and acts accordingly
	
	public String jsonString;
	
	public NewsReader() {
		
	}
	public void read(String query) {
		query = query.replace(" ", "+");
		try {
			retrieveJSON("http://www.google.com/search?q="+query+"&safe=strict&source=lnms&tbm=nws");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonString);
	}
	public void retrieveJSON(String url) throws IOException {
		URL u = new URL(url);
		URLConnection con = u.openConnection();
		InputStream in = con.getInputStream();
		String encoding = con.getContentEncoding();
		encoding = encoding == null ? "UTF-8" : encoding;
		String body = IOUtils.toString(in, encoding);
		//System.out.println(body);
		
		jsonString = body;
	}
}
