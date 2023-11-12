import React from 'react';
import { useNavigate } from 'react-router-dom';
// import styled from 'styled-components';
import AlopeciaDiagnosis from 'components/diagnosisResultPage/AlopeciaDiagnosis';
import DiagnosisDetailResult from 'components/diagnosisResultPage/DiagnosisDetailResult';
import ScalpImageView from 'components/DetailPage/ScalpImageView';
import ScalpScaleView from 'components/DetailPage/ScalpScaleView';
import EmptyButton from 'components/button/EmptyButton';
import Button from 'components/button/Button';
import DivisionRectangle from 'components/common/DivisionRectangle';
import RecommendCareRoutine2 from 'components/diagnosisResultPage/RecommendCareRoutine2';
import TopBarDepth2 from 'components/common/TapBarDepth2';
import { useRecoilValue } from 'recoil';
import { diagnoseIdState, diagnoseState, PartState } from 'recoil/atoms';
import { ImageState } from 'recoil/atoms';
import { useState } from 'react';
import { useEffect } from 'react';
import ResultSender from 'components/diagnosisResultPage/ResultSender';

const DiagnosisDetail = () => {
  const navigate = useNavigate();
  const values = useRecoilValue(diagnoseState);
  const image = useRecoilValue(ImageState);
  const scanPart = useRecoilValue(PartState);
  const diagnoseId = useRecoilValue(diagnoseIdState);
  // viewBoolean 배열 초기화
  console.log('스캔부위', scanPart);
  const initialViewBoolean = new Array(6).fill(false);
  const [viewBoolean, setViewBoolean] = useState(initialViewBoolean);

  // values 배열의 각 항목을 검사하여 viewBoolean 업데이트
  useEffect(() => {
    const updatedViewBoolean = values.map((value) => value >= 2);
    if (!updatedViewBoolean.includes(true)) {
      updatedViewBoolean.push(true);
    }
    setViewBoolean(updatedViewBoolean);
  }, [values]);

  console.log('여기요', viewBoolean);

  console.log(values);
  console.log(image);
  const handleButtonClick = () => {
    navigate('/');
  };
  const handleToRoutine = () => {
    navigate('/routine');
  };
  return (
    <div className="col-span-full">
      <div className="grid grid-cols-6 gap-[10px] mx-[23px]">
        <div className="col-span-full">
          <TopBarDepth2 name={'진단결과'} propsIsBack={false} rightBtnType={2} onClick={handleButtonClick} />
          <AlopeciaDiagnosis valuenumber={values[5]} />
          <ScalpScaleView
            value1={values[0]}
            value2={values[1]}
            value3={values[2]}
            value4={values[3]}
            value5={values[4]}
            value6={values[5]}
          />
          <DiagnosisDetailResult viewBoolean={viewBoolean} />
          <ScalpImageView scalp_img={image} sub_title={scanPart} />
          <EmptyButton name="지난 내역 확인 하기" onClick={() => handleButtonClick()} />
          <div>
            <ResultSender diagnoseId={diagnoseId} />
          </div>
        </div>
      </div>

      <div>
        <DivisionRectangle />
        <div className="grid grid-cols-6 gap-[10px] mx-[23px]">
          <div className="col-span-full">
            <RecommendCareRoutine2 />
            <div className="mb-[2rem]">
              <Button name="두피 케어 루틴 추천 받기" onClick={() => handleToRoutine()} />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default DiagnosisDetail;
