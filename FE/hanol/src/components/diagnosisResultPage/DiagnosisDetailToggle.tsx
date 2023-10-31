import React, { useState } from 'react';
import styled from 'styled-components';
import { ReactComponent as SeeMoreIcon } from '.././../assets/icons/expand_more_FILL0_wght400_GRAD0_opsz24 2.svg';
import { ReactComponent as NoSeeMoreIcon } from '.././../assets/icons/expand_back_FILL0_wght400_GRAD0_opsz24 2.svg';

interface Props {
  diagnosisTitle: string;
  diagnosisContent: string;
}

const DiagnosisDetailToggle = ({ diagnosisTitle, diagnosisContent }: Props) => {
  const [isToggleOpen, SetToggleOpen] = useState(false);

  const handleToggle = () => {
    SetToggleOpen((isToggleOpen) => !isToggleOpen);
  };

  return (
    // <div className="col-span-full">
    <DiagnosisDetailBox>
      <TitleBox>
        <div className="for_hidden">
          <NoSeeMoreIcon />
        </div>
        <div className="diagnosis_title">{diagnosisTitle}</div>
        <div onClick={handleToggle} className="toggle_icon_div">
          {isToggleOpen ? <NoSeeMoreIcon className="toggle_icon" /> : <SeeMoreIcon className="toggle_icon" />}
        </div>
      </TitleBox>
      {isToggleOpen ? (
        <ContentBox>
          <div className="diagnosis_content">{diagnosisContent}</div>
        </ContentBox>
      ) : (
        ''
      )}
    </DiagnosisDetailBox>
    // </div>
  );
};

const ContentBox = styled.div`
  margin-top: 1.25rem;

  .diagnosis_content {
    color: var(--Black, #252321);
    font-family: Noto Sans KR;
    font-size: 13px;
    font-weight: 400;
    line-height: 23px; /* 176.923% */
    word-break: keep-all;
  }
`;
const TitleBox = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;

  width: 100%;
  height: 1rem;
  .for_hidden {
    visibility: hidden;
  }
  .diagnosis_title {
    /* justify-self: center; */
    /* align-self: center; */
    cursor: pointer;
    color: var(--Black, #252321);
    text-align: center;

    /* PowerBody 1 */
    font-family: Noto Sans KR;
    font-size: 16px;
    font-weight: 500;
    line-height: 16px;
    letter-spacing: 0.1px;
  }
  .toggle_icon_div {
    cursor: pointer;
    /* display: flex;
    justify-content: center;
    align-items: center;

    align-self: center; */
    /* width: 16px; */
    /* height: 16px; */
  }

  .toggle_icon {
    display: inline-flex;
  }
`;

const DiagnosisDetailBox = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-top: 0.938rem;

  background-color: var(--white, #fffeff);
  border: 0.2px solid var(--GrayForTab, #bcbcbc);
  border-radius: 18px;
  opacity: 0.76;

  padding: 1.25rem 0.938rem;
`;
export default DiagnosisDetailToggle;
