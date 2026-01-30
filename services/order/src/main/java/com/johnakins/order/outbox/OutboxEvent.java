package com.johnakins.order.outbox;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "outbox_events")
public class OutboxEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String aggregateType;
    private Integer aggregateId;
    private String eventType;

    @Lob
    private String payload;

    private Instant createdAt = Instant.now();
    private boolean published = false;
}
