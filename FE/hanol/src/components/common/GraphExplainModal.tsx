import React from 'react';
import styled from 'styled-components';
import explainImg from '../../assets/images/graph_explain_img_white.png';

const GraphExplainModal = () => {
  return (
    <div className="col-span-full">
      <ModalBox>
        <img src={explainImg} alt="" className="explain_img" />
      </ModalBox>
    </div>
  );
};

const ModalBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #fffeff;
  border: 0.2px solid rgba(188, 188, 188, 0.5);
  border-radius: 18px;
  width: 15.625rem;
  height: 15.625rem;
  padding: 0.938rem;

  position: absolute;
  z-index: 10;
  top: 11.5%;
  left: 17.7%;
`;

export default GraphExplainModal;
