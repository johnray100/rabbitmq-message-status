package com.johnray100.io.rabbitmq_message_status.model;

public class MessageStatus {

    private int pending;   // messages consume
    private int ongoing;   // messages currently being processed
    private int completed; // messages successfully processed

    public MessageStatus(int pending, int ongoing, int completed) {
        this.pending = pending;
        this.ongoing = ongoing;
        this.completed = completed;
    }

    public int getPending() {
        return pending;
    }

    public int getOngoing() {
        return ongoing;
    }

    public int getCompleted() {
        return completed;
    }

    public void setPending(int pending) {
        this.pending = pending;
    }

    public void setOngoing(int ongoing) {
        this.ongoing = ongoing;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }
}
