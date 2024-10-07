package me.nouredden.ems.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.nouredden.ems.api.Client;
import me.nouredden.ems.entities.User;
import me.nouredden.ems.interfaces.IService;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class UserServiceImpl implements IService<User, UUID> {
    private final Client apiClient;
    private final ObjectMapper objectMapper;
    private static final String USERS_API_URL = "http://localhost:7153/api/users";

    public UserServiceImpl() {
        this.apiClient = Client.getInstance();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public CompletableFuture<List<User>> getAll() {
        return apiClient.getRequest(USERS_API_URL).thenApply(response -> {
            try {
                return objectMapper.readValue(response, objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
            } catch (Exception e) {
                throw new RuntimeException("Failed to parse users", e);
            }
        });
    }

    @Override
    public CompletableFuture<User> getById(UUID userId) {
        return apiClient.getRequest(USERS_API_URL + "/" + userId).thenApply(response -> {
            try {
                return objectMapper.readValue(response, User.class);
            } catch (Exception e) {
                throw new RuntimeException("Failed to parse user", e);
            }
        });
    }

    @Override
    public CompletableFuture<User> insert(User user) {
        try {
            String payload = objectMapper.writeValueAsString(user);
            return apiClient.postRequest(USERS_API_URL, payload).thenApply(response -> {
                try {
                    return objectMapper.readValue(response, User.class);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to parse created user", e);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize user", e);
        }
    }

    @Override
    public CompletableFuture<User> update(User user) {
        try {
            String payload = objectMapper.writeValueAsString(user);
            return apiClient.putRequest(USERS_API_URL + "/" + user.getUniqueId(), payload).thenApply(response -> {
                try {
                    return objectMapper.readValue(response, User.class);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to parse updated user", e);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize user", e);
        }
    }

    @Override
    public CompletableFuture<Void> delete(UUID userId) {
        return apiClient.deleteRequest(USERS_API_URL + "/" + userId).thenApply(response -> null);
    }
}
