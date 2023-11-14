import React, { useEffect } from 'react';
import styled from 'styled-components';

type SnackbarProps = {
  message: string;
  onClose: () => void;
};

const Snackbar: React.FC<SnackbarProps> = ({ message, onClose }) => {
  useEffect(() => {
    const timer = setTimeout(() => {
      onClose(); // 일정 시간 후에 스낵바를 자동으로 닫기 위한 타이머 설정
    }, 2000);

    return () => clearTimeout(timer); // 컴포넌트가 언마운트되면 타이머 해제
  }, [onClose]);

  return (
    <SnackbarContainer>
      <SnackbarMessage>{message}</SnackbarMessage>
    </SnackbarContainer>
  );
};

const SnackbarContainer = styled.div`
  position: fixed;
  bottom: 50%;
  left: 50%;
  transform: translateX(-50%); /* 가로 중앙 정렬을 위해 translateX(-50%) 설정 */
  display: flex;
  align-items: center;
  padding: 1rem 1rem;
  background-color: #888888;
  border-radius: 50px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  max-width: calc(100% - 46px); /* 양쪽 마진을 제외한 최대 너비로 설정 */
  width: min(
    404px,
    calc(100% - 46px)
  ); /* 최대 너비를 450px로 제한하고, 화면이 더 작을 경우에는 해당 크기에 맞춰 줄어들도록 설정 */
  color: #fff;
`;

const SnackbarMessage = styled.div`
  flex: 1;
`;

export default Snackbar;
