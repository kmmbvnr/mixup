package com.mixup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
    private Button mWhoBtn;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     
        setContentView(R.layout.main);

        mWhoBtn = (Button)findViewById(R.id.btn_who);
        mWhoBtn.setOnClickListener(this);
    }

	@Override
	public void onClick(View view) {
		if(view == mWhoBtn) {
			startActivity(new Intent(this, GameActivity.class));
		}
		
	}
    

}