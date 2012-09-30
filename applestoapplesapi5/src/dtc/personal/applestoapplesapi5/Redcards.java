/**
 * 
 */
package dtc.personal.applestoapplesapi5;
import java.util.HashMap;

/**
 * @author Douglas
 *
 */
public class Redcards {

	private InitZeroHashMap adjValues;
	public String cardName;
	public Redcards() {
		adjValues = new InitZeroHashMap();
	}
	public Redcards(String name) {
		cardName = name;
		adjValues = new InitZeroHashMap();
	}
	
	public void updateValue(String adj, Double winorlose) {
		adjValues.updateValue(adj, winorlose);
	}
	
	public Double getScoreForAdj(String adj) {
		return adjValues.get(adj);
	}
}
