package com.guitariffic.controller.filter;

import java.util.LinkedList;

import com.guitariffic.model.MusicChart;


/**
 * Filter chord chart list by position
 * 
 * @author rdbrmurray
 *
 */
public class PositionFilter extends ListFilter {

	public void setFilter(ListFilter filter, String filterString) {
		this.filter = filter;
		this.filterString = filterString;
	}

	@Override
	public LinkedList<MusicChart> filterList(LinkedList<MusicChart> list) {

		if (filterString == null || filterString.isEmpty())
			return filterComponent(list);

		LinkedList<MusicChart> filtered = new LinkedList<MusicChart>();

		for (MusicChart c : filterComponent(list)) {
			if (c.getChordPosition().equals(filterString)) {
				filtered.add(c);
			}
		}
		return filtered;
	}


}