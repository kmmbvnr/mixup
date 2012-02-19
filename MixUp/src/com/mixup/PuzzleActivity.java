package com.mixup;

import java.util.Timer;
import java.util.TimerTask;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PuzzleActivity extends FragmentActivity implements OnClickListener, android.view.View.OnClickListener, IGameStateListener {
	private static final int[][] LEVELS = {
		{ 300, 1 },
		{ 250, 1 },
		{ 200, 1 },
		{ 150, 2 },
		{ 100, 2 },
		{ 50, 2 },
		{ 30, 3 },
		{ 20, 3 },
		{ 10, 3 }
	};
	
	private Timer mTimer;
	private TextView mTimerText;
	private long mStartTime;
	private Puzzle mCurrentPuzzle;
	private int mCurrentLevel = 0;

	private Button mCheckBtn;

	private GameState mState;
	
	private SoundManager mSoundManager;

	private GameFragment mGameFragment;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzle);
       
        mTimerText = (TextView)findViewById(R.id.timer);
        mCheckBtn = (Button)findViewById(R.id.check_button);
        mCheckBtn.setOnClickListener(this);
        
        FragmentManager fragmentManager = getSupportFragmentManager();
        mGameFragment = (GameFragment)fragmentManager.findFragmentById(R.id.game_fragment);
        mGameFragment.setStateListener(this);
        
        mSoundManager = new SoundManager();
		mGameFragment.shuffleImages();
	}
	
	@Override
	protected void onStart() {
        startNewLevel();
        super.onStart();
	}
	
	public void startNewLevel() {
		mCurrentPuzzle = new Puzzle(LEVELS[mCurrentLevel][1]);
		mSoundManager.playPuzzleSound(mCurrentPuzzle.getPuzzle(), this);
		mStartTime = System.currentTimeMillis();
		mTimerText.setTextColor(getResources().getColor(R.color.default_text_color));
		mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				PuzzleActivity.this.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						int levelDuration = LEVELS[mCurrentLevel][0];
						long diff = levelDuration - (System.currentTimeMillis() - mStartTime)/1000;
						if(diff < 0) {
							PuzzleActivity.this.Lose(true);
						} else {
							long minutes = diff / 60;
							long seconds = diff % 60;
							if(diff < levelDuration/5) {
								mTimerText.setTextColor(getResources().getColor(R.color.levelend_text_color));
							}
							mTimerText.setText(String.format("%d:%02d", minutes%10, seconds));
						}
					}
				});
			}
		}, 0, 1000);
	}
	
	public void Lose(boolean outOfTime) {
		mTimer.cancel();
		mCurrentLevel = 0;
		AlertDialog dialog = new AlertDialog.Builder(this).create();
		if(outOfTime) {
			dialog.setMessage("Время истекло");
		} else {
			dialog.setMessage("Неправильно");
		}
		dialog.setButton("Еще раз", this);
		dialog.setButton2("Закончить", this);
		dialog.show();
	}
	
	public void Win() {
		mTimer.cancel();
		mCurrentLevel ++;
		if(mCurrentLevel < LEVELS.length) { 
			startNewLevel();
		} else {
			mCurrentLevel = 0;
			AlertDialog dialog = new AlertDialog.Builder(this).create();
			dialog.setMessage("Вы победили!");
			dialog.setButton("Еще раз", this);
			dialog.setButton2("Закончить", this);
			dialog.show();			
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mTimer.cancel();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		switch (which) {
		case AlertDialog.BUTTON1:
			startNewLevel();
			break;
		case AlertDialog.BUTTON2:
			finish();
			break;
		}
	}

	@Override
	public void onClick(View view) {
		if(view == mCheckBtn) {
			mTimer.cancel();
			if(mCurrentPuzzle.check(mState)) {
				Win();
			} else {
				Lose(false);
			}
		}
	}

	@Override
	public void stateGhanged(GameState state) {
		mState = state;
	}
}
