package com.johnakins.order.kafka;

import com.johnakins.order.outbox.OutboxEvent;
import com.johnakins.order.outbox.OutboxRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OutboxPublisher {

    private final OutboxRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    @Scheduled(fixedDelay = 5000)
    public void publish() {
        for (OutboxEvent event : repository.findByPublishedFalse()) {
            kafkaTemplate.send("order-topic", event.getPayload());
            event.setPublished(true);
            repository.save(event);
        }
    }
}

