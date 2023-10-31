import React from 'react';
type DateInfo = {
  year: number;
  month: number;
  day: number;
  dayOfWeek: string;
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
  const { year, month, day, dayOfWeek } = dateInfo;
  console.log(year, month);
  console.log(isSelected);
  const handleDateClick = () => {
    if (!isSelected) {
      onClick(dateInfo);
    }
  };
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
      <div className="flex justify-center items-center rounded-full bg-Gray w-[1.875rem] h-[1.875rem] p-[0.25rem]">
        {day}
      </div>
    </button>
  );
}

export default React.memo(DateBox);
