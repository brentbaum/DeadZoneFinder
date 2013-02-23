package com.bbaumgar.net;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;

/**
 * 
 * Class definition for a callback to be invoked when the response for the data 
 * submission is available.
 * 
 */
public abstract class PostCallback{
	Activity current;
	
    /**
     * Called when a POST success response is received. <br/>
     * This method is guaranteed to execute on the UI thread.
     */
    public void onPostSuccess() {
	}

	public void onDataReceived(String response) {}
}