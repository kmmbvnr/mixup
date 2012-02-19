package com.mixup;

import java.util.HashMap;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class SoundManager {
	
	private static final Integer CAT = 1;
	private static final Integer GOOSE = 2;
	private static final Integer HORSE = 3;
	
	private static final HashMap<Integer, Integer> ANIMAL_NAMES = new HashMap<Integer, Integer>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -8836643891432595645L;

		{
			put(R.drawable.cat_head, CAT);
			put(R.drawable.cat_body, CAT);
			put(R.drawable.cat_feet, CAT);
			put(R.drawable.goose_head, GOOSE);
			put(R.drawable.goose_body, GOOSE);
			put(R.drawable.goose_feet, GOOSE);
			put(R.drawable.horse_head, HORSE);
			put(R.drawable.horse_body, HORSE);
			put(R.drawable.horse_feet, HORSE);
		}
		
	};
	
	private static final HashMap<Integer, Integer[]> ANIMAL_SOUND = new HashMap<Integer, Integer[]>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2303590782329043721L;
		{
			put(CAT, new Integer[]{R.raw.sample, R.raw.sample});
			put(GOOSE, new Integer[]{R.raw.sample, R.raw.sample});
			put(HORSE, new Integer[]{R.raw.sample, R.raw.sample});
		}
	};
	
	private static final Integer FULL_SOUND = 0;
	private static final Integer PART_SOUND = 1;
	
	public void playStateSound(GameState state, Context context) {
		Integer[] sounds = getSoundArrayForState(state);
		playSound(context, sounds, 0);
	}
        
     
	private void playSound(final Context context, final Integer[] sounds, final Integer soundIndex) {
		if (soundIndex < sounds.length) {
			MediaPlayer mp = MediaPlayer.create(context, sounds[soundIndex]);   
	        mp.start();
	        mp.setOnCompletionListener(new OnCompletionListener() {
	            @Override
	            public void onCompletion(MediaPlayer mp) {
	                mp.release();
	                playSound(context, sounds, soundIndex+1);
	            }
	        });	
		}	
	}
        
	
	private Integer[] getSoundArrayForState(GameState state) {
		Integer topAnimal = ANIMAL_NAMES.get(state.getTopImageId());
		Integer middleAnimal = ANIMAL_NAMES.get(state.getMiddleImageId());
		Integer bottomAnimal = ANIMAL_NAMES.get(state.getBottomImageId());
		
		if (topAnimal.equals(middleAnimal)) {
			if (topAnimal.equals(bottomAnimal))
				return new Integer[]{ANIMAL_SOUND.get(topAnimal)[FULL_SOUND]};
			else 
				return new Integer[]{ANIMAL_SOUND.get(topAnimal)[PART_SOUND], 
					                 ANIMAL_SOUND.get(bottomAnimal)[FULL_SOUND]};
		}
		else {
			if (middleAnimal.equals(bottomAnimal))
				return new Integer[]{ANIMAL_SOUND.get(topAnimal)[PART_SOUND],
					                 ANIMAL_SOUND.get(middleAnimal)[FULL_SOUND]};
			else 
				return new Integer[]{ANIMAL_SOUND.get(topAnimal)[PART_SOUND], 
				                     ANIMAL_SOUND.get(middleAnimal)[PART_SOUND],
				                     ANIMAL_SOUND.get(bottomAnimal)[FULL_SOUND]};
		}	
	}

}