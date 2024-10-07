package me.nouredden.ems.entities;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class Event {
    private UUID uniqueId = UUID.randomUUID();
    private String title;
    private String description;
    private String location;

    private LocalDate creationDate = LocalDate.now();
    private LocalDate startDate;
    private LocalDate endDate;

    private byte[] thumbnail;
    private List<byte[]> photos;
    private int capacity;

    private int categoryId;
    private Category category;

}
