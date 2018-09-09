package Money.MoneyMachine;
public class Option { //The Option class keeps track of options we own
	String ticker;
	double strikePrice, expiration, pricePaid, value;
	boolean upSinceLastTick;
	double changeSinceLastTick;
	public Option(String ticker, double strikePrice, int expirationDays, double pricePaid, double value) {
		this.ticker = ticker;
		this.strikePrice = strikePrice;
		this.expiration = expiration;
		this.pricePaid =  pricePaid;
		this.value = value;
	}
}
