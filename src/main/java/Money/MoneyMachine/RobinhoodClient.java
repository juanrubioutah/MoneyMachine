package Money.MoneyMachine;

import java.io.BufferedReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RobinhoodClient {
	
	String auth_token; //auth_token for the session
	private String robinhoodURL = "https://api.robinhood.com/oauth2/token/";
	
	public static void main(String args[]) throws IOException {
		RobinhoodClient cli = new RobinhoodClient();
		cli.logIn("test", "test");
	}

	public RobinhoodClient() throws IOException {

	}
	
	public boolean logIn(String user, String pass) throws IOException {
		//post a request
		try {
			HttpResponse<String> jsonResponse = Unirest.post(robinhoodURL)
					.header("content-type", "application/json")
					.field("client_id", "c82SH0WZOsabOXGP2sxqcj34FxkvfnWRZBKlBjFS")
					.field("expires_in", "86400")
					.field("password", "Ch!spas159896")
					.field("scope", "internal")
					.field("username", "juan.r896@slcstudents.org")
					.asString();
			
			System.out.println(jsonResponse.getBody().toString());

			//JSONObject response = jsonResponse.getBody().getObject();
			//System.out.println(response.toString());

		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			System.out.println("There was an error with the Log In HTTP request");
			e.printStackTrace();
		}
		
		
		
		return false;
	}

}
