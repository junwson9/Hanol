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
}

export default App;
