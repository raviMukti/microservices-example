package com.micro.cqrs.core.infrastructure;

import com.micro.cqrs.core.commands.BaseCommand;
import com.micro.cqrs.core.commands.CommandHandlerMethod;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
