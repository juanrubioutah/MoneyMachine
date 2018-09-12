
package Money.MoneyMachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.goebl.david.Response;
import com.goebl.david.Webb;

/**
 * 
 * @author Parker Nilson
 * 
 * RobinhoodClient acts as an http client for the website Robinhood.com
 * It contains methods to log in, where it obtains an authorization token
 * for future authorized requests.
 * It contains functions for performing certain tasks, such as navigating the account,
 * viewing information, and purchasing / selling stocks, etc.
 * (To be implemented)
 *
 */
public class RobinhoodClient {
	
	Webb webb; //the web client 
	
	private boolean connected = false; //true when the client is logged in and has obtained a valid auth_token
	private String auth_token; //auth_token for the session
	private JSONObject userInfo;
	
	private String robinhoodURL = "https://api.robinhood.com/oauth2/token/";
	private String testURL = "https://jsonplaceholder.typicode.com/posts";
	private String robinhoodAccountsURL = "https://api.robinhood.com/accounts/";
	private String robinhoodStockFundamentalsURL = "https://api.robinhood.com/fundamentals/";
	private String robinhoodOptionsInstrumentsURL = "https://api.robinhood.com/options/instruments/";
	
	int buyingPower;
	
	public RobinhoodClient() throws IOException {

		webb = Webb.create(); //create the HTTP client

	}
	
	/**
	 * logIn posts the username and password to the robinhood OAuth2 api and receives an authorization token if successful
	 * 
	 * @param user
	 * A string username for a robinhood account.
	 * @param pass
	 * A string password for a robinhood account.
	 * @return
	 * returns true if login was successful.
	 * @throws IOException
	 */
	public boolean logIn(String user, String pass) throws IOException {
		
		Response<JSONObject> response = webb
				.post(robinhoodURL)
				.param("username", user)
				.param("password", pass)
				.param("grant_type", "password")
				.param("scope", "internal")
				.param("client_id", "c82SH0WZOsabOXGP2sxqcj34FxkvfnWRZBKlBjFS")
				.asJsonObject();
		
		//print the response
		JSONObject body = response.getBody();
		auth_token = body.getString("access_token"); //store the auth_token for future authorized requests
		//TODO: research how the expires_in response parameter works, then implement detection for when it expires
		//			I suspect that it tells what time of day the token will reset

		if(response.isSuccess()) {
			System.out.println("Robinhood Client signed in successfully for user: " + user);
			connected = true;
			
			return true;
			/*
			System.out.println("The request was successful\n");
			System.out.println(response.getBody().toString());
			*/
		}else {
			System.out.println("The log in request to " + robinhoodURL + " was unsuccessful\n");
			System.out.println(response.getStatusCode());
			System.out.println(response.getResponseMessage());
			System.out.println(response.getErrorBody());
		}

		return false;
	}
	
	public boolean loadUserInfo() {
		Response<JSONObject> response = webb
				.get(robinhoodAccountsURL)
				.header("authorization", "Bearer " + auth_token)
				.asJsonObject();
		
		userInfo = (JSONObject)response.getBody().getJSONArray("results").get(0);
		System.out.println("Acct #: " + userInfo.get("account_number"));
		System.out.println("Buying Power: " + userInfo.get("buying_power"));
		//System.out.println(userInfo.toString());
		
		return false;
	}
	
	public JSONObject getStockFundamentals(String ticker) {

		String url = robinhoodStockFundamentalsURL + ticker + "/";
		Response<JSONObject> response = webb
				.get(url)
				.asJsonObject();
		
		if(response.isSuccess()) {
			return response.getBody();
		}else {
			System.out.println("There was a problem collecting stock fundamentals from url: " + url);
		}

		return null;
	}
	
	public JSONObject getStockInstruments(String ticker) {
		JSONObject fundamentals = this.getStockFundamentals(ticker);
		
		String instrumentURL = (String)fundamentals.get("instrument");
		Response<JSONObject> response = webb
				.get(instrumentURL)
				.asJsonObject();
		
		if(response.isSuccess()) {
			return response.getBody();
		}else {
			System.out.println("There was a problem retrieving the Stock Instruments from URL: " + instrumentURL);
			return null;
		}
	}
	
	public JSONArray getStockOptions(String ticker) {
		JSONObject instruments = this.getStockInstruments(ticker);
		String chainIdURL = robinhoodOptionsInstrumentsURL 
							+ "?chain_id=" + instruments.get("tradable_chain_id")
							+ "&expiration_dates=2018-09-14"
							+ "&state=active"
							+ "&tradability=tradable"
							+ "&type=call";
		Response<JSONObject> response = webb
				.get(chainIdURL)
				.asJsonObject();
		
		if(response.isSuccess()) {
			System.out.println("Retrieved options from URL: " + chainIdURL);
			JSONArray optionsArray = response.getBody().getJSONArray("results");

			return optionsArray;
		}else {
			System.out.println("There was a problem retrieving the stock options from URL: " + chainIdURL);
			return null;
		}
	}

}