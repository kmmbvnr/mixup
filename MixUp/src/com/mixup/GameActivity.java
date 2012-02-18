package com.mixup;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GameActivity extends FragmentActivity {

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
}
