import React from 'react';
import styled from 'styled-components';
import Toggle from 'components/common/Toggle';

const PushSetting = () => {
  return (
    <div className="col-span-full">
      <PushSettingBox>
        <PushNotificationBox>
          <div className="push_notification_text">푸시 알림</div>
          <div className="toggle_box">
            <Toggle />
          </div>
        </PushNotificationBox>
      </PushSettingBox>
    </div>
  );
};

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
