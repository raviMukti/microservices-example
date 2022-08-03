package com.micro.account.query.api.queries;

import com.micro.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindAccountByIdQuery extends BaseQuery {
    private String id;
}
