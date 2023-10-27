import pandas as pd

excel_file_path = "survey_data.xlsx"
df = pd.read_excel(excel_file_path)

# DataFrame 출력
df = df.reset_index(drop=True)
df['color_hair'] = 0
df['fake_hair'] = 0
df['move_hair'] = 0
df['hair_etc'] = 0
df['shampoo'] = 0
df['hair_styling'] = 0
df['scalp_scaling'] = 0
df['hair_assence'] = 0
df['rinse'] = 0
df['treatment'] = 0
df['scalp_serum'] = 0

for i in range(len(df)):
    answer4 = list(df['Answer4'][i].split(','))
    for j in range(len(answer4)):
        tmp = answer4[j]
        if tmp == '염색 모발':
            df.loc[i, 'color_hair'] = 1
        elif tmp == '가발 사용(붙임머리 포함)':
            df.loc[i, 'fake_hair'] = 1
        elif tmp == '모발이식/시술':
            df.loc[i, 'move_hair'] = 1
        elif tmp == '기타':
            df.loc[i, 'hair_etc'] = 1
        else:
            pass

    answer5 = list(df['Answer5'][i].split(','))
    for j in range(len(answer5)):
        tmp = answer5[j]
        if tmp == '샴푸':
            df.loc[i, 'shampoo'] = 1
        elif tmp == '헤어 스타일링제':
            df.loc[i, 'hair_styling'] = 1
        elif tmp == '두피 스케일링제':
            df.loc[i, 'scalp_scaling'] = 1
        elif tmp == '헤어 에센스':
            df.loc[i, 'hair_assence'] = 1
        elif tmp == '린스':
            df.loc[i, 'rinse'] = 1
        elif tmp == '트리트먼트':
            df.loc[i, 'treatment'] = 1
        elif tmp == '두피 세럼':
            df.loc[i, 'scalp_serum'] = 1
        else:
            pass

df.to_excel("survey_data_prepared.xlsx", index=False)
    
