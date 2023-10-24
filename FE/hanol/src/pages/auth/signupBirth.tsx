import React from 'react';
import 'react-datepicker/dist/react-datepicker.css';
import DateValidationDisableFuture from 'components/picker/DatePicker';
import Button from 'components/button/Button';
import { useNavigate } from 'react-router-dom';

function SignupBirth() {
  const buttonName = '확인';
  const navigate = useNavigate();

  const handleButtonClick = () => {
    navigate('/signup-gender');
  };

  return (
    <div className="col-span-full h-screen flex flex-col justify-between">
      <div>
        <div className="mt-[105px] font-bold text-[18px] text-black flex">가입을 축하드려요!</div>
        <div className=" font-bold text-[18px] text-black flex">생년월일을 알려주세요.</div>
        <div className="mt-[12px] font-regular text-[12px] text-GrayForText flex">
          나이에 따라 분석 결과가 달라집니다.
        </div>
      </div>
      <div>
        <DateValidationDisableFuture />
      </div>
      <div className="mb-[4rem]">
        <Button name={buttonName} onClick={handleButtonClick} />
      </div>
    </div>
  );
}

export default SignupBirth;
