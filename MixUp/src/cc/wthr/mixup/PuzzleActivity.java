package cc.wthr.mixup;

import java.util.Timer;
import java.util.TimerTask;

import cc.wthr.mixup.R;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class PuzzleActivity extends FragmentActivity implements OnClickListener, android.view.View.OnClickListener, IGameStateListener {
	private static final int[][] LEVELS = {
		{ 300, 1 },
		{ 250, 1 },
		{ 200, 1 },
		{ 150, 2 },
		{ 100, 2 },
		{  50, 2 },
		{  30, 3 },
		{  20, 3 },
		{  10, 3 }
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
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC); 
	}
	
	@Override
	protected void onStart() {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				PuzzleActivity.this.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						startNewLevel();
					}
				});
			}
		}, 500);
        super.onStart();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		mTimer.cancel();
	}
	
	public void startNewLevel() {
		if(mCurrentPuzzle == null) {
			mCurrentPuzzle = new Puzzle(LEVELS[mCurrentLevel][1]);
			mSoundManager.playPuzzleSound(mCurrentPuzzle.getPuzzle(), this);
		} else {
			Puzzle newPuzzle = new Puzzle(LEVELS[mCurrentLevel][1]);
			while (newPuzzle.getPuzzle().equals(mCurrentPuzzle.getPuzzle())) {
				newPuzzle = new Puzzle(LEVELS[mCurrentLevel][1]);
			}
			mCurrentPuzzle = newPuzzle;
			mSoundManager.playNextPuzzleSound(mCurrentPuzzle.getPuzzle(), this);			
		}
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
		if(outOfTime) {
			mTimer.cancel();
			mCurrentLevel = 0;
			mCurrentPuzzle = null;
			mSoundManager.playTimeoutSound(this);
			AlertDialog dialog = new AlertDialog.Builder(this).create();
			dialog.setMessage(getString(R.string.time_is_out));
			dialog.setButton(getString(R.string.try_again), this);
			dialog.setButton2(getString(R.string.finish), this);
			dialog.show();
		} else {
			mSoundManager.playNotCorrectPuzzleSound(mCurrentPuzzle.getPuzzle(), this);
		}
	}
	
	public void Win() {
		mTimer.cancel();
		mCurrentLevel ++;
		if(mCurrentLevel < LEVELS.length) { 
			startNewLevel();
		} else {
			mSoundManager.playWinSound(this);
			AlertDialog dialog = new AlertDialog.Builder(this).create();
			dialog.setMessage(getString(R.string.you_win));
			dialog.setButton(getString(R.string.try_again), this);
			dialog.setButton2(getString(R.string.finish), this);
			dialog.show();			
			mCurrentLevel = 0;		
			mCurrentPuzzle = null;
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
