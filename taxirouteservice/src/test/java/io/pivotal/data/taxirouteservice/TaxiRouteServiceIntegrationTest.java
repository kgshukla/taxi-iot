/**
 * 
 */
package io.pivotal.data.taxirouteservice;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import io.pivotal.data.taxirouteservice.models.RouteData;
import io.pivotal.data.taxirouteservice.models.TaxiRouteMetrics;
import io.pivotal.data.taxirouteservice.repositories.TaxiRouteRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author shuklk2
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class TaxiRouteServiceIntegrationTest extends RequestHelper {

	@Autowired
	private TaxiRouteRepository repository;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
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
	}
	
	@After
	public void tearDown() throws Exception {
		repository.deleteAll();
	}
	
	@Test
	public void testTop10Routes() throws Exception {
		ResponseEntity<String> responseEntity = makeRequest("routes/top10routes", HttpMethod.GET, String.class);
        String content = responseEntity.getBody();
        System.out.println("data:"+content);
        String outputString1 = "\"route\":\"1-2\",\"numtrips\":\"41\",\"starttime\":\"1am\",\"endtime\":\"2am\"";
        String outputString2 = "\"route\":\"2-3\",\"numtrips\":\"42\",\"starttime\":\"2am\",\"endtime\":\"3am\"";
        assertThat(content, containsString(outputString1));
        assertThat(content, containsString(outputString2));
	}
	
}
