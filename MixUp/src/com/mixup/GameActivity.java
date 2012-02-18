package com.mixup;

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
			//	button.setSelected(true);
			}
        });
    }
}
