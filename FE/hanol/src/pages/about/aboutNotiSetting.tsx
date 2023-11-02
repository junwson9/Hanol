import React, { useEffect, useState } from 'react';
import axiosInstance from 'api/axiosInterceptor';
import NotiSettingComponent from 'components/About/NotiSettingComponent';
import TapBarDepth2 from 'components/common/TapBarDepth2';
import { aboutnotiSettingType } from 'types/DiagnosisResult';

const initData: aboutnotiSettingType = {
  notification_setting_id: 0,
  member_id: 0,
  is_check_routine_active: false,
  is_individual_routine_active: false,
};

const aboutNotiSetting = () => {
  const [notiSettingData, setNotiSettingData] = useState<aboutnotiSettingType>(initData);
  const [checkRoutine, setCheckRoutine] = useState<boolean>(false);
  const [individualRoutine, setIndividualRoutine] = useState<boolean>(false);

  useEffect(() => {
    // axiosInstance
    // .get('/diagnoses?limit=20')
    axiosInstance
      .get('/notifications')
      .then((response) => {
        console.log('알림 설정 조회 성공:', response);
        setNotiSettingData(response.data);
      })
      .catch((error) => {
        console.error('알림 설정 조회 실패:', error);
      });
  }, [checkRoutine, individualRoutine]);

  useEffect(() => {
    setCheckRoutine(notiSettingData?.is_check_routine_active);
    setIndividualRoutine(notiSettingData?.is_individual_routine_active);
  }, [notiSettingData]);

  // const handleCompleteBtnClick = () => {
  //   const data = {
  //     notification_type: noti_type,
  //     is_active: isToggleOn,
  //   };
  //   axiosInstance
  //     .patch('/notifications', data)
  //     .then((response) => {
  //       console.log('알림 설정 변경 요청 성공:', response);
  //     })
  //     .catch((error) => {
  //       console.error('알림 설정 변경 요청 실패:', error);
  //     });
  // };

  return (
    <div className="col-span-full">
      <TapBarDepth2 name="알림 설정" propsIsBack completeBtn />
      <NotiSettingComponent
        title="데일리 루틴 확인 알림"
        desc="저녁 9시 루틴을 수행했는지 확인해보세요."
        toggleState={checkRoutine}
        noti_type="CHECK_ROUTINE"
        setToggleState={setCheckRoutine}
      />
      <NotiSettingComponent
        title="데일리 루틴 개별 알림"
        desc="루틴 별로 설정한 시간에 알림을 드려요."
        toggleState={individualRoutine}
        noti_type="INDIVIDUAL_ROUTINE"
        setToggleState={setIndividualRoutine}
      />
    </div>
  );
};

export default aboutNotiSetting;
