from fastapi import FastAPI, WebSocket, WebSocketDisconnect
import asyncio
from picamera2 import Picamera2
from picamera2.encoders import MJPEGEncoder
from picamera2.outputs import FileOutput
from threading import Condition
import io
import base64

app = FastAPI()

# picamera2 설정
picam2 = Picamera2()
picam2.configure(picam2.create_video_configuration(main={"size": (640, 480)}))

class ConnectionManager:
    def __init__(self):
        self.active_connection: WebSocket = None  # 하나의 연결만 저장

    async def connect(self, websocket: WebSocket):
        if self.active_connection is not None:
            await websocket.close()
        else:
            await websocket.accept()
            self.active_connection = websocket

    def disconnect(self, websocket: WebSocket):
        if websocket == self.active_connection:
            self.active_connection = None

manager = ConnectionManager()

class StreamingOutput(io.BufferedIOBase):
    def __init__(self):
        self.frame = None
        self.condition = Condition()

    def write(self, buf):
        with self.condition:
            self.frame = buf
            self.condition.notify_all()

output = StreamingOutput()
picam2.start_recording(MJPEGEncoder(), FileOutput(output))

async def send_video_feed(websocket):
    while True:
        with output.condition:
            output.condition.wait()
            frame = output.frame
        jpg_as_text = base64.b64encode(frame)
        await websocket.send_text(jpg_as_text.decode("utf-8"))
        await asyncio.sleep(0.1)

@app.websocket("/ws")
async def websocket_endpoint(websocket: WebSocket):
    await manager.connect(websocket)
    try:
        while True:
            await send_video_feed(websocket)
    except WebSocketDisconnect:
        manager.disconnect(websocket)
        print('연결끊김')
    except Exception as e:
        print(f"WebSocket Error: {e}")

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8888)
