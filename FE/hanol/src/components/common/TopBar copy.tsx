type TapBarProps = {
  name: string;
  icon?: React.ReactNode; // icon을 선택적으로 받을 수 있도록 수정
};

function TapBar({ name, icon }: TapBarProps) {
  return (
    <div className="h-[3.75rem] flex items-center mb-[2rem]">
      <div className="flex ">
        <div className="flex items-center font-bold text-[1.25rem]">
          <div className="mr-[0.3rem]">{name}</div>
          <button>{icon}</button>
        </div>
      </div>
    </div>
  );
}

export default TapBar;
