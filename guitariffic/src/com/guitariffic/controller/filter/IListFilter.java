package com.guitariffic.controller.filter;


import java.util.LinkedList;

import com.guitariffic.model.MusicChart;

/**
 * Filter chord chart list interface
 * 
 * @author rdbrmurray
 *
 */
public interface IListFilter {

  public LinkedList<MusicChart> filterList(LinkedList<MusicChart> list);

}