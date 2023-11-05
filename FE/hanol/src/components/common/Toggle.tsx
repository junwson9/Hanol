import React from 'react';
import axios from 'axios';
import styled from 'styled-components';

interface Props {
  toggleState: boolean;
  noti_type?: string;
}
const Toggle = ({ toggleState, noti_type }: Props) => {
  const toggleHandler = () => {
    const data = {
      notification_type: noti_type,
      is_active: !toggleState,
    };
    // axiosInstance
    axios
      .patch('http://localhost:4000/notifications', data)
      .then((response) => {
        console.log('알림 설정 변경 성공:', response);
      })
      .catch((error) => {
        console.error('알림 설정 변경 실패:', error);
      });
  };

  return (
    <div className="col-span-full">
      <ToggleBox>
        <ToggleBackground
          onClick={() => toggleHandler()}
          className={`${toggleState ? 'on_background' : 'off_background'}`}
        >
          <ToggleSwitch className={`${toggleState ? 'on_switch' : 'off_switch'}`} />
        </ToggleBackground>
      </ToggleBox>
    </div>
  );
};

const ToggleBox = styled.div`
  /* display: flex; */

  .on_background {
    background-color: #3fcc8a;
  }

  .off_background {
    background-color: #bcbcbc;
  }

  .on_switch {
    left: 1.563rem;
  }

  .off_switch {
  }
`;
const ToggleBackground = styled.div`
  position: relative;
  width: 3.125rem;
  height: 1.563rem;
  border-radius: 50px;

  cursor: pointer;

  transition: all 0.2s ease-in-out;
`;
const ToggleSwitch = styled.div`
  width: 1rem;
  height: 1rem;
  background-color: #fffeff;
  border-radius: 50px;

  margin: 0 0.313rem;
  position: absolute;
  top: 0.281rem;
`;
export default Toggle;
