import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import { ReactComponent as HelpIcon } from '../../assets/icons/help_FILL0_wght400_GRAD0_opsz24 1.svg';
import { ResponsiveRadar } from '@nivo/radar';

interface Props {
  value1: number;
  value2: number;
  value3: number;
  value4: number;
  value5: number;
  value6: number;
}

const ScalpScaleView = ({ value1, value2, value3, value4, value5, value6 }: Props) => {
  const defaultData = [
    {
      category: '탈모',

      score: 0,
    },
    {
      category: '각질',

      score: 0,
    },
    {
      category: '피지',

      score: 0,
    },
    {
      category: '홍반',

      score: 0,
    },
    {
      category: '비듬',

      score: 0,
    },
    {
      category: '염증',

      score: 0,
    },
  ];
  const wantedData = [
    {
      category: '탈모',

      score: value6,
    },
    {
      category: '각질',

      score: value1,
    },
    {
      category: '피지',

      score: value2,
    },
    {
      category: '홍반',

      score: value3,
    },
    {
      category: '비듬',

      score: value4,
    },
    {
      category: '염증',

      score: value5,
    },
  ];
  const [graphicData, setGraphicData] = useState(defaultData);
  useEffect(() => {
    setGraphicData(wantedData);
  }, [value1, value2, value3, value4, value5, value6]);

  return (
    <div className="col-span-full">
      <ScalpImageViewBox>
        <TitleBox>
          <div className="title">두피 건강점수</div>
          <HelpIcon className="help_icon" />
        </TitleBox>
        <GraphBox>
          <ResponsiveRadar
            data={graphicData}
            keys={['score']}
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
            animate={true}
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
  margin-top: 1.563rem;
`;

export default ScalpScaleView;
