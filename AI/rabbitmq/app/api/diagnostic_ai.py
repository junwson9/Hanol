from fastapi import UploadFile
from PIL import Image
from io import BytesIO

class DiagnosticAI:
    def process_diagnostic(self,image_bytes:UploadFile, sse_id:int):
        print("로직 처리중")
        image = Image.open(BytesIO(image_bytes))

        #이미지 처리 로직
        image = image.convert('RGB')
        file_name = f"{sse_id}.jpg"  # 이미지 파일명 생성
        image.save(file_name)
        print("이미지 저장 완료")

        response = {
            "value1":1, 
            "value2":2, 
            "value3":2,
            "value4":0, 
            "value5":0,
            "value6":3   
        }
        return response