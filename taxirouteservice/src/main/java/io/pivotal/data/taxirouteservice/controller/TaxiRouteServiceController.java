/**
 * 
 */
package io.pivotal.data.taxirouteservice.controller;

import java.util.List;

import io.pivotal.data.taxirouteservice.models.RouteData;
import io.pivotal.data.taxirouteservice.services.TaxiRouteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shuklk2
 *
 */

@RestController
public class TaxiRouteServiceController {

	@Autowired
	private TaxiRouteService service;
	
	@RequestMapping(value = "/routes/top10routes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RouteData> getTop10Routes() {
		return service.getTop10Routes();
	}
	
}
