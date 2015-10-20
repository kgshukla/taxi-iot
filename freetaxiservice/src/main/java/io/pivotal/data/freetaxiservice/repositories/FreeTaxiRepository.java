package io.pivotal.data.freetaxiservice.repositories;

import io.pivotal.data.freetaxiservice.model.FreeTaxiMetrics;

import org.springframework.data.repository.CrudRepository;

public interface FreeTaxiRepository extends
		CrudRepository<FreeTaxiMetrics, String> {

}
