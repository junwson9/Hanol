import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from './pages/auth/login';
import LoginError from './pages/auth/loginError';
import './App.css';

function App() {
  return (
    <div className="App">
      <div className="grid grid-cols-6 gap-[10px] mx-[23px]">
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/login-error" element={<LoginError />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
