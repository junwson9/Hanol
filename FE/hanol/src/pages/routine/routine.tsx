import FloatingButton from 'components/button/FloatingButton';
import TapBar from 'components/common/TopBar';
import { useNavigate } from 'react-router';
import { useState } from 'react';
import { ReactComponent as Error } from 'assets/icons/error.svg';
import DateBox from 'components/routine/DateBox';
import { ReactComponent as ArrorLeft } from 'assets/icons/arrow_left.svg';
import { ReactComponent as ArrorRight } from 'assets/icons/arrow_right.svg';
import { ReactComponent as Calender } from 'assets/icons/calender.svg';
import CalenderBasic from 'components/picker/DateCalender';
import RoutineButton from 'components/button/RoutineButton';
import RoutineButtonGray from 'components/button/RoutineButtonGray';
type DateInfo = {
  year: number;
  month: number;
  day: number;
  dayOfWeek: string;
};

function Routine() {
  const [selectedDate, setSelectedDate] = useState(new Date());
  const today = new Date();
  const [selectedDateInfo, setSelectedDateInfo] = useState<DateInfo | null>({
    year: today.getFullYear(),
    month: today.getMonth() + 1,
    day: today.getDate(),
    dayOfWeek: getDayOfWeekName(today.getDay()),
  });

  const handleDateBoxClick = (dateInfo: DateInfo) => {
    setSelectedDateInfo(dateInfo);
  };

  const navigate = useNavigate();
  const handleClick = () => {
    navigate('/routine');
  };

  const { weekDates } = getCurrentDateAndWeekDates(selectedDate);
  const monthDate = new Date();
  const [formattedDate] = useState(formatDate(monthDate));

  console.log('오늘 날짜:', formattedDate);
  console.log('월요일부터 일요일까지의 날짜와 해당 년도 및 월:', weekDates);

  const handlePrevDate = () => {
    const newDate = new Date(selectedDate);
    newDate.setDate(selectedDate.getDate() - 7); // 7일을 이전으로 이동
    setSelectedDate(newDate);
  };

  const handleNextDate = () => {
    const newDate = new Date(selectedDate);
    newDate.setDate(selectedDate.getDate() + 7); // 7일을 다음으로 이동
    setSelectedDate(newDate);
  };
  return (
    <div className="col-span-full flex flex-col justify-between">
      <TapBar name={weekDates[0].year.toString() + '년 ' + weekDates[0].month.toString() + '월'} icon={<Calender />} />
      <div className="flex justify-center">
        <button onClick={handlePrevDate}>
          <ArrorLeft />
        </button>
        {weekDates.map((dateInfo, index) => (
          <DateBox
            key={index}
            dateInfo={dateInfo}
            isSelected={
              selectedDateInfo !== null &&
              dateInfo.year === selectedDateInfo.year &&
              dateInfo.month === selectedDateInfo.month &&
              dateInfo.day === selectedDateInfo.day
            }
            onClick={() => handleDateBoxClick(dateInfo)}
          />
        ))}
        <button onClick={handleNextDate}>
          <ArrorRight />
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
      <div>
        <CalenderBasic />
      </div>
      <div>
        <RoutineButton />
      </div>
      <div>
        <RoutineButtonGray />
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
  console.log(date);
  const year = date.getFullYear();
  const month = date.getMonth() + 1; // 월은 0부터 시작하므로 1을 더합니다.

  // 날짜, 월, 년도를 원하는 형식으로 조합합니다.
  const formattedDate = `${year}년 ${month}월 `;

  return formattedDate;
}

function getDayOfWeekName(dayOfWeek: number): string {
  const days = ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'];
  return days[dayOfWeek];
}
