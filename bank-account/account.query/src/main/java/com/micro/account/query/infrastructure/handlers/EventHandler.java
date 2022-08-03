package com.micro.account.query.infrastructure.handlers;

import com.micro.account.common.events.AccountClosedEvent;
import com.micro.account.common.events.AccountOpenedEvent;
import com.micro.account.common.events.FundsDepositEvent;
import com.micro.account.common.events.FundsWithdrawnEvent;

public interface EventHandler {
    void on(AccountOpenedEvent event);
    void on(FundsDepositEvent event);
    void on(FundsWithdrawnEvent event);
    void on(AccountClosedEvent event);
}
