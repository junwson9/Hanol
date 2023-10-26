import { ReactComponent as ExplainRoutinesvg } from 'assets/images/explainRoutine.svg';
import { ReactComponent as Bell } from 'assets/images/bell.svg';
import { ReactComponent as CareRoutineExplain } from 'assets/images/careRoutineExplain.svg';
// import FloatingButton from 'components/button/FloatingButton';

function ExplainRoutine() {
  return (
    <div className="col-span-full flex flex-col justify-between">
      <div>
        <div className="font-bold text-[1.125rem] text-black flex">두피 진단 결과를 바탕으로</div>
        <div className="font-bold text-[1.125rem] text-black flex">두피 케어 루틴을 추천해드려요.</div>
        <div className="mt-[1rem] bg-Gray px-[0.5rem] py-[2rem] rounded-[1rem]">
          <ExplainRoutinesvg className="w-full" />
        </div>
      </div>
      <div className="mt-[3rem]">
        <div className="flex items-center">
          <div>
            <div className="font-bold text-[1.125rem] text-black flex">루틴을 선택하고</div>
            <div className="font-bold text-[1.125rem] text-black flex">알림을 받아 실천하세요.</div>
          </div>
          <div className="ml-auto">
            <Bell />
          </div>
        </div>
        <div className="mt-[1rem] bg-Gray px-[0.5rem] py-[0.5rem] rounded-[2rem]">
          <div className="font-bold text-[0.75rem] flex ml-[2rem]">오늘의 루틴!</div>
          <div className="font-medium text-[0.75rem] flex ml-[2rem]">오늘의 두피 루틴을 시작해봅시다!</div>
        </div>
      </div>
      <div className="mt-[3rem]">
        <div className="font-bold text-[1.125rem] text-black flex">루틴을 선택하고</div>
        <div className="font-bold text-[1.125rem] text-black flex">알림을 받아 실천하세요.</div>
        <div className="flex justify-center bg-Gray mt-[4rem] rounded-[1rem]">
          <CareRoutineExplain className="h-[22rem] my-[2rem]" />
        </div>
      </div>
      {/* <FloatingButton name={'name'} /> */}
    </div>
  );
}
export default ExplainRoutine;
