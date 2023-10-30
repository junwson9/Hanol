// import { ReactComponent as CheckUnactive } from 'assets/icons/check-unactive.svg';
import { useState, useEffect, useRef } from 'react';
// import { ReactComponent as Check } from 'assets/icons/check.svg';
import FloatingButton from 'components/button/FloatingButton';
import SettingDone from 'components/routine/SettingDone';
import TopBarDepth2 from 'components/common/TapBarDepth2';
import { useNavigate } from 'react-router-dom';
import axiosInstance from 'api/axiosInterceptor';
import RoutineSetButton from 'components/button/RoutineSetButton';
type SuggestedRoutine = {
  routine_id: number;
  member_routine_id: null;
  routine_name: string;
};

function SetRoutine() {
  const navigate = useNavigate();
  const [settingDone, setSettingDone] = useState<boolean>(false);
  const containerRef = useRef<HTMLDivElement | null>(null);
  const [suggestedRoutines, setSuggestedRoutines] = useState<SuggestedRoutine[]>([]);
  const [myRoutines, setMyRoutines] = useState<SuggestedRoutine[]>([]);
  const [selectedRoutineIds, setSelectedRoutineIds] = useState<number[]>([]);
  const [selectedMyRoutineIds, setSelectedMyRoutineIds] = useState<number[]>([]);

  const handleNavigate = () => {
    navigate('/');
  };
  console.log(selectedRoutineIds);
  console.log(selectedMyRoutineIds);

  const handleRoutineClick = (element: SuggestedRoutine) => {
    // Toggle the routine's ID in the selectedRoutineIds array
    if (selectedRoutineIds.includes(element.routine_id)) {
      setSelectedRoutineIds(selectedRoutineIds.filter((id) => id !== element.routine_id));
    } else {
      setSelectedRoutineIds([...selectedRoutineIds, element.routine_id]);
    }
  };

  const handleMyRoutineClick = (element: SuggestedRoutine) => {
    // Toggle the routine's ID in the selectedRoutineIds array
    if (selectedMyRoutineIds.includes(element.routine_id)) {
      setSelectedMyRoutineIds(selectedMyRoutineIds.filter((id) => id !== element.routine_id));
    } else {
      setSelectedMyRoutineIds([...selectedMyRoutineIds, element.routine_id]);
    }
  };

  const handleButtonClick = async () => {
    try {
      const requestBody = {
        added_routines: selectedRoutineIds,
        removed_routines: selectedMyRoutineIds,
      };
      console.log(requestBody);
      const response = await axiosInstance.patch('/routines', requestBody);
      console.log(response);
      setSettingDone(true);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axiosInstance.get('/routines');
        const { my_routines, suggested_routines } = response.data.data;

        setMyRoutines(my_routines);
        setSuggestedRoutines(suggested_routines);

        console.log('My Routines:', my_routines);
        console.log('Suggested Routines:', suggested_routines);
      } catch (error) {
        console.log('호출한곳 ---------------');
        console.error('Error fetching data:', error);
      }
    };

    fetchData(); // 비동기 함수 호출
  }, []);

  return (
    <>
      {!settingDone ? (
        <div className="col-span-full relative h-screen">
          <div ref={containerRef}>
            <div className="col-start-1 col-end-7">
              <TopBarDepth2
                name="두피 케어 루틴 설정"
                onClick={() => {
                  handleNavigate();
                }}
                propsIsBack={true}
              />
            </div>
            <p className="text-[1.25rem]  text-left font-bold mt-20 col-start-1 col-end-5 whitespace-nowrap">
              두피 진단 결과를 바탕으로
              <br />
              추천 루틴이 완성 됐어요!
            </p>
            <p className=" text-[0.75rem] mt-[0.5rem] text-left text-GrayForText col-start-1 col-end-5 whitespace-nowrap">
              원하는 루틴을 골라서 시작해봐요.
            </p>

            <div className="mt-2.5 col-start-1 col-end-7">
              {myRoutines.length > 0 && (
                <p className="text-[1.25rem] text-left font-bold mt-9 col-start-1 col-end-5 whitespace-nowrap">
                  지금 실천 중인 루틴이에요.
                </p>
              )}
              {myRoutines.map((element) => (
                <RoutineSetButton
                  key={element.routine_id}
                  routineName={element.routine_name}
                  active={!selectedMyRoutineIds.includes(element.routine_id)}
                  onClick={() => handleMyRoutineClick(element)}
                />
              ))}
            </div>

            <div className="mt-2.5 col-start-1 col-end-7">
              <p className="text-[1.25rem] text-left font-bold mt-9 col-start-1 col-end-5 whitespace-nowrap">
                이런 루틴은 어때요?
              </p>
              {suggestedRoutines.map((element) => (
                <RoutineSetButton
                  key={element.routine_id}
                  routineName={element.routine_name}
                  active={selectedRoutineIds.includes(element.routine_id)}
                  onClick={() => handleRoutineClick(element)}
                />
              ))}
            </div>
          </div>

          <div className="col-span-full sticky mt-10 bottom-5">
            <FloatingButton name="루틴 설정하기" onClick={handleButtonClick} />
          </div>
        </div>
      ) : (
        <SettingDone />
      )}
    </>
  );
}
export default SetRoutine;
