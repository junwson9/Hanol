import { useEffect } from 'react';
import { ReactComponent as Camera } from 'assets/icons/camera.svg';
import { ReactComponent as RoutineUnActive } from 'assets/icons/routine-unactive.svg';
import { ReactComponent as Routine } from 'assets/icons/routine.svg';
import { ReactComponent as ExaminationUnActive } from 'assets/icons/examination-unactive.svg';
import { ReactComponent as Examination } from 'assets/icons/examination.svg';
import { ReactComponent as AboutUnActive } from 'assets/icons/about-unactive.svg';
import { ReactComponent as About } from 'assets/icons/about.svg';
import { ReactComponent as MyReportUnActive } from 'assets/icons/myReport-unactive.svg';
import { ReactComponent as MyReport } from 'assets/icons/myReport.svg';
import { useLocation, useNavigate } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import { MemberRoleState, selectedMenuState } from 'recoil/atoms';
import { useRecoilValue } from 'recoil';
function NavBar() {
  const location = useLocation();
  const navigate = useNavigate();
  const [selectedMenu, setSelectedMenu] = useRecoilState<string>(selectedMenuState);
  const Role = useRecoilValue(MemberRoleState);
  const handleNavigate = (route: string) => {
    navigate(route);
  };
  useEffect(() => {
    const path = location.pathname;
    if (path === '/') {
      setSelectedMenu('myreport');
    } else if (path === '/examination') {
      setSelectedMenu('examination');
    } else if (path === '/routine') {
      setSelectedMenu('routine');
    } else if (path === '/about') {
      setSelectedMenu('about');
    } else if (path === '/myreport-explain') {
      setSelectedMenu('myreport-explain');
    } else if (path === '/explain-routine') {
      setSelectedMenu('explain-routine');
    } else {
      setSelectedMenu('');
    }
  }, [location]);

  return (
    <div
      className="fixed bottom-0 h-[4.5rem] max-w-[450px] w-full px-[1rem] rounded-xl border bg-White"
      style={{ zIndex: 20 }}
    >
      <div className="flex justify-between">
        <button
          className="flex flex-col items-center justify-center w-[66px] h-[66px] gap-[7px]"
          onClick={() => {
            console.log(Role);
            if (Role == 'GUEST') {
              handleNavigate('/myreport-explain');
              setSelectedMenu('myreport-explain');
            } else {
              handleNavigate('/');
              setSelectedMenu('myreport');
            }
          }}
        >
          {selectedMenu === 'myreport' || selectedMenu === 'myreport-explain' ? <MyReport /> : <MyReportUnActive />}
          <p
            className={`${
              selectedMenu === 'myreport' || selectedMenu === 'myreport-explain' ? 'text-NavActive' : 'text-GrayForText'
            } text-xs whitespace-nowrap`}
          >
            마이리포트
          </p>
        </button>
        <button
          className="flex flex-col items-center justify-center w-[66px] h-[66px] gap-[7px]"
          onClick={() => {
            handleNavigate('/examination');
            setSelectedMenu('examination');
          }}
        >
          {selectedMenu === 'examination' ? <Examination /> : <ExaminationUnActive />}
          <p
            className={`${
              selectedMenu === 'examination' ? 'text-NavActive' : 'text-GrayForText'
            } text-xs whitespace-nowrap`}
          >
            두피TI
          </p>
        </button>
        <button>
          <Camera
            onClick={() => {
              handleNavigate('/diagnose');
              setSelectedMenu('');
            }}
          />
        </button>
        <button
          className="flex flex-col items-center justify-center w-[66px] h-[66px] gap-[7px]"
          onClick={() => {
            console.log(Role);
            if (Role == 'GUEST') {
              handleNavigate('/explain-routine');
              setSelectedMenu('explain-routine');
            } else {
              handleNavigate('/routine');
              setSelectedMenu('routine');
            }
          }}
        >
          {selectedMenu === 'routine' || selectedMenu === 'explain-routine' ? <Routine /> : <RoutineUnActive />}
          <p
            className={`${
              selectedMenu === 'routine' || selectedMenu === 'explain-routine' ? 'text-NavActive' : 'text-GrayForText'
            } text-xs whitespace-nowrap`}
          >
            케어루틴
          </p>
        </button>
        <button
          className="flex flex-col items-center justify-center w-[66px] h-[66px] gap-[7px]"
          onClick={() => {
            handleNavigate('/about');
            setSelectedMenu('about');
          }}
        >
          {selectedMenu === 'about' ? <About /> : <AboutUnActive />}
          <p
            className={`${selectedMenu === 'about' ? 'text-NavActive' : 'text-GrayForText'} text-xs whitespace-nowrap`}
          >
            더보기
          </p>
        </button>
      </div>
    </div>
  );
}
export default NavBar;
