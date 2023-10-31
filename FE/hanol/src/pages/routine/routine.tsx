import FloatingButton from 'components/button/FloatingButton';
import TapBar from 'components/common/TopBar';
import { useState, useEffect } from 'react';
import { ReactComponent as Error } from 'assets/icons/error.svg';
import DateBox from 'components/routine/DateBox';
import { ReactComponent as ArrorLeft } from 'assets/icons/arrow_left.svg';
import { ReactComponent as ArrorRight } from 'assets/icons/arrow_right.svg';
import { ReactComponent as Calender } from 'assets/icons/calender.svg';
import CalenderBasic from 'components/picker/DateCalender';
import RoutineButton from 'components/button/RoutineButton';
import axiosInstance from 'api/axiosInterceptor';
import { useNavigate } from 'react-router-dom';
import { useMemo } from 'react';

type DateInfo = {
  year: number;
  month: number;
  day: number;
  dayOfWeek: string;
};

type DailyRoutine = {
  routine_id: number;
  is_done: boolean;
  routine_name: string;
  member_routine_log_id: number;
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
  const [dailyRoutines, setDailyRoutine] = useState<DailyRoutine[]>([]);
  const handleDateBoxClick = (dateInfo: DateInfo) => {
    setSelectedDateInfo(dateInfo);
    const dateInfoString = `${dateInfo.year}-${dateInfo.month.toString().padStart(2, '0')}-${dateInfo.day
      .toString()
      .padStart(2, '0')}`;
    console.log(formatDateToYYYYMMDD(dateInfoString));

    const fetchDailyRoutine = async (dateInfoString: string) => {
      try {
        const response = await axiosInstance.get(
          `/routines/daily-routine?date=${formatDateToYYYYMMDD(dateInfoString)}`,
        );
        console.log(response);
        setDailyRoutine(response.data.data.daily_routines);
      } catch (error) {
        console.error('Error fetching daily routine:', error);
      }
    };

    fetchDailyRoutine(dateInfoString);
  };

  const navigate = useNavigate();
  const handleDateChange = (date: Date | null) => {
    console.log('선택된 날짜:', date);
  };
  const handleClick = () => {
    navigate('/set-routine');
  };
  const { weekDates } = useMemo(() => getCurrentDateAndWeekDates(selectedDate), [selectedDate]);
  console.log(weekDates);
  const handlePrevDate = () => {
    const newDate = new Date(selectedDate);
    newDate.setDate(selectedDate.getDate() - 7); // 7일을 이전으로 이동
    setSelectedDate(newDate);
    console.log(selectedDate);
  };

  const handleNextDate = () => {
    const newDate = new Date(selectedDate);
    newDate.setDate(selectedDate.getDate() + 7); // 7일을 다음으로 이동
    setSelectedDate(newDate);
  };

  const handleDataChange = (isDone: boolean, achievementRates: number, key: number) => {
    // setDailyRoutine()
    console.log(key);
    console.log('isDone:', isDone);
    console.log('achievementRates:', achievementRates);
  };

  useEffect(() => {
    const today = new Date();
    const formattedDate = formatDate(today);
    console.log(formattedDate);
    const fetchData = async () => {
      try {
        const response = await axiosInstance.get(`/routines/daily-routine?date=${formattedDate}`);
        setDailyRoutine(response.data.data.daily_routines);
        console.log(response);
      } catch (error) {
        console.error('데이터 가져오기 오류:', error);
      }
    };

    fetchData();
  }, []);

  useEffect(() => {
    const startYear = weekDates[0].year.toString().padStart(2, '0');
    const startMonth = weekDates[0].month.toString().padStart(2, '0');
    const startDay = weekDates[0].day.toString().padStart(2, '0');
    const endYear = weekDates[6].year.toString().padStart(2, '0');
    const endMonth = weekDates[6].month.toString().padStart(2, '0');
    const endDay = weekDates[6].day.toString().padStart(2, '0');
    const fetchData = async () => {
      try {
        const response = await axiosInstance.get(
          `/routines/daily-routine/achievement-rates?start-date=${startYear}-${startMonth}-${startDay}&end-date=${endYear}-${endMonth}-${endDay}`,
        );
        // setDailyRoutine(response.data.data.daily_routines);
        console.log(response);
      } catch (error) {
        console.error('데이터 가져오기 오류:', error);
      }
    };

    fetchData();
  }, [weekDates]);

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
        <CalenderBasic onDateChange={handleDateChange} />
      </div>
      <div>
        {dailyRoutines.map((routine, index) => (
          <RoutineButton
            key={index} // 고유한 키 사용
            is_done={routine.is_done}
            routine_id={routine.routine_id}
            routine_name={routine.routine_name}
            member_routine_log_id={routine.member_routine_log_id}
            onDataChange={handleDataChange}
          />
        ))}
      </div>
    </div>
  );
}

export default Routine;

// 달력을 위한 함수
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
  const month = (date.getMonth() + 1).toString().padStart(2, '0');
  const day = date.getDate().toString().padStart(2, '0');
  return `${year}-${month}-${day}`;
}

function getDayOfWeekName(dayOfWeek: number): string {
  const days = ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'];
  return days[dayOfWeek];
}

function formatDateToYYYYMMDD(dateInfo: string) {
  const [year, month, day] = dateInfo.split('-');
  const formattedMonth = month.length === 1 ? `0${month}` : month;
  const formattedDay = day.length === 1 ? `0${day}` : day;
  return `${year}-${formattedMonth}-${formattedDay}`;
}
