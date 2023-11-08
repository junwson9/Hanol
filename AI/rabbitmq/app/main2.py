from fastapi import FastAPI
import pika
import json

app = FastAPI()
connection = pika.BlockingConnection(
    pika.ConnectionParameters(
        host='15.165.74.87',
        port=40000,
        credentials=pika.PlainCredentials(username='admin', password='ssafya205!'))
    )
channel = connection.channel()

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

def test(ch, method, properties, body):
    print("되는중1")
    try:
        data = json.loads(body)
        print(data)

        respoonse_data = {
            "a": 1,
            "b": 2
        }

        channel.basic_publish(exchange=exchange,
                            routing_key=response_routing_key,
                            body=respoonse_data)
        print("응답 큐에 전송 완료")

    except Exception as e:
        print(e)

channel.exchange_declare(exchange=exchange, exchange_type='direct', durable=True)
channel.queue_bind(exchange=exchange, queue=queue_request_name, routing_key=request_routing_key)
channel.basic_consume(queue=queue_request_name, on_message_callback=test, auto_ack=True)

@app.on_event("startup")
async def startup_event():
    # RabbitMQ에서 메시지 처리 시작
    print("RabbitMQ 메시지 처리 시작")
    channel.start_consuming()
    print("됨?")