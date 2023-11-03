import * as React from 'react';
import { DemoItem } from '@mui/x-date-pickers/internals/demo';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import dayjs from 'dayjs';
import 'dayjs/locale/ko';
type DateValidationProps = {
  onDateChange: (formattedDate: string | null) => void;
};
export default function DateValidationDisableFuture({ onDateChange }: DateValidationProps) {
  const [selectedDate, setSelectedDate] = React.useState<Date | null>(null);
  const handleDateChange = (date: Date | null) => {
    setSelectedDate(date);

    if (date) {
      const formatted = dayjs(date).format('YYYY-MM-DD');
      onDateChange(formatted);
    }
  };

  return (
    <LocalizationProvider dateAdapter={AdapterDayjs} adapterLocale="ko">
      <DemoItem label="">
        <DatePicker value={selectedDate} onChange={handleDateChange} disableFuture views={['year', 'month', 'day']} />
      </DemoItem>
    </LocalizationProvider>
  );
}
