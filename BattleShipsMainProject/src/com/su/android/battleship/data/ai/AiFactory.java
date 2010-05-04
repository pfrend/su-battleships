package com.su.android.battleship.data.ai;

import java.util.Calendar;
import java.util.Random;

import com.su.android.battleship.data.GameAi;
import com.su.android.battleship.data.ai.impl.AiRandomPlayer;
import com.su.android.battleship.data.ai.impl.sppfd.AiPlayerEasy;
import com.su.android.battleship.data.ai.impl.sppfd.AiPlayerInsane;
import com.su.android.battleship.data.ai.impl.sppfd.AiPlayerNormal;

import android.util.Log;

public class AiFactory {
	
	public static final short AI_RANDOM_PLAYER = 0;
	public static final short AI_SPPFD_EASY = 1;
	public static final short AI_SPPFD_NORMAL = 2;
	public static final short AI_SPPFD_INSANE = 3;
	
	private static AiFactory instance = new AiFactory();
	
	public static AiFactory getInstance(){
		return instance;
	}
	
	//TODO : change aiType from int to enum
	public AiPlayer produceAiPlayer(int type,GameAi game){
		switch (type) {
		case AI_RANDOM_PLAYER:
			return new AiRandomPlayer(game);
		case AI_SPPFD_EASY:
			return new AiPlayerEasy(game);
		case AI_SPPFD_NORMAL:
			return new AiPlayerNormal(game);
		case AI_SPPFD_INSANE:
			return new AiPlayerInsane(game);
		default:
			return null;			
		}		
	}	
}