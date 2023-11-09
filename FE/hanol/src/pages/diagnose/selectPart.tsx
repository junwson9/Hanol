import { useRecoilState, useRecoilValue } from 'recoil';
import { DeviceState } from 'recoil/atoms';
import { PartState } from 'recoil/atoms';
import { useNavigate } from 'react-router';
import TapBarDepth2 from 'components/common/TapBarDepth2';
import CameraButton from 'components/button/Button';
import { ReactComponent as UnActiveCheck } from 'assets/icons/check-unactive.svg';
import { ReactComponent as Check } from 'assets/icons/check.svg';
import { useEffect, useState } from 'react';

function SelectPart() {
  const [doSelect, setDoSelect] = useState<boolean>(false);
  const [activePart, setActivePart] = useRecoilState(PartState);
  const selectedDevice = useRecoilValue(DeviceState);
  const parts: string[] = ['왼쪽 앞머리', '오른쪽 앞머리', '정수리', '왼쪽 옆머리', '오른쪽 옆머리', '뒷머리'];
  const navigate = useNavigate();
  const handleNavigate = () => {
    if (activePart) {
      if (selectedDevice === 0) {
        navigate('/IoTstreaming');
      } else {
        navigate('/streaming');
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
      <TapBarDepth2
        name="진단하기"
        onClick={() => {
          navigate('/diagnose');
        }}
        propsIsBack={true}
        rightBtnType={2}
      />
      <p className="text-lg  text-left font-bold mt-12 text-center">촬영 부위를 선택해 주세요!</p>
      <div className="flex justify-center">
        <div className="flex-col">
          {parts.map((part, index) => (
            <button
              key={index}
              className={`flex gap-3 mt-7 ${activePart === index ? 'text-Main' : ''}`}
              onClick={() => handlePartClick(index)}
            >
              {activePart === index ? <Check /> : <UnActiveCheck />}
              <p>{part}</p>
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
    </div>
  );
}
export default SelectPart;
