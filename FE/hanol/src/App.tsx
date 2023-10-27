import { Routes, Route, useLocation } from 'react-router-dom';
import Login from './pages/auth/login';
import LoginError from './pages/auth/loginError';
import SignupBirth from 'pages/auth/signupBirth';
import SignupGender from 'pages/auth/signupGender';
import './App.css';
import Home from 'pages/home';
import SetRoutine from 'pages/routine/setRoutine';
import NavBar from 'components/common/NavBar';
import ExplainRoutine from 'pages/routine/explainRoutine';
import ExplainDiagnose from 'pages/diagnose/explainDiagnose';
import SelectDevice from 'pages/diagnose/selectDevice';
import Test from 'components/diagnosisResultPage/RecommendCareRoutine';

function App() {
  const location = useLocation();
  const showNavBarPaths = ['/examination', '/routine', '/', '/myreport', '/about', '/diagnose'];
  const shouldShowNavBar = showNavBarPaths.includes(location.pathname);
  return (
    <div className="App">
      <div className={`AppContent ${shouldShowNavBar ? 'show-navbar' : ''}`}>
        <div className="grid grid-cols-6 gap-[10px] mx-[23px]">
          <Routes>
            <Route path="/diagnose" element={<ExplainDiagnose />} />
            <Route path="/login" element={<Login />} />
            <Route path="/set-routine" element={<SetRoutine />} />
            <Route path="/" element={<Home />} />
            <Route path="/signup-birth" element={<SignupBirth />} />
            <Route path="/signup-gender" element={<SignupGender />} />
            <Route path="/myreport" element={<Home />} />
            <Route path="/examination" element={<Home />} />
            <Route path="/routine" element={<Home />} />
            <Route path="/about" element={<Home />} />
            <Route path="/login-error" element={<LoginError />} />
            <Route path="/explain-routine" element={<ExplainRoutine />} />
            <Route path="/select-device" element={<SelectDevice />} />
            <Route path="/test" element={<Test />} />
          </Routes>
        </div>
      </div>
      {shouldShowNavBar && <NavBar />}
    </div>
  );
}

export default App;
