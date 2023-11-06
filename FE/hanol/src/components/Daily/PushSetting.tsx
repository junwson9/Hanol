import React from 'react';
import styled from 'styled-components';
import Toggle from 'components/common/Toggle';
import NotificationTime from './NotificationTime';

interface Props {
  onToggle?: (newState: boolean) => void;
  toggleState?: boolean;
  notiTime?: string;
  setNotitime: (arg: string) => void;
}

const PushSetting = ({ onToggle, toggleState, notiTime, setNotitime }: Props) => {
  console.log('toggleState', toggleState);

  const toggleHandler = () => {
    onToggle?.(!toggleState);
  };

  return (
    <div className="col-span-full">
      <PushSettingBox>
        <PushNotificationBox>
          <div className="push_notification_text">푸시 알림</div>
          <div className="toggle_box">
            <Toggle toggleState={toggleState} onClick={toggleHandler} />
          </div>
        </PushNotificationBox>

        {toggleState ? (
          <NotificationTimeBox>
            <NotificationTime
              timename="오전 9시"
              isActive={notiTime === '09:00:00'}
              setNotitime={() => setNotitime('09:00:00')}
              time="09:00:00"
            />
            <NotificationTime
              timename="오후 1시"
              isActive={notiTime === '13:00:00'}
              setNotitime={() => setNotitime('13:00:00')}
              time="13:00:00"
            />
            <NotificationTime
              timename="오후 7시"
              isActive={notiTime === '19:00:00'}
              setNotitime={() => setNotitime('19:00:00')}
              time="19:00:00"
            />
          </NotificationTimeBox>
        ) : (
          ''
        )}
      </PushSettingBox>
    </div>
  );
};

const NotificationTimeBox = styled.div`
  display: flex;
  margin-top: 1.25rem;
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
  height: 100%;
`;
export default PushSetting;
