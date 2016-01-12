package com.alexandreoliveira.startappifisnotberunning;


import java.util.Timer;
import java.util.TimerTask;



import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service{

	public TimerTask timerTask;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	 public int onStartCommand(Intent intent, int flags, int startId) {
		startTimer();
		 return super.onStartCommand(intent, flags, startId);
	}
	public void startTimer(){
		Timer timer  = new Timer();
		this.initiliazeTimerTask();
		timer.schedule(timerTask, 5000,30000);
		Log.i("AppAlexandre","Startando timer task");
	}
	public void initiliazeTimerTask() {
		
		Log.i("AppAlexandre","rodando Timer task");
		 timerTask = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				 new VerifyApp(getApplicationContext()).execute();
			}
		};
	}



}
