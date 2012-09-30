package dtc.personal.applestoapplesapi5;


import android.R.*;
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
			"barrels Denzel_Washington Mount_Rushmore blue_footed_boobies the_color_purple computers lemons lemmas " +
			"Fermat's_Little_Theorem blackness spiders scopes clowns Spongebob styrofoam starcraft California New_York " +
			"airplanes robotics string bijection lemmings graphs flowers pie donuts garlic chairs losers";
	public TextView currGreenCard;
	public TextView lastWinner;
	public TextView handGreenCard;
	public Button noun1;
	public Button noun2;
	public Button noun3;
	public Button joingame;
	public Button hand1;
	public Button hand2;
	public Button hand3;
	public Button hand4;
	public Button hand5;
	public Button lategamenoun1;
	public Button lategamenoun2;
	public Button lategamenoun3;
	public Button lategamenoun4;
	public greencards greendeck = new greencards();
	public List<Redcards> reddeck;
	public List<Redcards> randomcomphand;
	public List<Redcards> learningcomphand;
	public List<Redcards> randomcomp2hand;
	public List<Redcards> yourhandcards;
	public List<Redcards> playedcards;
	public List<Redcards> currPlayed;
	public String currentadj;
	public String lastwinner = " ";
	public Redcards learningcard;
	public Redcards randomcard;
	public Redcards randomcard2;
	public Redcards yourcard;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.judgescreen);
        noun1 = (Button) findViewById(R.id.noun1);
        noun1.setOnClickListener(button1Listener);
        noun2 = (Button) findViewById(R.id.noun2);
        noun2.setOnClickListener(button2Listener);
        noun3 = (Button) findViewById(R.id.noun3);
        noun3.setOnClickListener(button3Listener);
        joingame = (Button) findViewById(R.id.joingame);
        joingame.setOnClickListener(joingamelistener);
        currGreenCard = (TextView) findViewById(R.id.adj);
        currentadj = greendeck.getRandomCard();
        currGreenCard.setText(currentadj);
        handGreenCard = (TextView) findViewById(R.id.handGreenCard);
        lastWinner = (TextView) findViewById(R.id.lastWinner);
        
        reddeck = new ArrayList<Redcards>();
        String[] redarray = redcards.split(" ");
        for (String redname : redarray) {
        	reddeck.add(new Redcards(redname));
        }
        Collections.shuffle(reddeck);
        randomcomphand = drawfive();
        learningcomphand = drawfive();
        randomcomp2hand = drawfive();
        playall();
        
    }
    
    public void playall() {
    	currPlayed = new ArrayList<Redcards>();
    	currPlayed.add(playRandom(1));
    	currPlayed.add(playLearning(currentadj));
    	currPlayed.add(playRandom(2));
    	Collections.shuffle(currPlayed);
    	noun1.setText(currPlayed.get(0).cardName);
    	noun2.setText(currPlayed.get(1).cardName);
    	noun3.setText(currPlayed.get(2).cardName);
    	
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
    
    public Redcards playRandom(int randcompnum) {
    	int rand =  (int) Math.round(4 * Math.random());
    	Redcards thecard = null;
    	if (randcompnum == 1) {
    		thecard = randomcomphand.get(rand);
    		randomcomphand.remove(rand);
    		randomcomphand.add(draw());
    		randomcard = thecard;
    	} else {
    		thecard = randomcomp2hand.get(rand);
    		randomcomp2hand.remove(rand);
    		randomcomp2hand.add(draw());
    		randomcard2 = thecard;
    	}
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
    
    public void setUpYourTurn() {
		setContentView(R.layout.yourhand);
		currentadj = greendeck.getRandomCard();
		lastWinner = (TextView) findViewById(R.id.handWinner);
        hand1 = (Button) findViewById(R.id.handcard1);
        hand1.setOnClickListener(yourmovelistener);
        hand2 = (Button) findViewById(R.id.handcard2);
        hand2.setOnClickListener(yourmovelistener);
        hand3 = (Button) findViewById(R.id.handcard3);
        hand3.setOnClickListener(yourmovelistener);
        hand4 = (Button) findViewById(R.id.handcard4);
        hand4.setOnClickListener(yourmovelistener);
        hand5 = (Button) findViewById(R.id.handcard5);
        hand5.setOnClickListener(yourmovelistener);
		System.out.println(yourhandcards);
		hand1.setText(yourhandcards.get(0).cardName);
		hand2.setText(yourhandcards.get(1).cardName);
		hand3.setText(yourhandcards.get(2).cardName);
		hand4.setText(yourhandcards.get(3).cardName);
		hand5.setText(yourhandcards.get(4).cardName);
        handGreenCard = (TextView) findViewById(R.id.handGreenCard);
		handGreenCard.setText(currentadj);
		lastWinner.setText(lastwinner);
    	
    }
    
    public void setUpJudging() {
    	currPlayed = new ArrayList<Redcards>();
    	currPlayed.add(yourcard);
    	currPlayed.add(playRandom(1));
    	currPlayed.add(playRandom(2));
    	currPlayed.add(playLearning(currentadj));
    	Collections.shuffle(currPlayed);
    	setContentView(R.layout.lategamejudgescreen);
    	lategamenoun1 = (Button) findViewById(R.id.lategamenoun1);
    	lategamenoun1.setText(currPlayed.get(0).cardName);
    	lategamenoun1.setOnClickListener(lategamejudgelistener);
    	lategamenoun2 = (Button) findViewById(R.id.lategamenoun2);
    	lategamenoun2.setText(currPlayed.get(1).cardName);
    	lategamenoun2.setOnClickListener(lategamejudgelistener);
    	lategamenoun3 = (Button) findViewById(R.id.lategamenoun3);
    	lategamenoun3.setText(currPlayed.get(2).cardName);
    	lategamenoun3.setOnClickListener(lategamejudgelistener);
    	lategamenoun4 = (Button) findViewById(R.id.lategamenoun4);
    	lategamenoun4.setText(currPlayed.get(3).cardName);
    	lategamenoun4.setOnClickListener(lategamejudgelistener);
    }
	private OnClickListener button1Listener = new OnClickListener() {
		public void onClick(View notUsed) {
			if(noun1.getText().equals(learningcard.cardName)) {
				learningcard.updateValue(currentadj, new Double(1));
				randomcard.updateValue(currentadj, new Double(0));
				randomcard2.updateValue(currentadj,  new Double(0));
				lastWinner.setText("learning won!");
			} else if (noun1.getText().equals(randomcard.cardName)) {
				learningcard.updateValue(currentadj, new Double(0));
				randomcard.updateValue(currentadj, new Double(1));
				randomcard2.updateValue(currentadj, new Double(0));
				lastWinner.setText("random won!");
			} else {
				learningcard.updateValue(currentadj, new Double(0));
				randomcard.updateValue(currentadj, new Double(0));
				randomcard2.updateValue(currentadj, new Double(1));
				lastWinner.setText("random2 won!");
			}
			reddeck.add(learningcard);
			reddeck.add(randomcard);
			reddeck.add(randomcard2);
			Collections.shuffle(reddeck);
			playall();
		}
	};
	private OnClickListener button2Listener = new OnClickListener() {
		public void onClick(View notUsed) {
			if(noun2.getText().equals(learningcard.cardName)) {
				learningcard.updateValue(currentadj, new Double(1));
				randomcard.updateValue(currentadj, new Double(0));
				randomcard2.updateValue(currentadj,  new Double(0));
				lastWinner.setText("learning won!");
			} else if (noun2.getText().equals(randomcard.cardName)) {
				learningcard.updateValue(currentadj, new Double(0));
				randomcard.updateValue(currentadj, new Double(1));
				randomcard2.updateValue(currentadj, new Double(0));
				lastWinner.setText("random won!");
			} else {
				learningcard.updateValue(currentadj, new Double(0));
				randomcard.updateValue(currentadj, new Double(0));
				randomcard2.updateValue(currentadj, new Double(1));
				lastWinner.setText("random2 won!");
			}
			reddeck.add(learningcard);
			reddeck.add(randomcard);
			reddeck.add(randomcard2);
			Collections.shuffle(reddeck);
			playall();
		}
	};
	private OnClickListener button3Listener = new OnClickListener() {
		public void onClick(View notUsed) {
			if(noun3.getText().equals(learningcard.cardName)) {
				learningcard.updateValue(currentadj, new Double(1));
				randomcard.updateValue(currentadj, new Double(0));
				randomcard2.updateValue(currentadj,  new Double(0));
				lastWinner.setText("learning won!");
			} else if (noun3.getText().equals(randomcard.cardName)) {
				learningcard.updateValue(currentadj, new Double(0));
				randomcard.updateValue(currentadj, new Double(1));
				randomcard2.updateValue(currentadj, new Double(0));
				lastWinner.setText("random won!");
			} else {
				learningcard.updateValue(currentadj, new Double(0));
				randomcard.updateValue(currentadj, new Double(0));
				randomcard2.updateValue(currentadj, new Double(1));
				lastWinner.setText("random2 won!");
			}
			reddeck.add(learningcard);
			reddeck.add(randomcard);
			reddeck.add(randomcard2);
			Collections.shuffle(reddeck);
			playall();
		}
	};
	private OnClickListener joingamelistener = new OnClickListener() {
		public void onClick(View notused) {
			yourhandcards = drawfive();
			setUpYourTurn();
			
		}
	};
	private OnClickListener yourmovelistener = new OnClickListener() {
		public void onClick(View yourchoice) {
			Redcards thecard = null;
			if (yourchoice.equals(hand1)) {
				thecard = yourhandcards.get(0);
				yourcard = thecard;
				yourhandcards.remove(0);
				yourhandcards.add(draw());
				
			} else if (yourchoice.equals(hand2)) {
				thecard = yourhandcards.get(1);
				yourcard = thecard;
				yourhandcards.remove(1);
				yourhandcards.add(draw());
				
			} else if (yourchoice.equals(hand3)) {
				thecard = yourhandcards.get(2);
				yourcard = thecard;
				yourhandcards.remove(2);
				yourhandcards.add(draw());
				
			} else if (yourchoice.equals(hand4)) {
				thecard = yourhandcards.get(3);
				yourcard = thecard;
				yourhandcards.remove(3);
				yourhandcards.add(draw());
			} else {
				thecard = yourhandcards.get(4);
				yourcard = thecard;
				yourhandcards.remove(4);
				yourhandcards.add(draw());
			}
			setUpJudging();
			
		}
	};
	private OnClickListener lategamejudgelistener = new OnClickListener() {
		public void onClick(View bestnoun) {
			if (((Button) bestnoun).getText().equals(yourcard.cardName)) {
				yourcard.updateValue(currentadj, new Double(1));
				randomcard.updateValue(currentadj, new Double(0));
				randomcard2.updateValue(currentadj, new Double(0));
				learningcard.updateValue(currentadj, new Double(0));
				lastwinner = "you won!";
			} else if (((Button) bestnoun).getText().equals(randomcard.cardName)) {
				yourcard.updateValue(currentadj, new Double(0));
				randomcard.updateValue(currentadj, new Double(1));
				randomcard2.updateValue(currentadj, new Double(0));
				learningcard.updateValue(currentadj, new Double(0));
				lastwinner = "random won!";
			} else if (((Button) bestnoun).getText().equals(randomcard2.cardName)) {
				yourcard.updateValue(currentadj, new Double(0));
				randomcard.updateValue(currentadj, new Double(0));
				randomcard2.updateValue(currentadj, new Double(1));
				learningcard.updateValue(currentadj, new Double(0));
				lastwinner = "random 2 won!";
			} else {
				yourcard.updateValue(currentadj, new Double(0));
				randomcard.updateValue(currentadj, new Double(0));
				randomcard2.updateValue(currentadj, new Double(0));
				learningcard.updateValue(currentadj, new Double(1));
				lastwinner = "learning won!";
			}
			reddeck.add(yourcard);
			reddeck.add(randomcard);
			reddeck.add(randomcard2);
			reddeck.add(learningcard);
			Collections.shuffle(reddeck);
			setUpYourTurn();
		}
	};
}