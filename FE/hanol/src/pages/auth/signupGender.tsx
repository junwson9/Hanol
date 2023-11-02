import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { ReactComponent as Man } from '../../assets/images/man.svg';
import { ReactComponent as Woman } from '../../assets/images/woman.svg';
import DisabledButton from 'components/button/DisabledButton';
import TapBarDepth2 from './../../components/common/TapBarDepth2';

function SignupGender() {
  const buttonName = '확인';
  const navigate = useNavigate();

  const [gender, setGender] = useState<string | null>(null); // 초기값은 null 또는 문자열

  const handleManClick = () => {
    setGender('man');
  };

  const handleWomanClick = () => {
    setGender('woman');
  };

  const handleButtonClick = () => {
    navigate('/');
  };

  const handleCloseClick = () => {
    navigate('/login');
  };
  return (
    <div className="col-span-full h-screen flex flex-col justify-between">
      <div>
        <TapBarDepth2 name={'회원가입'} onClick={handleCloseClick} propsIsBack={true} completeBtn={false} />
        <div className="flex">
          <div className="h-[0.188rem] w-[67%] bg-Main absolute left-0"></div>
          <div className="h-[0.188rem] w-[33%] bg-Gray absolute left-[67%]"></div>
        </div>
      </div>
      <div>
        <div className="font-bold text-[18px] text-black flex">성별은</div>
        <div className="font-bold text-[18px] text-black flex">어떻게 되시나요?</div>
        <div className="mt-[12px] font-regular text-[12px] text-GrayForText flex">
          성별에 따라 제공되는 솔루션이 달라집니다.
        </div>
      </div>
      <div className="flex justify-center items-center">
        <div
          className={`mr-[1rem] rounded-[1rem] ${gender === 'man' ? 'shadow-lg' : 'shadow'}`}
          onClick={handleManClick}
        >
          <Man />
          <div className="font-medium text-[1rem] mb-[0.5rem]">남성</div>
        </div>
        <div className={`rounded-[1rem] ${gender === 'woman' ? 'shadow-lg' : 'shadow'}`} onClick={handleWomanClick}>
          <Woman />
          <div className="font-medium text-[1rem] mb-[0.5rem]">여성</div>
        </div>
      </div>
      <div></div>
      <div className="mb-[4rem]">
        <DisabledButton name={buttonName} onClick={handleButtonClick} disabled={gender === null} />
      </div>
    </div>
  );
}

export default SignupGender;
