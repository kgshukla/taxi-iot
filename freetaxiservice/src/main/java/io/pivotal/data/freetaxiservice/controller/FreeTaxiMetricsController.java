package io.pivotal.data.freetaxiservice.controller;

import java.util.List;

import io.pivotal.data.freetaxiservice.model.FreeTaxiData;
import io.pivotal.data.freetaxiservice.services.FreeTaxiService;

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
public class FreeTaxiMetricsController {

	@Autowired
	private FreeTaxiService service;
	
	@RequestMapping(value = "/freetaxies/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FreeTaxiData> getFreeTaxiesList() {
		return service.getFreeTaxiesList();
	}
	
}
