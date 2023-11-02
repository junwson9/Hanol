import TopBarDepth2 from 'components/common/TapBarDepth2';
import { useNavigate } from 'react-router-dom';
function MyPage() {
  const navigate = useNavigate();
  const handleCloseClick = () => {
    navigate(-1);
  };
  return (
    <div className="col-span-full h-screen flex flex-col justify-between">
      <div>
        <TopBarDepth2 name={'마이페이지'} onClick={handleCloseClick} propsIsBack={false} completeBtn={false} />
      </div>
      <div className="items-center">
        <div className="flex flex-row jutify-between">
          <div className="text-[1rem] font-medium mb-[2rem]">이름</div>
          <div>hi</div>
        </div>
        <div className="text-[1rem] font-medium mb-[2rem]">성별</div>
        <div className="text-[1rem] font-medium mb-[2rem]">생년월일</div>
      </div>
      <div>
        <div className="items-center">hi</div>
      </div>
    </div>
  );
}
export default MyPage;
