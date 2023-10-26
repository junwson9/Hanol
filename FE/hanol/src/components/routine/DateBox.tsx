import React, { useState } from 'react';

type DateInfo = {
  year: number;
  month: number;
  day: number;
  dayOfWeek: string;
};

type DateBoxProps = {
  dateInfo: DateInfo;
  selected: boolean;
};

function DateBox({ dateInfo }: DateBoxProps) {
  const { year, month, day, dayOfWeek } = dateInfo;
  console.log(year, month);
  const [isSelected, setIsSelected] = useState(false);

  const toggleSelected = () => {
    setIsSelected(!isSelected);
  };

  return (
    <button
      className={`w-[2.8rem] h-[4rem] flex flex-col justify-center items-center rounded-[0.75rem] border border-Gray ${
        isSelected ? 'bg-gray' : 'bg-White'
      }`}
      onClick={toggleSelected}
    >
      <div className="flex justify-center items-center font-regular">{dayOfWeek}</div>
      <div
        className={`flex justify-center items-center rounded-full ${
          isSelected ? 'bg-gray' : 'bg-Gray'
        } w-[1.875rem] h-[1.875rem] p-[0.25rem]`}
      >
        {day}
      </div>
    </button>
  );
}

export default DateBox;
