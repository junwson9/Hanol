import React from 'react';
import styled from 'styled-components';
import DiagnosisDetailToggle from './DiagnosisDetailToggle';

// interface Props {
//   valuenumber: number;
// }

const DiagnosisDetailResult = () => {
  return (
    <div className="col-span-full">
      <AlopeciaDiagnosisBox>
        <div className="title">상세 진단</div>
        <DiagnosisBox>
          <DiagnosisDetailToggle
            diagnosisTitle="건강한 두피"
            diagnosisContent="“두피 면역력 저하, 세균 감염, 물리적 자극, 화학약품에 의한 노출, 질병 등으로 인하여 발생되는 두피유형입니다. 지성 두피는 피지가 과도하게 분비된 상태가 특징입니다. 필요한 유분막의 양보다 많은 피지가 두피에 쌓이고 오염물들이 피지에 붙어있는 것이 문제가 되기 때문에, 이들을 제거하는 방향으로 두피관리를 해야 합니다. 피지 분비를 조절하는 성분이 들어있는 샴푸나 헤어 토너 등을 사용하고, 지성용 샴푸로 두피를 깨끗하게 씻어주시는 것이 좋습니다. 샴푸 브러시나 두피 마사지기를 사용해 혈액순환을 촉진하고 각질을 제거하는 것도 추천하는 방법입니다. ”"
          />
          <DiagnosisDetailToggle
            diagnosisTitle="건강한 두피"
            diagnosisContent="“두피 면역력 저하, 세균 감염, 물리적 자극, 화학약품에 의한 노출, 질병 등으로 인하여 발생되는 두피유형입니다. 지성 두피는 피지가 과도하게 분비된 상태가 특징입니다. 필요한 유분막의 양보다 많은 피지가 두피에 쌓이고 오염물들이 피지에 붙어있는 것이 문제가 되기 때문에, 이들을 제거하는 방향으로 두피관리를 해야 합니다. 피지 분비를 조절하는 성분이 들어있는 샴푸나 헤어 토너 등을 사용하고, 지성용 샴푸로 두피를 깨끗하게 씻어주시는 것이 좋습니다. 샴푸 브러시나 두피 마사지기를 사용해 혈액순환을 촉진하고 각질을 제거하는 것도 추천하는 방법입니다. ”"
          />
        </DiagnosisBox>
      </AlopeciaDiagnosisBox>
    </div>
  );
};

const DiagnosisBox = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-top: 0.5rem;
`;
const AlopeciaDiagnosisBox = styled.div`
  display: flex;
  flex-direction: column;
  align-items: start;

  margin-top: 1.563rem;

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
