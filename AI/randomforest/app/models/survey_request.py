from pydantic import BaseModel

class SurveyRequest(BaseModel):
    gender : str
    age : int
    answer1 : int
    answer2 : int
    answer3 : int
    answer4 : list
    answer5 : list
    answer6 : int
    answer7 : int