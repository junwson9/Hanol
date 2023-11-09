import { ReactComponent as IconKakaoLogin } from '../../assets/rescureImg/KakaoLogin.svg';
import axios from 'axios';
import axiosInstance from 'api/axiosInterceptor';
import KakaoLogin from 'react-kakao-login';
import { useNavigate } from 'react-router-dom';
import { getMessaging, getToken } from 'firebase/messaging';
import { MemberRoleState } from 'recoil/atoms';
import { useSetRecoilState } from 'recoil';
import { ReactComponent as HanolLogo } from '../../assets/icons/hanolLogo.svg';
function Login() {
  const kakaoID = process.env.REACT_APP_KAKAO_CLIENT_ID;
  const APP_URI = process.env.REACT_APP_API_URL;
  const navigate = useNavigate();
  console.log(kakaoID);
  const setMemberRole = useSetRecoilState(MemberRoleState);
  // FCM 토큰 관련
  // eslint-disable-next-line
  async function sendTokenToServer(messaging: any) {
    const token = await getToken(messaging, {
      vapidKey: process.env.REACT_APP_VAPID_KEY,
    });

    if (token) {
      console.log('token: ', token);
      try {
        const response = await axiosInstance.post('/notifications/token', { fcm_token: token });
        console.log('로그인 후 토큰을 서버로 전송했습니다.', response.data);
      } catch (error) {
        console.error('로그인 후 토큰을 서버로 전송하는 중 에러 발생:', error);
      }
    } else {
      console.log('토큰을 가져오지 못했습니다.');
    }
  }
  // eslint-disable-next-line
  const kakaoSuccessHandler = (data: any) => {
    const requestData = {
      id_token: data.response.id_token,
      oauth_provider: 'KAKAO',
    };
    console.log(requestData);
    axios
      .post<{ access_token: string; refresh_token: string }>(`${APP_URI}/members/oauth`, requestData)
      // eslint-disable-next-line
      .then((response: any) => {
        const { access_token, refresh_token } = response.data.data;
        console.log('data', response.data.data);
        localStorage.setItem('access_token', access_token);
        localStorage.setItem('refresh_token', refresh_token);
        const role = response.data.data.role;
        if (role == 'GUEST') {
          setMemberRole(role);
          navigate('/signup-birth');
          //FCM 토큰 관련
          const messaging = getMessaging();
          sendTokenToServer(messaging);
        } else {
          setMemberRole(role);
          navigate('/');
          //FCM 토큰 관련
          const messaging = getMessaging();
          sendTokenToServer(messaging);
        }
      })

      .catch((error) => {
        console.error('로그인 또는 회원가입에 실패했습니다.', error);
      });
  };
  // eslint-disable-next-line
  const kakaoFailHandler = (err: any) => {
    alert('카카오 로그인 실패');
    console.error(err);
  };
  return (
    <>
      <div className="col-span-full h-screen flex flex-col justify-between">
        <div className="">
          <div className="mt-[105px] font-bold text-[18px] text-black flex">모발의 모든것</div>
          <div className=" font-bold text-[18px] text-black flex">한올에서 간편하게</div>
          <div className="mt-[12px] font-regular text-[12px] text-GrayForText flex">
            더욱 풍성해질 당신을 항상 응원합니다.
          </div>
        </div>
        <div className="flex justify-center">
          <HanolLogo />
        </div>
        <div className="mb-[9rem]">
          <KakaoLogin
            token={kakaoID || ''}
            onSuccess={kakaoSuccessHandler}
            onFail={kakaoFailHandler}
            render={(props) => (
              <div className="flex justify-center">
                <IconKakaoLogin onClick={props.onClick} />
              </div>
            )}
          />
          <div className="mt-[3rem] flex justify-center">
            <button
              className="font-medium text-left mb-[1rem] items-center text-GrayForText underline"
              onClick={() => navigate('/')}
            >
              비회원으로 둘러 보기
            </button>
          </div>
        </div>
      </div>
    </>
  );
}

export default Login;
