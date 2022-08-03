package com.micro.account.query.infrastructure;

import com.micro.cqrs.core.domain.BaseEntity;
import com.micro.cqrs.core.infrastructure.QueryDispatcher;
import com.micro.cqrs.core.queries.BaseQuery;
import com.micro.cqrs.core.queries.QueryHandlerMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class AccountQueryDispatcher implements QueryDispatcher {
    private final Map<Class<? extends BaseQuery>, List<QueryHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handlerMethod) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handlerMethod);
    }

    @Override
    public <U extends BaseEntity> List<U> send(BaseQuery query) {
        var handlers = routes.get(query.getClass());
        if (handlers == null || handlers.size() <= 0)
        {
            throw new RuntimeException("No Query Handler Was Registered");
        } if (handlers.size() > 1)
        {
            throw new RuntimeException("Cannot Find Query To More than One Handler");
        }
        return handlers.get(0).handler(query);
    }
}
