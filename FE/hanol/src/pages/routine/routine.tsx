import FloatingButton from 'components/button/FloatingButton';
import TapBar from 'components/common/TopBarForRoutine';
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
  achievement: number;
};

type DailyRoutine = {
  routine_id: number;
  is_done: boolean;
  routine_name: string;
  member_routine_log_id: number;
  member_routine_id: number | null;
  is_notification_active: boolean | null;
  notification_time: string;
};

function Routine() {
  const [selectedDate, setSelectedDate] = useState(new Date());
  const today = new Date();
  const [selectedDateInfo, setSelectedDateInfo] = useState<DateInfo | null>({
    year: today.getFullYear(),
    month: today.getMonth() + 1,
    day: today.getDate(),
    dayOfWeek: getDayOfWeekName(today.getDay()),
    achievement: 0,
  });
  const [dailyRoutines, setDailyRoutine] = useState<DailyRoutine[]>([]);
  const [achievement, setAchievement] = useState<number[]>([]);
  const [render, setRender] = useState<boolean>(false);
  console.log('======================================================', render);

  // 데이트박스 클릭되는곳
  const handleDateBoxClick = (dateInfo: DateInfo) => {
    // console.log('여기', selectedDateInfo);
    const currentDate = new Date();
    const clickedDate = new Date(dateInfo.year, dateInfo.month - 1, dateInfo.day);

    // 클릭된 날짜가 현재 날짜보다 미래인 경우 클릭 이벤트 처리하지 않음
    if (clickedDate > currentDate) {
      return;
    }
    setSelectedDateInfo(dateInfo);
    const dateInfoString = `${dateInfo.year}-${dateInfo.month.toString().padStart(2, '0')}-${dateInfo.day
      .toString()
      .padStart(2, '0')}`;
    console.log('이날짜로 조회 드간다@@@@@@@', formatDateToYYYYMMDD(dateInfoString));

    const fetchDailyRoutine = async (dateInfoString: string) => {
      try {
        const response = await axiosInstance.get(
          `/routines/daily-routine?date=${formatDateToYYYYMMDD(dateInfoString)}`,
        );
        console.log('-------------------------------', response.data);
        if (response.data.data.daily_routines.length === 0) {
          setRender(false);
        } else {
          setRender(true);
        }
        setDailyRoutine(response.data.data.daily_routines);
      } catch (error) {
        console.error('Error fetching daily routine:', error);
      }
    };

    fetchDailyRoutine(dateInfoString);
  };
  /// 이 부분부터하면됩니다 날짜가 갱신이 안되요
  const navigate = useNavigate();
  const handleDateChange = (date: Date | null) => {
    if (date !== null) {
      const calenderDate = date.toISOString().slice(0, 10);
      // console.log('선택된 날짜:', calenderDate);
      // console.log('가즈앙', weekDates);
      // const tmp = getCurrentDateAndWeekDates(new Date(calenderDate));
      // console.log('드가자', tmp.weekDates[0]);
      setSelectedDate(new Date(calenderDate));
      console.log('이거는?', selectedDate);
    }
  };

  const handleClick = () => {
    navigate('/set-routine');
  };

  const { weekDates } = useMemo(() => getCurrentDateAndWeekDates(selectedDate), [selectedDate]);

  const updatedWeekDates = weekDates.map((dateInfo, index) => ({
    ...dateInfo,
    achievement: achievement[index], // achievement 배열의 해당 인덱스 값을 가져옴
  }));
  // updatedWeekDates 배열을 사용
  console.log('바끼냐?', updatedWeekDates);
  const handlePrevDate = () => {
    const newDate = new Date(selectedDate);
    newDate.setDate(selectedDate.getDate() - 7); // 7일을 이전으로 이동
    setSelectedDate(newDate);
    // console.log('선택되는 날짜', selectedDate);
    // console.log(selectedDate);
  };

  const handleNextDate = () => {
    const newDate = new Date(selectedDate);
    newDate.setDate(selectedDate.getDate() + 7); // 7일을 다음으로 이동
    setSelectedDate(newDate);
  };

  const handleDataChange = (isDone: boolean, achievementRates: number, index: number) => {
    console.log('isDone:', isDone);
    console.log('achievementRates:', achievementRates);
    const updatedRoutines = [...dailyRoutines];
    updatedRoutines[index].is_done = isDone;

    setDailyRoutine(updatedRoutines);
  };

  const [isCalenderOpen, setIsCalenderOpen] = useState(false);

  const openCalenderModal = () => {
    setIsCalenderOpen(true);
  };

  const closeCalenderModal = () => {
    setIsCalenderOpen(false);
  };

  const handleCalenderIconClick = () => {
    openCalenderModal();
  };
  useEffect(() => {
    const today = new Date();
    const formattedDate = formatDate(today);
    const fetchData = async () => {
      try {
        const response = await axiosInstance.get(`/routines/daily-routine?date=${formattedDate}`);
        setDailyRoutine(response.data.data.daily_routines);
        console.log('일별 조회 들어가요@@@@@@@@@@@@@@');
        if (response.data.data.daily_routines.length === 0) {
          setRender(false);
        } else {
          setRender(true);
        }
        console.log(response);
      } catch (error) {
        console.error('데이터 가져오기 오류:', error);
      }
    };

    fetchData();
  }, []);

  useEffect(() => {
    console.log('weekdates가 바뀌었어요!!!!!!!!!!!');
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
        console.log(response);
        const achievementRates = response.data.data.achievement_rates;
        const achievementValues = Object.values(achievementRates) as number[];
        setAchievement(achievementValues);
      } catch (error) {
        console.error('데이터 가져오기 오류:', error);
      }
    };

    fetchData();
  }, [weekDates, dailyRoutines]);

  return (
    <div className="col-span-full flex flex-col justify-between">
      <TapBar
        name={weekDates[0].year.toString() + '년 ' + weekDates[0].month.toString() + '월'}
        icon={<Calender />}
        onClickIcon={handleCalenderIconClick}
      />
      <div className="flex justify-between z-1">
        <button onClick={handlePrevDate}>
          <ArrorLeft />
        </button>
        {updatedWeekDates.map((dateInfo, index) => (
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
      <div style={{ position: 'relative', zIndex: 1000 }}>
        <CalenderBasic
          isModalOpen={isCalenderOpen}
          onDateChange={handleDateChange}
          openModal={openCalenderModal}
          closeModal={closeCalenderModal}
        />
        {/* <CalenderBasic /> */}
      </div>
      {render ? (
        <div className="z-1 mt-[4rem]">
          {dailyRoutines.map((routine, index) => (
            <div className="mb-[1rem]" key={index}>
              <RoutineButton
                index={index}
                is_done={routine.is_done}
                routine_id={routine.routine_id}
                routine_name={routine.routine_name}
                member_routine_log_id={routine.member_routine_log_id}
                member_routine_id={routine.member_routine_id}
                is_notification_active={routine.is_notification_active}
                notification_time={routine.notification_time}
                onDataChange={handleDataChange}
              />
            </div>
          ))}
        </div>
      ) : (
        <div className="mt-[4rem] z-1">
          <div className="flex justify-center">
            <Error />
          </div>
          <div className="mt-[1rem]">두피 건강을 위한 루틴을 설정하세요.</div>
          <div>꾸준한 실천이 건강한 모발을 만들어줄거에요.</div>
        </div>
      )}
      <div className="my-[2rem] sticky bottom-5 mb-[3rem] z-1">
        <FloatingButton name={'두피 케어 루틴 설정하기'} onClick={handleClick} />
      </div>
    </div>
  );
}

export default Routine;

// 달력을 위한 함수
function getCurrentDateAndWeekDates(currentDate: Date) {
  console.log('현재 날짜' + currentDate);
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
