package cc.wthr.mixup;

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
			
			int topAnimal = SoundManager.ANIMAL_NAMES.get(mTopImageId);
			int bottomAnimal = SoundManager.ANIMAL_NAMES.get(mBottomImageId);
			int middleAnimal = SoundManager.ANIMAL_NAMES.get(mMiddleImageId);
			int otherMiddleAnimal = SoundManager.ANIMAL_NAMES.get(other.mMiddleImageId);
			
			if(middleAnimal == topAnimal || middleAnimal == bottomAnimal) {
				return mTopImageId.equals(other.mTopImageId)
						&& mBottomImageId.equals(other.mBottomImageId)
						&& (otherMiddleAnimal == topAnimal || otherMiddleAnimal == bottomAnimal);
			} else {
				return mTopImageId.equals(other.mTopImageId)
						&& mMiddleImageId.equals(other.mMiddleImageId)
						&& mBottomImageId.equals(other.mBottomImageId);
			}
		}
		return false;
	}
}
