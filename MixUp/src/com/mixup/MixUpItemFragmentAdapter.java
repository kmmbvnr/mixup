package com.mixup;

import com.mixup.utils.ScrollableFragmentAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class MixUpItemFragmentAdapter extends ScrollableFragmentAdapter<MixUpItemFragment> {

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

	@Override
	public MixUpItemFragment[] getFragments() {
		return mFragments;
	}

}
