package me.nouredden.ems.utils;

import lombok.Getter;
import me.nouredden.ems.managers.EventManager;
import me.nouredden.ems.services.EventServiceImpl;
import me.nouredden.ems.services.UserServiceImpl;

public class SystemProvider
{
    @Getter
    private static final EventManager eventManager = new EventManager();
    @Getter
    private static final EventServiceImpl eventService = new EventServiceImpl();
    @Getter
    private static final UserServiceImpl userService = new UserServiceImpl();

}
