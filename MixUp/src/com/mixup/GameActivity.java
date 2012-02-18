package com.mixup;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class GameActivity extends FragmentActivity {
	private static Integer[] topImagesArray = new Integer[] {
		R.drawable.duck_head,
		R.drawable.cat_head,
		R.drawable.horse_head
	};

	private static Integer[] middleImagesArray = new Integer[] {
		R.drawable.duck_body,
		R.drawable.cat_body,
		R.drawable.horse_body,
	};
	
	private static Integer[] bottomImagesArray = new Integer[] {
		R.drawable.duck_feet,
		R.drawable.cat_feet,
		R.drawable.horse_feet
	};

	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.game);

		MixUpItemFragmentAdapter topAdapter = new MixUpItemFragmentAdapter(getSupportFragmentManager(), R.layout.mixup_item_top, topImagesArray);
		ViewPager topPager = (ViewPager)findViewById(R.id.top_pager);
		topPager.setAdapter(topAdapter);
		topPager.setCurrentItem(1, false);
		new MixUpFragmentScroller(topPager, topAdapter);
		
		MixUpItemFragmentAdapter middleAdapter = new MixUpItemFragmentAdapter(getSupportFragmentManager(), R.layout.mixup_item_middle, middleImagesArray);
		ViewPager middlePager = (ViewPager)findViewById(R.id.middle_pager);
		middlePager.setAdapter(middleAdapter);
		middlePager.setCurrentItem(1, false);
		new MixUpFragmentScroller(middlePager, middleAdapter);
		
		MixUpItemFragmentAdapter bottomAdapter = new MixUpItemFragmentAdapter(getSupportFragmentManager(), R.layout.mixup_item_bottom, bottomImagesArray);
		ViewPager bottomPager = (ViewPager)findViewById(R.id.bottom_pager);
		bottomPager.setAdapter(bottomAdapter);
		bottomPager.setCurrentItem(1, false);
		new MixUpFragmentScroller(bottomPager, bottomAdapter);
	}

}

