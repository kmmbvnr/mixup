package com.mixup;

public class GameState {
	
	private Integer mTopImageId;
	private Integer mMiddleImageId;
	private Integer mBottomImageId;
	
	public GameState (int topImageId, int middleImageId, int bottomImageId) {
		this.mTopImageId = topImageId;
		this.mMiddleImageId = middleImageId;
		this.mBottomImageId = bottomImageId;
	}
	
	public GameState() {
	}

	@Override
	public int hashCode() {
		return mTopImageId.hashCode() + mMiddleImageId.hashCode() + mBottomImageId.hashCode();
	}

	public void setTopImageId(int mTopImageId) {
		this.mTopImageId = mTopImageId;
	}

	public int getTopImageId() {
		return mTopImageId;
	}

	public void setMiddleImageId(int mMiddleImageId) {
		this.mMiddleImageId = mMiddleImageId;
	}

	public int getMiddleImageId() {
		return mMiddleImageId;
	}

	public void setBottomImageId(int mBottomImageId) {
		this.mBottomImageId = mBottomImageId;
	}

	public int getBottomImageId() {
		return mBottomImageId;
	}

	@Override
	public boolean equals(Object o) {
		if(o != null && o instanceof GameState) {
			GameState other = (GameState)o;
			return mTopImageId == other.mTopImageId 
					&& mMiddleImageId == other.mMiddleImageId
					&& mBottomImageId == other.mBottomImageId;
		}
		return false;
	}
}
