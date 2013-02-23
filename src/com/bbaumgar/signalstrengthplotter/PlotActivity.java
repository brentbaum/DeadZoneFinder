package com.bbaumgar.signalstrengthplotter;

import android.app.ActionBar;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ToggleButton;

import com.actionbarsherlock.app.SherlockActivity;

public class PlotActivity extends SherlockActivity {
	String TAG = "PlotSignalActivity";
	private RecorderService mBoundService;
	private ServiceConnection mConnection;
	private boolean mIsBound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_plot);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setTitle("Signal Data Collection");
		
		mConnection = new ServiceConnection() {
		    public void onServiceConnected(ComponentName className, IBinder service) {
		        mBoundService = ((RecorderService.RecorderBinder)service).getService();
		    }
		    public void onServiceDisconnected(ComponentName className) {
		        mBoundService = null;
		    }
		};
	}
	
	private void doBindService() {
	    // Establish a connection with the service.  We use an explicit
	    // class name because we want a specific service implementation that
	    // we know will be running in our own process (and thus won't be
	    // supporting component replacement by other applications).
	    bindService(new Intent(this, 
	            RecorderService.class), mConnection, Context.BIND_AUTO_CREATE);
	    mIsBound = true;
	}

	void doUnbindService() {
	    if (mIsBound) {
	        // Detach our existing connection.
	        unbindService(mConnection);
	        stopService(new Intent(this, RecorderService.class));
	        mIsBound = false;
	    }
	}

	@Override
	protected void onDestroy() {
	    super.onDestroy();
	    doUnbindService();
	}
	
	public void onToggleClick(View view) {
	    boolean on = ((ToggleButton) view).isChecked();
	    if(on) {
	    	doBindService();
	    }
	    if(!on) {
	    	doUnbindService();
	    }
	}
}
