import CheckLottie from '../../components/routine/CheckLottie';
import SuccessButton from 'components/button/Button';
import { useNavigate } from 'react-router';
import TopBarDepth2 from 'components/common/TapBarDepth2';

function LoginDone() {
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
      <p className="col-span-full font-bold text-[1.125rem] whitespace-nowrap">회원가입이 완료 되었어요!</p>
      <p className="col-start-2 col-end-6 text-[0.75rem] text-GrayForText whitespace-nowrap mt-5"></p>
      <div className="absolute w-[100%] bottom-5">
        <SuccessButton name="머라하지?" onClick={() => navigate('/')} />
      </div>
    </div>
  );
}
export default LoginDone;
