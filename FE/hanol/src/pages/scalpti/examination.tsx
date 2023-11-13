import TapBar from 'components/common/TopBar';
import TopTab from 'components/common/TopTabNew';
import { useState } from 'react';
import Image1 from '../../assets/images/click_icon.png';
import Image2 from '../../assets/images/paper_icon.png';
import FloatingButton from 'components/button/FloatingButton';
import { useNavigate } from 'react-router';
import { useEffect } from 'react';
import axiosInstance from 'api/axiosInterceptor';
import { ReactComponent as Error } from 'assets/icons/error.svg';
import { useLocation } from 'react-router-dom';
import DesertIcon from '../../assets/images/desert_icon.png';
import ItchIcon from '../../assets/images/itch_icon.png';
import Trophy_icon from '../../assets/images/trophy_icon.png';
import fragile_icon from '../../assets/images/fragile_icon.png';
import fuel_icon from '../../assets/images/fuel_icon.png';
import octopus_icon from '../../assets/images/octopus_icon.png';
import cloud_icon from '../../assets/images/cloud_icon.png';
import DivisionRectangle from 'components/common/DivisionRectangle';

type scalpType = {
  type0: boolean;
  type1: boolean;
  type2: boolean;
  type3: boolean;
  type4: boolean;
  type5: boolean;
  type6: boolean;
};

