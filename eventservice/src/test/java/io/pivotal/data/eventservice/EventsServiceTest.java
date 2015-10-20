package io.pivotal.data.eventservice;

import com.google.common.collect.Lists;

import io.pivotal.data.eventservice.model.EventMetrics;
import io.pivotal.data.eventservice.repositories.EventsRepository;
import io.pivotal.data.eventservice.services.EventsService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

/**
 * Created by cq on 17/9/15.
 */
public class EventsServiceTest {

    @InjectMocks
    EventsService service;

    @Mock
    EventsRepository repository;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testGetEvents() throws Exception {

        EventMetrics metrics = new EventMetrics();

        metrics.setKey("LATEST_DATA");
        List<String> data = Lists.newArrayList("9872652", "54321", "4359");
        metrics.setData(data);

        when(repository.findOne("LATEST_DATA")).thenReturn(metrics);


        long missedEvents = service.totalMissedEvents();
        long totalEvents = service.totalOfEvents();
        int wtp = service.latestWPT();


        assertThat(missedEvents,equalTo(54321L));
        assertThat(totalEvents,equalTo(9872652L));
        assertThat(wtp,equalTo(4359));

    }




}
