// type Routineprops = {
//   name: string;
// };

function RoutineButton() {
  const name = 'name';
  return (
    <button className="w-full h-[3.375rem] border border-Main flex items-center justify-between gap-2.5 inline-flex rounded-[0.5rem]">
      <div className="flex items-center">
        <div className="text-Main text-base font-medium text-[1rem] tracking-wide mx-[1rem]">{name}</div>
      </div>
      <div className="flex items-center">
        <button className="w-[3.375rem] mx-[0.5rem] px-[0.6rem] py-[0.2rem] border border-Main rounded-[0.3rem] text-[0.75rem] font-regular text-White bg-Main">
          했어요
        </button>
      </div>
    </button>
  );
}
export default RoutineButton;
