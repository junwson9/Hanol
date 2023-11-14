import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';
import { RecoilRoot } from 'recoil';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import * as serviceWorkerRegistration from './serviceWorkerRegistration';
import ReactGA from 'react-ga4';
import { useEffect } from 'react';
import { useLocation } from 'react-router-dom';

const root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement);
const TRAKING_ID = process.env.REACT_APP_MEASUREMENT_ID as string;
ReactGA.initialize(TRAKING_ID);
root.render(
  <RecoilRoot>
    <BrowserRouter>
      <ScrollToTop />
      <App />
    </BrowserRouter>
  </RecoilRoot>,
);

reportWebVitals();

serviceWorkerRegistration.register();

export default function ScrollToTop() {
  const { pathname } = useLocation();

  useEffect(() => {
    console.log(11111);
    console.log('Page Loaded. Scroll Position:', window.scrollY);
    window.scrollTo(0, 1);
  }, [pathname]);

  return null;
}
