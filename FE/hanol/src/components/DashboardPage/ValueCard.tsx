import React from 'react';
import styled from 'styled-components';
import ValueDiv from 'components/common/ValueDiv';

interface Props {
  title: string;
  value?: number;
  onClick?: (arg: number) => void;
  isSelected?: boolean;
}

const ValueCard = ({ title, value, onClick, isSelected }: Props) => {
  console.log('isselected:', isSelected, 'title', title);
  return (
    <ValueCardBox
      onClick={() => onClick?.(0)}
      style={isSelected ? { boxShadow: '0px 4px 4px 0px rgba(0, 0, 0, 0.10) inset' } : {}}
    >
      <div className="value_title">{title}</div>
      <ValueDiv valuenumber={value} />
    </ValueCardBox>
  );
};

const ValueCardBox = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 30%;
  height: 100%;
  flex-shrink: 0;
  padding: 0.75rem 0.75rem;
  border-radius: 18px;
  border: 0.2px solid rgba(188, 188, 188, 0.5);
  opacity: 0.76;
  background: var(--white, #fffeff);
  cursor: pointer;

  .value_title {
    display: flex;
    width: 37px;
    height: 17px;
    flex-direction: column;
    justify-content: center;
    flex-shrink: 0;
    margin-bottom: 0.625rem;

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
