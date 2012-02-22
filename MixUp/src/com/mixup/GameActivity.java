package com.mixup;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GameActivity extends FragmentActivity implements IGameStateListener, SensorEventListener {

	private int mTopSelectedImageId;
	private int mMiddleSelectedImageId;
	private int mBottomSelectedImageId;
	private GameState mGameState;
	private SoundManager soundManager;
	private GameFragment mGameFragment;
	private SensorManager mSensorManager;
	private float mAccel;        // acceleration apart from gravity
	private float mAccelCurrent; // current acceleration including gravity
	private float mAccelLast;    // last acceleration including gravity
	private Timer mAccelTimer;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        
        soundManager = new SoundManager();
    
        FragmentManager fragmentManager = getSupportFragmentManager();
        mGameFragment = (GameFragment)fragmentManager.findFragmentById(R.id.game_fragment);
        mGameFragment.setStateListener(this);
        
        final Button button = (Button) findViewById(R.id.who_am_i_button);
        button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				soundManager.playStateSound(mGameState, GameActivity.this);
			}
        });

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
	}
     
	@Override
	protected void onResume() {
	    super.onResume();
	    mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onStop() {
	    mSensorManager.unregisterListener(this);
	    super.onStop();
	}


	@Override
	public void stateGhanged(GameState state) {
		setGameState(state);
	}

	public void setTopSelectedImageId(int mTopSelectedImageId) {
		this.mTopSelectedImageId = mTopSelectedImageId;
	}

	public int getTopSelectedImageId() {
		return mTopSelectedImageId;
	}

	public void setMiddleSelectedImageId(int mMiddleSelectedImageId) {
		this.mMiddleSelectedImageId = mMiddleSelectedImageId;
	}

	public int getMiddleSelectedImageId() {
		return mMiddleSelectedImageId;
	}

	public void setBottomSelectedImageId(int mBottomSelectedImageId) {
		this.mBottomSelectedImageId = mBottomSelectedImageId;
	}
 
	public int getBottomSelectedImageId() {
		return mBottomSelectedImageId;
	}

	public void setGameState(GameState gameState) {
		this.mGameState = gameState;
	}

	public GameState getGameState() {
		return mGameState;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {}

	@Override
	public void onSensorChanged(SensorEvent se) {
		  float x = se.values[0];
		  float y = se.values[1];
		  float z = se.values[2];
		  mAccelLast = mAccelCurrent;
		  mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
		  float delta = mAccelCurrent - mAccelLast;
		  mAccel = mAccel * 0.9f + delta; // perform low-cut filter
		  
		  if (mAccel > 2) {
			  if(mAccelTimer != null) {
				 mAccelTimer.cancel();
			  }
			  mAccelTimer = new Timer();
			  mAccelTimer.schedule(new TimerTask() {
				@Override
				public void run() {
					GameActivity.this.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							shuffleImages();
							mAccelTimer=null;
						}
					});				
				}
			}, 500);
		  }
	}
	
	protected void shuffleImages() {
		mGameFragment.shuffleImages();
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		mGameFragment.shuffleImages();
		return super.onKeyUp(keyCode, event);
	}
}
