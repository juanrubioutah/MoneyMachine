package parker.nilson.littlejohn.api;

import org.json.JSONObject;

import com.goebl.david.Response;
import com.goebl.david.Webb;

public class Fundamentals {
	JSONObject jsonFundamentals;
	
	public Fundamentals(String ticker, Webb webb) {
		Response<JSONObject> response = webb
				.get(Endpoints.robinhoodStockFundamentalsURL + ticker + "/")
				.asJsonObject();
		
		if(response.isSuccess()) {
			jsonFundamentals = response.getBody();
		}else {
			//TODO: throw LittleJohnHTTPException
		}
	}
	
}
