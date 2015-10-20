package io.pivotal.data.taxirouteservice.repositories;

import io.pivotal.data.taxirouteservice.models.TaxiRouteMetrics;

import org.springframework.data.repository.CrudRepository;

public interface TaxiRouteRepository extends
		CrudRepository<TaxiRouteMetrics, String> {

}
