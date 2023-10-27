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
import Routine from 'pages/routine/routine';
import About from 'pages/about/about';

function App() {
  const location = useLocation();
  const showNavBarPaths = ['/set-routine', '/examination', '/routine', '/', '/myreport', '/about', '/explain-routine'];
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
            <Route path="/routine" element={<Routine />} />
            <Route path="/about" element={<About />} />
            <Route path="/login-error" element={<LoginError />} />
            <Route path="/explain-routine" element={<ExplainRoutine />} />
          </Routes>
        </div>
      </div>
      {shouldShowNavBar && <NavBar />}
    </div>
  );
}

export default App;
