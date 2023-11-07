import { Routes, Route, useLocation } from 'react-router-dom';
import './firebase-messaging-sw.js';
import Login from './pages/auth/login';
import LoginError from './pages/auth/loginError';
import SignupBirth from 'pages/auth/signupBirth';
import SignupGender from 'pages/auth/signupGender';
import './App.css';
import Home from 'pages/myreport/Myreport';
import SetRoutine from 'pages/routine/setRoutine';
import NavBar from 'components/common/NavBar';
import ExplainRoutine from 'pages/routine/explainRoutine';
import Routine from 'pages/routine/routine';
import About from 'pages/about/about';
import ExplainDiagnose from 'pages/diagnose/explainDiagnose';
import SelectDevice from 'pages/diagnose/selectDevice';
import Streaming from 'pages/diagnose/streaming';
import IoTstreaming from 'pages/diagnose/IoTstreaming';
import Test from 'components/diagnosisResultPage/ResultSender';
import MyPage from 'pages/about/mypage';
import Dashboard from 'pages/myreport/MyreportDashBoard';
import MyDetail from 'pages/myreport/MyreportDetailPage';
import DiagnosisDetail from 'pages/diagnose/diagnosisDetail';
import Analyzing from 'pages/diagnose/analyzingPage';
import SelectPart from 'pages/diagnose/selectPart';
import AboutNotiSetting from 'pages/about/aboutNotiSetting';
import LoginDone from 'pages/auth/loginDone';
import SetScalpTI1 from 'pages/scalpti/SetScalpTI';
import SetScalpTI2 from 'pages/scalpti/SetScalpTI2';
import SetScalpTI3 from 'pages/scalpti/SetScalpTI3';
import SetScalpTI4 from 'pages/scalpti/SetScalpTI4';
import SetScalpTI5 from 'pages/scalpti/SetScalpTI5';
import SetScalpTI6 from 'pages/scalpti/SetScalpTI6';
import SetScalpTI7 from 'pages/scalpti/SetScalpTI7';
import SetScalpDone from 'pages/scalpti/SetScalpDone';
import Examination from 'pages/scalpti/examination';
import RoutineNotiSetting from 'pages/routine/routineNotiSetting';

function App() {
  const location = useLocation();
  const showNavBarPaths = [
    '/examination',
    '/routine',
    '/',
    '/myreport',
    '/about',
    '/diagnose',
    '/explain-routine',
    '/dashboard',
  ];
  const shouldShowNavBar = showNavBarPaths.includes(location.pathname);
  return (
    <div className="App">
      <div className={`AppContent ${shouldShowNavBar ? 'show-navbar' : ''}`}>
        <div className="grid grid-cols-6 gap-[10px] mx-[23px]">
          <Routes>
            <Route path="/diagnose" element={<ExplainDiagnose />} />
            <Route path="/login" element={<Login />} />
            <Route path="/login-done" element={<LoginDone />} />
            <Route path="/set-routine" element={<SetRoutine />} />
            <Route path="/" element={<Home />} />
            <Route path="/signup-birth" element={<SignupBirth />} />
            <Route path="/signup-gender" element={<SignupGender />} />
            <Route path="/examination" element={<Examination />} />
            <Route path="/routine" element={<Routine />} />
            <Route path="/about" element={<About />} />
            <Route path="/explain-routine" element={<ExplainRoutine />} />
            <Route path="/select-device" element={<SelectDevice />} />
            <Route path="/streaming" element={<Streaming />} />
            <Route path="/test" element={<Test />} />
            <Route path="/mypage" element={<MyPage />} />
            <Route path="/IoTstreaming" element={<IoTstreaming />} />
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/mydetail" element={<MyDetail />} />
            <Route path="/diagnosis" element={<DiagnosisDetail />} />
            <Route path="/analyzing" element={<Analyzing />} />
            <Route path="/select-part" element={<SelectPart />} />
            <Route path="/about-noti-setting" element={<AboutNotiSetting />} />
            <Route path="/set-scalpti1" element={<SetScalpTI1 />} />
            <Route path="/set-scalpti2" element={<SetScalpTI2 />} />
            <Route path="/set-scalpti3" element={<SetScalpTI3 />} />
            <Route path="/set-scalpti4" element={<SetScalpTI4 />} />
            <Route path="/set-scalpti5" element={<SetScalpTI5 />} />
            <Route path="/set-scalpti6" element={<SetScalpTI6 />} />
            <Route path="/set-scalpti7" element={<SetScalpTI7 />} />
            <Route path="/set-scalp-done" element={<SetScalpDone />} />
            <Route path="/routine-noti-setting/:member_routine_id" element={<RoutineNotiSetting />} />
          </Routes>
        </div>
        <div>
          <Routes>
            <Route path="/login-error" element={<LoginError />} />
            <Route path="/myreport" element={<Home />} />
          </Routes>
        </div>
      </div>
      {shouldShowNavBar && <NavBar />}
    </div>
  );
}

export default App;
