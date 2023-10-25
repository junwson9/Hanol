import * as React from 'react';
import { DemoItem } from '@mui/x-date-pickers/internals/demo';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import 'dayjs/locale/ko';

export default function DateValidationDisableFuture() {
  const [selectedDate, setSelectedDate] = React.useState<Date | null>(null);
  console.log(selectedDate);
  const handleDateChange = (date: Date | null) => {
    setSelectedDate(date);
  };

  return (
    <LocalizationProvider dateAdapter={AdapterDayjs} adapterLocale="ko">
      <DemoItem label="">
        <DatePicker
          value={selectedDate} // 선택된 날짜를 value 속성을 통해 전달
          onChange={handleDateChange} // 날짜가 변경될 때 호출되는 콜백 함수 설정
          disableFuture
          views={['year', 'month', 'day']}
        />
      </DemoItem>
    </LocalizationProvider>
  );
}
