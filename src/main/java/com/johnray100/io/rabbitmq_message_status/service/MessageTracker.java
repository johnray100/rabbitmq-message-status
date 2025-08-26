package com.johnray100.io.rabbitmq_message_status.service;

import com.johnray100.io.rabbitmq_message_status.model.MessageRecord;
import com.johnray100.io.rabbitmq_message_status.repository.MessageRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class MessageTracker {

    @Autowired
    private MessageRecordRepository repository;

    private final List<String> pendingMessages = Collections.synchronizedList(new ArrayList<>());
    private final AtomicInteger ongoing = new AtomicInteger(0);
    private final AtomicInteger completed = new AtomicInteger(0);


    public void addPending(String message) {
        pendingMessages.add(message);
        repository.save(new MessageRecord(message, "pending"));
    }

    public void removePending(String msg) { pendingMessages.remove(msg); }

    public void incrementOngoing() { ongoing.incrementAndGet(); }
    public void decrementOngoing() { ongoing.decrementAndGet(); }

    public void incrementCompleted() { completed.incrementAndGet(); }

    public int getPending() { return pendingMessages.size(); }
    public int getOngoing() { return ongoing.get(); }
    public int getCompleted() { return completed.get(); }


//    public void markCompleted(String message) {
//        incrementCompleted();
//        removePending(message); // remove from in-memory list
//
//        // Update DB
//        MessageRecord record = (MessageRecord) repository.findByContent(message)
//                .orElseThrow(() -> new RuntimeException("Message not found in DB: " + message));
//        record.setStatus("completed");
//        repository.save(record);
//    }

    public void markCompleted(String message) {
        // Check DB first
        MessageRecord record = (MessageRecord) repository.findByContent(message)
                .orElse(null);
        if (record == null) {
            System.out.println("No record found for message: " + message);
            return; // do not increment completed
        }

        incrementCompleted();
        removePending(message);
        record.setStatus("completed");
        repository.save(record);
    }

}


