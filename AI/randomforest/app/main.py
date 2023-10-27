from fastapi import FastAPI
from api.examination_ai import process_examination

app = FastAPI()

@app.get("/test")
async def test():
    return 'test page'

@app.post("/examinations")
async def process_examination():
    return await process_examination()