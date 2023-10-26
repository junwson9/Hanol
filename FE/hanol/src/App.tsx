import { Routes, Route, useLocation } from 'react-router-dom';
import Login from './pages/auth/login';
import LoginError from './pages/auth/loginError';
import SignupBirth from 'pages/auth/signupBirth';
import SignupGender from 'pages/auth/signupGender';
import './App.css';
import Home from 'pages/home';
import SetRoutine from 'pages/routine/setRoutine';
import NavBar from 'components/common/NavBar';
import Test from 'components/DetailPage/ScalpView';

function App() {
  const location = useLocation();
  const showNavBarPaths = ['/examination', '/routine', '/', '/myreport', '/about'];
  const shouldShowNavBar = showNavBarPaths.includes(location.pathname);
  return (
    <div className="App">
      <div className={`AppContent ${shouldShowNavBar ? 'show-navbar' : ''}`}>
        <div className="grid grid-cols-6 gap-[10px] mx-[23px]">
          <Routes>
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
            <Route
              path="/test"
              element={
                <Test
                  sub_title="측두부"
                  scalp_img="https://mblogthumb-phinf.pstatic.net/MjAxNjExMTRfMTc4/MDAxNDc5MTEyODUxNjI2.Pvg3xVHws8c7qDGdpu68yjks3XJ8ylE4mGPINRE-5fkg.N5DqquF6To5sWd5adTdriUHfQo1C-7ZA15ZOKtUoHpsg.JPEG.forblisscm/%EA%B8%B0%EC%9A%B1%EB%91%90%ED%94%BC%28%EA%B0%90%EA%B8%B0%EC%A0%84%29.jpg?type=w800"
                />
              }
            />
          </Routes>
        </div>
      </div>
      {shouldShowNavBar && <NavBar />}
    </div>
  );
}

export default App;
