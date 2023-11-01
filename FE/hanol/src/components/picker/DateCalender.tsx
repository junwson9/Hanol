import * as React from 'react';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DateCalendar } from '@mui/x-date-pickers/DateCalendar';
import { useEffect } from 'react';
import { ReactComponent as Close } from '../../assets/icons/close.svg';
type CalendarBasicProps = {
  onDateChange: (date: Date | null) => void;
  isModalOpen: boolean;
  openModal: () => void;
  closeModal: () => void;
};

export default function CalendarBasic({ onDateChange, isModalOpen, openModal, closeModal }: CalendarBasicProps) {
  const [selectedDate, setSelectedDate] = React.useState<Date | null>(null);

  const handleDateChange = (date: Date | null) => {
    setSelectedDate(date || null);
    onDateChange(date);
    closeModal();
  };

  useEffect(() => {
    if (isModalOpen) {
      openModal();
    } else {
      closeModal();
    }
  }, [isModalOpen, openModal, closeModal]);

  return (
    <div className="relative z-1000">
      {isModalOpen && (
        <div className="fixed inset-0 flex items-center justify-center bg-gray-500 bg-opacity-50">
          <div className="bg-white">
            <button onClick={closeModal} className="pt-[1rem] pl-[16rem]">
              <Close />
            </button>
            <LocalizationProvider dateAdapter={AdapterDayjs} adapterLocale="ko">
              <DateCalendar
                showDaysOutsideCurrentMonth
                fixedWeekNumber={6}
                onChange={handleDateChange}
                value={selectedDate} // 포맷팅된 날짜를 value prop으로 전달
              />
            </LocalizationProvider>
          </div>
        </div>
      )}
    </div>
  );
}
