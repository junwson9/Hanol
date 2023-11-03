from fastapi import FastAPI, WebSocket, WebSocketDisconnect
import cv2
import base64
import asyncio

app = FastAPI()

cap = cv2.VideoCapture(0)
cap.set(3, 640)  # 가로 길이 설정
cap.set(4, 480)  # 세로 길이 설정

class ConnectionManager:
    def __init__(self):
        self.active_connection: WebSocket = None  # 하나의 연결만 저장
        self.active_connections: list[WebSocket] = []

    async def connect(self, websocket: WebSocket):
        if self.active_connection is not None:
            await websocket.close()
        else:
            await websocket.accept()
            self.active_connection = websocket

    def disconnect(self, websocket: WebSocket):
        if websocket == self.active_connection:
            self.active_connection = None
        self.active_connections.remove(websocket)

manager = ConnectionManager()

async def send_video_feed(websocket):
    while True:
        ret, frame = cap.read()
        if not ret:
            break

        _, buffer = cv2.imencode(".jpg", frame)
        jpg_as_text = base64.b64encode(buffer)
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
        await print('연결끊김')
    except Exception as e:
        print(f"WebSocket Error: {e}")

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8888)
