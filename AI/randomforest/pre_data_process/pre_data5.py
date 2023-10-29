import pandas as pd

df = pd.read_excel('data/total_data.xlsx')

def binary_to_decimal(binary_str):
    decimal_value = int(binary_str, 2)
    return decimal_value

df['type'] = 0

for i in range(len(df)):
    v1 = str(df.loc[i,'value_1'])
    v2 = str(df.loc[i,'value_2'])
    v3 = str(df.loc[i,'value_3'])
    v4 = str(df.loc[i,'value_4'])
    v5 = str(df.loc[i,'value_5'])
    v6 = str(df.loc[i,'value_6'])
    temp = v1+v2+v3+v4+v5+v6
    df.loc[i,'type'] = binary_to_decimal(temp)

df.to_excel('data/total_type_data.xlsx',index=False)