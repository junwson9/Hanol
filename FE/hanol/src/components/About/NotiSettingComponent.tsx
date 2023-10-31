import React, { useState } from 'react';
import styled from 'styled-components';
import Toggle from 'components/common/Toggle';

interface Props {
  title: string;
  desc: string;
}

const NotiSettingComponent = ({ title, desc }: Props) => {
  const [isToggleOn, setIsToggleOn] = useState(false);

  return (
    <div className="col-span-full">
      <NotiSettingComponentBox>
        <TextBox>
          <div className="noti_setting_title">{title}</div>
          <div className="setting_desc">{desc}</div>
        </TextBox>
        <Toggle isToggleOn={isToggleOn} setIsToggleOn={setIsToggleOn} />
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
