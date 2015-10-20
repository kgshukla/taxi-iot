package io.pivotal.data.eventservice.services;

import io.pivotal.data.eventservice.model.EventQuality;
import io.pivotal.data.eventservice.repositories.EventsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by cq on 17/9/15.
 */
@Component
public class EventsService {

    @Autowired
    EventsRepository eventsRepository;

    public long totalOfEvents(){

        List<String> eventData = eventsRepository.findOne("LATEST_DATA").getData();
        long totalEvents = Long.valueOf(eventData.get(0));
        return totalEvents;
    }

    public long totalMissedEvents(){
        List<String> eventData = eventsRepository.findOne("LATEST_DATA").getData();
        long missedEvents = Long.valueOf(eventData.get(1));
        return missedEvents;
    }

    public EventQuality eventsQuality(){
        List<String> eventData = eventsRepository.findOne("LATEST_DATA").getData();
        long totalEvents = Long.valueOf(eventData.get(0));
        long missedEvents = Long.valueOf(eventData.get(1));

        EventQuality eventQuality = new EventQuality();
        float goodEvents = ((totalEvents - missedEvents)/totalEvents);
        float badEvents = (missedEvents/totalEvents);

        eventQuality.setBadEvents(badEvents);
        eventQuality.setGoodEvents(goodEvents);
        return eventQuality;


    }

    /**
     * Gets the latest window processing time
     * @return
     */
    public int latestWPT(){
        List<String> eventData = eventsRepository.findOne("LATEST_DATA").getData();
        int wpt = Integer.valueOf(eventData.get(2));
        return wpt;
    }


}
