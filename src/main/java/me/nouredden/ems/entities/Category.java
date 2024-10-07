package me.nouredden.ems.entities;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Category {
    private final UUID id;
    private String name;
    private final List<Event> events;
}
