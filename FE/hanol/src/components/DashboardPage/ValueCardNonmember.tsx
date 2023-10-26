import React from 'react';
// import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

interface Props {
  title: string;
}

const ValueCardNonmember = ({ title }: Props) => {
  return (
    <ValueCardBox>
      <div className="value_title">{title}</div>
      <ValueBox>
        <div className="value">-</div>
      </ValueBox>
    </ValueCardBox>
  );
};

const ValueBox = styled.div`
  display: flex;
  justify-content: center;

  width: 50px;
  height: 35px;
  flex-shrink: 0;
  /* background-color: #f2fefe; */
  background-color: #f5f5f5;
  border-radius: 20px;

  .value {
    width: 37px;
    height: 24px;
    flex-shrink: 0;

    color: #c5c5c5;
    text-align: center;

    /* Body Header */
    font-family: Noto Sans KR;
    font-size: 18px;
    font-style: normal;
    font-weight: 700;
    line-height: 35px;
    letter-spacing: 0.2px;
  }
`;
const ValueCardBox = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;
  /* 후에 수정 */
  width: 98px;
  height: 80px;
  flex-shrink: 0;

  border-radius: 18px;
  border: 0.2px solid var(--GrayForTab, #bcbcbc);
  opacity: 0.76;
  background: var(--white, #fffeff);

  .value_title {
    display: flex;
    width: 37px;
    height: 17px;
    flex-direction: column;
    justify-content: center;
    flex-shrink: 0;

    color: var(--Black, #252321);
    text-align: center;

    /* PowerBody 1 */
    font-family: Noto Sans KR;
    font-size: 16px;
    font-style: normal;
    font-weight: 500;
    line-height: 140%; /* 22.4px */
    letter-spacing: 0.1px;
  }
`;

export default ValueCardNonmember;
