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
              {/* <ValueGraph title="탈모" dataList={diagnosisList} graphValue={graphValue} setIndex={setIndex} /> */}
              <div className="explain-container">
                <img src={BlurredImage} alt="설명을 위한 이미지입니다." className="explain_img" />
                <div className="explain">아직 진단 결과가 없습니다.</div>
              </div>
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
              {/* <DateNavigateButton
                index={index}
                setIndex={setIndex}
                date={formatDate(diagnosisList[index].created_date)}
                onClick={() => setIsModalOpen(true)}
                length={diagnosisList.length}
              /> */}
              {/* <ScalpScaleView
                value1={diagnosisList[index].value1}
                value2={diagnosisList[index].value2}
                value3={diagnosisList[index].value3}
                value4={diagnosisList[index].value4}
                value5={diagnosisList[index].value5}
                value6={diagnosisList[index].value6}
              /> */}
              {/* <ScalpImageView
                sub_title={diagnosisList[index].scan_part}
                // scalp_img="../.../src/assets/images/scalp.jpg"
                scalp_img={diagnosisList[index].image_url}
              /> */}
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

const OverwrapContainer1 = styled.div``;

const DashBoardBox = styled.div`
  .explain-container {
    position: relative;
    margin-top: 1.25rem;
    text-align: center;
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
    }

    .explain_img {
      width: 100%;
    }
  }
`;
const MyreportContainer = styled.div`
  position: relative;
`;
export default MyreportNonMember;
