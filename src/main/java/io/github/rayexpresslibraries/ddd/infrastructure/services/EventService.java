package io.github.rayexpresslibraries.ddd.infrastructure.services;

public interface EventService {
    void send(Object event);
}
