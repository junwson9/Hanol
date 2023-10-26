import React from 'react';
// import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

interface Props {
  Indicator: string;
  title1: string;
  title2: string;
}

interface StyleProps {
  Indicator: string;
}

const TopTab = ({ Indicator, title1, title2 }: Props) => {
  // const navigate = useNavigate();

  return (
    <TopTabBox>
      <LeftBox Indicator={Indicator}>
        <div className="top_tab_title">{title1}</div>

        <UnderBar>{Indicator === 'one' && <ActiveBar />}</UnderBar>
      </LeftBox>

      <RightBox Indicator={Indicator}>
        <div className="top_tab_title">{title2} </div>
        <UnderBar>{Indicator === 'two' && <ActiveBar />} </UnderBar>
      </RightBox>
    </TopTabBox>
  );
};

const ActiveBar = styled.div`
  width: 142px;
  height: 2px;
  flex-shrink: 0;

  border-radius: 1px;
  background: #353d4a;
`;
const UnderBar = styled.div`
  display: flex;
  justify-content: center;

  width: 50%;
  height: 1px;
  background: #d1d5db;
`;

const RightBox = styled.div<StyleProps>`
  display: flex;
  width: 50%;
  padding-top: 12px;
  flex-direction: column;
  justify-content: flex-end;
  align-items: center;
  gap: 13px;
  cursor: pointer;
  .top_tab_title {
    color: ${(props) => (props.Indicator === 'two' ? '#353d4a !important' : '#6d7582')};
  }
`;
const LeftBox = styled.div<StyleProps>`
  display: flex;
  width: 50%;
  padding-top: 12px;
  flex-direction: column;
  justify-content: flex-end;
  align-items: center;
  gap: 13px;
  cursor: pointer;
  .top_tab_title {
    color: ${(props) => (props.Indicator === 'one' ? '#353d4a !important' : '#6d7582')};
  }
`;
const TopTabBox = styled.div`
  display: inline-flex;
  align-items: flex-start;
  width: 100%;
  background: #fffeff;

  .top_tab_title {
    color: #6d7582;
    text-align: center;
    font-family: Noto Sans KR;
    font-size: 16px;
    font-style: normal;
    font-weight: 500;
    line-height: 140%;
    letter-spacing: 0.1px;
  }
`;

export default TopTab;
