package com.su.android.battleship.data.ai.impl.sppfd;

import java.util.ArrayList;
import java.util.List;

import com.su.android.battleship.data.GameAi;
import com.su.android.battleship.data.ai.AiCalculationBoardStatusManager;
import com.su.android.battleship.data.ai.AiPlayer_ShipPositionsPerFieldDependant;

public class AiPlayerEasy extends AiPlayer_ShipPositionsPerFieldDependant {

	
	public AiPlayerEasy(GameAi game) {
		super(game);		
	}

	@Override
	protected List<Short> getAllPotentialForAttackFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Short> getFieldTypesThatExcludePotentialShipPositions() {
		List<Short> resultList = new ArrayList<Short>();

		//this is easy AI - it know only that a ship can not move through another ship
		//but it does not know , that when a field is empty , there is no ship - ai will still try fields around the field 
		//as often as for every other field  
		resultList.add(AiCalculationBoardStatusManager.DESTROYED_SHIP);

		return resultList;
	}

}
