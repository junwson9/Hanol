import TapBar from 'components/common/TopBar';
import TopTab from 'components/common/TopTabNew';
import { useState } from 'react';
import { ReactComponent as ImageOne } from '../../assets/images/ScalpExam1.svg';
import Image2 from '../../assets/images/paper_icon.png';
import FloatingButton from 'components/button/FloatingButton';
import { useNavigate } from 'react-router';
function Examination() {
  // false가 오른쪽 탭 active
  const [isTabActive, setTabActive] = useState<boolean>(false);
  console.log(isTabActive);
  const navigate = useNavigate();
  const handleTabClick = () => {
    setTabActive((prevActive: boolean) => !prevActive);
  };
  const navToSetScalp = () => {
    navigate('/set-scalpti1');
  };
  return (
    <div className="col-span-full">
      <div>
        <TapBar name={'간편문진'} noMargin={true} />
      </div>
      <div>
        <TopTab active={isTabActive} title1="대시보드" title2="문진 결과" onTabClick={handleTabClick} />
      </div>
      {isTabActive ? (
        <div>
          <p className="text-[1.25rem]  text-left font-bold mt-[2rem] whitespace-nowrap">
            사진 촬영없이 몇 번의 클릭만으로
            <br />내 두피 상태를 진단해보아요.
          </p>
          <div className="mt-[1rem] flex justify-center">
            <ImageOne />
          </div>
        </div>
      ) : (
        <div>
          <p className="text-[1.25rem]  text-left font-bold mt-[2rem] whitespace-nowrap">
            내 생활 습관을 AI가 분석하여
            <br />
            두피 상태를 예측해드려요.
          </p>
          <div className="flex justify-center">
            <img className="w-[12.5rem] h-[12.rem]" src={Image2} alt="Image2" />
          </div>
          <div className="my-[2rem]">
            <FloatingButton name={'두피TI 시작하기'} onClick={navToSetScalp} />
          </div>
        </div>
      )}
    </div>
  );
}

export default Examination;
