package com.example.android.darts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Daniela on 25.02.2017.
 */
import static android.R.color.black;
import static com.example.android.darts.MainActivity.*;

public class FourPlayersActivity extends AppCompatActivity {

    public int isFirst = 0, isSecond = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_players);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        plusBtn = (Button)findViewById(R.id.plus);
        playBtn = (Button)findViewById(R.id.play);
        undoBtn = (Button)findViewById(R.id.undo);
        calcView = (TextView)findViewById(R.id.text_view_calc);
        playerOneView = (TextView)findViewById(R.id.player_one_value);
        playerTwoView = (TextView)findViewById(R.id.player_two_value);
        playerThreeView = (TextView)findViewById(R.id.player_three_value);
        playerFourView = (TextView)findViewById(R.id.player_four_value);
        gridLayout = (GridLayout)findViewById(R.id.grid_calc);
        layoutOne = (LinearLayout)findViewById(R.id.player_one);
        layoutTwo = (LinearLayout)findViewById(R.id.player_two);
        layoutThree = (LinearLayout)findViewById(R.id.player_three);
        layoutFour = (LinearLayout)findViewById(R.id.player_four);
        startValueView = (EditText)findViewById(R.id.start_value);
        playerOneEditText = (EditText)findViewById(R.id.player_one_edit);
        playerTwoEditText = (EditText)findViewById(R.id.player_two_edit);
        playerThreeEditText = (EditText)findViewById(R.id.player_three_edit);
        playerFourEditText = (EditText)findViewById(R.id.player_four_edit);

        enableViews(gridLayout,false);
        undoBtn.setEnabled(false);
    }

    public void onValueClick(View view){
        calcView.setText(value += view.getTag().toString());
    }

    public void onResetClick(View view){
        score = 0;
        enableViews(gridLayout,false);
        enableEditTextViews(true);
        startValueView.setText("");
        playerOneView.setText("");
        playerTwoView.setText("");
        playerThreeView.setText("");
        playerFourView.setText("");
        playerOneEditText.setText("");
        playerTwoEditText.setText("");
        playerThreeEditText.setText("");
        playerFourEditText.setText("");
        thirdTime = 0;
        value = "";
        calcView.setText("");
        isPlayerOne = true;
        isPlayerTwo = false;
        isPlayerThree = false;
        isPlayerFour = false;
        layoutOne.setBackgroundColor(getResources().getColor(R.color.inactivePlayer));
        layoutTwo.setBackgroundColor(getResources().getColor(R.color.inactivePlayer));
        layoutThree.setBackgroundColor(getResources().getColor(R.color.inactivePlayer));
        layoutFour.setBackgroundColor(getResources().getColor(R.color.inactivePlayer));
        namePlayerTwo = "";
        namePlayerOne = "";
        namePlayerThree = "";
        namePlayerFour = "";
    }

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
                undoPlayer = 1;
                if (valuePlayerOne < score){
                    Toast.makeText(this, "thrown over", Toast.LENGTH_SHORT).show();
                }
                else{
                    undoBtn.setEnabled(true);
                    valuePlayerOne -= score;
                    playerOneView.setText(Integer.toString(valuePlayerOne));

                    if (valuePlayerOne == 0){
                        undoBtn.setEnabled(false);
                        if (isFirst == 0){
                            isFirst = 1; //winner
                            Toast.makeText(this, namePlayerOne + " wins", Toast.LENGTH_SHORT).show();
                        }
                        else if (isFirst != 0){
                            if (isSecond == 0){
                                isSecond = 1;    //second one
                                Toast.makeText(this, namePlayerOne + " is second", Toast.LENGTH_SHORT).show();
                            }
                            else if (isSecond != 0){
                                //third one
                                Toast.makeText(this, namePlayerOne + " is third", Toast.LENGTH_SHORT).show();
                                enableViews(gridLayout,false);
                                enableEditTextViews(true);
                            }
                        }
                    }
                    else{
                        plusBtn.setEnabled(true);
                    }
                }
                if ((isFirst == 2 || isSecond == 2) && (isFirst == 3 || isSecond == 3))
                    activatePlayer("player4");
                else if (isFirst == 2 || isSecond == 2)
                    activatePlayer("player3");
                else
                    activatePlayer("player2");
            }
            else if (isPlayerTwo){
                undoPlayer = 2;
                if (valuePlayerTwo < score){
                    Toast.makeText(this, "thrown over", Toast.LENGTH_SHORT).show();
                }
                else {
                    undoBtn.setEnabled(true);
                    valuePlayerTwo -= score;
                    playerTwoView.setText(Integer.toString(valuePlayerTwo));

                    if (valuePlayerTwo == 0){
                        undoBtn.setEnabled(false);
                        if (isFirst == 0){
                            isFirst = 2; //winner
                            Toast.makeText(this, namePlayerTwo + " wins", Toast.LENGTH_SHORT).show();
                        }
                        else if (isFirst != 0){
                            if (isSecond == 0){
                                isSecond = 2;    //second one
                                Toast.makeText(this, namePlayerTwo + " is second", Toast.LENGTH_SHORT).show();
                            }
                            else if (isSecond != 0){
                                //third one
                                Toast.makeText(this, namePlayerTwo + " is third", Toast.LENGTH_SHORT).show();
                                enableViews(gridLayout,false);
                                enableEditTextViews(true);
                            }
                        }
                    }
                    else{
                        plusBtn.setEnabled(true);
                    }
                }
                if ((isFirst == 3 || isSecond == 3) && (isFirst == 4 || isSecond == 4))
                    activatePlayer("player1");
                else if (isFirst == 3 || isSecond == 3)
                    activatePlayer("player4");
                else
                    activatePlayer("player3");
            }

            else if (isPlayerThree){
                undoPlayer = 3;
                if (valuePlayerThree < score){
                    Toast.makeText(this, "thrown over", Toast.LENGTH_SHORT).show();
                }
                else {
                    undoBtn.setEnabled(true);
                    valuePlayerThree -= score;
                    playerThreeView.setText(Integer.toString(valuePlayerThree));

                    if (valuePlayerThree == 0){
                        undoBtn.setEnabled(false);
                        if (isFirst == 0){
                            isFirst = 3; //winner
                            Toast.makeText(this, namePlayerThree + " wins", Toast.LENGTH_SHORT).show();
                        }
                        else if (isFirst != 0){
                            if (isSecond == 0){
                                isSecond = 3;    //second one
                                Toast.makeText(this, namePlayerThree + " is second", Toast.LENGTH_SHORT).show();
                            }
                            else if (isSecond != 0){
                                //third one
                                Toast.makeText(this, namePlayerThree + " is third", Toast.LENGTH_SHORT).show();
                                enableViews(gridLayout,false);
                                enableEditTextViews(true);
                            }
                        }
                    }
                    else{
                        plusBtn.setEnabled(true);
                    }
                }
                if ((isFirst == 4 || isSecond == 4) && (isFirst == 1 || isSecond == 1))
                    activatePlayer("player2");
                else if (isFirst == 4 || isSecond == 4)
                    activatePlayer("player1");
                else
                    activatePlayer("player4");
            }
            else if (isPlayerFour){
                undoPlayer = 4;
                if (valuePlayerFour < score){
                    Toast.makeText(this, "thrown over", Toast.LENGTH_SHORT).show();
                }
                else {
                    undoBtn.setEnabled(true);
                    valuePlayerFour -= score;
                    playerFourView.setText(Integer.toString(valuePlayerFour));

                    if (valuePlayerFour == 0){
                        undoBtn.setEnabled(false);
                        if (isFirst == 0){
                            isFirst = 4; //winner
                            Toast.makeText(this, namePlayerFour + " wins", Toast.LENGTH_SHORT).show();
                        }
                        else if (isFirst != 0){
                            if (isSecond == 0){
                                isSecond = 4;    //second one
                                Toast.makeText(this, namePlayerFour + " is second", Toast.LENGTH_SHORT).show();
                            }
                            else if (isSecond != 0){
                                //third one
                                Toast.makeText(this, namePlayerFour + " is third", Toast.LENGTH_SHORT).show();
                                enableViews(gridLayout,false);
                                enableEditTextViews(true);
                            }
                        }
                    }
                    else{
                        plusBtn.setEnabled(true);
                    }
                }
                if ((isFirst == 1 || isSecond == 1) && (isFirst == 2 || isSecond == 2))
                    activatePlayer("player3");
                else if (isFirst == 1 || isSecond == 1)
                    activatePlayer("player2");
                else
                    activatePlayer("player1");
            }

            calcView.setText("");
            undoScore = score;
            score = 0;
        }

    }

    public void onUndoClick(View view){

        if (undoPlayer == 1){
            valuePlayerOne += undoScore;
            playerOneView.setText(Integer.toString(valuePlayerOne));
            activatePlayer("player1");
        }
        else if (undoPlayer == 2){
            valuePlayerTwo += undoScore;
            playerTwoView.setText(Integer.toString(valuePlayerTwo));
            activatePlayer("player2");
        }

        else if (undoPlayer == 3){
            valuePlayerThree += undoScore;
            playerThreeView.setText(Integer.toString(valuePlayerThree));
            activatePlayer("player3");
        }

        else if (undoPlayer == 4){
            valuePlayerFour += undoScore;
            playerFourView.setText(Integer.toString(valuePlayerFour));
            activatePlayer("player4");
        }

        undoBtn.setEnabled(false);
    }


    public static void onDeleteClick(View view){
        calcView.setText("");
        value = "";
    }

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

    public void onPlayClick(View view){;
        String start = startValueView.getText().toString();
        namePlayerOne = playerOneEditText.getText().toString();
        namePlayerTwo = playerTwoEditText.getText().toString();
        namePlayerThree = playerThreeEditText.getText().toString();
        namePlayerFour = playerFourEditText.getText().toString();

        if (start.equals("")){
            Toast.makeText(this, "Please enter a value to start", Toast.LENGTH_SHORT).show();
        }
        else if (namePlayerOne.equals("") || namePlayerTwo.equals("") || namePlayerThree.equals("") || namePlayerFour.equals("")){
            Toast.makeText(this, "Please enter player names to start", Toast.LENGTH_SHORT).show();
        }
        else{
            playerOneView.setText(start);
            valuePlayerOne = Integer.parseInt(start);
            playerTwoView.setText(start);
            valuePlayerTwo = Integer.parseInt(start);
            playerThreeView.setText(start);
            valuePlayerThree = Integer.parseInt(start);
            playerFourView.setText(start);
            valuePlayerFour = Integer.parseInt(start);

            enableViews(gridLayout,true);
            enableEditTextViews(false);

            isFirst = 0;
            isSecond = 0;

            if (isPlayerOne){
                activatePlayer("player1");
            }

        }
    }


    private void activatePlayer(String player){
        switch(player){
            case "player1":
                isPlayerOne = true;
                isPlayerTwo = false;
                isPlayerThree = false;
                isPlayerFour = false;

                layoutOne.setBackground(getDrawable(R.drawable.border_color));
                layoutTwo.setBackgroundColor(getResources().getColor(R.color.inactivePlayer));
                layoutThree.setBackgroundColor(getResources().getColor(R.color.inactivePlayer));
                layoutFour.setBackgroundColor(getResources().getColor(R.color.inactivePlayer));

                playerOneEditText.setTextColor(getResources().getColor(black));
                playerTwoEditText.setTextColor(getResources().getColor(R.color.grey));
                playerThreeEditText.setTextColor(getResources().getColor(R.color.grey));
                playerFourEditText.setTextColor(getResources().getColor(R.color.grey));
                break;

            case "player2":
                isPlayerOne = false;
                isPlayerTwo = true;
                isPlayerThree = false;
                isPlayerFour = false;

                layoutTwo.setBackground(getDrawable(R.drawable.border_color));
                layoutOne.setBackgroundColor(getResources().getColor(R.color.inactivePlayer));
                layoutThree.setBackgroundColor(getResources().getColor(R.color.inactivePlayer));
                layoutFour.setBackgroundColor(getResources().getColor(R.color.inactivePlayer));

                playerTwoEditText.setTextColor(getResources().getColor(black));
                playerOneEditText.setTextColor(getResources().getColor(R.color.grey));
                playerThreeEditText.setTextColor(getResources().getColor(R.color.grey));
                playerFourEditText.setTextColor(getResources().getColor(R.color.grey));
                break;

            case "player3":
                isPlayerOne = false;
                isPlayerTwo = false;
                isPlayerThree = true;
                isPlayerFour = false;

                layoutThree.setBackground(getDrawable(R.drawable.border_color));
                layoutOne.setBackgroundColor(getResources().getColor(R.color.inactivePlayer));
                layoutTwo.setBackgroundColor(getResources().getColor(R.color.inactivePlayer));
                layoutFour.setBackgroundColor(getResources().getColor(R.color.inactivePlayer));

                playerThreeEditText.setTextColor(getResources().getColor(black));
                playerTwoEditText.setTextColor(getResources().getColor(R.color.grey));
                playerOneEditText.setTextColor(getResources().getColor(R.color.grey));
                playerFourEditText.setTextColor(getResources().getColor(R.color.grey));
                break;

            case "player4":
                isPlayerOne = false;
                isPlayerTwo = false;
                isPlayerThree = false;
                isPlayerFour = true;

                layoutFour.setBackground(getDrawable(R.drawable.border_color));
                layoutOne.setBackgroundColor(getResources().getColor(R.color.inactivePlayer));
                layoutTwo.setBackgroundColor(getResources().getColor(R.color.inactivePlayer));
                layoutThree.setBackgroundColor(getResources().getColor(R.color.inactivePlayer));

                playerFourEditText.setTextColor(getResources().getColor(black));
                playerTwoEditText.setTextColor(getResources().getColor(R.color.grey));
                playerThreeEditText.setTextColor(getResources().getColor(R.color.grey));
                playerOneEditText.setTextColor(getResources().getColor(R.color.grey));
                break;
        }
    }

    private void enableEditTextViews(boolean enabled){
        playerOneEditText.setEnabled(enabled);
        playerTwoEditText.setEnabled(enabled);
        playerThreeEditText.setEnabled(enabled);
        playerFourEditText.setEnabled(enabled);
        startValueView.setEnabled(enabled);

        changePlayButtonText(enabled);

        if (enabled){
            playerOneEditText.setTextColor(getResources().getColor(black));
            playerTwoEditText.setTextColor(getResources().getColor(black));
            playerThreeEditText.setTextColor(getResources().getColor(black));
            playerFourEditText.setTextColor(getResources().getColor(black));
        }

    }

    private void changePlayButtonText(boolean play){
        if (play)
            playBtn.setText(getResources().getString(R.string.play));
        else
            playBtn.setText(getResources().getString(R.string.restart));
    }
}



