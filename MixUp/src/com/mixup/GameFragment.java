package com.mixup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GameFragment extends Fragment implements OnPageChangeListener {
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

	private ViewPager mTopPager;
	private ViewPager mMiddlePager;
	private ViewPager mBottomPager;
	
	private IGameStateListener mStateListener;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
	
		View view = inflater.inflate(R.layout.game_fragment, container, false);

		MixUpItemFragmentAdapter topAdapter = new MixUpItemFragmentAdapter(getFragmentManager(), R.layout.mixup_item_top, topImagesArray);
		mTopPager = (ViewPager)view.findViewById(R.id.top_pager);
		mTopPager.setAdapter(topAdapter);
		mTopPager.setCurrentItem(1, false);
		new MixUpFragmentScroller(mTopPager, topAdapter);
		
		MixUpItemFragmentAdapter middleAdapter = new MixUpItemFragmentAdapter(getFragmentManager(), R.layout.mixup_item_middle, middleImagesArray);
		mMiddlePager = (ViewPager)view.findViewById(R.id.middle_pager);
		mMiddlePager.setAdapter(middleAdapter);
		mMiddlePager.setCurrentItem(1, false);
		new MixUpFragmentScroller(mMiddlePager, middleAdapter);
		
		MixUpItemFragmentAdapter bottomAdapter = new MixUpItemFragmentAdapter(getFragmentManager(), R.layout.mixup_item_bottom, bottomImagesArray);
		mBottomPager = (ViewPager)view.findViewById(R.id.bottom_pager);
		mBottomPager.setAdapter(bottomAdapter);
		mBottomPager.setCurrentItem(1, false);
		new MixUpFragmentScroller(mBottomPager, bottomAdapter);
		
		return view;
	}


	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}


	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}


	@Override
	public void onPageSelected(int arg0) {
		mStateListener.stateGhanged(new GameState(topImagesArray[mTopPager.getCurrentItem()], 
				middleImagesArray[mMiddlePager.getCurrentItem()], 
				bottomImagesArray[mBottomPager.getCurrentItem()]));
	}


	public void setStateListener(IGameStateListener mStateListener) {
		this.mStateListener = mStateListener;
	}


	public IGameStateListener getStateListener() {
		return mStateListener;
	}




}

