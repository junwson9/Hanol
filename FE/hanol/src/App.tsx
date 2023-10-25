import { Routes, Route } from 'react-router-dom';
import Login from './pages/auth/login';
import LoginError from './pages/auth/loginError';
import SignupBirth from 'pages/auth/signupBirth';
import SignupGender from 'pages/auth/signupGender';
import './App.css';
import Home from 'pages/home';
import SetRoutine from 'pages/routine/setRoutine';
import NavBar from 'components/common/NavBar';

function App() {
  return (
    <div className="App">
      <div className="AppContent">
        <div className="grid grid-cols-6 gap-[10px] mx-[23px]">
          <Routes>
            <Route path="/login" element={<Login />} />
            <Route path="/set-routine" element={<SetRoutine />} />
          </Routes>
        </div>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/signup-birth" element={<SignupBirth />} />
          <Route path="/signup-gender" element={<SignupGender />} />
          <Route path="/myreport" element={<Login />} />
          <Route path="/examination" element={<Home />} />
          <Route path="/routine" element={<Home />} />
          <Route path="/about" element={<Home />} />
          <Route path="/login-error" element={<LoginError />} />
        </Routes>
      </div>
      <NavBar />
    </div>
  );
}

export default App;
