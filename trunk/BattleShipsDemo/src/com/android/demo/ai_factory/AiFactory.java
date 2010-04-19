package com.android.demo.ai_factory;

import java.util.Calendar;
import java.util.Random;

import android.util.Log;

import com.android.demo.data.AiPlayer;
import com.android.demo.data.Game;

public class AiFactory {
	
	private static AiFactory instance = new AiFactory();
	
	public static AiFactory getInstance(){
		return instance;
	}
	
	//TODO : change aiType from int to enum
	public AiPlayer produceAiPlayer(int type,Game game){
		switch (type) {
		case 0:
			return new AiRandomPlayer(game);			
		default:
			return null;			
		}		
	}
	
	
	public class AiRandomPlayer extends AiPlayer{
		
		public AiRandomPlayer(Game game) {
			this.game = game;
		}
		
		@Override		
		public int generateMove() {
			
			String[] playerBoard = game.getPlayerBoard();
			
			int possibleShotsCount = 0;
			int[] possibleShots = new int[Game.BOARD_SIZE];
			for(int i = 0 ; i < playerBoard.length ; i++){
				if(!playerBoard[i].equals(Game.ATTACKED)){
					possibleShots[possibleShotsCount++] = i;						
				}
			}
			Calendar calendar = Calendar.getInstance();
			long randomNumber = calendar.getTimeInMillis();
			
			Random rand = new Random(randomNumber);
			int randomIndex = rand.nextInt()%possibleShotsCount;
			if(randomIndex < 0){
				randomIndex = -randomIndex;
			}
			//Log.d("KOPELEEEEE", ""+randomIndex);
			return possibleShots[randomIndex];
		}
	}
}
