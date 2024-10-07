package me.nouredden.ems.managers;

import me.nouredden.ems.entities.User;
import me.nouredden.ems.interfaces.ICacheHandler;
import me.nouredden.ems.services.UserServiceImpl;
import me.nouredden.ems.utils.SystemProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class UserManager implements ICacheHandler<UUID, User> {

    private final Map<UUID, User> manager = new HashMap<>();
    private final UserServiceImpl userService = SystemProvider.getUserService();

    @Override
    public void insert(User user) {
        userService.insert(user);
    }

    @Override
    public User get(UUID id) {
        try {
            return userService.getById(id).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAll() {
        try {
            return userService.getAll().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(UUID id) {
        userService.delete(id);
    }

}
