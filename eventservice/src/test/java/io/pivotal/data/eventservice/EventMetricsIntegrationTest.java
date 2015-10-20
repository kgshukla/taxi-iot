package io.pivotal.data.eventservice;

import com.google.common.collect.Lists;

import io.pivotal.data.eventservice.model.EventMetrics;
import io.pivotal.data.eventservice.repositories.EventsRepository;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by cq on 17/9/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class EventMetricsIntegrationTest extends RequestHelper{


    @Autowired
    EventsRepository repository;


    @After
    public void tearDown() throws Exception {
        repository.deleteAll();

    }

    @Test
    public void testTotalEvents() throws Exception {

        EventMetrics metrics = new EventMetrics();
        metrics.setKey("LATEST_DATA");
        List<String> data = Lists.newArrayList("9872652", "54321", "4359");
        metrics.setData(data);

        repository.save(metrics);


        ResponseEntity<Long> responseEntity = makeRequest("events/total/", HttpMethod.GET, Long.class);

        long totalEvents = responseEntity.getBody();

        assertThat(totalEvents, equalTo(9872652L));

    }
}
