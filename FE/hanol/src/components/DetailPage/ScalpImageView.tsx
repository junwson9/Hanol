import React from 'react';
import styled from 'styled-components';
import { ReactComponent as HelpIcon } from '../../assets/icons/help_FILL0_wght400_GRAD0_opsz24 1.svg';
import { ResponsiveRadar } from '@nivo/radar';

const ScalpImageView = () => {
  const data = [
    {
      category: '탈모',

      점수: 1,
    },
    {
      category: '각질',

      점수: 2,
    },
    {
      category: '피지',

      점수: 3,
    },
    {
      category: '홍반',

      점수: 4,
    },
    {
      category: '비듬',

      점수: 2,
    },
    {
      category: '염증',

      점수: 1,
    },
  ];

  return (
    <div className="col-span-full">
      <ScalpImageViewBox>
        <TitleBox>
          <div className="title">두피 건강점수</div>
          <HelpIcon className="help_icon" />
        </TitleBox>
        <GraphBox>
          <ResponsiveRadar
            data={data}
            keys={['점수']}
            indexBy="category"
            margin={{ top: 30, right: 30, bottom: 30, left: 30 }}
            borderColor={{ from: 'color', modifiers: [] }}
            gridLevels={4}
            gridShape="linear"
            gridLabelOffset={18}
            dotSize={10}
            dotColor={{ theme: 'background' }}
            dotBorderWidth={2}
            dotBorderColor={{ from: 'color', modifiers: [] }}
            dotLabel="value"
            dotLabelYOffset={-14}
            colors={{ scheme: 'pastel1' }}
            // motionConfig="wobbly"
          />
        </GraphBox>
      </ScalpImageViewBox>
    </div>
  );
};

const GraphBox = styled.div`
  width: 14.125rem;
  height: 14.125rem;
  object-fit: cover;
  margin-top: 1.5rem;
  align-self: center;
  /* border-radius: 18px; */

  .scalp_img {
    border-radius: 18px;
  }
`;
const TitleBox = styled.div`
  display: flex;
  height: 1.125rem;
  justify-content: flex-start;
  /* align-items: center; */
  flex-shrink: 0;

  .title {
    color: var(--Black, #252321);

    font-family: Noto Sans KR;
    font-size: 18px;
    font-weight: 700;
    line-height: 18px;
    letter-spacing: 0.2px;
  }

  .help_icon {
    margin-left: 0.3125rem;
    align-self: flex-start;
    // 추후 수정
    margin-top: 1px;
    cursor: pointer;
  }
`;
const ScalpImageViewBox = styled.div`
  display: flex;
  flex-direction: column;
`;

export default ScalpImageView;
