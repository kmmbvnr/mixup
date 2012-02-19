package com.mixup;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

public class PuzzleActivity extends FragmentActivity {
	private Timer mTimer;
	private TextView mTimerText;
	private long mStartTime;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzle);
       
        mTimerText = (TextView)findViewById(R.id.timer);
        mTimer = new Timer();
        
        mStartTime = System.currentTimeMillis();
        mTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				PuzzleActivity.this.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						long diff = (System.currentTimeMillis() - mStartTime)/1000;
						long minutes = diff / 60;
						long seconds = diff % 60;
						mTimerText.setText(String.format("%d:%02d", minutes%10, seconds));
					}
				});
			}
		}, 0, 1000);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mTimer.cancel();
	}
}
