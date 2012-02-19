package com.mixup;

import java.util.Random;


public class Puzzle {
	private GameState mPuzzle;
	
	public GameState getPuzzle() {
		return mPuzzle;
	}

	public void setPuzzle(GameState mPuzzle) {
		this.mPuzzle = mPuzzle;
	}

	private Random mRand;
	
	public Puzzle(int level) {
		mRand = new Random();
		
		switch (level) {
		case 1:
			initLevel1();
			break;
		case 2:
			initLevel2();
		case 3:
			initLevel3();
		}
	}
	
	/**
	 * Simple picture
	 */
	private void initLevel1() {
		int picture = mRand.nextInt(GameFragment.topImagesArray.length);
		mPuzzle = new GameState(
				GameFragment.topImagesArray[picture], 
				GameFragment.middleImagesArray[picture], 
				GameFragment.bottomImagesArray[picture]);
	}
	
	/**
	 * Two animals-picture
	 */
	private void initLevel2() {
		int firstPic = mRand.nextInt(GameFragment.topImagesArray.length);
		int lastPic = firstPic;
		int middlePic = firstPic;
	
		while(lastPic != firstPic) {
			lastPic = mRand.nextInt(GameFragment.topImagesArray.length);
		}
		
		Boolean isMiddleForFirst = mRand.nextBoolean();
		if(isMiddleForFirst) {
			middlePic = firstPic;
		} else {
			middlePic = lastPic;
		}
		
		mPuzzle = new GameState(
				GameFragment.topImagesArray[firstPic], 
				GameFragment.middleImagesArray[middlePic], 
				GameFragment.bottomImagesArray[lastPic]);
	}
	
	/**
	 * 3 different pictures
	 */
	private void initLevel3() {
		int firstPic = mRand.nextInt(GameFragment.topImagesArray.length);
		int lastPic = firstPic;
		int middlePic = firstPic;
		
		while(lastPic != firstPic) {
			lastPic = mRand.nextInt(GameFragment.topImagesArray.length);
		}
		
		middlePic = (GameFragment.topImagesArray.length+1)*GameFragment.topImagesArray.length/2 - firstPic - lastPic;
		
		mPuzzle = new GameState(
				GameFragment.topImagesArray[firstPic], 
				GameFragment.middleImagesArray[middlePic], 
				GameFragment.bottomImagesArray[lastPic]);
	}
	
	public boolean check(GameState state) {
		return mPuzzle.equals(state);
	}
}
