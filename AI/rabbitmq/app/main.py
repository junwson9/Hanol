from fastapi import FastAPI
import pika
import json
import requests
from base64 import b64decode
from dotenv import load_dotenv
import os
import json
import base64

app = FastAPI()
load_dotenv()
api_key = os.getenv("OPEN_API_KEY")

headers = {
         "Content-Type": "application/json",
         "Authorization": f"Bearer {api_key}"
        }

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

def request_handler(ch, method, properties, body):
    try:
        data = json.loads(body)
        key_id = data.get("key_id")
        sse_id = data.get("sse_id")
        image = data.get("image")
        image_bytes = b64decode(image)
        base64_image = base64.b64encode(image_bytes).decode('utf-8')
        
        payload = {
                "model": "gpt-4-vision-preview",
                "messages":[
                    {
                        "role": "user",
                        "content": [
                            {
                                "type": "text",
                                "text": "Is this image human scalp? "
                                        +"If yes, answer must be start with yes. "
                                        +"If no, answer must be start with no."
                                },
                            {
                                "type": "image_url",
                                "image_url":{
                                    "url": f"data:image/jpg;base64,{base64_image}"
                                    }
                                }
                            ]
                        }


                    ],
                "max_tokens": 300
                }
        response = requests.post("https://api.openai.com/v1/chat/completions", headers=headers, json=payload)

        response_json = response.text
        response_data = json.loads(response_json)
        content = response_data['choices'][0]['message']['content']
        print("gpt 응답: ",content)

        if "Yes" in content:
            flag = "Yes"
        elif "yes" in content:
            flag = "Yes"
        else:
            flag = "No"

        if flag == "Yes":
            request_url = "http://fastapi-ai:7000/image"
            
            print("서버 요청 보냄")
            response = requests.post(request_url,
                    files={'image_bytes': image_bytes},
                    data={'sse_id': sse_id})
            result = response.json()
            sse_id = result.get("sse_id")
            value1 = result.get("value1")
            value2 = result.get("value2")
            value3 = result.get("value3")
            value4 = result.get("value4")
            value5 = result.get("value5")
            value6 = result.get("value6")
            key_id = key_id
        else:
            key_id = key_id
            sse_id = sse_id
            value1 = -1
            value2 = -1
            value3 = -1
            value4 = -1
            value5 = -1
            value6 = -1
        print("서버 응답 돌아옴")
        response_data = {
                "key_id": key_id,
                "sse_id": sse_id,
                "value1": value1,
                "value2": value2,
                "value3": value3,
                "value4": value4,
                "value5": value5,
                "value6": value6
        }

        body = json.dumps(response_data).encode('utf-8')

        channel.basic_publish(exchange=exchange,
                            routing_key=response_routing_key,
                            body=body)
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
