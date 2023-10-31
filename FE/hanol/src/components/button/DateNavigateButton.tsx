import React from 'react';
import styled from 'styled-components';
import { ReactComponent as LeftNavigateIcon } from '../../assets/icons/chevron_right_FILL0_wght400_GRAD0_opsz24 2.svg';
import { ReactComponent as RightNavigateIcon } from '../../assets/icons/chevron_right_FILL0_wght400_GRAD0_opsz24 1.svg';

interface Props {
  date: string;
  onClick: (arg: boolean) => void;
}

const DateNavigateButton = ({ date, onClick }: Props) => {
  return (
    <div className="col-span-full">
      <ButtonBox>
        <DateNavigateButtonBox>
          <LeftNavigateIcon className="left_icon" />
          <div className="date_box" onClick={() => onClick(true)}>
            {date}
          </div>
          <RightNavigateIcon className="right_icon" />
        </DateNavigateButtonBox>
      </ButtonBox>
    </div>
  );
};

const ButtonBox = styled.div`
  display: flex;
  justify-content: center;

  margin: 1.563rem 0;
`;

const DateNavigateButtonBox = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  /* width: 14.125rem; */
  width: 19.625rem;
  height: 2.188rem;
  border-radius: 18px;
  border: 0.2px solid var(--GrayForTab, #bcbcbc);
  opacity: 0.76;
  background: var(--white, #fffeff);

  .left_icon {
    margin-left: 0.625rem;
    cursor: pointer;
  }
  .right_icon {
    margin-right: 0.625rem;
    cursor: pointer;
  }
  .date_box {
    /* display: flex;
    flex-direction: column;
    justify-content: center; */

    width: 12.813rem;
    height: 0.875rem;
    flex-shrink: 0;

    color: var(--Black, #252321);
    text-align: center;

    font-family: Noto Sans KR;
    font-size: 14px;
    font-weight: 400;
    line-height: 14px;
    letter-spacing: 0.4px;
    cursor: pointer;
  }
`;

export default DateNavigateButton;
