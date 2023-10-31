import React from 'react';
import styled from 'styled-components';

const RecommendCareRoutine = () => {
  return (
    <RecMentBox>
      <div className="recommend_ment">두피 건강을 위한</div>
      <div className="recommend_ment">생활 습관을 기르고 싶다면?</div>
    </RecMentBox>
  );
};

const RecMentBox = styled.div`
  color: var(--Black, #252321);
  font-family: Noto Sans KR;
  font-size: 18px;
  font-style: normal;
  font-weight: 700;
  line-height: 140%; /* 25.2px */
  letter-spacing: 0.2px;
  text-align: left;
  margin-bottom: 1.25rem;
`;
export default RecommendCareRoutine;
