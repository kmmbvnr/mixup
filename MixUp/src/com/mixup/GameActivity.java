package com.mixup;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GameActivity extends FragmentActivity implements IGameStateListener {

	private int mTopSelectedImageId;
	private int mMiddleSelectedImageId;
	private int mBottomSelectedImageId;
	private GameState mGameState;
	private SoundManager soundManager;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        FragmentManager fragmentManager = getSupportFragmentManager();
        GameFragment gameFragment = (GameFragment)fragmentManager.findFragmentById(R.id.game_fragment);
        gameFragment.setStateListener(this);
        
        soundManager = new SoundManager();
        mGameState = new GameState();
        
        final Button button = (Button) findViewById(R.id.who_am_i_button);
        button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				soundManager.playStateSound(mGameState, GameActivity.this);
			}
        });
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
}
