
public class OptionData { //OptionData tracks the data we know about options traded by others
	String ticker;
	double callPercent, putPercent;
	public OptionData(String ticker, double callPercent, double putPercent) {
		this.ticker = ticker;
		this.callPercent = callPercent;
		this.putPercent = putPercent;
	}
}
