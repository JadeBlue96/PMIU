package com.example.user.lab_3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends BaseActivity{

    private static final String TAG= "SecondActivity";
    private static final String RES_CANCEL="NO_RES_RETURNED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_second);
        logTextView=(TextView)findViewById(R.id.id_logTextViewSec);
    }

    public void onReturnClicked(View view) {
        EditText ret_val;
        ret_val=(EditText) findViewById(R.id.id_editText);
        String passback=ret_val.getText().toString();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",passback);
        setResult(Activity.RESULT_OK,returnIntent);
        Log.d(TAG,passback);
        logTextView.append(passback+" ");
        finish();
    }

    public void onFinishClicked(View view) {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        Log.d(TAG,RES_CANCEL);
        logTextView.append(RES_CANCEL+" ");
        finish();
    }
}
