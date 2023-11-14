package com.ssafy.hanol.global.batch.expression;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;

public interface WhereNumberFunction<N extends Number & Comparable<?>> {

    BooleanExpression apply(NumberPath<N> id, int page, N currentId);
}
