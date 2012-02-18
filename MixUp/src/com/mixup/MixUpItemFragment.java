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
	private int layoutId;
	private int imageId;
	
	public MixUpItemFragment(int layoutId_, Integer imageId_) {
		super();
		layoutId = layoutId_;
		imageId = imageId_;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		setRetainInstance(true);
        mView = inflater.inflate(layoutId, container, false);   
        mMixUpImageView = (ImageView)mView.findViewById(R.id.item_image);
        mMixUpImageView.setImageResource(imageId);

		return mView;
    }
	
	@Override
	public void onDestroyView() {
		mView = null;
		super.onDestroyView();
	}
}
