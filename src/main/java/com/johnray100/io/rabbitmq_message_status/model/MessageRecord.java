package com.johnray100.io.rabbitmq_message_status.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MessageRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;
    private String content;
    private String status; // "pending", "ongoing", "completed"

    // constructors, getters, setters
    public MessageRecord() {

    }

    public MessageRecord(String content, String status) {
        this.content = content;
        this.status = status;
    }



    // getters & setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
