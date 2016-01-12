package com.alexandreoliveira.startappifisnotberunning;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;

public class VerifyApp extends AsyncTask<Void, Void, Void> {

	private Context context;

	public VerifyApp(Context context) {
		this.context = context;
	}

	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		String mainActivityName = getMainActivityName(context);
		String pacote = mainActivityName.replace(".MainActivity", "");

		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> procInfos = activityManager
				.getRunningAppProcesses();

		Log.i("AppAlexandre", "a main principal é: " + mainActivityName);
		boolean rodando = false;
		for (int i = 0; i < procInfos.size(); i++) {
			Log.i("AppAlexandre", procInfos.get(i).processName);
			if (procInfos.get(i).processName.equals(pacote)) {
				if (procInfos.get(i).importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
						|| procInfos.get(i).importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE) {
					Log.i("AppAlexandre", " " + pacote + " is running...");
					rodando = true;
					break;
				}

			}
		}

		if (!rodando) {
			Intent intent = context.getPackageManager()
					.getLaunchIntentForPackage(pacote);
			Log.i("AppAlexandre", "starting " + pacote);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);

		}
		/*
		 * Intent intent =
		 * context.getPackageManager().getLaunchIntentForPackage(pacote); if
		 * (intent != null) {
		 * 
		 * Log.i("AppAlexandre", "starting "+pacote);
		 * 
		 * // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		 * //context.startActivity(intent);
		 * 
		 * } else {
		 * 
		 * Log.i("AppAlexandre", pacote+" is running");
		 * 
		 * }
		 */

		return null;
	}

	private String getMainActivityName(Context context) {
		try {
			PackageManager pm = context.getPackageManager();
			ActivityInfo[] atividades = pm.getPackageInfo(
					context.getPackageName(), PackageManager.GET_ACTIVITIES).activities;
			if (atividades.length > 0) {
				return atividades[0].name;
			}
			return context.getPackageName() + ".MainActivity";
		} catch (Exception e) {
			// TODO: handle exception
			return context.getPackageName() + ".MainActivity";
		}

	}

}
