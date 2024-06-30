package me.nouredden.ems.managers;

import me.nouredden.ems.entities.Event;
import me.nouredden.ems.interfaces.ICacheHandler;

import java.util.*;

public class EventManager implements ICacheHandler<UUID, Event> {
    private final Map<UUID, Event> manager = new HashMap();

    @Override
    public void insert(Event event) {
        if (!manager.containsValue(event)) {
            manager.put(event.getUniqueId(), event);
            return;
        }
        System.out.println("An error occurred while trying to add event into memory/n Event already exist!");
    }

    @Override
    public Event get(UUID uniqueId) {
        return this.manager.get(uniqueId);
    }

    @Override
    public List<Event> getAll() {
        List<Event> events = new ArrayList<>();
        for (Event event : manager.values()) {
            events.add(event);
        }
        return events;
    }

    @Override
    public void delete(UUID uniqueId) {
        if (manager.containsKey(uniqueId)) {
            manager.remove(uniqueId);
            return;
        }
        System.out.println("An error occurred while trying to delete event from memory/n Event doesn't exist!");
    }

    @Override
    public void save() {
        //TODO - Save manager's data into database.
    }

    @Override
    public Map<UUID, Event> getManager() {
        return this.manager;
    }

}
