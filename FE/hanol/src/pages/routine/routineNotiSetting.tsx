import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axiosInstance from 'api/axiosInterceptor';
import styled from 'styled-components';
import RoutineName from 'components/Daily/RoutineName';
import PushSetting from 'components/Daily/PushSetting';
import Caution from 'components/Daily/Caution';
import TopBarDepth2 from 'components/common/TapBarDepth2';
import { routineNotiSettingType } from 'types/DiagnosisResult';

const routineNotiSetting = () => {
  const navigate = useNavigate();
  const [routineNotiSettingData, setRoutineNotiSettingData] = useState<routineNotiSettingType>();
  const [notiTime, setNotiTime] = useState<string>();
  const { member_routine_id } = useParams();

  console.log('notiTime', notiTime);

  useEffect(() => {
    axiosInstance
      .get(`/routines/${member_routine_id}`)
      .then((response) => {
        console.log('루틴 알림 설정 조회 성공:', response.data.data);
        setRoutineNotiSettingData(response.data.data);
        setNotiTime(response.data.data.notification_time);
      })
      .catch((error) => {
        console.error('루틴 알림 설정 조회 실패:', error);
      });
  }, []);

  const handleCompleteClick = () => {
    const data = {
      is_notification_active: routineNotiSettingData?.is_notification_active,
      notification_time: notiTime,
    };
    axiosInstance
      .patch(`routines/${member_routine_id}/notification`, data)
      .then((response) => {
        console.log('루틴변경요청성공', response);
      })
      .catch((error) => {
        console.error('루틴변경요청실패', error);
      });
    navigate(-1);
  };
  return (
    <div className="col-span-full">
      <PageBox>
        <TopBarDepth2 name="루틴 알림 설정" rightBtnType={1} onClick={handleCompleteClick} propsIsBack />
        <RoutineName routineName={routineNotiSettingData?.routine_name} />
        {routineNotiSettingData && (
          <PushSetting
            notiTime={notiTime}
            setNotitime={setNotiTime}
            toggleState={routineNotiSettingData?.is_notification_active}
            onToggle={(newState) =>
              setRoutineNotiSettingData((prevState) => ({
                ...(prevState as routineNotiSettingType),
                is_notification_active: newState,
              }))
            }
          />
        )}
        <Caution />
      </PageBox>
    </div>
  );
};

const PageBox = styled.div``;

export default routineNotiSetting;
