package cc.wthr.mixup;

import cc.wthr.mixup.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
    private Button mWhoBtn;
	private Button mPuzzleBtn;
	private Button mAboutBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     
        setContentView(R.layout.main);

        mWhoBtn = (Button)findViewById(R.id.btn_who);
        mWhoBtn.setOnClickListener(this);
        
        mPuzzleBtn = (Button)findViewById(R.id.btn_puzzle);
        mPuzzleBtn.setOnClickListener(this);
        
        mAboutBtn = (Button)findViewById(R.id.btn_about);
        mAboutBtn.setOnClickListener(this);
        
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

	@Override
	public void onClick(View view) {
		if(view == mWhoBtn) {
			startActivity(new Intent(this, GameActivity.class));
		} else if(view == mPuzzleBtn) {
			startActivity(new Intent(this, PuzzleActivity.class));
		} else if(view == mAboutBtn) {
			startActivity(new Intent(this, AboutActivity.class));
		}
	}
    

}