package com.johnray100.io.rabbitmq_message_status.controller;

import com.johnray100.io.rabbitmq_message_status.model.MessageStatus;
import com.johnray100.io.rabbitmq_message_status.service.MessageProducer;
import com.johnray100.io.rabbitmq_message_status.service.MessageTracker;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageProducer producer;
    private final MessageTracker tracker;

    public MessageController(MessageProducer producer, MessageTracker tracker) {
        this.producer = producer;
        this.tracker = tracker;
    }

    @PostMapping("/pending")
    public String sendPending(@RequestBody String message) {
        tracker.addPending(message);
        producer.sendPending(message);
        return "Message sent to Pending queue: " + message;
    }

    @GetMapping("/status")
    public MessageStatus getStatus() {
        return new MessageStatus(
                tracker.getPending(),
                tracker.getOngoing(),
                tracker.getCompleted()
        );
    }
}

