import { useRef, useEffect, useState } from 'react';
import TopBarDepth2 from 'components/common/TapBarDepth2';
import { ReactComponent as Camera } from 'assets/icons/diagnoseCamera.svg';
import { useNavigate } from 'react-router-dom';

function IoTstreaming() {
  const navigate = useNavigate();
  const videoRef = useRef<HTMLImageElement | null>(null);
  const [showCaptureButtons, setShowCaptureButtons] = useState(true);
  const [capturedImage, setCapturedImage] = useState<string | null>(null);
  let socket: WebSocket | null = null; // WebSocket 객체를 직접 선언

  const initializeWebSocket = () => {
    // WebSocket 연결을 초기화하는 함수
    socket = new WebSocket('ws://localhost:8888/ws');

    socket.onmessage = (event) => {
      if (videoRef.current) {
        videoRef.current.src = 'data:image/jpeg;base64,' + event.data;
      }
    };

    socket.onclose = (event) => {
      console.error('Socket Closed:', event);
    };

    socket.onerror = (event) => {
      console.error('Socket Error:', event);
    };
  };

  const closeWebSocket = () => {
    if (socket) {
      socket.close();
    }
  };
  useEffect(() => {
    // 컴포넌트가 마운트될 때 WebSocket 연결 열기
    initializeWebSocket();

    return () => {
      closeWebSocket(); // 컴포넌트가 언마운트될 때 WebSocket 연결 닫기
    };
  }, []);

  const captureImage = () => {
    if (videoRef.current) {
      const canvas = document.createElement('canvas');
      const context = canvas.getContext('2d');
      canvas.width = videoRef.current.width;
      canvas.height = videoRef.current.height;
      context?.drawImage(videoRef.current, 0, 0, canvas.width, canvas.height);
      const imageURI = canvas.toDataURL('image/jpeg');
      setCapturedImage(imageURI);
      setShowCaptureButtons(false);
    }
  };

  const resetCapture = () => {
    setCapturedImage(null);
    setShowCaptureButtons(true);
  };

  const diagnoseImage = () => {
    if (capturedImage) {
      // 이미지를 진단하는 로직을 추가하거나 서버에 업로드할 수 있습니다.
      console.log('이미지를 진단합니다.');
    }
  };

  return (
    <div className="col-span-full h-screen relative">
      <TopBarDepth2
        name=""
        onClick={() => {
          navigate('/diagnose');
        }}
        propsIsBack={true}
      />
      <p className="text-lg text-center font-bold mt-20">
        원하시는 부위에 카메라를 대고
        <br />
        사진을 찍어주세요!
      </p>
      {capturedImage ? (
        <img src={capturedImage} width="640" height="480" className="rounded-xl mt-20" />
      ) : (
        <img id="video-stream" width="640" height="480" ref={videoRef} className="rounded-xl mt-20" />
      )}
      {showCaptureButtons ? (
        <div className="absolute w-[100%] bottom-5 text-center">
          <button onClick={captureImage}>
            <Camera className="w-16 h-16" />
          </button>
        </div>
      ) : (
        <div className="absolute w-[100%] bottom-5 text-center">
          <div className="flex justify-center gap-4">
            <button onClick={resetCapture} className="bg-Main text-white rounded-xl justify-center items-center">
              <p className="px-5 py-3">다시 찍기</p>
            </button>
            <button onClick={diagnoseImage} className="bg-Main text-white rounded-xl justify-center items-center">
              <p className="px-5 py-3">진단 받기</p>
            </button>
          </div>
        </div>
      )}
    </div>
  );
}

export default IoTstreaming;
