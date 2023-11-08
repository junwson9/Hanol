import React from 'react';
import axiosInstance from 'api/axiosInterceptor';
import styled from 'styled-components';
import { getMessaging, getToken } from 'firebase/messaging';
import Toggle from 'components/common/Toggle';

interface Props {
  title: string;
  desc: string;
  toggleState?: boolean;
  noti_type: string;
  onToggle?: (newState: boolean) => void;
}

const NotiSettingComponent = ({ title, desc, toggleState, noti_type, onToggle }: Props) => {
  // console.log('toggleState', toggleState, 'noti_type', noti_type);

  // FCM 토큰 관련
  // eslint-disable-next-line
  async function sendTokenToServer(messaging: any) {
    const token = await getToken(messaging, {
      vapidKey: process.env.REACT_APP_VAPID_KEY,
    });

    if (token) {
      // console.log('token: ', token);
      try {
        const response = await axiosInstance.post('/notifications/token', { fcm_token: token });
        console.log('알림설정 변경 후 토큰을 서버로 전송했습니다.', response.data);
      } catch (error) {
        console.error('알림설정 변경 후 토큰을 서버로 전송하는 중 에러 발생:', error);
      }
    } else {
      console.log('토큰을 가져오지 못했습니다.');
    }
  }

  const toggleHandler = () => {
    const data = {
      notification_type: noti_type,
      is_active: !toggleState,
    };
    axiosInstance
      // axios
      // .patch('http://localhost:4000/notifications', data)
      .patch('/notifications', data)
      .then((response) => {
        onToggle?.(!toggleState);
        console.log('알림 설정 변경 성공:', response);
      })
      .catch((error) => {
        console.error('알림 설정 변경 실패:', error);
      });

    //FCM 토큰 관련
    const messaging = getMessaging();
    sendTokenToServer(messaging);
  };
  return (
    <div className="col-span-full">
      <NotiSettingComponentBox>
        <TextBox>
          <div className="noti_setting_title">{title}</div>
          <div className="setting_desc">{desc}</div>
        </TextBox>
        <Toggle toggleState={toggleState} onClick={toggleHandler} />
      </NotiSettingComponentBox>
    </div>
  );
};

const TextBox = styled.div`
  display: flex;
  flex-direction: column;

  text-align: start;

  .noti_setting_title {
    color: #252321;
    font-family: Noto Sans KR;
    font-size: 16px;
    font-weight: 500;
    line-height: 16px;
    letter-spacing: 0.1px;
  }

  .setting_desc {
    color: #999;
    font-size: 12px;
    font-weight: 400;
    line-height: 12px;
    letter-spacing: 0.4px;

    margin-top: 0.625rem;
  }
`;
const NotiSettingComponentBox = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 0.75rem 0 1.25rem;
  /* padding: 0.938rem 1.063rem; */
  padding: 1.25rem;

  width: 100%;
  height: 100%;
  border-radius: 15px;
  background: #eee;
`;
export default NotiSettingComponent;
