package com.mixup.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public abstract class ScrollableFragmentAdapter<T extends Fragment> extends FragmentPagerAdapter {
	public ScrollableFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	public abstract T[] getFragments();

	@Override
	public Fragment getItem(int position) {
		return getFragments()[position];
	}

	@Override
	public int getCount() {
		return getFragments().length;
	}

	public void scrollBack() {
		T[] fragments = getFragments();
		T lastFragment = fragments[fragments.length-1];
		System.arraycopy(fragments, 0, fragments, 1, fragments.length-2);
		fragments[0] = lastFragment;
	}

	public void scrollForward() {
		T[] fragments = getFragments();
		T firstFragment = fragments[0];
		System.arraycopy(fragments, 1, fragments, 0, fragments.length-2);
		fragments[0] = firstFragment;
	}
}
