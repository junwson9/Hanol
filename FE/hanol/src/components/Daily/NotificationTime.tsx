import React from 'react';
import styled from 'styled-components';

interface Props {
  timename: string;
  isActive: boolean;
  setActiveTime: (time: number) => void;
  index: number;
}
const NotificationTime = ({ timename, isActive, setActiveTime, index }: Props) => {
  return (
    <div className="col-span-full">
      <TimeBox onClick={() => setActiveTime(index)}>
        <BoxDiv className={`${isActive ? 'active_div' : null}`}>
          <div className={`time_txt ${isActive ? 'active_txt' : null}`}>{timename}</div>
        </BoxDiv>
      </TimeBox>
    </div>
  );
};

const BoxDiv = styled.div`
  display: flex;
  width: 100%;
  height: 2.25rem;
  padding: 1rem 1.875rem;
  justify-content: center;
  align-items: center;
  flex-shrink: 0;
  border: 1px solid var(--GrayForText, #888);
  border-radius: 8px;
  margin: 0 10px;
  cursor: pointer;
`;
const TimeBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  margin: 0 10px;

  .active_div {
    border: 1px solid var(--Main, #3fcc8a);
  }

  .time_txt {
    text-align: center;
    color: var(--GrayForText, #888);
    font-family: Noto Sans KR;
    font-size: 12px;
    font-weight: 400;
    line-height: 12px;
    letter-spacing: 0.4px;
  }

  .active_txt {
    color: var(--Main, #3fcc8a);
  }
`;

export default NotificationTime;
