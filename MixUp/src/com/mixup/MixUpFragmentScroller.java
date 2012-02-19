package com.mixup;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public class MixUpFragmentScroller implements OnPageChangeListener {

	private ViewPager mViewPager;
	private FragmentPagerAdapter mPagerAdapter;
	private IScrollerPageChangeListener mPagerListener;

	public MixUpFragmentScroller(ViewPager viewPager, FragmentPagerAdapter pagerAdapter) {
		mViewPager = viewPager;
		mPagerAdapter = pagerAdapter;
		mViewPager.setOnPageChangeListener(this);
		mViewPager.setOffscreenPageLimit(mPagerAdapter.getCount()/2);
	}

	@Override
	public void onPageScrollStateChanged(int state) {
		int itemCount = mPagerAdapter.getCount();
		
		 if (state == ViewPager.SCROLL_STATE_IDLE) {
			 int currentItem = mViewPager.getCurrentItem();
			 if(currentItem == 0) {
				 mViewPager.setCurrentItem(itemCount-2, false);
			 }
			 else if(currentItem == itemCount-1) {
				 mViewPager.setCurrentItem(1, false);				 
			 }
		 }	
	}
	
	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

	@Override
	public void onPageSelected(int position) {
		mPagerListener.onScrollerPageChanged();
	}

	public void setPagerListener(IScrollerPageChangeListener mPagerListener) {
		this.mPagerListener = mPagerListener;
	}

	public IScrollerPageChangeListener getPagerListener() {
		return mPagerListener;
	}	
	


}
