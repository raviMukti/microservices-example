package com.micro.cqrs.core.producers;

import com.micro.cqrs.core.events.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}
