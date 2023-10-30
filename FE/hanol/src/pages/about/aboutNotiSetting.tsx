import React from 'react';
import NotiSettingComponent from 'components/About/NotiSettingComponent';

const aboutNotiSetting = () => {
  return (
    <div className="col-span-full">
      <NotiSettingComponent title="데일리 루틴 확인 알림" desc="저녁 9시 루틴을 수행했는지 확인해보세요." />
      <NotiSettingComponent title="데일리 루틴 개별 알림" desc="루틴 별로 설정한 시간에 알림을 드려요." />
    </div>
  );
};

export default aboutNotiSetting;
