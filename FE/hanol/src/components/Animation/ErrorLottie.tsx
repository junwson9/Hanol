import Lottie from 'lottie-react';
import Error from 'assets/lottie/Error.json';
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
      <Lottie className="lottie" animationData={Error} height={80} width={80} loop={false} />
    </LottieFrame>
  );
}
export default CheckLottie;
