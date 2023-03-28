package com.github.rayexpresslibraries.ddd.domain.events;

@FunctionalInterface
public interface EventPublisher {
    void publishEvent(Event event);
}
