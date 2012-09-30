/**
 * 
 */
package dtc.personal.applestoapplesapi5;

import java.util.HashMap;

/**
 * @author Douglas
 *
 */
public class InitZeroHashMap {
	private HashMap<String, Double> theredcards;
	private double alpha = .3;
	

	public InitZeroHashMap() {
		theredcards = new HashMap<String, Double>();
	}
	public Double get(String key) {
		if (containsKey(key)) {
			return theredcards.get(key);
		} else {
			put(key, new Double(0));
			return new Double(0);
		}
			
	}
	public boolean containsKey(String key) {
		return theredcards.containsKey(key);
	}
	public void put(String thekey, Double thevalue) {
		theredcards.put(thekey, thevalue);
	}

 /** winorlose will be 1 or 0 depending on whether the card wins or loses. */
	public void updateValue(String adj, Double winorlose) {
		Double oldValue = get(adj);
		Double newValue = (1- alpha) * oldValue + alpha * winorlose;
		theredcards.put(adj, newValue);
		
	}


}
