package com.micro.account.query.infrastructure.consumers;

import com.micro.account.common.events.AccountClosedEvent;
import com.micro.account.common.events.AccountOpenedEvent;
import com.micro.account.common.events.FundsDepositEvent;
import com.micro.account.common.events.FundsWithdrawnEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {
    void consume(@Payload AccountOpenedEvent event, Acknowledgment ack);
    void consume(@Payload FundsDepositEvent event, Acknowledgment ack);
    void consume(@Payload FundsWithdrawnEvent event, Acknowledgment ack);
    void consume(@Payload AccountClosedEvent event, Acknowledgment ack);
}
