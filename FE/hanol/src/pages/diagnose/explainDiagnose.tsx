import DiagnoseExplain from 'assets/images/diagnoseExplain.png';
import FloatingButton from 'components/button/FloatingButton';
import { useNavigate } from 'react-router-dom';
import { useRecoilValue } from 'recoil';
import { MemberRoleState } from 'recoil/atoms';
import scoreEx from 'assets/images/scoreEx.png';
import TapBar from 'components/common/TopBar';
function ExplainDiagnose() {
  const navigate = useNavigate();
  const role = useRecoilValue(MemberRoleState);
  const handleClick = () => {
    if (role == 'GUEST') {
      navigate('/login-error');
    } else {
      navigate('/select-device');
    }
  };
  return (
    <div className="col-span-full">
      <div>
        <TapBar name={'두피진단'} />
      </div>
      <p className="text-lg text-left font-bold">
        두피 사진을 촬영하면
        <br /> AI가 회원님의 두피 상태를
        <br />
        분석해드려요.
      </p>
      <div className="flex mt-10 justify-center">
        <img src={DiagnoseExplain} />
      </div>
      <p className="text-lg  text-left font-bold mt-20">
        탈모 진행도, 두피 건강 상태를
        <br />
        확인 할 수 있어요.
      </p>
      <div className="flex mt-10 justify-center">
        <img src={scoreEx} className="w-64 h-64" />
      </div>
      <div className="mt-[1rem] mb-[3rem] sticky bottom-5 z-1">
        <FloatingButton name="시작하기" onClick={handleClick} />
      </div>
      <br />
      <br />
      <br />
      <br />
    </div>
  );
}
export default ExplainDiagnose;
