package Money.MoneyMachine;
import java.util.ArrayList;
import java.util.*;

public class TradeDeterminer {
	public HashMap<OptionData, Integer> goodOptions = new HashMap<OptionData, Integer>();
	public TradeDeterminer(ArrayList<OptionData> options) {
		goodOptions = new HashMap<OptionData, Integer>();
		for(int i = 0; i<options.size(); i++) {
			if(options.get(i).callPercent>=.75) {
				goodOptions.put(options.get(i), 0);
			}
			else if(options.get(i).putPercent>=.75) {
				goodOptions.put(options.get(i), 1);
			}
		}
		print();
	}
	public void print() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Good Options:\n");
		
		Set<OptionData> goodSet = goodOptions.keySet();
		Iterator itr = goodSet.iterator();
		while(itr.hasNext()) {
			OptionData temp = (OptionData) itr.next();
			System.out.print(temp.ticker+" "+temp.callPercent+" "+temp.putPercent+" ");
			if(goodOptions.get(temp)==0) {
				System.out.println("CALL");
			}
			else if(goodOptions.get(temp)==1) {
				System.out.println("PUT");
			}
		}
		System.out.println("\n\n\n");
	}
	public HashMap<OptionData, Integer> getGoodOptions(){
		return goodOptions;
	}
}
