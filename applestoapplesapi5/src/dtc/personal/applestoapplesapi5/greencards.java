package dtc.personal.applestoapplesapi5;

import java.util.ArrayList;
import java.lang.Math;

/**
*@author Douglas
*/

public class greencards {
	ArrayList<String> theAdjectivesList;
	String adjectives = "strong aggravating good flirtatious ingenius awkward funny awful convoluted faked generic delicious";
	String [] adjarray = adjectives.split(" ");
	String lastCard = " ";
	
	public greencards() {
	}
	
	public String getRandomCard() {
		int length = adjarray.length;
		String nextCard = adjarray[(int) Math.round((length - 1) * Math.random())];
		if (nextCard != lastCard) {
			lastCard = nextCard;
			return nextCard;
		} else {
			return getRandomCard();
		}
	}
	
	
	
	
}