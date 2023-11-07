import styled from 'styled-components';
import EmptyButton from 'components/button/EmptyButton';

function SocketLoading() {
  return (
    <div className="relative col-span-full h-screen">
      <ResultSenderBox>
        <div className="send_title">진단 결과 전송하기</div>
        <input type="email" className="email_box" placeholder="전송 받을 회원 이메일을 입력해주세요." />
        <div className="btn_div">
          <EmptyButton
            name="전송하기"
            onClick={() => {
              console.log(1);
            }}
          />
        </div>
      </ResultSenderBox>
    </div>
  );
}

const ResultSenderBox = styled.div`
  display: flex;
  flex-direction: column;

  .send_title {
    color: var(--Black, #252321);
    font-size: 18px;
    font-weight: 700;
    line-height: 140%;
    letter-spacing: 0.2px;
    text-align: start;
  }

  .email_box {
    font-size: 16px;
    font-style: normal;
    font-weight: 400;
    line-height: 22.4px;
    text-align: center;
    margin-top: 0.375rem;
    padding: 1rem;
    border-bottom: 1px solid var(--gray4, #ddd);
  }

  .btn_div {
    margin-top: 0.938rem;
  }
`;
export default SocketLoading;
