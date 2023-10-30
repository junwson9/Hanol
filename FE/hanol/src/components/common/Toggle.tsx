import React, { useState } from 'react';
import styled from 'styled-components';

const Toggle = () => {
  const [isToggleOn, setIsToggleOn] = useState(false);

  const toggleHandler = () => {
    setIsToggleOn((prev) => !prev);
  };

  return (
    <div className="col-span-full">
      <ToggleBox>
        <ToggleBackground onClick={toggleHandler} className={`${isToggleOn ? 'on_background' : 'off_background'}`}>
          <ToggleSwitch className={`${isToggleOn ? 'on_switch' : 'off_switch'}`} />
        </ToggleBackground>
      </ToggleBox>
    </div>
  );
};

const ToggleBox = styled.div`
  display: flex;
  margin-top: 0.625rem;

  .on_background {
    background-color: #3fcc8a;
  }

  .off_background {
    background-color: #bcbcbc;
  }

  .on_switch {
    left: 1.563rem;
  }

  .off_switch {
  }
`;
const ToggleBackground = styled.div`
  position: relative;
  width: 3.125rem;
  height: 1.563rem;
  border-radius: 50px;

  cursor: pointer;

  transition: all 0.2s ease-in-out;
`;
const ToggleSwitch = styled.div`
  width: 1rem;
  height: 1rem;
  background-color: #fffeff;
  border-radius: 50px;

  margin: 0 0.313rem;
  position: absolute;
  top: 0.281rem;
`;
export default Toggle;
