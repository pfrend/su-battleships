package com.su.generator.randomizer;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This class wraps the logic for choosing random number from a index row
 * (numbers from 0 to a certain limit) with the additional option of excluded
 * positions in the row.Excluded positions are such indexes that are not
 * considered possible to be selected as a random result from the index row -
 * the random number will be chosen from all the others.
 * 
 * The business logic is accessible through static method which is parameterized
 * by a rowElement upperLimit and a set of excluded numbers
 * 
 * @author vasko
 * 
 */
public class IndexRowRandomizer {

	private static Random random = new Random(Calendar.getInstance()
			.getTimeInMillis());

	/**
	 * @param rowCount
	 *            - the upper exclusive limit of the index row - if rowCount is
	 *            5, the row is {0,1,2,3,4}
	 * @param excludedNumbers
	 *            - set of numbers that are not to be chosen from - if {2,3} are
	 *            excluded ,this means that a random number should be chosen
	 *            from the rest of the numbers - {0,1,4}.
	 * @return - random number from the row numbers that are not excluded from
	 *         the choosing
	 */
	public static int getRandomIndexFromExclusiveRow(int rowCount,
			SortedSet<Integer> excludedNumbers) {
		// TODO : handle assert criteria - rowCount must be bigger than all
		// numbers in the excludedNumbers set and should be bigger than their
		// count
		int modul = rowCount - excludedNumbers.size();		

		int randomIndex = random.nextInt(modul);
		int result = getNthNotExcludedNumber(randomIndex, excludedNumbers);

		return result;
	}

	private static int getNthNotExcludedNumber(int nth,
			SortedSet<Integer> excludedNumbers) {
		// nth + excludedNumber.size() should be smaller then the whole size ,
		// however this method does not have to assert that
		int result = -1;
		while (nth >= 0) {
			result++;
			if (excludedNumbers.contains(result)) {
				continue;
			} else {
				nth--;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		SortedSet<Integer> set = new TreeSet<Integer>();
		set.add(0);
		set.add(2);
		set.add(1);
		set.add(4);

		IndexRowRandomizer.getRandomIndexFromExclusiveRow(7, set);

		int temp;
		for (int i = 0; i < 20; i++) {
			temp = IndexRowRandomizer.getRandomIndexFromExclusiveRow(7, set);
			System.out
					.println("random from {0,1,2,3,4,5,6} excluding {0,1,2,4}: "
							+ temp);
		}
	}

}