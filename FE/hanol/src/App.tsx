import { Routes, Route, useLocation } from 'react-router-dom';
import './firebase-messaging-sw.js';
import Login from './pages/auth/login';
import LoginError from './pages/auth/loginError';
import SignupBirth from 'pages/auth/signupBirth';
import SignupGender from 'pages/auth/signupGender';
import './App.css';
import Home from 'pages/home';
import SetRoutine from 'pages/routine/setRoutine';
import NavBar from 'components/common/NavBar';
import ExplainRoutine from 'pages/routine/explainRoutine';
import Routine from 'pages/routine/routine';
import About from 'pages/about/about';
import ExplainDiagnose from 'pages/diagnose/explainDiagnose';
import SelectDevice from 'pages/diagnose/selectDevice';
import Streaming from 'pages/diagnose/streaming';
import IoTstreaming from 'pages/diagnose/IoTstreaming';
import Test from 'components/diagnosisResultPage/DiagnosisDetailToggle';
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

function App() {
  const location = useLocation();
  const showNavBarPaths = ['/examination', '/routine', '/', '/myreport', '/about', '/diagnose', '/explain-routine'];
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
            <Route path="/myreport" element={<Home />} />
            <Route path="/examination" element={<Examination />} />
            <Route path="/routine" element={<Routine />} />
            <Route path="/about" element={<About />} />
            <Route path="/explain-routine" element={<ExplainRoutine />} />
            <Route path="/select-device" element={<SelectDevice />} />
            <Route path="/streaming" element={<Streaming />} />
            <Route
              path="/test"
              element={
                <Test
                  diagnosisTitle="건강한 두피"
                  diagnosisContent="“두피 면역력 저하, 세균 감염, 물리적 자극, 화학약품에 의한 노출, 질병 등으로 인하여 발생되는 두피유형입니다. 지성 두피는 피지가 과도하게 분비된 상태가 특징입니다. 필요한 유분막의 양보다 많은 피지가 두피에 쌓이고 오염물들이 피지에 붙어있는 것이 문제가 되기 때문에, 이들을 제거하는 방향으로 두피관리를 해야 합니다. 피지 분비를 조절하는 성분이 들어있는 샴푸나 헤어 토너 등을 사용하고, 지성용 샴푸로 두피를 깨끗하게 씻어주시는 것이 좋습니다. 샴푸 브러시나 두피 마사지기를 사용해 혈액순환을 촉진하고 각질을 제거하는 것도 추천하는 방법입니다. ”"
                />
              }
            />
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
          </Routes>
        </div>
        <div>
          <Routes>
            <Route path="/login-error" element={<LoginError />} />
          </Routes>
        </div>
      </div>
      {shouldShowNavBar && <NavBar />}
    </div>
  );
}

export default App;
