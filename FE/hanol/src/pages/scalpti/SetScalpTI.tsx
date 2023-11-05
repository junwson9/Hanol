// import { ReactComponent as CheckUnactive } from 'assets/icons/check-unactive.svg';
import { useState, useEffect, useRef } from 'react';
// import { ReactComponent as Check } from 'assets/icons/check.svg';
import DisabledButton from 'components/button/DisabledButton';
import TopBarDepth2 from 'components/common/TapBarDepth2';
import { useNavigate } from 'react-router-dom';
import RoutineSetButton from 'components/button/RoutineSetButton';
type SuggestedRoutine = {
  routine_id: number;
  member_routine_id: null;
  routine_name: string;
};

function SetScalpTI() {
  const containerRef = useRef<HTMLDivElement | null>(null);
  const question_1 = { 0: '1일 1회', 1: '1일 2회', 2: '2일 1회' };
  const handleButtonClick () => {
    useNavigate('/')
  }
  return (
    <>
      <div className="col-span-full relative h-screen">
        <div ref={containerRef}>
          <div className="col-start-1 col-end-7">
            <TopBarDepth2
              name="두피 케어 루틴 설정"
              onClick={() => {
                handleNavigate();
              }}
              propsIsBack={true}
              completeBtn={false}
            />
          </div>
          <p className="text-[1.25rem]  text-left font-bold mt-20 col-start-1 col-end-5 whitespace-nowrap">
            샴푸 사용 빈도를
            <br />
            선택해주세요!
          </p>

          <div className="mt-2.5 col-start-1 col-end-7">
            {question_1.map((element) => (
              <RoutineSetButton routineName={element.routine_name} />
            ))}
          </div>
        </div>

        <div className="col-span-full sticky mt-10 bottom-5">
          <DisabledButton name="다음" onClick={handleButtonClick} disabled={true} />
        </div>
      </div>
    </>
  );
}
export default SetScalpTI;
