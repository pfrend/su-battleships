package com.su.generator.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.su.generator.encoder.ShipPositionEncoder;
import com.su.generator.encoder.ShipPositionEncoderDefaultImpl;
import com.su.generator.rule.FieldForbiddingRule;
import com.su.generator.rule.FieldForbiddingRuleCrossImpl;
/**
 * This class implements the logic for manipulation with the forbiddenFieldsPositions
 * That is - it holds a list of the forbidden fields.From those fields , using the ForbiddenShipManager
 * is receives all the 'impossible' ships - those that can not be added to the board (because of the game rules).
 * Then, those ships are encoded with the shipPositionEncoder and they can be used
 * to obtain a random encoded ship using the ExcludeRowRandomizer.When the random ship
 * code is produced , it is decoded and the ship is returned to the business logic
 * of the game , and this Manager is updated. 
 * 
 * @author vasil.konstantinov
 *
 */
public class ForbiddenPositionsManager {
	
	private ShipPositionEncoder encoder;
	private FieldForbiddingRule rule;
		
	/*It is important to the count of ships that border with forbidden fields
	 * so that this class can be reused in the ship positioning implementation*/
	private Map<Short, Short> forbiddenFieldsHolder;
	
	public ForbiddenPositionsManager(short boardSideSize) {
		this.encoder = new ShipPositionEncoderDefaultImpl(boardSideSize);
		this.rule = new FieldForbiddingRuleCrossImpl(boardSideSize);
		
		this.forbiddenFieldsHolder = new HashMap<Short, Short>();
	}
	
	public List<Integer> getCodedForbiddenShipPositions(int shipLength){
		return null;
	}
	
	
}
