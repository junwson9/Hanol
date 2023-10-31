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

const DiagnosisDetail = () => {
  const navigate = useNavigate();

  const handleButtonClick = () => {
    navigate('/test');
  };
  return (
    <div className="col-span-full">
      <AlopeciaDiagnosis valuenumber={1} />
      <ScalpScaleView />
      <DiagnosisDetailResult />
      <ScalpImageView sub_title="전두부" scalp_img="adfd" />
      <EmptyButton name="지난 내역 확인 하기" onClick={() => handleButtonClick()} />
      <DivisionRectangle />
      <RecommendCareRoutine2 />
      <Button name="두피 케어 루틴 추천 받기" onClick={() => handleButtonClick()} />
    </div>
  );
};

export default DiagnosisDetail;
