import Lottie from 'lottie-react';
import checkLottie from 'assets/lottie/CheckLottie.json';
import styled from 'styled-components';

const LottieFrame = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;

  .lottie {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 50%;
  }
`;
function CheckLottie() {
  return (
    <LottieFrame>
      <Lottie className="lottie" animationData={checkLottie} height={200} width={200} loop={false} />
    </LottieFrame>
  );
}
export default CheckLottie;
