package com.guitariffic.controller.filter;

import java.util.LinkedList;

import com.guitariffic.model.BaseChordChart;

/**
 * 
 * Filter chord chart list abstract class
 * @author rdbrmurray
 *
 */
public abstract class ListFilter implements IListFilter {

	protected ListFilter filter;
	protected String filterString;

	/**
	 * Initialize ListFilter and filter string
	 * @param filter
	 * @param filterString
	 */
	public void setFilter(ListFilter filter, String filterString) {
		this.filter = filter;
		this.filterString = filterString;
	}

	@Override
	public LinkedList<BaseChordChart> filterList(LinkedList<BaseChordChart> list) {
		if (filter != null) {
			return filter.filterList(list);
		}
		else
			return list;
	}
	
	/**
	 * Filter method
	 * @param list
	 * @return
	 */
	protected LinkedList<BaseChordChart> filterComponent(
			LinkedList<BaseChordChart> list) {
		if (filter != null) {
			return filter.filterList(list);
		} else
			return list;
	}

}