import { useRecoilState, useRecoilValue } from 'recoil';
import { DeviceState } from 'recoil/atoms';
import { PartState } from 'recoil/atoms';
import { useNavigate } from 'react-router';
import TapBarDepth2 from 'components/common/TapBarDepth2';
import CameraButton from 'components/button/Button';
import { ReactComponent as UnActiveCheck } from 'assets/icons/check-unactive.svg';
import { ReactComponent as Check } from 'assets/icons/check.svg';
import { useState, useEffect, useRef } from 'react';
import { ImageState } from 'recoil/atoms';

function SelectPart() {
  const [, setImageURL] = useRecoilState<string>(ImageState);
  const fileInputRef = useRef<HTMLInputElement | null>(null);
  const [doSelect, setDoSelect] = useState<boolean>(false);
  const [activePart, setActivePart] = useRecoilState(PartState);
  const selectedDevice = useRecoilValue(DeviceState);
  const [selectedImage, setSelectedImage] = useState<string | null>(null);

  const confirmCapture = () => {
    if (selectedImage) {
      console.log('진단을 받아봅시다');
      setImageURL(selectedImage);
      navigate('/analyzing');
    }
  };
  console.log(activePart);
  const handleFileChange = async (event: React.ChangeEvent<HTMLInputElement>) => {
    const selectedFile = event.target.files?.[0];

    if (selectedFile) {
      // 여기서 서버로 파일을 업로드하거나 다른 작업을 수행할 수 있습니다.
      console.log('Selected File:', selectedFile);

      // Optional: Display the selected image
      const reader = new FileReader();
      reader.onload = () => {
        const imageUrl = reader.result as string;
        // 여기서 imageUrl을 상태에 저장하거나 사용할 수 있습니다.
        console.log('Image URL:', imageUrl);
        setSelectedImage(imageUrl);
      };
      reader.readAsDataURL(selectedFile);
    }
  };
  const parts: { [key: string]: number } = {
    '왼쪽 앞머리': 0,
    '오른쪽 앞머리': 1,
    정수리: 2,
    '왼쪽 옆머리': 3,
    '오른쪽 옆머리': 4,
    뒷머리: 5,
  };
  const navigate = useNavigate();
  const handleNavigate = () => {
    if (activePart !== 7) {
      if (selectedDevice === 0) {
        navigate('/IoTstreaming');
      } else {
        fileInputRef.current?.click();
      }
    } else {
      setDoSelect(true);
      setTimeout(() => {
        setDoSelect(false);
      }, 2000);
    }
  };
  const handlePartClick = (index: number) => {
    setActivePart(index);
  };
  useEffect(() => {
    setActivePart(7);
  }, []);
  return (
    <div className="col-span-full relative h-screen">
      <input
        type="file"
        accept="image/*"
        capture="environment"
        ref={fileInputRef}
        onChange={handleFileChange}
        style={{ display: 'none' }}
      />
      <TapBarDepth2
        name="진단하기"
        onClick={() => {
          navigate('/diagnose');
        }}
        propsIsBack={true}
        rightBtnType={2}
      />
      {!selectedImage ? (
        <>
          <p className="text-lg  text-left font-bold mt-12 text-center">촬영 부위를 선택해 주세요!</p>
          <div className="flex justify-center">
            <div className="flex-col">
              {Object.entries(parts).map(([partName, index]: [string, number]) => (
                <button
                  key={index}
                  className={`flex gap-3 mt-7 ${activePart === index ? 'text-Main' : ''}`}
                  onClick={() => handlePartClick(index)}
                >
                  {activePart === index ? <Check /> : <UnActiveCheck />}
                  <p>{partName}</p>
                </button>
              ))}
            </div>
          </div>
          {doSelect && (
            <p
              className="absolute  w-[100%] bottom-20 text-Error"
              style={{
                opacity: 1,
              }}
            >
              부위를 선택해 주세요.
            </p>
          )}
          <div className="absolute w-[100%] bottom-5">
            <CameraButton name="촬영하기" onClick={() => handleNavigate()} />
          </div>
        </>
      ) : (
        <>
          {selectedImage && (
            <div>
              <img src={selectedImage} alt="Selected" className="rounded-xl mt-20" />
              <div className="absolute w-[100%] bottom-5 text-center">
                <div className="flex justify-center">
                  <CameraButton name="진단받기" onClick={confirmCapture} />
                </div>
              </div>
            </div>
          )}
        </>
      )}
    </div>
  );
}
export default SelectPart;
