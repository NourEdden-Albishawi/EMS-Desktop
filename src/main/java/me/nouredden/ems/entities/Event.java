package me.nouredden.ems.entities;

import java.time.LocalDate;
import java.util.UUID;

public class Event {
    private UUID uniqueId;
    private String title;
    private LocalDate creationDate, startDate, endDate;
    private String location;

    public Event(String title, String location, LocalDate startDate, LocalDate endDate) {
        this.uniqueId = UUID.randomUUID();
        this.title = title;
        this.creationDate = LocalDate.now();
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UUID getUniqueId() {
        return this.uniqueId;
    }

    public String getTitle() {
        return this.title;
    }

    public LocalDate getCreationDate() {
        return this.creationDate;
    }

    public String getLocation() {
        return this.location;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }


    public void setLocation(String location) {
        this.location = location;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
