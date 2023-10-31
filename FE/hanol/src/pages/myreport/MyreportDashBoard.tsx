import React from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import BannerButton from 'components/button/BannerButton';
import ValueCard from 'components/DashboardPage/ValueCard';
import ValueGraph from 'components/DashboardPage/ValueGraph';
import DivisionRectangle from 'components/common/DivisionRectangle';
import RecommendCareRoutine from 'components/DashboardPage/RecommendCareRoutine';
import Button from 'components/button/Button';

const MyreaportDashBoard = () => {
  const navigate = useNavigate();

  const handleButtonClick = () => {
    navigate('/test');
  };

  return (
    <div className="col-span-full">
      <BannerButton />

      <ValueCardBox>
        <ValueCard title="탈모" value={0} />
        <ValueCard title="각질" value={2} />
        <ValueCard title="피지" value={1} />
      </ValueCardBox>
      <ValueCardBox>
        <ValueCard title="홍반" value={3} />
        <ValueCard title="염증" value={2} />
        <ValueCard title="비듬" value={1} />
      </ValueCardBox>
      <ValueGraph title="탈모" />
      <DivisionRectangle />
      <RecommendCareRoutine />
      <Button name="두피 케어 루틴 추천 받기" onClick={() => handleButtonClick()} />
      <br />
      <br />
      <br />
    </div>
  );
};

const ValueCardBox = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 1.25rem;
`;
export default MyreaportDashBoard;
