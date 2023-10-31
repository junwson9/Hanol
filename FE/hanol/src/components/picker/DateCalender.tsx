import * as React from 'react';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DateCalendar } from '@mui/x-date-pickers/DateCalendar';

export default function CalendarBasic({ onDateChange }: { onDateChange: (date: Date | null) => void }) {
  const [isModalOpen, setIsModalOpen] = React.useState(false);
  const [selectedDate, setSelectedDate] = React.useState<Date | null>(null);

  const openModal = () => {
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
  };

  const handleDateChange = (date: Date | null) => {
    setSelectedDate(date || null);
    onDateChange(date);
    closeModal();
  };

  return (
    <div>
      <button onClick={openModal}>열기</button>

      {isModalOpen && (
        <div className="modal">
          <LocalizationProvider dateAdapter={AdapterDayjs} adapterLocale="ko">
            <DateCalendar
              showDaysOutsideCurrentMonth
              fixedWeekNumber={6}
              onChange={handleDateChange}
              value={selectedDate}
            />
          </LocalizationProvider>
          <button onClick={closeModal}>닫기</button>
        </div>
      )}
    </div>
  );
}
