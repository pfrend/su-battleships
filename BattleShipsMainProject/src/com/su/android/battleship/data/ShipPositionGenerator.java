package com.su.android.battleship.data;

public class ShipPositionGenerator {
	
	
	public Ship[] getHardcodedShipPosition(){
		Ship[] resultShips = new Ship[Game.SHIPS_COUNT]; //5
		
		Ship s1 = new Ship((short) 2,new short[]{0,1});
		Ship s2 = new Ship((short) 2,new short[]{3,4});
		Ship s3 = new Ship((short) 3,new short[]{6,7,8});
		Ship s4 = new Ship((short) 4,new short[]{20,21,22,23});
		Ship s5 = new Ship((short) 5,new short[]{25,26,27,28,29});
		
		resultShips[0] = s1;
		resultShips[1] = s2;
		resultShips[2] = s3;
		resultShips[3] = s4;
		resultShips[4] = s5;
		
		return resultShips;
	}
	
	//TODO : implement random position generation algorithms 
}
