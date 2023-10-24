from fastapi import FastAPI,UploadFile
from models.image_request import ImageRequest
from api.diagnostic_ai import DiagnosticAI

app = FastAPI()

diag = DiagnosticAI()

@app.get("/test")
async def test():
    return 'test page'

@app.post("/image")
async def process_diagnostic(file: UploadFile):
    return await diag.process_diagnostic(file)