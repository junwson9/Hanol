// import { ReactComponent as CheckUnactive } from 'assets/icons/check-unactive.svg';
import { useState } from 'react';
import { ReactComponent as Check } from 'assets/icons/check.svg';
import FloatingButton from 'components/button/FloatingButton';
import SettingDone from 'components/routine/SettingDone';
import { useNavigate } from 'react-router';

function SetRoutine() {
  const [settingDone, setSettingDone] = useState<boolean>(false);
  const navigate = useNavigate();
  const handleClick = () => {
    navigate('/routine');
  };
  return (
    <>
      {!settingDone ? (
        <>
          <p className="text-xl  text-left font-bold mt-20 col-start-1 col-end-5 whitespace-nowrap">
            두피 진단 결과를 바탕으로
            <br />
            추천 루틴이 완성 됐어요!
          </p>
          <p className=" text-xs text-left text-GrayForText col-start-1 col-end-5 whitespace-nowrap">
            원하는 루틴을 골라서 시작해봐요.
          </p>
          <p className="text-xl  text-left font-bold mt-9 col-start-1 col-end-5 whitespace-nowrap">
            지금 실천 중인 루틴이에요.
          </p>
          <div className="mt-2.5 col-start-1 col-end-7">
            <div className="flex h-14 mt-3 border rounded-lg items-center gap-2.5 whitespace-nowrap">
              <Check className="ml-2.5" />
              여기에
            </div>
            <div className="flex h-14 mt-3 border rounded-lg items-center gap-2.5 whitespace-nowrap">
              <Check className="ml-2.5" />
              실천중인게
            </div>
            <div className="flex h-14 mt-3 border rounded-lg items-center gap-2.5 whitespace-nowrap">
              <Check className="ml-2.5" />
              들어갑니다.
            </div>
          </div>
          <p className="text-xl  text-left font-bold mt-9 col-start-1 col-end-5 whitespace-nowrap">
            이런 루틴은 어때요?
          </p>
          <div className="mt-2.5 col-start-1 col-end-7">
            <div className="flex h-14 mt-3 border rounded-lg items-center gap-2.5 whitespace-nowrap">
              <Check className="ml-2.5" />
              여기에
            </div>
            <div className="flex h-14 mt-3 border rounded-lg items-center gap-2.5 whitespace-nowrap">
              <Check className="ml-2.5" />
              추천이
            </div>
            <div className="flex h-14 mt-3 border rounded-lg items-center gap-2.5 whitespace-nowrap">
              <Check className="ml-2.5" />
              들어갑니다.
            </div>
            <div className="flex h-14 mt-3 border rounded-lg items-center gap-2.5 whitespace-nowrap">
              <Check className="ml-2.5" />
              들어갑니다.
            </div>
            <div className="flex h-14 mt-3 border rounded-lg items-center gap-2.5 whitespace-nowrap">
              <Check className="ml-2.5" />
              들어갑니다.
            </div>
            <div className="flex h-14 mt-3 border rounded-lg items-center gap-2.5 whitespace-nowrap">
              <Check className="ml-2.5" />
              들어갑니다.
            </div>
          </div>
          <div className="sticky col-start-1 col-end-7 mt-60 bottom-5" onClick={() => setSettingDone(true)}>
            <FloatingButton name="루틴 설정하기" onClick={handleClick} />
          </div>
        </>
      ) : (
        <SettingDone />
      )}
    </>
  );
}
export default SetRoutine;
