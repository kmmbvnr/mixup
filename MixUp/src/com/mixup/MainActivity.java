package com.mixup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
    private Button mWhoBtn;
	private Button mPuzzleBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     
        setContentView(R.layout.main);

        mWhoBtn = (Button)findViewById(R.id.btn_who);
        mWhoBtn.setOnClickListener(this);
        
        mPuzzleBtn = (Button)findViewById(R.id.btn_puzzle);
        mPuzzleBtn.setOnClickListener(this);
    }

	@Override
	public void onClick(View view) {
		if(view == mWhoBtn) {
			startActivity(new Intent(this, GameActivity.class));
		} else if(view == mPuzzleBtn) {
			startActivity(new Intent(this, PuzzleActivity.class));
		}
	}
    

}