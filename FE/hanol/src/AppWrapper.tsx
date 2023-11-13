import React, { useState } from 'react';
import App from './App';
import { Banner } from 'components/button/Banner';

export const AppWrapper = () => {
  const [showUpdate, setShowUpdate] = useState(false);

  const applyUpdate = () => {
    navigator.serviceWorker.getRegistrations().then((regs) =>
      regs.forEach((reg) => {
        console.log(1111111);

        reg.waiting?.postMessage({ type: 'SKIP_WAITING' });
      }),
    );
  };

  return (
    <>
      <Banner show={showUpdate} applyUpdate={applyUpdate} />
      <App onShow={(show) => setShowUpdate(show)} />
    </>
  );
};
