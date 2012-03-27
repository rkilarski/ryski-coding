package com.guitariffic.controller.filter;

import java.util.LinkedList;

import com.guitariffic.model.MusicChart;

/**
 * 
 * Filter chord chart list by name
 * 
 * @author rdbrmurray
 * 
 */
public class NameFilter extends ListFilter {

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
			if (ifMatchesCriteria(c.getChordName(), filterString)) {
				filtered.add(c);
			}
		}
		return filtered;
	}

	private boolean ifMatchesCriteria(String string, String criteria) {
		if (string.equals(criteria) || string.contains(criteria)) {
			return true;
		}
		return false;
	}

}