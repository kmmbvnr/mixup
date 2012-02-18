package com.mixup;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class GameActivity extends FragmentActivity {
	private static Integer[] topImagesArray = new Integer[] {
		R.drawable.item_image_top,
		R.drawable.item_image_top
	};
	
	private static Integer[] middleImagesArray = new Integer[] {
		R.drawable.item_image_middle,
		R.drawable.item_image_middle
	};
	
	private static Integer[] bottomImagesArray = new Integer[] {
		R.drawable.item_image_bottom,
		R.drawable.item_image_bottom
	};

	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);

		MixUpItemFragmentAdapter topAdapter = new MixUpItemFragmentAdapter(getSupportFragmentManager(), R.layout.mixup_item_top, topImagesArray);
		ViewPager topPager = (ViewPager)findViewById(R.id.top_pager);
		topPager.setAdapter(topAdapter);
		
		MixUpItemFragmentAdapter middleAdapter = new MixUpItemFragmentAdapter(getSupportFragmentManager(), R.layout.mixup_item_middle, middleImagesArray);
		ViewPager middlePager = (ViewPager)findViewById(R.id.middle_pager);
		middlePager.setAdapter(middleAdapter);
		
		MixUpItemFragmentAdapter bottomAdapter = new MixUpItemFragmentAdapter(getSupportFragmentManager(), R.layout.mixup_item_bottom, bottomImagesArray);
		ViewPager bottomPager = (ViewPager)findViewById(R.id.bottom_pager);
		bottomPager.setAdapter(bottomAdapter);
	}
}

