package bryanmarchena.scorecount;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class WinnerActivity extends AppCompatActivity {

    //declarations of widgets
    private TextView textView_win;
    private Button button_startShare;
    private String WinningTeam;
    private int score_Spread;
    private Intent shareActivity;

    //declarations of strings for intent passing
    public static final String EXTRA_WINNER = "com.bryanmarchena.scorecount.EXTRA_WINNER";
    public static final String EXTRA_SCORE = "com.bryanmarchena.scorecount.EXTRA_SCORE";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        //find and cast textview and button
        textView_win = (TextView)findViewById(R.id.winner_text);
        button_startShare = (Button)findViewById(R.id.button_shareAct);
        shareActivity = new Intent(this, ShareActivity.class);

        //setting and overriding onClick
        button_startShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareActivity.putExtra(EXTRA_WINNER, WinningTeam);
                shareActivity.putExtra(EXTRA_SCORE, score_Spread);

                //starting the ShareActivity
                startActivity(shareActivity);
            }
        });

        //get data from intent
        Intent intent = getIntent();
        int teamOne_int = intent.getIntExtra(MainActivity.EXTRA_INT1, 0);
        int teamTwo_int = intent.getIntExtra(MainActivity.EXTRA_INT2, 0);
        WinningTeam = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //calculate the score spread
        score_Spread = (Math.max(teamOne_int,teamTwo_int) - Math.min(teamOne_int,teamTwo_int));

        //prints the winner and spread
        textView_win.setText(WinningTeam + " won by: " + score_Spread);


    }

}