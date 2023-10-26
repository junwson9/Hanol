import StartButton from 'components/button/Button';
import { useNavigate } from 'react-router';
import TopBarDepth2 from 'components/common/TapBarDepth2';
import { ReactComponent as Ex } from 'assets/images/scalpExample.svg';

function SelectDevice() {
  const navigate = useNavigate();
  const handleNavigate = () => {
    navigate('/diagnose');
  };
  return (
    <div className="col-span-full relative h-screen">
      <TopBarDepth2
        name="진단하기"
        onClick={() => {
          handleNavigate();
        }}
        propsIsBack={true}
      />
      <p className="text-lg  text-left font-bold mt-12 text-center">촬영 기기를 선택해 주세요.</p>
      <div className="flex justify-between mt-6">
        <div className="flex w-[8.5rem] h-[8.5rem] rounded-xl shadow-lg justify-center items-center border">
          <p>한올 기기</p>
        </div>
        <div className="flex w-[8.5rem] h-[8.5rem] rounded-xl shadow-lg justify-center items-center border">
          <p className="text-center">휴대폰 카메라</p>
        </div>
      </div>
      <p className="text-lg  text-left font-bold mt-6">촬영 방법 안내</p>
      <p className="text-left mt-6">1. 카메라 엑세스 권한을 허용해주세요.</p>
      <p className="text-left mt-6">
        2. 아래 예시처럼 카메라를 15~20배 확대하여
        <br />
        &nbsp;&nbsp;&nbsp;&nbsp;두피를 촬영해 주세요.
      </p>
      <div className="mt-6 flex justify-center">
        <Ex />
      </div>
      <p className="text-left mt-6">
        3. 사진이 선명할 수록 분석이 정확해집니다.
        <br />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;촬영 시 타인의 도움을 받아보세요.
      </p>
      <div className="sticky mt-10 bottom-5">
        <StartButton name="선택 완료" onClick={() => navigate('/home')} />
      </div>
    </div>
  );
}
export default SelectDevice;
