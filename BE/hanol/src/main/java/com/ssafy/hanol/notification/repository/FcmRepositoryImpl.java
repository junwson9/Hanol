package com.ssafy.hanol.notification.repository;

import com.ssafy.hanol.notification.service.fcm.EachRoutinePushTargetInfo;
import com.ssafy.hanol.notification.service.fcm.PushTargetInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FcmRepositoryImpl implements FcmRepository {

    private final QueryDslFcmRepository queryDslFcmRepository;

    @Override
    public List<PushTargetInfo> selectCheckRoutinePushTargets() {
        return queryDslFcmRepository.selectCheckRoutinePushTargets();
    }

    @Override
    public List<EachRoutinePushTargetInfo> selectEachRoutinePushTargets(LocalTime time) {
        return queryDslFcmRepository.selectEachRoutinePushTargets(time);
    }
}
