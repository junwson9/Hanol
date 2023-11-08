import pika
import json

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

data = {
    "keyId": 1,
    "sseId": 1,
    "file": 
}

channel.queue_declare(queue=queue_response_name, durable=True)
