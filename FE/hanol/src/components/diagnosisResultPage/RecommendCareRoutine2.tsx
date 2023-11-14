import React from 'react';
import styled from 'styled-components';
import illust from '../../assets/images/checklist_image.png';

const RecommendCareRoutine2 = () => {
  return (
    <div className="col-span-full">
      <RecMentBox>
        <div className="title">두피 관리 미루지 마세요</div>
        <ContentBox>
          <div className="content">회원님께 꼭 맞는 두피 케어 루틴을 추천해드려요.</div>
          <div className="content">매일 루틴을 실천하고 두피 건강을 챙기세요.</div>
        </ContentBox>
        <IllustrationBox className="w-[14rem]">
          <img src={illust} alt="3d 일러스트 아이콘" className="illustration" />
        </IllustrationBox>
      </RecMentBox>
    </div>
  );
};

const IllustrationBox = styled.div`
  margin-top: 1.438rem;
  margin-bottom: 2.876rem;
  align-self: center;
`;
const ContentBox = styled.div`
  color: var(--GrayForText, #888);

  font-family: Noto Sans KR;
  font-size: 12px;
  font-weight: 400;
  line-height: 17px;
  letter-spacing: 0.4px;
  margin-top: 1.438rem;
  text-align: start;
`;
const RecMentBox = styled.div`
  display: flex;
  flex-direction: column;

  .title {
    color: var(--Black, #252321);
    font-family: Noto Sans KR;
    font-size: 18px;
    font-weight: 700;
    line-height: 18px;
    letter-spacing: 0.2px;
    text-align: start;

    /* margin-top: 4.625rem; */
  }
`;
export default RecommendCareRoutine2;
