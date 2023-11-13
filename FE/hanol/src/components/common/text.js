import styled from 'styled-components';

const testA2HS = () => {
  return (
    <SnackbarContainer>
      <SnackbarMessage>앱 다운로드 하기</SnackbarMessage>
    </SnackbarContainer>
  );
};

const SnackbarContainer = styled.div`
  position: fixed;
  bottom: 6rem;
  left: 50%;
  transform: translateX(-50%); /* 가로 중앙 정렬을 위해 translateX(-50%) 설정 */
  display: flex;
  align-items: center;
  padding: 1rem 1rem;
  background-color: #fff;
  color: #fff;
  border-radius: 12px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  max-width: calc(100% - 46px); /* 양쪽 마진을 제외한 최대 너비로 설정 */
  width: min(
    404px,
    calc(100% - 46px)
  ); /* 최대 너비를 450px로 제한하고, 화면이 더 작을 경우에는 해당 크기에 맞춰 줄어들도록 설정 */
  color: #3fcc8a;
  border: 1px solid #3fcc8a;
  z-index: 11111;
`;

const SnackbarMessage = styled.div`
  flex: 1;
`;

export default testA2HS;
