package dtc.personal.applestoapplesapi5;


import android.app.Activity;
import android.os.Bundle;
import java.util.HashMap;
import dtc.personal.applestoapplesapi5.*;
import android.widget.TextView;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.view.View;

public class Applestoapplesapi5Activity extends Activity {
	
	String redcards = "box the_Spanish_Inquisition Hilfinger penguins tables otters Illuminati Java Jawas Star_Wars " +
			"Lord_of_the_Rings hackjam3 trees fjords communists Berkeley dirty_dishes kpop android Chinese_people " +
			"barrels Denzel_Washington Mount_Rushmore blue_footed_boobies the_color_purple computers lemons lemmas" +
			" Fermat's_Little_Theorem blackness spiders scopes";
	public TextView currGreenCard;
	public TextView lastWinner;
	public Button noun1;
	public Button noun2;
	public greencards greendeck;
	public List<Redcards> reddeck;
	public List<Redcards> randomcomphand;
	public List<Redcards> learningcomphand;
	public List<Redcards> randomcomp2hand;
	public List<Redcards> playedcards;
	public List<Redcards> currPlayed;
	public String currentadj;
	public Redcards learningcard;
	public Redcards randomcard;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.judgescreen);
        greendeck = new greencards();
        noun1 = (Button) findViewById(R.id.noun1);
        noun1.setOnClickListener(button1Listener);
        noun2 = (Button) findViewById(R.id.noun2);
        noun2.setOnClickListener(button2Listener);
        currGreenCard = (TextView) findViewById(R.id.adj);
        currentadj = greendeck.getRandomCard();
        currGreenCard.setText(currentadj);
        lastWinner = (TextView) findViewById(R.id.lastWinner);
        
        reddeck = new ArrayList<Redcards>();
        String[] redarray = redcards.split(" ");
        for (String redname : redarray) {
        	reddeck.add(new Redcards(redname));
        }
        Collections.shuffle(reddeck);
        randomcomphand = drawfive();
        learningcomphand = drawfive();
        playall();
        
    }
    
    public void playall() {
    	currPlayed = new ArrayList<Redcards>();
    	currPlayed.add(playRandom());
    	currPlayed.add(playLearning(currentadj));
    	Collections.shuffle(currPlayed);
    	noun1.setText(currPlayed.get(0).cardName);
    	noun2.setText(currPlayed.get(1).cardName);
    	currentadj = greendeck.getRandomCard();
    	currGreenCard.setText(currentadj);
    	
    }
    
    public List<Redcards> drawfive() {
    	ArrayList<Redcards> thehand = new ArrayList<Redcards>();
    	for (int i = 0; i < 5; i++) {
    		thehand.add(reddeck.get(0));
    		reddeck.remove(0);
    	}
    	return thehand;
    }
    public Redcards draw() {
    	Redcards thecard = reddeck.get(0);
    	reddeck.remove(0);
    	return thecard;
    }
    
    public Redcards playRandom() {
    	int rand =  (int) Math.round(4 * Math.random());
    	Redcards thecard = randomcomphand.get(rand);
    	randomcomphand.remove(rand);
    	randomcomphand.add(draw());
    	randomcard = thecard;
    	//reddeck.add(thecard); //put the card back into the deck.
    	return thecard;
    	
    }
    
    public Redcards playLearning(String adj) {
    	Double bestscore = new Double(-2);
    	Redcards bestcard = null;
    	int index = 0;
    	for (int i = 0; i < 5; i++) {
    		Redcards nextcard = learningcomphand.get(i);
    		Double nextscore = nextcard.getScoreForAdj(adj);
    		if (nextscore > bestscore) {
    			bestscore = nextscore;
    			bestcard = nextcard;
    			index = i;
    		}
    	}
    	learningcomphand.remove(index);
    	learningcomphand.add(draw());
    	learningcard = bestcard;
    	return bestcard;
    		
    }
	private OnClickListener button1Listener = new OnClickListener() {
		public void onClick(View notUsed) {
			if(noun1.getText().equals(learningcard.cardName)) {
				learningcard.updateValue(currentadj, new Double(1));
				randomcard.updateValue(currentadj, new Double(0));
				lastWinner.setText("learning won!");
			} else {
				learningcard.updateValue(currentadj, new Double(0));
				randomcard.updateValue(currentadj, new Double(1));
				lastWinner.setText("random won!");
			}
			reddeck.add(learningcard);
			reddeck.add(randomcard);
			Collections.shuffle(reddeck);
			playall();
		}
	};
	private OnClickListener button2Listener = new OnClickListener() {
		public void onClick(View notUsed) {
			if(noun2.getText().equals(learningcard.cardName)) {
				learningcard.updateValue(currentadj, new Double(1));
				randomcard.updateValue(currentadj, new Double(0));
				lastWinner.setText("learning won!");
			} else {
				learningcard.updateValue(currentadj, new Double(0));
				randomcard.updateValue(currentadj, new Double(1));
				lastWinner.setText("random won!");
			}
			reddeck.add(learningcard);
			reddeck.add(randomcard);
			Collections.shuffle(reddeck);
			playall();
		}
	};
}