package parker.nilson.littlejohn.api;

import java.io.IOException;

import org.json.*;

import com.goebl.david.*;

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
	
	private boolean connected = false; //true when the client has logged in and obtained a valid auth_token
	private String auth_token; //auth_token for the session
	private JSONObject userInfo; //userInfo for the session
	


	public LittleJohnApi() throws IOException {
		
		webb = Webb.create();
		
	}
	
}
