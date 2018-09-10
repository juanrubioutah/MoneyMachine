package Money.MoneyMachine;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;

import org.apache.commons.io.IOUtils;

/**
 * 
 * @author Parker Nilson
 * 
 * JsonParser is an object that can fetch JSON content from the internet
 * and then return values of attributes in that JSON object
 *
 */
public class JsonParser {
	
	private String jsonString; //contains the string of json to parse
	private int curIndex; //keeps track of where the cursor is in the string
	
	private boolean ready = false; //if the object is ready to start parsing the jsonString string (has it been loaded?)
	
	public JsonParser() {

	}
	
	public boolean fetchJson(String url) throws IOException {
		//open connection and grab response
		URL u = new URL(url);
		URLConnection con = u.openConnection();
		InputStream in = con.getInputStream();
		String encoding = con.getContentEncoding();
		encoding = encoding == null ? "UTF-8" : encoding;
		
		//set the object's json string
		jsonString = IOUtils.toString(in, encoding);
		
		//TODO: implement failure detection
		return true;
	}
	
	public boolean hasNextAttribute(String attribute) {
		if(jsonString.indexOf("\"" + attribute + "\":", curIndex + 1) > -1) {
			return true;
		}
		
		return false;
	}
	
	public String getValueOfNextAttribute(String attribute) {
		String _attribute = "\"" + attribute + "\":"; // turn it from a regular string to a string of a string (attribute -> "attribute":)
		if(jsonString.indexOf(_attribute, curIndex + 1) > -1) {
			curIndex = jsonString.indexOf(_attribute, curIndex + 1) + _attribute.length();
			return jsonString.substring(curIndex, jsonString.indexOf(',', curIndex + 1));
		}else {
			return "";
		}
	}
	
}
