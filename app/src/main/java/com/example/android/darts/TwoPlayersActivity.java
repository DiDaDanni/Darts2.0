package com.example.android.darts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.darts.MainActivity.*;

public class TwoPlayersActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_players);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        plusBtn = (Button)findViewById(R.id.plus);
        calcView = (TextView)findViewById(R.id.text_view_calc);
        playerOneView = (TextView)findViewById(R.id.player_one_value);
        playerTwoView = (TextView)findViewById(R.id.player_two_value);
        gridLayout = (GridLayout)findViewById(R.id.grid_calc);
        layoutOne = (LinearLayout)findViewById(R.id.player_one);
        layoutTwo = (LinearLayout)findViewById(R.id.player_two);
        startValueView = (EditText)findViewById(R.id.start_value);
        playerOneEditText = (EditText)findViewById(R.id.player_one_edit);
        playerTwoEditText = (EditText)findViewById(R.id.player_two_edit);

        enableViews(gridLayout,false);

    }

    /***
     * show the clicked value in the textview
     * @param view
     */
    public void onValueClick(View view){
        calcView.setText(value += view.getTag().toString());
    }

    /***
     * reset the game
     * @param view
     */
    public void onResetClick(View view){
        score = 0;
        enableViews(gridLayout,false);
        enableEditTextViews(true);
        startValueView.setText("");
        playerOneView.setText("");
        playerTwoView.setText("");
        playerOneEditText.setText("");
        playerTwoEditText.setText("");
        thirdTime = 0;
        value = "";
        calcView.setText("");
        isPlayerOne = true;
        isPlayerTwo = false;
        layoutOne.setBackgroundColor(getResources().getColor(R.color.inactivePlayer));
        layoutTwo.setBackgroundColor(getResources().getColor(R.color.inactivePlayer));
        namePlayerTwo = "";
        namePlayerOne = "";
    }

    /***
     * add the enteredValue to the score
     * @param view
     */
    public void onPlusClick(View view){
        if (calcView.getText().equals("")){
            Toast.makeText(this, "please enter a value", Toast.LENGTH_SHORT).show();
        }
        else{
            enteredValue = Integer.parseInt(calcView.getText().toString());
            score += enteredValue;
            calcView.setText("");
            value = "";
            thirdTime++;
            if (thirdTime>=2) {
                plusBtn.setEnabled(false);
                thirdTime = 0;
            }
        }
    }

    /***
     * confirm the thrown values and sub it from the score
     * @param view
     */
    public void onEnterClick(View view){
        if (calcView.getText().equals("")){
            Toast.makeText(this, "please enter a value", Toast.LENGTH_SHORT).show();
        }
        else{
            enteredValue = Integer.parseInt(calcView.getText().toString());
            score += enteredValue;
            calcView.setText("");
            value = "";
            thirdTime = 0;

            if (isPlayerOne){
                if (valuePlayerOne < score){
                    Toast.makeText(this, "thrown over", Toast.LENGTH_SHORT).show();
                }
                else{
                    valuePlayerOne -= score;
                    playerOneView.setText(Integer.toString(valuePlayerOne));

                    if (valuePlayerOne == 0){
                        enableViews(gridLayout,false);
                        enableEditTextViews(true);
                        Toast.makeText(this, namePlayerOne + " wins", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        plusBtn.setEnabled(true);
                    }
                }
                activatePlayer("player2");
            }
            else{
                if (valuePlayerTwo < score){
                    Toast.makeText(this, "thrown over", Toast.LENGTH_SHORT).show();
                }
                else {
                    valuePlayerTwo -= score;
                    playerTwoView.setText(Integer.toString(valuePlayerTwo));

                    if (valuePlayerTwo == 0){
                        enableViews(gridLayout,false);
                        enableEditTextViews(true);
                        Toast.makeText(this, namePlayerTwo + " wins", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        plusBtn.setEnabled(true);
                    }
                }
                activatePlayer("player1");
            }
            calcView.setText("");
            score = 0;
        }

    }

    /***
     * multiply the enteredValue
     * @param view
     */
    public void onXClick(View view){
        String temp = (calcView.getText().toString());
        if (temp.equals("")){
            Toast.makeText(this, "First enter a value to multplicate", Toast.LENGTH_SHORT).show();
        }
        else{
            multValue = Integer.parseInt(temp);
            switch(view.getId()){
                case R.id.twice:
                    multValue *=2;
                    break;
                case R.id.triple:
                    if (multValue == 25)
                        Toast.makeText(this, "Can't throw a triple BULL", Toast.LENGTH_SHORT).show();
                    else
                        multValue *=3;
                    break;
            }
            calcView.setText(Integer.toString(multValue));
        }

    }

    /***
     * start the game
     * @param view
     */
    public void onPlayClick(View view){;
        String start = startValueView.getText().toString();
        namePlayerOne = playerOneEditText.getText().toString();
        namePlayerTwo = playerTwoEditText.getText().toString();

        if (start.equals("")){
            Toast.makeText(this, "Please enter a value to start", Toast.LENGTH_SHORT).show();
        }
        else if (namePlayerOne.equals("") || namePlayerTwo.equals("")){
            Toast.makeText(this, "Please enter player names to start", Toast.LENGTH_SHORT).show();
        }
        else{
            playerOneView.setText(start);
            valuePlayerOne = Integer.parseInt(start);
            playerTwoView.setText(start);
            valuePlayerTwo = Integer.parseInt(start);

            enableViews(gridLayout,true);
            enableEditTextViews(false);

            if (isPlayerOne){
                layoutOne.setBackground(getDrawable(R.drawable.border_color));
            }

        }
    }


    /***
     * delete the last enteredValue from the textView
     * @param view
     */
    public static void onDeleteClick(View view){
        calcView.setText("");
        value = "";
    }

    /***
     * set a player active
     * @param player define which player is set as active one
     */
    private void activatePlayer(String player){
        switch(player){
            case "player1":
                isPlayerOne = true;
                isPlayerTwo = false;

                layoutOne.setBackground(getDrawable(R.drawable.border_color));
                layoutTwo.setBackgroundColor(getResources().getColor(R.color.inactivePlayer));
                break;

            case "player2":
                isPlayerOne = false;
                isPlayerTwo = true;

                layoutTwo.setBackground(getDrawable(R.drawable.border_color));
                layoutOne.setBackgroundColor(getResources().getColor(R.color.inactivePlayer));
               break;
        }
    }

    /***
     * enable or disable the  editable textViews
     * @param enabled boolean values true=enabled, false=disabled
     */
    private void enableEditTextViews(boolean enabled){
        playerOneEditText.setEnabled(enabled);
        playerTwoEditText.setEnabled(enabled);
        startValueView.setEnabled(enabled);
    }
}

