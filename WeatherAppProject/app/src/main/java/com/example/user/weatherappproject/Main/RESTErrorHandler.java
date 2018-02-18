package com.example.user.weatherappproject.Main;

import android.util.Log;
import android.widget.Toast;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.net.SocketTimeoutException;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by User on 16/02/2018.
 */

public class RESTErrorHandler implements retrofit.ErrorHandler {

    protected final String TAG=getClass().getSimpleName();

    @Override
    public Throwable handleError(RetrofitError cause) {
        Response r=cause.getResponse();
        if(r!=null && r.getStatus()==401)
        {
            Log.e(TAG,"Error:",cause);
        }
        if(cause.getKind() == RetrofitError.Kind.NETWORK)
        {
            if(cause.getCause() instanceof SocketTimeoutException)
            {
                Log.e(TAG,"Connection timed out.");
            }
            else
            {
                Log.e(TAG,"No connection detected.");
            }
        }

        return cause;
    }
}
