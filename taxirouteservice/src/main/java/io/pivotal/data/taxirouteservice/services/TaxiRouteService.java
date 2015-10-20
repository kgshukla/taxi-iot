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
		List<RouteData> top10routes = repository.findOne("LATEST_DATA").getData();
		return top10routes;
	}
	
}
