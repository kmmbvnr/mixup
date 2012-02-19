package com.mixup;

import java.util.HashMap;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class SoundManager {
	
	private static final Integer CAT = 1;
	private static final Integer GOOSE = 2;
	private static final Integer HORSE = 3;
	private static final Integer BEE = 4;
	private static final Integer DOLPHIN = 5;
	
	
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
			put(R.drawable.bee_head, BEE);
			put(R.drawable.bee_body, BEE);
			put(R.drawable.bee_feet, BEE);
		}
		
	};
	
	private static final HashMap<Integer, Integer[]> ANIMAL_SOUND = new HashMap<Integer, Integer[]>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2303590782329043721L;
		{
			put(CAT, new Integer[]{R.raw.cat_full, R.raw.cat_part});
			put(GOOSE, new Integer[]{R.raw.goose_full, R.raw.goose_part});
			put(HORSE, new Integer[]{R.raw.horse_full, R.raw.horse_part});
			put(BEE, new Integer[]{R.raw.bee_full, R.raw.bee_part});
			put(DOLPHIN, new Integer[]{R.raw.dolphin_full, R.raw.dolphin_part});
		}
	};
	
	private static final Integer FULL_SOUND = 0;
	private static final Integer PART_SOUND = 1;
	
	public void playStateSound(GameState state, Context context) {
		Integer[] sounds = getSoundArrayForState(state);
		playSound(context, sounds, 0);
	}
	
	public void playPuzzleSound(GameState state, Context context) {
		Integer[] sounds = getSoundArrayForState(state);
		Integer[] allSounds = new Integer[sounds.length+1];
		allSounds[0] = R.raw.how_it_looks;
		for (int i=0; i<sounds.length; i++)
			allSounds[i+1] = sounds[i];
		
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