function Examination() {
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const isTabActiveParam = queryParams.get('isTabActive');
  // false가 오른쪽 탭 active
  const [isTabActive, setTabActive] = useState<boolean>(true);
  const [name, setName] = useState<string>('');
  const [scalpType, setScalpType] = useState<scalpType | null>();

  console.log(isTabActive);
  const navigate = useNavigate();
  const handleTabClick = () => {
    setTabActive((prevActive: boolean) => !prevActive);
  };
  const navToSetScalp = () => {
    navigate('/set-scalpti1');
  };

  useEffect(() => {
    if (isTabActiveParam == 'false') {
      setTabActive(false);
    }
  }, []);
  useEffect(() => {
    if (isTabActive == true) {
      return;
    }
    const fetchData = async () => {
      try {
        const response = await axiosInstance.get(`/examinations`);
        console.log(response.data.data);
        setScalpType(response.data.data);
      } catch (error) {
        console.error('데이터 가져오기 오류:', error);
        navigate('/login-error');
      }
    };
    const fetchMemberData = async () => {
      try {
        const response = await axiosInstance.get('/members/info');
        console.log(response.data.data);
        setName(response.data.data.name);
      } catch (error) {
        console.error(error);
        navigate('/login-error');
      }
    };
    fetchData();
    fetchMemberData();
  }, [isTabActive]);

  return (
    <>
      <div className="grid grid-cols-6 gap-[10px] mx-[23px]">
        <div className="col-span-full">
          <div>
            <TapBar name={'두피TI'} noMargin={true} />
          </div>
        </div>
      </div>
      <div>
        <TopTab active={isTabActive} title1="두피TI" title2="상세결과" onTabClick={handleTabClick} />
      </div>

      {isTabActive ? (
        <div className="grid grid-cols-6 gap-[10px] mx-[23px]">
          <div className="col-span-full">
            <div>
              <p className="text-[1.25rem] text-left font-bold mt-[3rem] whitespace-nowrap">
                사진 촬영없이 몇 번의 클릭만으로
                <br />내 두피 상태를 진단해보아요.
              </p>
              <div className="mt-[1rem] flex justify-center">
                <img className="w-[12.5rem] h-[12.rem]" src={Image1} alt="Image1" />
              </div>
              <p className="text-[1.25rem] text-left font-bold mt-[3rem] whitespace-nowrap">
                내 생활 습관을 AI가 분석하여
                <br />
                두피 상태를 예측해드려요.
              </p>
              <div className="flex justify-center">
                <img className="w-[12.5rem] h-[12.rem]" src={Image2} alt="Image2" />
              </div>
              <div className="my-[2rem] sticky bottom-5 mb-[3rem] z-1">
                <FloatingButton name={'두피TI 시작하기'} onClick={navToSetScalp} />
              </div>
            </div>
            <br />
            <br />
            <br />
            <br />
          </div>
        </div>
      ) : scalpType === null ? (
        <div className="grid grid-cols-6 gap-[10px] mx-[23px]">
          <div className="col-span-full">
            <div className="mt-[6rem]">
              <div className="flex justify-center">
                <Error />
              </div>
              <div className="mt-[1rem]">문진 결과가 없어요.</div>
            </div>
          </div>
        </div>
      ) : (
        <div>
          <p className="text-[1.125rem] mx-[23px] text-left font-regular mt-[2rem] whitespace-nowrap">
            {name}님,
            <br />
            지금 생활습관을 계속 유지하시면
            <br />
            <div className="font-bold">
              {scalpType && scalpType.type0 && <div>"건강형" </div>}
              {scalpType && scalpType.type1 && <div>"건성형" </div>}
              {scalpType && scalpType.type2 && <div>"지성형" </div>}
              {scalpType && scalpType.type3 && <div>"민감형" </div>}
              {scalpType && scalpType.type4 && <div>"염증형" </div>}
              {scalpType && scalpType.type5 && <div>"비듬형" </div>}
              {scalpType && scalpType.type6 && <div>"탈모진행형" </div>}
            </div>
            두피가 될 확률이 높습니다.
          </p>
          <DivisionRectangle />
          <div className="my-[2rem] mx-[4rem]">
            {scalpType && scalpType.type0 && (
              <div>
                <div className="font-bold text-[1.125rem]">건강형 </div>
                <div className="flex justify-center">
                  <img src={Trophy_icon} className="w-[14rem] h-[14rem]" />
                </div>
                <div className="text-[0.8125rem]">
                  “완벽한 두피 상태! 건성, 지성, 염증, 비듬, 자극, 탈모에서 자유로운 당신의 두피는 건강형입니다. 지금
                  그대로 생활 습관을 유지해주세요.”
                  <br />
                </div>
              </div>
            )}
            {scalpType && scalpType.type1 && (
              <div>
                <div className="font-bold text-[1.125rem]">건성형 </div>
                <div className="flex justify-center">
                  <img src={DesertIcon} className="w-[14rem] h-[14rem]"></img>
                </div>
                <div className="text-[0.8125rem]">
                  “유. 수분의 밸런스 붕괴로 인한 두피면역력 저하가 트러블을 유발하는 두피유형 입니다. <br />
                  건성 두피는 피지 분비가 부족해 말라있는 것이 특징입니다. 말라있는 두피를 건강하기 위해서는, 유분과
                  수분의 밸런스를 조절해 맞춰주는 것이 좋습니다. 말라있기 때문에 가려운 증상을 보이곤 하는데, 그럴 때
                  샴푸를 더 한다거나 머리를 자주 감는 것은 부족한 두피의 유분을 더 소실시키는 결과를 초래합니다. 샴푸는
                  하루에 한 번 하는 것이 좋고, 두피 세럼이나 트리트먼트, 헤어 마스크 등을 사용해 두피에 충분한 영양을
                  공급해주는 방식으로 관리할 수 있습니다. ”<br />
                </div>
              </div>
            )}
            {scalpType && scalpType.type2 && (
              <div>
                <div className="font-bold text-[1.125rem]">지성형 </div>
                <div className="flex justify-center">
                  <img src={fuel_icon} className="w-[14rem] h-[14rem]"></img>
                </div>
                <div className="text-[0.8125rem]">
                  “두피 면역력 저하, 세균 감염, 물리적 자극, 화학약품에 의한 노출, 질병 등으로 인하여 발생되는
                  두피유형입니다. <br />
                  지성 두피는 피지가 과도하게 분비된 상태가 특징입니다. 필요한 유분막의 양보다 많은 피지가 두피에 쌓이고
                  오염물들이 피지에 붙어있는 것이 문제가 되기 때문에, 이들을 제거하는 방향으로 두피관리를 해야 합니다.
                  피지 분비를 조절하는 성분이 들어있는 샴푸나 헤어 토너 등을 사용하고, 지성용 샴푸로 두피를 깨끗하게
                  씻어주시는 것이 좋습니다. 샴푸 브러시나 두피 마사지기를 사용해 혈액순환을 촉진하고 각질을 제거하는
                  것도 추천하는 방법입니다. ” <br />
                </div>
              </div>
            )}
            {scalpType && scalpType.type3 && (
              <div>
                <div className="font-bold text-[1.125rem]">민감형 </div>
                <div className="flex justify-center">
                  <img src={fragile_icon} className="w-[14rem] h-[14rem]"></img>
                </div>
                <div className="text-[0.8125rem]">
                  “두피 면역력 저하, 세균 감염, 물리적 자극, 화학약품에 의한 노출, 질병 등으로 인하여 발생되는
                  두피유형입니다. <br />
                  민감성 두피는 곧 두피가 약해진 상태라고도 말할 수 있습니다. 약한 자극에도 쉽게 트러블이 발생하는
                  상태이기 때문에 청결하게 상태를 유지하고 진정 효과가 있는 성분으로 두피를 안정시켜주는 방향으로 관리할
                  수 있습니다. 두피 세럼 등으로 유분과 수분을 관리하면서 진정시켜주는 방법을 추천드립니다. 염증이 생기기
                  쉬운 두피 상태이기 때문에 지성 두피와 함께 지루성 두피염이 발생하기 쉬운 타입입니다. ”
                  <br />
                </div>
              </div>
            )}
            {scalpType && scalpType.type4 && (
              <div>
                <div className="font-bold text-[1.125rem]">염증형 </div>
                <div className="flex justify-center">
                  <img src={ItchIcon} className="w-[14rem] h-[14rem]"></img>
                </div>
                <div className="text-[0.8125rem]">
                  “모낭충, 세균감염, 화학약품에 의한 자극 등으로 두피조직에 염증반응이 나타나는 두피유형입니다. <br />
                  염증성 두피는 많은 사람들이 치료에 어려움을 겪고 있으면서 또 쉽게 재발하는 유형입니다. 이 유형은
                  피부과에서 치료를 받는 것만큼 스스로 관리하는 것이 완치에 중요한 역할을 합니다. 두피를 습하지 않게
                  관리하고, 모자를 쓰지 않거나 기름진 음식을 줄이는 것이 좋습니다. 또, 손톱 등으로 머리를 긁지 않고
                  스트레스를 관리하는 것도 도움이 됩니다. ””
                  <br />
                </div>
              </div>
            )}
            {scalpType && scalpType.type5 && (
              <div>
                <div className="font-bold text-[1.125rem]">비듬형 </div>
                <div className="flex justify-center">
                  <img src={cloud_icon} className="w-[14rem] h-[14rem]"></img>
                </div>
                <div className="text-[0.8125rem]">
                  “비듬균의 이상증식이 두피자극을 유발하여 과각질화를 유발하는 두피유형입니다. <br />
                  비듬성 두피는 두피를 깨끗하게 유지하는 위생관리가 무엇보다도 중요합니다. 또한, 두피를 건조하지 않게
                  하며, 머리는 하루에 한번 감는 것이 좋습니다. 항진균제가 포함된 샴푸를 일주일에 2~3회 사용하는 것도
                  도움이 됩니다. 비듬은 완전히 치료되지 않고 종종 재발하므로 꾸준히 두피를 청결하게 유지하고 건조해지지
                  않도록 관리하는 것이 중요합니다. ”
                  <br />
                </div>
              </div>
            )}
            {scalpType && scalpType.type6 && (
              <div>
                <div className="font-bold text-[1.125rem]">탈모진행형 </div>
                <div className="flex justify-center">
                  <img src={octopus_icon} className="w-[14rem] h-[14rem]"></img>
                </div>
                <div className="text-[0.8125rem]">
                  “유전적 요인 및 잘못된 후천적 습관으로 모발의 굵기가 가늘어지고 탈모량이 증가하는 두피유형입니다.{' '}
                  <br />
                  탈모성 두피는 두피 건강을 챙기는 것과 동시에 적절한 의약품을 사용하는 것이 중요합니다. 먹는 약이나
                  바르는 약 등을 사용해 탈모 진행을 막고, 두피는 각질이나 피지를 잘 제거해 모공을 막지 않도록 신경
                  써주는 방법으로 모량을 유지하는 관리를 해나가야 합니다. 두피 마사지기나 LED 두피 치료기 등을 함께
                  병행해서 사용하는 방법도 있습니다.”
                </div>
              </div>
            )}
          </div>
        </div>
      )}
    </>
  );
}

export default Examination;
