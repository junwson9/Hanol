import pandas as pd

path = 'data/total_data.xlsx'

df = pd.read_excel(path)

df['dandruff'] = ((df['value_1'] != 0) | (df['value_2'] != 0) | (df['value_5'] != 0)).astype(int)
df['scalp_redness'] = ((df['value_3'] != 0) | (df['value_4'] != 0)).astype(int)
df['hair_loss'] = (df['value_6'] != 0).astype(int)

df.to_excel('data/total_new_data.xlsx',index=False)