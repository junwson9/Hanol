import pandas as pd

survey_data = pd.read_excel('data/survey_data_prepared.xlsx')
value_data = pd.read_excel('data/value_data.xlsx')

survey_data.drop(columns=['No'])

merge_df = value_data.merge(survey_data, on='Name', how='left')

path = 'data/total_data.xlsx'

merge_df.to_excel(path,index=False)
