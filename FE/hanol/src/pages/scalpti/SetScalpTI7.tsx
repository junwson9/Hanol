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
import axiosInstance from 'api/axiosInterceptor';

function SetScalpTI7() {
  const containerRef = useRef<HTMLDivElement | null>(null);
  const navigate = useNavigate();
  const question_1 = ['향', '머릿결', '가격', '헹굼후느낌', '두피자극', '세정력'];
  const [selectedOption, setSelectedOption] = useState<string | null>(null); // 선택된 옵션을 상태로 관리
  const [select, setSelect] = useRecoilState(examinationState);
  const [selectedIndex, setSelectedIndex] = useState<number | null>(null);
  const handleOptionClick = (option: string, index: number) => {
    setSelectedOption(option);
    setSelectedIndex(index);
  };

  const handleButtonClick = async () => {
    setSelect((prevSelect) => {
      const updatedSelect = [...prevSelect];
      updatedSelect[6] = selectedIndex as number;
      return updatedSelect;
    });
    const answer = {
      answer1: select[0],
      answer2: select[1],
      answer3: select[2],
      answer4: select[3],
      answer5: select[4],
      answer6: select[5],
      answer7: select[6],
    };
    console.log(answer);
    try {
      const response = await axiosInstance.post('/examinations', answer);
      console.log(response);
      navigate('/set-scalp-done');
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };
  return (
    <>
      <div className="col-span-full relative h-screen">
        <div ref={containerRef}>
          <div className="col-start-1 col-end-7">
            <TopBarDepth2 name="두피TI" propsIsBack={true} rightBtnType={2} onClick={() => navigate('/examination')} />
            <div className="flex">
              <div className="h-[0.188rem] w-[100%] bg-Main "></div>
              <div className="h-[0.188rem] w-[0%] bg-Gray "></div>
            </div>
          </div>
          <p className="text-[1.25rem]  text-left font-bold mt-20 col-start-1 col-end-5 whitespace-nowrap">
            샴푸 구매시
            <br />
            고려사항은 무엇인가요?
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
export default SetScalpTI7;
