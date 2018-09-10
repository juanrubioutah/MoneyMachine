package Money.MoneyMachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.JsonObject;

public class RobinhoodClient {
	
	String auth_token; //auth_token for the session
	private String robinhoodURL = "https://api.robinhood.com/oauth2/token/";
	private URL url;  
	private HttpsURLConnection con;
	private int responseCode; //HTTPS response code
	
	/* TODO: test code
	public static void main(String args[]) throws IOException {
		RobinhoodClient cli = new RobinhoodClient();
		cli.logIn("test", "test");
	}
	*/

	public RobinhoodClient() throws IOException {
		url = new URL(robinhoodURL);
		con = (HttpsURLConnection)url.openConnection();
		
		responseCode = con.getResponseCode();
		System.out.println("HTTPS Response: " + responseCode);
	}
	
	public boolean logIn(String user, String pass) throws IOException {
		con.setRequestMethod("POST");
		con.connect();
		con.setRequestProperty("Content-Type", "application/json");
		OutputStream os = con.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		osw.write("{"
				+ "\"client_id\":\"c82SH0WZOsabOXGP2sxqcj34FxkvfnWRZBKlBjFS\","
				+ "\"expires_in\":\"86400\","
				+ "\"grant_type\":\"password\","
				+ "\"password\":\"Ch!spas159896\","
				+ "\"scope\":\"password\","
				+ "\"username\":\"juan.r896@slcstudents.org\""
				+ "}");
		osw.flush();
		osw.close();
		os.close();
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
		String line = null;
		while((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}
		br.close();
		System.out.println("" + sb.toString());
		
		return false;
	}

}
