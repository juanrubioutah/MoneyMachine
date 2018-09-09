package Money.MoneyMachine;

import java.io.IOException;

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
		System.out.println("Tick");
		try {
			machine.retrieve();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
