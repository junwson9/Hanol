import TapBar from 'components/common/TopBar';

function About() {
  return (
    <div className="col-span-full">
      <div>
        <TapBar name="로고" />
      </div>
      <div className="h-[7rem] flex items-center justify-between border-y mb-[1rem]">
        <div className="text-[#999999] font-regular ">로그인이 필요합니다.</div>
        <button className="bg-[#F4F4F4] px-[0.5rem] py-[0.2rem] rounded-[0.3rem] text-[#555555] font-regular text-[0.813rem]">
          로그인
        </button>
      </div>
      <div className="">
        <button className="font-bold text-[1.125rem] w-full my-[1rem] text-left">알림설정</button>
        <button className="font-bold text-[1.125rem] w-full my-[1rem] text-left">ABOUT</button>
        <button className="font-bold text-[1.125rem] w-full my-[1rem] text-left">약관 및 정책</button>
        <button className="font-bold text-[1.125rem] w-full my-[1rem] text-left">오픈소스 라이브러리</button>
        <button className="font-bold text-[1.125rem] w-full my-[1rem] text-left">로그아웃</button>
      </div>
    </div>
  );
}

export default About;
