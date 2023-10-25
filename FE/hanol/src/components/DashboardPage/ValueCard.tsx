import React from 'react';
// import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

interface Props {
	title: string;
	value: number;
}

interface ValueColorProps {
	textColor: string;
	backgroundColor: string;
}

const ValueCard = ({ title, value }: Props) => {
	const determineValueTitle = (value: number) => {
		switch (value) {
			case 0:
				return '우수';
			case 1:
				return '양호';
			case 2:
				return '주의';
			case 3:
				return '위험';
			default:
				return '정도';
		}
	};
	const determineTextColor = (value: number) => {
		switch (value) {
			// 우수
			case 0:
				return '#5BC3C4';
			case 1:
				return '#6BE464';
			case 2:
				return '#FBDE48';
			case 3:
				return '#EA536F';
			default:
				return 'white';
		}
	};
	const determineBackgroundColor = (value: number) => {
		switch (value) {
			// 우수
			case 0:
				return '#F2FEFE';
			case 1:
				return '#F1FEF3';
			case 2:
				return '#FEF9E2';
			case 3:
				return '#FDEFF1';
			default:
				return 'white';
		}
	};
	const textColor = determineTextColor(value);
	const backgroundColor = determineBackgroundColor(value);

	return (
		<ValueCardBox>
			<div className="value_title">{title}</div>
			<ValueBox textColor={textColor} backgroundColor={backgroundColor}>
				<div className="value">{determineValueTitle(value)}</div>
			</ValueBox>
		</ValueCardBox>
	);
};

const ValueBox = styled.div<ValueColorProps>`
	display: flex;
	justify-content: center;

	width: 50px;
	height: 35px;
	flex-shrink: 0;
	/* background-color: #f2fefe; */
	background-color: ${(props) => props.backgroundColor};
	border-radius: 20px;

	.value {
		width: 37px;
		height: 24px;
		flex-shrink: 0;

		/* color: #5bc3c4; */
		color: ${(props) => props.textColor};
		text-align: center;

		/* Body Header */
		font-family: Noto Sans KR;
		font-size: 18px;
		font-style: normal;
		font-weight: 700;
		line-height: 35px;
		letter-spacing: 0.2px;
	}
`;
const ValueCardBox = styled.div`
	display: flex;
	flex-direction: column;
	justify-content: space-evenly;
	align-items: center;
	/* 후에 수정 */
	width: 98px;
	height: 80px;
	flex-shrink: 0;

	border-radius: 18px;
	border: 0.2px solid var(--GrayForTab, #bcbcbc);
	opacity: 0.76;
	background: var(--white, #fffeff);

	.value_title {
		display: flex;
		width: 37px;
		height: 17px;
		flex-direction: column;
		justify-content: center;
		flex-shrink: 0;

		color: var(--Black, #252321);
		text-align: center;

		/* PowerBody 1 */
		font-family: Noto Sans KR;
		font-size: 16px;
		font-style: normal;
		font-weight: 500;
		line-height: 140%; /* 22.4px */
		letter-spacing: 0.1px;
	}
`;

export default ValueCard;
