import Button from 'components/button/Button';
import { useNavigate } from 'react-router-dom';

function loginError() {
  const buttonName = '로그인 하기';
  const navigate = useNavigate();

  const handleButtonClick = () => {
    navigate('/login');
  };

  return (
    <div className="col-span-full h-screen flex items-center justify-center bg-neutral-700 bg-opacity-60">
      <div className="mx-[30px] w-full h-[280px] flex flex-col justify-center items-center bg-white rounded-[15px] bg-opacity-80">
        <div className="w-full font-medium text-[16px] mt-10">
          <div>로그인이 필요한</div>
          <div>서비스 입니다.</div>
        </div>
        <div className="w-full px-10 mt-10">
          <Button name={buttonName} onClick={handleButtonClick} />
        </div>
      </div>
    </div>
  );
}

export default loginError;
