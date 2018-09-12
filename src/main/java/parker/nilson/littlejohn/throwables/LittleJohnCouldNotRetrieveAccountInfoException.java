package parker.nilson.littlejohn.throwables;

@SuppressWarnings("serial")
public class LittleJohnCouldNotRetrieveAccountInfoException extends Exception {
	
	String error = "Little John API: Error: ";
	
	public LittleJohnCouldNotRetrieveAccountInfoException(String error) {
		this.error += error;
	}
	
	@Override
	public String getMessage() {
		return this.error;
	}

}
