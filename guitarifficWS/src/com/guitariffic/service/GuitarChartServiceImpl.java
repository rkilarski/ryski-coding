package com.guitariffic.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.guitariffic.model.GuitarChart;
import com.guitariffic.service.exception.GuitarChartAlreadyExists;
import com.guitariffic.service.exception.GuitarChartNotFound;

public class GuitarChartServiceImpl extends BaseService implements GuitarChartService {
	private Map<String, GuitarChart> map = new HashMap<String, GuitarChart>();

	@Override
	public String add(GuitarChart chart) throws GuitarChartAlreadyExists {
		String id = chart.getId();
		if (map.get(id) != null) {
			throw new GuitarChartAlreadyExists("Cannot add details of order " + id
					+ ". This order already exists.");
		}
		map.put(id, chart);
		return getBaseURL() + "chart/" + id;
	}

	@Override
	public String update(GuitarChart chart, String id) throws GuitarChartNotFound {
		GuitarChart order = map.get(id);
		if (order == null) {
			throw new GuitarChartNotFound("Details of order " + id + " cannot be found.");
		}
		boolean found = false;
		/*for (Item orderItem : order.getOrderItems()) {
			if (orderItem.getProductName().equals(item.getProductName())) {
				orderItem.setQuantity(item.getQuantity());
				orderItem.setPrice(item.getPrice());
				found = true;
			}
		}
		*/
		if (!found) {
			throw new GuitarChartNotFound("Details of order " + id + " cannot be found.");
		}
		return getBaseURL() + "chart/" + id;
	}

	@Override
	public void delete(String id) throws GuitarChartNotFound {
		if (map.get(id) == null) {
			throw new GuitarChartNotFound("Details of order " + id + " cannot be found.");
		}
		map.remove(id);
	}

	@Override
	public String[] getList(String search) {
		int size = map.size();
		String[] orders = new String[size];
		Iterator<String> iterator = map.keySet().iterator();
		int i = 0;
		while (iterator.hasNext()) {
			String orderId = iterator.next();
			orders[i] = getBaseURL() + "order/" + orderId;
			i++;
		}
		return orders;
	}

	@Override
	public GuitarChart get(String id) throws GuitarChartNotFound {
		GuitarChart order = map.get(id);
		if (order == null) {
			throw new GuitarChartNotFound("Details of order" + id + " cannot be found.");
		}
		return order;
	}
}
