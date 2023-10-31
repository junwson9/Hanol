import { useRef, useEffect } from 'react';

function IoTstreaming() {
  const videoRef = useRef<HTMLImageElement | null>(null);

  useEffect(() => {
    const socket = new WebSocket('ws://localhost:8888/ws');

    socket.onmessage = (event) => {
      if (videoRef.current) {
        videoRef.current.src = 'data:image/jpeg;base64,' + event.data;
      }
    };

    socket.onclose = (event) => {
      console.error('Socket Closed:', event);
    };

    socket.onerror = (event) => {
      console.error('Socket Error:', event);
    };

    return () => {
      socket.close();
    };
  }, []);

  return (
    <div>
      <img id="video-stream" width="640" height="480" ref={videoRef} />
    </div>
  );
}

export default IoTstreaming;
