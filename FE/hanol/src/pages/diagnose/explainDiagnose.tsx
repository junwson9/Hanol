import { ReactComponent as DiagnoseExplain } from 'assets/images/diagnoseExplain.svg';
import FloatingButton from 'components/button/FloatingButton';
import { useNavigate } from 'react-router-dom';

function ExplainDiagnose() {
  const navigate = useNavigate();
  return (
    <div className="col-span-full">
      <p className="text-lg  text-left font-bold mt-20">
        두피 사진을 촬영하면
        <br /> AI가 회원님의 두피 상태를
        <br />
        분석해드려요.
      </p>
      <div className="mt-10">
        <DiagnoseExplain className="mx-auto" />
      </div>
      <p className="text-lg  text-left font-bold mt-20">
        탈모 진행도, 두피 건강 상태를
        <br />
        확인 할 수 있어요.
      </p>
      <div className="bg-GrayForTab h-[300px] mt-10">그래프가 들어갑니다.</div>
      <div className="sticky mt-10 bottom-5">
        <FloatingButton name="시작하기" onClick={() => navigate('/select-device')} />
      </div>
    </div>
  );
}
export default ExplainDiagnose;
