from fastapi import FastAPI, UploadFile
from api.diagnostic_ai import DiagnosticAI
import pika
import time

app = FastAPI()
connection = pika.BlockingConnection(pika.ConnectionParameters('localhost:5672'))
channel = connection.channel()

diag = DiagnosticAI()

# 메시지 받을 큐 이름
queue_subscribe_name = 'scalp-diagnose-request-queue'
# 로직 처리하고 다시 전달할 큐 이름
queue_provide_name = 'scalp-diagnose-response'
# exchange
exchange = 'scalp-diagnose-exchange'

def request_handler(ch, method, properties, body):
    print("rabbitmq 구독 완료")
    sse_id = body.sse_id
    image = body.image

    response = diag.process_diagnostic(image,sse_id)
    response = {
                "sse_id": sse_id, 
                "value1": response.value1, 
                "value2": response.value2,
                "value3": response.value3,
                "value4": response.value4,
                "value5": response.value5,
                "value6": response.value6
                }

    print("로직 처리 완료")
    time.sleep(6)
    print("응답 반환 준비")
    channel.basic_publish(exchange=exchange,
                          routing_key=queue_provide_name,
                          body=response)
    print("응답 큐에 전송 완료")
    return "rabbitmq 잘됨"

channel.basic_consume(queue=queue_subscribe_name,
                      on_message_callback=request_handler,
                      auto_ack=True)

async def startup_event():
    print("서버 시작시 이벤트 자동 실행")
    connection.process_data_events()

app.add_event_handler("startup",startup_event)

@app.get("/test")
async def test():
    return 'test page'