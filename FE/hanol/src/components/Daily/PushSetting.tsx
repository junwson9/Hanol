import React, { useState } from 'react';
import styled from 'styled-components';
import Toggle from 'components/common/Toggle';
import NotificationTime from './NotificationTime';

const PushSetting = () => {
  const [activeTime, setActiveTime] = useState(2);

  return (
    <div className="col-span-full">
      <PushSettingBox>
        <PushNotificationBox>
          <div className="push_notification_text">푸시 알림</div>
          <div className="toggle_box">
            <Toggle />
          </div>
        </PushNotificationBox>
        <NotificationTimeBox>
          <NotificationTime timename="오전 9시" isActive={activeTime === 0} setActiveTime={setActiveTime} index={0} />
          <NotificationTime timename="오후 1시" isActive={activeTime === 1} setActiveTime={setActiveTime} index={1} />
          <NotificationTime timename="오후 7시" isActive={activeTime === 2} setActiveTime={setActiveTime} index={2} />
        </NotificationTimeBox>
      </PushSettingBox>
    </div>
  );
};

const NotificationTimeBox = styled.div`
  display: flex;
  margin-top: 1.813rem;
  justify-content: center;
`;
const PushNotificationBox = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;

  .push_notification_text {
    color: #252321;
    font-family: Noto Sans KR;
    font-size: 16px;
    font-weight: 400;
    line-height: 16px;
    letter-spacing: 0.5px;
  }

  .toggle_box {
    align-self: center;
  }
`;
const PushSettingBox = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 1.813rem;

  width: 100%;
  height: 1.688rem;
`;
export default PushSetting;
