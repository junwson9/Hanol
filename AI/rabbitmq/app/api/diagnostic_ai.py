from fastapi import UploadFile
from PIL import Image
from io import BytesIO

class DiagnosticAI:
    async def process_diagnostic(self,file:UploadFile):
        print("로직 처리중")
        image_bytes = await file.read()

        image = Image.open(BytesIO(image_bytes))
        image.save(image_bytes[:10]+".jpg")
        print("이미지 저장 완료")

        return {"value1":1, "value2":2}