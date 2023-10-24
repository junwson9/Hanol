import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import ValueCard from './components/DashboardPage/ValueCard';
import './App.css';

function App() {
	return (
		<BrowserRouter>
			<Routes>
				<Route path="/" element={<ValueCard title="탈모" value={2} />} />
			</Routes>
		</BrowserRouter>
	);
}

export default App;
