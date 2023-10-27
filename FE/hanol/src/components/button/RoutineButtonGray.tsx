// type Routineprops = {
//   name: string;
// };

function RoutineButtonGray() {
  const name = 'name';
  return (
    <button className="w-full h-[3.375rem] border border-[#999999] flex items-center justify-between gap-2.5 inline-flex rounded-[0.5rem]">
      <div className="flex items-center">
        <div className="text-[#999999] text-base font-medium text-[1rem] tracking-wide mx-[1rem]">{name}</div>
      </div>
      <div className="flex items-center">
        <button className="w-[3.375rem] mx-[0.5rem] px-[0.6rem] py-[0.2rem] border border-[#999999] rounded-[0.3rem] text-[0.75rem] font-regular text-White bg-[#999999]">
          완료
        </button>
      </div>
    </button>
  );
}
export default RoutineButtonGray;
