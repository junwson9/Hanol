import TapBar from 'components/common/TopBar';
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axiosInstance from 'api/axiosInterceptor';
// import { ReactComponent as Arrow } from '../../assets/icons/arrow_right.svg';
import { getMessaging, getToken } from 'firebase/messaging';
import { MemberRoleState } from 'recoil/atoms';
import { useRecoilState } from 'recoil';
import Logo from '../../assets/images/Hanol_Logo.png';

function About() {
  const navigate = useNavigate();
  const [role, setRole] = useState<string | null>(null);
  const [name, setName] = useState<string | null>(null);
  const [email, setEmail] = useState<string | null>(null);
  const [memberRole, setMemberRole] = useRecoilState(MemberRoleState);
  const handleButtonClick = () => {
    navigate('/');
  };
  const handleloginClick = () => {
    navigate('/login');
  };
  const handleLogout = async () => {
    try {
      const messaging = getMessaging();

      // Check if the browser supports notifications
      if (messaging && Notification.permission === 'granted') {
        const token = await getToken(messaging, {
          vapidKey: process.env.REACT_APP_VAPID_KEY,
        });

        // If the user has granted notification permissions, send the FCM token
        if (token) {
          await axiosInstance.patch(`/members/logout`, { fcm_token: token });
        }
      }

      localStorage.removeItem('access_token');
      localStorage.removeItem('refresh_token');
      setMemberRole('GUEST');
      handleButtonClick();
    } catch (error) {
      console.error('데이터 가져오기 오류:', error);
    }
  };

  const handleToSurvey = () => {
    window.open('https://naver.me/xVAGzMK5', '_blank');
  };

  const handleToAbout = () => {
    window.open('https://acoustic-epoch-1b4.notion.site/HANOL-d428de0d90a841a29b8f484041d51179?pvs=4', '_blank');
  };

  const handlenotisettings = () => {
    navigate('/about-noti-setting');
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axiosInstance.get(`/members/info`);
        setRole('logined');
        setName(response.data.data.name);
        setEmail(response.data.data.email);
      } catch (error) {
        setRole('notlogined');
        console.error(error);
      }
    };
    if (memberRole != 'GUEST') {
      fetchData();
    }
  }, []);

  return (
    <div className="col-span-full">
      <div>
        <TapBar logo={<img src={Logo} alt="로고" />} />
      </div>
      {role !== 'logined' ? (
        <div className="h-[7rem] flex items-center justify-between border-y mb-[1rem]">
          <div className="text-[#999999] font-regular ">로그인이 필요합니다</div>
          <button
            onClick={handleloginClick}
            className="bg-[#F4F4F4] px-[0.5rem] py-[0.2rem] rounded-[0.3rem] text-[#555555] font-regular text-[0.813rem]"
          >
            로그인
          </button>
        </div>
      ) : (
        <div className="border-y mb-[1rem]">
          <div className="flex h-[7rem] items-center justify-between">
            <div className="text-Black font-bold text-left text-[1.125rem]">
              {name}
              <div className="font-regular text-left mt-[0.2rem] text-[12px] text-[#999999]">{email}</div>
            </div>
            {/* <div className="ml-[2rem] mr-auto">
              <Arrow />
            </div> */}
          </div>
        </div>
      )}
      <div className="">
        {role === 'logined' && (
          <button onClick={handlenotisettings} className="font-bold text-[1.125rem] w-full my-[1rem] text-left">
            알림설정
          </button>
        )}
        <button className="font-bold text-[1.125rem] w-full my-[1rem] text-left" onClick={handleToAbout}>
          ABOUT
        </button>

        <button className="font-bold text-[1.125rem] text-Main w-full my-[1rem] text-left" onClick={handleToSurvey}>
          설문조사 참여하고 커피 받기
        </button>
        {role === 'logined' && (
          <button onClick={handleLogout} className="font-bold text-[1.125rem] w-full my-[1rem] text-left">
            로그아웃
          </button>
        )}
      </div>
    </div>
  );
}

export default About;
