import { useState } from 'react';
import { useNavigate } from 'react-router';
import TapBarDepth2 from 'components/common/TapBarDepth2';
import { useRecoilState } from 'recoil';
import { DeviceState } from 'recoil/atoms';
import { useRecoilValue } from 'recoil';
import { MemberRoleState } from 'recoil/atoms';
import Snackbar from 'components/common/SnackBar';
import { ReactComponent as CameraIcons } from 'assets/images/cellphoneCamera.svg';
import { ReactComponent as HanolDevice } from 'assets/images/hanol_device.svg';
import explainwifi from 'assets/icons/explain_WIFI 1.png';
import { ReactComponent as ExplainHanol } from 'assets/images/explainHanol.svg';
import FloatingButton from 'components/button/FloatingButton';
import { ReactComponent as Dopi } from 'assets/images/dopi.svg';
import { ReactComponent as CameraZoom } from 'assets/images/cameraZoom.svg';

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
      <p className="text-lg text-left font-bold mt-10">촬영 기기 선택</p>
      <div className="flex justify-center mt-8 gap-6">
        <button
          className={`flex w-[9rem] h-[9rem] rounded-xl justify-center items-center border ${
            selectedButton === 0 ? 'border-Main border-2' : ''
          }`}
          onClick={() => handleButtonSelect(0)}
        >
          <div>
            <div className="flex justify-center mb-[0.25rem]">
              <HanolDevice />
            </div>
            <p className={`${selectedButton === 0 ? 'text-Main' : ''}`}>한올 기기</p>
          </div>
        </button>
        <button
          className={`flex w-[9rem] h-[9rem] rounded-xl justify-center items-center border ${
            selectedButton === 1 ? 'border-Main border-2' : ''
          }`}
          onClick={() => handleButtonSelect(1)}
        >
          <div>
            <div className="flex justify-center mb-[0.25rem]">
              <CameraIcons />
            </div>
            <p className={`${selectedButton === 1 ? 'text-Main' : ''}`}>휴대폰 카메라</p>
          </div>
        </button>
      </div>
      <p className="text-lg text-left font-bold mt-16 mb-8">촬영 방법 안내</p>
      {!selectedButton ? (
        <>
          <div className="bg-[#F6F6F6] rounded-[12px] py-3">
            <div className="text-[#acacac] font-Medium text-[14px] pt-2">STEP 1.</div>
            <div className="font-Medium">WIFI 연결 확인</div>
            <div className="flex justify-center pt-2 pb-4">
              <img src={explainwifi} />
            </div>
          </div>
          <div className="bg-[#F6F6F6] rounded-[12px] mt-8 py-3">
            <div className="text-[#acacac] font-Medium text-[14px] pt-2">STEP 2.</div>
            <div className="font-Medium">고민 부위에 기기를 가까이 대고 촬영</div>
            <div className="flex justify-center pt-2 pb-4">
              <ExplainHanol />
            </div>
          </div>
          <div className="bg-[#F6F6F6] rounded-[12px] mt-8 py-3">
            <div className="text-[#acacac] font-Medium text-[14px] pt-2">TIP!</div>
            <div className="font-Medium">사진이 선명할수록 분석이 정확해집니다.</div>
            <div className="font-Medium">아래 사진처럼 초점을 잘 맞춰 </div>
            <div className="font-Medium">촬영해주세요!</div>
            <div className="flex justify-center pt-2 pb-4">
              <Dopi />
            </div>
          </div>
        </>
      ) : (
        <>
          <div className="bg-[#F6F6F6] rounded-[12px] py-3">
            <div className="text-[#acacac] font-Medium text-[14px] pt-2">STEP 1.</div>
            <div className="font-Medium">카메라 15~20배 확대</div>
            <div className="flex justify-center pt-2 pb-4">
              <img src={explainwifi} />
            </div>
          </div>
          <div className="bg-[#F6F6F6] rounded-[12px] mt-8 py-3">
            <div className="text-[#acacac] font-Medium text-[14px] pt-2">TIP!</div>
            <div className="font-Medium">사진이 선명할수록 분석이 정확해집니다.</div>
            <div className="font-Medium">아래 사진처럼 초점을 잘 맞춰 </div>
            <div className="font-Medium">촬영해주세요!</div>
            <div className="flex justify-center pt-2 pb-4">
              <CameraZoom />
            </div>
          </div>
        </>
      )}
      <div className="mt-[3rem] mb-[3rem] sticky bottom-5 z-1">
        <FloatingButton name={'선택 완료'} onClick={handleNavigate} />
      </div>
      {snackbarMessage && (
        <Snackbar message={snackbarMessage} onClose={() => setSnackbarMessage('')} /> // 스낵바 컴포넌트 추가
      )}
    </div>
  );
}
export default SelectDevice;
