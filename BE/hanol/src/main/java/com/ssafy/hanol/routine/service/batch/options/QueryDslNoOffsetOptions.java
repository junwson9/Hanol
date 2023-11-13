package com.ssafy.hanol.routine.service.batch.options;

import com.querydsl.core.types.Path;
import com.querydsl.jpa.impl.JPAQuery;
import com.ssafy.hanol.routine.service.batch.expression.Expression;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;

@Slf4j
public abstract class QueryDslNoOffsetOptions<T> {

    protected final String fieldName;
    protected final Expression expression;

    public QueryDslNoOffsetOptions(@Nonnull Path field, @Nonnull Expression expression) {
        String[] qField = field.toString().split("\\.");
        this.fieldName = qField[qField.length-1];
        this.expression = expression;

        if(log.isDebugEnabled()) {
            log.debug("fieldName: {}", fieldName);
        }
    }

    public String getFieldName() {
        return fieldName;
    }

    public abstract void initKeys(JPAQuery<T> query, int page);
    protected abstract void initFirstId(JPAQuery<T> query);
    protected abstract void initLastId(JPAQuery<T> query);
    public abstract JPAQuery<T> createQuery(JPAQuery<T> query, int page);
    public abstract void resetCurrentId(T item);

    protected Object getFiledValue(T item) {
        try {
            Field field = item.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(item);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.error("Not Found or Not Access Field= " + fieldName, e);
            throw new IllegalArgumentException("Not Found or Not Access Field");
        }
    }
}
