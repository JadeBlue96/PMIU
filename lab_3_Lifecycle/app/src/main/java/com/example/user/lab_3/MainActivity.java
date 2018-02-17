package com.example.user.lab_3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

    private static final String TAG= "MainActivity";
    private static final String RES_CANCEL="NO_RES_RETURNED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logTextView=(TextView)findViewById(R.id.id_logTextView);
        logTextView.setMovementMethod(new ScrollingMovementMethod());

    }

    public void onButtonClicked(View view) {
        Intent intent=new Intent(this,SecondActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
                Log.d(TAG,result);
                logTextView.append(result+" ");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                Log.d(TAG,RES_CANCEL);
                logTextView.append(RES_CANCEL+" ");
            }
        }
    }
}
