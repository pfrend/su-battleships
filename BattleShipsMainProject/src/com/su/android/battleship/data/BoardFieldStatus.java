package com.su.android.battleship.data;

public class BoardFieldStatus {
	
	private BoardFieldStatus(){		
	}
	
	public static final short WATER = 0x0F;
	public static final short WATER_ATTACKED = 0x1F;
	
	/*Ship index is corresponding to the length of the ship 
	 *The lower hex byte - the position in the Ship[] array*/
	public static final short SHIP_0 = 0x00;
	public static final short SHIP_0_ATTACKED = 0x10;
	
	public static final short SHIP_1 = 0x01;
	public static final short SHIP_1_ATTACKED = 0x11;
	
	public static final short SHIP_2 = 0x02;
	public static final short SHIP_2_ATTACKED = 0x12;
	
	public static final short SHIP_3 = 0x03;
	public static final short SHIP_3_ATTACKED = 0x13;
	
	public static short getAttackedFieldCode(short notAttackedFieldCode){
		return (short) (notAttackedFieldCode + 0x10);
	}
	
	public static short getShipMarkerCode(int shipIndex){
		short boardMarker;
		switch (shipIndex) {
		case 0:
			boardMarker = BoardFieldStatus.SHIP_0;
			break;
		case 1:
			boardMarker = BoardFieldStatus.SHIP_1;
			break;
		case 2:
			boardMarker = BoardFieldStatus.SHIP_2;
			break;
		case 3:
			boardMarker = BoardFieldStatus.SHIP_3;
			break;

		default:
			boardMarker = -1;
			break;
		}
		return boardMarker;		
	}
	/**
	 * 
	 * @param code
	 * @return whether code is a ship that IS NOT attacked
	 */
	public static boolean isShipNotAttackedStatus(short code){
		return (code == SHIP_0) || (code == SHIP_1) 
			   || (code == SHIP_2) || (code == SHIP_3);
	}
	
	/**
	 * 
	 * @param code
	 * @return whether code is a ship that IS attacked
	 */
	public static boolean isShipAttackedStatus(short code){
		return (code == SHIP_0_ATTACKED) || (code == SHIP_1_ATTACKED) 
			   || (code == SHIP_2_ATTACKED) || (code == SHIP_3_ATTACKED);
	}
	
	/**
	 * 
	 * @param num
	 * @return - returns ship index - lower byte of the shipCode corresponds to the ship's game number
	 */
	public static int getShipIndex(short num){
		if(!isShipNotAttackedStatus(num) && !isShipAttackedStatus(num)){
			//TODO : throw custom exception - can't get shipIndex from no-ship status
			return -1;
		}else{
			int shipIndex = num & 0x0F;
			return shipIndex;
		}		
	}
	
	public static boolean isAttackedFieldStatus(short code){
		return (code & 0x10) == 0x10;
	}
}
