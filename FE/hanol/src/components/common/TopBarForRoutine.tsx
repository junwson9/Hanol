type TapBarProps = {
  name: string;
  icon?: React.ReactNode;
  onClickIcon?: () => void; // 클릭 이벤트 핸들러를 선택적으로 받을 수 있도록 수정
};

function TapBar({ name, icon, onClickIcon }: TapBarProps) {
  const handleIconClick = () => {
    if (onClickIcon) {
      onClickIcon(); // 클릭 이벤트 핸들러 호출
    }
  };

  return (
    <div className="h-[3.75rem] flex items-center mb-[2rem]">
      <div className="flex ">
        <div className="flex items-center font-bold text-[1.25rem]">
          <div className="mr-[0.3rem]">{name}</div>
          <button onClick={handleIconClick}>{icon}</button>
        </div>
      </div>
    </div>
  );
}

export default TapBar;
