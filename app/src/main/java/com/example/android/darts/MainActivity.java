package com.example.android.darts;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by Daniela on 25.02.2017.
 */

public class MainActivity extends AppCompatActivity {

    //values for all activities
    public static String value = "";
    public static EditText startValueView, playerOneEditText, playerTwoEditText, playerThreeEditText, playerFourEditText;
    public static TextView calcView, playerOneView, playerTwoView, playerThreeView, playerFourView;
    public static Button plusBtn, playBtn, undoBtn;
    public static GridLayout gridLayout;
    public static LinearLayout layoutOne, layoutTwo, layoutThree, layoutFour;
    public static int score = 0, undoScore = 0;
    public static int valuePlayerOne, valuePlayerTwo, valuePlayerThree, valuePlayerFour;
    public static int enteredValue, multValue;
    public static int thirdTime = 0;
    public static boolean isPlayerOne = true, isPlayerTwo = false, isPlayerThree = false, isPlayerFour = false;
    public static String namePlayerOne, namePlayerTwo, namePlayerThree, namePlayerFour;
    public static int undoPlayer = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /***
     *function to switch between the activities for player amount
     * @param view
     */
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.two_players:
                if (checked)
                    startActivity(new Intent(this, TwoPlayersActivity.class));
                    break;
            case R.id.three_players:
                if (checked)
                    startActivity(new Intent(this, ThreePlayersActivity.class));
                    break;
            case R.id.four_players:
                if (checked)
                    startActivity(new Intent(this, FourPlayersActivity.class));
                    break;
        }
    }

    /***
     * enable or disable the buttons on the buttom
     * @param v
     * @param enabled get the boolean value true = enabled, false = disabled
     */
    public static void enableViews(View v, boolean enabled) {
        if (v instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) v;
            for (int i = 0;i<vg.getChildCount();i++) {
                enableViews(vg.getChildAt(i), enabled);
            }
        }
        v.setEnabled(enabled);
    }



}
