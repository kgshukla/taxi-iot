package io.pivotal.data.taxiservice.controllers;


import io.pivotal.data.taxiservice.model.EventQuality;
import io.pivotal.data.taxiservice.services.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cq on 17/9/15.
 */
@RestController
public class EventMetricsController {

    @Autowired
    private EventsService eventsService;

    @RequestMapping(value = "/events/total", method = RequestMethod.GET)
    public long getTotalEvents(){
        return eventsService.totalOfEvents();
    }

    @RequestMapping(value = "/events/missed", method = RequestMethod.GET)
    public long getMissedEvents(){
        return eventsService.totalMissedEvents();
    }

    @RequestMapping(value = "/events/quality", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public EventQuality eventsQuality(){
        return eventsService.eventsQuality();
    }
}
