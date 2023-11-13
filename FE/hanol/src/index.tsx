import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';
import { RecoilRoot } from 'recoil';
import './index.css';
import reportWebVitals from './reportWebVitals';
import * as serviceWorkerRegistration from './serviceWorkerRegistration';
import ReactGA from 'react-ga4';
import { AppWrapper } from 'AppWrapper';

const root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement);
const TRAKING_ID = process.env.REACT_APP_MEASUREMENT_ID as string;
ReactGA.initialize(TRAKING_ID);
root.render(
  <RecoilRoot>
    <BrowserRouter>
      <AppWrapper />
    </BrowserRouter>
  </RecoilRoot>,
);

reportWebVitals();

serviceWorkerRegistration.register({
  onUpdate: () => {
    console.log('onUpdate');
  },
  onSuccess: () => {
    console.log('onSuccess');
  },
});
