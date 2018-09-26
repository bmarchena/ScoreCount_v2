package bryanmarchena.scorecount;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ShareActivity extends AppCompatActivity {

    //strings for checking permissions
    public static final int REQUEST_CALL_PHONE = 1;
    public static final int REQUEST_MSG_PHONE = 2;

    //declaration of widgets
    private EditText etPhoneNum;
    private Button btMakeCall, btSendMsg, btSearch;
    private String callNum, sendNum, winTeam;
    private int score;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        //find and cast widgets
        etPhoneNum = (EditText)findViewById(R.id.editText_getPhoneNum);
        btMakeCall = (Button)findViewById(R.id.button_makeCall);
        btSendMsg = (Button)findViewById(R.id.button_sendMsg);
        btSearch = (Button)findViewById(R.id.button_searchBB);

        //getting intent and values from WinnerActivity
        Intent shareIntent = getIntent();
        score = shareIntent.getIntExtra(WinnerActivity.EXTRA_SCORE, 0);
        winTeam = shareIntent.getStringExtra(WinnerActivity.EXTRA_WINNER);

        //override onClick for btMakeCall to callFriend
        btMakeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFriend();
            }
        });


        //override onClick for btSendMsg to msgFriend
        btSendMsg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                msgFriend();
            }
        });

        //override onClick for btSearch to searchBB
        btSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                searchBB();
            }
        });



    }


    private void callFriend() {
        callNum = "tel:" + etPhoneNum.getText().toString();
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(callNum));

        //checking permissions
        if (callIntent.resolveActivity(getPackageManager()) != null);{
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE );
            }

            else{
                startActivity(callIntent);
            }
        }
    }

    private void msgFriend() {
        //get number with sms tag
        sendNum = "sms:" + etPhoneNum.getText().toString();

        //construct msg string
        String msg = winTeam + " won at ScoreCount by: " + score;

        //sending message
        Intent msgIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse(sendNum));
        msgIntent.putExtra("sms_body", msg);
        startActivity(msgIntent);

        //checking permissions
        if (msgIntent.resolveActivity(getPackageManager()) != null);{
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.SEND_SMS}, REQUEST_MSG_PHONE);
            }

            else{
                startActivity(msgIntent);
            }
        }

    }

    private void searchBB() {
        //search query for nearby basketball
        Uri search = Uri.parse("geo:0,0?q=basketball near me");

        //starting search
        Intent searchIntent = new Intent(Intent.ACTION_VIEW, search);
        startActivity(searchIntent);
    }


}
