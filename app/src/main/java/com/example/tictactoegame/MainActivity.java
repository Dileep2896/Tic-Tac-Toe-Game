package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Array for the block track
    int[] position = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    // Array for all the winning position
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    // Used as the active player
    int active;

    // To check to play or not
    boolean activeGame = true;

    //----------------------------------------------------------------------------------------------//
    // Game main logic
    public void click (View view) {

        ImageView iv = (ImageView) view;

        int tagPosition = Integer.parseInt(iv.getTag().toString());
        String winner = "";

        if(position[tagPosition] == 2 && activeGame) {

            position[tagPosition] = active;

            iv.animate().alpha(1).setDuration(500);

            // 0:Captain America, 1:Iron Man, 2:Empty
            if (active == 0) {
                iv.setImageResource(R.drawable.captainamerica);
                active = 1;
            } else {
                iv.setImageResource(R.drawable.ironman);
                active = 0;
            }

            for (int[] winningPosition : winningPositions) {

                if (position[winningPosition[0]] == position[winningPosition[1]]
                        && position[winningPosition[1]] == position[winningPosition[2]]
                        && position[winningPosition[0]] != 2) {

                    activeGame = false;

                    if (active == 1) {
                        winner = "Winner is Captain America";
                        gameFinish(winner);
                    } else if (active == 0){
                        winner = "Winner is Iron Man";
                        gameFinish(winner);
                    }
                } else if (isTied()
                        && position[winningPosition[0]] != position[winningPosition[1]]
                        && position[winningPosition[1]] != position[winningPosition[2]]){
                        winner = "Draw Match";
                        gameFinish(winner);
                }
            }
        }
    }

    //----------------------------------------------------------------------------------------------//
    // Play again logic
    public void playAgain (View view) {

        TextView tvResult = (TextView) findViewById(R.id.tvResult);

        Button btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);

        tvResult.setVisibility(View.INVISIBLE);

        btnPlayAgain.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++){

            ImageView imageView = (ImageView) gridLayout.getChildAt(i);

            imageView.animate().alpha(0).setDuration(500);
            imageView.setImageDrawable(null);

        }

        for(int i=0; i<position.length; i++){
            position[i] = 2;
        }

        activeGame = true;

        active = 0;

    }

    //----------------------------------------------------------------------------------------------//
    // Game over logic
    public void gameFinish(String winner){

        TextView tvResult = (TextView) findViewById(R.id.tvResult);

        Button btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);

        tvResult.setVisibility(View.VISIBLE);

        btnPlayAgain.setVisibility(View.VISIBLE);

        tvResult.setText(winner);

    }

    //----------------------------------------------------------------------------------------------//
    // Game draw logic
    public boolean isTied() {

        for(int i=0; i<position.length; i++){
            if(position[i] == 2){
                return false;
            }
        }
        return true;
    }

    //----------------------------------------------------------------------------------------------//
    // Create function blank
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}