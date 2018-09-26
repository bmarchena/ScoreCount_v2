package bryanmarchena.scorecount;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //declaration of variables/widgets
    private Button button_teamOne, button_teamTwo;
    private TextView textView_teamOne;
    private TextView textView_teamTwo;
    private int teamOne_int = 0, teamTwo_int = 0;
    private String win_team;

    //declaration of strings for intent passing
    public static final String EXTRA_MESSAGE = "com.bryanmarchena.scorecount.EXTRA_MESSAGE";
    public static final String EXTRA_INT1 = "com.bryanmarchena.scorecount.EXTRA_INT1";
    public static final String EXTRA_INT2 = "com.bryanmarchena.scorecount.EXTRA_INT2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //finding and casting views
        button_teamOne = (Button)findViewById(R.id.button_one);
        button_teamTwo = (Button)findViewById(R.id.button_two);
        textView_teamOne = (TextView)findViewById(R.id.teamOne_scoreDisplay);
        textView_teamTwo = (TextView)findViewById(R.id.teamTwo_scoreDisplay);

        //initializing team scores
        textView_teamOne.setText("Score: " + String.valueOf(teamOne_int));
        textView_teamTwo.setText("Score: " + String.valueOf(teamTwo_int));

        //setting onclick listener
        button_teamOne.setOnClickListener((View.OnClickListener)this);
        button_teamTwo.setOnClickListener((View.OnClickListener)this);
    }

    //overriding onClick method
    public void onClick(View view){
        if(button_teamOne.equals(view)){
            teamOne_int += 1;
            textView_teamOne.setText("Score: " + String.valueOf(teamOne_int));
            if(teamOne_int == 5){
                gameOver();
            }
        }
        else if(button_teamTwo.equals(view)){
            teamTwo_int += 1;
            textView_teamTwo.setText("Score: " + String.valueOf(teamTwo_int));
            if(teamTwo_int == 5){
                gameOver();
            }
        }

    }

    //end of game method for starting WinnerActivity
    public void gameOver(){
        if (teamOne_int == Math.max(teamOne_int,teamTwo_int)){
            win_team = "TEAM 1";
        }
        else{
            win_team = "TEAM 2";
        }

        //passing data from main to winner activities
        Intent intent = new Intent(this, WinnerActivity.class);
        intent.putExtra(EXTRA_MESSAGE, win_team);
        intent.putExtra(EXTRA_INT1,teamOne_int);
        intent.putExtra(EXTRA_INT2, teamTwo_int);
        startActivity(intent);
    }


}
