/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package com.guitariffic.service;

import com.guitariffic.model.GuitarChart;
import com.guitariffic.service.exception.GuitarChartAlreadyExists;
import com.guitariffic.service.exception.GuitarChartNotFound;

public interface GuitarChartService {

	public abstract String add(GuitarChart chart) throws GuitarChartAlreadyExists;

	public abstract void delete(String id) throws GuitarChartNotFound;

	public abstract GuitarChart get(String id) throws GuitarChartNotFound;

	public abstract String[] getList(String search);

	public abstract String update(GuitarChart chart, String id) throws GuitarChartNotFound;

}