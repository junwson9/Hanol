import CheckLottie from '../../components/routine/CheckLottie';
import SuccessButton from 'components/button/Button';
import { useNavigate } from 'react-router';
import { examinationState } from 'recoil/atoms';
import { useRecoilState } from 'recoil';
// import { useEffect } from 'react';
function SetScalpDone() {
  const navigate = useNavigate();
  const scalpInfo = useRecoilState(examinationState);

  console.log(scalpInfo);
  // useEffect(() => {
  //   const fetchData = async () => {
  //     try {
  //       const response = await axiosInstance.get('/routines');
  //       const { my_routines, suggested_routines } = response.data.data;

  //       setMyRoutines(my_routines);
  //       setSuggestedRoutines(suggested_routines);

  //       console.log('My Routines:', my_routines);
  //       console.log('Suggested Routines:', suggested_routines);
  //     } catch (error) {
  //       console.log('호출한곳 ---------------');
  //       console.error('Error fetching data:', error);
  //     }
  //   };

  //   fetchData(); // 비동기 함수 호출
  // }, []);
  return (
    <div className="relative col-span-full h-screen">
      <div className="col-start-2 col-end-6 mt-36">
        <CheckLottie />
      </div>
      <p className="col-span-full font-bold text-[1.125rem] whitespace-nowrap">
        두피 생활습관 문진이 <br />
        완료 되었어요
      </p>
      <p className="col-start-2 col-end-6 text-[0.75rem] text-GrayForText whitespace-nowrap mt-5"></p>
      <div className="absolute w-[100%] bottom-5">
        <SuccessButton name="결과 확인" onClick={() => navigate('/')} />
      </div>
    </div>
  );
}
export default SetScalpDone;
