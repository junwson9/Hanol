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

  const base64ToBlob = (base64: string, type: string) => {
    const byteString = atob(base64.split(',')[1]);
    const arrayBuffer = new ArrayBuffer(byteString.length);
    const uint8Array = new Uint8Array(arrayBuffer);
    console.log(type);
    for (let i = 0; i < byteString.length; i++) {
      uint8Array[i] = byteString.charCodeAt(i);
    }
    return new Blob([arrayBuffer], { type });
  };

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
      console.log(data);

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
    const imageBlob = base64ToBlob(image, 'image/jpeg');
    const formData = new FormData();
    formData.append('file', imageBlob, 'image.jpg');
    const data = {
      device_type: 0,
      scan_part: 0,
    };
    formData.append('data', new Blob([JSON.stringify(data)], { type: 'application/json' }));
    const fetchData = async () => {
      try {
        const response = await axiosInstance.post(`/diagnoses`, formData, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
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

// eslint-disable-next-line
function base64ToArrayBuffer(base64: any) {
  const binaryString = window.atob(base64);
  const length = binaryString.length;
  const bytes = new Uint8Array(length);
  for (let i = 0; i < length; i++) {
    bytes[i] = binaryString.charCodeAt(i);
  }
  return bytes.buffer;
}
