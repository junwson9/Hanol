import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';
import { RecoilRoot } from 'recoil';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import * as serviceWorkerRegistration from './serviceWorkerRegistration';
import ReactGA from 'react-ga4';

const root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement);
const TRAKING_ID = process.env.REACT_APP_MEASUREMENT_ID as string;
ReactGA.initialize(TRAKING_ID);
root.render(
  <RecoilRoot>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </RecoilRoot>,
);

reportWebVitals();

serviceWorkerRegistration.register();
