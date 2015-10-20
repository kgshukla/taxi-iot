/**
 * 
 */
package io.pivotal.data.analyticservice;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import io.pivotal.data.analyticservice.services.AnalyticService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author shuklk2
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class AnalyticServiceIntegrationTest extends RequestHelper{

	@Autowired
	private AnalyticService service;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		jdbcTemplate.execute("CREATE SCHEMA TAXI_STREAM");
		jdbcTemplate.execute("CREATE TABLE TAXI_STREAM.TAXI_DATA_PXF (medallion character varying(400), "+
	      "hack_license character varying(400),"+
	      "pickup_date character varying(400),"+
	      "dropoff_date character varying(400),"+
	      "trip_time numeric,"+
	      "trip_dist numeric,"+
	      "pickup_long numeric,"+
	      "pickup_lat numeric,"+
	      "dropoff_long numeric,"+
	      "dropoff_lat numeric,"+
	      "payment_type character varying(400),"+
	      "fare_amt numeric,"+
	      "surcharge numeric,"+
	      "mta_tax numeric,"+
	      "tip numeric,"+
	      "toll_amt numeric,"+
	      "total_amt numeric)");
		
		jdbcTemplate.execute("insert into taxi_stream.taxi_data_pxf values (\'0A1A901A69BA96370E6EB2591BF4DEB6\',\'931308A71E5CA780E6AC4383FF4687FB\',\'2013-01-20 23:55:31\',\'2013-01-21 00:11:45\',973,8.10,0.000000,0.000000,0.000000,0.000000,\'CRD\',25.00,0.50,0.50,4.00,0.00,30.00)");
		jdbcTemplate.execute("insert into taxi_stream.taxi_data_pxf values (\'1A1A901A69BA96370E6EB2591BF4DEB6\',\'931308A71E5CA780E6AC4383FF4687FB\',\'2013-01-20 23:55:31\',\'2013-01-21 00:11:45\',973,8.10,0.000000,0.000000,0.000000,0.000000,\'CRD\',25.00,0.50,0.50,4.00,0.00,30.00)");		
	}

	@After
	public void tearDown() throws Exception {
		jdbcTemplate.execute("drop table taxi_stream.taxi_data_pxf");
		jdbcTemplate.execute("drop schema taxi_stream");
	}
	
	
	@Test
	public void testEarningDrivers() throws Exception {
		ResponseEntity<String> responseEntity = makeRequest("analytics/top10earningdrivers", HttpMethod.GET, String.class);
        String content = responseEntity.getBody();
        String outputString = "\"drivernum\":\"0A1A901A69BA96370E6EB2591BF4DEB6\",\"earnedrev\":30";
        assertThat(content, containsString(outputString));
		
	}
	
	@Test
	public void testErroringDrivers() throws Exception {
		ResponseEntity<String> responseEntity = makeRequest("analytics/top10incorrectdrivers", HttpMethod.GET, String.class);
        String content = responseEntity.getBody();     
        String outputString = "\"drivernum\":\"0A1A901A69BA96370E6EB2591BF4DEB6\",\"numoferrors\":1";
        assertThat(content, containsString(outputString));
		
	}
	
}
