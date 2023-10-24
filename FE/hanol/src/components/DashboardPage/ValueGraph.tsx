import React from 'react';
import styled from 'styled-components';
import {
	Chart as ChartJS,
	CategoryScale,
	LinearScale,
	BarElement,
	Title,
	Tooltip,
	Legend,
	PointElement,
	LineElement,
} from 'chart.js';

import { Line } from 'react-chartjs-2';

ChartJS.register(
	CategoryScale,
	LinearScale,
	BarElement,
	Title,
	Tooltip,
	Legend,
	PointElement,
	LineElement,
);
const ValueGraph = () => {
	// 그래프 관련
	const options = {
		responsive: true,
		plugins: {
			legend: {
				position: 'top' as const,
				// display: false,
				labels: {
					font: {
						size: 10,
						family: 'Pretendard',
					},
				},
			},
			title: {
				display: true,
				// text: 'Chart.js Bar Chart',
			},
		},
		elements: {
			point: {
				radius: 3,
			},
		},
		scales: {
			y: {
				display: false,
			},
		},
	};

	const data = {
		labels: [
			'4주 전',
			'3주 전',
			'2주 전',
			'1주 전',
			'4주 전',
			'3주 전',
			'2주 전',
			'1주 전',
			'4주 전',
			'3주 전',
			'2주 전',
			'1주 전',
		],
		datasets: [
			{
				label: '관심 아티스트 등록 수 (명)',
				data: [
					123403, 123603, 125079, 126030, 123403, 123603, 125079, 126030,
					123403, 123603, 125079, 126030,
				],
				// data: artistLikesCountperWeek.reverse().map((data) => data.scrapCount),
				borderColor: '#3061B9',
				backgroundColor: '#3061B9',
			},
		],
	};

	return (
		<ValueGraphBox>
			<Line options={options} data={data} />
		</ValueGraphBox>
	);
};

const ValueGraphBox = styled.div`
	// 추후 수정
	/* width: 314px; */
	width: auto;
	height: 268px;
	margin: 0 auto;
	/* overflow-x: auto; */
`;
export default ValueGraph;
