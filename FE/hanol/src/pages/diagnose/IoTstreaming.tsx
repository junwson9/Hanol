import { useRef, useEffect } from 'react';
function IoTstreaming() {
  const videoRef = useRef<HTMLVideoElement | null>(null);

  useEffect(() => {
    const socket = new WebSocket('ws://raspberrypi_ip:8765'); // Replace with your Raspberry Pi's IP address

    socket.onmessage = (event) => {
      const frame_base64 = event.data;
      const frame = 'data:image/jpeg;base64,' + frame_base64;

      if (videoRef.current) {
        videoRef.current.src = frame;
      }
    };

    return () => {
      socket.close();
    };
  }, []);

  return (
    <div>
      <video ref={videoRef} autoPlay controls />
    </div>
  );
}

export default IoTstreaming;
