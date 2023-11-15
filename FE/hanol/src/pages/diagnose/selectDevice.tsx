import { useState } from 'react';
import StartButton from 'components/button/Button';
import { useNavigate } from 'react-router';
import TapBarDepth2 from 'components/common/TapBarDepth2';
import { ReactComponent as Ex } from 'assets/images/scalpExample.svg';
import { useRecoilState } from 'recoil';
import { DeviceState } from 'recoil/atoms';
import { useRecoilValue } from 'recoil';
import { MemberRoleState } from 'recoil/atoms';
import Snackbar from 'components/common/SnackBar';
import { ReactComponent as CameraIcon } from 'assets/images/Camera_icon.svg';
import { ReactComponent as HanolDevice } from 'assets/images/hanol_device.svg';
function SelectDevice() {
  const [selectedButton, setSelectedButton] = useState<number>(0);
  const Role = useRecoilValue(MemberRoleState);
  const [, setSelectedDevice] = useRecoilState<number>(DeviceState);
  const [snackbarMessage, setSnackbarMessage] = useState<string>(''); // 스낵바 메시지 상태

  const handleButtonSelect = (index: number) => {
    setSelectedButton(index);
  };
  const navigate = useNavigate();
  const handleNavigate = () => {
    if (selectedButton === 1) {
      setSelectedDevice(1);
    } else if (selectedButton === 0) {
      setSelectedDevice(0);
      if (Role == 'MEMBER') {
        setSnackbarMessage('사용을 원하시면 A205팀장 김지수에게 MM주세요!');
        return;
      }
    }
    navigate('/select-part');
  };
  return (
    <div className="col-span-full relative h-screen">
      <TapBarDepth2
        name="진단하기"
        onClick={() => {
          navigate('/diagnose');
        }}
        propsIsBack={false}
        rightBtnType={2}
      />
      <p className="text-lg  text-left font-bold mt-12 text-center">촬영 기기를 선택해 주세요.</p>
      <div className="flex justify-center mt-6 gap-6">
        <button
          className={`flex w-[9rem] h-[9rem] rounded-xl shadow-lg justify-center items-center border ${
            selectedButton === 0 ? 'border-Main border-4' : ''
          }`}
          onClick={() => handleButtonSelect(0)}
        >
          <div>
            <div className="flex justify-center">
              <HanolDevice />
            </div>
            <p>한올 기기</p>
          </div>
        </button>
        <button
          className={`flex w-[9rem] h-[9rem] rounded-xl shadow-lg justify-center items-center border ${
            selectedButton === 1 ? 'border-Main border-4' : ''
          }`}
          onClick={() => handleButtonSelect(1)}
        >
          <div>
            <div className="flex justify-center">
              <CameraIcon />
            </div>
            <p className="text-center">휴대폰 카메라</p>
          </div>
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
          <p className="text-left mt-5 mb-[14rem]">
            3. 사진이 선명할 수록 분석이 정확해집니다.
            <br />
            &nbsp;&nbsp;&nbsp;&nbsp;휴대폰 화면을 보고 기기 위치를 조정해 주세요!
          </p>
        </>
      )}
      <div className="absolute w-[100%] bottom-24">
        <StartButton name="선택 완료" onClick={() => handleNavigate()} />
      </div>
      {snackbarMessage && (
        <Snackbar message={snackbarMessage} onClose={() => setSnackbarMessage('')} /> // 스낵바 컴포넌트 추가
      )}
    </div>
  );
}
export default SelectDevice;
