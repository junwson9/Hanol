import CheckLottie from './CheckLottie';
import SuccessButton from 'components/button/Button';
import { useNavigate } from 'react-router';
import TopBarDepth2 from 'components/common/TapBarDepth2';

function SettingDone() {
  const navigate = useNavigate();
  const handleNavigate = () => {
    navigate('/');
  };
  return (
    <div className="relative col-span-full h-screen">
      <div className="col-start-1 col-end-7">
        <TopBarDepth2
          name="두피 케어 루틴 설정"
          onClick={() => {
            handleNavigate();
          }}
          propsIsBack={false}
        />
      </div>
      <div className="col-start-2 col-end-6 mt-36">
        <CheckLottie />
      </div>
      <p className="col-span-full font-bold text-xl whitespace-nowrap">두피 케어 루틴을 완성했어요!</p>
      <p className="col-start-2 col-end-6 text-xs text-GrayForText whitespace-nowrap mt-5">
        루틴 알림을 설정하시면 <br /> 까먹지 않고 실천할 수 있어요.
      </p>
      <div className="absolute w-[100%] bottom-5">
        <SuccessButton name="루틴 확인하기" onClick={() => navigate('/home')} />
      </div>
    </div>
  );
}
export default SettingDone;
