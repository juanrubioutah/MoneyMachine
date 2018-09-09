package Money.MoneyMachine;

import java.io.IOException;
import java.util.ArrayList;

public class Machine {
	public static String customTableEquitiesURL = "https://cdn.optionseducation.org/rest/customtableitem.customtable.OICTradeAlertEquity?hash=8668d92358b2cb481080a7da3d75b9b8e7c4a2bee9768160655cc20e45afb105&format=json";
	
	public static void main(String args[]) throws IOException {
		OptionDataRetriever retriever = new OptionDataRetriever();
		ArrayList<OptionData> options = retriever.retrieveOptionDataFrom(customTableEquitiesURL);
		TradeDeterminer determiner = new TradeDeterminer(options);
	}

}
