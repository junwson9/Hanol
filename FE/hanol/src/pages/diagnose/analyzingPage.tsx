import React from 'react';
import styled from 'styled-components';
import AnalyzingAnimation from 'components/Animation/AnalyzingAnimation';
import { useRecoilValue, useRecoilState } from 'recoil';
import { ImageState, diagnoseState, PartState, diagnoseIdState } from 'recoil/atoms';
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { EventSourcePolyfill, NativeEventSource } from 'event-source-polyfill';
import { DeviceState } from 'recoil/atoms';
import axiosInstance from 'api/axiosInterceptor';

const AnalyzingPage = () => {
  const scan_part = useRecoilValue(PartState);
  const image = useRecoilValue(ImageState);
  const device_type = useRecoilValue(DeviceState);
  const [, setImageURL] = useRecoilState(ImageState);
  const [, setValues] = useRecoilState(diagnoseState);
  const [, setPart] = useRecoilState(PartState);
  const [, setDiagnoseId] = useRecoilState(diagnoseIdState);
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
      console.log(data.value1);
      setImageURL(data.image_url);
      setPart(data.scan_part);
      setDiagnoseId(data.diagnosis_id);
      setValues((prevSelect) => {
        const updatedSelect = [...prevSelect];
        updatedSelect[0] = data.value1 as number;
        updatedSelect[1] = data.value2 as number;
        updatedSelect[2] = data.value3 as number;
        updatedSelect[3] = data.value4 as number;
        updatedSelect[4] = data.value5 as number;
        updatedSelect[5] = data.value6 as number;
        return updatedSelect;
      });
      eventSource.close();
      if (data.value1 === -1) {
        navigate('/scalp-exception');
      } else {
        navigate('/diagnosis');
      }
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
      device_type,
      scan_part,
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
