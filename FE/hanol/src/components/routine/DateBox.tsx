import React from 'react';
type DateInfo = {
  year: number;
  month: number;
  day: number;
  dayOfWeek: string;
  achievement: number;
};

function DateBox({
  dateInfo,
  isSelected,
  onClick,
}: {
  dateInfo: DateInfo;
  isSelected: boolean;
  onClick: (dateInfo: DateInfo) => void;
}) {
  const { day, dayOfWeek, achievement } = dateInfo;
  const handleDateClick = () => {
    if (!isSelected) {
      onClick(dateInfo);
    }
  };
  // console.log(achievement);
  const roundedAchievement = Math.round(achievement);
  const bgStyle = roundedAchievement > 0 ? `bg-Main bg-opacity-[${roundedAchievement}%]` : `bg-Gray`;
  // console.log(bgStyle);
  console.log(roundedAchievement);
  return (
    <button
      className={`bg-White w-[2.8rem] h-[4rem] flex flex-col justify-center items-center rounded-[0.75rem] border border-Gray ${
        isSelected ? 'bg-[#888888]' : ''
      }`}
      onClick={handleDateClick}
    >
      <div className={`flex justify-center items-center font-regular ${isSelected ? 'text-White' : ''}`}>
        {dayOfWeek}
      </div>
      <div
        className={`flex justify-center items-center rounded-full bg-Gray text-White w-[1.875rem] h-[1.875rem] p-[0.25rem] ${bgStyle}`}
      >
        {day}
      </div>
    </button>
  );
}

export default React.memo(DateBox);
