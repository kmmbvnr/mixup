package com.mixup;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MixUpItemFragmentAdapter extends FragmentPagerAdapter {

	private MixUpItemFragment[] mFragments;
	
	public MixUpItemFragmentAdapter(FragmentManager fm, int fragmentLayoutId, Integer[] imagesIdArray) {
		super(fm);
		
		mFragments = new MixUpItemFragment [imagesIdArray.length]; 
		for (int i=0; i<imagesIdArray.length; i++){
			mFragments[i] = new MixUpItemFragment(fragmentLayoutId, imagesIdArray[i]);
		}
	}

	@Override
	public Fragment getItem(int position) {
		return mFragments[position % mFragments.length];
	}

	@Override
	public int getCount() {
		return mFragments.length;
	}

}
