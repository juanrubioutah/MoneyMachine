package Money.MoneyMachine;
import java.util.ArrayList;
import java.util.*;

public class TradeDeterminer {
	public static HashMap<OptionData, Integer> goodOptions;
	public TradeDeterminer(ArrayList<OptionData> options) {
		goodOptions = new HashMap<OptionData, Integer>();
		for(int i = 0; i<options.size(); i++) {
			if(options.get(i).callPercent>=80) {
				goodOptions.put(options.get(i), 0);
			}
			else if(options.get(i).putPercent>=80) {
				goodOptions.put(options.get(i), 1);
			}
		}
		print();
	}
	public void print() {
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
	}
}
