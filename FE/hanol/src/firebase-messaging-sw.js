import { initializeApp } from 'firebase/app';
// import { getMessaging, getToken, onMessage } from 'firebase/messaging';
import { getMessaging, onMessage } from 'firebase/messaging';
// import axiosInstance from 'api/axiosInterceptor';
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
  // console.log('알림 권한 요청 중...');

  const permission = await Notification.requestPermission();
  if (permission === 'denied') {
    // console.log('알림 권한 허용 안됨');
    return;
  }

  // console.log('알림 권한이 허용됨');

  onMessage(messaging, (payload) => {
    console.log('메시지가 도착했습니다.', payload);
    // ...
  });
}

requestPermission();
