package com.su.generator.util;

import java.util.ArrayList;
import java.util.List;

public class ForbidenShipUtil {

	/**
	 * 
	 * @param field - the field that can not be a ship holder
	 * @param shipLength - the ship's length
	 * @param boardSideSize 
	 * @return list with forbidden ships - notice that if ships is 2 fields long, the forbidden ships positions
	 * will be at most 4 , but if the ship is long 4 fields , then there will be at most 8 forbidden ship positions
	 */
	public static List<Integer> getShipsForbiddenFromAField(int field,short shipLength,short boardSideSize){
		List<Integer> result = new ArrayList<Integer>();
		
		
		return result;
	}
}
