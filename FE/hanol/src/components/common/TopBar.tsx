type TapBarProps = {
  name: string;
};

function TapBar({ name }: TapBarProps) {
  return (
    <div className="h-[3.75rem] flex items-center mb-[2rem]">
      <div className="flex ">
        <div className="font-bold text-[1.25rem]">{name}</div>
      </div>
    </div>
  );
}

export default TapBar;
