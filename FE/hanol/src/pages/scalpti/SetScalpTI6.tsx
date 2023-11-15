// import { ReactComponent as CheckUnactive } from 'assets/icons/check-unactive.svg';
import { useRef } from 'react';
// import { ReactComponent as Check } from 'assets/icons/check.svg';
import DisabledButton from 'components/button/DisabledButton';
import TopBarDepth2 from 'components/common/TapBarDepth2';
import { useNavigate } from 'react-router-dom';
import RoutineSetButton from 'components/button/RoutineSetButton';
import { useState } from 'react';
import { useRecoilState } from 'recoil';
import { examinationState } from 'recoil/atoms';

function SetScalpTI6() {
  const containerRef = useRef<HTMLDivElement | null>(null);
  const navigate = useNavigate();
  const question_1 = ['예', '아니오'];
  const [selectedOption, setSelectedOption] = useState<string | null>(null); // 선택된 옵션을 상태로 관리
  const [select, setSelect] = useRecoilState(examinationState);
  const [selectedIndex, setSelectedIndex] = useState<number | null>(null);
  const handleOptionClick = (option: string, index: number) => {
    setSelectedOption(option);
    setSelectedIndex(index);
  };

  const handleButtonClick = () => {
    setSelect((prevSelect) => {
      const updatedSelect = [...prevSelect];
      updatedSelect[5] = selectedIndex as number;
      return updatedSelect;
    });
    console.log(select);

    navigate('/set-scalpti7');
  };
  return (
    <>
      <div className="col-span-full relative h-screen">
        <div ref={containerRef}>
          <div className="col-start-1 col-end-7">
            <TopBarDepth2 name="두피TI" propsIsBack={true} rightBtnType={2} onClick={() => navigate('/examination')} />
            <div className="flex">
              <div className="h-[0.188rem] w-[85%] bg-Main "></div>
              <div className="h-[0.188rem] w-[15%] bg-Gray "></div>
            </div>
          </div>
          <p className="text-[1.25rem]  text-left font-bold mt-20 col-start-1 col-end-5 whitespace-nowrap">
            두피 제품 사용을
            <br />
            희망하시나요?
          </p>
          <div className="mt-[2.5rem] col-start-1 col-end-7">
            {question_1.map((element, index) => (
              <RoutineSetButton
                key={index}
                routineName={element}
                active={selectedOption === element}
                onClick={() => handleOptionClick(element, index)}
              />
            ))}
          </div>
        </div>

        <div className="mt-[3rem] mb-[3rem] sticky bottom-5 z-1">
          <DisabledButton name="다음" onClick={handleButtonClick} disabled={selectedOption === null} />
        </div>
      </div>
    </>
  );
}
export default SetScalpTI6;
