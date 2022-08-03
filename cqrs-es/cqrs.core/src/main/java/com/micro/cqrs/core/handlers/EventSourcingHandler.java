package com.micro.cqrs.core.handlers;

import com.micro.cqrs.core.domain.AggregateRoot;

public interface EventSourcingHandler<T> {
    void save(AggregateRoot aggregateRoot);
    T getById(String id);
    void republishEvents();
}
