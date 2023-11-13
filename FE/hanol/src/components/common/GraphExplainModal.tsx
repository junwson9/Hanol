import React from 'react';
import styled from 'styled-components';
import explainImg from '../../assets/images/graph_explain_img.png';

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
  background-color: #f7f7f7;
  border-radius: 18px;
  width: 15.625rem;
  height: 15.625rem;
  padding: 0.938rem;

  position: absolute;
  z-index: 10;
  top: 12%;
  left: 18%;
`;

export default GraphExplainModal;
