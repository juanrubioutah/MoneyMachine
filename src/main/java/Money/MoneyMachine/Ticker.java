package Money.MoneyMachine;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;

public class Ticker {
	
	public Machine machine = new Machine();
	
	public Ticker(long msTick) {
		do {
			tick();
			try {
				Thread.sleep(msTick);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}while(true);
	}
	public void tick() {
		if(marketsOpen()) {
			System.out.println("The markets are currently OPEN!");
		}
		else {
			System.out.println("The markets are currently CLOSED!");
		}
		System.out.println("Tick");
		try {
			machine.retrieve();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean marketsOpen() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		System.out.println("The current time is: "+cal.getTime().toString());
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		if(dayOfWeek == 7||dayOfWeek == 1) {
			return false;
		}
		else if(hour<=7||hour>=2) {
			return false;
		}
		return true;
	}
}
