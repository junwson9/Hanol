import axios, { AxiosInstance } from 'axios';
import { useNavigate } from 'react-router-dom';

const API_URL = process.env.REACT_APP_API_URL;

const axiosInstance: AxiosInstance = axios.create({
  baseURL: `${API_URL}`,
  withCredentials: true,
});

const reIssuedToken = async () => {
  try {
    const refresh_token = localStorage.getItem('refresh_token'); // refresh_token 가져오기
    console.log('reissue 드가자!!!!!!!!');
    if (!refresh_token) {
      // refresh_token이 없을 경우 처리
      console.log('refresh_token이 없습니다.');
      const navigate = useNavigate();
      navigate('/login-error');
      return null;
    }

    const response = await axiosInstance.post('/members/reissue', {
      refresh_token: refresh_token, // refresh_token을 요청의 본문에 추가
    });
    console.log(response);
    localStorage.setItem('access_token', response.data.data.access_token);
    return localStorage.getItem('access_token'); // 재발급받은 access_token 반환
  } catch (e) {
    console.log('reissue실패');
    console.log(e);
    localStorage.removeItem('access_token');
    localStorage.removeItem('refresh_token');
    const navigate = useNavigate();

    navigate('/login-error');
  }
};

axiosInstance.interceptors.response.use(
  // 정상 응답 처리
  (response) => {
    // console.log(response);
    return response;
  },
  // 에러 처리
  async (error) => {
    const { config, response } = error;
    // console.log(error.message);
    // 토큰 자동 재발급 필요 외 다른 에러
    console.log('에러발생 ============================');

    if (config.url !== '/members/reissue' && response && response.status === 401) {
      console.log('재발급요청하기 ============================');
      const access_token = await reIssuedToken();

      config.headers.Authorization = `Bearer ${access_token}`; // 헤더에 넣어서

      return axiosInstance(config); // 다시 요청
    } else {
      const navigate = useNavigate();

      navigate('/login-error');
    }
    return Promise.reject(error);
  },
);

// Request Interceptor
axiosInstance.interceptors.request.use(
  (config) => {
    const access_token = localStorage.getItem('access_token');
    // console.log(access_token);
    // console.log(config);
    if (access_token) {
      config.headers.Authorization = `Bearer ${access_token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  },
);

export default axiosInstance;
