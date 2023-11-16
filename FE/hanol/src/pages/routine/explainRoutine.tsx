import { ReactComponent as ExplainRoutinesvg } from 'assets/images/explainRoutine.svg';
import Bell from 'assets/images/bell.png';
import CareRoutineExplain from 'assets/images/careRoutineExplain.png';
import FloatingButton from 'components/button/FloatingButton';
import TapBar from 'components/common/TopBar';
import { useNavigate } from 'react-router';
import { ReactComponent as ExplainAlarm } from 'assets/images/explainAlarm.svg';

function ExplainRoutine() {
  const navigate = useNavigate();
  const handleClick = () => {
    navigate('/login-error');
  };
  return (
    <div className="col-span-full flex flex-col justify-between">
      <TapBar name={'두피 케어 루틴'} />
      <div>
        <div className="font-bold text-[1.125rem] text-black flex">두피 진단 결과를 바탕으로</div>
        <div className="font-bold text-[1.125rem] text-black flex">두피 케어 루틴을 추천해드려요.</div>
        <div className="mt-[1rem] px-[0.5rem] py-[2rem] rounded-[1rem]">
          <ExplainRoutinesvg className="w-full" />
        </div>
      </div>
      <div className="mt-[3rem]">
        <div className="flex items-center">
          <div>
            <div className="font-bold text-[1.125rem] text-black flex">루틴을 선택하고</div>
            <div className="font-bold text-[1.125rem] text-black flex">알림을 받아 실천하세요.</div>
          </div>
          <div className="">
            <img className="w-[4.3rem]" src={Bell} alt="" />
          </div>
        </div>
        <div className="mt-[2rem] flex justify-center ">
          <ExplainAlarm />
        </div>
      </div>
      <div className="mt-[3rem]">
        <div className="font-bold text-[1.125rem] text-black flex">하루 하루 꾸준한 실천이</div>
        <div className="font-bold text-[1.125rem] text-black flex">한올 한올 건강한 모발을 </div>
        <div className="font-bold text-[1.125rem] text-black flex">만들어 줄거에요.</div>
        <div className="mt-[0.5rem] flex justify-center">
          <img className="w-[14rem]" src={CareRoutineExplain} alt="" />
        </div>
      </div>
      <div className="mt-[3rem] mb-[3rem] sticky bottom-5 z-1">
        <FloatingButton name={'두피 케어 루틴 설정하기'} onClick={handleClick} />
      </div>
      <br />
      <br />
      <br />
    </div>
  );
}
export default ExplainRoutine;
