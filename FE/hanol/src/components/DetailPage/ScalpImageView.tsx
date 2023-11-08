import React from 'react';
import styled from 'styled-components';
// import ScalpImg from '../../assets/images/scalp.jpg';

interface Props {
  sub_title?: number;
  scalp_img: string;
}

const ScalpImageView = ({ sub_title, scalp_img }: Props) => {
  const scalpe_sub_titles = (sub_title: number) => {
    switch (sub_title) {
      case 0:
        return '왼쪽 앞머리';
      case 1:
        return '오른쪽 앞머리';
      case 2:
        return '정수리';
      case 3:
        return '왼쪽 옆머리';
      case 4:
        return '오른쪽 옆머리';
      case 5:
        return '뒷머리';
      default:
        return '';
    }
  };

  return (
    <div className="col-span-full">
      <ScalpViewBox>
        <TitleBox>
          <div className="title">두피 이미지</div>
          <div className="sub_title">{scalpe_sub_titles(sub_title)}</div>
        </TitleBox>
        <ImgBox>
          <img src={scalp_img} alt={scalp_img} className="scalp_img" />
        </ImgBox>
      </ScalpViewBox>
    </div>
  );
};

const ImgBox = styled.div`
  width: 70%;
  object-fit: cover;
  margin-top: 1.5rem;
  margin-bottom: 2.876rem;
  align-self: center;

  .scalp_img {
    border-radius: 18px;
  }
`;
const TitleBox = styled.div`
  display: flex;
  height: 1.125rem;
  justify-content: flex-start;
  align-items: flex-end;
  flex-shrink: 0;

  .title {
    color: var(--Black, #252321);

    font-family: Noto Sans KR;
    font-size: 18px;
    font-weight: 700;
    line-height: 18px;
    letter-spacing: 0.2px;
  }

  .sub_title {
    color: var(--GrayForText, #888);
    font-family: Noto Sans KR;
    font-size: 12px;
    font-weight: 400;
    line-height: 12px;
    letter-spacing: 0.4px;
    margin-left: 0.625rem;
  }
`;
const ScalpViewBox = styled.div`
  display: flex;
  flex-direction: column;

  margin-top: 1.563rem;
`;

export default ScalpImageView;
