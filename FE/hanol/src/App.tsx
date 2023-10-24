import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import TopTab from './components/common/TopTab';
import './App.css';

function App() {
	return (
		<BrowserRouter>
			<Routes>
				<Route path="/" element={<TopTab Indicator="one" />} />
			</Routes>
		</BrowserRouter>
	);
}

export default App;
