package com.mixup;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class GameActivity extends FragmentActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);

		MixUpItemFragmentAdapter topAdapter = new MixUpItemFragmentAdapter(getSupportFragmentManager());
		ViewPager topPager = (ViewPager)findViewById(R.id.top_pager);
		topPager.setAdapter(topAdapter);
		
		MixUpItemFragmentAdapter middleAdapter = new MixUpItemFragmentAdapter(getSupportFragmentManager());
		ViewPager middlePager = (ViewPager)findViewById(R.id.middle_pager);
		middlePager.setAdapter(middleAdapter);
		
		MixUpItemFragmentAdapter bottomAdapter = new MixUpItemFragmentAdapter(getSupportFragmentManager());
		ViewPager bottomPager = (ViewPager)findViewById(R.id.bottom_pager);
		bottomPager.setAdapter(bottomAdapter);
	}
}

