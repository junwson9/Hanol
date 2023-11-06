import React from 'react';
import styled from 'styled-components';

interface Props {
  routineName?: string;
}

const RoutineName = ({ routineName }: Props) => {
  return (
    <div className="col-span-full">
      <RoutineNameBox>
        <div className="routine_name">
          {/* 루틴 이름이 들어갈 자리입니다.루틴 이름이 들어갈 자리입니다.루틴 이름이 들어갈 자리입니다. */}
          {routineName}
        </div>
        <div className="underline"></div>
      </RoutineNameBox>
    </div>
  );
};

const RoutineNameBox = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 1.75rem;

  .routine_name {
    color: var(--Black, #252321);
    font-family: Noto Sans KR;
    font-size: 16px;
    font-weight: 500;
    line-height: 140%;
    letter-spacing: 0.1px;

    text-align: start;
  }

  .underline {
    height: 1px;
    background-color: #d9d9d9;

    margin-top: 0.625rem;
  }
`;
export default RoutineName;
