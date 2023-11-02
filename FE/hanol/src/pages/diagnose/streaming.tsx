import { useRef, useEffect, useState } from 'react';
import { ReactComponent as Camera } from 'assets/icons/diagnoseCamera.svg';
import TopBarDepth2 from 'components/common/TapBarDepth2';
import { useNavigate } from 'react-router-dom';
import { useRecoilValue } from 'recoil';
import { DeviceState } from 'recoil/atoms';
import { PartState } from 'recoil/atoms';
const CONSTRAINTS = { video: true };

function Streaming() {
  const test1 = useRecoilValue(DeviceState);
  const test2 = useRecoilValue(PartState);
  const navigate = useNavigate();
  const videoRef = useRef<HTMLVideoElement>(null);
  const [capturedImage, setCapturedImage] = useState<string | null>(null);
  const [showCaptureButtons, setShowCaptureButtons] = useState(true);

  const captureVideoFrame = () => {
    if (videoRef.current) {
      const canvas = document.createElement('canvas');
      const context = canvas.getContext('2d');
      canvas.width = videoRef.current.videoWidth;
      canvas.height = videoRef.current.videoHeight;
      context?.drawImage(videoRef.current, 0, 0, canvas.width, canvas.height);
      const imageURI = canvas.toDataURL('image/jpeg');
      setCapturedImage(imageURI);
      setShowCaptureButtons(false);
    }
  };

  const resetCapture = () => {
    setCapturedImage(null);
    setShowCaptureButtons(true);

    // 기존 스트림을 해제
    if (videoRef.current && videoRef.current.srcObject) {
      const tracks: MediaStreamTrack[] = (videoRef.current.srcObject as MediaStream).getTracks();
      tracks.forEach((track: MediaStreamTrack) => track.stop());
    }

    // 다시 미디어 스트림을 가져와서 재생
    navigator.mediaDevices
      .getUserMedia(CONSTRAINTS)
      .then((stream) => {
        if (videoRef.current) {
          videoRef.current.srcObject = stream;
          videoRef.current.play();
        }
      })
      .catch((error) => {
        console.error('미디어 스트림을 다시 가져오는 동안 오류 발생: ', error);
      });
  };

  const confirmCapture = () => {
    if (capturedImage) {
      console.log('촬영 성공');
      // 여기에서 이미지를 저장하거나 서버에 업로드할 수 있습니다.
      // capturedImage에 캡처된 이미지 데이터가 있습니다.
    }
  };

  useEffect(() => {
    const startVideo = async () => {
      const stream = await navigator.mediaDevices.getUserMedia(CONSTRAINTS);
      if (videoRef.current && !videoRef.current.srcObject) {
        videoRef.current.srcObject = stream;
      }
    };

    const cleanup = () => {
      if (videoRef.current && videoRef.current.srcObject instanceof MediaStream) {
        const tracks: MediaStreamTrack[] = videoRef.current.srcObject.getTracks();
        tracks.forEach((track: MediaStreamTrack) => track.stop());
      }
    };

    startVideo();
    return cleanup;
  }, []);

  return (
    <div className="col-span-full h-screen relative">
      <TopBarDepth2
        name=""
        onClick={() => {
          navigate('/diagnose');
        }}
        propsIsBack={true}
      />
      <div>
        {test1}
        {test2}
      </div>
      <p className="text-lg text-center font-bold mt-20">
        원하시는 부위에 카메라를 대고
        <br />
        사진을 찍어주세요!
      </p>
      {capturedImage ? (
        <img src={capturedImage} alt="Captured" className="rounded-xl mt-20" />
      ) : (
        <video autoPlay ref={videoRef} playsInline className="rounded-xl mt-20" />
      )}
      {showCaptureButtons ? (
        <div className="absolute w-[100%] bottom-5 text-center">
          <button onClick={captureVideoFrame}>
            <Camera className="w-16 h-16" />
          </button>
        </div>
      ) : (
        <div className="absolute w-[100%] bottom-5 text-center">
          <div className="flex justify-center gap-4">
            <button onClick={resetCapture} className="bg-Main text-white rounded-xl justify-center items-center">
              <p className="px-5 py-3">다시 찍기</p>
            </button>
            <button onClick={confirmCapture} className="bg-Main text-white rounded-xl justify-center items-center">
              <p className="px-5 py-3">진단 받기</p>
            </button>
          </div>
        </div>
      )}
    </div>
  );
}

export default Streaming;
