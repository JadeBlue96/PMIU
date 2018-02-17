package com.example.user.weatherappproject;

import android.util.Log;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

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
        if(r.getStatus()==404)
        {
            Log.e(TAG,"Error-city not found:",cause);
        }
        return cause;
    }
}
