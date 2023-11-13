import React, { useEffect, useState } from 'react';
// import axios from 'axios';
import axiosInstance from 'api/axiosInterceptor';
import styled from 'styled-components';
import { ReactComponent as CloseIcon } from '../../assets/icons/close_FILL0_wght400_GRAD0_opsz24 1.svg';
import { ReactComponent as SelectedIcon } from '../../assets/icons/check_FILL0_wght400_GRAD0_opsz24 1.svg';
import { datelistType, diagnosisResultType } from 'types/DiagnosisResult';

interface Props {
  diagnosis_id: number;
  setIndex: (arg: number) => void;
  setIsModalOpen: (arg: boolean) => void;
  diagnosisResults: diagnosisResultType[];
  formatDate?: (arg: string) => string;
}

const DateNavigateModal = ({ diagnosis_id, setIndex, setIsModalOpen, diagnosisResults, formatDate }: Props) => {
  const [dateList, setDateList] = useState<datelistType[]>();
  // const findIndex = (diag_id: number) => {
  //   diagnosisResults.map((diagnosisResults, index) => {
  //     if (diagnosisResults.diagnosis_id === diag_id) return index;
  //   });
  // };

  // const handleDateBoxClick = (arg: number) => {
  //   setIsModalOpen(false);

  //   setIndex(findIndex(arg));
  // };

  const findIndex = (diag_id: number) => {
    let foundIndex = -1; // Default to -1 if not found
    diagnosisResults.map((result, index) => {
      if (result.diagnosis_id === diag_id) {
        foundIndex = index;
      }
    });
    return foundIndex;
  };

  const handleDateBoxClick = (arg: number) => {
    setIsModalOpen(false);

    const index = findIndex(arg);
    if (index !== -1) {
      setIndex(index);
    } else {
      console.log('Diagnosis ID not found');
    }
  };

  useEffect(() => {
    // axios
    //   .get('http://localhost:4000/dates')
    axiosInstance
      .get('/diagnoses/dates')
      .then((response) => {
        console.log('모든 날짜 조회 성공', response.data.data.diagnosis_id_list);
        setDateList(response.data.data.diagnosis_id_list);
      })
      .catch((error) => console.error('모든 날짜 조회 실패', error));
  }, []);
  dateList?.map((date) => {
    console.log('date:', date.created_date);
  });
  return (
    <div className="col-span-full">
      <ModalBackgroundBox>
        <ModalBox>
          <TitleBox>
            <div className="title">날짜 선택하기</div>
            <CloseIcon className="close_icon" onClick={() => setIsModalOpen(false)} />
          </TitleBox>
          <DateListBox>
            {/* <DateBox onClick={() => handleDateBoxClick()}>
              <div className="date">23.10.14 (금) 18:09</div>
              api에서 들어온 날짜들(=위에 div안에 들어가는거)랑,  date로 넘어온거랑 같으면 표시
              추가: 선택하면 date를 선택한 날짜와 같게 표시
              {date === '22.12.11' && <SelectedIcon className="selected_icon" />}
            </DateBox>
            <DateBox>
              <div className="date">23.10.14 (금) 18:09</div>
              {date === '23.12.29' && <SelectedIcon className="selected_icon" />}
            </DateBox>
         */}

            {dateList?.map((dateItem, index) => (
              <DateBox key={index} onClick={() => handleDateBoxClick(dateItem.diagnosis_id)}>
                <div className="date">{formatDate?.(dateItem.created_date)}</div>
                {diagnosis_id === dateItem.diagnosis_id && <SelectedIcon className="selected_icon" />}
              </DateBox>
            ))}
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

  height: 100vh;
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
  z-index: 999;
`;

export default DateNavigateModal;
