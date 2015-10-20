/**
 * 
 */
package io.pivotal.data.taxirouteservice.services;

import java.util.ArrayList;
import java.util.List;

import io.pivotal.data.taxirouteservice.models.RouteData;
import io.pivotal.data.taxirouteservice.models.TaxiRouteMetrics;
import io.pivotal.data.taxirouteservice.repositories.TaxiRouteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author shuklk2
 *
 */
@Component
public class TaxiRouteService {

	@Autowired
	private TaxiRouteRepository repository;
	
	public List<RouteData> getTop10Routes() {
		
		RouteData rd1 = new RouteData();
		rd1.setRoute("1-2");
		rd1.setNumtrips("41");
		rd1.setStarttime("1am");
		rd1.setEndtime("2am");
		RouteData rd2 = new RouteData();
		rd2.setRoute("2-3");
		rd2.setNumtrips("42");
		rd2.setStarttime("2am");
		rd2.setEndtime("3am");
		List<RouteData> list = new ArrayList<RouteData>();
		list.add(rd1);
		list.add(rd2);
		
		TaxiRouteMetrics trm = new TaxiRouteMetrics();
		trm.setData(list);
		trm.setKey("LATEST_DATA");
		repository.save(trm);
		
		List<RouteData> top10routes = repository.findOne("LATEST_DATA").getData();
		
		return top10routes;
	}
	
}
