package com.mixup;


import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

public class PuzzleActivity extends FragmentActivity {
	private Timer mTimer;
	private TextView mTimerText;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzle);
       
        mTimerText = (TextView)findViewById(R.id.timer);
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				
				
			}
		}, 0, 1000);
	}
}
