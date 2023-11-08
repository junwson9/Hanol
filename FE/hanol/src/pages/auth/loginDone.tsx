import CheckLottie from '../../components/routine/CheckLottie';
import SuccessButton from 'components/button/Button';
import { useNavigate } from 'react-router';

function LoginDone() {
  const navigate = useNavigate();

  return (
    <div className="relative col-span-full h-screen">
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
