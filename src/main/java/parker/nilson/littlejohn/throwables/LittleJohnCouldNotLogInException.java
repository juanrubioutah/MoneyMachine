package parker.nilson.littlejohn.throwables;

import parker.nilson.littlejohn.api.Endpoints;

@SuppressWarnings("serial")
public class LittleJohnCouldNotLogInException extends Exception{
	
	public String error = "There was a problem logging in to Robinhood with the Little John API";
	public int httpStatusCode;
	
	public LittleJohnCouldNotLogInException(int code) {
		this.httpStatusCode = code;
	}
	
	public LittleJohnCouldNotLogInException(int code, String error) {
		this.httpStatusCode = code;
		this.error = error;
	}
	
	private void setError(int code) {
		this.error = "Little John API: HTTP Error Code (" + code + ") ";
		
		if(code == 400) {
			this.error += "Robinhood could not log in with the provided credentials.";
		}
		else if(code == 404) {
			this.error += "The Log In url: " + Endpoints.logInURL + " could not be found.";
		}
		else if(code == 500) {
			this.error += "Robinhood experienced an internal server error and denied the request.";
		}
		else if(code == 503){
			this.error += "The Robinhood servers are currently unavailable.";
		}
		else {
			this.error += "There was a problem logging in to Robinhood.";
		}
	}
	
	@Override
	public String getMessage() {
		return this.error;
	}
	
	public int getHttpStatusCode() {
		return this.httpStatusCode;
	}

}
