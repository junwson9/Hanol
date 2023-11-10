import React from 'react';
import axiosInstance from 'api/axiosInterceptor';
import { useNavigate } from 'react-router';
import { ReactComponent as AlarmIcon } from '../../assets/icons/alarmIcon.svg';
import { useRef, useEffect } from 'react';
type RoutineButtonProps = {
  index: number;
  is_done: boolean;
  routine_id: number;
  routine_name: string;
  member_routine_log_id: number;
  member_routine_id: number | null;
  is_notification_active: boolean | null;
  notification_time: string;

  onDataChange: (isDone: boolean, achievementRates: number, index: number) => void;
};
function RoutineButton(props: RoutineButtonProps) {
  const containerRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    if (containerRef.current) {
      const containerHeight = containerRef.current.scrollHeight;
      if (containerHeight > 3.375 * 16) {
        // 3.375rem * 16px (assuming 1rem = 16px)
        containerRef.current.classList.add('col-span-full');
      } else {
        containerRef.current.classList.remove('col-span-full');
      }
    }
  }, [props.routine_name]);

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
      ref={containerRef}
      className={`w-full py-[1rem] border border-Main flex items-center justify-between gap-2.5 inline-flex rounded-[0.5rem] ${
        props.is_done ? 'border-[#999999]' : 'bg-White border-Main'
      }`}
    >
      <button onClick={handleTOAlarm} className="flex items-center">
        <div
          className={`text-Main text-base font-medium text-[1rem] text-left tracking-wide mx-[1rem] ${
            props.is_done ? 'text-[#999999]' : 'text-Main'
          }`}
        >
          {props.routine_name.includes('\\n') ? (
            <div className="whitespace-pre-line">
              {props.routine_name.split('\\n').map((line, index) => (
                <React.Fragment key={index}>
                  {line}
                  <br />
                </React.Fragment>
              ))}
            </div>
          ) : (
            props.routine_name
          )}
          {props.is_notification_active && (
            <div className="flex items-center ">
              <AlarmIcon />
              <div className="ml-[0.3rem] text-[#999999] ">{props.notification_time}</div>
            </div>
          )}
        </div>
      </button>
      <div className="flex items-center">
        <div
          onClick={handleClick}
          className={`w-[3.6rem] mx-[0.5rem] py-[0.2rem] border border-Main rounded-[0.3rem] text-[0.75rem] font-medium text-White bg-Main
          ${props.is_done ? 'border-[#999999] bg-White text-[#999999]' : 'bg-Main border-Main'}`}
        >
          {props.is_done ? '완료함' : '완료하기'}
        </div>
      </div>
    </div>
  );
}
export default RoutineButton;
