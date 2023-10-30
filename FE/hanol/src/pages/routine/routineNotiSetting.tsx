import React from 'react';
import styled from 'styled-components';
import RoutineName from 'components/Daily/RoutineName';
import PushSetting from 'components/Daily/PushSetting';
import Caution from 'components/Daily/Caution';

const routineNotiSetting = () => {
  return (
    <div className="col-span-full">
      <PageBox>
        <RoutineName />
        <PushSetting />
        <Caution />
      </PageBox>
    </div>
  );
};

const PageBox = styled.div``;

export default routineNotiSetting;
