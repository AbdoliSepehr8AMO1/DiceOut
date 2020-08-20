package com.example.diceout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //textview
    TextView rollResult;


    //field to hold roll button
    Button rollButton;

    //integer to hold our score
    int score;

    //field to hold random number generated
    Random rand;

    //fields to hold the dice values
    int die1;
    int die2;
    int die3;

    //field to hold the score text
    TextView scoreText;


    //array list to hold all 3 dice values
    ArrayList<Integer> dice;

    //arraylist to hold all 3 images
    ArrayList<ImageView> diceImageViews;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //set initial score
        score = 0;

        rollResult = (TextView) findViewById(R.id.rollResult);
        rollButton = (Button) findViewById(R.id.rollButton);
        scoreText = (TextView) findViewById(R.id.scoreText);


        //initialize the random number generator
        rand = new Random();

        //create arraylist container for dice values
        dice = new ArrayList<Integer>();

        ImageView die1Image = (ImageView) findViewById(R.id.die1Image);
        ImageView die2Image = (ImageView) findViewById(R.id.die2Image);
        ImageView die3Image = (ImageView) findViewById(R.id.die3Image);


        diceImageViews = new ArrayList<ImageView>();
        diceImageViews.add(die1Image);
        diceImageViews.add(die2Image);
        diceImageViews.add(die3Image);


    }




    public void rollDice(View v){
        rollResult.setText("Clicked!");

        //roll dice
        die1 = rand.nextInt(6) + 1;
        die2 = rand.nextInt(6) + 1;
        die3 = rand.nextInt(6) + 1;

        //set dice values into an arraylist
        dice.clear();
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);

        for (int dieOfSet = 0; dieOfSet <3; dieOfSet++){
            String imageName = "die_" + dice.get (dieOfSet) + ".png";

            try {
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream,null);
                diceImageViews.get(dieOfSet).setImageDrawable(d);

            } catch (IOException e){
                e.printStackTrace();
            }

        }


        //build message with the result
        String msg;

        if(die1 == die2 && die1 == die3) {
            //triples
            int scoreDelta = die1 * 100;
            msg = "You rolled a triple" + die1 + "! you score " + scoreDelta + " points!";
            score += scoreDelta;

        } else if (die1 == die2 || die1 == die3 || die2 == die3) {

            //doubles
            msg = "you rolled doubles for 50 points";
            score += 50;
        } else {
            msg = "you didn't score this roll. try again!";
        }


        //update the app to display the result message
        rollResult.setText(msg);
        scoreText.setText("score: " + score);

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}