package cc.wthr.mixup;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MixUpItemFragmentAdapter extends FragmentPagerAdapter {

	private MixUpItemFragment[] mFragments;
	
	public MixUpItemFragmentAdapter(FragmentManager fm, int fragmentLayoutId, Integer[] imagesIdArray) {
		super(fm);
		
		mFragments = new MixUpItemFragment [imagesIdArray.length+2]; 
		for (int i=0; i<imagesIdArray.length; i++){
			mFragments[i+1] = new MixUpItemFragment(fragmentLayoutId, imagesIdArray[i]);
		}
		mFragments[0] = new MixUpItemFragment(fragmentLayoutId, imagesIdArray[imagesIdArray.length-1]);
		mFragments[imagesIdArray.length+1] = new MixUpItemFragment(fragmentLayoutId, imagesIdArray[0]);
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
