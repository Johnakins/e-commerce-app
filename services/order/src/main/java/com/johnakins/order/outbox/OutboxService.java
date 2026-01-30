package com.johnakins.order.outbox;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class OutboxService {

    private final OutboxRepository repository;
    private final ObjectMapper mapper = new ObjectMapper();

    public OutboxService(OutboxRepository repository) {
        this.repository = repository;
    }

    public void saveEvent(String type, Integer aggregateId, Object event) {
        try {
            OutboxEvent outbox = new OutboxEvent();
            outbox.setAggregateType("Order");
            outbox.setAggregateId(aggregateId);
            outbox.setEventType(type);
            outbox.setPayload(mapper.writeValueAsString(event));
            repository.save(outbox);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
