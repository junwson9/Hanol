import TapBar from 'components/common/TopBar';
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axiosInstance from 'api/axiosInterceptor';
import { ReactComponent as Arrow } from '../../assets/icons/arrow_right.svg';

function About() {
  const navigate = useNavigate();
  const [role, setRole] = useState<string | null>(null);
  const [name, setName] = useState<string | null>(null);
  const handleButtonClick = () => {
    navigate('/login');
  };
  const handleLogout = () => {
    localStorage.removeItem('access_token');
    localStorage.removeItem('refresh_token');
    handleButtonClick();
  };

  const navToMypage = () => {
    navigate('/mypage');
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axiosInstance.get(`/members/info`);
        setRole('logined');
        console.log(response);
        console.log(response.data.data.name);
        setName(response.data.data.name);
      } catch (error) {
        setRole('notlogined');
        console.error('데이터 가져오기 오류:', error);
      }
    };

    fetchData();
  }, []);

  return (
    <div className="col-span-full">
      <div>
        <TapBar name="로고" />
      </div>
      {role !== 'logined' ? (
        <div className="h-[7rem] flex items-center justify-between border-y mb-[1rem]">
          <div className="text-[#999999] font-regular ">로그인이 필요합니다.</div>
          <button
            onClick={handleButtonClick}
            className="bg-[#F4F4F4] px-[0.5rem] py-[0.2rem] rounded-[0.3rem] text-[#555555] font-regular text-[0.813rem]"
          >
            로그인
          </button>
        </div>
      ) : (
        <div className="border-y mb-[1rem]">
          <button onClick={navToMypage} className="h-[7rem] flex items-center justify-between">
            <div className="text-Black font-bold text-[1.125rem]">{name}</div>
            <div className="ml-[2rem] mr-auto">
              <Arrow />
            </div>
          </button>
        </div>
      )}
      <div className="">
        <button className="font-bold text-[1.125rem] w-full my-[1rem] text-left">알림설정</button>
        <button className="font-bold text-[1.125rem] w-full my-[1rem] text-left">ABOUT</button>
        <button className="font-bold text-[1.125rem] w-full my-[1rem] text-left">약관 및 정책</button>
        <button className="font-bold text-[1.125rem] w-full my-[1rem] text-left">오픈소스 라이브러리</button>
        <button onClick={handleLogout} className="font-bold text-[1.125rem] w-full my-[1rem] text-left">
          로그아웃
        </button>
      </div>
    </div>
  );
}

export default About;
