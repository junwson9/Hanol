package com.ssafy.hanol.global.batch.options;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQuery;
import com.ssafy.hanol.global.batch.expression.Expression;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;

@Slf4j
public class QueryDslNoOffsetNumberOptions<T, N extends Number & Comparable<?>> extends QueryDslNoOffsetOptions <T> {

    private N currentId;
    private N lastId;

    private final NumberPath<N> field;

    public QueryDslNoOffsetNumberOptions(@Nonnull NumberPath<N> field, @Nonnull Expression expression) {
        super(field, expression);
        this.field = field;
    }

    public N getCurrentId() {
        return currentId;
    }

    public N getLastId() {
        return lastId;
    }

    @Override
    public void initKeys(JPAQuery<T> query, int page) {
        if(page == 0) {
            initFirstId(query);
            initLastId(query);

            if(log.isDebugEnabled()) {
                log.debug("First Key: {}, Last Key: {}", currentId, lastId);
            }
        }
    }

    @Override
    protected void initFirstId(JPAQuery<T> query) {
        JPAQuery<T> clone = query.clone();
        currentId = clone
                .select(expression.isAsc()? field.min() : field.max())
                .fetchFirst();
    }

    @Override
    protected void initLastId(JPAQuery<T> query) {
        JPAQuery<T> clone = query.clone();
        lastId = clone
                .select(expression.isAsc()? field.max() : field.min())
                .fetchFirst();
    }

    @Override
    public JPAQuery<T> createQuery(JPAQuery<T> query, int page) {
        if(currentId == null) {
            return query;
        }
        return query
                .where(whereExpression(page))
                .orderBy(orderExpression());
    }

    private BooleanExpression whereExpression(int page) {
        return expression.where(field, page, currentId)
                .and(expression.isAsc()? field.loe(lastId) : field.goe(lastId));
    }

    private OrderSpecifier<N> orderExpression() {
        return expression.order(field);
    }

    @Override
    public void resetCurrentId(T item) {
        currentId = (N) getFiledValue(item);

        if(log.isDebugEnabled()) {
            log.debug("Current Select Key: {}", currentId);
        }
    }
}
