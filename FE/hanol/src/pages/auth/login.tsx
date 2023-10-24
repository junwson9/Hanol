import { ReactComponent as KakaoLogin } from '../../assets/rescureImg/KakaoLogin.svg';

function Login() {
  return (
    <div className="col-span-full h-screen flex flex-col justify-between">
      <div>
        <div className="mt-[105px] font-bold text-[18px] text-black flex">모발의 모든것</div>
        <div className=" font-bold text-[18px] text-black flex">한올에서 간편하게</div>
        <div className="mt-[12px] font-regular text-[12px] text-GrayForText flex">
          더욱 풍성해질 당신을 항상 응원합니다.
        </div>
      </div>
      <div className="flex justify-center mb-[50px]">
        <KakaoLogin />
      </div>
    </div>
  );
}

export default Login;
