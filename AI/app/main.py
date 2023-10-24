from fastapi import FastAPI
from PIL import Image
import api

app = FastAPI()

@app.get("/test")
async def test():
    return 'test page'

@app.post("/image")
async def process_diagnostic(request:Image):
    return await api.diagnostic_ai(request)