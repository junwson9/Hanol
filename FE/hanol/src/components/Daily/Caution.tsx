import React from 'react';
import styled from 'styled-components';

const NotificationTime = () => {
  return (
    <div className="col-span-full">
      <CautionBox>
        <div className="caution_text">
          <p>[!] 알림 설정을 했는데 알림을 받지 못했다면?</p>
          <p>‘더보기 &gt; 알림 설정’에서 ‘데일리 루틴 개별 알림’을 활성화해주세요.</p>
        </div>
      </CautionBox>
    </div>
  );
};

const CautionBox = styled.div`
  margin-top: 0.938rem;

  .caution_text {
    color: var(--GrayForText, #888);
    font-family: Noto Sans KR;
    font-size: 12px;
    font-weight: 400;
    line-height: 140%; /* 16.8px */
    letter-spacing: 0.5px;

    text-align: start;
  }
`;
export default NotificationTime;
