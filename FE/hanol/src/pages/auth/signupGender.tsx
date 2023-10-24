import React from 'react';
import Button from 'components/button/Button';
import { useNavigate } from 'react-router-dom';

function SignupGender() {
  const buttonName = '확인';
  const navigate = useNavigate();

  const handleButtonClick = () => {
    navigate('/signup-gender');
  };

  return (
    <div className="col-span-full h-screen flex flex-col justify-between">
      <div>
        <div className="mt-[105px] font-bold text-[18px] text-black flex">성별은</div>
        <div className=" font-bold text-[18px] text-black flex">어떻게 되시나요?</div>
        <div className="mt-[12px] font-regular text-[12px] text-GrayForText flex">
          성별에 따라 제공되는 솔루션이 달라집니다.
        </div>
      </div>
      <div>{/* 여기에 성 별 */}</div>
      <div className="mb-[4rem]">
        <Button name={buttonName} onClick={handleButtonClick} />
      </div>
    </div>
  );
}

export default SignupGender;
