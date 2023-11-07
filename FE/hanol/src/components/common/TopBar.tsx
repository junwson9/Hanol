type TapBarProps = {
  name: string;
  icon?: React.ReactNode; // icon을 선택적으로 받을 수 있도록 수정
  noMargin?: boolean;
};

function TapBar({ name, icon, noMargin }: TapBarProps) {
  const marginClass = noMargin ? 'mb-[0.5rem]' : 'mb-[2rem]';
  return (
    <div className={`h-[3.75rem] flex items-center ${marginClass}`}>
      <div className="flex ">
        <div className="flex items-center font-bold text-[1.25rem]">
          <div className="mr-[0.3rem] ">{name}</div>
          <button>{icon}</button>
        </div>
      </div>
    </div>
  );
}

export default TapBar;
