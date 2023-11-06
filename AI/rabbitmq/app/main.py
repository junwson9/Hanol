from fastapi import FastAPI, UploadFile
from api.diagnostic_ai import DiagnosticAI
import pika
import time
import json
from base64 import b64decode


app = FastAPI()
connection = pika.BlockingConnection(
    pika.ConnectionParameters(
        host='localhost',
        port=5672,
        credentials=pika.PlainCredentials(username='admin', password='ssafya205!'))
    )
channel = connection.channel()

diag = DiagnosticAI()


# 메시지 받을 큐 이름
queue_request_name = 'scalp-diagnose-request-queue'
# 로직 처리하고 다시 전달할 큐 이름
queue_response_name = 'scalp-diagnose-response-queue'
# routing key
request_routing_key = 'scalp-diagnose-request'
response_routing_key = 'scalp-diagnose-response'
# exchange
exchange = 'scalp-diagnose-exchange'

# 응답 Queue 선언
channel.queue_declare(queue=queue_response_name, durable=True)

def request_handler(ch, method, properties, body):
    print("rabbitmq 구독 완료")
    try:
        data = json.loads(body)
        key_id = data.get("key_id")
        sse_id = data.get("sse_id")
        image_base64 = data.get("image")
        image_bytes = b64decode(image_base64)

        response = diag.process_diagnostic(image_bytes,sse_id)
        response_data = {
                    "key_id" : key_id,
                    "sse_id": sse_id, 
                    "value1": response["value1"], 
                    "value2": response["value2"], 
                    "value3": response["value3"], 
                    "value4": response["value4"], 
                    "value5": response["value5"], 
                    "value6": response["value6"], 
                    }

        print("로직 처리 완료")
        time.sleep(6)
        print("응답 반환 준비")
        channel.basic_publish(exchange=exchange,
                            routing_key=response_routing_key,
                            body=json.dumps(response_data))
        print("응답 큐에 전송 완료")
    except Exception as e:
        print(e)

channel.exchange_declare(exchange=exchange, exchange_type='direct', durable=True)
channel.queue_bind(exchange=exchange, queue=queue_request_name, routing_key=request_routing_key)
channel.basic_consume(queue=queue_request_name, on_message_callback=request_handler, auto_ack=True)

@app.on_event("startup")
async def startup_event():
    # RabbitMQ에서 메시지 처리 시작
    print("RabbitMQ 메시지 처리 시작")
    channel.start_consuming()

@app.get("/test")
async def test():
    return {"message": "Test endpoint is working!"}

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8001, reload=True)