type RoutineButtonProps = {
  is_done: boolean;
  routine_id: number;
  routine_name: string;
};
function RoutineButton(props: RoutineButtonProps) {
  // props를 인자로 받음
  return (
    <button
      className={`w-full h-[3.375rem] border border-Main flex items-center justify-between gap-2.5 inline-flex rounded-[0.5rem] ${
        props.is_done ? 'bg-[#999999] border-[#999999]' : 'bg-White border-Main'
      }`}
    >
      <div className="flex items-center">
        <div className="text-Main text-base font-medium text-[1rem] tracking-wide mx-[1rem]">
          {props.routine_name} {/* routine_name props 사용 */}
        </div>
      </div>
      <div className="flex items-center">
        <button className="w-[3.375rem] mx-[0.5rem] px-[0.6rem] py-[0.2rem] border border-Main rounded-[0.3rem] text-[0.75rem] font-regular text-White bg-Main">
          {props.is_done ? '완료' : '했어요'} {/* is_done props 사용 */}
        </button>
      </div>
    </button>
  );
}
export default RoutineButton;
