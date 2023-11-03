import { initializeApp } from 'firebase/app';
import { getMessaging, getToken, onMessage } from 'firebase/messaging';
import axiosInstance from 'api/axiosInterceptor';
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: process.env.REACT_APP_API_KEY,
  authDomain: 'hanol-project.firebaseapp.com',
  projectId: 'hanol-project',
  storageBucket: 'hanol-project.appspot.com',
  messagingSenderId: process.env.REACT_APP_MESSAGING_SENDER_ID,
  appId: process.env.REACT_APP_APP_ID,
  measurementId: process.env.REACT_APP_MEASUREMENT_ID,
};

const app = initializeApp(firebaseConfig);
const messaging = getMessaging(app);

async function requestPermission() {
  console.log('권한 요청 중...');

  const permission = await Notification.requestPermission();
  if (permission === 'denied') {
    console.log('알림 권한 허용 안됨');
    return;
  }

  console.log('알림 권한이 허용됨');

  const token = await getToken(messaging, {
    vapidKey: process.env.REACT_APP_VAPID_KEY,
  });

  if (token) console.log('token: ', token);
  else console.log('Can not get Token');

  onMessage(messaging, (payload) => {
    console.log('메시지가 도착했습니다.', payload);
    // ...
  });
}

requestPermission();

async function sendTokenToServer() {
  const token = await getToken(messaging, {
    vapidKey: process.env.REACT_APP_VAPID_KEY,
  });

  // 사용자가 로그인한 후, 해당 토큰을 서버로 전송하고 서버에서 필요한 처리를 진행합니다.
  if (token) {
    // 토큰을 서버로 전송하기 위해 Axios나 fetch 등을 사용할 수 있습니다.
    try {
      const response = await axiosInstance.post('/notifications/token', { fcm_token: token });
      console.log('토큰을 서버로 전송했습니다.', response.data);
    } catch (error) {
      console.error('토큰을 서버로 전송하는 중 에러 발생:', error);
    }
  } else {
    console.log('토큰을 가져오지 못했습니다.');
  }
}

sendTokenToServer();
