import ErrorLottie from 'components/Animation/ErrorLottie';
import { useNavigate } from 'react-router';
import TopBarDepth2 from 'components/common/TapBarDepth2';

function DiagnoseError() {
  const navigate = useNavigate();
  const handleNavigate = () => {
    navigate('/diagnose');
  };
  return (
    <div className="relative col-span-full h-screen">
      <div className="col-start-1 col-end-7">
        <TopBarDepth2
          name=""
          onClick={() => {
            handleNavigate();
          }}
          propsIsBack={false}
        />
      </div>
      <div className="col-start-2 col-end-6 mt-36">
        <ErrorLottie />
      </div>
      <p className="col-span-full font-bold text-[1.125rem] whitespace-nowrap">문제가 발생했습니다!</p>
      <p className="col-start-2 col-end-6 text-[0.75rem] text-GrayForText whitespace-nowrap mt-5">
        잠시 후에 다시 시도해 주세요.
      </p>
    </div>
  );
}
export default DiagnoseError;
