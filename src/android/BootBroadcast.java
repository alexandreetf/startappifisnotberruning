package com.alexandreoliveira.startappifisnotberunning;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootBroadcast extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		context.startService(new Intent(context,MyService.class));
		Log.i("AppAlexandre", "receiver plugin Start App is not be running");
	}

}
