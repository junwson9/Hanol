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
  // 클릭되었을 때 onClick 함수를 호출하여 선택된 날짜를 설정
  const handleDateClick = () => {
    if (!isSelected) {
      onClick(dateInfo);
    }
  };
  return (
    <button
      className={`bg-White w-[2.8rem] h-[4rem] flex flex-col justify-center items-center rounded-[0.75rem] border border-Gray ${
        isSelected ? 'bg-GrayForText' : ''
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

export default DateBox;
