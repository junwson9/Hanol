import React from 'react';
import styled from 'styled-components';
import ValueDiv from 'components/common/ValueDiv';

interface Props {
  valuenumber: number;
}

const DiagnosisDetailResult = ({ valuenumber }: Props) => {
  return (
    <div className="col-span-full">
      <AlopeciaDiagnosisBox>
        <div className="title">상세 진단</div>
        <DiagnosisBox>
          <div className="diagnosis">탈모 상태는</div>
          &nbsp;
          <ValueDiv valuenumber={valuenumber} />
          &nbsp;
          <div className="diagnosis">수준입니다</div>
        </DiagnosisBox>
      </AlopeciaDiagnosisBox>
    </div>
  );
};

const DiagnosisBox = styled.div`
  display: flex;
  margin-top: 1.438rem;
  align-self: center;
  align-items: center;

  .diagnosis {
    color: var(--Black, #252321);
    font-family: Noto Sans KR;
    font-size: 18px;
    font-weight: 700;
    line-height: 18px;
    letter-spacing: 0.2px;
  }
`;
const AlopeciaDiagnosisBox = styled.div`
  display: flex;
  flex-direction: column;
  align-items: start;

  margin-top: 1.438rem;

  .title {
    color: var(--Black, #252321);

    /* Body Header */
    font-family: Noto Sans KR;
    font-size: 18px;
    font-weight: 700;
    line-height: 18px;
    letter-spacing: 0.2px;
  }
`;
export default DiagnosisDetailResult;
