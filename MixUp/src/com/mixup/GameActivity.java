package com.mixup;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class GameActivity extends FragmentActivity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     
        setContentView(R.layout.game);

    }
}
