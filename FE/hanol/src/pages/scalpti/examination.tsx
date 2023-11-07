import TapBar from 'components/common/TopBar';
import TopTab from 'components/common/TopTabNew';
import { useState } from 'react';
import Image1 from '../../assets/images/click_icon.png';
import Image2 from '../../assets/images/paper_icon.png';
import FloatingButton from 'components/button/FloatingButton';
import { useNavigate } from 'react-router';
import { useEffect } from 'react';
import axiosInstance from 'api/axiosInterceptor';
import { ReactComponent as Error } from 'assets/icons/error.svg';

type scalpType = {
  type0: boolean;
  type1: boolean;
  type2: boolean;
  type3: boolean;
  type4: boolean;
  type5: boolean;
  type6: boolean;
};

function Examination() {
  // false가 오른쪽 탭 active
  const [isTabActive, setTabActive] = useState<boolean>(true);
  const [scalpType, setScalpType] = useState<scalpType | null>();
  console.log(isTabActive);
  const navigate = useNavigate();
  const handleTabClick = () => {
    setTabActive((prevActive: boolean) => !prevActive);
  };
  const navToSetScalp = () => {
    navigate('/set-scalpti1');
  };

  useEffect(() => {
    if (isTabActive == true) {
      return;
    }
    const fetchData = async () => {
      try {
        const response = await axiosInstance.get(`/examinations`);
        console.log(response.data.data);
        setScalpType(response.data.data);
        console.log(scalpType);
      } catch (error) {
        console.error('데이터 가져오기 오류:', error);
        navigate('/login-error');
      }
    };

    fetchData();
  }, [isTabActive]);

  return (
    <div className="col-span-full">
      <div>
        <TapBar name={'간편문진'} noMargin={true} />
      </div>
      <div>
        <TopTab active={isTabActive} title1="두피TI" title2="문진 결과" onTabClick={handleTabClick} />
      </div>
      {isTabActive ? (
        <div>
          <p className="text-[1.25rem]  text-left font-bold mt-[3rem] whitespace-nowrap">
            사진 촬영없이 몇 번의 클릭만으로
            <br />내 두피 상태를 진단해보아요.
          </p>
          <div className="mt-[1rem] flex justify-center">
            <img className="w-[12.5rem] h-[12.rem]" src={Image1} alt="Image1" />
          </div>
          <p className="text-[1.25rem] text-left font-bold mt-[3rem] whitespace-nowrap">
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
      ) : scalpType === null ? (
        <div className="mt-[6rem]">
          <div className="flex justify-center">
            <Error />
          </div>
          <div className="mt-[1rem]">문진 결과가 없어요.</div>
        </div>
      ) : (
        <div>
          <p className="text-[1.125rem] text-left font-medium mt-[2rem] whitespace-nowrap">
            최홍준님,
            <br />
            지금 생활습관을 계속 유지하시면
            <br />
          </p>
        </div>
      )}
    </div>
  );
}

export default Examination;
