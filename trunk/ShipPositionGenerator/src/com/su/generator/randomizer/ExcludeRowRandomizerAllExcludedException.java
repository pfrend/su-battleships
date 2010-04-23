package com.su.generator.randomizer;
/**
 * This class wraps exception caused when all numbers are to be excluded from the row, thus
 * there are no numbers to randomly choose from 
 * @author vasil.konstantinov
 *
 */
public class ExcludeRowRandomizerAllExcludedException extends Exception {
	
	public ExcludeRowRandomizerAllExcludedException(String msg) {
		super("msg");
	}
	
}
