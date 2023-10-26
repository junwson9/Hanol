import FloatingButton from 'components/button/FloatingButton';
import TapBar from 'components/common/TopBar';
import { useNavigate } from 'react-router';
import { useState } from 'react';
import { ReactComponent as Error } from 'assets/icons/error.svg';
import { ReactComponent as ArrowLeft } from 'assets/icons/arrow_left.svg';
import { ReactComponent as ArrowRight } from 'assets/icons/arrow_right.svg';
import DateBox from 'components/routine/DateBox';

function Routine() {
  const [selectedDate, setSelectedDate] = useState<Date | null>(null);
  const [currentDate] = useState(new Date());

  const navigate = useNavigate();
  const handleClick = () => {
    navigate('/routine');
  };

  const { weekDates } = getCurrentDateAndWeekDates(currentDate);
  const monthDate = new Date();
  const [formattedDate] = useState(formatDate(monthDate));

  console.log('오늘 날짜:', formattedDate);
  console.log('월요일부터 일요일까지의 날짜와 해당 년도 및 월:', weekDates);

  const handlePrevDate = () => {
    if (selectedDateIndex > 0) {
      setSelectedDateIndex(selectedDateIndex - 1);
    }
  };

  const handleNextDate = () => {
    if (selectedDateIndex < weekDates.length - 1) {
      setSelectedDateIndex(selectedDateIndex + 1);
    }
  };
  const handleDateBoxClick = (date: Date) => {
    setSelectedDate(date);
  };

  return (
    <div className="col-span-full flex flex-col justify-between">
      <TapBar name={formattedDate} />
      <div className="flex">
        <button onClick={handlePrevDate}>
          <ArrowLeft />
        </button>
        {weekDates.map((dateInfo, index) => (
          <DateBox
            key={index}
            dateInfo={dateInfo}
            selected={
              selectedDate !== null &&
              selectedDate.toDateString() === new Date(dateInfo.year, dateInfo.month - 1, dateInfo.day).toDateString()
            }
            onClick={() => handleDateBoxClick(new Date(dateInfo.year, dateInfo.month - 1, dateInfo.day))}
          />
        ))}
        <button onClick={handleNextDate}>
          <ArrowRight />
        </button>
      </div>
      <div className="mt-[2rem]">
        <div className="flex justify-center">
          <Error />
        </div>
        <div className="mt-[1rem]">두피 건강을 위한 루틴을 설정하세요.</div>
        <div>꾸준한 실천이 건강한 모발을 만들어줄거에요.</div>
      </div>

      <div className="my-[2rem] sticky bottom-5 mb-[3rem]">
        <FloatingButton name={'두피 케어 루틴 설정하기'} onClick={handleClick} />
      </div>
    </div>
  );
}

export default Routine;

// 나머지 코드 (getCurrentDateAndWeekDates, formatDate 함수, DateBox 컴포넌트 등) 유지

function getCurrentDateAndWeekDates(currentDate: Date) {
  const daysOfWeek = ['일', '월', '화', '수', '목', '금', '토'];

  const currentDayOfWeek = currentDate.getDay();
  const daysUntilMonday = currentDayOfWeek === 0 ? 6 : currentDayOfWeek - 1;
  const monday = new Date(currentDate);
  monday.setDate(currentDate.getDate() - daysUntilMonday);
  const sunday = new Date(currentDate);
  sunday.setDate(currentDate.getDate() + (6 - currentDayOfWeek) + 1);

  return {
    weekDates: (() => {
      const weekDates = [];

      for (let i = 0; i < 7; i++) {
        const newDate = new Date(currentDate);
        newDate.setDate(currentDate.getDate() + i - currentDayOfWeek);

        const dayOfWeek = daysOfWeek[newDate.getDay()];
        weekDates.push({
          year: newDate.getFullYear(),
          month: newDate.getMonth() + 1,
          day: newDate.getDate(),
          dayOfWeek: dayOfWeek,
        });
      }

      return weekDates;
    })(),
  };
}

function formatDate(date: Date) {
  const year = date.getFullYear();
  const month = date.getMonth() + 1; // 월은 0부터 시작하므로 1을 더합니다.

  // 날짜, 월, 년도를 원하는 형식으로 조합합니다.
  const formattedDate = `${year}년 ${month}월 `;

  return formattedDate;
}
