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
import { diagnosisResultType } from 'types/DiagnosisResult';
import TapBar from 'components/common/TopBar';

const MyreportDashBoard = () => {
  const navigate = useNavigate();
  const [isTabActive, setTabActive] = useState<boolean>(false);
  const [diagnosisList, setDiagnosisList] = useState<diagnosisResultType[]>();
  const [graphValue, setgraphValue] = useState<number>(6);
  // const [diagnosisId, setDiagnosisId] = useState<number>();
  const [value1, setValue1] = useState<number>();
  const [value2, setValue2] = useState<number>();
  const [value3, setValue3] = useState<number>();
  const [value4, setValue4] = useState<number>();
  const [value5, setValue5] = useState<number>();
  const [value6, setValue6] = useState<number>();

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
      })
      .catch((error) => {
        console.error('진단 결과 리스트 조회 실패:', error);
      });
  }, []);
  // console.log(`zz: ${diagnosisList?.[0].value1}`);

  useEffect(() => {
    if (diagnosisList) {
      setValue1(diagnosisList[0].value1);
      setValue2(diagnosisList[0].value2);
      setValue3(diagnosisList[0].value3);
      setValue4(diagnosisList[0].value4);
      setValue5(diagnosisList[0].value5);
      setValue6(diagnosisList[0].value6);
    }
  }, [diagnosisList]);

  // useEffect(() => {
  //   if (diagnosisId && diagnosisList) {
  //     const selectedDiagnosis = diagnosisList.find((diagnosis) => diagnosis.diagnosis_id === diagnosisId);

  //     if (selectedDiagnosis) {
  //       setValue1(selectedDiagnosis.value1);
  //       setValue2(selectedDiagnosis.value2);
  //       setValue3(selectedDiagnosis.value3);
  //       setValue4(selectedDiagnosis.value4);
  //       setValue5(selectedDiagnosis.value5);
  //       setValue6(selectedDiagnosis.value6);
  //     }
  //   }
  // }, [diagnosisId, diagnosisList]);

  return (
    <div className="col-span-full">
      <TapBar name="마이리포트" noMargin />
      <TopTab active={isTabActive} title1="대시보드" title2="상세보기" onTabClick={handleTabClick} />
      {isTabActive ? (
        <>
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
          <DivisionRectangle />
          <RecommendCareRoutine />
          <Button name="두피 케어 루틴 추천 받기" onClick={() => handleButtonClick()} />
          <br />
          <br />
          <br />
        </>
      ) : (
        ''
      )}
    </div>
  );
};

const ValueCardBox = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 1.25rem;
`;
export default MyreportDashBoard;
