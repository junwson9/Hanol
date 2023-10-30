import { useState } from 'react';
import StartButton from 'components/button/Button';
import { useNavigate } from 'react-router';
import TopBarDepth2 from 'components/common/TapBarDepth2';
import { ReactComponent as Ex } from 'assets/images/scalpExample.svg';

function SelectDevice() {
  const [selectedButton, setSelectedButton] = useState<number>(0);
  const handleButtonSelect = (index: number) => {
    setSelectedButton(index);
  };
  const navigate = useNavigate();
  const handleNavigate = () => {
    if (selectedButton === 1) {
      navigate('/streaming');
    } else if (selectedButton === 0) {
      navigate('/IoTstreaming');
    }
  };
  return (
    <div className="col-span-full relative h-screen">
      <TopBarDepth2
        name="진단하기"
        onClick={() => {
          navigate('/diagnose');
        }}
        propsIsBack={false}
      />
      <p className="text-lg  text-left font-bold mt-12 text-center">촬영 기기를 선택해 주세요.</p>
      <div className="flex justify-center mt-6 gap-6">
        <button
          className={`flex w-[6.5rem] h-[6.5rem] rounded-xl shadow-lg justify-center items-center border ${
            selectedButton === 0 ? 'border-Main border-4' : ''
          }`}
          onClick={() => handleButtonSelect(0)}
        >
          <p>한올 기기</p>
        </button>
        <button
          className={`flex w-[6.5rem] h-[6.5rem] rounded-xl shadow-lg justify-center items-center border ${
            selectedButton === 1 ? 'border-Main border-4' : ''
          }`}
          onClick={() => handleButtonSelect(1)}
        >
          <p className="text-center">휴대폰 카메라</p>
        </button>
      </div>
      <p className="text-lg  text-left font-bold mt-6">촬영 방법 안내</p>
      {selectedButton ? (
        <>
          <p className="text-left mt-5">1. 카메라 엑세스 권한을 허용해주세요.</p>
          <p className="text-left mt-5">
            2. 아래 예시처럼 카메라를 15~20배 확대하여
            <br />
            &nbsp;&nbsp;&nbsp;&nbsp;두피를 촬영해 주세요.
          </p>
          <div className="mt-6 flex justify-center">
            <Ex />
          </div>
          <p className="text-left mt-5">
            3. 사진이 선명할 수록 분석이 정확해집니다.
            <br />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;촬영 시 타인의 도움을 받아보세요.
          </p>
        </>
      ) : (
        <>
          <p className="text-left mt-5">1. 와이파이를 연결해주세요.</p>
          <p className="text-left mt-5">
            2. 고민부위에 기기를 대고
            <br />
            &nbsp;&nbsp;&nbsp;&nbsp;두피를 촬영해 주세요.
          </p>
          <p className="text-left mt-5">
            3. 사진이 선명할 수록 분석이 정확해집니다.
            <br />
            &nbsp;&nbsp;&nbsp;&nbsp;휴대폰 화면을 보고 기기 위치를 조정해 주세요!
          </p>
        </>
      )}
      <div className="absolute w-[100%] bottom-5">
        <StartButton name="선택 완료" onClick={() => handleNavigate()} />
      </div>
    </div>
  );
}
export default SelectDevice;
