import pandas as pd

df = pd.read_excel('data/total_new_data.xlsx')

def binary_to_decimal(binary_str):
    decimal_value = int(binary_str, 2)
    return decimal_value

for i in range(len(df)):
    v1 = str(df.loc[i,'dandruff'])
    v2 = str(df.loc[i,'scalp_redness'])
    v3 = str(df.loc[i,'hair_loss'])
    temp = v1+v2+v3
    df.loc[i,'type'] = binary_to_decimal(temp)

df.to_excel('data/total_new_type_data.xlsx',index=False)