import joblib
import pandas as pd

path = 'model/random_forest_model.pkl'
model = joblib.load(path)

path1 = 'data/total_new_type_data.xlsx'
df = pd.read_excel(path1)

target = 'type'

features = df.columns.difference([target])
				
new_data = pd.DataFrame({
    'Gender' : 1,
    'Age' : 20,
    'Answer1' : 1,
    'Answer2' : 0,
    'Answer3' : 0,
    'Answer6' : 0,
    'Answer7' : 0,
    'color_hair' : 0,
	'fake_hair' : 0,
	'move_hair' : 0,
	'hair_etc' : 1,
	'shampoo' : 1,
    'hair_styling' : 0,
	'scalp_scaling' : 0,
	'hair_assence' : 0,
	'rinse' : 1,
	'treatment' : 0,
    'scalp_serum' : 0
}, index=[0], columns=features)

result = model.predict(new_data)

print(result)