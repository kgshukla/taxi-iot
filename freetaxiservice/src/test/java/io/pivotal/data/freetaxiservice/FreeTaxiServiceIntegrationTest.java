/**
 * 
 */
package io.pivotal.data.freetaxiservice;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import io.pivotal.data.freetaxiservice.RequestHelper;
import io.pivotal.data.freetaxiservice.model.FreeTaxiMetrics;
import io.pivotal.data.freetaxiservice.repositories.FreeTaxiRepository;

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
public class FreeTaxiServiceIntegrationTest extends RequestHelper{

	@Autowired
	private FreeTaxiRepository repository;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		List<String> data1 = new ArrayList<String>();
        data1.add("41");
        data1.add("81");
        data1.add("1234");
        List<String> data2 = new ArrayList<String>();
        data2.add("40");
        data2.add("80");
        data2.add("12345");
        
        List<List<String>> list = new ArrayList<List<String>>();
        list.add(data1);
        list.add(data2);
        
        FreeTaxiMetrics ftm = new FreeTaxiMetrics();
        ftm.setData(list);
        ftm.setKey("LATEST_DATA");
        repository.save(ftm);
	}
	
	@After
	public void tearDown() throws Exception {
		repository.deleteAll();
	}
	
	@Test
	public void testFreeTaxiAPICall() throws Exception {
		ResponseEntity<String> responseEntity = makeRequest("freetaxies/list", HttpMethod.GET, String.class);
        String content = responseEntity.getBody();
        String outputString1 = "\"latitude\":\"41\",\"longitude\":\"81\",\"driver_num\":\"1234\"";
        String outputString2 = "\"latitude\":\"40\",\"longitude\":\"80\",\"driver_num\":\"12345\"";
        assertThat(content, containsString(outputString1));
        assertThat(content, containsString(outputString2));
	}
}
