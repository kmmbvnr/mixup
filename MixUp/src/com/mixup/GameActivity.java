package com.mixup;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GameActivity extends FragmentActivity implements IGameStateListener {

	private int mTopSelectedImageId;
	private int mMiddleSelectedImageId;
	private int mBottomSelectedImageId;
	private GameState mGameState;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     
        setContentView(R.layout.game);

        final Button button = (Button) findViewById(R.id.who_am_i_button);
        button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MediaPlayer mp = MediaPlayer.create(GameActivity.this, R.raw.sample);   
                mp.start();
                mp.setOnCompletionListener(new OnCompletionListener() {

                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        // TODO Auto-generated method stub
                        mp.release();
                    }

                });

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
