package com.micro.cqrs.core.queries;

import com.micro.cqrs.core.domain.BaseEntity;

import java.util.List;

public interface QueryHandlerMethod<T extends BaseQuery>{
    List<BaseEntity> handler(T query);
}
