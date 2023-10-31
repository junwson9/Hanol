import React, { useState } from 'react';
import styled from 'styled-components';
import TopTab from 'components/common/TopTab';
import DateNavigateButton from 'components/button/DateNavigateButton';
import ScalpScaleView from 'components/DetailPage/ScalpScaleView';
import ScalpImageView from 'components/DetailPage/ScalpImageView';
import DateNavigateModal from 'components/DetailPage/DateNavigateModal';

const MyreportDetailPage = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedDate, setSelectedDate] = useState('23.12.29');

  return (
    <div className="col-span-full">
      <OverwrapContainer1>
        <TopTab Indicator="two" title1="대시보드" title2="진단 결과" link1="dashboard" link2="mydetail" />
        <DateNavigateButton date={selectedDate} onClick={() => setIsModalOpen(true)} />
        <ScalpScaleView />
        <ScalpImageView sub_title="측두부" scalp_img="../.../src/assets/images/scalp.jpg" />
        {isModalOpen && (
          <OverwrapContainer2>
            <DateNavigateModal date={selectedDate} setSelectedDate={setSelectedDate} setIsModalOpen={setIsModalOpen} />
          </OverwrapContainer2>
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
