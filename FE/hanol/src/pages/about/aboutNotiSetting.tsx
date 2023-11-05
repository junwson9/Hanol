import React, { useEffect, useState } from 'react';
import axiosInstance from 'api/axiosInterceptor';
// import axios from 'axios';
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
  // const [checkRoutine, setCheckRoutine] = useState<boolean>(false);
  // const [individualRoutine, setIndividualRoutine] = useState<boolean>(false);
  useEffect(() => {
    // axiosInstance
    // .get('/diagnoses?limit=20')
    axiosInstance
      // axios
      .get('/notifications')
      .then((response) => {
        console.log('알림 설정 조회 성공:', response.data.data);
        setNotiSettingData(response.data.data);
      })
      .catch((error) => {
        console.error('알림 설정 조회 실패:', error);
      });
  }, []);

  // useEffect(() => {
  //   setCheckRoutine(notiSettingData?.is_check_routine_active);
  //   setIndividualRoutine(notiSettingData?.is_individual_routine_active);
  //   console.log('notiSettingData', notiSettingData);
  //   console.log('checkRoutine:', checkRoutine);
  //   console.log('individualRoutine', individualRoutine);
  // }, [notiSettingData]);

  return (
    <div className="col-span-full">
      <TapBarDepth2 name="알림 설정" propsIsBack completeBtn />
      <NotiSettingComponent
        title="데일리 루틴 확인 알림"
        desc="저녁 9시 루틴을 수행했는지 확인해보세요."
        toggleState={notiSettingData.is_check_routine_active}
        noti_type="CHECK_ROUTINE"
        onToggle={(newState) =>
          setNotiSettingData((prevState) => ({ ...prevState, is_check_routine_active: newState }))
        }
      />
      <NotiSettingComponent
        title="데일리 루틴 개별 알림"
        desc="루틴 별로 설정한 시간에 알림을 드려요."
        toggleState={notiSettingData.is_individual_routine_active}
        noti_type="INDIVIDUAL_ROUTINE"
        onToggle={(newState) =>
          setNotiSettingData((prevState) => ({ ...prevState, is_individual_routine_active: newState }))
        }
      />
    </div>
  );
};

export default aboutNotiSetting;
