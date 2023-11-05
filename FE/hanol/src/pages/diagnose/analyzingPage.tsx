import React from 'react';
import styled from 'styled-components';
import AnalyzingAnimation from 'components/Animation/AnalyzingAnimation';
import { useRecoilValue } from 'recoil';
import { ImageState } from 'recoil/atoms';
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { EventSourcePolyfill, NativeEventSource } from 'event-source-polyfill';
import { DeviceState } from 'recoil/atoms';
import axiosInstance from 'api/axiosInterceptor';
const AnalyzingPage = () => {
  const image = useRecoilValue(ImageState);
  const device = useRecoilValue(DeviceState);
  console.log(device);
  console.log(image);
  const navigate = useNavigate();
  const access_token = localStorage.getItem('access_token');
  const setupSSE = () => {
    const API_URL = process.env.REACT_APP_API_URL;
    const EventSource = EventSourcePolyfill || NativeEventSource;
    const eventSource = new EventSource(`${API_URL}/sse/`, {
      headers: {
        Authorization: `Bearer ${access_token}`,
      },
    });
    eventSource.onmessage = (event) => {
      const data = JSON.parse(event.data);
      if (data.url === 'error') {
        // navigate('/posterror');
        eventSource.close();
      }

      // 서버로부터 받은 데이터 처리
      // SSE 연결 닫기
      eventSource.close();
    };

    eventSource.onerror = (error) => {
      console.error('SSE 연결 중 오류 발생:', error);
      navigate(-1);
      eventSource.close();
    };

    return eventSource; // 정리 함수에서 사용하기 위해 이벤트 소스를 반환
  };

  useEffect(() => {
    const eventSource = setupSSE(); // 이벤트 소스를 생성하고 캡처
    const formData = new FormData();

    const value = [
      {
        divice_type: { device },
        scan_part: 0,
      },
    ];

    const blob = new Blob([JSON.stringify(value)], { type: 'application/json' });
    console.log(blob);
    formData.append('data', blob);
    const fetchData = async () => {
      try {
        const response = await axiosInstance.post(`/diagnoses`, {
          headers: {
            'Content-Type': 'multipart/form-data', // Content-Type을 반드시 이렇게 하여야 한다.
          },
          data: formData, // data 전송시에 반드시 생성되어 있는 formData 객체만 전송 하여야 한다.
        });
        console.log(response);
      } catch (error) {
        console.error('데이터 가져오기 오류:', error);
      }
    };

    fetchData();
    return () => {
      // 컴포넌트가 언마운트될 때 SSE 연결 닫기
      if (eventSource) {
        eventSource.close();
      }
    };
  }, []);

  return (
    <div className="col-span-full">
      <AnalyzingPageBox>
        <AnalyzingAnimation />
        <div className="ment">두피를 분석하고 있어요</div>
        {/* <div>{image}</div> */}
      </AnalyzingPageBox>
    </div>
  );
};

const AnalyzingPageBox = styled.div`
  display: flex;

  flex-direction: column;
  /* align-items: center; */
  justify-content: center;

  margin-top: 25vh;

  .ment {
    color: var(--Black, #252321);
    text-align: center;

    font-family: Noto Sans KR;
    font-size: 20px;
    font-weight: 700;
    line-height: 20px;

    margin-top: 0.625rem;
  }
`;
export default AnalyzingPage;
