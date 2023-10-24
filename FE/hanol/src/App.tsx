import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import ValueCard from './components/DashboardPage/ValueCard';
import ValueGraph from './components/DashboardPage/ValueGraph';
import './App.css';

function App() {
	return (
		<BrowserRouter>
			<Routes>
				<Route path="/" element={<ValueCard title="탈모" value={2} />} />
				<Route path="/graph" element={<ValueGraph />} />
			</Routes>
		</BrowserRouter>
	);
import { Routes, Route } from 'react-router-dom';
import Login from './pages/auth/login';
import LoginError from './pages/auth/loginError';
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
          <Route path="/login" element={<Login />} />
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
