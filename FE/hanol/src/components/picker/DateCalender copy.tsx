import React, { useState } from 'react';
import { Button } from '@mui/material';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';

function CustomizedDatePicker() {
  const [open, setOpen] = useState(false);

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  return (
    <div>
      <Button onClick={handleOpen}>Open DatePicker</Button>

      {open && (
        <div className="modal z-0">
          <LocalizationProvider dateAdapter={AdapterDayjs}>
            <DatePicker label="Basic date picker" />
          </LocalizationProvider>
          <Button onClick={handleClose}>Close DatePicker</Button>
        </div>
      )}
    </div>
  );
}

export default CustomizedDatePicker;
