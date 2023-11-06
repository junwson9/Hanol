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

function SetScalpTI1() {
  const containerRef = useRef<HTMLDivElement | null>(null);
  const navigate = useNavigate();
  const question_1 = ['1일 1회', '1일 2회', '2일 1회'];
  const [selectedOption, setSelectedOption] = useState<string | null>(null); // 선택된 옵션을 상태로 관리
  const [select, setSelect] = useRecoilState(examinationState);
  const [selectedIndex, setSelectedIndex] = useState<number | null>(null);

  const handleOptionClick = (option: string, index: number) => {
    setSelectedOption(option);
    setSelectedIndex(index);
    console.log(select);
  };

  const handleButtonClick = () => {
    setSelect((prevSelect) => {
      const updatedSelect = [...prevSelect];
      updatedSelect[0] = selectedIndex as number;
      return updatedSelect;
    });
    navigate('/set-scalpti2');
  };
  return (
    <>
      <div className="col-span-full relative h-screen">
        <div ref={containerRef}>
          <div className="col-start-1 col-end-7">
            <TopBarDepth2 name="두피TI" propsIsBack={true} rightBtnType={2} onClick={() => navigate('/examination')} />
            <div className="flex">
              <div className="h-[0.188rem] w-[14%] bg-Main"></div>
              <div className="h-[0.188rem] w-[86%] bg-Gray"></div>
            </div>
          </div>
          <p className="text-[1.25rem]  text-left font-bold mt-20 col-start-1 col-end-5 whitespace-nowrap">
            샴푸 사용 빈도를
            <br />
            선택해주세요.
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

        <div className="col-span-full sticky mt-10 bottom-5">
          <DisabledButton name="다음" onClick={handleButtonClick} disabled={selectedOption === null} />
        </div>
      </div>
    </>
  );
}
export default SetScalpTI1;
