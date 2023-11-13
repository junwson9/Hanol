import { useEffect, useState } from 'react';

const useA2HS = () => {
  const [deferredPrompt, setDeferredPrompt] = useState(null);

  useEffect(() => {
    const handler = (e) => {
      e.preventDefault();
      setDeferredPrompt(e);
    };

    window.addEventListener('beforeinstallprompt', handler);

    return () => {
      window.removeEventListener('beforeinstallprompt', handler);
    };
  }, []);

  const installApp = () => {
    deferredPrompt?.prompt();
    deferredPrompt?.userChoice.then(() => {
      clearPrompt();
    });
  };

  const clearPrompt = () => {
    setDeferredPrompt(null);
  };

  return { deferredPrompt, installApp, clearPrompt };
};

const A2HS = () => {
  const { deferredPrompt, installApp, clearPrompt } = useA2HS();

  return deferredPrompt ? (
    <div>
      <button onClick={clearPrompt}>취소</button>
      <button onClick={installApp}>홈 화면에 추가</button>
    </div>
  ) : null;
};

export default A2HS;
