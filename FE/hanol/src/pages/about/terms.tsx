import TapBarDepth2 from 'components/common/TapBarDepth2';
function Terms() {
  return (
    <div className="col-span-full">
      <div className="">
        <TapBarDepth2 name="약관 및 정책" propsIsBack={true} />
      </div>
      <div className="mt-[3rem]">
        <button className="font-bold text-[1.125rem] w-full my-[1rem] text-left mt-[1.5rem]">서비스이용약관</button>
        <button className="font-bold text-[1.125rem] w-full my-[1rem] text-left mt-[1.5rem]">개인정보 처리방침</button>
        <button className="font-bold text-[1.125rem] w-full my-[1rem] text-left mt-[1.5rem]">민감정보 제공동의</button>
      </div>
    </div>
  );
}

export default Terms;
