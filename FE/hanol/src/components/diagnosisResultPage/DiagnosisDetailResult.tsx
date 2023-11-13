import React from 'react';
import styled from 'styled-components';
import DiagnosisDetailToggle from './DiagnosisDetailToggle';

interface Props {
  viewBoolean: boolean[];
}

const DiagnosisDetailResult = ({ viewBoolean }: Props) => {
  console.log(viewBoolean);
  return (
    <div className="col-span-full">
      <AlopeciaDiagnosisBox>
        <div className="title">상세 진단</div>
        <DiagnosisBox>
          {/* {scalpType && scalpType.type0 && <div>"건강형" </div>} */}

          {viewBoolean && viewBoolean[0] && (
            <div>
              <DiagnosisDetailToggle
                diagnosisTitle="건성형 두피"
                diagnosisContent=" “유. 수분의 밸런스 붕괴로 인한 두피면역력 저하가 트러블을 유발하는 두피유형
                입니다. 건성 두피는 피지 분비가 부족해 말라있는 것이 특징입니다. 말라있는 두피를 건강하기 위해서는, 유분과 수분의 밸런스를 조절해 맞춰주는 것이 좋습니다. 말라있기 때문에 가려운 증상을 보이곤 하는데, 그럴 때 샴푸를 더 한다거나 머리를 자주 감는 것은 부족한 두피의 유분을 더 소실시키는 결과를 초래합니다. 샴푸는 하루에 한 번 하는 것이 좋고, 두피 세럼이나 트리트먼트, 헤어 마스크 등을 사용해 두피에 충분한 영양을 공급해주는 방식으로 관리할 수 있습니다. ”"
              />
            </div>
          )}
          {viewBoolean && viewBoolean[1] && (
            <div>
              <DiagnosisDetailToggle
                diagnosisTitle="지성형 두피"
                diagnosisContent=" “두피 면역력 저하, 세균 감염, 물리적 자극, 화학약품에 의한 노출, 질병 등으로 인하여 발생되는 두피유형입니다. 지성 두피는 피지가 과도하게 분비된 상태가 특징입니다. 필요한 유분막의 양보다 많은 피지가 두피에 쌓이고 오염물들이 피지에 붙어있는 것이 문제가 되기 때문에, 이들을 제거하는 방향으로 두피관리를 해야 합니다. 피지 분비를 조절하는 성분이 들어있는 샴푸나 헤어 토너 등을 사용하고, 지성용 샴푸로 두피를 깨끗하게 씻어주시는 것이 좋습니다. 샴푸 브러시나 두피 마사지기를 사용해 혈액순환을 촉진하고 각질을 제거하는 것도 추천하는 방법입니다. ”"
              />
            </div>
          )}
          {viewBoolean && viewBoolean[2] && (
            <div>
              <DiagnosisDetailToggle
                diagnosisTitle="민감형 두피"
                diagnosisContent=" “두피 면역력 저하, 세균 감염, 물리적 자극, 화학약품에 의한 노출, 질병 등으로 인하여 발생되는 두피유형입니다. 민감성 두피는 곧 두피가 약해진 상태라고도 말할 수 있습니다. 약한 자극에도 쉽게 트러블이 발생하는 상태이기 때문에 청결하게 상태를 유지하고 진정 효과가 있는 성분으로 두피를 안정시켜주는 방향으로 관리할 수 있습니다. 두피 세럼 등으로 유분과 수분을 관리하면서 진정시켜주는 방법을 추천드립니다. 염증이 생기기 쉬운 두피 상태이기 때문에 지성 두피와 함께 지루성 두피염이 발생하기 쉬운 타입입니다.  ”"
              />
            </div>
          )}
          {viewBoolean && viewBoolean[3] && (
            <div>
              <DiagnosisDetailToggle
                diagnosisTitle="비듬형 두피"
                diagnosisContent=" “비듬균의 이상증식이 두피자극을 유발하여 과각질화를 유발하는 두피유형입니다. 비듬성 두피는 두피를 깨끗하게 유지하는  위생관리가 무엇보다도 중요합니다. 또한, 두피를 건조하지 않게 하며, 머리는 하루에 한번 감는 것이 좋습니다. 항진균제가 포함된 샴푸를 일주일에 2~3회 사용하는 것도 도움이 됩니다. 비듬은 완전히 치료되지 않고 종종 재발하므로 꾸준히 두피를 청결하게 유지하고 건조해지지 않도록 관리하는 것이 중요합니다. ”"
              />
            </div>
          )}
          {viewBoolean && viewBoolean[4] && (
            <div>
              <DiagnosisDetailToggle
                diagnosisTitle="염증형 두피"
                diagnosisContent=" “모낭충, 세균감염, 화학약품에 의한 자극 등으로 두피조직에 염증반응이 나타나는 두피유형입니다. 염증성 두피는 많은 사람들이 치료에 어려움을 겪고 있으면서 또 쉽게 재발하는 유형입니다. 이 유형은 피부과에서 치료를 받는 것만큼 스스로 관리하는 것이 완치에 중요한 역할을 합니다. 두피를 습하지 않게 관리하고, 모자를 쓰지 않거나 기름진 음식을 줄이는 것이 좋습니다. 또, 손톱 등으로 머리를 긁지 않고 스트레스를 관리하는 것도 도움이 됩니다. ”"
              />
            </div>
          )}
          {viewBoolean && viewBoolean[5] && (
            <div>
              <DiagnosisDetailToggle
                diagnosisTitle="탈모진행형 두피"
                diagnosisContent=" “유전적 요인 및 잘못된 후천적 습관으로 모발의 굵기가 가늘어지고 탈모량이 증가하는 두피유형입니다. 탈모성 두피는 두피 건강을 챙기는 것과 동시에 적절한 의약품을 사용하는 것이 중요합니다. 먹는 약이나 바르는 약 등을 사용해 탈모 진행을 막고, 두피는 각질이나 피지를 잘 제거해 모공을 막지 않도록 신경 써주는 방법으로 모량을 유지하는 관리를 해나가야 합니다. 두피 마사지기나 LED 두피 치료기 등을 함께 병행해서 사용하는 방법도 있습니다.”
                "
              />
            </div>
          )}
          {viewBoolean && viewBoolean[6] && (
            <div>
              <DiagnosisDetailToggle
                diagnosisTitle="건강형 두피"
                diagnosisContent="“완벽한 두피 상태! 
                건성, 지성, 염증, 비듬, 자극, 탈모에서 자유로운 당신의 두피는 건강형입니다. 지금 그대로 생활 습관을 유지해주세요.”"
              />
            </div>
          )}
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
