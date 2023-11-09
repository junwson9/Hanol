import React from 'react';
// import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

interface Props {
  valuenumber?: number;
}

interface ValueColorProps {
  textcolor: string;
  backgroundcolor: string;
}

const ValueDiv = ({ valuenumber }: Props) => {
  const determineValueTitle = (valuenumber: number) => {
    switch (valuenumber) {
      case 0:
        return '우수';
      case 1:
        return '양호';
      case 2:
        return '주의';
      case 3:
        return '위험';
      case 5:
        return '-';
      default:
        return '정도';
    }
  };
  const determineTextColor = (valuenumber: number) => {
    switch (valuenumber) {
      // 우수
      case 0:
        return '#5BC3C4';
      case 1:
        return '#6BE464';
      case 2:
        return '#FBDE48';
      case 3:
        return '#EA536F';
      case 5:
        return '#C5C5C5';
      default:
        return 'white';
    }
  };
  const determineBackgroundColor = (valuenumber: number) => {
    switch (valuenumber) {
      // 우수
      case 0:
        return '#F2FEFE';
      case 1:
        return '#F1FEF3';
      case 2:
        return '#FEF9E2';
      case 3:
        return '#FDEFF1';
      case 5:
        return '#F5F5F5';
      default:
        return 'white';
    }
  };
  const textcolor = determineTextColor(valuenumber || 0);
  const backgroundcolor = determineBackgroundColor(valuenumber || 0);

  return (
    <ValueBox textcolor={textcolor} backgroundcolor={backgroundcolor}>
      <div className="valuenumber">{determineValueTitle(valuenumber || 0)}</div>
    </ValueBox>
  );
};

const ValueBox = styled.div<ValueColorProps>`
  display: flex;
  justify-content: center;

  width: 50px;
  height: 35px;
  flex-shrink: 0;
  /* background-color: #f2fefe; */
  background-color: ${(props) => props.backgroundcolor};
  border-radius: 20px;

  .valuenumber {
    width: 37px;
    height: 24px;
    flex-shrink: 0;

    /* color: #5bc3c4; */
    color: ${(props) => props.textcolor};
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

export default ValueDiv;
