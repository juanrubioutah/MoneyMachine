package Money.MoneyMachine;

import java.io.IOException;
import java.util.Scanner;

import conrad.weiser.robinhood.api.RobinhoodApi;
import conrad.weiser.robinhood.api.throwables.RobinhoodApiException;
import conrad.weiser.robinhood.api.throwables.RobinhoodNotLoggedInException;

import java.util.ArrayList;

public class Machine {
	public static String customTableEquitiesURL = "https://cdn.optionseducation.org/rest/customtableitem.customtable.OICTradeAlertEquity?hash=8668d92358b2cb481080a7da3d75b9b8e7c4a2bee9768160655cc20e45afb105&format=json";
	
	
	
	public static void main(String args[]) {
		System.out.println("Enter Username: ");
		Scanner nameReader = new Scanner(System.in);
		String name = nameReader.next();
		System.out.println("Enter Password: ");
		Scanner passReader = new Scanner(System.in);
		String pass = passReader.next();
		try {
			RobinhoodApi api = new RobinhoodApi();
			api.logUserIn(name, pass);
			System.out.println("Success! Logged into account "+api.getAccountData().getAccountNumber());
			System.out.println("Current Buying Power: "+api.getAccountData().getBuyingPower());
		} catch (RobinhoodApiException e) {//TODO: handle exceptions individually
			System.out.println("ERROR: Api Exception");
			e.printStackTrace();
		} catch (RobinhoodNotLoggedInException f) {
			System.out.println("ERROR: Not logged in exception");
			f.printStackTrace();
		}
		System.out.println("Enter a tick time (in seconds): ");
		Scanner tickReader = new Scanner(System.in);
		int tickTime = tickReader.nextInt();
		long tick = (long)tickTime*1000;
		Ticker ticker = new Ticker(tick);
	}
	public void retrieve() throws IOException{
		OptionDataRetriever retriever = new OptionDataRetriever();
		ArrayList<OptionData> options = retriever.retrieveOptionDataFrom(customTableEquitiesURL);
		
		//TODO: the outputs from TradeDeterminer are not working
		TradeDeterminer determiner = new TradeDeterminer(options);
	}

}
