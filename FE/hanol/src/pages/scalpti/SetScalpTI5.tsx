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

function SetScalpTI5() {
  const containerRef = useRef<HTMLDivElement | null>(null);
  const navigate = useNavigate();
  const question_1 = ['샴푸', '헤어 스타일링제', '두피 스타일링제', '헤어 에센스', '린스', '트리트먼트', '두피 세럼'];
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
      updatedSelect[4] = selectedIndices;
      return updatedSelect;
    });
    console.log(select);
    navigate('/set-scalpti6');
  };

  return (
    <>
      <div className="col-span-full relative h-screen">
        <div ref={containerRef}>
          <div className="col-start-1 col-end-7">
            <TopBarDepth2
              name="두피TI"
              propsIsBack={true}
              completeBtn={false}
              onClick={() => navigate('/examination')}
            />
            <div className="flex">
              <div className="h-[0.188rem] w-[70%] bg-Main "></div>
              <div className="h-[0.188rem] w-[30%] bg-Gray "></div>
            </div>
          </div>
          <p className="text-[1.25rem]  text-left font-bold mt-20 col-start-1 col-end-5 whitespace-nowrap">
            사용 두피 제품을
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
export default SetScalpTI5;
