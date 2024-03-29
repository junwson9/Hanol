import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axiosInstance from 'api/axiosInterceptor';
// import axios from 'axios';
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
import AlopeciaDiagnosis from 'components/diagnosisResultPage/AlopeciaDiagnosis';
import DiagnosisDetailResult from 'components/diagnosisResultPage/DiagnosisDetailResult';
// import { MemberRoleState } from 'recoil/atoms';
// import { useRecoilValue } from 'recoil';

// const initData: diagnosisResultType[] = [
//   {
//     diagnosis_id: 0,
//     member_id: 0,
//     value1: 0,
//     value2: 0,
//     value3: 0,
//     value4: 0,
//     value5: 0,
//     value6: 0,
//     image_url: '',
//     device_type: 0,
//     scan_part: 0,
//     created_date: '',
//   },
// ];

const Myreport = () => {
  const navigate = useNavigate();
  // const Role = useRecoilValue(MemberRoleState);
  //대시보드
  const [isTabActive, setTabActive] = useState<boolean>(true);
  const [diagnosisList, setDiagnosisList] = useState<diagnosisResultType[]>();
  const [maxDiagnosisCount] = useState(8); // 최대 저장할 진단 결과 개수
  const [diagnosisListDash, setDiagnosisListDash] = useState<diagnosisResultType[]>();
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
  const [isExplainModalOpen, setIsExplainModalOpen] = useState(false);
  const [index, setIndex] = useState<number>(0);

  // viewBoolean 배열 초기화
  const initialViewBoolean = new Array(6).fill(false);
  const [viewBoolean, setViewBoolean] = useState(initialViewBoolean);

  // values 배열의 각 항목을 검사하여 viewBoolean 업데이트
  useEffect(() => {
    const diagnosisItem = diagnosisList?.[index];
    if (diagnosisItem) {
      const updatedViewBoolean = [];

      // value1부터 value6까지 반복
      for (let i = 1; i <= 6; i++) {
        //eslint-disable-next-line
        updatedViewBoolean.push((diagnosisItem as any)[`value${i}`] >= 2);
      }

      if (!updatedViewBoolean.includes(true)) {
        updatedViewBoolean.push(true);
      }

      setViewBoolean(updatedViewBoolean);
    }
  }, [diagnosisList, index]);

  console.log('여기요', viewBoolean);

  // 날짜 포맷 변경
  const formatDate = (dateString: string) => {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = `0${date.getMonth() + 1}`.slice(-2);
    const day = `0${date.getDate()}`.slice(-2);
    const hours = `0${date.getHours()}`.slice(-2);
    const minutes = `0${date.getMinutes()}`.slice(-2);

    return `${year}-${month}-${day} ${hours}:${minutes}`;
  };

  const handleTabClick = () => {
    setTabActive((prevActive: boolean) => !prevActive);
  };

  const handleButtonClick = () => {
    navigate('/routine');
  };

  const handleBannerButtonClick = () => {
    navigate('/diagnose');
  };

  const handleValueCardClick = (arg: number) => {
    setgraphValue(arg);
  };
  const getValueTitle = (arg: number) => {
    switch (arg) {
      case 1:
        return '각질';
      case 2:
        return '피지';
      case 3:
        return '홍반';
      case 4:
        return '염증';
      case 5:
        return '비듬';
      case 6:
        return '탈모';
    }
  };
  useEffect(() => {
    axiosInstance
      .get('/diagnoses?')
      .then((response) => {
        console.log('진단 결과 리스트 조회 성공:', response);
        const fetchedDiagnosisList = response.data.data.diagnosis_info_list;

        if (!fetchedDiagnosisList || fetchedDiagnosisList.length === 0) {
          console.log('아무것도 없어용');
          navigate('/myreport-explain');
          console.log('이도오오옹');
        }
        const limitedDiagnosisList = fetchedDiagnosisList.slice(0, maxDiagnosisCount);
        setDiagnosisListDash(limitedDiagnosisList);
        setDiagnosisList(response.data.data.diagnosis_info_list);
        setValue1(response.data.data.diagnosis_info_list?.[0].value1);
        setValue2(response.data.data.diagnosis_info_list?.[0].value2);
        setValue3(response.data.data.diagnosis_info_list?.[0].value3);
        setValue4(response.data.data.diagnosis_info_list?.[0].value4);
        setValue5(response.data.data.diagnosis_info_list?.[0].value5);
        setValue6(response.data.data.diagnosis_info_list?.[0].value6);
      })
      .catch((error) => {
        console.error('진단 결과 리스트 조회 실패:', error);
      });
  }, []);

  useEffect(() => {
    setValue1(diagnosisList?.[index].value1);
    setValue2(diagnosisList?.[index].value2);
    setValue3(diagnosisList?.[index].value3);
    setValue4(diagnosisList?.[index].value4);
    setValue5(diagnosisList?.[index].value5);
    setValue6(diagnosisList?.[index].value6);
  }, [index]);

  useEffect(() => {
    setIndex(0);
  }, [isTabActive]);

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
          <DashBoardBox>
            <div className="grid grid-cols-6 gap-[10px] mx-[23px]">
              <div className="col-span-full">
                {diagnosisListDash && (
                  <>
                    <BannerButton name="내 두피 분석 하러가기" onClick={() => handleBannerButtonClick()} />
                    <ValueCardBox>
                      <ValueCard
                        title="탈모"
                        value={value6}
                        onClick={() => handleValueCardClick(6)}
                        isSelected={graphValue === 6}
                      />
                      <ValueCard
                        title="각질"
                        value={value1}
                        onClick={() => handleValueCardClick(1)}
                        isSelected={graphValue === 1}
                      />
                      <ValueCard
                        title="피지"
                        value={value2}
                        onClick={() => handleValueCardClick(2)}
                        isSelected={graphValue === 2}
                      />
                    </ValueCardBox>
                    <ValueCardBox>
                      <ValueCard
                        title="홍반"
                        value={value3}
                        onClick={() => handleValueCardClick(3)}
                        isSelected={graphValue === 3}
                      />
                      <ValueCard
                        title="염증"
                        value={value4}
                        onClick={() => handleValueCardClick(4)}
                        isSelected={graphValue === 4}
                      />
                      <ValueCard
                        title="비듬"
                        value={value5}
                        onClick={() => handleValueCardClick(5)}
                        isSelected={graphValue === 5}
                      />
                    </ValueCardBox>
                    <ValueGraph
                      title={getValueTitle(graphValue)}
                      dataList={diagnosisListDash}
                      // dataList={reversedDiagnosisList}
                      graphValue={graphValue}
                      setIndex={setIndex}
                    />
                  </>
                )}
              </div>
            </div>
            <DivisionRectangle />
            <div className="grid grid-cols-6 gap-[10px] mx-[23px]">
              <div className="col-span-full mb-[5rem]">
                <RecommendCareRoutine />
                <Button name="두피 케어 루틴 추천 받기" onClick={() => handleButtonClick()} />
                <br />
                <br />
                <br />
              </div>
            </div>
          </DashBoardBox>
        </>
      ) : (
        <div className="grid grid-cols-6 gap-[10px] mx-[23px]">
          <div className="col-span-full">
            <OverwrapContainer1>
              {diagnosisList && index < diagnosisList.length && (
                <>
                  <DateNavigateButton
                    index={index}
                    setIndex={setIndex}
                    date={formatDate(diagnosisList[index].created_date)}
                    onClick={() => setIsModalOpen(true)}
                    length={diagnosisList.length}
                  />
                  <AlopeciaDiagnosis valuenumber={diagnosisList[index].value6} />

                  <ScalpScaleView
                    value1={diagnosisList[index].value1}
                    value2={diagnosisList[index].value2}
                    value3={diagnosisList[index].value3}
                    value4={diagnosisList[index].value4}
                    value5={diagnosisList[index].value5}
                    value6={diagnosisList[index].value6}
                    isExplainModalOpen={isExplainModalOpen}
                    setIsExplainModalOpen={setIsExplainModalOpen}
                  />
                  <DiagnosisDetailResult viewBoolean={viewBoolean} />

                  <ScalpImageView
                    sub_title={diagnosisList[index].scan_part}
                    // scalp_img="../.../src/assets/images/scalp.jpg"
                    scalp_img={diagnosisList[index].image_url}
                  />
                  {isModalOpen && (
                    <OverwrapContainer2>
                      <DateNavigateModal
                        diagnosis_id={diagnosisList[index].diagnosis_id}
                        setIndex={setIndex}
                        setIsModalOpen={setIsModalOpen}
                        diagnosisResults={diagnosisList}
                        formatDate={formatDate}
                      />
                    </OverwrapContainer2>
                  )}
                </>
              )}
            </OverwrapContainer1>
            <br />
            <br />
            <br />
            <br />
            <br />
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
  background-color: #f7f7f7;
  position: absolute;
  top: 0;
  left: 0;
  z-index: 20;
  width: 100%;
`;

const OverwrapContainer1 = styled.div`
  /* position: relative; */
`;

const DashBoardBox = styled.div``;
const MyreportContainer = styled.div`
  position: relative;
`;
export default Myreport;
