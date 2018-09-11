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

	private boolean convertOptionsSuccess = false;
	
	public ArrayList<OptionData> retrieveOptionDataFrom(String url) throws IOException{
		//TODO: implement failure detection
		
		JsonParser json = new JsonParser();
		json.fetchJson(url);
		
		convertOptionsSuccess = convertJsonToOptionData(json);
		
		return optionData;
	}
	
	public boolean convertJsonToOptionData(JsonParser json) {
		//TODO: use the actual JSONObject library instead of my crappier self-made version

		while(json.hasNextAttribute("call_volume")) {
			String strCallVolume = json.getValueOfNextAttribute("call_volume");
			int callVolume = Integer.parseInt(strCallVolume);
			
			String strPutVolume = json.getValueOfNextAttribute("put_volume");
			int putVolume = Integer.parseInt(strPutVolume);
			
			
			String ticker = json.getValueOfNextAttribute("usymbol");
			
			int totalOptions = callVolume + putVolume;
			double percentCalls = (double)callVolume / (double)totalOptions;
			double percentPuts = (double)putVolume / (double)totalOptions;
			OptionData optionDataObject = new OptionData(ticker, percentCalls, percentPuts);
			System.out.println(ticker + " " + percentCalls + " " + percentPuts);
			this.optionData.add(optionDataObject);
		}
		
		//TODO: implement failure detection
		return true;
	}
}
