package parker.nilson.littlejohn.api;

import org.json.JSONObject;

import com.goebl.david.Response;
import com.goebl.david.Webb;

import parker.nilson.littlejohn.throwables.LittleJohnCouldNotRetrieveAccountInfoException;

public class Account {
	
	Webb webb;
	
	private String auth_token;
	private String username;
	private String password;
	private JSONObject userInfo;

	public Account(String username, String password, String auth_token, Webb webb) {
		this.username = username;
		this.password = password;
		this.auth_token = auth_token;
		this.webb = webb;
	}
	
	public void retrieveAccountInfo() throws LittleJohnCouldNotRetrieveAccountInfoException {
		Response<JSONObject> response = webb
				.get(Endpoints.robinhoodAccountsURL)
				.header("authorization", "Bearer " + auth_token)
				.asJsonObject();
		
		if(response.isSuccess()) {
			this.userInfo = (JSONObject)response.getBody().getJSONArray("results").get(0);
		}else {
			String error = "Could not retrieve account information";
			throw new LittleJohnCouldNotRetrieveAccountInfoException(error);
		}
	}
}
