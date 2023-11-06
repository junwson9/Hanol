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

function SetScalpTI4() {
  const containerRef = useRef<HTMLDivElement | null>(null);
  const navigate = useNavigate();
  const question_1 = ['염색모발', '가발 사용(붙임머리 포함)', '모발이식 / 시술', '기타'];
  const [selectedOptions, setSelectedOptions] = useState<boolean[]>(new Array(question_1.length).fill(false));
  const [select, setSelect] = useRecoilState(examinationState);
  // const [selectedIndex, setSelectedIndex] = useState<number | null>(null);
  const handleOptionClick = (option: string, index: number) => {
    const newSelectedOptions = [...selectedOptions];
    newSelectedOptions[index] = !newSelectedOptions[index]; // Toggle 선택 상태
    setSelectedOptions(newSelectedOptions);
    console.log(selectedOptions);
  };

  const handleButtonClick = () => {
    const selectedIndices = selectedOptions.reduce((indices, isSelected, index) => {
      if (isSelected) {
        indices.push(index);
      }
      return indices;
    }, [] as number[]);
    console.log(selectedIndices);

    setSelect((prevSelect) => {
      const updatedSelect = [...prevSelect];
      console.log(updatedSelect);
      updatedSelect[3] = selectedIndices;
      return updatedSelect;
    });
    console.log(select);
    navigate('/set-scalpti5');
  };

  return (
    <>
      <div className="col-span-full relative h-screen">
        <div ref={containerRef}>
          <div className="col-start-1 col-end-7">
            <TopBarDepth2 name="두피TI" propsIsBack={true} rightBtnType={2} onClick={() => navigate('/examination')} />
            <div className="flex">
              <div className="h-[0.188rem] w-[56%] bg-Main "></div>
              <div className="h-[0.188rem] w-[44%] bg-Gray "></div>
            </div>
          </div>
          <p className="text-[1.25rem]  text-left font-bold mt-20 col-start-1 col-end-5 whitespace-nowrap">
            현재 모발 상태를
            <br />
            선택해주세요.
            <br />
            <div className="mt-[12px] font-regular text-[12px] text-GrayForText flex">중복 선택 가능</div>
          </p>
          <div className="mt-[2.5rem] col-start-1 col-end-7">
            {question_1.map((element, index) => (
              <RoutineSetButton
                key={index}
                routineName={element}
                active={selectedOptions[index]}
                onClick={() => handleOptionClick(element, index)}
              />
            ))}
          </div>
        </div>

        <div className="col-span-full sticky mt-10 bottom-5">
          <DisabledButton
            name="다음"
            onClick={handleButtonClick}
            disabled={!selectedOptions.some((selected) => selected)}
          />
        </div>
      </div>
    </>
  );
}
export default SetScalpTI4;
