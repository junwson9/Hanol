from fastapi import FastAPI
from api.examination_ai import ExaiminationAI
from models.survey_request import SurveyRequest

app = FastAPI()

exam = ExaiminationAI()

@app.get("/test")
async def test():
    return 'test page'

@app.post("/examinations")
async def process_examination(request: SurveyRequest):
    return await exam.process_examination(request)