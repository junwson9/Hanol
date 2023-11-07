import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
// import axiosInstance from 'api/axiosInterceptor';
import axios from 'axios';
import styled from 'styled-components';
import BannerButton from 'components/button/BannerButton';
import ValueCard from 'components/DashboardPage/ValueCard';
import ValueGraph from 'components/DashboardPage/ValueGraph';
import DivisionRectangle from 'components/common/DivisionRectangle';
import RecommendCareRoutine from 'components/DashboardPage/RecommendCareRoutine';
import Button from 'components/button/Button';
import TopTab from 'components/common/TopTabNew';
import DateNavigateButton from 'components/button/DateNavigateButton';
import ScalpScaleView from 'components/DetailPage/ScalpScaleView';
import ScalpImageView from 'components/DetailPage/ScalpImageView';
import DateNavigateModal from 'components/DetailPage/DateNavigateModal';
import { diagnosisResultType } from 'types/DiagnosisResult';
import TapBar from 'components/common/TopBar';

const initData: diagnosisResultType[] = [
  {
    diagnosis_id: 0,
    member_id: 0,
    value1: 0,
    value2: 0,
    value3: 0,
    value4: 0,
    value5: 0,
    value6: 0,
    image_url: '',
    device_type: 0,
    scan_part: 0,
    created_date: '',
  },
];

const MyreportDashBoard = () => {
  const navigate = useNavigate();
  //대시보드
  const [isTabActive, setTabActive] = useState<boolean>(true);
  const [diagnosisList, setDiagnosisList] = useState<diagnosisResultType[]>(initData);
  const [graphValue, setgraphValue] = useState<number>(6);
  // const [diagnosisId, setDiagnosisId] = useState<number>();
  const [value1, setValue1] = useState<number>();
  const [value2, setValue2] = useState<number>();
  const [value3, setValue3] = useState<number>();
  const [value4, setValue4] = useState<number>();
  const [value5, setValue5] = useState<number>();
  const [value6, setValue6] = useState<number>();

  //상세보기
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [index, setIndex] = useState<number>(0);

  const handleTabClick = () => {
    setTabActive((prevActive: boolean) => !prevActive);
  };

  const handleButtonClick = () => {
    navigate('/test');
  };

  const handleBannerButtonClick = () => {
    navigate('/test');
  };

  const handleValueCardClick = (arg: number) => {
    setgraphValue(arg);
    console.log('whatvalue:', graphValue);
  };

  useEffect(() => {
    // axiosInstance
    // .get('/diagnoses?limit=20')
    axios
      .get('http://localhost:4000/diagnoses')
      .then((response) => {
        console.log('진단 결과 리스트 조회 성공:', response);
        setDiagnosisList(response.data.diagnosis_info_list);
        setValue1(response.data.diagnosis_info_list[0].value1);
        setValue2(response.data.diagnosis_info_list[0].value2);
        setValue3(response.data.diagnosis_info_list[0].value3);
        setValue4(response.data.diagnosis_info_list[0].value4);
        setValue5(response.data.diagnosis_info_list[0].value5);
        setValue6(response.data.diagnosis_info_list[0].value6);
      })
      .catch((error) => {
        console.error('진단 결과 리스트 조회 실패:', error);
      });
  }, []);
  // console.log(`zz: ${diagnosisList?.[0].value1}`);

  //   useEffect(() => {
  //     if (diagnosisList) {
  //       setValue1(diagnosisList[0].value1);
  //       setValue2(diagnosisList[0].value2);
  //       setValue3(diagnosisList[0].value3);
  //       setValue4(diagnosisList[0].value4);
  //       setValue5(diagnosisList[0].value5);
  //       setValue6(diagnosisList[0].value6);
  //     }
  //   }, [diagnosisList]);

  return (
    <MyreportContainer>
      <div className="grid grid-cols-6 gap-[10px] mx-[23px]">
        <div className="col-span-full">
          <TapBar name="마이리포트" noMargin />
        </div>
      </div>

      <TopTab active={isTabActive} title1="대시보드" title2="상세보기" onTabClick={handleTabClick} />
      {isTabActive ? (
        <>
          <div className="grid grid-cols-6 gap-[10px] mx-[23px]">
            <div className="col-span-full">
              <BannerButton name="내 두피 분석 하러가기" onClick={() => handleBannerButtonClick()} />
              <ValueCardBox>
                <ValueCard title="탈모" value={value6 || 0} onClick={() => handleValueCardClick(6)} />
                {/* <ValueCard title="탈모" value={diag} /> */}
                <ValueCard title="각질" value={value1 || 0} onClick={() => handleValueCardClick(1)} />
                <ValueCard title="피지" value={value2 || 0} onClick={() => handleValueCardClick(2)} />
              </ValueCardBox>
              <ValueCardBox>
                <ValueCard title="홍반" value={value3 || 0} onClick={() => handleValueCardClick(3)} />
                <ValueCard title="염증" value={value4 || 0} onClick={() => handleValueCardClick(4)} />
                <ValueCard title="비듬" value={value5 || 0} onClick={() => handleValueCardClick(5)} />
              </ValueCardBox>
              <ValueGraph title="탈모" dataList={diagnosisList || []} graphValue={graphValue} />
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
        </>
      ) : (
        <div className="grid grid-cols-6 gap-[10px] mx-[23px]">
          <div className="col-span-full">
            <OverwrapContainer1>
              {diagnosisList.length > 0 && index < diagnosisList.length && (
                <>
                  <DateNavigateButton
                    index={index}
                    setIndex={setIndex}
                    date={diagnosisList[index].created_date}
                    onClick={() => setIsModalOpen(true)}
                    length={diagnosisList.length}
                  />
                  <ScalpScaleView
                    value1={diagnosisList[index].value1}
                    value2={diagnosisList[index].value2}
                    value3={diagnosisList[index].value3}
                    value4={diagnosisList[index].value4}
                    value5={diagnosisList[index].value5}
                    value6={diagnosisList[index].value6}
                  />
                  <ScalpImageView
                    sub_title={diagnosisList[index].scan_part}
                    // scalp_img="../.../src/assets/images/scalp.jpg"
                    scalp_img={diagnosisList[index].image_url}
                  />
                  {isModalOpen && (
                    <OverwrapContainer2>
                      <DateNavigateModal
                        date={diagnosisList[index].created_date}
                        setIndex={setIndex}
                        setIsModalOpen={setIsModalOpen}
                        diagnosisResults={diagnosisList}
                      />
                    </OverwrapContainer2>
                  )}
                </>
              )}
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

const OverwrapContainer2 = styled.div`
  position: absolute;
  top: 0;
  left: 0;

  width: 100%;
`;

const OverwrapContainer1 = styled.div``;

const MyreportContainer = styled.div`
  position: relative;
`;
export default MyreportDashBoard;
