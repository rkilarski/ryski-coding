package com.guitariffic.controller.filter;


import java.util.LinkedList;

import com.guitariffic.model.BaseChordChart;

/**
 * Filter chord chart list interface
 * 
 * @author rdbrmurray
 *
 */
public interface IListFilter {

  public LinkedList<BaseChordChart> filterList(LinkedList<BaseChordChart> list);

}