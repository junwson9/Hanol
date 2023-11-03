import requests

# 업로드할 이미지 파일 경로
image_path = 'rn_image_picker_lib_temp_8236168d-b5d5-459b-9007-cb9545873bb0.jpg'  # 이미지 파일의 경로를 지정합니다.

# FastAPI 서버의 엔드포인트 URL
server_url = 'http://54.180.105.65/image'  # FastAPI 서버의 URL을 지정합니다.
# server_url = 'http://localhost:8000/image'

# 이미지 파일을 업로드하기 위한 POST 요청
files = {'file': open(image_path, 'rb')}  # 이미지 파일을 파일 객체로 열어 전달합니다.
response = requests.post(server_url, files=files)

# 서버로부터의 응답 출력
print(response.status_code)
print(response.json())
