import React from 'react';
import styled from 'styled-components';
import { ReactComponent as CloseIcon } from '../../assets/icons/close_FILL0_wght400_GRAD0_opsz24 1.svg';
import { ReactComponent as SelectedIcon } from '../../assets/icons/check_FILL0_wght400_GRAD0_opsz24 1.svg';

const DateNavigateButton = () => {
  return (
    <div className="col-span-full">
      <ModalBackgroundBox>
        <ModalBox>
          <TitleBox>
            <div className="title">날짜 선택하기</div>
            <CloseIcon className="close_icon" />
          </TitleBox>
          <DateListBox>
            <DateBox>
              <div className="date">23.10.14 (금) 18:09</div>
              <SelectedIcon className="selected_icon" />
            </DateBox>
            <DateBox>
              <div className="date">23.10.14 (금) 18:09</div>
              <SelectedIcon className="selected_icon" />
            </DateBox>
            <DateBox>
              <div className="date">23.10.14 (금) 18:09</div>
              <SelectedIcon className="selected_icon" />
            </DateBox>
            <DateBox>
              <div className="date">23.10.14 (금) 18:09</div>
              <SelectedIcon className="selected_icon" />
            </DateBox>
            <DateBox>
              <div className="date">23.10.14 (금) 18:09</div>
              <SelectedIcon className="selected_icon" />
            </DateBox>
            <DateBox>
              <div className="date">23.10.14 (금) 18:09</div>
              <SelectedIcon className="selected_icon" />
            </DateBox>
            <DateBox>
              <div className="date">23.10.14 (금) 18:09</div>
              <SelectedIcon className="selected_icon" />
            </DateBox>
            <DateBox>
              <div className="date">23.10.14 (금) 18:09</div>
              <SelectedIcon className="selected_icon" />
            </DateBox>
            <DateBox>
              <div className="date">23.10.14 (금) 18:09</div>
              <SelectedIcon className="selected_icon" />
            </DateBox>
            <DateBox>
              <div className="date">23.10.14 (금) 18:09</div>
              <SelectedIcon className="selected_icon" />
            </DateBox>
            <DateBox>
              <div className="date">23.10.14 (금) 18:09</div>
              <SelectedIcon className="selected_icon" />
            </DateBox>
            <DateBox>
              <div className="date">23.10.14 (금) 18:09</div>
              <SelectedIcon className="selected_icon" />
            </DateBox>
            <DateBox>
              <div className="date">23.10.14 (금) 18:09</div>
              <SelectedIcon className="selected_icon" />
            </DateBox>
            <DateBox>
              <div className="date">23.10.14 (금) 18:09</div>
              <SelectedIcon className="selected_icon" />
            </DateBox>
            <DateBox>
              <div className="date">23.10.14 (금) 18:09</div>
              <SelectedIcon className="selected_icon" />
            </DateBox>
            <DateBox>
              <div className="date">23.10.14 (금) 18:09</div>
              <SelectedIcon className="selected_icon" />
            </DateBox>
          </DateListBox>
        </ModalBox>
      </ModalBackgroundBox>
    </div>
  );
};

const DateBox = styled.div`
  width: 100%;
  margin-bottom: 1.563rem;

  display: flex;
  justify-content: space-between;
  cursor: pointer;

  .date {
    margin-left: 1.438rem;
  }
  .selected_icon {
    margin-right: 2.188rem;
  }
`;

const DateListBox = styled.div`
  width: 100%;

  margin-top: 1.875rem;
  align-self: flex-start;
  /* margin-left: 1.438rem; */

  height: 55vh;
  overflow-y: auto;
`;
const TitleBox = styled.div`
  width: 100%;

  display: flex;
  justify-content: space-between;
  margin-top: 1.625rem;

  .title {
    color: var(--Black, #252321);

    font-family: Noto Sans KR;
    font-size: 18px;
    font-weight: 700;
    line-height: 18px; /* 25.2px */
    letter-spacing: 0.2px;

    margin-left: 1.438rem;
  }

  .close_icon {
    margin-right: 1.438rem;
    cursor: pointer;
  }
`;
const ModalBox = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-top: 35vh;
  /* z-index: 999; */
  border-radius: 15px 15px 0px 0px;
  background: var(--white, #fffeff);

  /* overflow-y: auto; */
  /* max-height: 70vh; */

  /* overflow-y: initial !important; */
`;

const ModalBackgroundBox = styled.div`
  /* position: absolute; */
  /* top: 0; */
  /* left: 0; */
  /* z-index: -1; */
  display: flex;
  width: 100%;
  height: 100%;

  background: rgba(72, 72, 72, 0.67);
`;

export default DateNavigateButton;
