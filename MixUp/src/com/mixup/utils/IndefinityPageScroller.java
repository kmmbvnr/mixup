package com.mixup.utils;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public class IndefinityPageScroller<T extends Fragment> implements OnPageChangeListener{

	private ViewPager mPager;
	private ScrollableFragmentAdapter<T> mAdapter;

	public IndefinityPageScroller(ViewPager pager, ScrollableFragmentAdapter<T> adapter) {
		mPager = pager;
		mAdapter = adapter;
		pager.setOnPageChangeListener(this);
	}
	
	@Override
	public void onPageScrollStateChanged(int state) {
		 if (state == ViewPager.SCROLL_STATE_IDLE) {
			 int currentItem = mPager.getCurrentItem();
			 if(currentItem == 0) {
				 mAdapter.scrollBack();
				 mAdapter.notifyDataSetChanged();
				 mPager.setCurrentItem(1, false);
			 }
			 else if(currentItem == mAdapter.getCount()-1) {
				 mAdapter.scrollForward();
				 mAdapter.notifyDataSetChanged();
				 mPager.setCurrentItem(mAdapter.getCount()-2, false);				 
			 }
		 }
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

	@Override
	public void onPageSelected(int position) {}

}
