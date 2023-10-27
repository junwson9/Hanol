import React from 'react';
import styled from 'styled-components';
import AnalyzingAnimation from 'components/Animation/AnalyzingAnimation';

const AnalyzingPage = () => {
  return (
    <div className="col-span-full">
      <AnalyzingPageBox>
        <AnalyzingAnimation />
        <div className="ment">두피를 분석하고 있어요</div>
      </AnalyzingPageBox>
    </div>
  );
};

const AnalyzingPageBox = styled.div`
  display: flex;

  flex-direction: column;
  /* align-items: center; */
  justify-content: center;

  margin-top: 25vh;

  .ment {
    color: var(--Black, #252321);
    text-align: center;

    font-family: Noto Sans KR;
    font-size: 20px;
    font-weight: 700;
    line-height: 20px;

    margin-top: 0.625rem;
  }
`;
export default AnalyzingPage;
