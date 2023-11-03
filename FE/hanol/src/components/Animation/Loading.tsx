import Lottie from 'lottie-react';
import LoadingLottie from 'assets/lottie/Loading.json';
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
function Loading() {
  return (
    <LottieFrame>
      <Lottie className="lottie" animationData={LoadingLottie} height={80} width={80} loop={true} />
    </LottieFrame>
  );
}
export default Loading;
