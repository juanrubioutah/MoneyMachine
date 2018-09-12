package parker.nilson.littlejohn.api;

import java.io.IOException;

import org.json.*;

import com.goebl.david.*;

import parker.nilson.littlejohn.throwables.LittleJohnCouldNotLogInException;
import parker.nilson.littlejohn.throwables.LittleJohnCouldNotRetrieveAccountInfoException;

/**
 * 
 * @author Parker Nilson
 * 
 * The Little John API is a client API for managing stocks
 * on Robinhood.com
 *
 */
public class LittleJohnApi {
	
	Webb webb; //the web client
	
	private Account account;
	
	private boolean connected = false; //true when the client has logged in and obtained a valid auth_token


	public LittleJohnApi() throws IOException {
		
		webb = Webb.create();
		
	}
	
	public void logIn(String user, String pass) throws IOException, LittleJohnCouldNotLogInException{
		//make log in request
		Response<JSONObject> response = webb
				.post(Endpoints.logInURL)
				.param("username", user)
				.param("password", pass)
				.param("grant_type", "password")
				.param("scope", "internal")
				.param("client_id", "c82SH0WZOsabOXGP2sxqcj34FxkvfnWRZBKlBjFS")
				.asJsonObject();
		
		//collect the response
		JSONObject body = response.getBody();
		//auth_token = body.getString("access_token"); //store the auth_token for future authorized requests
		
		if(response.isSuccess()) {
			System.out.println("LittleJohn Client signed in successfully for user: " + user);
			connected = true;
		}else{
			//HTTP response code
			int status = response.getStatusCode();
			
			throw new LittleJohnCouldNotLogInException(status);
		}
		
		//create account object from collected information
		String auth_token = body.getString("access_token");
		account = new Account(user, pass, auth_token, webb);
		try {
			account.retrieveAccountInfo();
		} catch (LittleJohnCouldNotRetrieveAccountInfoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
