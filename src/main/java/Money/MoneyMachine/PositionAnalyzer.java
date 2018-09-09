package Money.MoneyMachine;

import java.util.ArrayList;

public class PositionAnalyzer { //Analyzes currently held positions and determines if they should be sold
	public PositionAnalyzer() {
		ArrayList<Option> data = Machine.currentlyHeldOptions;
		analyze(data);
	}
	public void analyze(ArrayList<Option> data) {
		for(int i = 0; i < data.size(); i++) {
			//TODO: Get current option value and compare to previous value
		}
	}
}
