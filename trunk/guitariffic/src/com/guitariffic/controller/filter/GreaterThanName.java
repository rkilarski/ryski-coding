/**
 * 
 */
package com.guitariffic.controller.filter;

import java.util.Comparator;

import com.guitariffic.model.MusicChart;

/**
 * @author rdbrmurray
 * 
 * Sort chord chart display by name
 *
 */
public class GreaterThanName implements Comparator<MusicChart> {

	@Override
	public int compare(MusicChart arg0, MusicChart arg1) {
	
		if (arg0.getChordName().compareTo(arg1.getChordName() ) < 0 )
			return -1;
		else if (arg0.getChordName().equals(arg1.getChordName()) )
			return 0;
		else
			return 1;
	}

}
