import Lottie from 'lottie-react';
import AnalyzingLottie from '../../assets/lottie/Analyzing.json';
import styled from 'styled-components';

const AnalyzingAnimation = () => {
  return (
    <LottieFrame>
      <Lottie className="lottie" animationData={AnalyzingLottie} loop={true} />
    </LottieFrame>
  );
};

const LottieFrame = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;

  .lottie {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 60%;
  }
`;
export default AnalyzingAnimation;
