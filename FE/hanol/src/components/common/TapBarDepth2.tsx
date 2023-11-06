import { ReactComponent as Close } from '../../assets/icons/close.svg';
import { ReactComponent as Back } from 'assets/icons/back.svg';
import { useNavigate } from 'react-router-dom';
type TapBarDepth2Props = {
  name: string;
  onClick?: () => void;
  propsIsBack: boolean;
  completeBtn?: boolean;
};

function TapBarDepth2({ name, onClick, propsIsBack, completeBtn }: TapBarDepth2Props) {
  const navigate = useNavigate();
  return (
    <div className="h-[3.75rem] flex items-center justify-between ">
      {propsIsBack ? (
        <button className="item-center" onClick={() => navigate(-1)}>
          <Back />
        </button>
      ) : (
        <div className="w-[24px] h-[24px]"></div>
      )}
      <div className="flex items-center ">
        <div className="font-medium text-[1.063rem] whitespace-nowrap">{name}</div>
      </div>
      <button className="items-center" onClick={onClick}>
        {completeBtn ? (
          // <div className="w-[34px] h-[22px] text-center text-black text-base font-normal font-['Noto Sans KR'] leading-snug tracking-wide">
          //   완료
          // </div>
          ''
        ) : (
          <Close />
        )}
      </button>
    </div>
  );
}

export default TapBarDepth2;
