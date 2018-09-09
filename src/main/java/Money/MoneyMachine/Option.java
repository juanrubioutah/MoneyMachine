package Money.MoneyMachine;
public class Option { //The Option class keeps track of options we own
	String ticker;
	double strikePrice, expiration, pricePaid;
	public Option(String ticker, double strikePrice, double expiration, double pricePaid) {
		this.ticker = ticker;
		this.strikePrice = strikePrice;
		this.expiration = expiration;
		this.pricePaid =  pricePaid;
	}
}
