package org.example.entities;

import org.example.utils.UuidGenerator;

import java.time.LocalDateTime;

public class Todo {
    public String identifier;
    public String name;
    public String details;
    public LocalDateTime dateCreated;
    public String ownerEmail;
    public String status;

    public  Todo(String name, String details, String ownerEmail){
        this.name = name;
        this.details = details;
        this.ownerEmail = ownerEmail;
        this.identifier = UuidGenerator.generateShortUniqueId();
        this.dateCreated = LocalDateTime.now();
        this.status = "active";
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if(status.equals("active") || status.equals("completed")) {
            this.status = status;
        }
    }

    @Override
    public String toString() {
        return "Todo{" +
                "identifier='" + identifier + '\'' +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", dateCreated=" + dateCreated +
                ", ownerUsername='" + ownerEmail + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
