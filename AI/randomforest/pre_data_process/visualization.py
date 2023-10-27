import joblib
import pandas as pd
import matplotlib.pyplot as plt

path = 'model/random_forest_model2.pkl'
model = joblib.load(path)

path1 = 'data/total_new_type_data.xlsx'
df = pd.read_excel(path1)

target = 'type'

features = ['Age', 'Gender', 'Answer7', 'treatment', 'Answer2', 'Answer3', 'Answer1', 'rinse']

ftr_importances_values = model.feature_importances_
ftr_importances = pd.Series(ftr_importances_values, index=features)
ftr_top20 = ftr_importances.sort_values(ascending=False)

print(ftr_top20.index)
print(ftr_top20)
