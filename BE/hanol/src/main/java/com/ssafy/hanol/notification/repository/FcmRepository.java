package com.ssafy.hanol.notification.repository;

import com.ssafy.hanol.notification.service.fcm.EachRoutinePushTargetInfo;
import com.ssafy.hanol.notification.service.fcm.PushTargetInfo;

import java.time.LocalTime;
import java.util.List;

public interface FcmRepository {
    List<PushTargetInfo> selectCheckRoutinePushTargets();
    List<EachRoutinePushTargetInfo> selectEachRoutinePushTargets(LocalTime time);
}
