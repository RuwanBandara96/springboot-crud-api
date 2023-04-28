package com.springboot.camel.postgresql.service;

import com.springboot.camel.postgresql.entity.Event;
import com.springboot.camel.postgresql.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findAllEvents() {

        return eventRepository.findAll();
    }

    public Event findEventById(int eventId) {

        return eventRepository.findByEventId(eventId);
    }

    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    public void removeEvent(int eventId) {
        eventRepository.deleteById(eventId);
    }

    public Event updateEvent(int eventId, Event event) {
        return eventRepository.findById(eventId)
                .map(employee -> {
                    employee.setTransId(event.getTransId());
                    employee.setTransTms(event.getTransTms());
                    employee.setAddrNbr(event.getAddrNbr());
                    employee.setRcNum(event.getRcNum());
                    employee.setEventCnt(event.getEventCnt());
                    employee.setLocationId1(event.getLocationId1());
                    employee.setLocationId2(event.getLocationId2());
                    employee.setAddrNbr(event.getAddrNbr());
                    employee.setLocationCd(event.getLocationCd());
                    return eventRepository.save(employee);
                })
                .orElseGet(() -> {
                    event.setEventId(eventId);
                    return eventRepository.save(event);
                });



    }

    public void saveEvent(Event newEvent) {
    }
}
