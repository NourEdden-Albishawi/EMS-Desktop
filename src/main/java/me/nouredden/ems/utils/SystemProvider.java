package me.nouredden.ems.utils;

import me.nouredden.ems.managers.EventManager;

public class SystemProvider
{
    private static final EventManager eventManager = new EventManager();
    public static EventManager getEventManager(){
        return eventManager;
    }
}
