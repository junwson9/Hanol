import React from 'react';
import styled from 'styled-components';

const DivisionRectangle = () => {
  return (
    <div>
      <Rectangle />
    </div>
  );
};

const Rectangle = styled.div`
  width: 100%;
  height: 0.688rem;
  flex-shrink: 0;

  background: #f6f6f6;
`;

export default DivisionRectangle;
