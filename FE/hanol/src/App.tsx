import React from 'react';
// import { BrowserRouter, Routes, Route } from 'react-router-dom';
// import ValueCard from './components/DashboardPage/ValueCard';
// import ValueGraph from './components/DashboardPage/ValueGraph';
// import './App.css';

// function App() {
// 	return (
// 		<BrowserRouter>
// 			<Routes>
// 				<Route path="/" element={<ValueCard title="탈모" value={2} />} />
// 				<Route path="/graph" element={<ValueGraph />} />
// 			</Routes>
// 		</BrowserRouter>
// 	);
import { Routes, Route } from 'react-router-dom';
import Login from './pages/auth/login';
import ValueCardNonmember from './components/DashboardPage/ValueCardNonmember';
import './App.css';

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/login" element={<Login />} />
      </Routes>
      <Routes>
        <Route path="/test" element={<ValueCardNonmember title="피지" />} />
      </Routes>
    </div>
  );
}

export default App;
