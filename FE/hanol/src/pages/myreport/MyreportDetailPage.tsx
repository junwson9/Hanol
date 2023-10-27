import React from 'react';
import TopTab from 'components/common/TopTab';
import DateNavigateButton from 'components/button/DateNavigateButton';
import ScalpScaleView from 'components/DetailPage/ScalpScaleView';
import ScalpImageView from 'components/DetailPage/ScalpImageView';

const MyreportDetailPage = () => {
  return (
    <div className="col-span-full">
      <TopTab Indicator="two" title1="대시보드" title2="진단 결과" />
      <DateNavigateButton date="23.12.29" />
      <ScalpScaleView />
      <ScalpImageView sub_title="측두부" scalp_img="dklafj" />
    </div>
  );
};

export default MyreportDetailPage;
