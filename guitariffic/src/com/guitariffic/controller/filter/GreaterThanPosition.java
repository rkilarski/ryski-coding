/**
 * 
 */
package com.guitariffic.controller.filter;

import java.util.Comparator;

import com.guitariffic.model.MusicChart;

/**
 * @author rdbrmurray
 * 
 *         Sort chord chart display by position
 * 
 */
public class GreaterThanPosition implements Comparator<MusicChart> {

	@Override
	public int compare(MusicChart arg0, MusicChart arg1) {

		int pos0 = Integer.parseInt(arg0.getChordPosition());
		int pos1 = Integer.parseInt(arg1.getChordPosition());

		if (pos0 < pos1)
			return -1;
		else if (pos0 == pos1)
			return 0;
		else
			return 1;
	}

}
