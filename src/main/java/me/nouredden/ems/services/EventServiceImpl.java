package me.nouredden.ems.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.nouredden.ems.api.Client;
import me.nouredden.ems.entities.Event;
import me.nouredden.ems.interfaces.IService;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class EventServiceImpl implements IService<Event, UUID>
{
    private final Client apiClient;
    private final ObjectMapper objectMapper;
    private static final String EVENTS_API_URL = "http://localhost:7153/api/events";

    public EventServiceImpl() {
        this.apiClient = Client.getInstance();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public CompletableFuture<List<Event>> getAll() {
        return apiClient.getRequest(EVENTS_API_URL)
                .thenApply(response -> {
                    try {
                        return objectMapper.readValue(response, objectMapper.getTypeFactory().constructCollectionType(List.class, Event.class));
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to parse events", e);
                    }
                });
    }

    @Override
    public CompletableFuture<Event> getById(UUID eventId) {
        return apiClient.getRequest(EVENTS_API_URL + "/" + eventId)
                .thenApply(response -> {
                    try {
                        return objectMapper.readValue(response, Event.class);
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to parse event", e);
                    }
                });
    }

    @Override
    public CompletableFuture<Event> insert(Event event) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            return apiClient.postRequest(EVENTS_API_URL, payload)
                    .thenApply(response -> {
                        try {
                            return objectMapper.readValue(response, Event.class);
                        } catch (Exception e) {
                            throw new RuntimeException("Failed to parse created event", e);
                        }
                    });
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize event", e);
        }
    }

    @Override
    public CompletableFuture<Event> update(Event event) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            return apiClient.postRequest(EVENTS_API_URL + "/" + event.getUniqueId(), payload)
                    .thenApply(response -> {
                        try {
                            return objectMapper.readValue(response, Event.class);
                        } catch (Exception e) {
                            throw new RuntimeException("Failed to parse updated event", e);
                        }
                    });
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize event", e);
        }
    }

    @Override
    public CompletableFuture<Void> delete(UUID eventId) {
        return apiClient.getRequest(EVENTS_API_URL + "/delete/" + eventId).thenApply(response -> null);
    }
}
