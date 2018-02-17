package com.example.user.lab_3;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by User on 14/02/2018.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected TextView logTextView;
    private static final String log_Start= "onStart";
    private static final String log_Resume= "onResume";
    private static final String log_Pause= "onPause";
    private static final String log_Stop= "onStop";
    private static final String log_Destroy= "onDestroy";
    private static final String TAG= "BaseActivity";

    @Override
    protected void onStart() {

        super.onStart();
        Log.d(TAG,log_Start);
        logTextView.append(log_Start+" ");
    }

    @Override
    protected void onResume() {

        super.onResume();
        Log.d(TAG,log_Resume);
        //logTextView.append(TAG);
       logTextView.append(log_Resume+" ");
    }

    @Override
    protected void onPause() {

        super.onPause();
        Log.d(TAG,log_Pause);
        //logTextView.append(TAG);
        logTextView.append(log_Pause+" ");
    }

    @Override
    protected void onStop() {

        super.onStop();
        Log.d(TAG,log_Stop);
        //logTextView.append(TAG);
        logTextView.append(log_Stop+" ");
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        Log.d(TAG,log_Destroy);
        //logTextView.append(TAG);
        logTextView.append(log_Destroy+" ");
    }
}
