import React from 'react';
import styled from 'styled-components';
import illust from '../../assets/images/checklist_image.png';

const RecommendCareRoutine = () => {
  return (
    <RecMentBox>
      <div className="recommend_ment">두피 건강을 위한</div>
      <div className="recommend_ment">생활 습관을 기르고 싶다면?</div>
      <IllustrationBox className="w-[14rem]">
        <img src={illust} alt="3d 일러스트 아이콘" className="illustration" />
      </IllustrationBox>
    </RecMentBox>
  );
};

const IllustrationBox = styled.div`
  margin-top: 1.438rem;
  /* margin-bottom: 2.876rem; */
  align-self: center;
`;
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

  display: flex;
  flex-direction: column;
`;
export default RecommendCareRoutine;
