import os
import json
import pandas as pd

path = "survey_data"

li = list(os.listdir(path))
li_name = []
li_gender = [] # 남자 0 / 여자 1
li_age = [] # 숫자만
li_answer1 = [] # 샴푸 사용 빈도
li_answer2 = [] # 펌 주기
li_answer3 = [] # 염색 주기
li_answer4 = [] # 현재 모탈 상태
li_answer5 = [] # 현재 사용하고 있는 모발 제품
li_answer6 = [] # 맞춤두피케어 제품 사용 희망
li_answer7 = [] # 샴푸 구매시 중요시 고려하는 부분

for file_name in li:
    user_name = file_name[:-15]
    if user_name not in li_name:
        li_name.append(user_name)
        with open(os.path.join(path,file_name), 'r') as f:
            json_data = json.load(f)

        json.dumps(json_data)

        gender = json_data['gender']
        age = json_data['age']
        answer1 = json_data['answers1']
        answer2 = json_data['answers2']
        answer3 = json_data['answers3']
        answer4 = json_data['answers4']
        answer5 = json_data['answers5']
        answer6 = json_data['answers6']
        answer7 = json_data['answers7']
        
        if gender == "남":
            gender = 0
        else:
            gender = 1

        age = int(age[:-1])

        if answer6 == "예":
            answer6 = 1
        else:
            answer6 = 0

        li_gender.append(gender)
        li_age.append(age)
        li_answer1.append(answer1)
        li_answer2.append(answer2)
        li_answer3.append(answer3)
        li_answer4.append(answer4)
        li_answer5.append(answer5)
        li_answer6.append(answer6)
        li_answer7.append(answer7)

    else:
        pass

no = list(range(1, len(li_name) + 1))

data = {
    'No': no,
    'Name': li_name,
    'Gender': li_gender,
    'Age': li_age,
    'Answer1': li_answer1,
    'Answer2': li_answer2,
    'Answer3': li_answer3,
    'Answer4': li_answer4,
    'Answer5': li_answer5,
    'Answer6': li_answer6,
    'Answer7': li_answer7
}

df = pd.DataFrame(data)

df.to_excel("survey_data.xlsx", index=False)
