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
          rightBtnType={2}
        />
      </div>
      <div className="col-start-2 col-end-6 mt-36">
        <ErrorLottie />
      </div>
      <p className="col-span-full font-bold text-[1.125rem] whitespace-nowrap">진단할 수 없는 이미지입니다.</p>
      <p className="col-start-2 col-end-6 text-[0.75rem] text-GrayForText whitespace-nowrap mt-5">
        두피 촬영을 다시 해주세요.
      </p>
    </div>
  );
}
export default DiagnoseError;
