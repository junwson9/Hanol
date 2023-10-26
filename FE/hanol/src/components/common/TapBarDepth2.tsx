import { ReactComponent as Close } from '../../assets/icons/close.svg';

type TapBarDepth2Props = {
  name: string;
  onClick: () => void;
};

function TapBarDepth2({ name, onClick }: TapBarDepth2Props) {
  return (
    <div className="h-[3.75rem] flex items-center justify-between">
      <div></div>
      <div className="flex items-center ">
        <div className="font-medium text-[1.063rem]">{name}</div>
      </div>
      <div className="items-center" onClick={onClick}>
        <Close />
      </div>
    </div>
  );
}

export default TapBarDepth2;
