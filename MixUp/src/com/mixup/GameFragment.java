package com.mixup;

import java.util.Random;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GameFragment extends Fragment implements IScrollerPageChangeListener {
	public static Integer[] topImagesArray = new Integer[] {
		R.drawable.goose_head,
		R.drawable.cat_head,
		R.drawable.horse_head
	};

	public static Integer[] middleImagesArray = new Integer[] {
		R.drawable.goose_body,
		R.drawable.cat_body,
		R.drawable.horse_body,
	};
	
	public static Integer[] bottomImagesArray = new Integer[] {
		R.drawable.goose_feet,
		R.drawable.cat_feet,
		R.drawable.horse_feet
	};

	private ViewPager mTopPager;
	private ViewPager mMiddlePager;
	private ViewPager mBottomPager;
	private Random mRandom;
	private IGameStateListener mStateListener;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
	
		View view = inflater.inflate(R.layout.game_fragment, container, false);

		MixUpItemFragmentAdapter topAdapter = new MixUpItemFragmentAdapter(getFragmentManager(), R.layout.mixup_item_top, topImagesArray);
		mTopPager = (ViewPager)view.findViewById(R.id.top_pager);
		mTopPager.setAdapter(topAdapter);
		mTopPager.setCurrentItem(1, false);
		MixUpFragmentScroller topScroller = new MixUpFragmentScroller(mTopPager, topAdapter);
		topScroller.setPagerListener(this);
		
		MixUpItemFragmentAdapter middleAdapter = new MixUpItemFragmentAdapter(getFragmentManager(), R.layout.mixup_item_middle, middleImagesArray);
		mMiddlePager = (ViewPager)view.findViewById(R.id.middle_pager);
		mMiddlePager.setAdapter(middleAdapter);
		mMiddlePager.setCurrentItem(1, false);
		MixUpFragmentScroller middleScroller = new MixUpFragmentScroller(mMiddlePager, middleAdapter);
		middleScroller.setPagerListener(this);
		
		MixUpItemFragmentAdapter bottomAdapter = new MixUpItemFragmentAdapter(getFragmentManager(), R.layout.mixup_item_bottom, bottomImagesArray);
		mBottomPager = (ViewPager)view.findViewById(R.id.bottom_pager);
		mBottomPager.setAdapter(bottomAdapter);
		mBottomPager.setCurrentItem(1, false);
		MixUpFragmentScroller bottomScroller = new MixUpFragmentScroller(mBottomPager, bottomAdapter);
		bottomScroller.setPagerListener(this);
		
		mRandom = new Random();
		
		return view;
	}
	
	public void shuffleImages() {
		mTopPager.setCurrentItem(mRandom.nextInt()%topImagesArray.length, true);
		mMiddlePager.setCurrentItem(mRandom.nextInt()%middleImagesArray.length, true);
		mBottomPager.setCurrentItem(mRandom.nextInt()%bottomImagesArray.length, true);
	}
	
	private int decodeCurrentPosition(int position) {
		if (position==0)
			return topImagesArray.length-1;
		if (position==topImagesArray.length+1)
			return 0;
		return position-1;
		
	}

	private void notifyListener() {
		int topImageIndex = decodeCurrentPosition(mTopPager.getCurrentItem());
		int middleImageIndex = decodeCurrentPosition(mMiddlePager.getCurrentItem());
		int bottomImageIndex = decodeCurrentPosition(mBottomPager.getCurrentItem());
		mStateListener.stateGhanged(new GameState(topImagesArray[topImageIndex], 
				middleImagesArray[middleImageIndex], 
				bottomImagesArray[bottomImageIndex]));
	}


	public void setStateListener(IGameStateListener mStateListener) {
		this.mStateListener = mStateListener;
		notifyListener();
	}


	public IGameStateListener getStateListener() {
		return mStateListener;
	}

	@Override
	public void onScrollerPageChanged() {
		notifyListener();	
	}

}

