import React, { useState, useEffect } from 'react';
// import axiosInstance from 'api/axiosInterceptor';
import axios from 'axios';
import styled from 'styled-components';
import TopTab from 'components/common/TopTab';
import DateNavigateButton from 'components/button/DateNavigateButton';
import ScalpScaleView from 'components/DetailPage/ScalpScaleView';
import ScalpImageView from 'components/DetailPage/ScalpImageView';
import DateNavigateModal from 'components/DetailPage/DateNavigateModal';
import { diagnosisResultType } from 'types/DiagnosisResult';

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
const MyreportDetailPage = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [diagnosisResults, setDiagnosisResults] = useState<diagnosisResultType[]>(initData);
  const [index, setIndex] = useState<number>(0);

  useEffect(() => {
    axios
      // axiosInstance
      // .get('/diagnoses?limit=20')
      .get('http://localhost:4000/diagnoses')
      .then((response) => {
        // setSelectedDiagnosis(response.data);
        setDiagnosisResults(response.data.diagnosis_info_list);
        console.log('진단결과 상세조회 성공', response.data.diagnosis_info_list);
      })
      .catch((error) => console.error('진단결과 상세조회 실패', error));
  }, []);
  return (
    <div className="col-span-full">
      <OverwrapContainer1>
        <TopTab Indicator="two" title1="대시보드" title2="진단 결과" link1="dashboard" link2="mydetail" />
        {diagnosisResults.length > 0 && index < diagnosisResults.length && (
          <>
            <DateNavigateButton
              index={index}
              setIndex={setIndex}
              date={diagnosisResults[index].created_date}
              onClick={() => setIsModalOpen(true)}
              length={diagnosisResults.length}
            />
            <ScalpScaleView
              value1={diagnosisResults[index].value1}
              value2={diagnosisResults[index].value2}
              value3={diagnosisResults[index].value3}
              value4={diagnosisResults[index].value4}
              value5={diagnosisResults[index].value5}
              value6={diagnosisResults[index].value6}
            />
            <ScalpImageView
              sub_title={diagnosisResults[index].scan_part}
              // scalp_img="../.../src/assets/images/scalp.jpg"
              scalp_img={diagnosisResults[index].image_url}
            />
            {isModalOpen && (
              <OverwrapContainer2>
                <DateNavigateModal
                  date={diagnosisResults[index].created_date}
                  setIndex={setIndex}
                  setIsModalOpen={setIsModalOpen}
                  diagnosisResults={diagnosisResults}
                />
              </OverwrapContainer2>
            )}
          </>
        )}
      </OverwrapContainer1>
    </div>
  );
};

const OverwrapContainer2 = styled.div`
  position: absolute;
  top: 0;
  left: 0;

  width: 100%;
`;
const OverwrapContainer1 = styled.div`
  position: relative;
`;
export default MyreportDetailPage;
