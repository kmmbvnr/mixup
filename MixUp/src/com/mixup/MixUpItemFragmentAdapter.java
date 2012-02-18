package com.mixup;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MixUpItemFragmentAdapter extends FragmentPagerAdapter {

	private MixUpItemFragment[] mFragments;
	
	public MixUpItemFragmentAdapter(FragmentManager fm) {
		super(fm);
		
		mFragments = new MixUpItemFragment [] {
			new MixUpItemFragment(),
			new MixUpItemFragment(),
			new MixUpItemFragment()
		};
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
