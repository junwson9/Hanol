import TopBarDepth2 from 'components/common/TapBarDepth2';
import { useNavigate } from 'react-router-dom';
import axiosInstance from 'api/axiosInterceptor';
import { useEffect } from 'react';
import { useState } from 'react';

function MyPage() {
  const navigate = useNavigate();
  const [userInfo, setUserInfo] = useState({ name: '', gender: '', birth: '', email: '' });
  const handleCloseClick = () => {
    navigate(-1);
  };
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axiosInstance.get(`/members/info`);
        setUserInfo(response.data.data);
      } catch (error) {
        console.error('데이터 가져오기 오류:', error);
      }
    };

    fetchData();
  }, []);

  return (
    <div className="col-span-full h-screen flex flex-col justify-between">
      <div>
        <TopBarDepth2 name={'마이페이지'} onClick={handleCloseClick} propsIsBack={false} />
      </div>
      <div className="items-center text-left ml-[6rem]">
        <div className="text-[1rem] font-medium mb-[2rem]">이름 : {userInfo.name}</div>
        <div className="text-[1rem] font-medium mb-[2rem]">성별 : {userInfo.gender}</div>
        <div className="text-[1rem] font-medium mb-[2rem]">생년월일 : {userInfo.birth}</div>
        <div className="text-[1rem] font-medium mb-[2rem]">email : {userInfo.email}</div>
      </div>
      <div>
        <button className="font-medium text-left mb-[6rem] items-center text-GrayForText underline">회원 탈퇴</button>
      </div>
    </div>
  );
}
export default MyPage;
