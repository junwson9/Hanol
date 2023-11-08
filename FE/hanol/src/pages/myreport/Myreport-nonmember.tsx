import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
// import axios from 'axios';
import styled from 'styled-components';
import BannerButton from 'components/button/BannerButton';
import ValueCard from 'components/DashboardPage/ValueCard';
import DivisionRectangle from 'components/common/DivisionRectangle';
import RecommendCareRoutine from 'components/DashboardPage/RecommendCareRoutine';
import Button from 'components/button/Button';
import TopTab from 'components/common/TopTabNew';
import TapBar from 'components/common/TopBar';
import BlurredImage from '../../assets/images/blurred_graph.png';
import BlurredImageRadar from '../../assets/images/blurred_image_radar.png';
import { ReactComponent as HelpIcon } from '../../assets/icons/help_FILL0_wght400_GRAD0_opsz24 1.svg';
import ScalpImageView from 'components/DetailPage/ScalpImageView';
import ScalpImgDefault from 'assets/images/scalp_img_default.png';
// import { MemberRoleState } from 'recoil/atoms';
// import { useRecoilValue } from 'recoil';

const MyreportNonMember = () => {
  const navigate = useNavigate();
  //   const Role = useRecoilValue(MemberRoleState);

  //대시보드
  const [isTabActive, setTabActive] = useState<boolean>(true);

  const handleTabClick = () => {
    setTabActive((prevActive: boolean) => !prevActive);
  };

  const handleButtonClick = () => {
    navigate('/routine');
  };

  const handleBannerButtonClick = () => {
    navigate('/diagnose');
  };

  return (
    <MyreportContainer>
      <div className="grid grid-cols-6 gap-[10px] mx-[23px]">
        <div className="col-span-full">
          <TapBar name="마이리포트" noMargin />
        </div>
      </div>

      <TopTab active={isTabActive} title1="대시보드" title2="상세보기" onTabClick={handleTabClick} />
      {isTabActive ? (
        <DashBoardBox>
          <div className="grid grid-cols-6 gap-[10px] mx-[23px]">
            <div className="col-span-full">
              <BannerButton name="내 두피 분석 하러가기" onClick={() => handleBannerButtonClick()} />
              <ValueCardBox>
                <ValueCard title="탈모" value={5} />
                <ValueCard title="각질" value={5} />
                <ValueCard title="피지" value={5} />
              </ValueCardBox>
              <ValueCardBox>
                <ValueCard title="홍반" value={5} />
                <ValueCard title="염증" value={5} />
                <ValueCard title="비듬" value={5} />
              </ValueCardBox>
              <ExplainContainer>
                <img src={BlurredImage} alt="설명을 위한 이미지입니다." className="explain_img" />
                <div className="explain">아직 진단 결과가 없습니다.</div>
              </ExplainContainer>
            </div>
          </div>
          <DivisionRectangle />
          <div className="grid grid-cols-6 gap-[10px] mx-[23px]">
            <div className="col-span-full">
              <RecommendCareRoutine />
              <Button name="두피 케어 루틴 추천 받기" onClick={() => handleButtonClick()} />
              <br />
              <br />
              <br />
            </div>
          </div>
        </DashBoardBox>
      ) : (
        <div className="grid grid-cols-6 gap-[10px] mx-[23px]">
          <div className="col-span-full">
            <OverwrapContainer1>
              <DateNavigateButtonBox>
                <div className="date_box">2023-11-08 17:09</div>
              </DateNavigateButtonBox>

              <TitleBox>
                <div className="title">두피 건강점수</div>
                <HelpIcon className="help_icon" />
              </TitleBox>
              <ExplainContainer>
                <img src={BlurredImageRadar} alt="설명을 위한 이미지입니다." className="explain_img_radar" />
                <div className="explain">아직 진단 결과가 없습니다.</div>
              </ExplainContainer>
              <ScalpImageView sub_title={6} scalp_img={ScalpImgDefault} />
            </OverwrapContainer1>
          </div>
        </div>
      )}
    </MyreportContainer>
  );
};

const ValueCardBox = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 1.25rem;
`;

// const OverwrapContainer2 = styled.div`
//   position: absolute;
//   top: 0;
//   left: 0;

//   width: 100%;
// `;

const DateNavigateButtonBox = styled.div`
  align-self: center;
  display: flex;
  justify-content: center;
  align-items: center;
  /* width: 14.125rem; */
  width: 19.625rem;
  height: 2.188rem;
  border-radius: 18px;
  border: 0.2px solid var(--GrayForTab, #bcbcbc);
  opacity: 0.76;
  background: var(--white, #fffeff);
  margin: 1.563rem 0;

  .date_box {
    /* display: flex;
    flex-direction: column;
    justify-content: center; */

    width: 12.813rem;
    height: 0.875rem;
    flex-shrink: 0;

    color: var(--Black, #252321);
    text-align: center;

    font-family: Noto Sans KR;
    font-size: 14px;
    font-weight: 400;
    line-height: 14px;
    letter-spacing: 0.4px;

    filter: blur(2px);
  }
`;

const OverwrapContainer1 = styled.div`
  display: flex;
  flex-direction: column;
`;

const ExplainContainer = styled.div`
  position: relative;
  text-align: center;
  align-self: center;

  .explain {
    position: absolute;
    top: 50%; // Adjust as needed to center vertically
    left: 50%; // Adjust as needed to center horizontally
    transform: translate(-50%, -50%);
    color: #252321;
    font-size: 18px;
    font-weight: 700;
    line-height: 140%;
    letter-spacing: 0.2px;
    width: 100%;
  }

  .explain_img {
    width: 100%;
    margin-top: 1.25rem;
  }

  .explain_img_radar {
    width: 14.125rem;
    height: 14.125rem;
    object-fit: cover;
    margin-top: 1.5rem;
    align-self: center;
    justify-self: center;
  }
`;

const MyreportContainer = styled.div``;

const TitleBox = styled.div`
  display: flex;
  height: 1.125rem;
  justify-content: flex-start;
  /* margin-top: 1.563rem; */

  .title {
    color: var(--Black, #252321);

    font-family: Noto Sans KR;
    font-size: 18px;
    font-weight: 700;
    line-height: 18px;
    letter-spacing: 0.2px;
  }

  .help_icon {
    margin-left: 0.3125rem;
    align-self: flex-start;
    // 추후 수정
    margin-top: 1px;
    cursor: pointer;
  }
`;
const DashBoardBox = styled.div``;
export default MyreportNonMember;
