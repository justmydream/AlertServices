package com.example.alertservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;

public class ScreenReceiver extends BroadcastReceiver{

	public static boolean wasScreenOn = true;
	public static int screenCount=0;
	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF))
		{
			wasScreenOn = false;
			screenCount++;
			if(screenCount>=10)
				screenCount=0;
		}
		
		else if(intent.getAction().equals(Intent.ACTION_SCREEN_ON))
		{
			wasScreenOn = true;
			screenCount++;
			if(screenCount>=10)
				screenCount=0;
			
		}
		
		
	}

}
