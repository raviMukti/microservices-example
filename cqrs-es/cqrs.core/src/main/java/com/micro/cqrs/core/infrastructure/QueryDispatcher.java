package com.micro.cqrs.core.infrastructure;

import com.micro.cqrs.core.domain.BaseEntity;
import com.micro.cqrs.core.queries.BaseQuery;
import com.micro.cqrs.core.queries.QueryHandlerMethod;

import java.util.List;

public interface QueryDispatcher {
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handlerMethod);
    <U extends BaseEntity>List<U> send(BaseQuery query);
}
