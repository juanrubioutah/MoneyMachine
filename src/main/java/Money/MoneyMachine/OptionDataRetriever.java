package Money.MoneyMachine;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;

/**
 * 
 * @author Parker Nilson
 * 
 * The GetOptionData class contains an arraylist of OptionData objects
 * and methods for obtaining that data from the internet.
 *
 */
public class OptionDataRetriever {
	
	public static ArrayList<OptionData> optionData = new ArrayList<OptionData>();
	private String jsonString;
	
	public ArrayList<OptionData> retrieveOptionDataFrom(String url) throws IOException{
		//TODO: implement failure detection
		retrieveJSON(url);
		if(convertJsonToOptionData(jsonString)) {
			return optionData;
		}
	
		return null;
	}
	
	public boolean convertJsonToOptionData(String jsonString) {
		int curIndex = 0;

		//run until there are no more occurences of "avg_total_calls", which means that there are no more options left in the jason array
		while(jsonString.indexOf("\"call_volume\":", curIndex + 1) > -1) {
			//convert the string version of the json array of options objects into OptionData objects and insert them into the optionData ArrayList
			curIndex = jsonString.indexOf("\"call_volume\":", curIndex + 1); 
			int valueIndex = curIndex + "\"call_volume\":".length();
			String strAvgTotalCalls = jsonString.substring(valueIndex, jsonString.indexOf(',', valueIndex));
			int AvgTotalCalls = Integer.parseInt(strAvgTotalCalls);

			curIndex = jsonString.indexOf("\"put_volume\":", curIndex);
			valueIndex = curIndex + "\"put_volume\":".length();
			String strAvgTotalPuts = jsonString.substring(valueIndex, jsonString.indexOf(',', valueIndex));
			int AvgTotalPuts = Integer.parseInt(strAvgTotalPuts);
			
			curIndex = jsonString.indexOf("\"usymbol\":\"", curIndex);
			valueIndex = curIndex + "\"usymbol\":\"".length();
			String symbol = jsonString.substring(valueIndex, jsonString.indexOf("\"", valueIndex));
			
			int avgTotalOptions = AvgTotalCalls + AvgTotalPuts;
			double percentCalls = (double)AvgTotalCalls / (double)avgTotalOptions;
			double percentPuts = (double)AvgTotalPuts / (double)avgTotalOptions;
			OptionData optionDataObject = new OptionData(symbol, percentCalls, percentPuts);
			System.out.println(symbol + " " + percentCalls + " " + percentPuts);
			this.optionData.add(optionDataObject);
		}
		
		//TODO: implement failure detection
		return true;
	}
	
	/**
	 * 
	 * 
	 * @param url
	 * A string URL to collect JSON from.
	 * 
	 * @throws IOException
	 */
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
