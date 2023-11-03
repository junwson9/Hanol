import { ReactComponent as IconKakaoLogin } from '../../assets/rescureImg/KakaoLogin.svg';
import axios from 'axios';
import KakaoLogin from 'react-kakao-login';
import { useNavigate } from 'react-router-dom';

function Login() {
  const kakaoID = process.env.REACT_APP_KAKAO_CLIENT_ID;
  const APP_URI = process.env.REACT_APP_API_URL;
  const navigate = useNavigate();
  console.log(kakaoID);
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
          navigate('/signup-birth');
        } else {
          navigate('/');
        }
      })
      // eslint-disable-next-line
      .catch((error: any) => {
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
        <div>
          <div className="mt-[105px] font-bold text-[18px] text-black flex">모발의 모든것</div>
          <div className=" font-bold text-[18px] text-black flex">한올에서 간편하게</div>
          <div className="mt-[12px] font-regular text-[12px] text-GrayForText flex">
            더욱 풍성해질 당신을 항상 응원합니다.
          </div>
        </div>
        <div className="flex justify-center mb-[50px]">
          <KakaoLogin
            token={kakaoID || ''}
            onSuccess={kakaoSuccessHandler}
            onFail={kakaoFailHandler}
            render={(props) => (
              <div>
                {/* <button onClick={props.onClick}>카카오 로그인</button> */}
                <IconKakaoLogin onClick={props.onClick} />
              </div>
            )}
          />
        </div>
      </div>
    </>
  );
}

export default Login;
