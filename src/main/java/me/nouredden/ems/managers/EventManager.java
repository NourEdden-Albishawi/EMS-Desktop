package me.nouredden.ems.managers;

import me.nouredden.ems.entities.Event;
import me.nouredden.ems.interfaces.ICacheHandler;
import me.nouredden.ems.services.EventServiceImpl;
import me.nouredden.ems.utils.SystemProvider;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class EventManager implements ICacheHandler<UUID, Event> {
    private final EventServiceImpl eventService = SystemProvider.getEventService();

    @Override
    public void insert(Event event) {
        eventService.insert(event);
    }

    @Override
    public Event get(UUID uniqueId) {
        try {
            return eventService.getById(uniqueId).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Event> getAll() {
        try {
            return eventService.getAll().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(UUID uniqueId) {
        eventService.delete(uniqueId);
    }

}
