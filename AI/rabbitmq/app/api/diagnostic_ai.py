from fastapi import UploadFile
from PIL import Image
from io import BytesIO

class DiagnosticAI:
    async def process_diagnostic(self,image_bytes:UploadFile, sse_id:int):
        print("로직 처리중")
        image_content = await image_bytes.read()
        image = Image.open(BytesIO(image_content))

        image.save(image_bytes[:10]+".jpg")
        print("이미지 저장 완료")

        return {"value1":1, "value2":2, "value3":2, "value4":0, "value5":0, "value6":3}