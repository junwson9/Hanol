package com.ssafy.hanol.notification.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FcmRepositoryImpl implements FcmRepository {

    private final QueryDslFcmRepository queryDslFcmRepository;
}
