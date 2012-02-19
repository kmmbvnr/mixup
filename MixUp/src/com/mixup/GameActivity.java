package com.mixup;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
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
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

	}
     
	@Override
	  protected void onResume() {
	    super.onResume();
	    mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
	  }

	  @Override
	  protected void onStop() {
	    mSensorManager.unregisterListener(mSensorListener);
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
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	/* put this into your activity class */
	  private SensorManager mSensorManager;
	  private float mAccel; // acceleration apart from gravity
	  private float mAccelCurrent; // current acceleration including gravity
	  private float mAccelLast; // last acceleration including gravity
	  private final SensorEventListener mSensorListener = new SensorEventListener() {

		  public void onSensorChanged(SensorEvent se) {
			  float x = se.values[0];
			  float y = se.values[1];
			  float z = se.values[2];
			  mAccelLast = mAccelCurrent;
			  mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
			  float delta = mAccelCurrent - mAccelLast;
			  mAccel = mAccel * 0.9f + delta; // perform low-cut filter
			  
			  if (mAccel > 2) {
				  shuffleImages();
				  
			  }
		  }

		  public void onAccuracyChanged(Sensor sensor, int accuracy) {
		  }
	  };

	protected void shuffleImages() {
		mGameFragment.shuffleImages();
	}
}
