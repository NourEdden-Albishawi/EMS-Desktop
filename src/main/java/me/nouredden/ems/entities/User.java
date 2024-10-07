package me.nouredden.ems.entities;

import java.util.UUID;

public class User
{
    private UUID uniqueId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;

    public UUID getUniqueId() {
        return uniqueId;
    }

    public void setId(UUID uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

}
