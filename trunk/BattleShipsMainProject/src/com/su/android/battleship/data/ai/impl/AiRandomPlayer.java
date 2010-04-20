package com.su.android.battleship.data.ai.impl;

import java.util.Calendar;
import java.util.Random;

import com.su.android.battleship.data.BoardFieldStatus;
import com.su.android.battleship.data.Game;
import com.su.android.battleship.data.GameAi;
import com.su.android.battleship.data.Ship;
import com.su.android.battleship.data.ai.iface.AiPlayer;

public class AiRandomPlayer extends AiPlayer {
	
	public AiRandomPlayer(GameAi game) {
		super.game = game;
	}

	@Override
	public short generateMove() {
		Short[] playerBoard = game.getPlayerBoard((short)1);//ai is always the second player

		short possibleShotsCount = 0;
		short[] possibleShots = new short[Game.BOARD_SIZE];
		for (short i = 0; i < playerBoard.length; i++) {
			if (!BoardFieldStatus.isAttackedFieldStatus(playerBoard[i])) {
				possibleShots[possibleShotsCount++] = i;
			}
		}
		Calendar calendar = Calendar.getInstance();
		long randomNumber = calendar.getTimeInMillis();

		Random rand = new Random(randomNumber);
		int randomIndex = rand.nextInt() % possibleShotsCount;
		if (randomIndex < 0) {
			randomIndex = -randomIndex;
		}
		return possibleShots[randomIndex];
	}
}
