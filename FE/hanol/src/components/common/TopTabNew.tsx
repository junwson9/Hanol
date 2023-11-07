import React from 'react';
import styled from 'styled-components';

interface Props {
  active: boolean;
  title1: string;
  title2: string;
  onTabClick: () => void;
}

interface StyleProps {
  active: boolean;
}

const TopTab = ({ active, title1, title2, onTabClick }: Props) => {
  const handleTabClick = () => {
    // 탭을 클릭할 때마다 상위 컴포넌트에서 제공한 `onTabClick` 콜백 함수를 호출합니다.
    onTabClick();
  };
  console.log(active);
  return (
    <TopTabBox>
      <LeftBox active={active} onClick={handleTabClick}>
        <div className="top_tab_title">{title1}</div>
        <UnderBar>{active && <ActiveBar />}</UnderBar>
      </LeftBox>

      <RightBox active={!active} onClick={handleTabClick}>
        <div className="top_tab_title">{title2} </div>
        <UnderBar>{!active && <ActiveBar />} </UnderBar>
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
  width: 100%;
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
    color: ${(props) => (props.active === true ? '#353d4a !important' : '#6d7582')};
  }
};
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
    color: ${(props) => (props.active === true ? '#353d4a !important' : '#6d7582')};
  }
};
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
};
`;
export default TopTab;
