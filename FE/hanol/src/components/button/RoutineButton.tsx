import axiosInstance from 'api/axiosInterceptor';
import { useNavigate } from 'react-router';

type RoutineButtonProps = {
  index: number;
  is_done: boolean;
  routine_id: number;
  routine_name: string;
  member_routine_log_id: number;
  member_routine_id: number | null;

  onDataChange: (isDone: boolean, achievementRates: number, index: number) => void;
};
function RoutineButton(props: RoutineButtonProps) {
  const navigate = useNavigate();
  const handleClick = async () => {
    try {
      const is_done = !props.is_done;
      const data = { is_done };
      console.log(data);
      const response = await axiosInstance.patch(
        `/routines/daily-routine/${props.member_routine_log_id}/achievement`,
        data,
      );
      props.onDataChange(
        response.data.data.updated_routine_log.is_done,
        response.data.data.achievement_rates,
        props.index,
      );
      console.log(props.index);

      console.log(response);
    } catch (error) {
      console.error('Error fetching daily routine:', error);
    }
  };

  const handleTOAlarm = () => {
    navigate(`/routine-noti-setting/${props.member_routine_id}`);
  };

  return (
    <div
      className={`w-full h-[3.375rem] border border-Main flex items-center justify-between gap-2.5 inline-flex rounded-[0.5rem] ${
        props.is_done ? 'border-[#999999]' : 'bg-White border-Main'
      }`}
    >
      <button onClick={handleTOAlarm} className="flex items-center">
        <div
          className={`text-Main text-base font-medium text-[1rem] text-left tracking-wide mx-[1rem] ${
            props.is_done ? 'text-[#999999]' : 'text-Main'
          }`}
        >
          {props.routine_name} {/* routine_name props 사용 */}
        </div>
      </button>
      <div className="flex items-center">
        <div
          onClick={handleClick}
          className={`w-[3.4rem] mx-[0.5rem] px-[0.6rem] py-[0.2rem] border border-Main rounded-[0.3rem] text-[0.75rem] font-medium text-White bg-Main
          ${props.is_done ? 'border-[#999999] bg-White text-[#999999]' : 'bg-Main border-Main'}`}
        >
          {props.is_done ? '완료' : '했어요'} {/* is_done props 사용 */}
        </div>
      </div>
    </div>
  );
}
export default RoutineButton;
