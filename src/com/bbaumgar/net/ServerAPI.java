package com.bbaumgar.net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.firebase.security.token.TokenGenerator;

import android.app.Activity;
import android.util.Log;

public class ServerAPI {
	private String auth;

	public static ServerAPI getInstance() {
		return new ServerAPI();
	}

	private ServerAPI() {
		
	}

	/**
	 * Submit a user profile to the server.
	 * 
	 * @param facebookID
	 * @param profile
	 *            The profile to submit
	 * @param callback
	 *            The callback to execute when submission status is available.
	 */
	public void sendLocationData(double lat, double lon, int str, final PostCallback callback) {
		String restUrl = Utils.constructRestUrlForSendData();
		String requestBody = Utils.serializeSendData(lat, lon, str);
		new PostTask(restUrl, requestBody, "Send Data",
				new RestTaskCallback() {
					@Override
					public void onTaskComplete(String response) {
						callback.onDataReceived(response);
					}
				}).execute();
	}
}