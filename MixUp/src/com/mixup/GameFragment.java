package com.mixup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GameFragment extends Fragment {
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

	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
	
		View view = inflater.inflate(R.layout.game_fragment, container, false);

		MixUpItemFragmentAdapter topAdapter = new MixUpItemFragmentAdapter(getFragmentManager(), R.layout.mixup_item_top, topImagesArray);
		ViewPager topPager = (ViewPager)view.findViewById(R.id.top_pager);
		topPager.setAdapter(topAdapter);
		topPager.setCurrentItem(1, false);
		new MixUpFragmentScroller(topPager, topAdapter);
		
		MixUpItemFragmentAdapter middleAdapter = new MixUpItemFragmentAdapter(getFragmentManager(), R.layout.mixup_item_middle, middleImagesArray);
		ViewPager middlePager = (ViewPager)view.findViewById(R.id.middle_pager);
		middlePager.setAdapter(middleAdapter);
		middlePager.setCurrentItem(1, false);
		new MixUpFragmentScroller(middlePager, middleAdapter);
		
		MixUpItemFragmentAdapter bottomAdapter = new MixUpItemFragmentAdapter(getFragmentManager(), R.layout.mixup_item_bottom, bottomImagesArray);
		ViewPager bottomPager = (ViewPager)view.findViewById(R.id.bottom_pager);
		bottomPager.setAdapter(bottomAdapter);
		bottomPager.setCurrentItem(1, false);
		new MixUpFragmentScroller(bottomPager, bottomAdapter);
		
		return view;
	}

}

