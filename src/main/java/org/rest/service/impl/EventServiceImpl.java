package org.rest.service.impl;

import org.rest.model.Event;
import org.rest.repository.hibernate.EventRepositoryImpl;
import org.rest.service.EventService;

import java.util.List;
import java.util.Set;

public class EventServiceImpl implements EventService {
    EventRepositoryImpl eventRepository = new EventRepositoryImpl();
    @Override
    public Event getById(Integer integer) {
        return eventRepository.getById(integer);
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.getAll();
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event update(Event event) {
        return eventRepository.update(event);
    }

    @Override
    public void deleteById(Integer integer) {
        eventRepository.deleteById(integer);
    }
}
