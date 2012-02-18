package com.mixup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MixUpItemFragment extends Fragment {
	
	private View mView;
	private ImageView mMixUpImageView;
	private int mLayoutId;
	private int mImageId;
	
	public MixUpItemFragment(int layoutId, Integer imageId) {
		super();
		this.mLayoutId = layoutId;
		this.mImageId = imageId;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		setRetainInstance(true);
        mView = inflater.inflate(mLayoutId, container, false);   
        mMixUpImageView = (ImageView)mView.findViewById(R.id.item_image);
        mMixUpImageView.setImageResource(mImageId);
		return mView;
    }
	
	@Override
	public void onDestroyView() {
		mView = null;
		super.onDestroyView();
	}
	
	
}
