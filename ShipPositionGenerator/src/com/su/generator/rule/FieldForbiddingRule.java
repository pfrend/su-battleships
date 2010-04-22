package com.su.generator.rule;

import java.util.List;

public interface FieldForbiddingRule {

		/**
		 * 
		 * @param field - position of a field,that holds a ship on it
		 * @return list with the forbidden fields that can not hold a ship placed on them
		 * obtained from the field position given as parameter
		 */
		public List<Integer> getForbiddenFields(int field);
}
