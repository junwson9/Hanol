import React, { useState } from 'react';
import styled from 'styled-components';

interface Props {
  time: string;
  isActive: boolean;
}
const NotificationTimeBox = ({ time, isActive }: Props) => {
  return (
    <div className="col-span-full">
      <TimeBox className={`${isActive ? 'active_div' : null}`}>
        <div className={`time_txt ${isActive ? 'active_txt' : null}`}>{time}</div>
      </TimeBox>
    </div>
  );
};

const TimeBox = styled.div`
  display: flex;
  width: 6.125 rem;
  height: 2.25 rem;
  /* padding: 16px 14px; */
  justify-content: center;
  align-items: center;
  gap: 10px;
  flex-shrink: 0;
  border: 1px solid var(--GrayForText, #888);
  border-radius: 8px;
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

export default NotificationTimeBox;
