import styled from 'styled-components';
import EmptyButton from 'components/button/EmptyButton';
import { useState } from 'react';
import axiosInstance from 'api/axiosInterceptor';

type ResultSenderProps = {
  diagnoseId: number;
};

function ResultSender({ diagnoseId }: ResultSenderProps) {
  const [email, setEmail] = useState<string>('');

  const sendEmail = async () => {
    try {
      const response = await axiosInstance.post(`/diagnoses/${diagnoseId}/send`, { email: email });
      console.log(response);
      // eslint-disable-next-line
    } catch (error: any) {
      console.error('데이터 가져오기 오류:', error);
      if (error.response && error.response.status === 404) {
        // Handle 404 error - Invalid email
        console.log('유효하지 않은 이메일입니다.');
      } else {
        // Handle other errors
        console.log('일시적인 오류가 발생했습니다. 나중에 다시 시도해주세요.');
      }
    }
  };
  return (
    <div className="relative col-span-full mt-[2rem]">
      <ResultSenderBox>
        <div className="send_title">진단 결과 전송하기</div>
        <input
          type="email"
          className="email_box"
          placeholder="전송 받을 회원 이메일을 입력해주세요."
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <div className="btn_div">
          <EmptyButton name="전송하기" onClick={sendEmail} />
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
    outline: none;
  }

  .btn_div {
    margin-top: 0.938rem;
  }
`;
export default ResultSender;
