package com.guitariffic.controller.filter;

import java.util.LinkedList;

import com.guitariffic.model.BaseChordChart;

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
	public LinkedList<BaseChordChart> filterList(LinkedList<BaseChordChart> list) {

		if (filterString == null || filterString.isEmpty())
			return filterComponent(list);

		LinkedList<BaseChordChart> filtered = new LinkedList<BaseChordChart>();

		for (BaseChordChart c : filterComponent(list)) {
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