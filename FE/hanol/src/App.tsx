import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from './pages/auth/login';
import './App.css';
import Home from 'pages/home';
import NavBar from 'components/common/NavBar';

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/myreport" element={<Login />} />
        <Route path="/examination" element={<Home />} />
        <Route path="/routine" element={<Home />} />
        <Route path="/about" element={<Home />} />
      </Routes>
      <NavBar />
    </div>
  );
}

export default App;
